package se.itello.example.datafileregistration;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import se.itello.example.payments.PaymentReceiver;
import se.itello.example.payments.PaymentReceiverDummy;


public abstract class PaymentHandler extends Handler{
    protected class PaymentPost {
        protected final BigDecimal amount;
        protected final String reference;
        public PaymentPost(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    protected List<PaymentPost> paymentPosts;
    
    protected String accountNumber;
    protected Date paymentDate;
    protected String currency;
    
    protected PaymentReceiver paymentReceiver;
    
    
    protected PaymentHandler() {
        this.paymentReceiver = new PaymentReceiverDummy();
        paymentPosts = new ArrayList<>();
    }
    @Override
    public void registerData() {
        paymentReceiver.startPaymentBundle(accountNumber, paymentDate, currency);
        for(PaymentPost p : paymentPosts) {
            paymentReceiver.payment(p.amount, p.reference);
        }
        paymentReceiver.endPaymentBundle();
    }
    
    
}
