<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<h2>${BOARD.name}</h2>
<div>
	<table>
		<tr>
			<td><h3>${ARTICLE.title}</h3></td>
		</tr>
		<tr>
			<td>${ARTICLE.content}</td>
		</tr>
		<tr>
			<td>
				<span class="tail">
					(<fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy-MM-dd"/>)
				</span>
			</td>
		</tr>
	</table>
</div>
<div style="text-align: right;">
	<span class="button red">
		<input type="button" value="목록" onclick="BoardArticle.listForm()">
	</span>
</div>
<div class="trackback">
	<div class="address">
		트래백 주소:${TRACK_ADDR} 
		<span class="button blue small">
			<input type="button" onclick="$.UTIL.copyClipboard('${TRACK_ADDR}')" value="주소복사">
		</span>
	</div>
	<div class="list">
		<ul>
			<c:forEach var="tarckback" items="${TRACK_LIST}">
				<li>
					<a href="${tarckback.url}" target="_blank">${tarckback.title}</a>
				</li>
			</c:forEach>				
		</ul>
	</div>
</div>
<jsp:include page="../board_article_form.inc.jsp"></jsp:include>