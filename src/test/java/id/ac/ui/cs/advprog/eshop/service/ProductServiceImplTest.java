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
import java.util.Iterator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    private Product testProduct1;
    private Product testProduct2;

    @BeforeEach
    void setUp() {
        testProduct1 = new Product();
        testProduct1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        testProduct1.setProductName("Sampo Cap Bambang");
        testProduct1.setProductQuantity(10);

        testProduct2 = new Product();
        testProduct2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        testProduct2.setProductName("Sampo Cap Usep");
        testProduct2.setProductQuantity(20);
    }

    @Test
    void testCreateProduct() {
        when(productRepository.create(testProduct1)).thenReturn(testProduct1);

        Product createdProduct = productService.create(testProduct1);

        assertNotNull(createdProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", createdProduct.getProductId());
        assertEquals("Sampo Cap Bambang", createdProduct.getProductName());
        verify(productRepository, times(1)).create(testProduct1);
    }

    @Test
    void testFindAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(testProduct1);
        productList.add(testProduct2);

        Iterator<Product> iterator = productList.iterator();
        when(productRepository.findAll()).thenReturn(iterator);

        List<Product> foundProducts = productService.findAll();

        assertNotNull(foundProducts);
        assertEquals(2, foundProducts.size());
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundProducts.get(0).getProductId());
        assertEquals("a0f9de46-90b1-437d-a0bf-d0821dde9096", foundProducts.get(1).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testUpdateProduct() {
        when(productRepository.update(testProduct1)).thenReturn(testProduct1);

        Product updatedProduct = productService.update(testProduct1);

        assertNotNull(updatedProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", updatedProduct.getProductId());
        verify(productRepository, times(1)).update(testProduct1);
    }

    @Test
    void testFindProductById() {
        when(productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6")).thenReturn(testProduct1);

        Product foundProduct = productService.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");

        assertNotNull(foundProduct);
        assertEquals("eb558e9f-1c39-460e-8860-71af6af63bd6", foundProduct.getProductId());
        verify(productRepository, times(1)).findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }

    @Test
    void testDeleteProduct() {
        doNothing().when(productRepository).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        productService.delete("eb558e9f-1c39-460e-8860-71af6af63bd6");

        verify(productRepository, times(1)).delete("eb558e9f-1c39-460e-8860-71af6af63bd6");
    }
}
