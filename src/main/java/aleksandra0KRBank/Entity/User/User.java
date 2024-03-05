package aleksandra0KRBank.Entity.User;

import aleksandra0KRBank.Exceptions.NullUserSurnameException;
import aleksandra0KRBank.Exceptions.NullUserNameException;
import aleksandra0KRBank.Model.Observer.ObserverUser;
import lombok.Getter;
import lombok.Setter;

import java.util.*;


public class User implements ObserverUser {
    @Getter
    private String Name;
    @Getter
    private String Surname;
    private String Address;
    private String PassportNumber;
    @Getter
    private List<String> _messages = new ArrayList<>();

    @Getter
    @Setter
    private UUID ID;

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
        if(name == null)  throw new NullUserNameException();
        Name = name;
    }

    public void SetSurname(String surname){
        if(surname == null)  throw new NullUserSurnameException();
        Surname = surname;
    }

    public void Update(String message) {
        _messages.add(message);
    }
}
