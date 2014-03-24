package se.itello.example.registration.internal;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;


public final class PaymentFileHandlerBetalningsservice extends PaymentFileHandler{
    private final static String OPENING_POST = "O";
    private final static String PAYMENT_POST = "B";
    
    private final DataRowSection postTypeSection;
    private final DataRowSection accountNumberSection;
    private final DataRowSection paymentDateSection;
    private final DataRowSection currencySection;
    private final DataRowSection amountSection;
    private final DataRowSection referenceSection;
    

    public PaymentFileHandlerBetalningsservice() {
        postTypeSection         = new DataRowSection(1, 1);
        accountNumberSection    = new DataRowSection(2, 16);
        paymentDateSection      = new DataRowSection(41, 48);
        currencySection         = new DataRowSection(49, 51);
        amountSection           = new DataRowSection(2, 15);
        referenceSection        = new DataRowSection(16, 50);
    }
    
    @Override
    protected void parse(List<String> dataRows) {
        for(String dataRow : dataRows) {
            String postType = postTypeSection.getSectionFrom(dataRow);
            
            switch(postType) {
                case PAYMENT_POST:
                    String amountStr = amountSection.getSectionFrom(dataRow);
                    BigDecimal amount = getBigDecimalFrom(amountStr);
                    String reference = referenceSection.getSectionFrom(dataRow);
                    addPayment(amount, reference);
                    break;

                case OPENING_POST:
                    String accountNumber = accountNumberSection.getSectionFrom(dataRow);
                    String dateStr = paymentDateSection.getSectionFrom(dataRow);
                    Date paymentDate = getDateFromString(dateStr);
                    String currency = currencySection.getSectionFrom(dataRow);
                    setPaymentInfo(accountNumber, paymentDate, currency);
                    break;
            }
        }
    }
    private BigDecimal getBigDecimalFrom(String s) {
        String formatted = s.replace(" ", "").replace(",", ".");
        return new BigDecimal(formatted);
    }
    private Date getDateFromString(String s) {
        String formatted = 
                s.substring(0, 4) + "-" + 
                s.substring(4, 6) + "-" + 
                s.substring(6);
        
        return Date.valueOf(formatted);
    }
        
    
}
