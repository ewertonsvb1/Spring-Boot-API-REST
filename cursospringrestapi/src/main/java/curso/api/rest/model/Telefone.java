package curso.api.rest.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Telefone {

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO) // AJUDA PARA GERAR OS IDS em cada cadastro
		private Long id;
		
		private String numero;
		
		@JsonIgnore // evitar que o Json fique em loop
		@org.hibernate.annotations.ForeignKey(name = "usuario_id") // DEFINE quem vai ser a chave estrangeira de telefone na tabela de usuario
		@ManyToOne(optional = false)// n:1   - Varios telefones para um usuario    --  optional - caso perca associacao no codigo, vai restringir no bacno de dados
		// para cadastrar o telefone, tem que ter o pai(usuario) -  isso evita de algum telefone fica perdido
		private Usuario usuario;

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getNumero() {
			return numero;
		}

		public void setNumero(String numero) {
			this.numero = numero;
		}

		public Usuario getUsuario() {
			return usuario;
		}

		public void setUsuario(Usuario usuario) {
			this.usuario = usuario;
		}
		
		
}

