function CaptureCtrl0 ($scope, $location) {
$scope.cameraStarted = false;

    if ('addEventListener' in window) {
        window.addEventListener('load', function() { document.body.className = document.body.className.replace(/\bis-loading\b/, ''); });
        document.body.className += (navigator.userAgent.match(/(MSIE|rv:11\.0)/) ? ' is-ie' : '');
    }

    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;

    if (navigator.getUserMedia) {
        navigator.getUserMedia({video: true}, handleVideo, videoError);
    }
}

function handleVideo(stream) {
    var video = document.querySelector("#videoElement");
    video.src = window.URL.createObjectURL(stream);
}

function videoError(e) {
    // do something
}


function ImageCaptureModal($scope, $rootScope,$uibModalInstance, $data){
    $scope.loadImage = function(){
        var canvas_capture = document.getElementById('capturedImage');
        console.log("Captured Image");
        console.log($rootScope.capturedImageData);
        if($rootScope.capturedImageData != null){
            canvas_capture.getContext("2d").putImageData($rootScope.capturedImageData, 10,10);
        }
    }

     $scope.identifyFace = function(){
         if($rootScope.capturedImageData != null)
         {
            var canvas_capture = document.getElementById('capturedImage');
            $data.identifyFace(canvas_capture.toDataURL("image/png")).then(
             (result)=>{
                if(angular.equals(result.data, {})){
                    alert("No Match found");
                 } else {
                    alert(JSON.stringify(result.data));
                 }
             }, (error)=>{
                 console.log("IDENTIFICATION ERROR : "+JSON.stringify(error));
             });
         } else {
            console.log("No Image to Identify");
         }
     }
     $scope.dismissModal = function () {
       $uibModalInstance.dismiss('cancel');
       $rootScope.trackerTask.run();
     };
}
facialrecog.controller("ImageCaptureModal",ImageCaptureModal);

function CaptureCtrl ($scope, $rootScope,$location, $data, $uibModal) {
      var video = document.getElementById('video');
      var captureButton = document.getElementById('captureButton');
      var canvas = document.getElementById('canvas');
      var canvas_capture = document.getElementById('canvas_capture');
      var context = canvas.getContext('2d');
      var tracker = new tracking.ObjectTracker('face');
      var currentTrackerFrameProperties = null;
      $rootScope.capturedImageData = null;
        var streaming = false;
        tracker.setInitialScale(2);
        tracker.setStepSize(2);
        tracker.setEdgesDensity(0.1);
        $rootScope.trackerTask = tracking.track('#video', tracker, { camera: true });
        tracker.on('track', function(event) {
          context.clearRect(0, 0, canvas.width, canvas.height);
          event.data.forEach(function(trackerFrameProperties) {
              currentTrackerFrameProperties=trackerFrameProperties;
              var rect = trackerFrameProperties;
              context.strokeStyle = '#a64ceb';
              context.strokeRect(rect.x, rect.y, rect.width, rect.height);
              context.font = '11px Helvetica';
              context.fillStyle = "#fff";
              context.fillText('x: ' + rect.x + 'px and ' + rect.width + ' width', rect.x + rect.width + 5, rect.y + 11);
              context.fillText('y: ' + rect.y + 'px and ' + rect.height + ' height', rect.x + rect.width + 5, rect.y + 22);
          });
        });
      var gui = new dat.GUI();
      gui.add(tracker, 'edgesDensity', 0.1, 0.5).step(0.01);
      gui.add(tracker, 'initialScale', 1.0, 10.0).step(0.1);
      gui.add(tracker, 'stepSize', 1, 5).step(0.1);

      //trying new code
      video.addEventListener('canplay', function(ev) {
          if (!streaming) {
            height = video.videoHeight / (video.videoWidth / width);
            video.setAttribute('width', width);
            video.setAttribute('height', height);
            canvas.setAttribute('width', width);
            canvas.setAttribute('height', height);
            streaming = true;
          }
        }, false);

     $scope.takepicture = function() {
        if(currentTrackerFrameProperties != null ) {
            var rect = currentTrackerFrameProperties;
            //video.style.display = "none";
            canvas_capture.style.display = "block";
            canvas_capture.width = rect.width;
            canvas_capture.height = rect.height;
            canvas_capture.getContext('2d').drawImage(video, rect.x + rect.width / 2, rect.y + rect.width / 2, rect.width, rect.height, 0, 0, rect.width, rect.height);
            $rootScope.capturedImageData = canvas_capture.getContext('2d').getImageData(0, 0, rect.width, rect.height);
            $rootScope.trackerTask.stop();
            //show popup
            var modalInstance = $uibModal.open({
                  animation: false,
                  ariaLabelledBy: 'modal-title',
                  ariaDescribedBy: 'modal-body',
                  templateUrl: '../../moduleResources/facialrecog/partials/imageCaptureModal.html',
                  controller: 'ImageCaptureModal',
                  controllerAs: '$ctrl',
                  size: "lg",
                  resolve: {
                    items: function () {
                      return null;//$ctrl.items;
                    }
                  }
            });

            modalInstance.result.then(function (selectedItem) {
              //$ctrl.selected = selectedItem;
            }, function (error) {
                console.log(JSON.stringify(error));
            });
        } else {
            console.log("No face tracked/detected!!!")
        }
     }
}


