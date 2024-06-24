<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
    <head>
        <title>Product List</title>
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
        <%--    icon--%>
        <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">
        <link rel="stylesheet" href="header/header1.css">
        <link rel="stylesheet" href="footer/footer.css">
        <link rel="stylesheet" href="footer/ad-container.css">
        <link rel="stylesheet" href="productpage/productlist/filter-container.css">
        <link rel="stylesheet" href="productpage/productlist/product-list-container1.css">
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <section class="Product_List_Container">
            <div class="Product_List_InnerContainer">
                <div class="Filter_Container">
                    <a class='bx bx-x' id="x_filter-icon"></a>
                    <div class="Category_Container">
                        <h1>Category</h1>
                        <c:forEach var="cate" items="${cateList}" varStatus="status">
                            <c:if test="${status.index < 8}">
                                <c:if test="${collectionID == null}">

                                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all"
                                       class="Category_Option">
                                        <span>${cate.getCategoryName()}</span>
                                    </a>
                                </c:if>
                                <c:if test="${collectionID != null}">
                                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all&collectionID=${collectionID}"
                                       class="Category_Option">
                                        <span>${cate.getCategoryName()}</span>
                                    </a>
                                </c:if>
                            </c:if>
                        </c:forEach>
                    </div>
                    <div class="Color_Container">
                        <h1>Color</h1>
                        <div class="Color">
                            <c:forEach items="${colorList}" var="color">
                                <c:if test="${collectionID == null}">
                                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${category.getCategoryID()}&color_ID=${color.getColor_ID()}">
                                        <div style="background-color: ${color.getColor_Name()}"></div>
                                        <span>${color.getColor_Name()}</span>
                                    </a>
                                </c:if>
                                <c:if test="${collectionID != null}">
                                    <a style="color: black" href="${pageContext.request.contextPath}/productList-servlet?categoryID=${category.getCategoryID()}&color_ID=${color.getColor_ID()}&collectionID=${collectionID}">
                                        <div style="background-color: ${color.getColor_Name()}"></div>
                                        <span>${color.getColor_Name()}</span>
                                    </a>
                                </c:if>
                            </c:forEach>
                        </div>
                    </div>
                </div>
                <div class="List_Container">
                    <div class="Product_List_Headline">
                        <h1>${category.getCategoryName()}</h1>
                    </div>

                    <a class="bx bx-menu" id="menu_Filter-icon">Filter</a>
                    <div class="Product_List">
                        <c:if test="${productList == null || productList.isEmpty()}">
                            <h2 style="font-family: 'Nunito Sans', sans-serif; margin-left: 12px">No products found</h2>
                        </c:if>
                        <c:if test="${productList != null || !productList.isEmpty()}">
                            <c:forEach items="${productList}" var="product">
                                <div class="Product">
                                    <img src="webImage/productImg/${product.getThumbnail()}">
                                    <div class="Product_Text">
                                        <div class="Product_Name">
                                            <a href="${pageContext.request.contextPath}/productDetail-servlet?ProductID=${product.getProductID()}&color_Name=${product.getColor_Name()}">${product.getProductName()}</a>

                                            <c:if test="${product.getDiscount() != 0}">
                                                <p style="text-decoration:line-through">${product.getPrice()}</p>
                                                <p>${product.getDiscount()}</p>
                                            </c:if>
                                            <c:if test="${product.getDiscount() == 0}">
                                                <p>${product.getPrice()}</p>
                                            </c:if>
                                        </div>
                                        <p>${product.getColor_Name()}</p>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script>
            //cart
            z1 = document.querySelector("#menu_Filter-icon");
            z2 = document.querySelector("#x_filter-icon");
            z3 = document.querySelector(".Filter_Container");
            z1.onclick = function () {
                z3.classList.toggle("openFilter");
            };
            z2.onclick = function () {
                z3.classList.toggle("openFilter");
            };

        </script>
    </body>
</html>
