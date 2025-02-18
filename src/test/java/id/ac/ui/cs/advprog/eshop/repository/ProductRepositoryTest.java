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
    void testFindAllIfEmpty() {
        Iterator<Product> productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testFindAllIfMoreThanOneProduct() {
        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(100);
        productRepository.create(product1);

        Product product2 = new Product();
        product2.setProductId("a0f9de46-90b1-437d-a0bf-d0821dde9096");
        product2.setProductName("Sampo Cap Usep");
        product2.setProductQuantity(50);
        productRepository.create(product2);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        Product savedProduct = productIterator.next();
        assertEquals(product1.getProductId(), savedProduct.getProductId());
        savedProduct = productIterator.next();
        assertEquals(product2.getProductId(), savedProduct.getProductId());
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testEditProduct(){
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
        assertEquals("ab55e9f-1c39-460e-8860-71aaf6af63bd6", result.getProductId());
    }

    @Test
    void testEditNullProduct() {
        //
        Product nullProduct = new Product();
        nullProduct.setProductId("null-object");
        nullProduct.setProductName("None");
        nullProduct.setProductQuantity(122);
        productRepository.update(nullProduct);

        assertNull(productRepository.findById("null-object"));
    }

    @Test
    void testDeleteProduct(){

        Product product = new Product();
        product.setProductId("ab55e9f-1c39-460e-8860-71aaf6af63bd6");
        product.setProductName("Pintu kemana saja");
        product.setProductQuantity(88);
        productRepository.create(product);

        Iterator<Product> productIterator = productRepository.findAll();
        assertTrue(productIterator.hasNext());
        productRepository.delete("ab55e9f-1c39-460e-8860-71aaf6af63bd6");

        productIterator = productRepository.findAll();
        assertFalse(productIterator.hasNext());
    }

    @Test
    void testNullDeleteProduct(){

        Product nullProduct = new Product();
        nullProduct.setProductId("nullID");
        productRepository.delete("nullID");

        assertNull(productRepository.findById("nullID"));
    }
}