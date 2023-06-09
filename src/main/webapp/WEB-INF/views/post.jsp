<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <title>Форум job4j</title>
</head>
<body>
<div class="container mt-3">
    <div class="row justify-content-end">
        <a class="btn btn-link" href="<c:url value='/'/>">Вернуться к списку объявлений</a>
        <div class="col-2 align-self-center">
            Вы вошли как : ${user.username}
        </div>
        <div class="col-2 align-self-center">
            <a class="btn btn-link" href="<c:url value='/logout'/>">Выйти</a>
        </div>
    </div>
</div>
<div class="container mt-3">
    <div class="row">
        <h4>Форум job4j</h4>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Тема</th>
                <th scope="col">Описание</th>
                <th scope="col">Пользователь</th>
                <th scope="col">Время создания</th>
            </tr>
            </thead>
            <tbody>
            <input type='hidden' name="id" value="${post.id}"/>
            <tr>
                <td><c:out value="${post.name}"/></td>
                <td><c:out value="${post.description}"/></td>
                <td><c:out value="${post.user.username}"/></td>
                <td><c:out value="${post.created}"/></td>
            </tr>
            </tbody>
        </table>
    </div>
    <br><br>
    <div class="row">
        <h6>Комментарии:</h6>
    </div>
    <div class="row">
        <table class="table">
            <thead>
            <tr>
                <th scope="col">Содержание</th>
                <th scope="col">Пользователь</th>
                <th scope="col">Время создания</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${comments}" var="comment">
                <tr>
                    <td><c:out value="${comment.text}"/></td>
                    <td><c:out value="${comment.user.username}"/></td>
                    <td><c:out value="${comment.created}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
<br><br>
<div class="container mt-3">
    <div class="row">
        <form name='comment' action="<c:url value='/addComm'/>" method='POST'>
            <table class="table">
                <input type ='hidden' name = "postId" value = "${post.id}"/>
                <tr>
                    <td>
                        <input type="submit" name="submit" value="Добавить комментарий">
                    </td>
                    <td>
                        <textarea class="form-control" name="text" placeholder="Поле для комментария"
                                  rows="5" required></textarea>
                    </td>
                </tr>
            </table>
        </form>
    </div>
</div>

<!-- Optional JavaScript -->
<!-- jQuery first, then Popper.js, then Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
        integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
        crossorigin="anonymous"></script>
</body>
</html>