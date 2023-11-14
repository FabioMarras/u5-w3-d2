package fabiomarras.u5w2d5.exceptions;

import fabiomarras.u5w2d5.payloads.NewDispositivoRequestDTO;
import fabiomarras.u5w2d5.payloads.NewUserRequestDTO;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestControllerAdvice
public class ExceptionsHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsPayload handleNotFound(NotFoundException e) {
        return new ErrorsPayload(e.getMessage(), 404, new Date());
    }
    @ExceptionHandler(SameIdException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)  // 404
    public ErrorsPayload handleNotFound(SameIdException e) {
        return new ErrorsPayload(e.getMessage(), 400, new Date());
    }

    /*@ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public NewDispositivoRequestDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new NewDispositivoRequestDTO(e.getMessage(), null, errorsList);
        } else {
            return new NewDispositivoRequestDTO(e.getMessage(), null, new ArrayList<>());
        }
    }*/

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST) //400
    public NewUserRequestDTO handleBadRequest(BadRequestException e) {
        if (e.getErrorsList() != null) {
            List<String> errorsList = e.getErrorsList().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).toList();
            return new NewUserRequestDTO(e.getMessage(), null, null,null, null, null, null, null, errorsList);
        } else {
            return new NewUserRequestDTO(e.getMessage(), null, null, null, null, null, null, null, new ArrayList<>());
        }
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED) // 401
    public ErrorsPayload handleUnauthorized(UnauthorizedException e){
        return new ErrorsPayload(e.getMessage(), 401, new Date());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN) // 403
    public ErrorsPayload handleAccessDenied(AccessDeniedException e){
        return new ErrorsPayload(e.getMessage(), 403, new Date());
    }


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    public ErrorsPayload handleGeneric(Exception e) {
        e.printStackTrace();
        return new ErrorsPayload("PROBLEMI LATO SERVER", 500, new Date());
    }
}
