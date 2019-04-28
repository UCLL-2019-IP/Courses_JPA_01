<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Delete course</title>
</head>
<body>
<h1>Delete course</h1>

<p>
    <a href="/home">HOME</a>
</p>


<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Type</th>
    </tr>
    <tr>
        <td><c:out value="${ course.name }" /></td>
        <td><c:out value="${ course.description }"/></td>
        <td><c:out value=" ${ course.courseType } " /></td>
    </tr>
</table>

<ul>
    <li>
        <a href="/course">NO</a>
    </li>
    <li>
        <a href="/course/confirmdelete?name=${ course.name }">YES</a>
    </li>
</ul>

</body>
</html>