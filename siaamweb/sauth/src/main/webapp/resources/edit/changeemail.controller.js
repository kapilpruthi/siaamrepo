(function() {
	'use strict';

	angular.module('app').controller('ChangeEmailController',
			ChangeEmailController);

	ChangeEmailController.$inject = [ '$http', '$location', 'FlashService' ];
	function ChangeEmailController($http, $location, FlashService) {
		var vm = this;
		vm.changeEmail = changeEmail;

		function changeEmail() {

			vm.dataLoading = true;
			var dataObj = {
				currPwd : vm.currPwd,
				email : vm.email,
				confirmEmail : vm.confirmEmail
			};
			$http.post('/sAuth/ws/user/edit/email', dataObj).success(
					function(response) {
						FlashService.Success(response.description, true);
						$location.path('/');
					}).error(function(data, status, headers, config) {
				FlashService.Error(data.description);
				vm.dataLoading = false;

			});
		}
	}
})();
