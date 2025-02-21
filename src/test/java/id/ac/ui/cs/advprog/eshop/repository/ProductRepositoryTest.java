package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductRepositoryTest {

    @InjectMocks
    ProductRepository productRepository;

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreateAndFind() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(100);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product.getProductId(), savedProduct.getProductId());
        assertEquals(product.getProductName(), savedProduct.getProductName());
        assertEquals(product.getProductQuantity(), savedProduct.getProductQuantity());
    }

    @Test
    void testCreateNullProduct() {
        Product result = productRepository.create(null);
        assertNull(result);
    }

    @Test
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindByIdSuccess() {
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        productRepository.create(product);

        Product foundProduct = productRepository.findById("eb558e9f-1c39-460e-8860-71af6af63bd6");
        assertNotNull(foundProduct);
        assertEquals("Sampo Cap Bambang", foundProduct.getProductName());
    }

    @Test
    void testFindByIdFailure() {
        Product foundProduct = productRepository.findById("nonExistingID");
        assertNull(foundProduct);
    }

    @Test
    void testFindByIdWithNull() {
        Product foundProduct = productRepository.findById(null);
        assertNull(foundProduct);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        product.setProductId("ab55e9f-1c39-460e-8860-71aaf6af63bd6");
        product.setProductName("Sampo Limbad");
        product.setProductQuantity(5);
        productRepository.create(product);

        Product updatedProduct = new Product();
        updatedProduct.setProductId("ab55e9f-1c39-460e-8860-71aaf6af63bd6");
        updatedProduct.setProductName("Sabun Limbad");
        updatedProduct.setProductQuantity(10);
        productRepository.update(updatedProduct);

        Product result = productRepository.findById("ab55e9f-1c39-460e-8860-71aaf6af63bd6");

        assertNotNull(result);
        assertEquals("Sabun Limbad", result.getProductName());
        assertEquals(10, result.getProductQuantity());
    }

    @Test
    void testEditNonExistingProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId("nonExistingID");
        updatedProduct.setProductName("Invalid Product");
        updatedProduct.setProductQuantity(0);

        Product result = productRepository.update(updatedProduct);
        assertNull(result);
    }

    @Test
    void testEditProductWithNullId() {
        Product updatedProduct = new Product();
        updatedProduct.setProductId(null);
        updatedProduct.setProductName("Invalid Product");

        Product result = productRepository.update(updatedProduct);
        assertNull(result);
    }

    @Test
    void testEditNullProduct() {
        Product result = productRepository.update(null);
        assertNull(result);
    }

    @Test
    void testDeleteProduct() {
        Product product = new Product();
        product.setProductId("ab55e9f-1c39-460e-8860-71aaf6af63bd6");
        product.setProductName("Pintu kemana saja");
        product.setProductQuantity(88);
        productRepository.create(product);

        productRepository.delete("ab55e9f-1c39-460e-8860-71aaf6af63bd6");
        assertNull(productRepository.findById("ab55e9f-1c39-460e-8860-71aaf6af63bd6"));
    }

    @Test
    void testDeleteNonExistingProduct() {
        productRepository.delete("nonExistingID");
        assertNull(productRepository.findById("nonExistingID"));
    }

    @Test
    void testDeleteWithNullId() {
        Product product = new Product();
        product.setProductName("Bambang");

        productRepository.delete("qqq");
        Iterator<Product> it = productRepository.findAll();
        assertFalse(it.hasNext());
    }
}
