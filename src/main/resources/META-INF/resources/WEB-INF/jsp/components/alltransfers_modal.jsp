<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="Alltransfers f--2 featuresModal hidden">
      <button type="button" class="btnClose closeAlltransfers">&times;</button>

   <section class="mt-5">
         <form action="">
           <h2>All Money Transfers</h2>
 
           <table class="table">
             <thead>
               <tr>
                 <th scope="col">Transaction ID</th>
                 <th scope="col">To(Acc No.)</th>
                 <th scope="col">Account Name</th>
                 <th scope="col">Amount</th>
                 <th scope="col">Reference</th>
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