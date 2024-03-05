package aleksandra0KRBank.Model.Observer;

public interface SubjectBank {
    void Attach(ObserverUser observer);
    void Detach(ObserverUser observer);
    void Notify(String updates);
}
