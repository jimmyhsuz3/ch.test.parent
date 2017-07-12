<%@ page language="java" contentType="text/html; charset=utf8" pageEncoding="utf8" isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h4>Hello World!祥</h4>
<c:forEach var="model" items="${requestScope.modelList}">
	<h4><c:out value="${model.title}"/></h4>
	<table border='1'>
		<c:forEach var="resp" items="${model.list}">
			<tr><td><pre><c:out value="${resp}"/></pre></td></tr>
		</c:forEach>
	</table>
</c:forEach>
<h4>crcList</h4>
<table border='1'>
	<c:forEach var="crc" items="${requestScope.crcList}">
		<tr><td><pre><c:out value="${crc[0]}"/></pre></td>
		<td><pre><c:out value="${crc[1]}"/></pre></td>
		<td><pre><c:out value="${crc[2]}"/></pre></td></tr>
	</c:forEach>
</table>
</body>
</html>