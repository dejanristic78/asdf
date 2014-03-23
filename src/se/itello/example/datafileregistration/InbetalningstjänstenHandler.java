package se.itello.example.datafileregistration;

import java.math.BigDecimal;

public class InbetalningstjänstenHandler extends PaymentHandler{
    private final static String OPENING_POST = "00";
    private final static String PAYMENT_POST = "30";
    private final static String ENDING_POST = "99";
    
    private final DataPostSection postTypeSection;
    private final DataPostSection accountNumberSection;
    private final DataPostSection amountSection;
    private final DataPostSection referenceSection;
    
    InbetalningstjänstenHandler() {
        
        postTypeSection         = new DataPostSection(1, 2);
        accountNumberSection    = new DataPostSection(15, 24);
        amountSection           = new DataPostSection(3, 22);
        referenceSection        = new DataPostSection(41, 65);
        
        paymentDate = null;
        currency = "SEK";
    }
    
    @Override
    protected void parseLines() {
        for(String line : lines) {
            String postType = postTypeSection.getData(line);
            
            switch(postType) {
                case PAYMENT_POST:
                    
                case OPENING_POST:
                    
                case ENDING_POST:
                    
            }
        }
    }
    //OBS! Snygga till!
    private BigDecimal getBigDecimalFromString(String s) {
        String formattedValue = s.replace(" ", "").replace(",", ".");
        //Lägg till sanity check
        BigDecimal b = new BigDecimal(formattedValue);
        return b;
    }
    
}
