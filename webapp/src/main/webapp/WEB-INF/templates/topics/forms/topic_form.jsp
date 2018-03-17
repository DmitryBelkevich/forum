<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h1>new Topic</h1>

<form method="post" action="/topics/save">

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
        <label for="titleInput">Title</label>
        <input type="text" class="form-control" id="titleInput" aria-describedby="emailHelp" placeholder="Title"
               name="title">
    </div>

    <div class="form-group">
        <label for="messageInput">Message</label>
        <textarea type="text" class="form-control" id="messageInput" placeholder="Message" name="text"
                  rows="5"></textarea>
    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

</form>
