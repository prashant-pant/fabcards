"use strict";
(function(appName) {
	var app = angular.module(appName);
	app.controller('HeaderCtrl', function($scope, AuthenticationService, NotificationService, $state) {
		$scope.logout = function() {
			AuthenticationService.logout();
			$state.go("login",{},{reload: true}).then(function(){
				NotificationService.success("Logged Out");
			});
		}
	});
})("fabcards");
