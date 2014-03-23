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
    protected class DataPostSection {
        private final int beginIndex;
        private final int endIndex;
        public DataPostSection(int startPosition, int endPosition) {
            this.beginIndex = startPosition-1;
            this.endIndex = endPosition;
        }
        public String getData(String line) {
            return line.substring(beginIndex, endIndex);
        }
    }
    protected class PaymentPost {
        protected final BigDecimal amount;
        protected final String reference;
        public PaymentPost(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    protected static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");
    protected static Charset charset;
    
    protected List<PaymentPost> paymentPosts;
    
    protected String accountNumber;
    protected Date paymentDate;
    protected String currency;
    
    protected List<String> lines;
    protected PaymentReceiver paymentReceiver;
    
    
    public PaymentHandler() {
        charset = DEFAULT_CHARSET;
        this.paymentReceiver = new PaymentReceiverDummy();
        paymentPosts = new ArrayList<>();
    }
    @Override
    public void dispatchFileData(Path path) {
        lines = FileReader.textFileToList(path, charset);
        parseLines();
        registerData();
    }
    protected void parseLines() {
    }
    
    public void registerData() {
        paymentReceiver.startPaymentBundle(accountNumber, paymentDate, currency);
        for(PaymentPost p : paymentPosts) {
            paymentReceiver.payment(p.amount, p.reference);
        }
        paymentReceiver.endPaymentBundle();
    }
    
    
}
