package aleksandra0KRBank.Model.Transaction;

import java.util.UUID;

public interface Transaction {
    void execute();
    void cancel();
    void printInfo();
}
