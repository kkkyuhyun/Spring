<%--
  Created by IntelliJ IDEA.
  User: sam99
  Date: 2024-08-05
  Time: 오후 3:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Insert title here</title>
</head>
<body>
<form action="/sample/exUploadPost" method="post" enctype="multipart/form-data" >
    <%-- 인코딩타입 multipart/form-data 업로드 하기 위해서 반드시 설정, method는 무조건 post---%>
    <div>
        <input type="file" name="files" />
    </div>
    <div>
        <input type="file" name="files" />
    </div>
    <div>
        <input type="file" name="files" />
    </div>
    <div>
        <input type="file" name="files" />
    </div>
    <div>
        <input type="file" name="files" />
    </div>
    <div>
        <input type="submit" />
    </div>
</form>
</body>
</html>