<%@ include file="/WEB-INF/template/include.jsp" %>
<%@ include file="/WEB-INF/template/header.jsp" %>

<openmrs:require privilege="Edit Cohorts" otherwise="/login.htm" redirect="/module/muzima/facehome.list"/>
<%-- <openmrs:htmlInclude file="/moduleResources/facialrecog/css/main.css"/> --%>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/simple-sidebar.css"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/css/noscript.css"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/jquery/jquery-3.1.1.min.js" />

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-v1.2.32.min.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/angular/angular-route.min.js"/>

<openmrs:htmlInclude file="/moduleResources/facialrecog/js/custom/app.js"/>
<openmrs:htmlInclude file="/moduleResources/facialrecog/js/custom/controllers.js"/>

<h3><spring:message code="mUzima Biometrics - Facial Recognition"/></h3>
<div class="bootstrap-scope" ng-app="facialrecog">
    <div ng-view ></div>
</div>

<%@ include file="/WEB-INF/template/footer.jsp" %>