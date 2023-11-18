<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="Alltransactions f--3 featuresModal hidden">
      <button type="button" class="btnClose closeAlltransact">&times;</button>
   <section class="mt-5">
         <form action="">
           <h2>Recent Transactions</h2>
 
           <table class="table">
             <thead>
               <tr>
                 <th scope="col">Transaction ID</th>
                 <th scope="col">Transaction Type</th>
                 <th scope="col">Amount</th>
                 <th scope="col">Status</th>
                 <th scope="col">Status Detail</th>
                 <th scope="col">Date</th>
               </tr>
             </thead>
             <tbody class="tbody">
             <c:forEach items="${requestScope.recentTransactions}" var="r_trans">
               <tr>
                   <td>${r_trans.transaction_id}</td>
                   <td>${r_trans.transaction_type}</td>
                   <td>${r_trans.amount}</td>
                   <td>${r_trans.status}</td>
                   <td>${r_trans.status_detail}</td>
                   <td>${r_trans.created_at}</td>
                 </tr>
                 </c:forEach>
             </tbody>
           </table>
         </form>

     </section>

   </div>