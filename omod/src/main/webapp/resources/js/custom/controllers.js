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

function CaptureCtrl ($scope, $location) {

      var video = document.getElementById('video');
      var captureButton = document.getElementById('captureButton');
      var canvas = document.getElementById('canvas');
      var canvas_capture = document.getElementById('canvas_capture');
      var context = canvas.getContext('2d');
      var tracker = new tracking.ObjectTracker('face');
      tracker.setInitialScale(4);
      tracker.setStepSize(2);
      tracker.setEdgesDensity(0.1);
      tracking.track('#video', tracker, { camera: true });
      tracker.on('track', function(event) {
        context.clearRect(0, 0, canvas.width, canvas.height);
        event.data.forEach(function(rect) {
          context.strokeStyle = '#a64ceb';
          context.strokeRect(rect.x, rect.y, rect.width, rect.height);
          context.font = '11px Helvetica';
          context.fillStyle = "#fff";
          context.fillText('x: ' + rect.x + 'px', rect.x + rect.width + 5, rect.y + 11);
          context.fillText('y: ' + rect.y + 'px', rect.x + rect.width + 5, rect.y + 22);
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

        captureButton.addEventListener('click', function(ev) {
          	if(captureButton.innerText==="Capture")
            {
            	takepicture();
            }
            else
            {
            	video.style.display = "block";
            	canvas_capture.style.display = "none";
              captureButton.innerText= "Capture";
            }
            ev.preventDefault();
          }, false);
}

function takepicture() {
  	//video.style.display = "none";
    canvas_capture.style.display = "block";
    captureButton.innerText= "Retake";
  	canvas_capture.width = 200;
    canvas_capture.height = 150;
    canvas_capture.getContext('2d').drawImage(video, 0, 0, canvas_capture.width, canvas_capture.height);
    //canvas_capture.getContext('2d').drawImage(video, rect.x, rect.y, rect.width, rect.height, rect.x, rect.y, rect.width, rect.height);
    var data = canvas_capture.toDataURL('image/png');
    photo.setAttribute('src', data);
  }
