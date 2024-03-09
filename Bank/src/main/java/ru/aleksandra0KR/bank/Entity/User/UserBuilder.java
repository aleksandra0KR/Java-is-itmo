package ru.aleksandra0KR.bank.Entity.User;

import java.util.UUID;

/**
 * Class for building a user
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public class UserBuilder {

  private User User = new User();

  /**
   * Creates a new user with the provided name and surname.
   *
   * @param name    The name of the user.
   * @param surname The surname of the user.
   */
  public void CreateUser(String name, String surname) {
    User.SetName(name);
    User.SetSurname(surname);
    User.setID(UUID.randomUUID());
  }

  /**
   * Sets the address for the user.
   *
   * @param address The address to set.
   */
  public void SetAddress(String address) {
    User.setAddress(address);
  }

  /**
   * Sets the passport number for the user.
   *
   * @param passport The passport number to set.
   */
  public void SetPassport(String passport) {
    User.setPassportNumber(passport);
  }

  /**
   * Returns the built user.
   *
   * @return The built user.
   */
  public User GetUser() {

    User result = this.User;
    this.Reset();
    return result;
  }

  /**
   * Resets the builder to its initial state.
   */
  public void Reset() {
    User = new User();
  }
}
