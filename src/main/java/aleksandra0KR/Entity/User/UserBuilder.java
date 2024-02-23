package aleksandra0KR.Entity.User;

public class UserBuilder {
    private User user = new User();

    public void CreateUser(String name, String surname){
        user.SetName(name);
        user.SetSurname(surname);
    }

    public void SetAddress(String address){
        user.SetAddress(address);
    }

    public void SetPassport(String passport){
        user.SetPassport(passport);
    }

    public User GetClient(){

        User result = this.user;
        this.Reset();
        return result;
    }

    public void Reset()
    {
        this.user = new User();
    }
}
