package org.epam.training;

public class Starter {

  public static void main(String[] args) {
    //Almost works properly
    Users users = new Users();
    Accounts accounts = new Accounts();
    Transactions transactions = new Transactions();

    Users.setUserId();
    if (CheckUserId.checkUserId().contains(users.getUserId().toString())) {
      accounts.showAccount();
      System.out.println("Please choose your account currency");
      accounts.setAccountCurrency();
      accounts.showChoosenAccount();
//      In progress
//      if (CheckCurrency.checkCurrency().contains(accounts.getAccountCurrency())) {
//        accounts.showAccount();
//      } else {
//        System.out.println("You don`t have account in this currency. Please create it");
//        accounts.createAccount();
//        if (CheckCurrency.checkCurrency().contains(accounts.getAccountCurrency())) {
//          System.out.println("You already have an account in this currency");
//        } else {
//          accounts.showAccount();
//        }
//      }

      System.out.println("Please choose transaction:");
      transactions.setTransaction();
      if (Transactions.getTransaction() == 1) {
        transactions.popUpBalance();
        accounts.showChoosenAccount();
      } else {
        if (Transactions.getTransaction() == 2) {
          transactions.withdrawalCash();
          accounts.showChoosenAccount();
        }
      }

    } else {
      Users.setName();
      Users.setAddress();
      users.addUser();
      accounts.createAccount();
      accounts.showAccount();
      accounts.setAccountId();
      transactions.setTransaction();
      System.out.println("Please choose transaction:");
      transactions.setTransaction();
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

    //Not realized
    //Transactions.showAccount();
    //CheckUserId();

  }
}


