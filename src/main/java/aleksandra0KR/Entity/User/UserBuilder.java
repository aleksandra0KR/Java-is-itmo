package aleksandra0KR.Entity.User;

import aleksandra0KR.Entity.Bank.Bank;

public class UserBuilder {
    private User User = new User();

    public void CreateUser(String name, String surname){
        User.SetName(name);
        User.SetSurname(surname);
    }

    public void SetAddress(String address){
        User.SetAddress(address);
    }

    public void SetPassport(String passport){
        User.SetPassport(passport);
    }

    public User GetClient(){

        User result = this.User;
        this.Reset();
        return result;
    }

    public void Reset()
    {
        this.User = new User();
    }
}
