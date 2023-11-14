package fabiomarras.u5w2d5.services;

import fabiomarras.u5w2d5.Enum.Role;
import fabiomarras.u5w2d5.entities.Dispositivo;
import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.NotFoundException;
import fabiomarras.u5w2d5.exceptions.SameIdException;
import fabiomarras.u5w2d5.payloads.NewUserRequestDTO;
import fabiomarras.u5w2d5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class UserService {
    //GET /user
    //GET /user/id
    //POST /user - crea uno user
    //PUT /user/id - modifica uno user
    //DELETE /user/id - cancella uno user

    @Autowired
    private UserRepository userRepository;


    //GET /user per avere una la lista degli user
        public Page<User> getAllUser(int page, int size, String orderBy){
            Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
            return userRepository.findAll(pageable);
        }

    //GET /user/id ricerca per specifico id
    public User findById(int id){
            return userRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(()-> new NotFoundException(email));
    }


    //PUT /user/id - modifica uno user specifico
    public User findByIdAndUpdate(int id,@RequestBody NewUserRequestDTO body){
            User user = this.findById(id);

            user.setUsername(body.username());
            user.setName(body.name());
            user.setLastName(body.lastName());
            user.setEmail(body.email());
            user.setAvatar(body.avatar());
            user.setDispositivo(body.dispositivo());
            return userRepository.save(user);
    }

    //DELETE /user/id - cancella uno user specifido dal suo id
    public void findByIdAndDelete(int id){
            User user = this.findById(id);{
                userRepository.delete(user);
        }
    }

}

