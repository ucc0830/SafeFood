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
	<table class="table">
		<tbody>
			<tr>
				<th scope="row" style="width: 50%">일일 제공량</th>
				<td>${result.supportpereat}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">칼로리</th>
				<td>${result.calory}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">탄수화물</th>
				<td>${result.carbo}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">단백질</th>
				<td>${result.protein}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">지방</th>
				<td>${result.fat}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">당류</th>
				<td>${result.sugar}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">나트륨</th>
				<td>${result.natrium}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">콜레스트롤</th>
				<td>${result.chole}</td>
			</tr>
				<tr><th scope="row" style="width: 50%">포화지방산</th>
				<td>${result.fattyacid}</td>
			</tr>
			<tr>
				<th scope="row" style="width: 50%">트렌스지방</th>
				<td>${result.transfat}</td>
			</tr>
		</tbody>
	</table>
</div>
</body>
</html>