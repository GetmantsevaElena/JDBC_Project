package org.epam.training;

public class Starter {

  public static void main(String[] args) {
    //Almost works properly
    Users users = new Users();

    Accounts accounts = new Accounts();

    Users.setUserId();
    if (CheckUserId.checkUserId().contains(users.getUserId().toString())) {
      accounts.showAccount();
      System.out.println("Please choose your account currency");
      accounts.getAccountCurrency();
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

//      System.out.println("Please choose operation:");
//      transactions.getTransaction();
    } else {
      Users.setName();
      Users.setAddress();
      users.addUser();
      accounts.createAccount();
      accounts.showAccount();
//      transactions.getTransaction();
    }

    //Not realized
    //Transactions.showAccount();
    //CheckUserId();

  }
}


