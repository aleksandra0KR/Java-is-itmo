package aleksandra0KR.Entity.User;

import aleksandra0KR.Model.Observer.ObserverUser;
import lombok.Getter;

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

    public void Update(String message) {
        _messages.add(message);
    }
}
