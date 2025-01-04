<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product List</title>
    <link rel="stylesheet" type="text/css" href="css/styles01.css">
</head>
<body>
    <header>
        <h1>Product Management</h1>
    </header>
    <div class="container">
        <h2>Product List</h2>
        <a href="ProductManagementServlet?action=new">Add New Product</a>
        <!-- Search Form -->
        <form action="ProductManagementServlet" method="get">
            <input type="hidden" name="action" value="search"/>
            <label for="keyword">Search by Name or ID:</label>
            <input type="text" name="keyword" id="keyword"/>
            <button type="submit">Search</button>
        </form>
        <!-- End Search Form -->
        <table>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Price</th>
                <th>Status</th>
                <th>Image</th>
                <th>Actions</th>
            </tr>
            <c:forEach var="product" items="${listProduct}">
                <tr>
                    <td>${product.id}</td>
                    <td>${product.name}</td>
                    <td>${product.price}</td>
                    <td>${product.status}</td>
                    <td><img src="images/${product.image}" alt="${product.name}" width="100"/></td>
                    <td>
                        <a href="ProductManagementServlet?action=edit&id=${product.id}">Edit</a>
                        <a href="ProductManagementServlet?action=delete&id=${product.id}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <script src="js/validation.js"></script>
</body>
</html>
