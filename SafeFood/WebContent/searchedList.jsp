<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>상품 리스트</title>

<script
	src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/js/bootstrap-select.min.js"></script>

	
<script type="text/javascript">
$(function() {
	var gOption = '${param.searchOpt}';
	var gKeyword = '${param.searchKeyword}';
	console.log(gOption);
	console.log(gKeyword);
	
	$.ajax({
		type:'get',
		url:'food.do?command=search&&searchKeyword='+gKeyword+'&&searchOpt='+gOption,
		success:function(result){
			$('div#newest-item').html(result);
		}
	});
	
	
	$("body").on("mouseenter mouseleave", '.card' ,function(e) {
		if (e.type == "mouseenter") {
			$(this).addClass('shadow-lg').css('cursor', 'pointer');
		} else {
			$(this).removeClass('shadow-lg');
		}
	});
	
	$("body").on("click", '.card', function(e) {
		console.log();
		var name = e.currentTarget.title;
		location.href='prodcutInfo.jsp?name='+name;
	})
	
});

function searchClick(){
	var option = $('select.bootstrap-select').val();
	console.log(option);
	var keyword = $('input.search_input').val();
	console.log(keyword);
	location.href='searchedList.jsp?searchOpt='+option+'&searchKeyword='+keyword;
}
</script>
<!-- Custom styles for this template -->
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css"
	integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.5.0/css/all.css"
	integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU"
	crossorigin="anonymous">
<link
	href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-select@1.13.9/dist/css/bootstrap-select.min.css">

<link href="./css/banner.css" rel="stylesheet">
<link href="./css/searchBar.css" rel="stylesheet">
<link href="./css/loginIndex.css" rel="stylesheet">
<link href="./css/productList.css" rel="stylesheet">
<style type="text/css">
.bd-placeholder-img {
	font-size: 1.125rem;
	text-anchor: middle;
	-webkit-user-select: none;
	-moz-user-select: none;
	-ms-user-select: none;
	user-select: none;
	-ms-user-select: none;
}

@media ( min-width : 768px) {
	.bd-placeholder-img-lg {
		font-size: 3.5rem;
	}
}

.background-banner {
	height: 700px;
	position: relative;
	background-image: url(./img/banner_bg_productInfo.jpg);
	background-repeat: no-repeat;
	background-size: cover;
	background-position: center;
}

.banner-font {
	font-family: Dotum;
	font-weight: bold;
	color: white;
}

#infomypage{
	margin-right: 10px;
	color: white;
}
.logoutfont{
	margin-right: 10px;
	background-color: black;
	color: white;
}

</style>

</head>
<body>
	<header class="background-banner">
		<nav class="navbar navbar-expand-sm">
			<div class="container-fluid justify-content-end">
				<!-- mypage 연결 -->
				<div id="loginlogout">
					<jsp:include page="loginlogout.jsp"></jsp:include>

				</div>
			</div>
		</nav>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<a class="navbar-brand" href="index.jsp"><img src="./img/logo.png"></a>
			<button class="navbar-toggler navbar-toggler-right" type="button"
				data-toggle="collapse" data-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div class="collapse navbar-collapse justify-content-end"
				id="collapsibleNavbar">
				<div id="tablist">
					<jsp:include page="tablist.jsp"></jsp:include>
				</div>
			</div>
		</nav>
		<br>
		<div
			class="container-fluid position-relative overflow-hidden text-center banner-bg-main">
			<div class="col-md-5 p-lg-5 mx-auto my-5">
				<h1 class="display-4 font-weight-normal">
					WHAT WE <b>PROVIDE</b>
				</h1>
				<p class="lead font-weight-normal">건강한 삶을 위한 먹거리 프로젝트</p>
				<div class="container">
					<div class="d-flex justify-content-center">
						<div class="searchbar">
							<select class="bootstrap-select">
								<option value="전체" selected="selected">전체</option>
								<option value="상품명">상품명</option>
								<option value="제조사">제조사</option>
								<option value="성분">성분</option>
							</select>
							<input class="search_input" type="text" name=""
								placeholder="Search..."> <a href="#" class="search_icon" onclick="searchClick();"><i
								class="fas fa-search"></i></a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</header>
	<br>
	<div class="container">
		<form action="" method="post">
			<div class="form-group" id="allergy">
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="checkbox"
						class="form-check-input" value="allergy" name="ALLERGYSORT">알레르기 상품 제외
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="checkbox"
						class="form-check-input" value="calory" name="ALLERGYSORT">칼로리
					</label>
				</div>
				<div class="form-check-inline">
					<label class="form-check-label"> <input type="checkbox"
						class="form-check-input" value="protein" name="ALLERGYSORT">단백질
					</label>
				</div>
				<div class="form-check-inline">
					<button type="submit" id="signin" class="logoutfont btn btn-outline-secondary btn-sm"><b>검색</b></button>
				</div>
			</div>
		
		</form>
	</div>
	<div id="newest-item" class="container" style="margin-top: 30px">
	</div>


	<footer class="container py-5">
		<div class="row">
			<div class="col-12 col-md">
				<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24"
					fill="none" stroke="currentColor" stroke-linecap="round"
					stroke-linejoin="round" stroke-width="2" class="d-block mb-2"
					role="img" viewBox="0 0 24 24" focusable="false">
					<title>Product</title><circle cx="12" cy="12" r="10" />
					<path
						d="M14.31 8l5.74 9.94M9.69 8h11.48M7.38 12l5.74-9.94M9.69 16L3.95 6.06M14.31 16H2.83m13.79-4l-5.74 9.94" /></svg>
				<small class="d-block mb-3 text-muted">&copy; 2017-2019</small>
			</div>
			<div class="col-6 col-md">
				<h5>Feature</h5>
				<ul class="list-unstyled text-small">
					<li><a class="text-muted" href="#">공지사항</a></li>
					<li><a class="text-muted" href="#">상품 정보</a></li>
					<li><a class="text-muted" href="#">베스트 섭취 정보</a></li>
					<li><a class="text-muted" href="#">내 섭취 정보</a></li>
					<li><a class="text-muted" href="#">예상 섭취 정보</a></li>
				</ul>
			</div>
			<div class="col-6 col-md">
				<h5>About</h5>
				<ul class="list-unstyled text-small">
					<li><a class="text-muted" href="about.html">팀원소개</a></li>
				</ul>
			</div>
		</div>
	</footer>
</body>
</html>
