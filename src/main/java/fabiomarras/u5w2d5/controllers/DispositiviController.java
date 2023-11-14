package fabiomarras.u5w2d5.controllers;

import fabiomarras.u5w2d5.entities.Dispositivo;
import fabiomarras.u5w2d5.exceptions.BadRequestException;
import fabiomarras.u5w2d5.payloads.NewDispositivoRequestDTO;
import fabiomarras.u5w2d5.services.DispositivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/dispositivi")
public class DispositiviController {

    @Autowired
    private DispositivoService dispositiviService;

    @GetMapping("")
    public Page<Dispositivo> getDispositivo(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy){
        return dispositiviService.getDispositivo(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public Dispositivo findById(@PathVariable int id){
        return dispositiviService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Dispositivo saveNewDispositivo(@RequestBody @Validated NewDispositivoRequestDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return dispositiviService.save(body);
        }
    }

    @PutMapping("/{id}")
    public Dispositivo findByIdAndUpdate(@PathVariable int id, @RequestBody Dispositivo body,@RequestParam("userId") int userId){
        return dispositiviService.findByIdAndUpdate(id, body, userId);
    }
    @DeleteMapping("/{id}")
    public void findByIdAndDelete(@PathVariable int id){
        dispositiviService.findByIdAndDelete(id);
    }
}
