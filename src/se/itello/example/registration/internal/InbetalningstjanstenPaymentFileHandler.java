package se.itello.example.registration.internal;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public final class InbetalningstjanstenPaymentFileHandler extends PaymentFileHandler{
    private final static String OPENING_POST    = "00";
    private final static String PAYMENT_POST    = "30";
    private final static String ENDING_POST     = "99";
    
    private final static Date paymentDate = null;     //Filformatet saknar denna information.
    private final static String currency = "SEK";     //Enligt beskrivning av filformatet.
    
    private final DataPostSection postTypeSection;
    private final DataPostSection accountNumberSection;
    private final DataPostSection amountSection;
    private final DataPostSection referenceSection;
    
    public InbetalningstjanstenPaymentFileHandler() {
        postTypeSection         = new DataPostSection(1, 2);
        accountNumberSection    = new DataPostSection(15, 24);
        amountSection           = new DataPostSection(3, 22);
        referenceSection        = new DataPostSection(41, 65);
    }
    
    @Override
    protected void parse(List<String> dataPosts) {
        for(String post : dataPosts) {
            String postType = postTypeSection.getDataFrom(post);
            
            switch(postType) {
                case PAYMENT_POST:
                    String amountStr = amountSection.getDataFrom(post);
                    BigDecimal amount = getBigDecimalFromString(amountStr);
                    String reference = referenceSection.getDataFrom(post);
                    addPayment(amount, reference);
                    break;
                case OPENING_POST:
                    String accountNumber = accountNumberSection.getDataFrom(post);
                    setPaymentInfo(accountNumber, paymentDate, currency);
                    break;
                //case ENDING_POST:
                    //Har ingen data som efterfrågas av PaymentReceiver. 
                    //Eventuellt kan man lägga till kontroll av totalbelopp här
            }
        }
    }
    private BigDecimal getBigDecimalFromString(String s) {
        int decimalI = s.length()-2;
        String formatted = s.substring(0, decimalI) + "." + s.substring(decimalI);
        return new BigDecimal(formatted);
    }
    
}
