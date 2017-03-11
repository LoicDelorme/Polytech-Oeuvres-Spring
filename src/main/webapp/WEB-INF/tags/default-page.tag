<%@ taglib prefix="core" uri="http://java.sun.com/jsp/jstl/core"%>

<%@ tag description="Default Artwork Page" pageEncoding="UTF-8"%>

<%@attribute name="title"%>
<%@attribute name="page_title"%>
<%@attribute name="header_content" fragment="true" %>
<%@attribute name="body_content" fragment="true" %>
<%@attribute name="footer_content" fragment="true" %>

<!DOCTYPE html>
<html lang="en">

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Artworks Project">
    <meta name="author" content="Loïc DELORME & Nicolas KASPRZYK">
    
    <title>${title}</title>
    
    <core:url value="/resources/bootstrap/css/bootstrap.min.css" var="css_bootstrap" />
    <core:url value="/resources/metisMenu/metisMenu.min.css" var="css_metisMenu" />
    <core:url value="/resources/font-awesome/css/font-awesome.min.css" var="css_font_awesome" />
    <core:url value="/resources/css/sb-admin-2.css" var="css_sb_admin_2" />
    
    <link href="${css_bootstrap}" rel="stylesheet" />
    <link href="${css_metisMenu}" rel="stylesheet" />
    <link href="${css_font_awesome}" rel="stylesheet" />
    <link href="${css_sb_admin_2}" rel="stylesheet" />
    
    <jsp:invoke fragment="header_content"/>
</head>

<body>
    <div id="wrapper">
    	<nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/oeuvres/index">Polytech Lyon</a>
            </div>
            
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a></li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a></li>
                        <li class="divider"></li>
                        <li><a href="#"><i class="fa fa-sign-out fa-fw"></i> Logout</a></li>
                    </ul>
                </li>
            </ul>
            
            <div class="navbar-default sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li><a href="/oeuvres/index"><i class="glyphicon glyphicon-list-alt"></i> Features</a></li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-user"></i> Members<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/MemberController/list">List all members</a></li>
                                <li><a href="/oeuvres/MemberController/add-form">Add a new member</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-user"></i> Owners<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/OwnerController/list">List all owners</a></li>
                                <li><a href="/oeuvres/OwnerController/add-form">Add a new owner</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-book"></i> Loan Artworks<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/LoanArtworkController/list">List all loan artworks</a></li>
                                <li><a href="/oeuvres/LoanArtworkController/add-form">Add a new loan artwork</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-book"></i> Sale Artworks<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/SaleArtworkController/list">List all sale artworks</a></li>
                                <li><a href="/oeuvres/SaleArtworkController/add-form">Add a new sale artwork</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i> Artwork States<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/ArtworkStateController/list">List all artwork states</a></li>
                                <li><a href="/oeuvres/ArtworkStateController/add-form">Add a new artwork state</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-tasks"></i> Purchase Status<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/PurchaseStatusController/list">List all purchase status</a></li>
                                <li><a href="/oeuvres/PurchaseStatusController/add-form">Add a new purchase status</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-tags"></i> Loans<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/LoanController/list">List all loans</a></li>
                                <li><a href="/oeuvres/LoanController/add-form">Add a new loan</a></li>
                            </ul>
                        </li>
                        <li>
                            <a href="#"><i class="glyphicon glyphicon-tags"></i> Purchases<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li><a href="/oeuvres/PurchaseController/list">List all purchases</a></li>
                                <li><a href="/oeuvres/PurchaseController/add-form">Add a new purchase</a></li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
        
        <div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header">${page_title}</h1>
                </div>
            </div>
            
            <div class="row">
                <div class="col-lg-12">
                	<jsp:invoke fragment="body_content"/>
                </div>
            </div>
        </div>
	</div>
	
	<jsp:invoke fragment="footer_content"/>
    
    <core:url value="/resources/jquery/jquery.min.js" var="js_jquery" />
    <core:url value="/resources/bootstrap/js/bootstrap.min.js" var="js_bootstrap" />
    <core:url value="/resources/metisMenu/metisMenu.min.js" var="js_metisMenu" />
    <core:url value="/resources/js/sb-admin-2.min.js" var="js_sb_admin_2" />
    
    <script src="${js_jquery}"></script>
    <script src="${js_bootstrap}"></script>
    <script src="${js_metisMenu}"></script>
    <script src="${js_sb_admin_2}"></script>
</body>

</html>