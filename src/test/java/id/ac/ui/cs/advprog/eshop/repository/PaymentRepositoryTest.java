package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {

    PaymentRepository repository;
    Payment payment1;
    Payment payment2;
    Order order;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        repository = new PaymentRepository();

        List<Product> products = new ArrayList<>();
        Map<String, String> voucherData = new HashMap<>();

        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(3);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products,
                1708560000L, "Safira Sudrajat");

        voucherData.put("voucherCode", "ESHOP12345678AAA");

        Map<String, String> cashOnDeliveryData = new HashMap<>();
        cashOnDeliveryData.put("address", "Jl. Merdeka No. 45, Jakarta");
        cashOnDeliveryData.put("deliveryFee", "15000");

        payment1 = new Payment("id-payment1", order, "VOUCHER", voucherData);
        payment2 = new Payment("id-payment2", order, "CASH_ON_DELIVERY", cashOnDeliveryData);
    }

    @Test
    void testCreateAndSaveSuccess() {
        Payment saved = repository.save(payment1);

        assertNotNull(saved);
        assertEquals("id-payment1", saved.getId());

        Payment found = repository.findById("id-payment1");
        assertNotNull(found);
        assertEquals("id-payment1", found.getId());
    }

    @Test
    void testCreateAndSaveFail() {
        assertThrows(NullPointerException.class, () -> {
            repository.save(null);
        });
    }

    @Test
    void testSaveUpdateSuccess() {
        repository.save(payment1);
        payment1.setStatus(PaymentStatus.SUCCESS.getValue());
        repository.save(payment1);

        Payment updated = repository.findById("id-payment1");

        assertEquals(PaymentStatus.SUCCESS.getValue(), updated.getStatus());
    }

    @Test
    void testFindByIdFound() {
        repository.save(payment1);
        Payment found = repository.findById("id-payment1");

        assertNotNull(found);
        assertEquals("id-payment1", found.getId());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        Payment notFound = repository.findById("not-found");
        assertNull(notFound);
    }

    @Test
    void testFindAllPayments() {
        repository.save(payment1);
        repository.save(payment2);

        List<Payment> allPayments = repository.findAll();

        assertEquals(2, allPayments.size());
        assertEquals("id-payment1", allPayments.get(0).getId());
        assertEquals("id-payment2", allPayments.get(1).getId());
    }

}