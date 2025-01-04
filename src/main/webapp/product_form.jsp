<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Form</title>
    <link rel="stylesheet" type="text/css" href="css/styles01.css">
</head>
<body>
    <header>
        <h1>${product == null ? 'Add New Product' : 'Edit Product'}</h1>
    </header>
    <div class="container">
        <form action="ProductManagementServlet?action=${product == null ? 'insert' : 'update'}" method="post" enctype="multipart/form-data">
            <c:if test="${product != null}">
                <input type="hidden" name="id" value="${product.id}"/>
            </c:if>
            <label for="name">Name:</label>
            <input type="text" name="name" value="${product != null ? product.name : ''}" required/>
            <label for="price">Price:</label>
            <input type="number" step="0.01" name="price" value="${product != null ? product.price : ''}" required/>
            <label for="status">Status:</label>
            <select name="status" required>
                <option value="active" ${product != null && product.status == 'active' ? 'selected' : ''}>Active</option>
                <option value="inactive" ${product != null && product.status == 'inactive' ? 'selected' : ''}>Inactive</option>
            </select>
            <label for="image">Image:</label>
            <input type="file" name="image" ${product == null ? 'required' : ''}/>
            <button type="submit">${product == null ? 'Add' : 'Update'}</button>
        </form>
    </div>
    <script src="js/validation.js"></script>
</body>
</html>
