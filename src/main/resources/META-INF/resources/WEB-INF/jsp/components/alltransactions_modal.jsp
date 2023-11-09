<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="Alltransactions f--3 featuresModal hidden">
      <button type="button" class="btnClose closeAlltransact">&times;</button>
      <section class="container">
     <div class="row no-gutters align-items-center">
       <div class="col-lg-12">
         <div class="card user-card">
           <div class="card-body">
             <div class="row">
               <div class="col-lg-4 order-1 order-md-1">
                 <div class="card user-card">
                   <div class="card-body">
                     <h5 class="card-title">Account Name</h5>
                     <div class="d-flex align-items-center">
                       <div class="card-icon">
                         <img
                           src="../assets/images/icons8_account.ico"
                           alt=""
                         />
                       </div>
                       <div class="ps-3">
                         <h6 class="totalwithd">Clifford Agyei</h6>
                       </div>
                     </div>
                   </div>
                 </div>
               </div>

               <div class="col-lg-4 order-2 order-md-2">
                 <div class="card user-card">
                   <div class="card-body">
                     <h5 class="card-title">Account Number</h5>
                     <div class="d-flex align-items-center">
                       <div class="card-icon">
                         <img
                           src="../assets/images/icons8_authentication_2.ico"
                           alt=""
                         />
                       </div>
                       <div class="ps-3">
                         <h6 class="bal">xxxxxxxx</h6>
                       </div>
                     </div>
                   </div>
                 </div>
               </div>

               <div class="col-lg-4 order-3 order-md-3">
                 <div class="card user-card">
                   <div class="card-body">
                     <h5 class="card-title">Account Balance</h5>
                     <div class="d-flex align-items-center">
                       <div class="card-icon">
                         <img
                           src="../assets/images/icons8_Sales_Performance_Balance.ico"
                           alt=""
                         />
                       </div>
                       <div class="ps-3">
                         <h6 class="bal">GH&#8373; 3,264</h6>
                       </div>
                     </div>
                   </div>
                 </div>
               </div>
             </div>
           </div>
         </div>
       </div>
     </div>
   </section>

   <section class="mt-5">
         <form action="">
           <h2>All Transactions</h2>
 
           <table class="table">
             <thead>
               <tr>
                 <th scope="col">Transaction ID</th>
                 <th scope="col">Account Number</th>
                 <th scope="col">Transaction Type</th>
                 <th scope="col">Amount</th>
                 <th scope="col">Status</th>
                 <th scope="col">Status Detail</th>
                 <th scope="col">Date</th>
               </tr>
             </thead>
             <tbody class="tbody">
               <!-- <tr>
                   <th scope="row"></th>
                   <td></td>
                   <td></td>
                 </tr> -->
             </tbody>
           </table>
         </form>
     </section>
   </div>