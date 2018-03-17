<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<h1>new Topic</h1>

<form:form method="post" action="/topics/save">
    <div class="form-group">
        <label for="categoryInput">Category</label>
        <select class="custom-select" id="categoryInput">
            <option selected>--- select category ---</option>
            <option value="1">One</option>
            <option value="2">Two</option>
            <option value="3">Three</option>
        </select>
    </div>

    <div class="form-group">
        <form:label path="title" for="titleInput">Title</form:label>
        <form:input
                path="title"
                type="text"
                class="form-control"
                id="titleInput"
                aria-describedby="emailHelp"
                placeholder="Title"
        />
    </div>

    <div class="form-group">
        <label for="messageInput">Message</label>
        <textarea
                type="text"
                class="form-control"
                id="messageInput"
                placeholder="Message"
                rows="5"
                name="text"
        ></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

</form:form>
