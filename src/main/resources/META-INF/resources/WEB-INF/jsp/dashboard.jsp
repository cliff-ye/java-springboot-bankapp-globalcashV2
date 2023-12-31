<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>global cash</title>
    <link href="../assets/images/icons8_rocket_take_off.ico" rel="icon" />

    <!-- google fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@10..48,300;10..48,400;10..48,600&family=Roboto:wght@900&display=swap"
      rel="stylesheet"
    />

    <link
      rel="stylesheet"
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css"
    />

    <link rel="stylesheet" href="../assets/css/dashboard.css" />

  </head>
  
  <body>
    <div class="mainDiv" >
      <header class="header" bg-img bg-cover" style="background-image: url('../assets/images/moja-msanii-vO9-gal54go-unsplash1.jpg')">
        <nav id="main-navbar" class="navbar navbar-dark navbar-expand-md">
          <div class="container-fluid">
            <a class="navbar-brand ms-5" href="#"
              ><img
                src="../assets/images/icons8_rocket_take_off_50px.png"
                alt=""
              />
              <span>globalcash</span></a
            >

            <button
              class="navbar-toggler"
              type="button"
              data-bs-toggle="collapse"
              data-bs-target="#navbarNavAltMarkup"
              aria-controls="navbarNav"
              aria-expanded="false"
              aria-label="Toggle navigation"
            >
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
              <div class="navbar-nav ms-auto">
                <a id="customer-name" class="nav-link active" aria-current="page" href="#">
                  <img
                    src="../assets/images/icons8_male_user_30px.png"
                    alt="Profile"
                    class="rounded-circle"
                  />
                  <span id="labelName"> ${userAcct.account_name} </span>
                </a>
              </div>
              <div class="d-flex ms-auto" role="search">
                <a href="/logout-user" id="logout-btn" class="btn btn-primary">Logout</a>
              </div>
            </div>
          </div>
        </nav>
        
        <div class="container">
	<!-- sending empty fields message -->
 		<c:if test="${requestScope.error != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.error}</b>
 			</div>
 		</c:if>
 		
 		<!-- account not existing -->
 		<c:if test="${requestScope.userNotFoundError != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.userNotFoundError}</b>
 			</div>
 		</c:if>
 		
 		<!-- transfer to same account error -->
 		<c:if test="${requestScope.transError != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.transError}</b>
 			</div>
 		</c:if>
 		
 		<!-- sending zero amount as deposit -->
 		<c:if test="${requestScope.zeroerror != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.zeroerror}</b>
 			</div>
 		</c:if>
 		
 		<!-- failed to make deposit -->
 		<c:if test="${requestScope.failedmsg != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.failedmsg}</b>
 			</div>
 		</c:if>
 		
 		<!-- insufficient funds -->
 		<c:if test="${requestScope.insufficientFunds != null}" >
 			<div class="alert alert-danger text-center border border-danger mb-0">
 				<b>${requestScope.insufficientFunds}</b>
 			</div>
 		</c:if>
 		
 		<!-- successful  -->
 		<c:if test="${requestScope.successmsg != null}" >
 			<div class="alert alert-success text-center border border-success mb-0">
 				<b>${requestScope.successmsg}</b>
 			</div>
 		</c:if>
 		</div>
 		
        <section class="container-fluid dashboard">
          <div class="row no-gutters align-items-center">
            <div class="col-lg-3 order-1 order-md-1 cards">
              <div class="card info-card deposit-card">
                <div class="card-body">
                 <img src="../assets/images/icons8_total_sales.ico" alt="">
                 	 Total Deposits
                  <div class="d-flex align-items-center"> 
                    <div class="ps-1 mt-3">
                      <h6 class="totaldep">GH&#8373; ${totalDeps}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-lg-3 order-2 order-md-2 cards">
              <div class="card info-card withdrawal-card">
                <div class="card-body">
                <img src="../assets/images/icons8_withdrawal.ico" alt="">
                  Total Withdrawals
                  <div class="d-flex align-items-center">
                    <div class="ps-1 mt-3">
                      <h6 class="totalwithd">GH&#8373; ${totalWithd}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            
            <div class="col-lg-3 order-3 order-md-3 cards">
              <div class="card info-card withdrawal-card">
                <div class="card-body">
                	<img src="../assets/images/icons8_Credit_Card_Transfer.ico" alt="">
                	  Total transfers
                  <div class="d-flex align-items-center">
                    <div class="ps-1 mt-3">
                      <h6 class="totalwithd">GH&#8373; ${totalTransfer}</h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div class="col-lg-3 order-4 order-md-4 cards">
              <div class="card info-card balance-card">
                <div class="card-body">
                  <img src="../assets/images/icons8_Sales_Performance_Balance.ico" alt="">
             		    Balance
                  <div class="d-flex align-items-center">
                    <div class="ps-1 mt-3">
                      <h6 class="bal">GH&#8373; ${totalAcctBal} </h6>
                    </div>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </section>
      </header>
    </div>

      <section class="container-fluid">
        <div class="row no-gutters align-items-center feature--btn">
          <div class="col-lg-3 order-1 order-md-1 items">
            <div class="text-center d-lg-block mt-5">
              <button class="btn" data-tab="1">
                <img src="../assets/images/icons8_wallet.ico" alt="" />
                <p>
                 transact <br />
                   make safe transactions
                </p>
              </button>
            </div>
          </div>

          <div class="col-lg-3 order-2 order-md-2 items">
            <div class="text-center d-lg-block mt-5">
              <button class="btn" data-tab="2">
                <img src="../assets/images/icons8_payment_history.ico" alt="" />
                <p>Transfer History <br />all transfers to other accounts</p>
              </button>
            </div>
          </div>

          <div class="col-lg-3 order-3 order-md-3 items">
            <div class="text-center d-lg-block mt-5">
              <button class="btn" data-tab="3">
                <img src="../assets/images/icons8_transaction_1.ico" alt="" />
                <p>
                  Transaction History <br />
                   all transactions on account
                </p>
              </button>
            </div>
          </div>

          <div class="col-lg-3 order-4 order-md-4 items">
            <div class="text-center d-lg-block mt-5">
              <button class="btn" data-tab="4">
                <img src="../assets/images/icons8_account.ico" alt="" />
                <p>
                   Account <br />
                   see account details here
                </p>
              </button>
            </div>
          </div>
        </div>
      </section>
    </div>

  
   <!-- TRANSACT MODAL HERE -->
	<c:import url="components/transact_modal.jsp" />
	
	<!-- ALL TRANSFERS DISPLAY DETAILS HERE -->
   <c:import url="components/alltransfers_modal.jsp" />
   
     <!-- ALL TRANSACTIONS DISPLAY DETAILS HERE -->
   <c:import url="components/alltransactions_modal.jsp" />
    
	<!-- ACCOUNT DISPLAY DETAILS HERE -->
   <c:import url="components/account_display.jsp" />
   

    <div class="overlay hidden"></div>
    
     <footer class="footer">
      <div class="container-fluid">
        cliff-ye
        <img src="../assets/images/icons8_GitHub.ico" alt="">
      </div>
    </footer>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js"></script>
    <script src="../assets/js/main.js"></script>
  </body>
</html>


