<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
            <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
                <!DOCTYPE html>
                <html lang="en">

                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Final Exam Home Page</title>
                    <!-- Link to Bootstrap CSS -->
                    <link rel="stylesheet"
                        href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
                    <!-- Link to Bootstrap JS -->
                    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
                    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
                    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
                    <style>
                        <%@include file="/resource/css/main.css" %>
                    </style>
                </head>

                <body>
                    <jsp:include page="header.jsp" />
                    <div class="contain pt-10">
                        <!-- Input group -->
                        <form:form action="search" method="get">
                            <div class="d-flex ">
                                <div class="input-group w-auto">
                                    <input name="searchInput" type="text" class="form-control"
                                        placeholder="Search input" aria-label="Search input" />
                                    <button class="btn btn-primary" type="submit" data-mdb-ripple-color="dark">Search
                                    </button>
                                </div>
                            </div>
                        </form:form>

                        <div class="mt-10">
                            <p>
                                <c:out value="${msg}" />
                            </p>
                            <c:if test="${empty orderList}">
                                <p>No data</p>
                            </c:if>
                            <c:if test="${not empty orderList}">
                                <table class="table">
                                    <thead class="thead-dark">
                                        <tr>
                                            <th scope="col">OrderId</th>
                                            <th scope="col">OrderDate</th>
                                            <th scope="col">CustomerName</th>
                                            <th scope="col"></th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="order" items="${orderList}" varStatus="e">
                                            <tr>
                                                <td>${order.orderId}</td>
                                                <td>${order.orderDate}</td>
                                                <td>${order.customerName}</td>
                                                <td><button class="btn btn-sm btn-primary"
                                                        onclick="location.href='view-detail/${order.orderId}'">View
                                                        Details</button></td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>
                            </c:if>

                            <div>
                            </div>
                </body>

                </html>