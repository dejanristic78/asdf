package se.itello.example.datafileregistration;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import se.itello.example.payments.PaymentReceiver;


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
    
    
    public PaymentHandler(PaymentReceiver paymentReceiver) {
        this.paymentReceiver = paymentReceiver;
        paymentPosts = new ArrayList<>();
    }
    
    
}
