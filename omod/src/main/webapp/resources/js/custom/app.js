var facialrecog = angular.module('facialrecog', ['ui.bootstrap']);

facialrecog.
    config(['$routeProvider', '$compileProvider', function ($routeProvider, $compileProvider) {
        $compileProvider.urlSanitizationWhitelist(/^\s*(https?|ftp|mailto|file):/);
        $routeProvider.when('/captureImage', {controller: CaptureCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/captureImage.html'});


      /*  $routeProvider.when('/cohortdefinition', {controller: CohortDefinitionCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/cohortdefinition.html'});
        $routeProvider.when('/cohortdefinition/:uuid', {controller: CohortDefinitionCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/cohortdefinition.html'});
        $routeProvider.when('/createcohortdefinition', {controller: CohortDefinitionCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/cohortdefinition.html'});*/
        $routeProvider.otherwise({redirectTo: '/captureImage'});
    }]);