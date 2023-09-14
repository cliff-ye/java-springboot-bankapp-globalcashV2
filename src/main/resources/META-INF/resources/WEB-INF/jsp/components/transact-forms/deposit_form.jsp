 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 		
 <!-- deposit money card -->
         <div class="card dep-card cardlook hidden">
          <div class="card-body">
            <!-- form -->
            <form action="/transact/deposit" method="POST">
            <div class="form-group mb-3">
               <label for="">Enter Amount to Deposit(GH&#8373;)</label>
               <input type="text" name="amount" class="form-control inputlook" required="required">
            </div>

         <div class="form-group ">
          <button id="transact" class="btn--look">Done</button>
       </div>
      </form>
            <!-- end form -->
          </div>
            <!-- end deposti card body-->
      </div>
    <!-- end of deposit card -->