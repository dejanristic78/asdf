package se.itello.example.datafileregistration;

import java.math.BigDecimal;
import java.util.List;

public class InbetalningstjanstenHandler extends PaymentHandler{
    private final static String OPENING_POST    = "00";
    private final static String PAYMENT_POST    = "30";
    private final static String ENDING_POST     = "99";
    
    private final DataPostSection postTypeSection;
    private final DataPostSection accountNumberSection;
    private final DataPostSection amountSection;
    private final DataPostSection referenceSection;
    
    InbetalningstjanstenHandler() {
        
        postTypeSection         = new DataPostSection(1, 2);
        accountNumberSection    = new DataPostSection(15, 24);
        amountSection           = new DataPostSection(3, 22);
        referenceSection        = new DataPostSection(41, 65);
        
        paymentDate = null;     //Denna information saknas
        currency = "SEK";       //Enligt beskrivning av filformatet
    }
    
    @Override
    protected void parseLines(List<String> lines) {
        for(String line : lines) {
            String postType = postTypeSection.getDataFrom(line);
            
            switch(postType) {
                case PAYMENT_POST:
                    String amountStr = amountSection.getDataFrom(line);
                    BigDecimal amount = getBigDecimalFromString(amountStr);
                    String reference = referenceSection.getDataFrom(line);
                    payments.add( new PaymentData(amount, reference) );
                    break;
                case OPENING_POST:
                    accountNumber = accountNumberSection.getDataFrom(line);
                    break;
                //case ENDING_POST:
                    //Har ingen data som efterfrågas av paymentreceiver. 
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
