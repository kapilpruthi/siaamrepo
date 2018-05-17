(function() {
	'use strict';

	angular.module('app').controller('HomeController', HomeController);

	HomeController.$inject = [ '$http', '$rootScope', '$location', 'FlashService' ];
	function HomeController($http, $rootScope, $location, FlashService) {
		var vm = this;
		read();
		function read() {
			$http.get("/sAuth/ws/read/userdetails").success(
					function(data, status) {
						vm.data = data;
					}).error(function(data, status) {
				FlashService.Error("Sorry, We are unable to process your request at this time.");
				vm.dataLoading = false;
				$location.path('/login');
			});

		}
	}

})();