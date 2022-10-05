package cl.bci.expose;

import cl.bci.business.UserService;
import cl.bci.expose.response.UserResponse;
import cl.bci.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/user")
public class UsuarioController {

    @Autowired
    private UserService service;

    @PostMapping
    public UserResponse create(@RequestBody User user){
        return service.create(user);
    }

}
