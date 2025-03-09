//==========================================
//        ALUGUEL DE CARROS
//==========================================
package negocio;

public class Aluguel extends LBox<Aluguel>{
	int dias;
	Carro carro;
	double valor;
	Cliente cliente;

	public Aluguel(Cliente cliente, Carro carro, int dias){
		localSet("dias", dias);
		localSet("carro", carro);
		localSet("cliente", cliente);

		this.getters = "dias carro valor cliente";
		this.setters = "dias cliente valor";
		this.props = "dias carro valor cliente";
	}
}
