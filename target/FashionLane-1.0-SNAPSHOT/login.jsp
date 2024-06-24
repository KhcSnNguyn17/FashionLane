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
            <h2>Please log in to your account</h2>
            <form action="login-servlet" method="post">
                <input type="email" id="email" name="email" value="${newemail}${emailUser}" placeholder="Email Address">
                <h2>${EmailErr}</h2>
                <div class="showpass">
                    <input type="password" id="password" name="password" value="${newpass}${passwordUser}"
                           placeholder="Password">
                    <span onclick="ShowPassword()" class="input-group-text cursor-pointer"><i
                            class="bx bx-hide"></i></span>
                </div>
                <h2>${PassErr}</h2>
                <div class="form-check">
                    <input style="width: 16px; height: 16px; margin-right: 10px" class="form-check-input" type="checkbox" id="remember-me" name="remember me"/>
                    <label style="font-family: 'Nunito Sans', sans-serif;" class="form-check-label" for="remember-me"> Remember Me </label>
                </div>
                <input type="submit" value="Login">
                <h2 style="color: red">
                    ${Message} ${MessageGG}
                </h2>
                <h2 style="color: green;">
                    ${success}
                </h2>
            </form>
            <a href="register.jsp">Don’t have an account? Sign up</a>
            <a href="forget-password">Forgot your password</a>
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
