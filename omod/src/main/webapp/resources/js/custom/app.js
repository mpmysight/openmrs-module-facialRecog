var facialrecog = angular.module('facialrecog', ['ngRoute','ui.bootstrap']);


facialrecog.
    config(['$routeProvider', '$compileProvider', function ($routeProvider, $compileProvider) {
        $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|ftp|mailto|file):/);
        $routeProvider.when('/captureImage', {controller: CaptureCtrl,
            templateUrl: '../../moduleResources/facialrecog/partials/captureImage.html'});
        $routeProvider.otherwise({redirectTo: '/captureImage'});
    }]);

facialrecog.factory('$data', function ($http) {
    var identifyFace = function(faceImageData){
        console.log("Uploading data: "+faceImageData);
        var $return =  $http.post("identify.json", {"faceimagedata":faceImageData});
        console.log("Identification Response: "+ JSON.stringify($return));
    }

    return {identifyFace:identifyFace}
});