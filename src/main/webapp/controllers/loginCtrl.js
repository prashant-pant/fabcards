"use strict";
(function(appName){
	var app = angular.module(appName);
	app.controller('LoginCtrl',function($scope,AuthenticationService,$state,NotificationService){
		$scope.loginForm = {};
		function loginSuccess(){
			$state.go("home",{},{reload: true}).then(function(){
				NotificationService.success("Logged In");				
			});
		}

		function loginFailure(){
			NotificationService.error("Authentication Failed");
		}

		$scope.login = function(){
			AuthenticationService.login($scope.loginForm.email,$scope.loginForm.password).then(loginSuccess,loginFailure);
		}

	});
})("fabcards");
