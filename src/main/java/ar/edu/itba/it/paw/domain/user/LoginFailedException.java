package ar.edu.itba.it.paw.domain.user;

@SuppressWarnings("serial")
public class LoginFailedException extends Exception {
    public String getMessage(){
        return "Login failed.";
    }
}
