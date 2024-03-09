package ru.aleksandra0KR.bank.Model.Observer;

import ru.aleksandra0KR.bank.Entity.User.User;

/**
 * Interface for subject user
 *
 * @author Aleksandra0KR
 * @version 1.0
 */
public interface SubjectBank {

  void Attach(User observer);

  void Detach(User observer);

  void Notify(String updates);
}
