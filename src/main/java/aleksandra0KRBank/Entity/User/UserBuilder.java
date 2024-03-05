package aleksandra0KRBank.Entity.User;

import java.util.UUID;

public class UserBuilder {
    private User User = new User();

    public void CreateUser(String name, String surname){
        User.SetName(name);
        User.SetSurname(surname);
        User.setID(UUID.randomUUID());
    }

    public void SetAddress(String address){
        User.SetAddress(address);
    }

    public void SetPassport(String passport){
        User.SetPassport(passport);
    }

    public User GetUser(){

        User result = this.User;
        this.Reset();
        return result;
    }

    public void Reset()
    {
        this.User = new User();
    }
}
