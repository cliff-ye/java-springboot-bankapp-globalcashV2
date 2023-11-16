<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


   <div class="transactModal f--1 featuresModal hidden">
      <button type="button" class="btnClose closeTransact">&times;</button>
        <h1>Transact</h1>

        <div class="transact-body mt-3 ">
          <!-- transaction type dropdown list -->
          <select name="transact-type" class="form-control inputlook mb-3" id="transact-type">
            <option value="">--Select Transaction Type --</option>
            <!-- <option value="payment">Payment</option> -->
            <option value="transfer">Transfer to GC user</option>
            <option value="transferMomo">Transfer to Momo user</option>
            <option value="airtime">Airtime</option>
            <option value="deposit">Deposit</option>
            <option value="withdraw">Withdraw</option>
          </select>
          <!-- end-->

       <!-- TRANSFER MONEY CARD DISPLAYS HERE -->
		<c:import url="components/transact-forms/transfer_form.jsp" />

		<!-- TRANSFER TO MOMO ACCOUNT -->
		<c:import url="components/transact-forms/transfer_momo_form.jsp" />

		<!-- BUY AIRTIME -->
        <c:import url="components/transact-forms/airtime_form.jsp" />

        <!-- DEPOSIT CARD DISPLAYS HERE -->
		<c:import url="components/transact-forms/deposit_form.jsp" />
		
		<!-- WITHDRAWAL CARD DISPLAYS HERE -->
		<c:import url="components/transact-forms/withdraw_form.jsp" />


      </div>
    
    </div>