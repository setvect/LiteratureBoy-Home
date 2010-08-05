<%@ page language="java" pageEncoding="utf-8" isELIgnored="false" %>
<%@page import="java.util.Collection"%>
<%@page import="com.setvect.literatureboy.vo.board.Board"%>
<%@page import="com.setvect.common.util.GenericPage"%>
<%@page import="com.setvect.literatureboy.web.ConstraintWeb"%>
<%@page import="com.setvect.literatureboy.web.board.BoardManagerController"%>
<%
GenericPage<Board> boardPaging = (GenericPage<Board>)request.getAttribute(BoardManagerController.AttributeKey.BOARD_LIST.name());
%>

<div>
	<table>
		<thead>
			<tr>
				<th>번호</th>
				<th>코드</th>
				<th>이름</th>
				<th>수정</th>
				<th>삭제</th>
			</tr>
		</thead>
		<tbody>
<%
	Collection<Board> list = boardPaging.getList();
	int c=0;
	for(Board b : list){
		c++;
%>		
			<tr>
				<td><%=c%></td>
				<td><%=b.getBoardCode() %></td>
				<td><%=b.getName() %></td>
				<td><input type="button" value="수정" onclick="updateFrom('<%=b.getBoardCode()%>')"></td>
				<td><input type="button" value="삭제" onclick="deleteProc('<%=b.getBoardCode()%>')"></td>																
			</tr>
<%
	}
	if(c == 0){
%>
			<tr>
				<td colspan="5">자료가 없습니다.</td>
			</tr>
<%		
	}
%>			
		</tbody>
	</table>
</div>
<div>
	<input type="button" value="생성" onclick="createFrom();">
</div>