//==========================================
//            CLASSE CLIENTE           
//==========================================
package negocio;

public class Cliente extends LBox<Cliente>{    
	private String cpf;
	private String nome;

// CONSTRUTOR DA CLASSE
	public Cliente (String cpf, String nome){
		this.cpf = cpf;
		this.nome = nome;

		this.setters = "nome";
		this.getters = "cpf nome";
		this.props = "cpf nome";
	}
}
