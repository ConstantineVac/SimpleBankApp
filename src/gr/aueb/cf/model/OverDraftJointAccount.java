package gr.aueb.cf.model;

import gr.aueb.cf.exceptions.InsufficientBalanceException;
import gr.aueb.cf.exceptions.NegativeAmountException;
import gr.aueb.cf.exceptions.SsnNotValidException;

public class OverDraftJointAccount extends JointAccount {

    public OverDraftJointAccount() {}

    public OverDraftJointAccount(User holder, String iban, double balance) {
        super(holder, iban, balance);
    }

    @Override
    public void withdraw(double amount, String ssn)
            throws SsnNotValidException, NegativeAmountException {
        try {
            if (amount < 0) throw new NegativeAmountException(amount);
            if (!isSsnValid(ssn)) throw new SsnNotValidException(ssn);                              // Inherited by Account.

            setBalance(getBalance() - amount);                                                      // It's private so we use set and get.
        } catch (SsnNotValidException | NegativeAmountException e) {
            System.out.println("Error: withdrawal");
            throw e;
        }
    }

}

