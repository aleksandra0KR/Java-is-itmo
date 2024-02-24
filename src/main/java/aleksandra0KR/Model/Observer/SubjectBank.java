package aleksandra0KR.Model.Observer;

public interface SubjectBank {
    // Присоединяет наблюдателя к издателю.
    void Attach(ObserverUser observer);

    // Отсоединяет наблюдателя от издателя.
    void Detach(ObserverUser observer);

    // Уведомляет всех наблюдателей о событии.
    void Notify(String updates);
}
