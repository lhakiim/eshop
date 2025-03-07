package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Order order1;
    private Order order2;
    private Payment payment1;
    private Payment payment2;
    private List<Payment> payments;
    private Map<String, String> paymentData1;
    private Map<String, String> paymentData2;

    @BeforeEach
    void setUp() {
        payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        order1 = new Order("13652556-012a-4c07-b546-54eb1396d79b",
                products, 1708560000L, "Safira Sudrajat");
        order2 = new Order("7f9e15bb-4b15-42f4-aebc-c3af385fb078",
                products, 1708570000L, "Safira Sudrajat");

        paymentData1 = new HashMap<>();
        paymentData2 = new HashMap<>();
        paymentData1.put("address", "Jl. Merdeka No. 45, Jakarta");
        paymentData1.put("deliveryFee", "15000");
        paymentData2.put("address", "Jl. Merdeka No. 46, Jakarta");
        paymentData2.put("deliveryFee", "15000");

        payment1 = new Payment("id-payment1",
                order1, PaymentMethod.VOUCHER.getValue(),
                paymentData1, PaymentStatus.SUCCESS.getValue());
        payment2 = new Payment("id-payment2",
                order2, PaymentMethod.VOUCHER.getValue(),
                paymentData2, PaymentStatus.SUCCESS.getValue());

        payments.add(payment1);
        payments.add(payment2);
    }

    @Test
    void testAddPayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment1);

        Payment addedPayment = paymentService.addPayment(order1, PaymentMethod.VOUCHER.getValue(), paymentData1);

        assertNotNull(addedPayment);
        assertEquals(PaymentMethod.VOUCHER.getValue(), addedPayment.getMethod());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    @Test
    void testSetStatus() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment1);

        paymentService.setStatus(payment1, PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment1.getStatus());

        verify(paymentRepository, times(1)).save(payment1);
    }

    @Test
    void testGetPaymentByIdFound() {
        when(paymentRepository.getPaymentById("id-payment1")).thenReturn(payment1);

        Payment foundPayment = paymentService.getPayment("id-payment1");
        assertNotNull(foundPayment);
        assertEquals("id-payment1", foundPayment.getId());
        verify(paymentRepository, times(1)).getPaymentById("id-payment1");
    }

    @Test
    void testGetPaymentByIdNotFound() {
        when(paymentRepository.getPaymentById("notFound")).thenReturn(null);
        assertNull(paymentService.getPayment("notFound"));
    }

    @Test
    void testGetAllPayments() {
        when(paymentRepository.getAllPayments()).thenReturn(payments);

        List<Payment> retrievedPayments = paymentService.getAllPayments();

        assertEquals(2, retrievedPayments.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}