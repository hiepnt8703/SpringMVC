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
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
                <script>
                    $(document).ready(() => {
                        const avatarFile = $("#avatarFile");
                        const orgImage = "${newAvatar.image}";
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#avatarPreview").attr("src", imgURL);
                            $("#avatarPreview").css({ "display": "block" });
                        });


                    });
                </script>
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
                                    <li class="breadcrumb-item"><a style="text-decoration: none"
                                            href="/admin/user">Users</a></li>
                                    <li class="breadcrumb-item active">Edit</li>
                                </ol>
                                <div class=" mt-5">
                                    <div class="row">
                                        <div class="col-6 mx-auto">

                                            <h1 class="text-center">Update User</h1>
                                            <form:form action="/admin/user/update" method="post"
                                                modelAttribute="newUser" enctype="multipart/form-data">
                                                <div class="row">
                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">ID:</label>
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </div>
                                                    <div class="col-12 mb-3">
                                                        <c:set var="errorEmail">
                                                            <form:errors path="email" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Email address:</label>
                                                        <form:input type="email"
                                                            class=" form-control ${not empty errorEmail ? 'is-invalid' :''}"
                                                            path="email" disabled="true" />
                                                        ${errorEmail}
                                                    </div>

                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <c:set var="errorFullname">
                                                            <form:errors path="fullName" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">FullName:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorFullname ? 'is-invalid' : ''}"
                                                            path="fullName" />
                                                        ${errorFullname}
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <c:set var="errorPhone">
                                                            <form:errors path="phone" cssClass="invalid-feedback" />
                                                        </c:set>
                                                        <label class="form-label">Number Phone</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorPhone ? 'is-invalid' : ''}"
                                                            path="phone" />
                                                        ${errorPhone}
                                                    </div>
                                                </div>
                                                <div class="mb-3">
                                                    <c:set var="errorAddress">
                                                        <form:errors path="address" cssClass="invalid-feedback" />
                                                    </c:set>
                                                    <label class="form-label">Address:</label>
                                                    <form:input type="text"
                                                        class="form-control ${not empty errorAddress ? 'is-invalid' : ''}"
                                                        path="address" />
                                                    ${errorAddress}
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Role:</label>
                                                        <form:select class="form-select" path="role.name">
                                                            <form:option value="ADMIN">Admin</form:option>
                                                            <form:option value="USER">Guest</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label for="avatarfile" class="form-label">Choose
                                                            avatar:</label>
                                                        <input class="form-control" type="file" id="avatarFile"
                                                               accept=".png, .jpg, .jpeg" name="imageFile" />
                                                    </div>
                                                    <div class="col-12 mb-3">
                                                        <img style="display: none;" alt="avatar preview"
                                                             id="avatarPreview">
                                                    </div>
                                                </div>
                                                <div class="col-12 mb-5">
                                                    <button type="submit" class="btn btn-primary">Update</button>
                                                </div>
                                            </form:form>
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