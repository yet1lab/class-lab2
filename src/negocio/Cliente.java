//==========================================
//            CLASSE CLIENTE           
//==========================================
package negocio;

import java.io.Serializable;

public class Cliente extends LBox<Cliente> implements Serializable{    
	private String cpf;
	private String nome;
        private String email;
        private int idade;
        private String telefone;
        private static final long serialVersionUID = 1L;
        
        

// CONSTRUTOR DA CLASSE
	public Cliente (String nome, String cpf, String email, int idade, String telefone){
                this.setters = "nome email idade telefone";
		this.getters = "cpf nome email idade telefone";
		this.props = "cpf nome email idade telefone ";
            
                localSet("nome", nome);
                localSet("cpf", cpf);
                localSet("email", email);
                localSet("idade", idade);
                localSet("telefone", telefone);

                
	}
        
        @Override
        public String toString(){
            return nome +"/" +cpf + "/" + idade;
        }
       
}
