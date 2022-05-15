package com.yunhalee.walkerholic.config;

import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Details;
import com.paypal.api.payments.Item;
import com.paypal.api.payments.ItemList;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.PayerInfo;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Refund;
import com.paypal.api.payments.RefundRequest;
import com.paypal.api.payments.Sale;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PaypalService {

    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

    public String authorizePayment() throws PayPalRESTException {
        Payer payer = getPayerInformation();
        RedirectUrls redirectUrls = getRedirectURLs();

        List<Transaction> listTransaction = getTransactionInformation();
        Payment requestPayment = new Payment();
        requestPayment.setTransactions(listTransaction)
                        .setRedirectUrls(redirectUrls)
                        .setPayer(payer)
                        .setIntent("sale");

        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        Payment approvedPayment = requestPayment.create(apiContext);

        System.out.println(approvedPayment);

        return getApprovalLink(approvedPayment);
    }

    public Payment getPaymentDetails(String paymentId) throws PayPalRESTException {
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        return Payment.get(apiContext, paymentId);
    }

    public Payment executePayment(String paymentId, String payerId) throws PayPalRESTException {
        PaymentExecution paymentExecution = new PaymentExecution();
        paymentExecution.setPayerId(payerId);
        Payment payment = new Payment().setId(paymentId);
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);
        Payment executedPayment = payment.execute(apiContext, paymentExecution);
        System.out.println(executedPayment);
//        String id = executedPayment.getTransactions().get(0).getRelatedResources().get(0).getSale().getId();
        return executedPayment;
    }

    public void refundPayment(String saleId) throws PayPalRESTException {
        Amount amount = new Amount();
        amount.setTotal("7.00");
        amount.setCurrency("USD");

        Sale sale = new Sale();
        sale.setId(saleId);

        RefundRequest request = new RefundRequest();
        request.setAmount(amount);
        APIContext apiContext = new APIContext(clientId, clientSecret, mode);

        Refund returnRefund = sale.refund(apiContext, request);
        System.out.println(returnRefund);
    }

    private String getApprovalLink(Payment approvedPayment){
        List<Links> links = approvedPayment.getLinks();
        String approvalLink = null;
        for (Links link : links) {
            if (link.getRel().equalsIgnoreCase("approval_url")){
                approvalLink = link.getHref();
            }
        }
        System.out.println(approvalLink);

        return approvalLink;

    }

    private List<Transaction> getTransactionInformation(){
        Details details = new Details();
        details.setShipping("2.00");
        details.setSubtotal("5.00");
        details.setTax("0.00");
        Amount amount = new Amount();
        amount.setCurrency("USD");
        amount.setTotal("7.00");
        amount.setDetails(details);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDescription("orderPurchaseTest");

        ItemList itemList = new ItemList();
        List<Item> items = new ArrayList<>();
        Item item = new Item();
        item.setCurrency("USD")
            .setName("TestItem")
            .setTax("0.00")
            .setPrice("5.00")
            .setQuantity("1");
        items.add(item);
        itemList.setItems(items);

        transaction.setItemList(itemList);

        List<Transaction> listTransaction = new ArrayList<>();
        listTransaction.add(transaction);

        return listTransaction;
    }

    private RedirectUrls getRedirectURLs() {
        RedirectUrls redirectUrls = new RedirectUrls();
        redirectUrls.setCancelUrl("http://localhost:3000/cancel");
        redirectUrls.setReturnUrl("http://localhost:3000/review");
        return redirectUrls;
    }

    private Payer getPayerInformation() {
        Payer payer = new Payer();
        payer.setPaymentMethod("paypal");
        PayerInfo payerInfo = new PayerInfo();
        payerInfo.setFirstName("William")
            .setLastName("Peter")
            .setEmail("test@example.com");
        payer.setPayerInfo(payerInfo);
        return payer;
    }

}
