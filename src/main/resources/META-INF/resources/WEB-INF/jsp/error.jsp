<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Error</title>
    <link href="../assets/images/icons8_rocket_take_off.ico" rel="icon" />
    <!-- google fonts -->
    <link
      href="https://fonts.googleapis.com/css2?family=Bricolage+Grotesque:opsz,wght@10..48,300;10..48,400;10..48,600&family=Roboto:wght@900&display=swap"
      rel="stylesheet"
    />
    <!-- bootstrap -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <!-- custom css -->
    <link rel="stylesheet" href="assets/css/style.css" />
    <style>
      body {
        background-color: #ffffff;
        height: 100vh;
      }

      .card .card-text {
        font-size: 16px;
      }
    </style>
  </head>
  <body class="d-flex align-items-center justify-content-center">
    <div
      class="card col-4 alert alert-danger border border-danger text-danger error-card"
    >
      <h3 class="card-title">
        <img src="assets/images/icons8_error.ico" alt="" /> Errors
      </h3>
      <hr />
      <div class="card-body">
        <p class="card-text">
          ${errorDetail}
        </p>
        <hr />
        <a href="/login">Back</a>
      </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
  <!--  <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript">
      window.addEventListener("load", function () {
        Swal.fire({
          title: "Oops...Something went wrong",
          icon: "error",
          showCloseButton: false,
          showCancelButton: false,
          focusConfirm: false,
          confirmButtonText:
            '<a style="color:white; text-decoration:none" href="/no-acct"> ok</a>',
        });
      });
    </script>-->
  </body>
</html>
