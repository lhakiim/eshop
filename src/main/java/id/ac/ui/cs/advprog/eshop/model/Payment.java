package id.ac.ui.cs.advprog.eshop.model;

import lombok.Builder;
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
        this.status = "PENDING PAYMENT";
    }

    public Payment(String id, Order order, String method, Map<String, String> paymentData, String status) {
        this(id, order, method, paymentData);
        this.status = status;
    }

    public void verifyAndSetStatus() {
        if ("VOUCHER".equalsIgnoreCase(method)) {
            verifyVoucherPayment();
        } else if ("CASH_ON_DELIVERY".equalsIgnoreCase(method)) {
            verifyCashOnDeliveryPayment();
        } else {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
    }

    private void verifyVoucherPayment() {
        String voucherCode = paymentData.get("voucherCode");
        if (voucherCode == null || voucherCode.length() != 16 || !voucherCode.startsWith("ESHOP")) {
            setStatus("REJECTED");
            return;
        }

        long digitCount = voucherCode.chars().filter(Character::isDigit).count();
        setStatus(digitCount == 8 ? "SUCCESS" : "REJECTED");
    }

    private void verifyCashOnDeliveryPayment() {
        String address = paymentData.get("address");
        String deliveryFee = paymentData.get("deliveryFee");

        boolean isInvalid = address == null || address.trim().isEmpty()
                || deliveryFee == null || deliveryFee.trim().isEmpty();

        setStatus(isInvalid ? "REJECTED" : "SUCCESS");
    }
}