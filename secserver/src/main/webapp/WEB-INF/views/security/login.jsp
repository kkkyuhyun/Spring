<%--
  Created by IntelliJ IDEA.
  User: sam99
  Date: 2024-08-19
  Time: 오후 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>login</h1>
    <form name='f' action='/security/login' method='POST'>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username' value=''>
                </td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <input name="submit" type="submit" value="Login" />
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
