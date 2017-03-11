<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<tags:default-page>
    <jsp:attribute name="title">
    	Polytech Lyon - List of all registered artwork states
    </jsp:attribute>
    
    <jsp:attribute name="page_title">
    	Registered artwork states
    </jsp:attribute>
    
    <jsp:attribute name="header_content">
    </jsp:attribute>
    
    <jsp:attribute name="body_content">
    	<table class="table table-striped">
			<thead>
				<tr>
					<th>ID</th>
					<th>Label</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<core:forEach items="${artworkStates}" var="artworkState">
					<tr>
						<td><a href="/oeuvres/ArtworkStateController/overview&id=${artworkState.id}">${artworkState.id}</a></td>
						<td>${artworkState.label}</td>
						<td>
							<a class="btn btn-warning" href="/oeuvres/ArtworkStateController/update-form&id=${artworkState.id}" role="button"><i class="glyphicon glyphicon-pencil"></i></a>
							<a class="btn btn-danger" href="/oeuvres/ArtworkStateController/delete&id=${artworkState.id}" role="button"><i class="glyphicon glyphicon-remove"></i></a>
						</td>
					</tr>
				</core:forEach>
			</tbody>
		</table>
    </jsp:attribute>
    
    <jsp:attribute name="footer_content">
    </jsp:attribute>
</tags:default-page>