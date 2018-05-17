(function() {
	'use strict';

	angular.module('app').controller('ResetPwdConfirmController',
			ResetPwdConfirmController);

	ResetPwdConfirmController.$inject = [ '$http', '$location', 'FlashService' ];
	function ResetPwdConfirmController($http, $location, FlashService) {
		var vm = this;
		vm.resetPwdConfirm = resetPwdConfirm;

		function resetPwdConfirm() {
			var token = ($location.search()).token;
			vm.dataLoading = true;
			var dataObj = {
				onlineId : vm.username,
				newPwd : vm.password,
				confirmNewPwd : vm.confirmpassword
			};
			$http.post('/sAuth/ws/reset/pwd/submit?token=' + token, dataObj)
					.success(function(response) {
						FlashService.Success(response.description, true);
						$location.path('/resetPwdSuccess');
					}).error(function(data, status, headers, config) {
						FlashService.Error(data.description);
						vm.dataLoading = false;
					});
		}
	}
})();
