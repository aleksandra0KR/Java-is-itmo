package ru.aleksandra0KR.bank.Entity.User;

import ru.aleksandra0KR.bank.Exceptions.NullUserNameException;
import ru.aleksandra0KR.bank.Exceptions.NullUserSurnameException;
import ru.aleksandra0KR.bank.Model.Observer.ObserverUser;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

/**
 * Class for the user
 * @author Aleksandra0KR
 * @version 1.0
 */
public class User implements ObserverUser {
    @Getter
    private String Name;  // The name of the user
    @Getter
    private String Surname; // The surname of the user
    private String Address; // The address of the user
    private String PassportNumber;  // The passport number of the user
    @Getter
    private List<String> _messages = new ArrayList<>(); // List of messages for the user

    @Getter
    @Setter
    private UUID ID; // The unique identifier for the user

    /**
     Checks if the user is verified based on address and passport information.
     @return true if both address and passport number are not null, false otherwise.
     */
    public boolean IsVerified(){
        if(Address != null && PassportNumber != null) return true;
        return false;
    }

    /**
     Sets the address for the user.
     @param address The address to set.
     */
    public void SetAddress(String address){
        Address = address;
    }

    /**
     Sets the passport number for the user.
     @param passportNumber The passport number to set.
     */
    public void SetPassport(String passportNumber){
        PassportNumber = passportNumber;
    }

    /**
     Sets the name for the user.
     @throws NullUserNameException customized exception if name is null.
     @param name The name to set.
     */
    public void SetName(String name){
        if(name == null)  throw new NullUserNameException();
        Name = name;
    }

    /**
     Sets the surname for the user.
     @throws NullUserSurnameException customized exception if surname is null.
     @param surname The surname to set.
     */
    public void SetSurname(String surname){
        if(surname == null)  throw new NullUserSurnameException();
        Surname = surname;
    }

    /**
     Updates the user with a new message.
     @param message The message to add to the user's messages list.
     */
    public void Update(String message) {
        _messages.add(message);
    }
}
