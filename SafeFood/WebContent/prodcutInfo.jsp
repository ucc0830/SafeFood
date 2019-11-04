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
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"></script>
	
<script type="text/javascript">
$(function() {
/* 	var itemName = '메로나';
	console.log(itemName); */
 	var itemName = '${param.name}';
	console.log(itemName);
	
	
	
	
	$.ajax({
		type:'get',
		url:'food.do?command=searchItem&&name='+itemName,
		success:function(result){
			$('div.item-information').html(result);
		}
	});
	
	$.ajax({
		type:'get',
		url:'food.do?command=searchItemNutrition&&name='+itemName,
		success:function(result){
			$('div.item-nutrition').html(result);
		}
	});
	
	$.ajax({
		type:'post',
		dataType:"json",
		url:'food.do?command=searchItemJson&&name='+itemName,
		success:function(result , status , xhr){
			$.each(result, function(idx, obj){
				var ctx = document.getElementById('myChart').getContext('2d');
				var myChart = new Chart(ctx, {
				    type: 'doughnut',
				    data: {
				        labels: ['칼로리', '탄수화물', '단백질', '지방', '당류', '나트륨', '콜레스트롤', '포화 지방산', '트렌스지방'],
				        datasets: [{
				            label: '# of Votes',
				            data: [obj.calory, obj.carbo, obj.protein, obj.fat, obj.sugar, obj.natrium, 
				            	obj.chole, obj.fattyacid, obj.transfat],
				            backgroundColor: [
				                '#003f5c',
				                '#2f4b7c',
				                '#665191',
				                '#a05195',
				                '#d45087',
				                '#f95d6a',
				                '#ff7c43',
				                '#ffa600',
				                '#de425b'
				            ],
				            borderWidth: 1
				        }]
				    },
				    options: {
				        scales: {
				            yAxes: [{
				                ticks: {
				                    beginAtZero: true
				                }
				            }]
				        }
				    }
				});
			});
			
		}
	});
	
	
	
	
	
	
	
});

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
			</div>
		</div>
	</header>
	<br>

	<div id="item-page-main" class="container" style="margin-top: 30px">
		<hr class="featurette-divider">
		<div id="item-page">
			<div class="row featurette item-information">
			</div>
		</div>
		<hr class="featurette-divider">
		<div id="item-al">
			<div class="row featurette">
				<div class="col-md-7 order-md-2 item-nutrition" id="table-div">
					
				</div>
				<div class="col-md-5 order-md-1">
					<canvas id="myChart" width="400" height="400"></canvas>
				</div>
			</div>
		</div>
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