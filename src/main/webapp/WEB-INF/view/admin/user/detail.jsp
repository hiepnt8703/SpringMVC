<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="utf-8" />
                <meta http-equiv="X-UA-Compatible" content="IE=edge" />
                <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
                <meta name="description" content="Hỏi Dân IT - Dự án laptopshop" />
                <meta name="author" content="Hỏi Dân IT" />
                <title>Dashboard - Tuan Hiep Shop</title>
                <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
                <link href="/css/styles.css" rel="stylesheet" />
                <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
            </head>

            <body class="sb-nav-fixed">
                <jsp:include page="../layout/header.jsp" />
                <div id="layoutSidenav">
                    <jsp:include page="../layout/sidebar.jsp" />
                    <div id="layoutSidenav_content">
                        <main>
                            <div class="container-fluid px-4">
                                <h1 class="mt-4">Dashboard</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a style="text-decoration: none"
                                            href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item "><a style="text-decoration: none"
                                            href="/admin/user">Users</a></li>
                                    <li class="breadcrumb-item active">Detail</li>
                                </ol>
                                <div class="container mt-5">
                                    <div class="row">
                                        <div class="col-12 mx-auto">
                                            <div class="d-flex justify-content-between">
                                                <h3>User Detail with ID: ${id}</h3>
                                            </div>
                                            <hr>
                                            <div class="card" style="width: 60%">
                                                <img class="card-img-top" src="/images/avatar/${user.avatar}"
                                                    alt="Card image cap">
                                                <div class="card-header">
                                                    Information
                                                </div>
                                                <ul class="list-group list-group-flush">
                                                    <li class="list-group-item">Email address: ${user.email}</li>
                                                    <li class="list-group-item">Password: ${user.password}</li>
                                                    <li class="list-group-item">Full name: ${user.fullName}</li>
                                                    <li class="list-group-item">Address: ${user.address}</li>
                                                    <li class="list-group-item">Phone: ${user.phone}</li>
                                                    <li class="list-group-item">Role: ${user.role.name}</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </main>
                        <jsp:include page="../layout/footer.jsp" />
                        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
                            crossorigin="anonymous"></script>
                        <script src="js/scripts.js"></script>
                        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.8.0/Chart.min.js"
                            crossorigin="anonymous"></script>
                        <script src="js/chart-area-demo.js"></script>
                        <script src="js/chart-bar-demo.js"></script>
                        <script
                            src="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/umd/simple-datatables.min.js"
                            crossorigin="anonymous"></script>
                        <script src="js/datatables-simple-demo.js"></script>
            </body>

            </html>