<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="context" value="${pageContext.request.contextPath }" />
<table>
	<tr>
		<td><a href="${context}/user/get_retrieve.do">사용자관리</a></td>
	</tr>
	<tr>
		<td><a href="${context}/board/get_retrieve.do">게시판목록</a></td>
	</tr>
	<tr>
		<td><a href="${context}/main/main.do">Main</a></td>
	</tr>
	<tr>
		<td><a href="${context}/chart/pie_chart_view.do">파이차트</a></td>
	</tr>
	<tr>
		<td><a href="${context}/chart/line_chart_view.do">라인차트</a></td>
	</tr>

</table>