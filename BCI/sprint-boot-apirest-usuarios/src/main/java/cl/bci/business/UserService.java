package cl.bci.business;

import cl.bci.expose.response.UserResponse;
import cl.bci.model.User;

public interface UserService {

    UserResponse create(User user);

}
