    <%@ include file="common/header.jspf"%>
    <%@ include file="common/navigation.jspf"%>
    <div class="container">
        <h1>Enter Todo Details</h1>
        <form:form method="post" modelAttribute="todo">
          <fieldset class="mb-3">
            <label for="exampleInputEmail1">Description</label>
            <form:input type="text" path="description" required="required"/>
            <form:errors path="description" cssClass="text-warning"/>
          </fieldset>

          <fieldset class="mb-3">
              <label for="exampleInputDate">Target Date</label>
              <form:input type="text" path="targetDate" required="required"/>
              <form:errors path="targetDate" cssClass="text-warning"/>
          </fieldset>


            <form:input type="hidden" path="id" />
            <form:input type="hidden" path="done"  />
          <button type="submit" class="btn btn-primary">Submit</button>
       </form:form>
    </div>
    <%@ include file="common/footer.jspf"%>
     <script>
         $('#targetDate').datepicker({
             format: 'yyyy-mm-dd'
         });
       </script>