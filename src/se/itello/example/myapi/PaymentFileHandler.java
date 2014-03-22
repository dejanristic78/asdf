package se.itello.example.myapi;

import se.itello.example.payments.PaymentReceiver;


public abstract class PaymentFileHandler extends FileHandler{
    protected PaymentReceiver paymentReceiver;
    
    
    public PaymentFileHandler(PaymentReceiver paymentReceiver) {
        this.paymentReceiver = paymentReceiver;
    }
    
    
}
