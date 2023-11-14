package fabiomarras.u5w2d5.controllers;

import fabiomarras.u5w2d5.entities.User;
import fabiomarras.u5w2d5.exceptions.BadRequestException;
import fabiomarras.u5w2d5.payloads.NewUserRequestDTO;
import fabiomarras.u5w2d5.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("")
    public Page<User> getAllUser(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size, @RequestParam(defaultValue = "id") String orderBy){
        return userService.getAllUser(page, size, orderBy);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable int id){
        return userService.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('ADMIN')")
    public User saveNewUser(@RequestBody @Validated NewUserRequestDTO body, BindingResult validation) throws IOException{
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.save(body);
        }
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public User findByIdAndUpdate(@PathVariable int id, @RequestBody @Validated NewUserRequestDTO body, BindingResult validation) throws IOException {
        if (validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        } else {
            return userService.findByIdAndUpdate(id, body);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void findByIdAndDelete(@PathVariable int id){
        userService.findByIdAndDelete(id);
    }

    @GetMapping("/me")
    public UserDetails getMyProfile(@AuthenticationPrincipal User currentUser){
        return currentUser;
    }

    @PutMapping("/me")
    public UserDetails getProfile(@AuthenticationPrincipal User currentUser, @RequestBody NewUserRequestDTO body){
        return userService.findByIdAndUpdate(currentUser.getId(), body);
    }

    @DeleteMapping("/me")
    @ResponseStatus(HttpStatus.NO_CONTENT) // <-- 204 NO CONTENT
    public void getProfile(@AuthenticationPrincipal User currentUser){
        userService.findByIdAndDelete(currentUser.getId());
    };
}
