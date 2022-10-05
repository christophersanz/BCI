package cl.bci.expose;

import cl.bci.model.User;
import cl.bci.business.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class UsuarioController {

    @Autowired
    private UserService service;

    @PostMapping("/create")
    public User create(@RequestBody User user){
        return service.create(user);
    }

}
