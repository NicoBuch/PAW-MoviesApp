package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class LoginFailedException extends Exception {
    public String getMessage(){
        return "Login failed.";
    }
}
