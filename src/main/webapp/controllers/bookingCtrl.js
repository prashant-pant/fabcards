"use strict";
(function(appName){
	var app = angular.module(appName);
	app.controller('BookingCtrl',function($scope,$state,NotificationService,AppConstants,$http,fabCardApiResponse,roomPriceApiResponse){
		$scope.bookingForm = {};
		$scope.fabCard = fabCardApiResponse.data;


		$scope.showCreateFabCardModal = function(){
			var modalConfig = {};
			modalConfig.templateUrl = 'views/createFabcards.html';
			modalConfig.controller = 'CreateFabCardCtrl';
			var modalInstance = NotificationService.openModal(modalConfig);
			modalInstance.result.then(function(data){
				$scope.fabCard = data;
			},function(){});
		}

		$scope.price = roomPriceApiResponse.data;
	  $scope.startDateOptions = {
	    minDate: new Date()
	  };
	  $scope.endDateOptions = {
	    minDate: new Date()
	  };

		$scope.dateChanged = function(){
			$scope.endDateOptions.minDate = $scope.startDate;
			if($scope.endDate<$scope.startDate){
				$scope.endDate = null;
				$scope.totalPrice = null;
				$scope.days = null;
			}
			if($scope.endDate && $scope.startDate){
				$scope.days = ($scope.dateDaysDiff($scope.endDate,$scope.startDate));
				$scope.totalPrice = $scope.days * $scope.price;
			}
		}

		$scope.bookingForm.roomType = roomPriceApiResponse.config.params.roomType;
		$scope.book = function(){
			$scope.bookingForm.startDate = $scope.startDate;
			$scope.bookingForm.endDate = $scope.endDate;
			$scope.bookingForm.days = $scope.days;
			$http.post(AppConstants.BOOK_ROOM_URL,{},{ params: $scope.bookingForm}).then(function(response){
				NotificationService.success(response.data);
				$state.go("home");
			},function(){
				NotificationService.error("Room booking failed");
			});
		}

		$scope.showRechargeFabCardModal = function(){
			var modalConfig = {};
			modalConfig.templateUrl = 'views/rechargeFabcard.html';
			modalConfig.controller = 'RechargeFabCardCtrl';
			modalConfig.size = 'md';
			modalConfig.data = {};
			modalConfig.data.fabcard = $scope.fabCard;
			var modalInstance = NotificationService.openModal(modalConfig);
			modalInstance.result.then(function(data){
				$scope.fabCard = data;
			},function(){});
		}

		$scope.dateDaysDiff = function(firstDate, secondDate){
		    var oneDay = 24*60*60*1000;
		    return Math.round(Math.abs((firstDate.getTime() - secondDate.getTime())/(oneDay))) + 1;
		}

	});
})("fabcards");
