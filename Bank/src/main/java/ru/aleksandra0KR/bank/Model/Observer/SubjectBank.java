package ru.aleksandra0KR.bank.Model.Observer;

/**
 * Interface for subject user
 * @author Aleksandra0KR
 * @version 1.0
 */
public interface SubjectBank {
    void Attach(ObserverUser observer);
    void Detach(ObserverUser observer);
    void Notify(String updates);
}
