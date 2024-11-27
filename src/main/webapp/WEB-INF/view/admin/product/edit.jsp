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
                        const avatarFile = $("#productFile");
                        const orgImage = "${newProduct.image}";
                        if (orgImage) {
                            const urlImage = "/images/product/" + orgImage;
                            $("#productPreview").attr("src", urlImage);
                            $("#productPreview").css({ "display": "block" });
                        }
                        avatarFile.change(function (e) {
                            const imgURL = URL.createObjectURL(e.target.files[0]);
                            $("#productPreview").attr("src", imgURL);
                            $("#productPreview").css({ "display": "block" });
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
                                <h1 class="mt-4">Products</h1>
                                <ol class="breadcrumb mb-4">
                                    <li class="breadcrumb-item"><a href="/admin">Dashboard</a></li>
                                    <li class="breadcrumb-item active">Product</li>
                                </ol>
                                <div class="mt-5">
                                    <div class="row">
                                        <div class="col-md-6 col-12 mx-auto">
                                            <h1 class="text-center">Edit Product</h1>
                                            <form:form action="/admin/product/update" method="post"
                                                modelAttribute="newProduct" enctype="multipart/form-data">
                                                <c:set var="errorName">
                                                    <form:errors path="name" cssClass="invalid-feedback" />
                                                </c:set>

                                                <c:set var="errorPrice">
                                                    <form:errors path="price" cssClass="invalid-feedback" />
                                                </c:set>

                                                <c:set var="errorDetail">
                                                    <form:errors path="detailDesc" cssClass="invalid-feedback" />
                                                </c:set>

                                                <c:set var="errorDetailShort">
                                                    <form:errors path="shortDesc" cssClass="invalid-feedback" />
                                                </c:set>

                                                <c:set var="errorQuantity">
                                                    <form:errors path="quantity" cssClass="invalid-feedback" />
                                                </c:set>
                                                <div class="row">
                                                    <div class="mb-3" style="display: none;">
                                                        <label class="form-label">ID:</label>
                                                        <form:input type="text" class="form-control" path="id" />
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Name:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorName ? 'is-invalid' : ''}"
                                                            path="name" />
                                                        ${errorName}
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Price:</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorPrice ? 'is-invalid' : ''}"
                                                            path="price" />
                                                        ${errorPrice}
                                                    </div>

                                                </div>
                                                <div class="mb-3">
                                                    <label class="form-label">Detail Description:</label>
                                                    <form:input type="text"
                                                        class="form-control py-4 ${not empty errorDetail ? 'is-invalid' : ''}"
                                                        path="detailDesc" />
                                                    ${errorDetail}
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Short Description:</label>
                                                        <form:input type="text"
                                                            class="form-control ${not empty errorDetailShort ? 'is-invalid' : ''}"
                                                            path="shortDesc" />
                                                        ${errorDetailShort}
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Quantity:</label>
                                                        <form:input type="number"
                                                            class="form-control ${not empty errorQuantity ? 'is-invalid' : ''}"
                                                            path="quantity" />
                                                        ${errorQuantity}
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Factory:</label>
                                                        <form:select class="form-select" path="factory">
                                                            <form:option value="Apple">Apple</form:option>
                                                            <form:option value="Xiaomi">Xiaomi</form:option>
                                                            <form:option value="Samsung">Samsung</form:option>
                                                            <form:option value="Lenovo">Lenovo</form:option>
                                                            <form:option value="Asus">Asus</form:option>
                                                        </form:select>
                                                    </div>
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label class="form-label">Target:</label>
                                                        <form:select class="form-select" path="target">
                                                            <form:option value="Office">Office</form:option>
                                                            <form:option value="Gaming">Gaming</form:option>
                                                            <form:option value="BusinessMan">Doanh nhân</form:option>
                                                            <form:option value="Student">Sinh viên</form:option>
                                                        </form:select>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="col-12 mb-3 col-md-6">
                                                        <label for="productfile" class="form-label">Choose
                                                            image:</label>
                                                        <input class="form-control" type="file" id="productFile"
                                                            accept=".png, .jpg, .jpeg" name="imageFile" />
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class=" mb-3 col-md-6">
                                                        <img style="display: none;" alt="avatar preview"
                                                            id="productPreview">
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