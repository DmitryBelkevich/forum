<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h1>Categories</h1>

<table class="table">
    <thead>
    <tr>
        <th>#</th>
        <th>Title</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${categories}" var="category" varStatus="count">
        <tr>
            <td>${count.index + 1}</td>
            <td><a href="/categories/${category.id}">${category.title}</a></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
