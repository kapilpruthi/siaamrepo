(function() {
	'use strict';

	angular.module('app').controller('RegisterController', RegisterController);

	RegisterController.$inject = [ '$http', '$location', '$rootScope',
			'FlashService' ];
	function RegisterController($http, $location, $rootScope, FlashService) {
		var vm = this;
		vm.register = register;

		function register() {

			vm.dataLoading = true;
			var dataObj = {
				firstname : vm.firstname,
				lastname : vm.lastname,
				email : vm.email,
				username : vm.username,
				password : vm.password,
				confirmPassword : vm.confirmpassword
			};
			$http.post('/sAuth/ws/user/add', dataObj).success(
					function(response) {
						FlashService.Success('Registration successful', true);
						$location.path('/login');
					}).error(function(data, status, headers, config) {
				FlashService.Error(data.description);
				vm.dataLoading = false;
			});
		}

	}
})();
