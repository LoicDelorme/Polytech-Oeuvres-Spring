<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tags:default-page>
    <jsp:attribute name="title">
    	Polytech Lyon - Update a purchase status
    </jsp:attribute>
    
    <jsp:attribute name="page_title">
    	Update a purchase status
    </jsp:attribute>
    
    <jsp:attribute name="header_content">
    </jsp:attribute>
    
    <jsp:attribute name="body_content">
	    <core:url value="/resources/js/purchase-status-form.js" var="js_purchase_status_form" />
	    <script src="${js_purchase_status_form}"></script>
	    
    	<form name="purchaseStatusForm" method="post" action="PurchaseStatusController/update" onsubmit="return checkInputs()">
			<div class="form-group">
				<input type="hidden" class="form-control" id="id" name="id" value="${purchaseStatus.id}">
		    </div>
			<div class="form-group">
				<label for="label">Label</label>
				<input type="text" class="form-control" id="label" name="label" placeholder="Enter your label" value="${purchaseStatus.label}">
		    </div>
		    <button type="submit" class="btn btn-primary">Submit</button>
		</form>
    </jsp:attribute>
    
    <jsp:attribute name="footer_content">
    </jsp:attribute>
</tags:default-page>