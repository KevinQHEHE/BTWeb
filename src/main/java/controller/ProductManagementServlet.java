package controller;

import dao.ProductDAO;
import model.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/ProductManagementServlet")
@MultipartConfig
public class ProductManagementServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductDAO productDAO;

    public ProductManagementServlet() {
        super();
        productDAO = new ProductDAO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                listProduct(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "insert":
                insertProduct(request, response);
                break;
            case "update":
                updateProduct(request, response);
                break;
            case "search":
                searchProduct(request, response);
                break;
            default:
                doGet(request, response);
                break;
        }
    }

    private void listProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> listProduct = productDAO.getAllProducts();
        request.setAttribute("listProduct", listProduct);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/product_form.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product existingProduct = productDAO.getAllProducts().stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        request.setAttribute("product", existingProduct);
        request.getRequestDispatcher("/product_form.jsp").forward(request, response);
    }

    private void insertProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
    	 String name = request.getParameter("name");
         double price = Double.parseDouble(request.getParameter("price"));
         String status = request.getParameter("status");

         Part filePart = request.getPart("image");
         String fileName = filePart.getSubmittedFileName();
         String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
         File uploadDir = new File(uploadPath);
         if (!uploadDir.exists()) uploadDir.mkdir();
         String filePath = uploadPath + File.separator + fileName;
         filePart.write(filePath);

         Product newProduct = new Product();
         newProduct.setName(name);
         newProduct.setPrice(price);
         newProduct.setStatus(status);
         newProduct.setImage(fileName);

         productDAO.addProduct(newProduct);
         response.sendRedirect("ProductManagementServlet?action=list");
    }

    private void updateProduct(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        String status = request.getParameter("status");

        Part filePart = request.getPart("image");
        String fileName = filePart.getSubmittedFileName();
        String uploadPath = getServletContext().getRealPath("") + File.separator + "images";
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        Product product = new Product();
        product.setId(id);
        product.setName(name);
        product.setPrice(price);
        product.setStatus(status);
        product.setImage(fileName);

        productDAO.updateProduct(product);
        response.sendRedirect("ProductManagementServlet?action=list");
    }

    private void deleteProduct(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.deleteProduct(id);
        response.sendRedirect("ProductManagementServlet?action=list");
    }
    
    private void searchProduct(HttpServletRequest request, HttpServletResponse response) throws IOException ,ServletException{
        String keyword = request.getParameter("keyword").toLowerCase();
        List<Product> searchResult = productDAO.getAllProducts().stream()
                .filter(p -> p.getName().toLowerCase().contains(keyword) || String.valueOf(p.getId()).contains(keyword))
                .collect(Collectors.toList());
        request.setAttribute("listProduct", searchResult);
        request.getRequestDispatcher("/products.jsp").forward(request, response);
    }
}
