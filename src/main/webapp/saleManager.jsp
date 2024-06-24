<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html
    lang="en"
    class="light-style layout-menu-fixed"
    dir="ltr"
    data-theme="theme-default"
    data-assets-path="../bootstrap/assets/"
    data-template="vertical-menu-template-free"
    >
    <head>
        <meta charset="utf-8"/>
        <meta
            name="viewport"
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            />

        <title>Sale Manager</title>

        <meta name="description" content=""/>

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>

        <!-- Fonts -->
        <link rel="preconnect" href="https://fonts.googleapis.com"/>
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin/>
        <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
            />

        <!-- Icons. Uncomment required icon fonts -->
        <link rel="stylesheet" href="bootstrap/assets/vendor/fonts/boxicons.css"/>

        <!-- Core CSS -->
        <link rel="stylesheet" href="bootstrap/assets/vendor/css/core.css" class="template-customizer-core-css"/>
        <link rel="stylesheet" href="bootstrap/assets/vendor/css/theme-default.css" class="template-customizer-theme-css"/>
        <link rel="stylesheet" href="bootstrap/assets/css/demo.css"/>

        <!-- Vendors CSS -->
        <link rel="stylesheet" href="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css"/>

        <!-- Page CSS -->
        <link rel="stylesheet" href="adminpage/product-list/product-list.css">

        <!-- Helpers -->
        <script src="bootstrap/assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="bootstrap/assets/js/config.js"></script>

        <link rel="stylesheet" href="css/sale-manager.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css">
    </head>

    <body>
        <!-- Layout wrapper -->
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <!-- Menu -->
                <aside id="layout-menu" class="layout-menu menu-vertical menu bg-menu-theme">
                    <div class="app-brand demo">
                        <a href="${pageContext.request.contextPath}/home-servlet" class="app-brand-link">
                            <span class="app-brand-logo demo">
                                <img src="webImage/other/logo/logo.png" style="width:128px; height:14px">
                            </span>
                        </a>

                        <a href="dashboardManager.jsp"
                           class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none">
                            <i class="bx bx-chevron-left bx-sm align-middle"></i>
                        </a>
                    </div>

                    <div class="menu-inner-shadow"></div>

                    <ul class="menu-inner py-1">
                        <!-- Account Settings -->
                        <li class="menu-item active">
                            <a href="saleManager" class="menu-link ">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Account Setting">Customer Orders</div>
                            </a>
                        </li>
                        <!-- User List -->
                        <li class="menu-item">
                            <a href="#" class="menu-link">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Orders & Returns">Sale Dashboard</div>
                            </a>
                        </li>

                        <li class="menu-item">
                            <a href="#" class="menu-link">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Change password">Account Settings</div>
                            </a>
                        </li>
                        <!-- Forms -->
                        <%--logout--%>
                        <li class="menu-item">
                            <a href="logout-servlet" class="menu-link ">
                                <i class='menu-icon tf-icons bx bx-user'></i>
                                <div data-i18n="Account Setting" style="color:red;">Log out</div>
                            </a>
                        </li>
                    </ul>
                </aside>
                <!-- / Menu -->

                <!-- Layout container -->
                <div class="layout-page">
                    <!-- Navbar -->
                    <nav
                        class="layout-navbar container-xxl navbar navbar-expand-xl navbar-detached align-items-center bg-navbar-theme"
                        id="layout-navbar"
                        >
                        <div class="layout-menu-toggle navbar-nav align-items-xl-center me-3 me-xl-0 d-xl-none">
                            <a class="nav-item nav-link px-0 me-xl-4" href="javascript:void(0)">
                                <i class="bx bx-menu bx-sm"></i>
                            </a>
                        </div>


                    </nav>
                    <!-- / Navbar -->
                    <!-- Content wrapper -->
                    <div class="content-wrapper">
                        <!-- Content -->
                        <div class="container-xxl flex-grow-1 container-p-y ">
                            <h4 class="fw-bold py-3 mb-4">Customer Orders Management</h4>
                            <div class="filter-search-container">
                                <div class="search-bar">
                                    <input type="text" id="search-input" placeholder="Search orders..." />
                                    <button id="search-button">Search</button>
                                </div>
                                <div class="filter-bar">
                                    <select id="status-filter">
                                        <option value="">Filter by status</option>
                                        <option value="Pending">Pending</option>
                                        <option value="Prepared Order">Prepared Order</option>
                                        <option value="Package Order">Package Order</option>
                                        <option value="Delivering">Delivering</option>
                                        <option value="Successfully">Successfully</option>
                                    </select>
                                    <button id="filter-button">Filter</button>
                                </div>
                            </div>
                            <c:if test="${not empty sessionScope.msg}">
                                <div class="alert alert-success alert-dismissible fade show" role="alert" style="text-align: center">
                                    ${sessionScope.msg}
                                    <button type="button" class="btn-danger" data-bs-dismiss="alert" aria-label="Close">x</button>
                                </div>
                                <%
                                    // Clear the notification after displaying it
                                    session.removeAttribute("msg");
                                %>
                            </c:if>

                            <!-- Error Notification -->
                            <c:if test="${not empty sessionScope.err}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert"  style="text-align: center">
                                    ${sessionScope.err}
                                    <button type="button" class="btn-danger" data-bs-dismiss="alert" >x</button>
                                </div>
                                <%
                                    // Clear the notification after displaying it
                                    session.removeAttribute("err");
                                %>
                            </c:if>
                            <div class="card">
                                <h5 class="card-header">Orders</h5>
                                <div class="table-responsive text-nowrap listtable">
                                    <table id="orders-table" class="table">
                                        <thead>
                                            <tr>
                                                <th>Order ID</th>
                                                <th>Total Price</th>
                                                <th>Status</th>
                                                <th>Recipient</th>
                                                <th>Phone's Number</th>
                                                <th>Sale Assignment</th>
                                                <th class="action-header">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody class="table-border-bottom-0">
                                            <c:forEach items="${requestScope.listOrder}" var="obj">
                                                <tr>
                                                    <td>${obj.getShop_orderID()}</td>
                                                    <td>${obj.getOrder_total()}</td>
                                                    <td>
                                                        <c:forEach items="${listOrderStatus}" var="ods"> 
                                                            <c:if test="${ods.id == obj.getOrder_status()}">
                                                                <c:choose>
                                                                    <c:when test="${obj.getOrder_status() == 1}">
                                                                        <span style="background: gray" class="badge text-bg-primary">${ods.name}</span>
                                                                    </c:when>
                                                                    <c:when test="${obj.getOrder_status() == 2}">
                                                                        <span style="background: blue" class="badge text-bg-primary">${ods.name}</span>
                                                                    </c:when>
                                                                    <c:when test="${obj.getOrder_status() == 3}">
                                                                        <span style="background: blue" class="badge text-bg-primary">${ods.name}</span>
                                                                    </c:when>
                                                                    <c:when test="${obj.getOrder_status() == 4}">
                                                                        <span style="background: blue" class="badge text-bg-primary">${ods.name}</span>
                                                                    </c:when>
                                                                    <c:when test="${obj.getOrder_status() == 5}">
                                                                        <span style="background: green" class="badge text-bg-primary">${ods.name}</span>
                                                                    </c:when>
                                                                </c:choose>

                                                            </c:if>
                                                        </c:forEach>
                                                    </td>
                                                    <td>${obj.getRecipient()}</td>
                                                    <td>${obj.getRecipent_phone()}</td>
                                                    <td>${obj.sale_name}</td>
                                                    <td>
                                                        <button class="confirm-btn" data-bs-toggle="modal" data-bs-target="#edit${obj.shop_orderID}">Assign</button>
                                                    </td>
                                                    <td>
                                                        <div class="dropdown">
                                                            <button type="button" class="btn p-0 dropdown-toggle hide-arrow" data-bs-toggle="dropdown">
                                                                <i class="bx bx-dots-vertical-rounded"></i>
                                                            </button>
                                                            <div class="dropdown-menu">
                                                                <a class="dropdown-item" href="${pageContext.request.contextPath}/OrderDetail?OrderID=${obj.shop_orderID}&mod=1">Detail</a>
                                                            </div>
                                                        </div>
                                                    </td>
                                                </tr>
                                            <div class="modal fade" id="edit${obj.shop_orderID}" tabindex="-1" role="dialog" aria-labelledby="addModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="addModalLabel">Assign Order</h5>
                                                            <button class="close" type="button" data-bs-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">×</span>
                                                            </button>
                                                        </div>
                                                        <form id="form${obj.shop_orderID}" action="saleManager" method="POST">
                                                            <div class="modal-body">
                                                                <label class="form-text">Choose Seller</label>
                                                                <div class="form-control">
                                                                    <select class="form-check" name="userId">
                                                                        <option class="form-select" value="none">Select option:</option>
                                                                        <c:forEach items="${sales}" var="o">
                                                                            <option class="form-select" value="${o.getUserID()}" <c:if test="${o.getUserID() == obj.sale_id}">selected</c:if>>${o.userName}</option>
                                                                        </c:forEach>
                                                                    </select>
                                                                </div>
                                                                <div class="statusError" style="color: red; display: none;">Please select a Seller.</div>
                                                            </div>
                                                            <div class="modal-footer">
                                                                <input type="hidden" name="id" value="${obj.shop_orderID}">
                                                                <button type="button" class="btn btn-secondary btn-sm" data-bs-dismiss="modal">Cancel</button>
                                                                <button type="submit" class="btn btn-success btn-sm">Save</button>
                                                            </div>
                                                        </form>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                        <!-- / Content -->


                        <div class="content-backdrop fade"></div>
                    </div>
                    <!-- Content wrapper -->
                </div>
                <!-- / Layout page -->
            </div>

            <!-- Overlay -->
            <div class="layout-overlay layout-menu-toggle"></div>
        </div>
        <!-- / Layout wrapper -->

        <!-- Core JS -->
        <!-- build:js assets/vendor/js/core.js -->
        <script src="bootstrap/assets/vendor/libs/jquery/jquery.js"></script>
        <script src="bootstrap/assets/vendor/libs/popper/popper.js"></script>
        <script src="bootstrap/assets/vendor/js/bootstrap.js"></script>
        <script src="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.js"></script>

        <script src="bootstrap/assets/vendor/js/menu.js"></script>
        <!-- endbuild -->

        <!-- Vendors JS -->

        <!-- Main JS -->
        <script src="bootstrap/assets/js/main.js"></script>

        <!-- Page JS -->

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
        <script src="css/sale-manager.js"></script>

    </body>
</html>
