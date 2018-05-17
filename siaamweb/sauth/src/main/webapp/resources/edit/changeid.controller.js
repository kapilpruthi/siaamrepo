(function() {
	'use strict';

	angular.module('app').controller('ChangeIdController', ChangeIdController);

	ChangeIdController.$inject = [ '$http', '$location', 'FlashService' ];
	function ChangeIdController($http, $location, FlashService) {
		var vm = this;
		vm.changeId = changeId;

		function changeId() {

			vm.dataLoading = true;
			var dataObj = {
				currOnlineId : vm.username,
				newOnlineId : vm.newusername
			};
			$http.post('/sAuth/ws/user/edit/id', dataObj).success(
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
