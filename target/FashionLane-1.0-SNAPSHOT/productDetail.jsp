<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
    <head>
        <title><c:out value="${pi.productName}"/></title>
        <%--    icon--%>
        <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

        <link rel="stylesheet" href="header/header1.css">
        <link rel="stylesheet" href="productpage/productdetail/product-detail1.css">
        <link rel="stylesheet" href="homepage/best-seller-slider1.css">
        <link rel="stylesheet" href="footer/ad-container.css">
        <link rel="stylesheet" href="footer/footer.css">
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
    </head>
    <body onpageshow="showCart()">
        <jsp:include page="header.jsp"/>

        <section class="Product_Detail_Container">
            <div class="Product_Image_Container">
                <img src="webImage/productImg/${pi.thumbnail}">
                <img src="webImage/productImg/${pi.product_img_1}">
                <img src="webImage/productImg/${pi.product_img_2}">
                <img src="webImage/productImg/${pi.product_img_3}">
            </div>
            <div class="Product_Detail">
                <div class="Product_Name">
                    <div class="Name_And_Price">
                        <h1>${pi.productName}</h1>
                        <div class="Price">
                            <c:choose>
                                <c:when test="${pi.discount != 0}">
                                    <p>${pi.price}</p>
                                    <p>${pi.discount}</p>
                                </c:when>
                                <c:otherwise>
                                    <p style="text-decoration: none">${pi.price}</p>
                                </c:otherwise>
                            </c:choose>
                        </div>
                    </div>
                </div>
                <form action="addToCart" method="POST">
                    <div class="Product_Color">
                        <h1>Color</h1>
                        <div class="Color">
                            <c:forEach var="color" items="${colors}">
                                <a style="color: black" href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=${pi.productID}&color_Name=${color.color_Name}">
                                    <div style="background-color: ${color.color_Name}"></div>
                                    <span style="color: black">${color.color_Name}</span>
                                </a>
                            </c:forEach>
                        </div>
                    </div>

                    <div class="Product_Size">
                        <h1>Size</h1>
                        <div class="Size">
                            <input type="hidden" name="ProductID" value="${pi.productID}">
                            <input type="hidden" name="color_Name" value="${pi.color_Name}">
                            <div class="radiocheck_container">
                                <c:forEach var="size" items="${sizes}">
                                    <div class="radiocheck" for="size_name">
                                        <input type="radio" id="size_name" name="size_name" value="${size.size_Name}" required>
                                        <p id="radio_name">${size.size_Name}</p>
                                    </div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>
                    <h1 style="color:red; font-family: 'Nunito Sans', sans-serif; font-size: 14px">${CartMessError}</h1>
                    <h1 style="color:green;font-family: 'Nunito Sans', sans-serif; font-size: 14px">${ShippMess}</h1>
                    <c:set var="customerLoginPage" value="${sessionScope.acc}" />
                    <c:if test="${customerLoginPage != null && customerLoginPage.role == 4}">
                        <button color="white" type="submit" onclick="showCart()">Add To Cart</button>
                    </c:if>
                </form>

                <div class="Product_Description">
                    <h1>Description</h1>
                    <p>Made of 100% cotton poplin with a subtle sheen, the Smock Dress has a comfortable, breathable feel with an effortlessly polished look.</p>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
