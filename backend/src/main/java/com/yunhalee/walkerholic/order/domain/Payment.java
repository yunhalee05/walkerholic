package com.yunhalee.walkerholic.order.domain;

import com.yunhalee.walkerholic.order.exception.OrderNotPaidException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Getter
@NoArgsConstructor
public class Payment {

    private BigDecimal shipping;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "paid_at")
    private LocalDateTime paidAt;

    @Column(name = "transaction_id")
    private String transactionId;

    public Payment(BigDecimal shipping, String paymentMethod, String transactionId) {
        this.shipping = shipping;
        this.paymentMethod = paymentMethod;
        this.paidAt = LocalDateTime.now();
        this.transactionId = transactionId;
    }
}
