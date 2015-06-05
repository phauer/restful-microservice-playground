'use strict';

/**
 * @ngdoc function
 * @name yeomanPlaygroundApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the yeomanPlaygroundApp
 */

(function(){
  var PAGE_SIZE = 10;
  angular
  .module('yeomanPlaygroundApp')
  .factory('EmployeeRes', function ($resource) {
        return $resource('http://localhost:8080/v1/employees', {limit:PAGE_SIZE, offset:0});
  })
  .controller('MainCtrl', ['$scope', 'EmployeeRes', '$timeout', function($scope, EmployeeRes, $timeout) {
    $scope.itemsPerPage = PAGE_SIZE;
    $scope.currentPage = 1;
    $scope.maxVisiblePages = 5;
    $scope.$watch('currentPage',function(newPage, oldPage){
      var offset = (newPage - 1) * PAGE_SIZE;
      EmployeeRes.get({offset:offset, search:$scope.searchText}, function(response){
        $scope.totalItems = response.totalCount;
        $scope.employees = response.employees;
        console.log(response);
        console.log(response.employees[0]);
      });
    });

    $scope.selectCurrent = function(employee){
      $scope.selectedEmployee = employee;
      console.dir(employee);
      employee.resource("projectdays").query(function(projectdays){
        $scope.selectedEmployeeProjectDays = projectdays;
      });
    };
    $scope.sumProjectBonuses = function(projectDays){
      var sum = projectDays.reduce(function(prev, cur){
        return prev + cur.daysCount;
      }, 0);
      return sum;
    };

    //search field
    var filterTextTimeout;
    $scope.$watch('searchText', function(newSearchString){
      if (filterTextTimeout) $timeout.cancel(filterTextTimeout);
      filterTextTimeout = $timeout(function() {
        //$scope.currentPage = 1;//triggers request
        EmployeeRes.get({search:$scope.searchText}, function(response){
          $scope.totalItems = response.totalCount;
          $scope.employees = response.employees;
        });
      }, 300); // delay 300 ms
    });
  }])
  .directive('activeDirective', function() {
    return {
      restrict: 'A',
      link: function(scope, elem, attrs) {
        $(elem).click(function(){
          $(this).addClass('active');
          $(this).siblings('.active').removeClass('active');
        });
      }
    };
  })
  ;
}());
