<%@ page language="java" contentType="text/html; charset=utf-8"
		 pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="cart" class="edu.unsw.comp9321.entity.Cart" scope="session" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<%--<title>COMP9321 Book Store</title>--%>
	<%--<meta charset="utf-8">--%>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width,initial-scale=1">
	<title>Digit Library</title>
	<meta name="keywords" content="">
	<meta name="author" content="Lanley">
	<link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
	<link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet">
	<link href="css/site.min.css" rel="stylesheet">
	<!-- <script>var _hmt = _hmt || [];</script> -->
	<link rel="apple-touch-icon-precomposed" sizes="144x144" href="/assets/ico/apple-touch-icon-144-precomposed.png">
	<link rel="shortcut icon" href="ico/favicon.ico">
</head>
<body class="home-template">

<header class="site-header jumbotron">
	<div class="site-nav">
		<a href="/advSearch.jsp">Advance</a>
		<span>/</span>
		<a href="/cart">Cart</a>
	</div>
	<div class="container">
		<div class="row">
			<div class="col-xs-12">
				<h1>DigitalLib</h1>
				<p>
                    Open Bibliographic Information Service
					<br>
					<%--<span class="package-amount">--%>
							<%--Search <strong>4,006,655</strong>--%>
							<%--items--%>
						<%--</span>--%>
				</p>
				<form class="" role="search" action="search">
					<div class="form-group">
						<input type="hidden" name="type" value="generic">
						<input type="text" name="query" class="form-control search clearable" placeholder="Search Article, eg：title, author"> <i class="fa fa-search"></i>
                    </div>
				</form>
			</div>
		</div>
	</div>
</header>

<main class="packages-list-container" id="all-packages">
	<div class="container">
		<div class="list-group packages">

			<c:forEach items="${searchResults}" var="book">
				<a href="/article?id=${book.id}" class="package list-group-item">
					<div class="row">
						<div class="col-md-3">
							<h4 class="package-name">${book.author}</h4>
						</div>
						<div class="col-md-9 hidden-xs">
							<p class="package-description">
								${book.title}
							</p>
						</div>
						<div class="package-extra-info col-md-9 col-md-offset-3 col-xs-12">
							<span>
								<i class="fa fa-tag"></i>
								${book.year}
							</span>
						</div>
					</div>
				</a>
			</c:forEach>

			<%--<a href="/d3/" class="package list-group-item" data-library-name="d3" target="_blank" onclick="_hmt.push(['_trackEvent', 'packages', 'click', 'd3'])">--%>
				<%--<div class="row">--%>
					<%--<div class="col-md-3">--%>
						<%--<h4 class="package-name">d3</h4>--%>
					<%--</div>--%>
					<%--<div class="col-md-9 hidden-xs">--%>
						<%--<p class="package-description">A JavaScript visualization library for HTML and SVG.</p>--%>
					<%--</div>--%>
					<%--<div class="package-extra-info col-md-9 col-md-offset-3 col-xs-12">--%>
							<%--<span>--%>
								<%--<i class="fa fa-star"></i>--%>
								<%--53635--%>
							<%--</span>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</a>--%>
			
		</div>
	</div>
</main>
<footer id="footer" class="footer hidden-print">
	<div class="copy-right">
		<span>© 2016 Langley. All rights reserved.</span>
	</div>
</footer>
<a href="#" id="back-to-top">
	<i class="fa fa-angle-up"></i>
</a>
<script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<!-- background generator-->
<script src="http://cdn.bootcss.com/geopattern/1.2.3/js/geopattern.min.js"></script>
<!-- <script src="http://cdn.bootcss.com/zeroclipboard/2.2.0/ZeroClipboard.min.js"></script> -->
<script src="http://cdn.bootcss.com/localforage/1.4.2/localforage.min.js"></script>
<script src="js/site.min.js"></script>
<!-- <script type="text/javascript">var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F24aff315392dc2c9b2805cfa4d8e101a' type='text/javascript'%3E%3C/script%3E"));</script> -->
</body>

</html>