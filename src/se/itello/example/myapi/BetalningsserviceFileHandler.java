package se.itello.example.myapi;

import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.sql.Date;
import java.util.List;
import se.itello.example.payments.PaymentReceiver;


public class BetalningsserviceFileHandler extends FileHandler{
    
    class LineDataSection {
        final int beginIndex;
        final int endIndex;
        public LineDataSection(int startPosition, int endPosition) {
            this.beginIndex = startPosition-1;
            this.endIndex = endPosition;
        }
        public String getData(String line) {
            return line.substring(beginIndex, endIndex);
        }
    }
    class PaymentPost {
        final BigDecimal amount;
        final String reference;
        public PaymentPost(BigDecimal amount, String reference) {
            this.amount = amount;
            this.reference = reference;
        }
    }
    protected List<PaymentPost> paymentPosts = null;
    
    protected String accountNumber = null;
    protected Date paymentDate = null;
    protected String currency = null;
    
    private final static String OPENING_POST = "O";
    private final static String PAYMENT_POST = "B";
    
    private final LineDataSection postTypeSection;
    private final LineDataSection accountNumberSection;
    private final LineDataSection paymentDateSection;
    private final LineDataSection currencySection;
    private final LineDataSection amountSection;
    private final LineDataSection referenceSection;
    
    
    public BetalningsserviceFileHandler() {
        super();
        
        postTypeSection = new LineDataSection(1, 1);
        accountNumberSection = new LineDataSection(2, 16);
        paymentDateSection = new LineDataSection(41, 48);
        currencySection = new LineDataSection(49, 51);
        amountSection = new LineDataSection(2, 15);
        referenceSection = new LineDataSection(16, 50);
        
    }
    
    
    private void parseLines() {
        for(String line : lines) {
            String postType = postTypeSection.getData(line);
            
            System.out.println(postType);
            
            switch(postType) {
                case PAYMENT_POST:
                    String amountStr = amountSection.getData(line);
                    BigDecimal amount = stringToBigDecimal(amountStr);
                    String reference = referenceSection.getData(line);
                    paymentPosts.add( new PaymentPost(amount, reference) );
                    break;

                case OPENING_POST:
                    accountNumber = accountNumberSection.getData(line);
                    String dateStr = paymentDateSection.getData(line);
                    paymentDate = Date.valueOf(dateStr);
                    currency = currencySection.getData(line);
                    break;
            }
        }
    }
    //OBS! Snygga till!
    private BigDecimal stringToBigDecimal(String value) {
        System.out.println("\n"+value);
        String formattedValue = value.replace(",", ".");
        BigDecimal b = new BigDecimal(formattedValue);
        System.out.println("stringToBigDecimal: "+b);
        return b;
    }
        
    
}
