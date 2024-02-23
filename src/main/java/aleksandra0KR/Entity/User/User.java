package aleksandra0KR.Entity.User;

public class User {
    private String Name;
    private String Surname;
    private String Address;
    private String PassportNumber;

    public boolean IsVerified(){
        if(Address != null && PassportNumber != null) return true;
        return false;
    }

    public void SetAddress(String address){
        Address = address;
    }

    public void SetPassport(String passportNumber){
        PassportNumber = passportNumber;
    }

    public void SetName(String name){
        if(name == null)  throw new IllegalArgumentException("Parameter 'Name' can't be null");
        Name = name;
    }

    public void SetSurname(String surname){
        if(surname == null)  throw new IllegalArgumentException("Parameter 'Surname' can't be null");
        Surname = surname;
    }
}
