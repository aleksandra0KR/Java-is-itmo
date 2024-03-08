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
@Getter
public class User implements ObserverUser {

    private String Name;  // The name of the user

    private String Surname; // The surname of the user

    @Setter
    private String Address; // The address of the user

    @Setter
    private String PassportNumber;  // The passport number of the user

    private final List<String> _messages = new ArrayList<>(); // List of messages for the user

    @Setter
    private UUID ID; // The unique identifier for the user

    /**
     Checks if the user is verified based on address and passport information.
     @return true if both address and passport number is not null, false otherwise.
     */
    public boolean IsVerified(){
        return Address != null && PassportNumber != null;
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
