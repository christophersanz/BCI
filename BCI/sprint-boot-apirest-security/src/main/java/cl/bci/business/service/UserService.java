package cl.bci.business.service;

import cl.bci.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User create(User user);

}
