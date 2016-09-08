<%--
  Created by IntelliJ IDEA.
  User: Langley
  Date: 8/28/16
  Time: 2:26 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="cart" class="edu.unsw.comp9321.entity.Cart" scope="session"/>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>Digit Library</title>
    <link href="http://cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
    <link href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.css" rel="stylesheet">
    <link href="css/site.min.css" rel="stylesheet">
    <link href="js/info.js" rel="stylesheet">
    <!--<script>var _hmt = _hmt || [];</script>-->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="ico/apple-touch-icon-144-precomposed.png">
    <link rel="shortcut icon" href="ico/favicon.ico">
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top hidden-print navbar-back">
    <div class="container-fluid"><a href="/" class="btn btn-default navbar-btn btn-back">Back</a></div>
</div>
<header class="site-header jumbotron">
    <div class="site-nav"><a href="/">Home</a> <span>/</span> <a
            href="/cart">Cart</a></div>
    <div class="container"><h1>DigitalLib</h1>
        <p>This service provides open bibliographic information on major computer science journals and proceedings.</p>
    </div>
</header>

<main class="container">

    <form class="form-inline" action="/advance" method="get">
        <div class="search-border-box">
            <div class="form-group search-info-box">
                <span class="field">
                    <input type="text" name="query_1" id="query_1" size="50"/>
                </span>
                <span class="field">
                <%--<label for="searchin_1">in</label>--%>
                <select class="search-in" name="searchin_1" id="searchin_1">
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="year">Year</option>
                    <option value="journal">Journal</option>
                    <option value="volume">Volume</option>
                </select>
                </span>
                <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
            </div>
            <div class="form-group search-info-box">
                <span class="field">
                    <select class="search-conditional" name="searchconditional_2" id="searchconditional_2">
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </span>
                <span class="field">
                    <input type="text" name="query_2" id="query_2" size="50"/>
                </span>
                <span class="field">
                <%--<label for="searchin_2">in</label>--%>
                    <select class="search-in" name="searchin_2" id="searchin_2">
                        <option value="title">Title</option>
                        <option value="author">Author</option>
                        <option value="year">Year</option>
                        <option value="journal">Journal</option>
                        <option value="volume">Volume</option>
                    </select>
                </span>
                <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
            </div>
            <div class="form-group search-info-box">
                <span class="field">
                    <select class="search-conditional" name="searchconditional_3" id="searchconditional_3">
                        <option value="AND">AND</option>
                        <option value="OR">OR</option>
                        <option value="NOT">NOT</option>
                    </select>
                </span>
                <span class="field">
                <input type="text" name="query_3" id="query_3" size="50"/>
                </span>
                <span class="field">
                <%--<label for="searchin_3">in</label>--%>
                <select class="search-in" name="searchin_3" id="searchin_3">
                    <option value="title">Title</option>
                    <option value="author">Author</option>
                    <option value="year">Year</option>
                    <option value="journal">Journal</option>
                    <option value="volume">Volume</option>
                </select>
                </span>
                <a href="#" class="delete"><i class="fa fa-trash-o" aria-hidden="true"></i></a>
            </div>

            <div class="add-more-info-link">
                <a href="#" class="oneMore">Add More</a>
            </div>
            <div class="sub-box pull-right">
                <button type="submit" class="btn btn-default">Submit</button>
            </div>
        </div>

    </form>

</main>

<footer id="footer" class="footer hidden-print">
    <div class="copy-right"><span>© 2016 Langley. All rights reserved.</span></div>
</footer>
<a href="#" id="back-to-top"><i class="fa fa-angle-up"></i></a>
<script src="http://cdn.bootcss.com/jquery/3.0.0/jquery.min.js"></script>
<script src="http://cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<script src="http://cdn.bootcss.com/geopattern/1.2.3/js/geopattern.min.js"></script>
<script src="http://cdn.bootcss.com/zeroclipboard/2.2.0/ZeroClipboard.min.js"></script>
<script src="http://cdn.bootcss.com/localforage/1.4.2/localforage.min.js"></script>
<script src="js/site.min.js"></script>
<script src="js/info.js"></script>
<!--<script type="text/javascript">var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");-->
<!--document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3F24aff315392dc2c9b2805cfa4d8e101a' type='text/javascript'%3E%3C/script%3E"));</script>-->
</body>
</html>
