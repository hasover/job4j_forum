<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <title>Форум job4j</title>
</head>
<body>
<div class="container mt-3">
    <div>
        <a href="/logout">Пользователь ${user.username}| Выйти</a>
    </div>
    <div class="row">
        <h4>Форум job4j</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col" width="20%">Тема</th>
                <th scope="col" width="40%">Описание</th>
                <th scope="col" width="15%">Автор</th>
                <th scope="col" width="25%">Дата создания</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${topics}" var="topic">
                <tr>
                    <td>
                        <c:if test="${user.id == topic.user.id}">
                        <a href="<c:url value="topic?topicId=${topic.id}"/>">
                            <i class="fa fa-edit mr-3"></i>
                        </a>
                        </c:if>

                        <a href="<c:url value='posts?topicId=${topic.id}'/>"> <c:out value="${topic.name}"/> </a>
                    </td>
                    <td><c:out value="${topic.description}"/></td>
                    <td><c:out value="${topic.user.username}"/></td>
                    <td><fmt:formatDate value="${topic.created.time}" type="both" dateStyle="short"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
    <div>
        <a href="topic">Новая тема</a>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
</body>
</html>