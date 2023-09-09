<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8" />
    <meta
      name="viewport"
      content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />

    <!-- Bootstrap CSS -->
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css"
      rel="stylesheet"
    />

    <title>Success</title>
    <link href="../assets/images/icons8_rocket_take_off.ico" rel="icon" />
  </head>
  <body>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script type="text/javascript">
      window.addEventListener("load", function () {
        Swal.fire({
          title: "Account created successfully",
          icon: "success",
          showCloseButton: false,
          showCancelButton: false,
          focusConfirm: false,
          confirmButtonText:
            '<a style="color:white; text-decoration:none" href="/app/dashboard"> ok</a>',
        });
      });
    </script>
  </body>
</html>
