<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Fashion Lane</title>
    <%--    icon--%>
    <link href="https://cdn.jsdelivr.net/npm/remixicon@3.2.0/fonts/remixicon.css" rel="stylesheet">
    <link rel="stylesheet" href="https://unpkg.com/boxicons@latest/css/boxicons.min.css">

    <%--    fonts--%>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Nunito+Sans:wght@200&display=swap" rel="stylesheet">

    <%--    css--%>
    <link rel="stylesheet" href="header/header1.css">
    <link href="header/pop-up/pop-up.css" rel="stylesheet">
    <link rel="stylesheet" href="homepage/season-collection1.css">
    <link rel="stylesheet" href="homepage/category.css">
    <link rel="stylesheet" href="homepage/best-seller-slider1.css">
    <link rel="stylesheet" href="homepage/sale-banner.css">
    <link rel="stylesheet" href="homepage/story-container1.css">
    <link rel="stylesheet" href="footer/footer.css">

    <!-- Favicon -->
    <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>

</head>
<body>
<jsp:include page="header.jsp"/>

<div class="wrapper">
    <button id="left" class='bx bx-chevron-left'></button>
    <div class="carousel">
        <c:forEach items="${listCollection}" var="collection">
            <div class="carousel-item">
                <section class="Season_Collection_Banner" id="Season_Collection_Banner">
                    <img src="webImage/collection/${collection.getCollectionImg()}" alt="">
                    <div class="Season_Collection_Banner_text">
                        <h1>${collection.getCollectionName()}</h1>
                        <p>${collection.getCollection_description()}</p>
                        <div class="carousel-button">
                            <button value="collectionID" name="collectionID">
                                <a href="${pageContext.request.contextPath}/productList-servlet?collectionID=${collection.getCollectionID()}&categoryID=1&color_ID=all">
                                    SHOP THE COLLECTION
                                </a>
                            </button>
                        </div>
                    </div>
                </section>
            </div>
        </c:forEach>
    </div>
    <button id="right" class='bx bx-chevron-right'></button>
</div>

<!---------------------------------->

<section class="Category_Container">
    <h1>Shop by Category</h1>
    <div class="Category_List_Container">
        <c:forEach var="cate" items="${cateList}" varStatus="status">
            <c:if test="${status.index < 8}">
                <div class="Category">
                    <img src="webImage/category/${cate.getCategoryImg()}" alt="">
                    <div class="Category_text">
                        <button value="cateID"><a
                                href="${pageContext.request.contextPath}/productList-servlet?categoryID=${cate.getCategoryID()}&color_ID=all"> ${cate.getCategoryName()}</a>
                        </button>
                    </div>
                </div>
            </c:if>
        </c:forEach>
    </div>
</section>

<section class="Category_Container">
    <div class="Category_List_Container">
        <div class="Category2">
            <img src="webImage/category/newarrival.jpg">
            <div class="Category_text">
                <h2>NEW ARRIVALS</h2>
                <button>
                    <a href="#">Shop Now</a>
                </button>
            </div>
        </div>
        <div class="Category2">
            <img src="webImage/category/bestseller.jpg">
            <div class="Category_text">
                <h2>BEST SELLER</h2>
                <button>
                    <a href="#">Shop
                        Now</a>
                </button>
            </div>
        </div>
    </div>
</section>

<section class="Stories_Container" style="margin-top: 10%">
    <c:forEach var="story" items="${storyList}" varStatus="a">
        <c:if test="${a.index < 2}">
            <a style="color: black" href="StoryCusServlet?input=${story.getStory_ID()}" class="Story">
                <h1>${story.getTitle()}</h1>
                <button>Learn More</button>
                <div class="Storyimg">
                    <img src="webImage/story/${story.getThumbnail()}">
                </div>
            </a>
        </c:if>
    </c:forEach>
</section>

<jsp:include page="footer.jsp"/>
<script>
    const carousel = document.querySelector(".carousel"),
        firstImg = carousel.querySelectorAll(".carousel-item")[0],
        arrowIcons = document.querySelectorAll(".wrapper button");

    arrowIcons.forEach(icon => {
        icon.addEventListener("click", () => {
            let firstImgWidth = firstImg.clientWidth; // getting first img width
            // if clicked icon is left, reduce width value from the carousel scroll left else add to it
            carousel.scrollLeft += icon.id === "left" ? -firstImgWidth : firstImgWidth;
        });
    });

    // pop up
    xSale = document.querySelector("#pop_up_x");
    ySale = document.querySelector(".pop_up_container");
    xSale.onclick = function () {
        ySale.style.display = "none";
    }
</script>
</body>
</html>
