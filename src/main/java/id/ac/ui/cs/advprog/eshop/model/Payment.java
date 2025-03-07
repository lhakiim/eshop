package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
public class Payment {
    String id;
    Order order;
    String method;

    @Setter
    String status;
    Map<String, String> paymentData;

    public Payment(String id, Order order, String method, Map<String, String> paymentData) {
        this.id = id;
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = PaymentStatus.PENDING.getValue();
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this(id, order, method, paymentData);
        this.status = status;
    }

    public void verifyAndSetStatus() {
        if (PaymentMethod.VOUCHER.getValue().equalsIgnoreCase(method)) {
            verifyVoucherPayment();
        } else if (PaymentMethod.CASH_ON_DELIVERY.getValue().equalsIgnoreCase(method)) {
            verifyCashOnDeliveryPayment();
        } else {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }

    private void verifyVoucherPayment() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            setStatus(PaymentStatus.REJECTED.getValue());
            return;
        }

        long digitCount = voucherCode.chars().filter(Character::isDigit).count();
        setStatus(digitCount == 8 ? PaymentStatus.SUCCESS.getValue() :
                PaymentStatus.REJECTED.getValue());
    }

    private void verifyCashOnDeliveryPayment() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        boolean isInvalid = address == null || address.trim().isEmpty()
                || deliveryFee == null || deliveryFee.trim().isEmpty();

        setStatus(isInvalid ? PaymentStatus.REJECTED.getValue() : PaymentStatus.SUCCESS.getValue());
    }
}