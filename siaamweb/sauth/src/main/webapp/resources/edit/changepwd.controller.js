(function() {
	'use strict';

	angular.module('app')
			.controller('ChangePwdController', ChangePwdController);

	ChangePwdController.$inject = [ '$http', '$location', 'FlashService' ];
	function ChangePwdController($http, $location, FlashService) {
		var vm = this;
		vm.changePwd = changePwd;

		function changePwd() {

			vm.dataLoading = true;
			var dataObj = {
				currPwd : vm.currPwd,
				newPwd : vm.newPwd,
				confirmNewPwd : vm.confirmNewPwd
			};
			$http.post('/sAuth/ws/user/edit/pwd', dataObj).success(
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
