<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Login</title>
        <link rel="stylesheet" href="header/header1.css">
        <link rel="stylesheet" href="account/authentication/authentication1.css">
        <link rel="stylesheet" href="footer/ad-container.css">
        <link rel="stylesheet" href="footer/footer.css">
        <!-- Favicon -->
        <link rel="icon" type="image/x-icon" href="webImage/other/icon/favicon.png"/>
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        <section class="Authentication_Container">
            <div class="Authentication_Inner_Container">
                <div class="Login_Container">
                    <h1>Good to see you</h1>
                    <h2>Enter you email and username to reset password</h2>
                    <form action="forget-password" method="post">
                        <input type="email" id="email" name="email" placeholder="Email Address">
                        <input type="text" id="username" name="username" placeholder="Your username">
                        <div class="form-check">
                        </div>
                        <input type="submit" value="Reset password">
                        <h2 style="color: red">
                            ${Message} ${messageError}
                        </h2>
                        <h2 style="color: green;">
                            ${messageSuccess}
                        </h2>
                    </form>
                    <a href="register.jsp">Don’t have an account? Sign up</a>
                    <a href="login-servlet">Login</a>
                </div>
            </div>
        </section>
        <jsp:include page="footer.jsp"/>
        <script>
            function ShowPassword() {
                var x = document.getElementById("password");
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            }
        </script>
    </body>
</html>
