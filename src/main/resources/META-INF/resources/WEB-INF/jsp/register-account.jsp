<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Create account</title>
    <link href="../assets/images/icons8_rocket_take_off.ico" rel="icon" />
    <!-- google fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@10..48,300;10..48,400;10..48,600&family=Roboto:wght@900&display=swap"
      rel="stylesheet"
    />
    <!-- bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />
 

    <!-- custom css -->
    <link rel="stylesheet" href="assets/css/style.css" />
  </head>
  <body class="d-flex " bg-img bg-cover" style="background-image: url('assets/images/helena-lopes-PGnqT0rXWLs-unsplash.jpg')">
    
    <div class="signup2Modal">
   		 <!-- failed registered message -->
 		<c:if test="${requestScope.failedmsg != null}" >
 			<div class="alert alert-danger text-center border border-danger">
 				<b>${requestScope.failedmsg}</b>
 			</div>
 		</c:if>
 		
 		<!-- sending no data message -->
 		<c:if test="${requestScope.erromsg != null}" >
 			<div class="alert alert-danger text-center border border-danger">
 				<b>${requestScope.erromsg}</b>
 			</div>
 		</c:if>
 		
      <form:form action="/reg-acct" method="post" modelAttribute="account">
        <h1>
          Create Account 
        </h1>
       
        <div class="mb-3">
          <form:label path="account_name" class="form-label" >Account Name*</form:label>
          <form:input path="account_name" type="text" class="form-control inputlook input--accountName" pattern="[a-zA-Z ]*" required="required"/>
          <form:errors path="account_name" cssClass="text-danger"/>
        </div>

        <div class="mb-4">
         <form:label path="account_type">Select Account Type*</form:label><br>
          <form:select path="account_type" class="form-control inputlook">
            <form:option path="account_type" value="">-- Select Account Type --</form:option>
            <form:option path="account_type" value="check">Cheque</form:option>
            <form:option path="account_type" value="savings">Savings</form:option>
            <form:option path="account_type" value="business">Business</form:option>
          </form:select>
          <form:errors path="account_type" cssClass="text-danger" />
        </div>

     

        <div class="mb-3">
          <button type="submit" class="btn btn-primary btn--look btn--register">
            Submit
          </button>
        </div>
      </form:form>
    </div>



    <div class="overlay"></div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
