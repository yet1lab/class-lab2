//========================================================
//                 CLASSE MANUTENCAO
//========================================================
package negocio;

public class Manutencao extends LBox<Manutencao>{    
	Carro carro;            // O carro que foi para manutenção
	double valor;           // Valor total do conserto
	static int nextId = 1; 

	public void Manuntencao(Carro carro, int nivelDeDano){
		localSet("carro", carro);
		carro.set("nivelDeDano", 0);
		
		localSet("valor", gerarMulta(nivelDeDano));
		localSet("id", nextId++);

		this.getters = "carro valor id nextId";
		this.setters = "carro valor id nextId";
		this.props = "carro valor id nextId";
	}

	public String ToString(){
		return "O cliente está devendo: "+ valor;
	}

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
