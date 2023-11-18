 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


 		
 <!--airtime form card -->
         <div class="card airtime-card cardlook hidden">
          <div class="card-body">
            <!-- form -->
            <form action="/transact/airtime" method="POST">
            <div class="form-group mb-2">
               <label for="">Mobile Number</label>
               <input type="number" name="mobileNumber" class="form-control inputlook" required="required">
             </div>
            <div class="form-group mb-3">
               <label for="">Enter Amount(GH&#8373;)</label>
               <input type="number" name="amount" class="form-control inputlook" required="required">
            </div>

         <div class="form-group ">
          <button id="transact" class="btn--look">Send</button>
       </div>
      </form>
            <!-- end form -->
          </div>
            <!-- end airtime card body-->
      </div>
    <!-- end of airtime card -->