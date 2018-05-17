(function() {
	'use strict';

	angular.module('app').controller('ResetPwdReqController',
			ResetPwdReqController);

	ResetPwdReqController.$inject = [ '$http', '$location', 'FlashService' ];
	function ResetPwdReqController($http, $location, FlashService) {
		var vm = this;
		vm.resetPwdReq = resetPwdReq;

		function resetPwdReq() {

			vm.dataLoading = true;
			var dataObj = {
				onlineId : vm.username,
				email : vm.email,
			};
			$http.post('/sAuth/ws/reset/pwd/request', dataObj).success(
					function(response) {
						FlashService.Success(response.description, true);
						$location.path('/resetPwdEmailSent');
					}).error(function(data, status, headers, config) {
				FlashService.Error(data.description);
				vm.dataLoading = false;
			});
		}
	}
})();
