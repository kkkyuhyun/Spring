<%--
  Created by IntelliJ IDEA.
  User: yhkim
  Date: 2024-08-04
  Time: 오전 12:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html>
<%@ include file="../layouts/header.jsp"%>

<%--&lt;%&ndash; 개별 페이지 &ndash;%&gt;--%>
<%--<h1>목록 보기</h1>--%>
<%--    --%>
<%--<div>--%>
<%--    --%>
<%--</div>--%>

<h1 class="page-header my-4"><i class="fas fa-list"></i> 글 목록</h1>

<table class="table table-hover">
    <thead>
    <tr>
        <th style="width: 60px">No</th>
        <th>제목</th>
        <th style="width: 100px">작성자</th>
        <th style="width: 130px">등록일</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="board" items="${list}">
        <tr>
            <td>${board.no}</td>
            <td><a href="get?no=${board.no}">${board.title}</a> </td>
            <td>${board.writer}</td>
            <td><fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}" /></td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-right">
    <a href="create" class="btn btn-primary">
        <i class="far fa-edit"></i>
        글쓰기
    </a>
</div>

<%@ include file="../layouts/footer.jsp"%>