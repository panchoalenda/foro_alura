package es.foro.alura.foroalura.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class TratadorDeErrores {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarError400(MethodArgumentNotValidException e){
        List<DatosErrorValidacion> error = e.getFieldErrors().stream().map(DatosErrorValidacion::new).toList(); //Con esto veo la lista de errores.
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity tratarErrorOptional(NoSuchElementException e){
        String errorOp = "Optional Error";
        DatosErrorValidacion error = new DatosErrorValidacion(errorOp, e.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    private record DatosErrorValidacion(String Campo, String Error){
        public DatosErrorValidacion(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}
