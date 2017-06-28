<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:require privilege="Edit Cohorts" otherwise="/login.htm" redirect="/module/muzima/facehome.list"/>
<%-- <openmrs:htmlInclude file="/moduleResources/facialrecog/css/main.css"/> --%>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/simple-sidebar.css"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/bootstrap.min.css"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/noscript.css"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/jquery/jquery.js" />
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-resource.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-route.min.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-route.min.js.map"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/tracking/tracking-min.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/tracking/tracking.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/tracking/face-min.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/tracking/dat.gui.min.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/tracking/stats.min.js"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/custom/controllers.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/ui-bootstrap/ui-bootstrap-tpls-2.5.0.min.js"/>

<h3 class="module-head"><spring:message code="mUzima Biometrics - Facial Recognition"/></h3>
<div class="bootstrap-scope" ng-app="facialrecog">
    <div ng-view ></div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>