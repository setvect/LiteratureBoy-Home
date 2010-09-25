<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@include file="/common/taglib.inc.jsp"%>
<div class="left_area">
	<div class="main_image"><a href="/"><img src="/upload/image_reg/main_08.jpg" alt="야구장에서..."/></a></div>
	<ul>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA01">글</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA09">꿈</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA02">책</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA03">음악</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA04">영화</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA05">사진</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA08">잡담</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA06">기억</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA07">인연</a></li>
		<li><a href="/literatureboy/bd.do?searchCode=BDAAAA10">기술사</a></li>
		<li><a href="#" onclick="$.POPUP.popupWindowCenter('/emailget.do', 'emailInput', 400, 250, false, false, false)">email</a></li>
	</ul>
	<hr/>
		
	<c:if test="${fn:length(BOARD_ITEMS) > 0}" >
	<ul>
		<c:forEach var="board" items="${BOARD_ITEMS}">
			<li><a href="/literatureboy/bdm.do?searchCode=${board.boardCode}">${board.name}</a></li>		
		</c:forEach>
	</ul>
	</c:if>
</div>