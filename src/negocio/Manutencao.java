//========================================================
//                 CLASSE MANUTENCAO
//========================================================
package negocio;

import java.io.Serializable;

public class Manutencao extends LBox<Manutencao> implements Serializable{    
	Carro carro;            // O carro que foi para manutenção
	double valor;           // Valor total do conserto
        private static final long serialVersionUID = 1L;
        
	public Manutencao(Carro carro, int nivelDeDano){
		localSet("carro", carro);
		carro.set("nivelDeDano", 0);
                carro.emManutencao();
		localSet("valor", gerarMulta(nivelDeDano));

		this.getters = "carro valor";
		this.setters = "carro valor";
		this.props = "carro valor";
                
	}

	public String toString(){ return carro +"/"+valor; }

	public double gerarMulta(int nivel) {
		return switch (nivel) {
			case 0 -> 0;
			case 1 -> 100.00;
			case 2 -> 200.00;
			case 3 -> 300.00;
			case 4 -> 400.00;
			default -> 500.00;
		};
	}
}
