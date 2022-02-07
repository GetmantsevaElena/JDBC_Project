package org.epam.training;

public class NewUser {

  Users users = new Users();
  Accounts accounts = new Accounts();
  Transactions transactions = new Transactions();

  public void newUserActions() {
    Users.setName();
    Users.setAddress();
    users.addUser();
    System.out.println("You don`t have any accounts, please create it");
    Accounts.setDepositCurrency();
    Accounts.setBalance();
    accounts.createAccount();
    transactions.createTransactionNewAccount();
    accounts.showCreatedAccount();
    Menu.menuOptionals();
  }
}