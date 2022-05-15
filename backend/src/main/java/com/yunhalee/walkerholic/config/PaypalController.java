package com.yunhalee.walkerholic.config;


import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaypalController {

    private final PaypalService paypalService;

    public PaypalController(PaypalService paypalService) {
        this.paypalService = paypalService;
    }

    @PostMapping("/paypal/test")
    public String paypalTest() throws PayPalRESTException {
        return paypalService.authorizePayment();
    }

    @GetMapping("/paypal/detail/test")
    public String getPaymentDetail (@RequestParam("payerId") String payerId, @RequestParam("paymentId") String paymentId) throws PayPalRESTException {

        Payment payment =  paypalService.getPaymentDetails(paymentId);
        return payment.getIntent();
    }

    @PostMapping("/paypal/payment")
    public String paypalPayment (@RequestParam("payerId") String payerId, @RequestParam("paymentId") String paymentId) throws PayPalRESTException {
        Payment payment =  paypalService.executePayment(paymentId, payerId);
        return payment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId();

//        return payment.getIntent();
    }

    @PostMapping("/paypal/refund")
    public String paypalRefund (@RequestParam("saleId") String saleId) throws PayPalRESTException {
         paypalService.refundPayment(saleId);
        return "successRefund";
    }


}
