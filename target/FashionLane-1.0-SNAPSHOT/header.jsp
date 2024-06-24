<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Title</title>
        <link rel="icon" type="image/x-icon"
              href="https://asset.brandfetch.io/idg0XRU3ny/iduCRhJlyM.jpeg?updated=1667682228156">

        <%--    icon--%>
        <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <%--    font--%>
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200&display=swap" rel="stylesheet">

        <%--    css--%>
        <link rel="stylesheet" href="header/header1.css"/>
        <link rel="stylesheet" href="header/cart/cart2.css">
        <link rel="stylesheet" href="header/search/search1.css">

        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>

    </head>
    <body>
        <c:set var="customerLogin" value="${sessionScope.acc}" />
        <header>
            <%-- web--%>
            <a class="bx bx-menu" id="menu-icon"></a>
            <ul class="navbar">
                <li><a href="#">Women</a></li>
                <li><a href="#">About</a></li>
                <li><a href="${pageContext.request.contextPath}/StoryCusServlet?input=all">Stories</a></li>
            </ul>
            <a href="${pageContext.request.contextPath}/home-servlet" class="logo"><img
                    src="${pageContext.request.contextPath}/webImage/other/logo/logo.png"></a>

            <div class="main">
                <a href="#" class="search" id="search-icon"><i class="ri-search-line"></i></a>
                    <c:if test="${sessionScope.acc != null}">
                        <c:if test="${sessionScope.acc.getRole() == 4}">
                        <a href="${pageContext.request.contextPath}/user-account-detail-servlet" class="user">
                            <i class="ri-user-3-line"></i>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.acc.getRole() == 5}">
                        <a href="${pageContext.request.contextPath}/saleManager" class="user">
                            <i class="ri-user-3-line"></i>
                        </a>
                    </c:if>
                    <c:if test="${sessionScope.acc.getRole() == 2}">
                        <a href="${pageContext.request.contextPath}/saler" class="user">
                            <i class="ri-user-3-line"></i>
                        </a>
                    </c:if>
                </c:if>
                <c:if test="${sessionScope.acc == null}">
                    <a href="${pageContext.request.contextPath}/login-servlet" class="user"><i class="ri-user-3-line"></i></a>
                    </c:if>
                    <%--            <a href="login.jsp" class="user"><i class="ri-user-3-line"></i></a>--%>
                    <c:if test="${customerLogin != null && customerLogin.role == 4}">
                    <a class="cart" id="cart-icon"><i class="ri-shopping-cart-2-line"></i></a>
                    </c:if>
            </div>
            <c:if test="${customerLogin != null && customerLogin.role == 4}">
                <div class="cart_container">
                    <a class='bx bx-x' id="x-icon"></a>
                    <div class="cart_list">
                        <h1>Your cart</h1>

                        <c:set var="totalValue" value="0"/>
                        <c:set var="totalCartItem" value="0"/>

                        <c:forEach items="${sessionScope.cartItemList}" var="item">
                            <div class="cart_item">
                                <a class="cart_item_img">
                                    <img src="${pageContext.request.contextPath}/webImage/productImg/${item.getThumbnail()}" alt="">
                                </a>
                                <div class="cart_item_text">
                                    <div class="cart_item_name">
                                        <p>${item.getProductName()}</p>
                                        <a href="DeleteFromCart?ProductID=${item.getProductID()}&variationID=${item.getVariationID()}"><i
                                                class='bx bx-trash' style="color:black;"></i></a>
                                    </div>
                                    <p>${item.getSize_Name()} | ${item.getColor_Name()}</p>

                                    <!-- Iterate through cart item details to calculate total item count -->
                                    <c:forEach items="${sessionScope.cartItemList2}" var="o">
                                        <c:if test="${o.getProductID() == item.getProductID() && o.getVariationID() == item.getVariationID()}">
                                            <div class="cart_item_price">
                                                <c:if test="${item.getDiscount() != 0}">
                                                    <p>₫${item.getPrice() * o.getQuantity()}</p>
                                                    <p>₫${item.getDiscount() * o.getQuantity()}</p>
                                                </c:if>
                                                <c:if test="${item.getDiscount() == 0}">
                                                    <p>₫${item.getPrice() * o.getQuantity()}</p>
                                                </c:if>
                                                <!-- Increment total item count -->
                                                <c:set var="totalCartItem" value="${totalCartItem + o.getQuantity()}"/>
                                                <!-- Increment total value -->
                                                <c:set var="totalValue" value="${totalValue + (item.getDiscount() != 0 ? item.getDiscount() * o.getQuantity() : item.getPrice() * o.getQuantity())}"/>
                                                <form action="${pageContext.request.contextPath}/adjustQuantity" method="post">
                                                    <div class="Cart_Item_Amount_Change">
                                                        <button class='bx bx-minus' name="choice" value="minus"></button>
                                                        <p id="amount">${ o.getQuantity() }</p>
                                                        <button class='bx bx-plus' name="choice" value="plus"></button>
                                                    </div>
                                                    <input type="hidden" name="ProductID" value="${ o.getProductID() }">
                                                    <input type="hidden" name="VariationID" value="${ o.getVariationID() }">
                                                    <input type="submit" style="display: none;">
                                                </form>
                                            </div>
                                        </c:if>
                                    </c:forEach>
                                </div>
                            </div>
                        </c:forEach>
                        <a  href="${pageContext.request.contextPath}/productList-servlet?categoryID=2&color_ID=all" style="font-family:'Nunito Sans';font-weight: bold;color: black">Choose More Product</a>
                    </div>

                    <div class="checkout_container">
                        <div class="subtotal_container">

                            <p>Subtotal (${totalCartItem} items)</p>
                            <p>₫${totalValue}</p>
                        </div>
                        <a class="buttonCheckout" href="${pageContext.request.contextPath}/Checkout?input=3">Continue To Checkout</a>
                    </div>
                </div>
            </c:if>
            <div class="menu_container">
                <div class="menu_container1">
                    <a href="#">Best-Sellers</a>
                    <a href="#">Everworld Stories</a>
                </div>
                <div class="menu_container2">
                    <a href="">What's New</a>
                    <a href="">Apparel</a>
                    <a href="">Denim</a>
                    <a href="">Shoes,Bags & Accessories</a>
                    <a href="">Sales</a>
                    <a href="">About</a>
                </div>
                <div class="menu_container3">
                    <a href="">Account</a>
                    <a href="">Log Out</a>
                </div>
            </div>
        </header>

        <div class="navbar2_container">
            <ul class="navbar2">
                <li><a href="">What's New</a></li>
                <li><a href="">Best Sellers</a></li>
                <li><a href="">Apparel</a></li>
                <li><a href="">Denim</a></li>
                <li><a href="">Shoes, Bags & Accessories</a></li>
                <li><a href="" style="color: red">Sale</a></li>
            </ul>
        </div>

        <div class="search_container">
            <form action="${pageContext.request.contextPath}/SearchServlet" method="post" class="search_inner">
                <input type="text" id="site-search" name="productName" placeholder="Search..." list="productL">
                <datalist id="productL">

                </datalist>
                <button class='bx bx-search-alt'></button>
            </form>
        </div>
        <script>
            //menu
            x = document.querySelector("#menu-icon");
            y = document.querySelector(".menu_container");
            x.onclick = function () {
                x.classList.toggle("bx-x");
                y.classList.toggle("open");
            }

            //cart
            x1 = document.querySelector("#cart-icon");
            x2 = document.querySelector("#x-icon")
            y1 = document.querySelector(".cart_container");
            x1.onclick = function () {
                y1.classList.toggle("openCart");
            }
            x2.onclick = function () {
                y1.classList.toggle("openCart");
            }


            function showCart() {
                y1.classList.toggle("openCart");
            }

            //search
            xSearch = document.querySelector("#search-icon");
            ySearch = document.querySelector(".search_container");
            xSearch.onclick = function () {
                ySearch.classList.toggle("openSearch");
            }


        </script>

    </body>
</html>
