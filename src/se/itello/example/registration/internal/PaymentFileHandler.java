package se.itello.example.registration.internal;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import se.itello.example.payments.PaymentReceiver;
import se.itello.example.payments.PaymentReceiverDummy;


public abstract class PaymentFileHandler extends FileHandler{
    private class Payment {
        private final BigDecimal amount;
        private final String reference;
        Payment(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    private final List<Payment> payments;
    
    private String accountNumber;
    private Date paymentDate;
    private String currency;
    
    private final PaymentReceiver paymentReceiver;
    
    
    protected PaymentFileHandler() {
        this.paymentReceiver = new PaymentReceiverDummy();
        payments = new ArrayList<>();
    }
    protected void setPaymentInfo(String accountNumber, Date paymentDate, String currency){
        this.accountNumber = accountNumber;
        this.paymentDate = paymentDate;
        this.currency = currency;
    }
    protected void addPayment(BigDecimal amount, String reference) {
        payments.add( new Payment(amount, reference) );
    }
    @Override
    public void registerData() {
        paymentReceiver.startPaymentBundle(accountNumber, paymentDate, currency);
        for(Payment p : payments) {
            paymentReceiver.payment(p.amount, p.reference);
        }
        paymentReceiver.endPaymentBundle();
    }
    
    
}
