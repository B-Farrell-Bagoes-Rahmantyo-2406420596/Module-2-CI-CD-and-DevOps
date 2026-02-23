package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImpTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        product1 = new Product();
        product1.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);

        product2 = new Product();
        product2.setProductID("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(product1)).thenReturn(product1);

        Product result = productService.create(product1);

        assertNotNull(result);
        assertEquals(product1.getProductID(), result.getProductID());
        assertEquals(product1.getProductName(), result.getProductName());
        assertEquals(product1.getProductQuantity(), result.getProductQuantity());
        verify(productRepository, times(1)).create(product1);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = Arrays.asList(product1, product2);
        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(product1.getProductID(), result.get(0).getProductID());
        assertEquals(product2.getProductID(), result.get(1).getProductID());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindAllProductsWhenEmpty() {
        List<Product> emptyList = new ArrayList<>();
        Iterator<Product> iterator = emptyList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertEquals(0, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testFindProductById() {
        String productId = "eb558e9f-1c39-460e-8860-71af6af63bd6";
        when(productRepository.findById(productId)).thenReturn(product1);

        Product result = productService.findById(productId);

        assertNotNull(result);
        assertEquals(product1.getProductID(), result.getProductID());
        assertEquals("Sampo Cap Bambang", result.getProductName());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindProductByIdNotFound() {
        String productId = "non-existent-id";
        when(productRepository.findById(productId)).thenReturn(null);

        Product result = productService.findById(productId);

        assertNull(result);
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductID("eb558e9f-1c39-460e-8860-71af6af63bd6");
        updatedProduct.setProductName("Sampo Cap Bambang Updated");
        updatedProduct.setProductQuantity(200);

        when(productRepository.update(updatedProduct)).thenReturn(updatedProduct);

        Product result = productService.update(updatedProduct);

        assertNotNull(result);
        assertEquals("Sampo Cap Bambang Updated", result.getProductName());
        assertEquals(200, result.getProductQuantity());
        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testUpdateProductNotFound() {
        Product updatedProduct = new Product();
        updatedProduct.setProductID("non-existent-id");
        updatedProduct.setProductName("Non-existent Product");
        updatedProduct.setProductQuantity(10);

        when(productRepository.update(updatedProduct)).thenReturn(null);

        Product result = productService.update(updatedProduct);

        assertNull(result);
        verify(productRepository, times(1)).update(updatedProduct);
    }

    @Test
    void testDeleteProduct() {
        when(productRepository.delete(product1)).thenReturn(product1);

        Product result = productService.delete(product1);

        assertNotNull(result);
        assertEquals(product1.getProductID(), result.getProductID());
        verify(productRepository, times(1)).delete(product1);
    }

    @Test
    void testDeleteProductNotFound() {
        Product nonExistentProduct = new Product();
        nonExistentProduct.setProductID("non-existent-id");

        when(productRepository.delete(nonExistentProduct)).thenReturn(null);

        Product result = productService.delete(nonExistentProduct);

        assertNull(result);
        verify(productRepository, times(1)).delete(nonExistentProduct);
    }
}