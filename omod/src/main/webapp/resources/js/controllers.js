facialrecog.CaptureCtrl ([$ngCamera, $ngSanitize, ]) {
$scope.cameraStarted = false;

if ('addEventListener' in window) {
    window.addEventListener('load', function() { document.body.className = document.body.className.replace(/\bis-loading\b/, ''); });
	document.body.className += (navigator.userAgent.match(/(MSIE|rv:11\.0)/) ? ' is-ie' : '');
	}

	var video = document.querySelector("#videoElement");
    navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia || navigator.oGetUserMedia;

    if (navigator.getUserMedia) {
        navigator.getUserMedia({video: true}, handleVideo, videoError);
    }
}

function handleVideo(stream) {
    video.src = window.URL.createObjectURL(stream);
}

function videoError(e) {
    // do something
}
<!-- Scripts -->
			<!--[if lte IE 8]><script src="assets/js/respond.min.js"></script><![endif]-->
			<script>

			</script>
		<script>


</script>