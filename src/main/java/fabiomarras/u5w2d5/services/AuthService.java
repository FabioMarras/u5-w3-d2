package fabiomarras.u5w2d5.services;

import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.UnauthorizedException;
import fabiomarras.u5w2d5.payloads.UserLoginDTO;
import fabiomarras.u5w2d5.security.JWTTools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTTools jwtTools;

    public String authenticateUser(UserLoginDTO body){
        User user = userService.findByEmail(body.email());
        if(body.password().equals(user.getPassword())) {
            return jwtTools.createToken(user);
        } else {
            throw new UnauthorizedException("Credenziali non valide");
        }
    }
}
