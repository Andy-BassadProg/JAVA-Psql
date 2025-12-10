import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DataRetriever {
    Connection c;

    public List<Category> getCategories() throws SQLException {
        List<Category> categories = new ArrayList<>();
        Statement s = c.createStatement();

        ResultSet rs = s.executeQuery("SELECT id_product_category, name FROM product_category");
        while (rs.next()) {
            int id_product_category = rs.getInt("id_product_category");
            String name = rs.getString("name");
            Category category = new Category(id_product_category, name);
            categories.add(category);
        }
        return categories;
    }

    public List<Product> getProductList(int page, int size) throws SQLException {
        List<Product> products = new ArrayList<>();
        int offset = (page - 1) * size;
        Statement s = c.createStatement();
        ResultSet sr = s.executeQuery("SELECT id_product, product.name AS product_name, product.price, " +
                "product.creation_datetime FROM product " +
                "INNER JOIN product_category" +
                "ON product.id_product = product_category.id_product" +
                "LIMIT" + size + "OFFSET" + offset);

        while (sr.next()) {
            int id_Category = sr.getInt("product_category.id");
            String name = sr.getString("product_category.name");

            int id = sr.getInt("id_product");
            String name_product = sr.getString("product_name");
            LocalDateTime creationDateTime = sr.getObject("datetime", LocalDateTime.class);
            double price = sr.getDouble("product.price");

            Category category = new Category(id_Category, name);
            Product product = new Product(id, name_product, creationDateTime, price, category);
            products.add(product);
        }
        return products;
    }

    public List<Product> getProductsByCriteria(String productName, String
            categoryName, LocalDateTime creationMin, LocalDateTime creationMax) throws SQLException {

        List<Product> products = new ArrayList<>();
        String sql = "SELECT product.id AS id_product, product.name AS product_name, product.price, product.creation_datetime," +
                "product_category.id_product_category AS category_id, product_category.name AS category_name" +
                "FROM product INNER JOIN product_category" +
                "ON product.id_product = product_category.id_product" +
                "WHERE 1=1";

        if (productName != null) {
            sql += " AND product.name ILIKE ?";
        }
        if (categoryName != null) {
            sql += " AND category_name ILIKE ?";
        }
        if (creationMin != null) {
            sql += " AND creation_datetime < ?";
        }
        if (creationMax != null) {
            sql += " AND creation_datetime > ?";
        }

        PreparedStatement ps = c.prepareStatement(sql);
        int i = 1;
        if (productName != null) {
            ps.setString(i++, "%" + productName + "%");
        }
        if (categoryName != null) {
            ps.setString(i++, "%" + categoryName + "%");
        }
        if (creationMin != null) {
            ps.setObject(i++, creationMin);
        }
        if (creationMax != null) {
            ps.setObject(i++, creationMax);
        }

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id_product = rs.getInt("id_product");
        }
    }
}

