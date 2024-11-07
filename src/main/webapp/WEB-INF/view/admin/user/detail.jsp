<%@page contentType="text/html" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>${title}</title>
                <!-- Latest compiled and minified CSS -->
                <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
                <!-- Latest compiled JavaScript -->
                <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
                <!--Jquery-->
                <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
            </head>

            <body>
                <div class="container mt-5">
                    <div class="row">
                        <div class="col-12 mx-auto">
                            <div class="d-flex justify-content-between">
                                <h3>User Detail with ID: ${id}</h3>
                            </div>
                            <hr>
                            <div class="card" style="width: 60%">
                                <div class="card-header">
                                    Information
                                </div>
                                <ul class="list-group list-group-flush">
                                    <li class="list-group-item">Email address: ${user.email}</li>
                                    <li class="list-group-item">Password: ${user.password}</li>
                                    <li class="list-group-item">Full name: ${user.fullName}</li>
                                    <li class="list-group-item">Address: ${user.address}</li>
                                    <li class="list-group-item">Phone: ${user.phone}</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </body>

            </html>