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
	<ul class="nav navbar-nav">
		<span style="color: white;"> ${msg }</span>
		<c:choose>
			<c:when test="${not empty user }">

				<li class="logoutList"><a
					type="button" href="member.do?command=mypage"
					class="logoutfont btn btn-outline-secondary btn-sm"><b>My
							Page</b></a></li>
				<li class="logoutList"><a type="button" href="member.do?command=logout" id="logout"
					class="logoutfont btn btn-outline-secondary btn-sm"><b>logout</b></a></li>
			</c:when>
			<c:otherwise>
				<li class="loginList"><a type="button" href="sign-in.jsp"
					class="logoutfont btn btn-outline-secondary btn-sm"><b>Sign-in</b>
				</a></li>
				<li class="dropdown dropdown-menu-right loginList"><a
					type="button" href="#"
					class="btn dropdown-toggle btn-outline-secondary btn-sm"
					data-toggle="dropdown"><b>Login</b> <span class="caret"></span></a>
					<ul id="login-dp" class="dropdown-menu dropdown-menu-right">
						<li>
							<div class="row">
								<div class="col-md-12">
									<b>Login via</b>
									<div class="social-buttons">
										<a href="#" class="btn btn-fb"><i class="fa fa-facebook"></i>
											Facebook</a> <a href="#" class="btn btn-tw"><i
											class="fa fa-twitter"></i> Twitter</a>
									</div>
									or
									<form class="form" role="form" method="post"
										action="member.do?command=login" accept-charset="UTF-8"
										id="login-nav">
										<div class="form-group">
											<label class="sr-only" for="exampleInputEmail2">Email
												address</label> <input type="text" name="email"
												class="form-control" id="exampleInputEmail2"
												placeholder="Email address" required>
										</div>
										<div class="form-group">
											<label class="sr-only" for="exampleInputPassword2">Password</label>
											<input type="password" name="password" class="form-control"
												id="exampleInputPassword2" placeholder="Password" required>
											<div class="help-block text-right">
												<a type="button" href="findpw.html">Forget the password
													?</a>
											</div>
										</div>
										<div class="form-group">
											<!-- 로그인 mainpage 연결 -->
											<button type="submit" id="login"
												class="btn btn-primary btn-block">Sign
												in</button>
										</div>
										<div class="checkbox">
											<label> <input type="checkbox"> keep me
												logged-in
											</label>
										</div>
									</form>
								</div>
								<div class="container-fluid bottom text-center">
									<!-- 회원가입 링크 연결 -->
									New here ? <a href="sign-in.jsp"><b>Join Us</b></a>
								</div>
							</div>
						</li>
					</ul></li>
			</c:otherwise>
		</c:choose>


	</ul>
</body>
</html>