/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package se.itello.example.payments;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Dejan
 */
public class PaymentReceiverDummy implements PaymentReceiver{
    String name = this.getClass().getSimpleName();
    @Override
    public void startPaymentBundle(String accountNumber, Date paymentDate, String currency) {
        System.out.println(
                name+".startPaymentBundle() accountNumber: " + accountNumber + 
                " paymentDate: " + paymentDate + 
                " currency: " + currency
        );
    }

    @Override
    public void payment(BigDecimal amount, String reference) {
        System.out.println(
                name+".payment() amount: " + amount + 
                " reference: " + reference 
        );
    }

    @Override
    public void endPaymentBundle() {
        System.out.println(name+".endPaymentBundle()" );
    }
    
}
