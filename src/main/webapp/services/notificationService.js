"use strict";
(function(appName) {
    var app = angular.module(appName);
    app.service('NotificationService', function($uibModal) {

				var that = this;

        this.success = function(message) {
					var modalConfig = {};
					modalConfig.title = "Success !";
          modalConfig.body = message;
					modalConfig.type = "success";
					modalConfig.templateUrl = 'views/alert.html';
					var modalInstance = that.openModal(modalConfig);
          modalInstance.result.catch(function () { modalInstance.close(); });
        };

				this.error = function(message) {
					var modalConfig = {};
					modalConfig.title = "Error !";
					modalConfig.body = message;
					modalConfig.type = "error";
					modalConfig.templateUrl = 'views/alert.html';
					var modalInstance = that.openModal(modalConfig);
          modalInstance.result.catch(function () { modalInstance.close(); });
				};

        this.openModal = function(modalConfig) {
						var templateUrl = modalConfig.templateUrl || 'views/modal.html';
            var controller = modalConfig.controller || modalController;
            var size = modalConfig.size || 'sm';
            var modalInstance = $uibModal.open({
                animation: true,
                ariaLabelledBy: 'modal-title',
                ariaDescribedBy: 'modal-body',
                templateUrl: templateUrl,
                controller: controller,
                controllerAs: 'modalCtrl',
                size: size,
                resolve: {
									modalConfig: function(){
										return modalConfig;
									}
								}
            });
            return modalInstance;
        };

        function modalController($uibModalInstance,modalConfig) {
            var modalCtrl = this;
						modalCtrl.modalConfig = modalConfig;
            modalCtrl.ok = function() {
                $uibModalInstance.close('close');
            };
            modalCtrl.cancel = function() {
                $uibModalInstance.dismiss('cancel');
            };
        }

    });

})("fabcards");
