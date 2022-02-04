package org.epam.training;

public class Starter {

  public static void main(String[] args) {
    Users users = new Users();
    Accounts accounts = new Accounts();
    Transactions transactions = new Transactions();

    Users.setUserId();
    if (CheckUserId.checkUserId().contains(users.getUserId().toString())) {
      accounts.showAccount();
      System.out.println("Please choose your account currency");
      Accounts.setAccountCurrency();
      if (CheckCurrency.checkCurrency().contains(Accounts.getAccountCurrency())) {
        accounts.showChoosenAccount();
      } else {
        System.out.println("You don`t have account in this currency. Please create it");
        accounts.createAccount();
        if (CheckCurrency.checkCurrency().contains(Accounts.getAccountCurrency())) {
          accounts.showChoosenAccount();
        } else {
          System.out.println("Your already have account in this currency");

        }
      }
      System.out.println("Please choose transaction:");
      transactions.setTransaction();
      transactions.setAmount();
      //accounts.setAccountId();
      if (Transactions.getTransaction() == 1) {
        transactions.createTransaction();
        transactions.popUpBalance();
        accounts.showChoosenAccount();
      } else {
        if (Transactions.getTransaction() == 2) {
          transactions.createTransaction();
          transactions.withdrawalCash();
          accounts.showChoosenAccount();
        } else if (Transactions.getTransaction() == 3) {
          //accounts.setAccountId();
          transactions.createTransaction();
          System.exit(0);
        }
      }
    } else {
      Users.setName();
      Users.setAddress();
      users.addUser();
      accounts.createAccount();
      accounts.showAccount();
      transactions.setTransaction();
      transactions.setAmount();
      //accounts.setAccountId();
      if (Transactions.getTransaction() == 1) {
        transactions.createTransaction();
        transactions.popUpBalance();
        accounts.showChoosenAccount();
      } else {
        if (Transactions.getTransaction() == 2) {
          transactions.createTransaction();
          transactions.withdrawalCash();
          accounts.showChoosenAccount();
        }
      }
    }
  }
}


