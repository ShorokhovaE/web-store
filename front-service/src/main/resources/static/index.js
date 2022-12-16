(function () {
    angular
        .module('market', ['ngRoute', 'ngStorage'])
        .config(config)
        .run(run);

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: 'welcome/welcome.html',
                controller: 'welcomeController'
            })
            .when('/store', {
                templateUrl: 'store/store.html',
                controller: 'storeController'
            })
            .when('/cart', {
                templateUrl: 'cart/cart.html',
                controller: 'cartController'
            })
            .when('/orders', {
                templateUrl: 'orders/orders.html',
                controller: 'ordersController'
            })
            .when('/registration', {
                templateUrl: 'registration/registration.html',
                controller: 'registrationController'
            })
            .otherwise({
                redirectTo: '/'
            });
    }

    function run($rootScope, $http, $localStorage) {
        if ($localStorage.springUser) {
            try {
                let jwt = $localStorage.springUser.token;
                let payload = JSON.parse(atob(jwt.split('.')[1]));
                let currentTime = parseInt(new Date().getTime() / 1000);
                if (currentTime > payload.exp) {
                    console.log("Token is expired!!!");
                    delete $localStorage.springUser;
                    $http.defaults.headers.common.Authorization = '';
                }
            } catch (e) {
            }

            if ($localStorage.springUser) {
                $http.defaults.headers.common.Authorization = 'Bearer ' + $localStorage.springUser.token;
            }
        }
        if (!$localStorage.CartId) {
            $http.get('http://localhost:5555/cart/api/v1/cart/generate_uuid')
                .then(function (response) {
                    $localStorage.CartId = response.data.value;
                });
        }
    }
})();


angular.module('market').controller('IndexController', function ($rootScope, $scope, $http, $location, $localStorage){
    $scope.tryToAuth = function (){
        $http.post('http://localhost:5555/auth/auth', $scope.user, $localStorage.CartId)
            .then(function successCallback(response) {
                if (response.data.token) {
                    $http.defaults.headers.common.Authorization = 'Bearer ' + response.data.token;
                    $localStorage.springUser = {username: $scope.user.username, token: response.data.token};
                    $scope.user.username = null;
                    $scope.user.password = null;

                    $location.path('/');
                }
            }, function errorCallback(response){
            });
    }

    $scope.tryToLogout = function (){
        $scope.clearUser();
        $scope.user = null;
        $location.path('/');
    }

    $scope.clearUser = function (){
        delete $localStorage.springUser;
        $http.defaults.headers.common.Authorization = '';
    }
    $scope.isLoggedIn = function (){
        if ($localStorage.springUser){
            return true;
        } else {
            return false;
        }
    }
});