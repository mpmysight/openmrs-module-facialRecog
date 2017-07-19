function SearchPatientCtrl($scope,$data, $location,$window) {
    if(typeof $location.search().patientId !== 'undefined'){
        var patientId = $location.search().patientId;
        $data.searchPatient($location.search().patientId).then(function(response){
            var result = response.data;
            if(angular.equals(result,{})){
                document.getElementById('searchResults').innerHTML = "Could not find patient with identifier='"+patientId+"'";
            } else {
                $window.location.href="../../patientDashboard.form?patientId="+result.id;
            }
        },function(error){
            document.getElementById('searchResults').innerHTML = "Error while searching patient with identifier='"+patientId+"'";
        });
    } else {
        document.getElementById('searchResults').innerHTML = "No Patient ID specified";
    }
    $scope.searchPatient = function(){

    }
}