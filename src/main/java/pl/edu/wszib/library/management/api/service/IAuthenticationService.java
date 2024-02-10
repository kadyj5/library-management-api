package pl.edu.wszib.library.management.api.service;

public interface IAuthenticationService {
    void login(String login, String password);
    void logout();
}
