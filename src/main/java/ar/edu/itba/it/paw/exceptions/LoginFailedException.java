package ar.edu.itba.it.paw.exceptions;

@SuppressWarnings("serial")
public class LoginFailedException extends RuntimeException {
    public String getMessage(){
        return "Login failed.";
    }
}
