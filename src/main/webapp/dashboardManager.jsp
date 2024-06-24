<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="model.*" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="entity.BestSeller" %>
<%@ page import="entity.User" %>
<%@ page import="entity.Product" %>
<html
    class="light-style layout-menu-fixed"
    data-assets-path="../bootstrap/assets/"
    data-template="vertical-menu-template-free"
    data-theme="theme-default"
    dir="ltr"
    lang="en"
    >
    <head>
        <meta charset="utf-8"/>
        <meta
            content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"
            name="viewport"
            />

        <title>Dashboard</title>

        <meta content="" name="description"/>

        <!-- Favicon -->
        <link href="webImage/other/icon/favicon.png" rel="icon" type="image/x-icon"/>

        <!-- Fonts -->
        <link href="https://fonts.googleapis.com" rel="preconnect"/>
        <link crossorigin href="https://fonts.gstatic.com" rel="preconnect"/>
        <link
            href="https://fonts.googleapis.com/css2?family=Public+Sans:ital,wght@0,300;0,400;0,500;0,600;0,700;1,300;1,400;1,500;1,600;1,700&display=swap"
            rel="stylesheet"
            />

        <!-- Icons. Uncomment required icon fonts -->
        <link href="bootstrap/assets/vendor/fonts/boxicons.css" rel="stylesheet"/>

        <!-- Core CSS -->
        <link class="template-customizer-core-css" href="bootstrap/assets/vendor/css/core.css" rel="stylesheet"/>
        <link class="template-customizer-theme-css" href="bootstrap/assets/vendor/css/theme-default.css" rel="stylesheet"/>
        <link href="bootstrap/assets/css/demo.css" rel="stylesheet"/>

        <!-- Vendors CSS -->
        <link href="bootstrap/assets/vendor/libs/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet"/>

        <link href="bootstrap/assets/vendor/libs/apex-charts/apex-charts.css" rel="stylesheet"/>

        <!-- Page CSS -->
        <script src="https://www.gstatic.com/charts/loader.js"></script>

        <!-- Helpers -->
        <script src="bootstrap/assets/vendor/js/helpers.js"></script>

        <!--! Template customizer & Theme config files MUST be included after core stylesheets and helpers.js in the <head> section -->
        <!--? Config:  Mandatory theme config file contain global vars & default theme options, Set your preferred theme option in this file.  -->
        <script src="bootstrap/assets/js/config.js"></script>
    </head>
    <%
        User user = (User) session.getAttribute("accHU");

        OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
        long saleTotal = orderDetailDAO.SaleTotal();
        String[] dashboardDate = orderDetailDAO.getDashboarDate();
        String[] dashboardOrTotal = orderDetailDAO.getDashboarOrTotal();

        UserDAO userDAO = new UserDAO();
        long cusTotal = userDAO.CustomerTotal("all");
        long male = userDAO.CustomerTotal("male");
        long female = userDAO.CustomerTotal("female");
        long other = userDAO.CustomerTotal("other");

        ProductForEditDAO productForEditDAO = new ProductForEditDAO();
        long proTotal = productForEditDAO.PTotal();

        ProductDAO productDAO = new ProductDAO();
        List<Product> inventory = productDAO.getAllProducts("inventory", 1);

        BestSellerDAO bestseller = new BestSellerDAO();
        List<BestSeller> bestSellers = bestseller.getBestSeller();
        List<Product> productList = new ArrayList<>();
        for (BestSeller bestSeller: bestSellers){
            Product product = productDAO.getProductByProIDColName(String.valueOf(bestSeller.getProductID()), bestSeller.getColorName());
            productList.add(product);
        }

    %>
    <body>
        <!-- Layout wrapper -->
        <div class="layout-wrapper layout-content-navbar">
            <div class="layout-container">
                <!-- Menu -->
                <aside class="layout-menu menu-vertical menu bg-menu-theme" id="layout-menu">
                    <div class="app-brand demo">
                        <a class="app-brand-link" href="dashboardManager.jsp">
                            <span class="app-brand-logo demo">
                                <img src="webImage/other/logo/logo.png" style="width:128px; height:14px">
                            </span>
                        </a>

                        <a class="layout-menu-toggle menu-link text-large ms-auto d-block d-xl-none"
                           href="dashboardManager.jsp">
                            <i class="bx bx-chevron-left bx-sm align-middle"></i>
                        </a>
                    </div>

                    <div class="menu-inner-shadow"></div>

                    <ul class="menu-inner py-1">
                        <!-- Dashboard -->
                        <li class="menu-item active">
                            <a class="menu-link" href="dashboardManager.jsp">
                                <i class="menu-icon tf-icons bx bx-home-circle"></i>
                                <div data-i18n="Analytics">Dashboard</div>
                            </a>
                        </li>

                        <!-- Layouts -->
                        <!-- Pages -->
                        <li class="menu-header small text-uppercase"><span class="menu-header-text">Pages</span></li>
                        <!-- Product List -->
                        <%if (user.getRole() == 1) {%>
                        <%}%>
                        <%if (user.getRole() == 1 || user.getRole() == 2) {%>
                        <li class="menu-item">
                            <a class="menu-link"
                               href="${pageContext.request.contextPath}/ProductListManagerServlet?input=all">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Product List">Product List</div>
                            </a>
                        </li>
                        <!-- User List -->
                        <li class="menu-item">
                            <a class="menu-link" href="${pageContext.request.contextPath}/UserListManagerServlet?role=4">
                                <i class='menu-icon tf-icons bx bx-user'></i>
                                <div data-i18n="User List">Customer List</div>
                            </a>
                        </li>
                        <li class="menu-item">
                            <a class="menu-link menu-toggle" href="javascript:void(0);">
                                <i class="menu-icon tf-icons bx bx-detail"></i>
                                <div data-i18n="Sale">Sale</div>
                            </a>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a class="menu-link" href="PromotionServlet?input=all">
                                        <div data-i18n="Promotion List">Promotion List</div>
                                    </a>
                                </li>
                            </ul>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a class="menu-link" href="${pageContext.request.contextPath}/CollectionUpdatePromotion">
                                        <div data-i18n="Promotion List">Season Collection (Update Promotion)</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <%}%>
                        <%if (user.getRole() == 1 || user.getRole() == 3) {%>
                        <li class="menu-item">
                            <a class="menu-link menu-toggle" href="javascript:void(0);">
                                <i class="menu-icon tf-icons bx bx-detail"></i>
                                <div data-i18n="Marketing">Marketing</div>
                            </a>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a class="menu-link" href="${pageContext.request.contextPath}/seasonCollectionEditServlet">
                                        <div data-i18n="Season Collection">Season Collection (Home Page)</div>
                                    </a>
                                </li>
                            </ul>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a class="menu-link" href="${pageContext.request.contextPath}/categoryEditServlet">
                                        <div data-i18n="Season Collection">Category (Home Page)</div>
                                    </a>
                                </li>
                            </ul>
                            <ul class="menu-sub">
                                <li class="menu-item">
                                    <a class="menu-link" href="${pageContext.request.contextPath}/StoryServlet?input=all">
                                        <div data-i18n="Story List">Story List (Story Page)</div>
                                    </a>
                                </li>
                            </ul>
                        </li>
                        <%}%>
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

                        <div class="navbar-nav-right d-flex align-items-center" id="navbar-collapse">
                            <ul class="navbar-nav flex-row align-items-center ms-auto">
                                <!-- User -->
                                <li class="nav-item navbar-dropdown dropdown-user dropdown">
                                    <a class="nav-link dropdown-toggle hide-arrow" data-bs-toggle="dropdown"
                                       href="javascript:void(0);">
                                        <div class="avatar avatar-online">
                                            <img alt class="w-px-40 h-auto rounded-circle"
                                                 src="webImage/other/icon/ava.png"/>
                                        </div>
                                    </a>
                                    <ul class="dropdown-menu dropdown-menu-end">
                                        <li>
                                            <a class="dropdown-item" href="#">
                                                <div class="d-flex">
                                                    <div class="flex-shrink-0 me-3">
                                                        <div class="avatar avatar-online">
                                                            <img alt class="w-px-40 h-auto rounded-circle"
                                                                 src="webImage/other/icon/ava.png"/>
                                                        </div>
                                                    </div>
                                                    <div class="flex-grow-1">
                                                        <span class="fw-semibold d-block"><%=user.getFirstName()%> <%=user.getLastName()%></span>
                                                        <%if (user.getRole() == 1) {%>
                                                        <small class="text-muted">Admin
                                                        </small>
                                                        <%} else if (user.getRole() == 2) {%>
                                                        <small class="text-muted">Sale
                                                        </small>
                                                        <%} else if (user.getRole() == 3) {%>
                                                        <small class="text-muted">Marketing
                                                        </small>
                                                        <%} else {%>
                                                        <small class="text-muted">Customer
                                                        </small>
                                                        <%}%>
                                                    </div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="dropdown-divider"></div>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="HighUserAccountDetailServlet">
                                                <i class="bx bx-user me-2"></i>
                                                <span class="align-middle">My Profile</span>
                                            </a>
                                        </li>
                                        <li>
                                            <div class="dropdown-divider"></div>
                                        </li>
                                        <li>
                                            <a class="dropdown-item" href="logout-servlet">
                                                <i class="bx bx-power-off me-2"></i>
                                                <span class="align-middle">Log Out</span>
                                            </a>
                                        </li>
                                    </ul>
                                </li>
                                <!--/ User -->
                            </ul>
                        </div>
                    </nav>
                    <!-- / Navbar -->

                    <!-- Content wrapper -->
                    <div class="content-wrapper">
                        <!-- Content -->

                        <div class="container-xxl flex-grow-1 container-p-y">
                            <div class="row">
                                <%--Welcome banner--%>
                                <div class="col-lg-8 mb-4 order-0">
                                    <div class="card">
                                        <div class="d-flex align-items-end row">
                                            <div class="col-sm-7">
                                                <div class="card-body">
                                                    <h5 class="card-title text-primary">Welcomeback <%=user.getUserName()%>!
                                                        🎉</h5>
                                                    <p class="mb-4">
                                                        We have reached <span class="fw-bold"><%=saleTotal%></span> more sales today.
                                                    </p>
                                                </div>
                                            </div>
                                            <div class="col-sm-5 text-center text-sm-left">
                                                <div class="card-body pb-0 px-0 px-md-4">
                                                    <img
                                                        alt="View Badge User"
                                                        data-app-dark-img="illustrations/man-with-laptop-dark.png"
                                                        data-app-light-img="illustrations/man-with-laptop-light.png"
                                                        height="140"
                                                        src="webImage/other/icon/man-with-laptop-light.png"
                                                        />
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--/Welcome banner--%>

                                <div class="col-lg-4 col-md-4 order-1">
                                    <div class="row">
                                        <%--Sale amount--%>
                                        <div class="col-lg-6 col-md-12 col-6 mb-4">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="card-title d-flex align-items-start justify-content-between">
                                                        <div class="avatar flex-shrink-0">
                                                            <img
                                                                alt="Credit Card"
                                                                class="rounded"
                                                                src="webImage/other/icon/dollar.png"
                                                                />
                                                        </div>
                                                    </div>
                                                    <span>Sales</span>
                                                    <h6 class="card-title text-nowrap mb-1"><%=saleTotal%>
                                                    </h6>
                                                </div>
                                            </div>
                                        </div>
                                        <%--/Sale amount--%>

                                        <%--Customer Amount--%>
                                        <div class="col-lg-6 col-md-12 col-6 mb-4">
                                            <div class="card">
                                                <div class="card-body">
                                                    <div class="card-title d-flex align-items-start justify-content-between">
                                                        <div class="avatar flex-shrink-0">
                                                            <img
                                                                alt="User Amount"
                                                                class="rounded"
                                                                src="webImage/other/icon/ava.png"
                                                                />
                                                        </div>
                                                    </div>
                                                    <span>Customer Amount</span>
                                                    <h6 class="card-title text-nowrap mb-1"><%=cusTotal%>
                                                    </h6>
                                                </div>
                                            </div>
                                        </div>
                                        <%--/Customer Amount--%>
                                    </div>
                                </div>
                                <!-- Total Revenue -->
                                <div class="col-12 col-lg-8 order-2 order-md-3 order-lg-2 mb-4">
                                    <div class="card">
                                        <div class="row row-bordered g-0">
                                            <div class="col-md-12">
                                                <h5 class="card-header m-0 me-2 pb-3">Total Revenue</h5>
                                                <div class="col-md-12" id="TotalRevenue"
                                                     style="max-width:px; height:400px"></div>
                                                <script type="text/javascript">
                                                    google.charts.load('current', {'packages': ['corechart']});
                                                    google.charts.setOnLoadCallback(drawChart);

                                                    function drawChart() {
                                                        var data = google.visualization.arrayToDataTable([
                                                            ['Date', 'Sale'],
                                                            ['<%=dashboardDate[0]%>', <%=dashboardOrTotal[1]%>],
                                                            ['<%=dashboardDate[1]%>', <%=dashboardOrTotal[0]%>]                                            
                                                        ]);

                                                        var options = {
                                                            title: 'Company Performance',
                                                            curveType: 'function',
                                                            legend: {position: 'bottom'}
                                                        };

                                                        var chart = new google.visualization.LineChart(document.getElementById('TotalRevenue'));

                                                        chart.draw(data, options);
                                                    }
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!--/ Total Revenue -->
                                <div class="col-12 col-lg-4 order-2 order-md-3 order-lg-2 mb-4">
                                    <div class="row">
                                        <div class="col-12 mb-5">
                                            <div class="card">
                                                <h5 class="card-header m-0 me-2 pb-3">Gender</h5>
                                                <div class="col-12" id="Gender"
                                                     style="max-width:700px; height:400px"></div>
                                                <script>
                                                    google.charts.load('current', {'packages': ['corechart']});
                                                    google.charts.setOnLoadCallback(drawChart);

                                                    function drawChart() {

                                                        const data = google.visualization.arrayToDataTable([
                                                            ['Gender', 'Mhl'],
                                                            ['Male', <%=male*100/cusTotal%>],
                                                            ['Female', <%=female*100/cusTotal%>],
                                                            ['Other', <%=other*100/cusTotal%>]
                                                        ]);

                                                        const options = {};

                                                        const chart = new google.visualization.PieChart(document.getElementById('Gender'));
                                                        chart.draw(data, options);
                                                    }
                                                </script>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <!-- Transactions -->
                                <div class="col-md-6 col-lg-4 order-2 mb-4">
                                    <div class="card h-100">
                                        <div class="card-header d-flex align-items-center justify-content-between">
                                            <h5 class="card-title m-0 me-2">Best Seller</h5>
                                        </div>
                                        <div class="card-body">
                                            <ul class="p-0 m-0" style="height: 600px; overflow-y: scroll">
                                                <%for (Product i : productList) {%>
                                                <li class="d-flex mb-4 pb-1">
                                                    <div class="avatar flex-shrink-0 me-3">
                                                        <img alt="User" class="rounded"
                                                             src="webImage/productImg/<%=i.getThumbnail()%>"/>
                                                    </div>
                                                    <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                                                        <div class="me-2">
                                                            <h6 class="mb-0"><%=i.getProductName()%>
                                                            </h6>
                                                            <small class="text-muted d-block mb-1"><%=i.getColor_Name()%>
                                                                | <%=i.getSize_Name()%> | <%=i.getPrice()%>
                                                            </small>
                                                        </div>
                                                    </div>
                                                </li>
                                                <%}%>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!--/ Transactions -->
                                <!-- Transactions -->
                                <div class="col-md-6 col-lg-4 order-2 mb-4">
                                    <div class="card h-100">
                                        <div class="card-header d-flex align-items-center justify-content-between">
                                            <h5 class="card-title m-0 me-2">Inventory</h5>
                                        </div>
                                        <div class="card-body">
                                            <ul class="p-0 m-0" style="height: 600px; overflow-y: scroll">
                                                <%for (Product i : inventory) {%>
                                                <li class="d-flex mb-4 pb-1">
                                                    <div class="avatar flex-shrink-0 me-3">
                                                        <img alt="User" class="rounded"
                                                             src="webImage/productImg/<%=i.getThumbnail()%>"/>
                                                    </div>
                                                    <div class="d-flex w-100 flex-wrap align-items-center justify-content-between gap-2">
                                                        <div class="me-2">
                                                            <h6 class="mb-0"><%=i.getProductName()%>
                                                            </h6>
                                                            <small class="text-muted d-block mb-1"><%=i.getColor_Name()%>
                                                                | <%=i.getSize_Name()%> | <%=i.getQty_in_stock()%>
                                                                | <%=i.getPrice()%>
                                                            </small>
                                                        </div>
                                                    </div>
                                                </li>
                                                <%}%>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                                <!--/ Transactions -->

                                <%--Number of product--%>
                                <div class="col-md-6 col-lg-4 order-2 mb-4">
                                    <div class="card">
                                        <div class="card-body">
                                            <div class="d-flex justify-content-between flex-sm-row flex-column gap-3">
                                                <div class="d-flex flex-sm-column flex-row align-items-start justify-content-between">
                                                    <div class="card-title">
                                                        <h5 class="text-nowrap mb-2"></h5>
                                                        <span class="badge bg-label-warning rounded-pill">Number of items</span>
                                                    </div>
                                                    <div class="mt-sm-auto">
                                                        <h6 class="mb-0"><%=proTotal%>
                                                        </h6>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <%--/Number of product--%>
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
        <script src="bootstrap/assets/vendor/libs/apex-charts/apexcharts.js"></script>

        <!-- Main JS -->
        <script src="bootstrap/assets/js/main.js"></script>

        <!-- Page JS -->
        <script src="bootstrap/assets/js/dashboards-analytics.js"></script>

        <!-- Place this tag in your head or just before your close body tag. -->
        <script async defer src="https://buttons.github.io/buttons.js"></script>
    </body>
</html>

