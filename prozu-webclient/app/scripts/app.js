'use strict';

/**
 * @ngdoc overview
 * @name yeomanPlaygroundApp
 * @description
 * # yeomanPlaygroundApp
 *
 * Main module of the application.
 */
angular
  .module('yeomanPlaygroundApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
    ,'hateoas'
    ,'ui.bootstrap'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/create-employee', {
        templateUrl: 'views/create-employee.html',
        controller: 'CreateEmployeeCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .config(function (HateoasInterceptorProvider) {
      HateoasInterceptorProvider.transformAllResponses();
  })
  ;
