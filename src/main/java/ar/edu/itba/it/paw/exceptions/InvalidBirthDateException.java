package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class InvalidBirthDateException extends RuntimeException {
    public String getMessage(){
        return "Invalid birthdate.";
    }
}
