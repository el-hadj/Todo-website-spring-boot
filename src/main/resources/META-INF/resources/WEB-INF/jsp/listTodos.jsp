    <%@ include file="common/header.jspf"%>
    <%@ include file="common/navigation.jspf"%>
    <div class="container">
            <h2> Your Todos</h2>
            <table class="table">
              <thead>
                <tr>
                  <th scope="col">Description</th>
                  <th scope="col">Target Date</th>
                  <th scope="col">Is Done</th>
                  <th scope="col"></th>
                </tr>
              </thead>
              <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                      <td>${todo.description}</td>
                      <td>${todo.targetDate}</td>
                      <td>${todo.done}</td>
                      <td><a href="/todo/delete?id=${todo.id}" class="btn btn-warning">Delete</a></td>
                      <td><a href="/todo/update?id=${todo.id}" class="btn btn-success">Update</a></td>
                     </tr>
                </c:forEach>
              </tbody>
            </table>
            <div><a href="/todo/add"> <button type="button" class="btn btn-success" clic>Add Todo</button></a></div>
    </div>

    <%@ include file="common/footer.jspf"%>