<%@page import="com.setvect.literatureboy.service.comment.CommentModule"%>
<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<div class="main_content">
	<c:if test="${! empty MAIN_ARTICLE}">
		${MAIN_ARTICLE.content}
		<div class="tail">
			<a href="/literatureboy/bd.do?searchCode=${MAIN_ARTICLE.boardCode}">이전 메인화면</a>	
		</div>
	</c:if>
	<c:if test="${empty MAIN_ARTICLE}">
		내용없음
	</c:if>
	<br/><br/><br/><br/><br/>
	<jsp:include page="/app/comment/comment.portlet.jsp">
		<jsp:param name="moduleName" value="<%=CommentModule.MAIN%>"/>
		<jsp:param name="moduleId" value="0"/>
		<jsp:param name="writerForm" value="false"/>
	</jsp:include>	
</div>