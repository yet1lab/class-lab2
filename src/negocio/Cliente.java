//==========================================
	package negocio;
//==========================================
public class Cliente {    
    public String cpf;
    public String nome;

// CONSTRUTOR DA CLASSE
    public Cliente (String cpf, String nome){
        this.cpf = cpf;
        this.nome = nome;
    }

// SET GERAL DA CLASSE
    public void setDados(String cpf, String nome){
        this.nome = nome;
        this.cpf = cpf;
    }
}
