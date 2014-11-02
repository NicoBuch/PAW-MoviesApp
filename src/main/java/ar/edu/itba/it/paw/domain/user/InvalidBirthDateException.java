package ar.edu.itba.it.paw.domain.user;

@SuppressWarnings("serial")
public class InvalidBirthDateException extends Exception {
    public String getMessage(){
        return "Invalid birthdate.";
    }
}
