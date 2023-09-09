<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

 <div class="accountModal f--4 featuresModal hidden">
      <button type="button" class="btnClose closeAcctModal">&times;</button>
       <div class="mb-3">
         <h1>Account Details</h1>
      </div>
      
       <!-- Account details card -->
       <div class="card mt-3 cardlook ">
          <div class="card-body">
             <div class="mb-3">
                <p>Account Name : <b>${userAcct.account_name }</b></p>
              </div>

              <div class="mb-3">
                <p>Account Number: <b>${userAcct.account_number }</b></p>
              </div>

              <div class="mb-3">
                <p>Account Type: <b>${userAcct.account_type }</b></p>
              </div>

              <div class="mb-3">
               <p>Account Balance: <b>GH&#8373; ${userAcct.account_balance }</b></p>
              </div>

              <div class="mb-3">
                <p>Account Created at: <b>${userAcct.created_at }</b></p>
              </div>

              <!-- end account card body-->
          </div>
      <!-- end of account card -->
      </div>
</div>