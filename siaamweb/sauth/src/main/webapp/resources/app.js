(function () {
    'use strict';

    angular
        .module('app', ['ngRoute', 'ngCookies'])
        .config(config)
        .run(run);

    config.$inject = ['$routeProvider', '$locationProvider'];
    function config($routeProvider, $locationProvider) {
        $routeProvider
            .when('/', {
                controller: 'HomeController',
                templateUrl: 'home/home.view.html',
                controllerAs: 'vm'
            })

            .when('/login', {
                controller: 'LoginController',
                templateUrl: 'login/login.view.html',
                controllerAs: 'vm'
            })

            .when('/register', {
                controller: 'RegisterController',
                templateUrl: 'register/register.view.html',
                controllerAs: 'vm'
            })
            
            .when('/resetIdReq', {
            	controller: 'ResetIdReqController',
                templateUrl: 'forgotId/resetIdReq.view.html',
                controllerAs: 'vm'
            })
            
            .when('/resetIdEmailSent', {
            	templateUrl: 'forgotId/resetIdEmailSent.view.html'
            })
            
            .when('/resetPwdReq', {
            	controller: 'ResetPwdReqController',
                templateUrl: 'resetPwd/resetPwdReq.view.html',
                controllerAs: 'vm'
            })
            
            .when('/resetPwdConfirm', {
                controller: 'ResetPwdConfirmController',
                templateUrl: 'resetPwd/resetPwdConfirm.view.html',
                controllerAs: 'vm'
            })
            
            .when('/resetPwdEmailSent', {
            	templateUrl: 'resetPwd/resetPwdEmailSent.view.html'
            })
            
            .when('/resetPwdSuccess', {
            	templateUrl: 'resetPwd/resetPwdSuccess.view.html'
            })
            
            .when('/changePwd', {
                controller: 'ChangePwdController',
                templateUrl: 'edit/changepwd.view.html',
                controllerAs: 'vm'
            })
            
            .when('/changeId', {
                controller: 'ChangeIdController',
                templateUrl: 'edit/changeid.view.html',
                controllerAs: 'vm'
            })
            
            .when('/changeEmail', {
                controller: 'ChangeEmailController',
                templateUrl: 'edit/changeemail.view.html',
                controllerAs: 'vm'
            })
            .otherwise({ redirectTo: '/login' });
    }

    run.$inject = ['$rootScope', '$location', '$cookieStore', '$http'];
    function run($rootScope, $location, $cookieStore, $http) {
    	// Do any pre sets here.
    	$rootScope.$on('$locationChangeStart', function (event, next, current) {
        	// redirect to login page if not logged in and trying to access a restricted page
            var authenticatedPage = $.inArray($location.path(), ['/login', '/register', '/resetIdReq', '/resetPwdReq', '/resetPwdConfirm', '/resetPwdEmailSent','/resetIdEmailSent', '/resetPwdSuccess']) === -1;
            var loggedIn = $rootScope.authenticated;
            // also check for header X-AUTH-TOKEN presence for authenticated pages.
            if (authenticatedPage && !loggedIn) {
                $location.path('/login');
            }
        });
    }

})();