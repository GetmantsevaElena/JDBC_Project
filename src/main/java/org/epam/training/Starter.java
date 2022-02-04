package org.epam.training;

public class Starter {

  public static void main(String[] args) {
    //Almost works properly
    Users users = new Users();
    Transactions transactions = new Transactions();
    CheckCurrency checkCurrency = new CheckCurrency();
    Accounts accounts = new Accounts();

    int userId = users.UserId();
    users.setUserId(userId);
    if (CheckUserId.checkUserId().contains(users.getUserId().toString())) {
      //if(CheckCurrency.checkCurrency().contains())
      System.out.println("You already have an accounts. Please choose currency");
      //accounts.getAccountCurrency();
      accounts.showAccount();
//      System.out.println("Please choose operation:");
//      transactions.getTransaction();
    } else {
      String name = users.Name();
      users.setName(name);
      String address = users.Address();
      users.setAddress(address);
      Users.addUser();
      Accounts.createAccount();
      accounts.showAccount();
      transactions.getTransaction();
    }




    //Almost works properly
    //Accounts.createAccount();
    //Not realized
    //Transactions.showAccount();
    //CheckUserId();

  }
}


