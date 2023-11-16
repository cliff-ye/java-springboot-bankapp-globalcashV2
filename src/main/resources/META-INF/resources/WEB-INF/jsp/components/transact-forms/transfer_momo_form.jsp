<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



   <!-- transfer momo card -->
          <div class="card transfer-momo-card cardlook hidden">
              <div class="card-body">
                <!-- form -->
                <form action="/transact/transfer-momo" method="post">
                <div class="form-group mb-2">
                   <label for="">Mobile Number</label>
                   <input type="text" name="mobNumber" class="form-control inputlook" required="required">
                </div>

                <div class="form-group mb-2">
                  <label for="">Reference</label>
                  <input type="text" name="reference" class="form-control inputlook" required="required">
               </div>

               <div class="form-group mb-3">
                <label for="">Enter Amount(GH&#8373;)</label>
                <input type="number" name="transfer_amount" class="form-control inputlook" required="required">
             </div>

             <div class="form-group">

              <button id="transact" class="btn--look">Send</button>
           </div>
           </form>
                <!-- end form -->
              </div>
                <!-- end transfer card body-->
          </div>
        <!-- end of transact card -->