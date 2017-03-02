<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:require privilege="Edit Cohorts" otherwise="/login.htm" redirect="/module/muzima/facehome.list"/>
<%-- <openmrs:htmlInclude file="/moduleResources/facialrecog/css/main.css"/> --%>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/simple-sidebar.css"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/noscript.css"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/jquery/jquery.js" />

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-resource.js"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/app.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/controllers.js"/>

<h3><spring:message code="mUzima Biometrics - Facial Recognition"/></h3>
<div class="bootstrap-scope" ng-app="facialrecog">
    <div ng-view ></div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>