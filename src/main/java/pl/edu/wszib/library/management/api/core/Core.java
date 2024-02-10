package pl.edu.wszib.library.management.api.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.edu.wszib.library.management.api.auth.Authenticator;
import pl.edu.wszib.library.management.api.auth.IAuthenticator;
import pl.edu.wszib.library.management.api.gui.GUI;

@Component
public class Core implements ICore{

    @Autowired
    private IAuthenticator authenticator;

    @Override
    public void start() {

    }
}
