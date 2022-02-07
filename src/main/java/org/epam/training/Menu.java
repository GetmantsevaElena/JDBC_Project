package org.epam.training;

public class Menu {

  public static void menuOptionals() {

    Accounts accounts = new Accounts();
    Transactions transactions = new Transactions();
    do {
      transactions.setTransaction();
      if (Transactions.getTransaction() == 1) {
        Accounts.setAccountCurrency();
        if (CheckCurrency.checkCurrency().contains(Accounts.getAccountCurrency())) {
          transactions.setAmount();
          transactions.popUpBalance();
          accounts.showAccountAfterTransaction();
          transactions.createTransactionExistedAccount();
        } else {
          System.out.println("You don`t have account in this currency. Please create it");
          Accounts.setDepositCurrency();
          Accounts.setBalance();
          accounts.createAccount();
          accounts.showCreatedAccount();
          transactions.createTransactionNewAccount();
        }
      } else {
        if (Transactions.getTransaction() == 2) {
          Accounts.setAccountCurrency();
          if (CheckCurrency.checkCurrency().contains(Accounts.getAccountCurrency())) {
            transactions.setAmount();
            transactions.withdrawalCash();
            accounts.showAccountAfterTransaction();
            transactions.createTransactionExistedAccount();
          } else {
            System.out.println("You don`t have account in this currency. Please create it");
            Accounts.setDepositCurrency();
            Accounts.setBalance();
            accounts.createAccount();
            accounts.showCreatedAccount();
            transactions.createTransactionNewAccount();
          }
        } else {
          if (Transactions.getTransaction() == 3) {
            if (CheckAccountsQuantity.checkAccountsQuantity() == 3) {
              System.out.println("Sorry, you not allowed to create more than 3 accounts");
            } else {
              Accounts.setDepositCurrency();
              if (CheckCurrency.checkCurrency().contains(Accounts.getDepositCurrency())) {
                System.out.println("You already have account in this currency");
                accounts.showCurrentAccount();
              } else {
                Accounts.setBalance();
                accounts.createAccount();
                transactions.createTransactionNewAccount();
                accounts.showCreatedAccount();
              }
            }
          } else if (Transactions.getTransaction() == 4) {
            System.exit(0);
          }
        }
      }
    }
    while (Transactions.getTransaction() != 4);
  }
}
