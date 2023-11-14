package fabiomarras.u5w2d5.services;

import fabiomarras.u5w2d5.Enum.Role;
import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.UnauthorizedException;
import fabiomarras.u5w2d5.payloads.NewUserRequestDTO;
import fabiomarras.u5w2d5.payloads.UserLoginDTO;
import fabiomarras.u5w2d5.repositories.UserRepository;
import fabiomarras.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder bcrypt;

    public String authenticateUser(UserLoginDTO body){
        User user = userService.findByEmail(body.email());
        if(bcrypt.matches(body.password(), user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }

    //POST /user - crea uno user
    public User save(@RequestBody NewUserRequestDTO body){
        User newUser = new User();
        newUser.setUsername(body.username());
        newUser.setName(body.name());
        newUser.setLastName(body.lastName());
        newUser.setEmail(body.email());
        newUser.setPassword(bcrypt.encode(body.password()));
        newUser.setRole(Role.USER);
        User saveUser = userRepository.save(newUser);
        return saveUser;
    }
}
