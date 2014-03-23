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
    protected class PaymentData {
        private final BigDecimal amount;
        private final String reference;
        PaymentData(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    protected List<PaymentData> payments;
    
    protected String accountNumber;
    protected Date paymentDate;
    protected String currency;
    
    protected PaymentReceiver paymentReceiver;
    
    
    protected PaymentHandler() {
        this.paymentReceiver = new PaymentReceiverDummy();
        payments = new ArrayList<>();
    }
    @Override
    public void registerData() {
        paymentReceiver.startPaymentBundle(accountNumber, paymentDate, currency);
        for(PaymentData pd : payments) {
            paymentReceiver.payment(pd.amount, pd.reference);
        }
        paymentReceiver.endPaymentBundle();
    }
    
    
}
