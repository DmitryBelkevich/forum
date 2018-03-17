<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Category 1</h1>

<h2>Topics list</h2>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Title</th>
        <th>Category</th>
        <th>Author</th>
        <th>Last message date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${topics}" var="topic" varStatus="count">
        <tr>
            <td>${count.index + 1}</td>
            <td><a href="/topics/${topic.id}">${topic.title}</a></td>
            <td>${topic.category.title}</td>
            <td>${topic.user.username}</td>
            <td>${topic.lastMessage.formattedDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
