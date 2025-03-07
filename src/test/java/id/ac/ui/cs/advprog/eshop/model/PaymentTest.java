package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Order order;
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product1.setProductName("Sampo Cap Bambang");
        product1.setProductQuantity(2);
        products.add(product1);

        this.order = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");

        this.paymentData = new HashMap<String, String>();
    }

    @Test
    void testCreatePaymentWithValidData() {
        Payment payment = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData);

        assertEquals("id-payment1", payment.getId());
        assertEquals(this.order, payment.getOrder());
        assertEquals(PaymentMethod.CASH_ON_DELIVERY.getValue(), payment.getMethod());
        assertEquals(this.paymentData, payment.getPaymentData());
        assertEquals(PaymentStatus.PENDING.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentWithStatus() {
        Payment payment1 = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData, "SUCCESS");
        Payment payment2 = new Payment("id-payment2", this.order, "CASH_ON_DELIVERY", this.paymentData, "REJECTED");
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment1.getStatus());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment2.getStatus());
    }

    @Test
    void testSetPaymentStatus() {
        Payment payment = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData);
        payment.setStatus(PaymentStatus.SUCCESS.getValue());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithValidVoucher() {
        this.paymentData.put("voucherCode", "ESHOP12345678ZZZ");
        Payment payment = new Payment("id-payment1", this.order, "VOUCHER", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucher_Length() {
        paymentData.put("voucherCode", "ESHOP123ABC567");
        Payment payment = new Payment("id-payment1", this.order, "VOUCHER", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucher_NumberCount() {
        paymentData.put("voucherCode", "ESHOP12ABCD567E");
        Payment payment = new Payment("id-payment1", this.order, "VOUCHER", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucher_Prefix() {
        paymentData.put("voucherCode", "SHOPEE4ABC567800");
        Payment payment = new Payment("id-payment1", this.order, "VOUCHER", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidVoucher_Empty() {
        paymentData.put("voucherCode", "");
        Payment payment = new Payment("id-payment1", this.order, "VOUCHER", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithCashOnDelivery_Valid() {
        this.paymentData.put("address", "Jl. Merdeka No.1");
        this.paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidCashOnDelivery_EmptyAddress() {
        this.paymentData.put("address", "");
        this.paymentData.put("deliveryFee", "10000");
        Payment payment = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testPaymentWithInvalidCashOnDelivery_EmptyDeliveryFee() {
        this.paymentData.put("address", "Jl. Merdeka No.1");
        this.paymentData.put("deliveryFee", "");
        Payment payment = new Payment("id-payment1", this.order, "CASH_ON_DELIVERY", this.paymentData);
        payment.verifyAndSetStatus();
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidPaymentMethod() {
        Payment payment = new Payment("id-payment1", this.order, "INVALID_METHOD", this.paymentData);
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            payment.verifyAndSetStatus();
        });
        assertTrue(exception.getMessage().contains("Invalid payment method"));
    }
}