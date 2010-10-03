<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<script type="text/javascript" src="/app/board/board_article.js"></script>
<div>
	<table>
		<tr>
			<td class="title">${ARTICLE.title}</td>
			<td>(<fmt:formatDate value="${ARTICLE.regDate}" pattern="yyyy-MM-dd"/>)</td>
		</tr>
		<tr>
			<td colspan="2">${ARTICLE.content}</td>
		</tr>
	</table>
</div>
<div>
	<input type="button" value="목록" onclick="BoardArticle.listForm()">
</div>

<div class="trackback">
	<div class="address">
		트래백 주소:${TRACK_ADDR} <input type="button" onclick="$.UTIL.copyClipboard('${TRACK_ADDR}')" value="주소복사">
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