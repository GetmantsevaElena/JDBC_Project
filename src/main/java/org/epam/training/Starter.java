package org.epam.training;

public class Starter {

  public static void main(String[] args) {
    Users users = new Users();
    NewUser newUser = new NewUser();
    ExistedUser existedUser = new ExistedUser();
    Users.setUserId();
    if (CheckUserId.checkUserId().contains(users.getUserId().toString()))
      existedUser.existedUserActions();
    else
      newUser.newUserActions();
  }
}


