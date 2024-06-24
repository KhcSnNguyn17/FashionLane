<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Register</title>
        <link rel="stylesheet" href="header/header1.css">
        <link href="header/pop-up/pop-up.css" rel="stylesheet">
        <link href="header/pop-up/policy.css" rel="stylesheet">
        <link rel="stylesheet" href="account/authentication/authentication1.css">
        <link rel="stylesheet" href="footer/ad-container.css">
        <link rel="stylesheet" href="footer/footer.css">
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
    </head>
    <body>
        <%--Header--%>
        <jsp:include page="header.jsp"/>
        <div class="policy_pop_up_container">
            <div class="pop_up" style="background: gray">
                <i class='bx bx-x' id="pop_up_x"></i>
                <h2>
                    Privacy Policy and Terms of Service
                </h2>
                <p>
                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis bibendum, metus id ullamcorper auctor, leo
                    lacus consequat sapien, nec tincidunt lacus nunc laoreet metus. In scelerisque turpis sed aliquam
                    vestibulum. Nullam aliquet nunc turpis, ut pretium magna vehicula ut. Class aptent taciti sociosqu ad litora
                    torquent per conubia nostra, per inceptos himenaeos. Phasellus eu eros id lorem facilisis scelerisque.
                    Praesent quis nisi vestibulum, lobortis leo a, porta justo. Proin dolor justo, mattis sed lectus eget,
                    commodo commodo nunc. Suspendisse eget elit augue. <br><br>
                    Praesent nisl risus, aliquam eget scelerisque id, iaculis at tellus. Fusce iaculis vestibulum faucibus.
                    Quisque porttitor quam semper, maximus risus ac, mollis arcu. Donec blandit, diam eget euismod cursus, felis
                    urna pretium mauris, sit amet pellentesque nibh nisi a est. Morbi luctus leo ut eros pulvinar, ut dictum
                    orci pretium. Cras scelerisque eget ex ut viverra. Maecenas mattis nulla eros, ac accumsan nisl imperdiet
                    ac. Nam ac libero elementum, congue risus in, iaculis justo. Ut sagittis at augue eget vestibulum. Phasellus
                    tincidunt, diam at sagittis sagittis, lacus lacus lobortis nisi, at mollis erat purus vel nisl. Mauris
                    facilisis augue felis, ac interdum sem dignissim blandit. Donec ullamcorper mi velit, ac auctor nibh
                    dignissim sit amet. Sed blandit venenatis sem, id gravida orci lacinia eget. Nunc rhoncus, ligula non
                    lacinia egestas, dolor erat condimentum nulla, sed faucibus turpis elit ut mi. <br><br>
                    Aenean condimentum libero sed quam pulvinar pharetra. Donec vitae rhoncus risus. Etiam lacus ante, pharetra
                    at nulla fermentum, venenatis suscipit erat. Pellentesque commodo dignissim arcu nec ultrices. Quisque
                    hendrerit lorem non leo tristique, id porttitor nunc tincidunt. Proin eget ante et erat placerat faucibus.
                    Vivamus a ex nisl. Maecenas non lectus porta ante viverra imperdiet. <br><br>
                </p>
            </div>
        </div>
        <%--Body--%>
        <%--Authentication Container--%>
        <section class="Authentication_Container" id="Authentication_Container">
            <div class="Authentication_Inner_Container">
                <div class="Login_Container">
                    <h1>Create a password</h1>
                    <h2>Complete your sign up to
                        receive your discount.*</h2>
                    <form action="register-servlet" method="post">
                        <input type="text" id="username" name="username" value="" placeholder="User Name">
                        <input type="email" id="email" name="email" value="" placeholder="Email Address">
                        <input type="date"  name="dob" value="" required>
                        <input type="password" id="password" name="password" value="" placeholder="Password">
                        <input type="password" id="repass" name="repass" value="" placeholder="Repeat Password">
                        <input type="hidden" id="role" name="role" value="4">
                        <h2 style="color: red">${error}</h2>
                        <input type="submit" value="Unlock Your Discount">
                    </form>
                    <h2 style="text-align: center; cursor: pointer" class="open_policy">By providing your email address, you
                        agree to our <br>
                        <span style="text-decoration: underline">Privacy Policy</span> and <span
                            style="text-decoration: underline">Terms of Service</span>.</h2>
                    <a href="login.jsp">Already have an account? Sign in</a>
                </div>
            </div>
        </section>

        <%--footer--%>
        <jsp:include page="footer.jsp"/>
        <script>
            //pop up
            xPolicy = document.querySelector("#pop_up_x");
            yPolicy = document.querySelector(".policy_pop_up_container");
            zPolicy = document.querySelector(".open_policy");
            zPolicy.onclick = function () {
                yPolicy.style.display = "flex";
            }
            xPolicy.onclick = function () {
                yPolicy.style.display = "none";
            }

        </script>
    </body>
</html>
