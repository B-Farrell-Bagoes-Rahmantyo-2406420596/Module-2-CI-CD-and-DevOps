package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();

        product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    // ============ CREATE PRODUCT TESTS ============

    @Test
    void testCreateProductPageDisplaysForm() throws Exception {
        mockMvc.perform(get("/product/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("createProduct"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    void testCreateProductPostRedirectsToList() throws Exception {
        when(productService.create(any(Product.class))).thenReturn(product1);

        mockMvc.perform(post("/product/create")
                        .param("productName", "Sampo Cap Bambang")
                        .param("productQuantity", "100"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).create(any(Product.class));
    }

    // ============ LIST PRODUCT TESTS ============

    @Test
    void testProductListPageDisplaysProducts() throws Exception {
        List<Product> productList = Arrays.asList(product1, product2);
        when(productService.findAll()).thenReturn(productList);

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }

    @Test
    void testProductListPageDisplaysEmptyList() throws Exception {
        when(productService.findAll()).thenReturn(new ArrayList<>());

        mockMvc.perform(get("/product/list"))
                .andExpect(status().isOk())
                .andExpect(view().name("productList"))
                .andExpect(model().attributeExists("products"));

        verify(productService, times(1)).findAll();
    }

    // ============ EDIT PRODUCT TESTS ============

    @Test
    void testEditProductPageDisplaysProduct() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product1);

        mockMvc.perform(get("/product/edit/{id}", productId))
                .andExpect(status().isOk())
                .andExpect(view().name("editProduct"))
                .andExpect(model().attributeExists("product"));

        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPageWhenProductNotFound() throws Exception {
        String productId = "non-existent-id";
        when(productService.findById(productId)).thenReturn(null);

        // Should redirect to list when product not found
        mockMvc.perform(get("/product/edit/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).findById(productId);
    }

    @Test
    void testEditProductPostUpdatesAndRedirects() throws Exception {
        when(productService.update(any(Product.class))).thenReturn(product1);

        mockMvc.perform(post("/product/edit")
                        .param("productID", "eb558e9f-1c39-460e-8860-71af6af63bd6")
                        .param("productName", "Sampo Cap Bambang Updated")
                        .param("productQuantity", "200"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("list"));

        verify(productService, times(1)).update(any(Product.class));
    }

    // ============ DELETE PRODUCT TESTS ============

    @Test
    void testDeleteProductRemovesAndRedirects() throws Exception {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productService.findById(productId)).thenReturn(product1);
        when(productService.delete(product1)).thenReturn(product1);

        mockMvc.perform(get("/product/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).findById(productId);
        verify(productService, times(1)).delete(product1);
    }

    @Test
    void testDeleteProductWhenNotFound() throws Exception {
        String productId = "non-existent-id";
        when(productService.findById(productId)).thenReturn(null);

        // Should redirect to list when product not found (without calling delete)
        mockMvc.perform(get("/product/delete/{id}", productId))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/product/list"));

        verify(productService, times(1)).findById(productId);
        verify(productService, never()).delete(any(Product.class));
    }
}