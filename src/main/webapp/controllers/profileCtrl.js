"use strict";
(function(appName){
	var app = angular.module(appName);
	app.controller('ProfileCtrl',function($scope,NotificationService,AppConstants,$http){

    $scope.profileUpdateForm = {};
		$scope.emailAvailable = true;
		$scope.states = AppConstants.states;

		$http.get(AppConstants.GET_MY_PROFILE_URL).then(function(response){
			$scope.profileUpdateForm = response.data;
		});

		$scope.updateProfile = function() {
			$http.post(AppConstants.PROFILE_UPDATE_URL, $scope.profileUpdateForm).then(function(){
				NotificationService.success("Profile updated");
			},function(){
				NotificationService.error("Profile updation failed");
			});
		}

	});
})("fabcards");
