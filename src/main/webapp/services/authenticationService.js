"use strict";
(function(appName){
	var app = angular.module(appName);
	app.service('AuthenticationService',function($rootScope,$http,$cookieStore,$q,AppConstants){

		var that = this;
		var authDetails = {};
		$rootScope.authDetails = authDetails;

		this.authDetails = authDetails;

		this.getAuthDetailsAsync = function(){
			return $q(function(resolve,reject){
				if(
						(!authDetails.isAuthenticated) ||
						(authDetails.isAuthenticated && authDetails.user !== null && authDetails.user !== undefined)
					){
					resolve(authDetails);
				}else{
					$http.post(AppConstants.LOGIN_URL).then(function(response){
						authDetails.isAuthenticated = true;
						authDetails.user = response;
						resolve(authDetails);
					},function(response){
						that.logout();
						resolve(authDetails);
					});
				}
			});
		}

		this.login = function(username,password){
        	var authorization = 'Basic ' +  btoa(username + ':' + password);
        	var config = {};
        	config.headers = {};
        	config.headers.Authorization = authorization;
        	return $q(function(resolve,reject){
        		$http.post(AppConstants.LOGIN_URL, {},config)
        		.then(function(response){
        			that.setCredentials(username,password);
					authDetails.isAuthenticated = true;
					authDetails.user = response;
					resolve(authDetails);
        		},function(response){
					authDetails.isAuthenticated = false;
					authDetails.user = null;
					reject(authDetails);
        		});
        	});
        }

        this.logout = function () {
            authDetails.isAuthenticated = false;
            authDetails.user = null;
            that.clearCredentials();
        };

        this.setCredentials = function(username,password){
        	var authorization = 'Basic ' +  btoa(username + ':' + password);
        	$http.defaults.headers.common.Authorization = authorization;
        	$cookieStore.put('authorization', authorization);
        }

        this.clearCredentials = function(){
            $http.defaults.headers.common.Authorization = '';
            $cookieStore.remove('authorization');
        }

	});
})("fabcards");
