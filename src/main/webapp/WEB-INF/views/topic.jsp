<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<body>
<form name='topic' action="<c:url value='/topic'/>" method='POST'>
    <table>
        <tr>
            <td>Название:</td>
            <td><input type='text' name='name' value="${topic.name}"></td>
        </tr>
        <tr>
            <td>Описание:</td>
            <td><input type='text' name='description' value="${topic.description}"/></td>
        </tr>
        <tr>
            <c:if test="${not empty topic.id}">
                <input type="hidden" name="id" value="${topic.id}">
            </c:if>
            <td colspan='2'><input name="submit" type="submit" value="submit" />
            </td>
        </tr>
    </table>
</form>
</body>
</html>