package se.itello.example.datafileregistration;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


final class BetalningsserviceHandler extends PaymentHandler{
    private final static String OPENING_POST = "O";
    private final static String PAYMENT_POST = "B";
    
    private final DataPostSection postTypeSection;
    private final DataPostSection accountNumberSection;
    private final DataPostSection paymentDateSection;
    private final DataPostSection currencySection;
    private final DataPostSection amountSection;
    private final DataPostSection referenceSection;
    
    
    BetalningsserviceHandler() {
        
        postTypeSection         = new DataPostSection(1, 1);
        accountNumberSection    = new DataPostSection(2, 16);
        paymentDateSection      = new DataPostSection(41, 48);
        currencySection         = new DataPostSection(49, 51);
        amountSection           = new DataPostSection(2, 15);
        referenceSection        = new DataPostSection(16, 50);
        
    }
    
    @Override
    protected void parseLines(List<String> dataPosts) {
        for(String post : dataPosts) {
            String postType = postTypeSection.getDataFrom(post);
            
            switch(postType) {
                case PAYMENT_POST:
                    String amountStr = amountSection.getDataFrom(post);
                    BigDecimal amount = getBigDecimalFromString(amountStr);
                    String reference = referenceSection.getDataFrom(post);
                    payments.add( new PaymentData(amount, reference) );
                    break;

                case OPENING_POST:
                    accountNumber = accountNumberSection.getDataFrom(post);
                    String dateStr = paymentDateSection.getDataFrom(post);
                    paymentDate = getDateFromString(dateStr);
                    currency = currencySection.getDataFrom(post);
                    break;
            }
        }
    }
    private BigDecimal getBigDecimalFromString(String s) {
        String formattedValue = s.replace(" ", "").replace(",", ".");
        return new BigDecimal(formattedValue);
    }
    private Date getDateFromString(String s) {
        //Lägg till sanity check
        String jdbcFormat = 
                s.substring(0, 4) + "-" + 
                s.substring(4, 6) + "-" + 
                s.substring(6);
        
        return Date.valueOf(jdbcFormat);
    }
        
    
}
