<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Update course</title>
</head>
<body>
<h1>Update course</h1>

<p>
    <a href="/home">HOME</a>
</p>

    <c:forEach var="error" items="${errors}">
        <c:out value="${error}" />
    </c:forEach>
    <form action="/course/update" method="POST">
        <input type="hidden" name="id" value="${ course.id }">

        <label for="name">Name : </label>
        <input type="text" name="name" id="name" value="${ course.name }"><br>

        <label for="description">Description : </label>
        <input type="text" name="description" id="description" value="${ course.description }"><br>

        <label for="courseType">Type : </label>
        <select name="courseType" id="courseType">
            <c:forEach var="value" items="${ values }">
                <option value="${ value }"> ${ value }</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Update"><br>
    </form>

    <a href="/course">Cancel update</a>

</body>
</html>