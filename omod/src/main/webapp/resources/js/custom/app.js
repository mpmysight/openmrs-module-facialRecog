var facialrecog = angular.module('facialrecog', ['ngRoute','ui.bootstrap']);


facialrecog.
    config(['$routeProvider', '$compileProvider', function ($routeProvider, $compileProvider) {
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file):/);
        $routeProvider.when('/captureImage', {controller: CaptureCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/captureImage.html'});

        $routeProvider.when('/searchPatient', {controller: SearchPatientCtrl,
                    templateUrl: '../../moduleResources/facialrecog/partials/searchPatient.html'});

        $routeProvider.otherwise({redirectTo: '/searchPatient'});
    }]);

facialrecog.factory('$data', function ($http) {
    var identifyFace = function(faceImageData){
        return $http.post("identify.json", faceImageData).then(
        (res)=>{ return $http.get("identify.json"); }
        );
    }
    var searchPatient = function(identifier){
        return $http.get("searchpatient.json?patientId="+identifier);
    }

    return {identifyFace:identifyFace, searchPatient:searchPatient}
});