<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="row justify-content-center">
	<div class="col-md-7 order-md-2" id="item-detail">
		<table class="table"> 
			<tbody> 
				<tr> 
					<th scope="row" style="width: 25%">제품명</th>
					<td>${result.name}</td>
				</tr>
				<tr> 
					<th scope="row" style="width: 25%">제조사</th>
					<td>${result.maker}</td>
				</tr>
				<tr>
					<th scope="row" style="width: 25%">원재료</th>
					<td>${result.material}</td>
				</tr>
				<tr>
					<th scope="row" style="width: 25%">기본 알레르기 성분</th>
					<td>${result.allergyStr}</td>
				</tr>
				<tr>
					<th scope="row" style="width: 25%">수량</th>
					<td> <input type="number">&nbsp;&nbsp;&nbsp;<div class="btn-group"><a href="#" class="btn btn-primary">추가</a>
		<a href="#" class="btn btn-primary">찜</a></div></td>
				</tr>
			</tbody>
		</table>
<!-- 		Quantity <input type="number">&nbsp;&nbsp;&nbsp;
		<div class="btn-group"><a href="#" class="btn btn-primary">추가</a>
		<a href="#" class="btn btn-primary">찜</a></div> -->
	</div>
	<div class="col-md-5 order-md-1" id="item-detail-img">
		<img alt="" src="${result.img}" style="width:100%" class="img-rounded">
	</div>
</div>
</body>
</html>