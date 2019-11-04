<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<c:choose>
		<c:when test="${not empty user }">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
				<li class="nav-item"><a class="nav-link"
					href="searchedList.jsp">상품 정보</a></li>
				<li class="nav-item"><a class="nav-link" href="#">베스트 섭취 정보</a></li>
				<li class="nav-item logoutList"><a class="nav-link" href="#">내
						섭취 정보</a></li>
				<li class="nav-item logoutList"><a class="nav-link" href="#">예상
						섭취 정보</a></li>
			</ul>
		</c:when>
		<c:otherwise>
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" href="#">공지사항</a></li>
				<li class="nav-item"><a class="nav-link"
					href="searchedList.jsp">상품 정보</a></li>
				<li class="nav-item"><a class="nav-link" href="#">베스트 섭취 정보</a></li>
			</ul>
		</c:otherwise>
	</c:choose>

</body>
</html>