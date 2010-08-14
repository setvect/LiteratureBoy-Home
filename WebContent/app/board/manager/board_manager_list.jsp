<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%@page import="com.setvect.common.util.PagingCondition"%>
<%@page import="com.setvect.common.util.StringUtilAd"%>
<%@page import="com.setvect.literatureboy.service.board.BoardService"%>
<%@include file="/common/taglib.inc.jsp"%>
<jsp:include page="/common/script.inc.jsp"/>
<script type="text/javascript" src="/app/board/manager/board_manager.js"></script>
<div>
	<display:table name="pageList.list" class="table" id="boardList" requestURI="" export="false" partialList="true" size="${pageList.total}" pagesize="${pageList.pagesize}"  style="margin-top:10px;">
    <display:column title="row number" >
      <c:out value="${pageList.rowNumDesc - boardList_rowNum + 1}"/>
    </display:column>
		<display:column property="boardCode" sortable="true" href="javascript:BoardManager.readForm('${boardList.boardCode}')" titleKey="Code"/>
		<display:column property="name" sortable="true" title="Name"/>
		<display:column title="Update">
			<input type="button" value="수정" onclick="BoardManager.updateFrom('${boardList.boardCode}')">
		</display:column>
		<display:column title="Delete">
			<input type="button" value="삭제" onclick="BoardManager.deleteAction('${boardList.boardCode}')">
		</display:column>
	</display:table>
</div>
<div>
	<input type="button" value="생성" onclick="BoardManager.createFrom();">
</div>
<jsp:include page="board_manager_form.inc.jsp"></jsp:include>