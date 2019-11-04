<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="newest-item" class="container" style="margin-top: 30px">
	<div class="row justify-content-center">
<c:forEach var="item" items="${result}">
<c:set var="sMaterial" value="${item.material}"/>
		<div class="card col-3 card-hover" title="${item.name}">
			<img class="card-img-top" src="${item.img}" style="width:100%">
			<div class="card-body" title="${item.name}">
				<h4 class="card-title">${item.name}</h4>
				<p class="card-text">${item.maker}<br>${fn:substring(sMaterial, 0, 20)}...</p>
				<div class="btn-group">
					<a href="#" class="btn btn-primary">추가</a>
					<a href="#" class="btn btn-primary">찜</a>
				</div>
			</div>
		</div>
</c:forEach>
	</div>
</div>
</body>
</html>