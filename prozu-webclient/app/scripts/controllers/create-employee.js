'use strict';

/**
 * @ngdoc function
 * @name yeomanPlaygroundApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the yeomanPlaygroundApp
 */
angular.module('yeomanPlaygroundApp')
  .factory('EmployeeRes', function ($resource) {
        return $resource('http://localhost:8080/employees');
  })
  .controller('CreateEmployeeCtrl', ['$scope','EmployeeRes', function ($scope, EmployeeRes) {
    console.log("create")
    $scope.create = function(){
      console.log($scope.newEmployee);
      $scope.url = EmployeeRes.save($scope.newEmployee, function(u, headers){
        console.log(u);
        console.log(headers);
        //TODO access location header
      });
    };
  }]);
