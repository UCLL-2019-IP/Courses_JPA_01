<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Courses</title>
</head>
<body>
<h1>Courses</h1>

<p>
    <a href="/home">HOME</a> | <a href="/course/add">Add course</a>
</p>

<ul>
    <c:choose>
        <c:when test="${empty courses}">
            <p>No courses yet!</p>
        </c:when>
        <c:otherwise>
            <table>
                <tr>
                    <th>Name</th>
                    <th>Description</th>
                    <th>Type</th>
                    <th>Update</th>
                    <th>Delete</th>
                </tr>
                <c:forEach var="course" items="${ courses }">
                    <tr>
                        <td><c:out value="${ course.name }"/></td>
                        <td><c:out value="${ course.description }"/></td>
                        <td><c:out value=" ${ course.courseType } "/></td>
                        <td><a href="/course/update?name=${ course.name }">Update</a> </td>
                        <td><a href="/course/delete?name=${ course.name }">Delete</a> </td>
                    </tr>
                </c:forEach>
            </table>
        </c:otherwise>
    </c:choose>
</ul>

</body>
</html>