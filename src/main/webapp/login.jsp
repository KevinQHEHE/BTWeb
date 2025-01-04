<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="css/styles01.css">
</head>
<body>
    <header>
        <h1>Login</h1>
    </header>
    <div class="container">
        <form action="LoginServlet" method="post" onsubmit="return validateLoginForm()">
            <label for="username">Username:</label>
            <input type="text" name="username" required/>
            <label for="password">Password:</label>
            <input type="password" name="password" required/>
            <button type="submit">Login</button>
        </form>
    </div>
    <script src="js/validation.js"></script>
</body>
</html>
