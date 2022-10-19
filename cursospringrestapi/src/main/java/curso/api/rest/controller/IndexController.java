package curso.api.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping(value = "/usuario")
@RestController // ativar o servico RESTFULL
public class IndexController {
	
	@GetMapping(value = "/", produces = "application/json") // para ser possivel acessar pela URL
	public ResponseEntity init() {
		return new ResponseEntity("Olá USUARIO REST", HttpStatus.OK);
	}
		
}


