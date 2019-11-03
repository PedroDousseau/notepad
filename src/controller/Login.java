package controller;

import model.User;

public class Login {
  
  private static User logged;

  public static boolean authenticate(String user, 
      String password) {
    for(User u : SignUp.getCadastrados()) {
      if(u.getEmail().equals(user)) {
        if(u.getSenha().equals(password)) {
          logged = u;
          return true;
        } else {
          return false;
        }
      }
    }
    return false;
  }

}
