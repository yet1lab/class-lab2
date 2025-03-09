//==========================================
//            CLASSE CARRO
//==========================================
package negocio;

public class Carro extends LBox<Carro> {
	private int ano;
	private int dano;
	private int valor;
	private String placa;
	private String marca;
	private String modelo;
	private String estado;
	private boolean disponivel;

// CONSTRUTOR DA CLASSE
	public Carro(String marca, String modelo, int ano, String placa) {
		localSet("dano",0);
		localSet("ano", ano);
		localSet("placa", placa);
		localSet("marca", marca);
		localSet("modelo", modelo);
		localSet("disponivel", true);
		localSet("estado","disponivel");

		this.getters = "ano placa marca modelo valor estado disponivel dano";
		this.setters = "ano placa marca modelo valor estado disponivel dano";
		this.props = "ano placa marca modelo valor estado disponivel dano";
	}
	
// PROCESSOS DE ALUGUEL
	public String toString(){ return marca+"/"+modelo+"/"+ano+"/"+placa+"/"+estado; }
	public void setDisponivel(){ localSet("disponivel", true); }
	public void setIndisponivel(){ localSet("disponivel", false); }

	public void alugaCarro(){ 
		localSet("disponivel", false);
		localSet("estado", "Alugado");
	}
	public void devolveCarro(String estado){
		localSet("estado", estado);
		localSet("disponivel", true);
	}
}
