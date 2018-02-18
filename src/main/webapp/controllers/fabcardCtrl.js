"use strict";
(function(appName){
	var app = angular.module(appName);
	app.controller('FabcardCtrl',function($scope,$state,NotificationService,AppConstants,fabCardApiResponse){
		$scope.fabcard = fabCardApiResponse.data;
		$scope.showCreateFabCardModal = function(){
			var modalConfig = {};
			modalConfig.templateUrl = 'views/createFabcards.html';
			modalConfig.controller = 'CreateFabCardCtrl';
			var modalInstance = NotificationService.openModal(modalConfig);
			modalInstance.result.then(function(data){
				$scope.fabcard = data;
			},function(){});
		}

		$scope.showRechargeFabCardModal = function(){
			var modalConfig = {};
			modalConfig.templateUrl = 'views/rechargeFabcard.html';
			modalConfig.controller = 'RechargeFabCardCtrl';
			modalConfig.size = 'md';
			modalConfig.data = {};
			modalConfig.data.fabcard = $scope.fabcard;
			var modalInstance = NotificationService.openModal(modalConfig);
			modalInstance.result.then(function(data){
				$scope.fabcard = data;
			},function(){});
		}

	});

	app.controller('CreateFabCardCtrl',function($uibModalInstance,modalConfig,$scope,AppConstants,$http,NotificationService){
		$scope.ok = function(){
			var config = {};
			config.params = $scope.createFabCardForm;
			$http.post(AppConstants.CREATE_FABCARD_URL,null,config).then(function(response){
				$uibModalInstance.close(response.data);
			},function(response){
				NotificationService.error("Failed in creating FabCard");
			});
		}
			$scope.cancel = function(){
				$uibModalInstance.dismiss('cancel');
			}
	});

	app.controller('RechargeFabCardCtrl',function($uibModalInstance,modalConfig,$scope,AppConstants,$http,NotificationService){
		$scope.rechargeFabCardForm = {};
		$scope.rechargeFabCardForm.id = modalConfig.data.fabcard.id;
		$scope.rechargeFabCardForm.uuid = modalConfig.data.fabcard.uuid;
		$scope.ok = function(){
			var config = {};
			config.params = $scope.rechargeFabCardForm;
			$http.post(AppConstants.RECHARGE_FABCARD_URL,null,config).then(function(response){
				$uibModalInstance.close(response.data);
			},function(response){
				NotificationService.error("Failed in recharging FabCard");
			});
		}
			$scope.cancel = function(){
				$uibModalInstance.dismiss('cancel');
			}
	});
})("fabcards");
