<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Topic</h1>

<h2>topic1</h2>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Author</th>
        <th>Message</th>
        <th>Date</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${messages}" var="message" varStatus="count">
        <tr>
            <td>${count.index + 1}</td>
            <td>${message.user.username}</td>
            <td>${message.text}</td>
            <td>${message.formattedDate}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
