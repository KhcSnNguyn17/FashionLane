<%-- 
    Document   : dashboardSaler
    Created on : Jun 24, 2024, 3:59:18 AM
    Author     : HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
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
                            <a href="saler" class="menu-link ">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Account Setting">Customer Orders</div>
                            </a>
                        </li>
                        <!-- User List -->
                        <li class="menu-item">
                            <a href="sale-dashboard" class="menu-link">
                                <i class='menu-icon tf-icons bx bxs-package'></i>
                                <div data-i18n="Orders & Returns">Sale Dashboard</div>
                            </a>
                        </li>

                        <li class="menu-item">
                            <a href="user-account-detail-servlet" class="menu-link">
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
                            <c:if test="${not empty errorMessage}">
                                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                                    <strong>Error!</strong> ${errorMessage}
                                    <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
                                </div>
                            </c:if>
                            <div class="row">
                                <%--Welcome banner--%>
                                <div class="col-lg-8 mb-4 order-0">
                                    <div class="card">
                                        <div class="d-flex align-items-end row">
                                            <div class="col-sm-7">
                                                <div class="card-body">
                                                    <h5 class="card-title text-primary">Welcomeback 
                                                        🎉</h5>
                                                    <p class="mb-4">
                                                        We have reached <span class="fw-bold"></span> more sales today.
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
                                                    <h6 class="card-title text-nowrap mb-1">
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
                                                    <h6 class="card-title text-nowrap mb-1">
                                                    </h6>
                                                </div>
                                            </div>
                                        </div>
                                        <%--/Customer Amount--%>
                                    </div>
                                </div>
                                <!-- Total Revenue -->
                                <div class="col-12 col-lg-12 order-2 order-md-3 order-lg-2 mb-4">
                                    <div class="card">
                                        <div class="row row-bordered g-0">
                                            <div class="container">
                                                <h2>Daily Revenue Statistics</h2>
                                                <form action="sale-dashboard" method="get">
                                                    <div class="mb-3">
                                                        <label for="startDate" class="form-label">Start Date:</label>
                                                        <input value="${start}" type="date" id="startDate" name="startDate" class="form-control" required>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="endDate" class="form-label">End Date :</label>
                                                        <input value="${end}" type="date" id="endDate" name="endDate" class="form-control" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-primary">Submit</button>
                                                </form>

                                                <canvas id="revenueChart" width="400" height="200"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <script>
                                    const ctx = document.getElementById('revenueChart').getContext('2d');
                                    const labels = [];
                                    const data = [];

                                    <c:forEach var="sale" items="${dailySaleRevenues}">
                                    labels.push('${sale.orderDate}');
                                    data.push(${sale.totalRevenue});
                                    </c:forEach>

                                    const revenueChart = new Chart(ctx, {
                                        type: 'line',
                                        data: {
                                            labels: labels,
                                            datasets: [{
                                                    label: 'Total Revenue',
                                                    data: data,
                                                    borderColor: 'rgba(75, 192, 192, 1)',
                                                    backgroundColor: 'rgba(75, 192, 192, 0.2)',
                                                    borderWidth: 1
                                                }]
                                        },
                                        options: {
                                            scales: {
                                                y: {
                                                    beginAtZero: true
                                                }
                                            }
                                        }
                                    });
                                </script>
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


