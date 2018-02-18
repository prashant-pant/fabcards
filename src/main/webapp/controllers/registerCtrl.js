"use strict";
(function(appName){
	var app = angular.module(appName);
	app.controller('RegisterCtrl',function($scope,$state,NotificationService,AppConstants,$http){

    $scope.registrationForm = {};
		$scope.emailAvailable = true;
		$scope.states = AppConstants.states;

		$scope.register = function() {
			$http.post(AppConstants.REGISTER_URL, $scope.registrationForm).then(function(){
				$state.go("login").then(function(){
						NotificationService.success("User Registration Successfull");
				});
			},function(){
				NotificationService.error("User Registration Failed");
			});
		}

		$scope.emailChanged = function() {
			$scope.emailAvailable = true;
			if (!$scope.registrationForm.email) {
				return;
			}
			$http.get(AppConstants.ACCOUNT_EXISTS_URL, {
				params : {
					emailId : $scope.registrationForm.email
				}
			}).then(function(response) {
				$scope.emailAvailable = !response.data;
			},function(response) {
			});

		}
	});
})("fabcards");
