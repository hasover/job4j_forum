<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form name='post' action="<c:url value='/post'/>" method='POST'>
    <table>
        <tr>
            <td>Пост:</td>
            <td><input type='text' name='description' value="${post.description}"/></td>
        </tr>
        <tr>
            <input type="hidden" name="topicId" value="${param.topicId}">
            <c:if test="${not empty post.id}">
                <input type="hidden" name="id" value="${post.id}">
            </c:if>
            <td colspan='2'><input name="submit" type="submit" value="submit" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>