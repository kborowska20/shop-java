package com.codecool.shop.controller;

import com.codecool.shop.dao.implementation.ProductDaoSQLite;
import com.codecool.shop.model.Product;
import com.codecool.shop.model.ProductCategory;
import com.codecool.shop.model.Supplier;
import org.junit.jupiter.api.Test;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by morthan on 23.05.17.
 */
class ProductControllerTest {

    @Test
    void testRenderAllProducts() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductController productController = new ProductController(conn);
        assertEquals("{categoryList=[id: 1, name: Fruit, department: Food," +
                        " description: The best the Earth has given us., id: 2, name: Dairy, department: Food," +
                        " description: Stolen from under a cow's nose., id: 3, name: Pastries, department: Food," +
                        " description: Tasty and fresh., id: 4, name: Meat, department: Food, description: Always fresh.," +
                        " id: 5, name: Processed, department: Food, description: Forever fresh.]," +
                        " supplierList=[id: 1, name: Mlekpol, description: Polish dairy products., id: 2, name: Sokołów," +
                        " description: Our sausages are actually superior., id: 3, name: Felix," +
                        " description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga," +
                        " description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples.," +
                        " id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]," +
                        " productList=[id: 1, name: Bread, defaultPrice: 2,20, defaultCurrency: PLN," +
                        " productCategory: Pastries, supplier: Piekarnia Mojego Taty, id: 2, name: Milk, defaultPrice: 1,70," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, id: 3, name: Bananas, defaultPrice: 3,10," +
                        " defaultCurrency: PLN, productCategory: Fruit, supplier: Boongaboonga, id: 4, name: Butter, defaultPrice: 4,20," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, id: 5, name: Peanut butter, defaultPrice: 9,50," +
                        " defaultCurrency: PLN, productCategory: Processed, supplier: Felix, id: 6, name: Apples, defaultPrice: 1,40," +
                        " defaultCurrency: PLN, productCategory: Fruit, supplier: PolSad, id: 7, name: White cheese, defaultPrice: 1,80," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, id: 8, name: Eggs, defaultPrice: 5,40," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Felix, id: 9, name: Tomatoes, defaultPrice: 3,70," +
                        " defaultCurrency: PLN, productCategory: Fruit, supplier: PolSad, id: 10, name: Chicken," +
                        " defaultPrice: 6,90, defaultCurrency: PLN, productCategory: None, supplier: None]}"
                , productController.renderProducts(req, res).getModel().toString());

    }

    @Test
    void renderProductsBySupplier() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductController productController = new ProductController(conn);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(":sid", "1");
        when(req.params()).thenReturn(hashMap);
        ModelAndView testModelAndView = productController.renderProducts(req, res);
        assertEquals("{categoryList=[id: 1, name: Fruit, department: Food, description: The best the Earth has given us.," +
                        " id: 2, name: Dairy, department: Food, description: Stolen from under a cow's nose., id: 3," +
                        " name: Pastries, department: Food, description: Tasty and fresh., id: 4, name: Meat," +
                        " department: Food, description: Always fresh., id: 5, name: Processed, department: Food," +
                        " description: Forever fresh.], supplierList=[id: 1, name: Mlekpol, description: Polish dairy products.," +
                        " id: 2, name: Sokołów, description: Our sausages are actually superior., id: 3, name: Felix," +
                        " description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga," +
                        " description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples.," +
                        " id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]," +
                        " productList=[id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN," +
                        " productCategory: Dairy, supplier: Mlekpol, id: 4, name: Butter, defaultPrice: 4,20," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, id: 7, name: White cheese," +
                        " defaultPrice: 1,80, defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol]}"
                , productController.renderProducts(req, res).getModel().toString());
    }

    @Test
    void renderProductsByCategory() throws SQLException {
        Request req = mock(Request.class);
        Response res = mock(Response.class);
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductController productController = new ProductController(conn);
        HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put(":cid", "2");
        when(req.params()).thenReturn(hashMap);
        assertEquals("{categoryList=[id: 1, name: Fruit, department: Food, description: The best the Earth has given us.," +
                        " id: 2, name: Dairy, department: Food, description: Stolen from under a cow's nose.," +
                        " id: 3, name: Pastries, department: Food, description: Tasty and fresh., " +
                        "id: 4, name: Meat, department: Food, description: Always fresh., id: 5, name: Processed," +
                        " department: Food, description: Forever fresh.], supplierList=[id: 1, name: Mlekpol, " +
                        "description: Polish dairy products., id: 2, name: Sokołów, description: Our sausages are actually superior.," +
                        " id: 3, name: Felix, description: Suck on that peanut, won't ya'?, id: 4, name: Boongaboonga," +
                        " description: Our food isn't safe to eat., id: 5, name: PolSad, description: We only sell apples.," +
                        " id: 6, name: Piekarnia Mojego Taty, description: Actually, only Mom knows how to make the bread we sell.]," +
                        " productList=[id: 2, name: Milk, defaultPrice: 1,70, defaultCurrency: PLN, productCategory: Dairy," +
                        " supplier: Mlekpol, id: 4, name: Butter, defaultPrice: 4,20, defaultCurrency: PLN," +
                        " productCategory: Dairy, supplier: Mlekpol, id: 7, name: White cheese, defaultPrice: 1,80," +
                        " defaultCurrency: PLN, productCategory: Dairy, supplier: Mlekpol, id: 8, name: Eggs, " +
                        "defaultPrice: 5,40, defaultCurrency: PLN, productCategory: Dairy, supplier: Felix]}"
                , productController.renderProducts(req, res).getModel().toString());
    }

    @Test
    void testAddProduct() throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:sqlite:src/main/test/resources/test.db");
        ProductController productController = new ProductController(conn);
        ProductDaoSQLite productDaoSQLite = new ProductDaoSQLite(conn);
        ProductCategory productCategory = new ProductCategory(1, "Fruit", "Test", "Test");
        Supplier supplier = new Supplier(1, "Mlekpol", "Test");
        Product product = new Product(25, "Test", 2.00f, "PLN", "Test", productCategory, supplier);
        productController.addProduct(product);
        List<Product> products = productDaoSQLite.getAll();
        Product testProduct = products.get(products.size() - 1);
        assertEquals(product.getName(), testProduct.getName());
        productController.removeProduct(testProduct.getId());
    }
}