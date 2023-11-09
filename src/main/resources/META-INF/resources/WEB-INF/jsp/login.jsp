<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Login</title>
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
  <body class="" bg-img bg-cover" style="background-image: url('assets/images/priscilla-du-preez-nF8xhLMmg0c-unsplash.jpg')">
    
   
       <div class="loginModal ">
          
               <!-- sending empty fields message -->
 		<c:if test="${requestScope.errormsg != null}" >
 			<div class="text-danger">
 				<b>${requestScope.errormsg}</b>
 			</div>
 		</c:if>
 		
          <!-- successfully registered message -->
 		<c:if test="${requestScope.successmessage != null}" >
 			<div class="alert alert-success text-center border border-success">
 				<b>${requestScope.successmessage}</b>
 			</div>
 		</c:if>
 		
 		<!-- incorrect pass or email message -->
 			<c:if test="${requestScope.errorMsg1 != null}" >
 			<div class="text-danger">
 				<b>${requestScope.errorMsg1}</b>
 			</div>
 		</c:if>
 		
 		<!-- user cannot be found in db message -->
 			<c:if test="${requestScope.errorMsg2 != null}" >
 			<div class="text-danger">>
 				<b>${requestScope.errorMsg2}</b>
 			</div>
 		</c:if>
 			<h1>
                <img src="assets/images/icons8_rocket_take_off_50px.png" alt="" />
                login
              </h1>
              
            <form action="/login" method="post">
              
              <div class="mb-1">
                <label for="email" class="form-label">Email</label>
                <input type="text" class="form-control inputlook input--username" id="email" name="email"/>
              </div>
      
              <div class="mb-3">
                <label for="password" class="form-label">Password</label>
                <input type="password" class="form-control inputlook input--password" id="password" name="password"/>
              </div>
              
        		<input type="hidden" id="token" name="token" value="${token}" />	
                
              <div class="mb-3">
                <button type="submit" class="btn btn-primary btn--look btn--login">
                  Login
                </button>
              </div>
              <div>
                <p>Don't have an account? <a href="/reguser">Sign up</a></p>
              </div>
            </form>
          </div>
     
  

    <!-- End Hero -->

    <div class="overlay "></div>
  </body>
</html>
