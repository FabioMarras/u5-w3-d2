package fabiomarras.u5w2d5.services;

import fabiomarras.u5w2d5.Enum.StatusDispositivi;
import fabiomarras.u5w2d5.entities.Dispositivo;
import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.NotFoundException;
import fabiomarras.u5w2d5.payloads.NewDispositivoRequestDTO;
import fabiomarras.u5w2d5.repositories.DispositivoRepository;
import fabiomarras.u5w2d5.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Service
public class DispositivoService {

    //GET /dispositivi
    //GET /dispositivi/id
    //POST /dispositivi - crea un dispositivi
    //PUT /dispositivi/id - modifica un dispositivi
    //DELETE /dispositivi/id - cancella un dispositivi

    @Autowired DispositivoRepository dispositivoRepository ;
    @Autowired
    private UserRepository userRepository;

    //GET /dispositivi
    public Page<Dispositivo> getDispositivo(int page, int size, String orderBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(orderBy));
        return dispositivoRepository.findAll(pageable);
    }

    //GET /dispositivi/id
    public Dispositivo findById(int id){
        return dispositivoRepository.findById(id).orElseThrow(()-> new NotFoundException(id));
    }

    //POST /dispositivi - crea un dispositivi
    public Dispositivo save(@RequestBody NewDispositivoRequestDTO body) throws IOException {
        Dispositivo newDispositivo = new Dispositivo();
        newDispositivo.setType(body.type());
        newDispositivo.setStatusDispositivi((StatusDispositivi) body.statusDispositivi());

        Dispositivo saveDispositivo = dispositivoRepository.save(newDispositivo);
        return saveDispositivo;
    }

    //PUT /dispositivi/id - modifica un dispositivi
    public Dispositivo findByIdAndUpdate(int id, Dispositivo body, int userId){
        Dispositivo newDispositivo = dispositivoRepository.findById(id).orElseThrow(() -> new NotFoundException(id));

        newDispositivo.setType(body.getType());
        newDispositivo.setStatusDispositivi(body.getStatusDispositivi());

        User user = userRepository.findById(userId).orElseThrow(()-> new NotFoundException(userId));
        newDispositivo.setUser(user);

        return dispositivoRepository.save(newDispositivo);
    }

    //DELETE /dispositivi/id - cancella un dispositivi
    public void findByIdAndDelete(int id){
        Dispositivo newDispositivo = this.findById(id);
        dispositivoRepository.delete(newDispositivo);
    }

}
