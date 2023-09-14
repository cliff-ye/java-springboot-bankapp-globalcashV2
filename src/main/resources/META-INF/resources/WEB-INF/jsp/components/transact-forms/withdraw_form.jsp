<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

    <!-- withdraw money card -->
    <div class="card withdraw-card cardlook hidden">
      <div class="card-body">
        <!-- form -->
        <form action="/transact/withdraw" method="post">
	        <div class="form-group mb-3">
	           <label for="">Enter Amount to Withdraw(GH&#8373;)</label>
	           <input type="text" name="amount" class="form-control inputlook" required="required">
	        </div>

     	<div class="form-group ">
     	 <button id="transact" class="btn--look">Done</button>
   </div>
  </form>
        <!-- end form -->
      </div>
        <!-- end withdraw card body-->
  </div>
<!-- end of withdraw card -->