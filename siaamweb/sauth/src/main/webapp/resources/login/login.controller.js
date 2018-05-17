(function() {
	'use strict';

	angular.module('app').controller('LoginController', LoginController);

	LoginController.$inject = [ '$http', '$rootScope', '$location',
			'FlashService' ];
	function LoginController($http, $rootScope, $location, FlashService) {
		var vm = this;
		$rootScope.authenticated = false;
		vm.login = login;

		(function initController() {
			// reset login status
			ClearCredentials(vm.username, vm.password);
		})();

		function login() {
			vm.dataLoading = true;
			$http
					.post(
							'/sAuth/ws/loginws',
							{
								j_username : vm.username,
								j_password : vm.password
							},
							{
								headers : {
									"Content-Type" : 'application/x-www-form-urlencoded;charset=utf-8'
								},
								transformRequest : [ function(data) {
									return angular.isObject(data) ? jQuery
											.param(data) : data;
								} ]
							}).success(function(response) {
						$rootScope.authenticated = true;
						$location.path('/');
					}).error(function(data, status, headers, config) {
						$rootScope.authenticated = false;
						FlashService.Error("Invalid credentials");
						ClearCredentials();
						vm.dataLoading = false;
					});

		}

		function ClearCredentials() {
			vm.username = "";
			vm.password = "";
		}
	}

})();
