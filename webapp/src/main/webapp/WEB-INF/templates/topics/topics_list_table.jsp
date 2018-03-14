<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Topics</h1>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Author</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${topics}" var="topic" varStatus="count">
        <tr>
            <td>${count.index + 1}</td>
            <td><a href="/topics/${topic.id}">${topic.title}</a></td>
            <td>//TODO user1</td>
            <td>//TODO 10.10.10</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
