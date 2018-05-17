(function() {
	'use strict';

	angular.module('app').controller('ResetIdReqController',
			ResetIdReqController);

	ResetPwdReqController.$inject = [ '$http', '$location', 'FlashService' ];
	function ResetIdReqController($http, $location, FlashService) {
		var vm = this;
		vm.resetIdReq = resetIdReq;

		function resetIdReq() {
			vm.dataLoading = true;
			var dataObj = {
				email : vm.email,
			};
			$http.post('/sAuth/ws/reset/id/request', dataObj).success(
					function(response) {
						FlashService.Success(response.description, true);
						$location.path('/resetIdEmailSent');
					}).error(function(data, status, headers, config) {
				FlashService.Error(data.description);
				vm.dataLoading = false;
			});
		}
	}
})();
