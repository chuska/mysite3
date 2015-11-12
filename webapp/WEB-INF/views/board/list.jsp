<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>mysite</title>
<meta http-equiv="content-type" content="text/html; charset=utf-8">
<link href="/mysite3/assets/css/board.css" rel="stylesheet"
	type="text/css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<div id="content">
			<div id="board">
				<form id="search_form" action="/mysite3/board" method="get">
					<input type="text" name="kw" value="${listData.keyWord }">
					<input type="submit" value="찾기">
				</form>
				<table class="tbl-ex">
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>글쓴이</th>
						<th>조회수</th>
						<th>작성일</th>
						<th>&nbsp;</th>
					</tr>
					<c:set var='count' value='${fn:length(listData.list) }'></c:set>
					<c:forEach items='${listData.list}' var="vo" varStatus="status">
						<tr>
							<td>${count-status.index }</td>
							<td class="title" style="padding-left:${( vo.depth - 1 )*30 }px">
								<c:if test="${vo.depth > 1 }">
									<img src="/mysite3/assets/images/ico-reply.gif">
								</c:if> <a href="/mysite3/board/view/${vo.no}">${vo.title }</a>
							</td>
							<td>${vo.memberName }</td>
							<td>${vo.viewCount}</td>
							<td>${vo.regDate }</td>

							<c:if test="${not empty authUser&&authUser.no==vo.memberNo }">
								<td><a href="/mysite3/board/delete/${vo.no}" class="del">삭제</a></td>
							</c:if>
						</tr>
					</c:forEach>
				</table>
				<div class="pager">
					<ul>
						<c:if test="${listData.startPage > 1 }">
							<li class="pg-prev"><a
								href="/mysite3/board?p=${listData.startPage-1 }">◀ 이전</a></li>
						</c:if>
						<c:forEach begin="${listData.startPage }"
							end="${listData.endPage }" var="pageIndex" step="1">
							<c:choose>
								<c:when test="${pageIndex > listData.totalPage}">
									<li class="disable">${pageIndex }</li>
								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${pageIndex == listData.currentPage }">
											<li>${pageIndex }</li>
										</c:when>
										<c:otherwise>
											<li><a href="/mysite3/board?p=${pageIndex }">${pageIndex }</a></li>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<c:if test="${listData.endPage < listData.totalPage }">
							<li class="pg-next"><a
								href="/mysite3/board?p=${listData.endPage+1}">다음 ▶</a></li>
						</c:if>
					</ul>
				</div>
				<div class="bottom">
					<c:if test="${not empty authUser }">
						<a href="/mysite3/board/writeform" id="new-book">글쓰기</a>
					</c:if>
				</div>
			</div>
		</div>
		<c:import url="/WEB-INF/views/include/navigation.jsp"></c:import>
		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
	</div>
</body>
</html>