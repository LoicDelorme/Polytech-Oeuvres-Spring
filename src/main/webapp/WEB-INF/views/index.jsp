<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tags:default-page>
    <jsp:attribute name="title">
    	Polytech Lyon - Features
    </jsp:attribute>
    
    <jsp:attribute name="page_title">
    	Features
    </jsp:attribute>
    
    <jsp:attribute name="header_content">
    </jsp:attribute>
    
    <jsp:attribute name="body_content">
		<core:if test="${message != null}">
   			<div class="alert alert-success alert-dismissible" role="alert">
	  			<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	  			<strong>${message}</strong>
			</div>
		</core:if>
    	
		<dl>
			<dt>Members</dt>
			<dd><a href="/oeuvres/MemberController/list">List all members</a></dd>
			<dd><a href="/oeuvres/MemberController/add-form">Add member</a></dd>
		</dl>
		
		<dl>
			<dt>Owners</dt>
			<dd><a href="/oeuvres/OwnerController/list">List all owners</a></dd>
			<dd><a href="/oeuvres/OwnerController/add-form">Add owner</a></dd>
		</dl>
		
		<dl>
			<dt>Loan Artworks</dt>
			<dd><a href="/oeuvres/LoanArtworkController/list">List all loan artworks</a></dd>
			<dd><a href="/oeuvres/LoanArtworkController/add-form">Add loan artwork</a></dd>
		</dl>
		
		<dl>
			<dt>Sale Artworks</dt>
			<dd><a href="/oeuvres/SaleArtworkController/list">List all sale artworks</a></dd>
			<dd><a href="/oeuvres/SaleArtworkController/add-form">Add sale artwork</a></dd>
		</dl>
		
		<dl>
			<dt>Artwork States</dt>
			<dd><a href="/oeuvres/ArtworkStateController/list">List all artwork states</a></dd>
			<dd><a href="/oeuvres/ArtworkStateController/add-form">Add artwork state</a></dd>
		</dl>
		
		<dl>
			<dt>Purchase Status</dt>
			<dd><a href="/oeuvres/PurchaseStatusController/list">List all purchase status</a></dd>
			<dd><a href="/oeuvres/PurchaseStatusController/add-form">Add purchase status</a></dd>
		</dl>
		
		<dl>
			<dt>Loans</dt>
			<dd><a href="/oeuvres/LoanController/list">List all loans</a></dd>
			<dd><a href="/oeuvres/LoanController/add-form">Add a new loan</a></dd>
		</dl>
		
		<dl>
			<dt>Purchases</dt>
			<dd><a href="/oeuvres/PurchaseController/list">List all purchases</a></dd>
			<dd><a href="/oeuvres/PurchaseController/add-form">Add a new purchase</a></dd>
		</dl>
    </jsp:attribute>
    
    <jsp:attribute name="footer_content">
    </jsp:attribute>
</tags:default-page>