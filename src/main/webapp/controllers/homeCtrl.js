"use strict";
(function(appName) {
	var app = angular.module(appName);
	app.controller('HomeCtrl', function($scope, AuthenticationService, NotificationService,$state) {
		$scope.openBookingForm = function(roomType){
			$state.go("booking",{roomType : roomType});
		}
	});
})("fabcards");
