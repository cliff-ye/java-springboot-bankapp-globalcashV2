<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Register</title>
    <link href="../assets/images/icons8_rocket_take_off.ico" rel="icon" />
    <!-- google fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@10..48,300;10..48,400;10..48,600&family=Roboto:wght@900&display=swap"
      rel="stylesheet"
    />
    <!-- bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"/>
      
    <!-- custom css -->
  <link rel="stylesheet" href="assets/css/style.css" />
  </head>
  <body class="d-flex " bg-img bg-cover" style="background-image: url('assets/images/adolfo-felix-Yi9-QIObQ1o-unsplash.jpg')">
    
    
    
    <div class="signupModal">
 		<!-- password mismatch message -->
 		<c:if test="${requestScope.passMismatch != null}" >
 			<div class="alert alert-danger text-center border border-danger ">
 				<b>${requestScope.passMismatch}</b>
 			</div>
 		</c:if>
 		
 		
 		
 		<!-- failed registered message -->
 		<c:if test="${requestScope.failedmessage != null}" >
 			<div class="alert alert-danger text-center border border-danger">
 				<b>${requestScope.failedmessage}</b>
 			</div>
 		</c:if>
 
      <form:form action="/reguser" method="POST" modelAttribute="user">
        <h1>
          Register
        </h1>
   		
        <div class="row">
	        <div class="col mb-3">
	          <form:label path="first_name" class="form-label">Firstname*</form:label>
	          <form:input type="text" path="first_name" class="form-control inputlook input--firstname" pattern="[a-zA-Z]*" required="required" autocomplete="off"/>
	          <form:errors path="first_name" cssClass="text-danger" />
	        </div>
	
	        <div class="col mb-3">
	          <form:label path="last_name" class="form-label">Lastname*</form:label>
	          <form:input type="text" path="last_name" class="form-control inputlook input--lastname" pattern="[a-zA-Z]*" required="required" autocomplete="off"/>
	          <form:errors path="last_name" cssClass="text-danger" />
	        </div>
      </div>
      
      <div class="row">
	      <div class="col mb-3">
	          <form:label path="phone" class="form-label">Phone*</form:label>
	          <form:input type="number" path="phone" class="form-control inputlook input--phone" required="required" autocomplete="off"/>
	          <form:errors path="phone" cssClass="text-danger" />
	        </div>
	
	        <div class="col mb-3">
	          <form:label path="email" class="form-label">Email*</form:label>
	          <form:input type="email" path="email" class="form-control inputlook input--email"  required="required" autocomplete="off"/>
	          <form:errors path="email" cssClass="text-danger" />
	        </div>
        </div>
        
        <div class="row">
	        <div class="col mb-3">
	          <form:label path="email" class="form-label">Password*</form:label>
	          <form:input type="password" path="password" class="form-control inputlook input--password" required="required" autocomplete="off"/>
	          <form:errors path="password" cssClass="text-danger" />
	        </div>
	        
	        
	        <div class="col mb-3">
	          <label for="confirm_password" class="form-label">Confirm Password*</label>
	          <input type="password" class="form-control inputlook input--confirm_password" id="confirm_password" name="confirm_password" autocomplete="off"/>
	          <small class="text-white">${confirm_pass}</small>
	        </div>
      </div>

        <div class="mb-3">
          <button type="submit" class="btn btn-success btn--look btn--register">
            Submit
          </button>
        </div>
        
        <div>
          <p>Already have an account? <a href="/login">Login</a></p>
        </div>
      </form:form>
    </div>
 

    <div class="overlay "></div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  </body>
</html>
