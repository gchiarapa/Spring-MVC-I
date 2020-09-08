<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Livros de Java, Android, Iphone, PHP, Ruby e muito mais - Casa do Código</title>
</head>
<body>
	
	<form:form action="${s:mvcUrl('PC#grava').build() }" method="POST" commandName="produto" enctype="multipart/form-data">
	
		<div>
			<label>Titulo</label>
			<form:input path="titulo" />
			<form:errors path="titulo"></form:errors>
		</div>
		
		<div>
			<label>Descrição</label>
			<form:textarea path="descricao" rows="10" cols="20" name="descricao"></form:textarea>
			<form:errors path="descricao"></form:errors>
		</div>
		<div>
			<label>Páginas</label>
			<form:input path="paginas" />
			<form:errors path="paginas"></form:errors>
		</div>
		
		<div>
			<label>Data de Lançamento</label>
			<form:input path="dataLancamento" />
			<form:errors path="dataLancamento"></form:errors>
		</div>
		
		<c:forEach items="${tipos }" var="tipoPreco" varStatus="status">
			<div>
				<label>${tipoPreco}</label>
				<form:input path="precos[${status.index}].valor" />
				<form:hidden path="precos[${status.index}].tipo" value="${tipoPreco}" />
			</div>
		</c:forEach>
		
		<div>
			<label>Sumário</label>
			<input name="sumario" type="file" >
		</div>
		
		<button type="submit">Cadastrar</button>		
	
	</form:form>
	
</body>
</html>