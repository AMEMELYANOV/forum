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

    <title>Форум job4j. Вход.</title>
</head>
<body>
<div class="container mt-3">
    <div class="row">
        <div class="col-10"></div>
        <div class="col">
            <a class="btn btn-link" href="<c:url value='/reg'/>">Регистрация</a>
        </div>
        <div class="card" style="width: 100%">
            <div class="card-header">
                <h4>Форум job4j. Вход.</h4>
            </div>
            <div class="row mx-4 px-2 my-1 py-1">
                <c:if test="${not empty errorMessage}">
                    <div style="color:red; font-weight: bold; margin: 30px 0px;">
                            ${errorMessage}
                    </div>
                </c:if>
            </div>
            <form name='login' action="<c:url value='/login'/>" method='POST'>
                <div class="row mx-4 px-2 my-1 py-1">
                    <table>
                        <tr>
                            <td>UserName:</td>
                            <td><input type='text' name='username'></td>
                        </tr>
                        <tr>
                            <td>Password:</td>
                            <td><input type='password' name='password'/></td>
                        </tr>
                        <tr>
                            <td colspan='2'><input name="submit" type="submit" value="Войти"/></td>
                        </tr>
                    </table>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
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