"use strict";
(function(appName) {
	var app = angular.module(appName, [ 'ui.router', 'ngCookies', 'ui.bootstrap' ]);

	app.config(function($stateProvider, $urlRouterProvider) {
		$urlRouterProvider.otherwise('/');
		$stateProvider.state('root', {
			url : '/',
			resolve : {
				AuthDetails : function(AuthenticationService) {
					return AuthenticationService.getAuthDetailsAsync();
				}
			},
			templateUrl : 'views/root.html',
			controller : function($state,AuthDetails,$location){
				if($state.current.name=='root'){
					if(AuthDetails.isAuthenticated) $state.go("home");
					else $state.go("login");
				}
			}
		}).state('home', {
			url : 'home',
			templateUrl : 'views/home.html',
			parent: 'root',
			needsAuth : true,
			controller : 'HomeCtrl'
		}).state('login', {
			url : 'login',
			templateUrl : 'views/login.html',
			controller: 'LoginCtrl',
			parent: 'root'
		}).state('register', {
			url : 'register',
			templateUrl : 'views/register.html',
			controller : 'RegisterCtrl',
			parent: 'root'
		}).state('profile', {
			url : 'profile',
			templateUrl : 'views/profile.html',
			controller: 'ProfileCtrl',
			parent: 'root',
			needsAuth : true
		}).state('fabcard', {
			url : 'fabcard',
			templateUrl : 'views/fabcard.html',
			controller: 'FabcardCtrl',
			parent: 'root',
			needsAuth : true,
			resolve : {
				fabCardApiResponse : function($http,AppConstants){
					return $http.get(AppConstants.GET_FABCARD_URL);
				}
			}
		}).state('booking', {
			url : 'booking/:roomType',
			templateUrl : 'views/booking.html',
			controller: 'BookingCtrl',
			parent: 'root',
			needsAuth : true,
			resolve : {
				fabCardApiResponse : function($http,AppConstants){
					return $http.get(AppConstants.GET_FABCARD_URL);
				},
				roomPriceApiResponse : function($http,AppConstants,$transition$){
					return $http.get(AppConstants.GET_ROOM_PRICE_URL,{params : $transition$.params() });
				}
			}
		});
	});

	app.run(function($rootScope, $location, $cookieStore, $http,
					AuthenticationService,$transitions,$state,NotificationService) {
				var authorization = $cookieStore.get('authorization');
				if (authorization) {
					$http.defaults.headers.common['Authorization'] = authorization;
					$rootScope.authDetails.isAuthenticated = true;
				}

				$transitions.onBefore( {}, function(transition) {
					var state = transition.to();
					if(!AuthenticationService.authDetails.isAuthenticated){
						if(state.needsAuth){
							return false;
						}
					}
				});

				$transitions.onError( {}, function(transition) {
					var state = transition.to();
					if(!AuthenticationService.authDetails.isAuthenticated){
						if(state.needsAuth){
							NotificationService.error("Authentication Required");
							$state.go("login");
						}
					}
				});

			});

})("fabcards");
