<%--
  Created by IntelliJ IDEA.
  User: sam99
  Date: 2024-08-08
  Time: 오후 2:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<%@ include file="../layouts/header.jsp" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 class="page-header my-4"><i class="far fa-file-alt"></i> ${board.title}</h1>
<div class="d-flex justify-content-between">
    <div><i class="fas fa-user"></i> ${board.writer}</div>
    <div>
        <i class="fas fa-clock"></i>
        <fmt:formatDate pattern="yyyy-MM-dd" value="${board.regDate}"/>
    </div>
</div>

<div>
    ${board.content}
</div>

<div class="text-end">
    <c:forEach var="file" items="${board.attaches}">
        <div class="attach-file-item">
            <a href="/board/download/${file.no}" class="file-link">
                <i class="fa-solid fa-floppy-disk"></i>
                    ${file.filename} (${file.fileSize})<br>
                <%-- 원본 이름    /board/download/2(경로변수) --%>
            </a>
        </div>
    </c:forEach>
</div>

<div class="mt-4">
    <a href="list" class="btn btn-primary"><i class="fas fa-list"></i> 목록</a>
    <a href="update?no=${board.no}" class="btn btn-primary"><i class="far fa-edit"></i> 수정</a>
    <a href="#" class="btn btn-primary delete"><i class="fas fa-trash-alt"></i> 삭제</a>
</div>

<form action="delete" method="post" id="deleteForm">
    <input type="hidden" name="no" value="${board.no}"/>
</form>
<script src="/resources/js/board.js"></script>
<%@ include file="../layouts/footer.jsp" %>



</body>
</html>

