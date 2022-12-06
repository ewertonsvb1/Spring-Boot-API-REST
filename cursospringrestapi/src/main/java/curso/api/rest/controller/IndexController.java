package curso.api.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.api.rest.model.Usuario;
import curso.api.rest.repository.UsuarioRepository;

@RequestMapping(value = "/usuario")
@RestController // ativar o servico RESTFULL
public class IndexController {

	@Autowired
	private UsuarioRepository usuarioRepository;

	// exemplo de outros metodos pela urls - RELATORIO
	@GetMapping(value = "/{id}/codigovenda/{venda}", produces = "application/json")
	public ResponseEntity<Usuario> relatorio(@PathVariable(value = "id") Long id,
			@PathVariable(value = "venda") Long venda) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		// o retorno seria um relatorio
		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	// se der / usuario / id, busca um
	@GetMapping(value = "/{id}", produces = "application/json") // para ser possivel acessar pela URL

	public ResponseEntity<Usuario> init(@PathVariable(value = "id") Long id) {

		Optional<Usuario> usuario = usuarioRepository.findById(id);

		return new ResponseEntity<Usuario>(usuario.get(), HttpStatus.OK);
	}

	// se der / usarua / vai buscar todos
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<List<Usuario>> usuario() {
		List<Usuario> list = (java.util.List<Usuario>) usuarioRepository.findAll();

		return new ResponseEntity<List<Usuario>>(list, HttpStatus.OK);
	}

	@PostMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {

		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
			// vai "amarrar" os telefones ao "pai - usuario"
		}
		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	@PutMapping(value = "/", produces = "application/json")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		
		
		for (int pos = 0; pos < usuario.getTelefones().size(); pos++) {
			usuario.getTelefones().get(pos).setUsuario(usuario);
			// vai "amarrar" os telefones ao "pai - usuario"
		}

		Usuario usuarioSalvo = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}", produces = "application/json") // para ser possivel acessar pela URL
	public String deletar(@PathVariable(value = "id") Long id) {

		usuarioRepository.deleteById(id);

		return "DELETADO";
	}

}
