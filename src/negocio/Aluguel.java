//==========================================
//        ALUGUEL DE CARROS
//==========================================
package negocio;

import java.io.Serializable;

public class Aluguel extends LBox<Aluguel> implements Serializable{
	int dias;
	Carro carro;
	double valor;
	Cliente cliente;
        private static final long serialVersionUID = 1L;
        
	public Aluguel(Cliente cliente, Carro carro, int dias){
		localSet("dias", dias);
		localSet("carro", carro);
		localSet("cliente", cliente);

		this.getters = "dias carro valor cliente";
		this.setters = "dias cliente valor";
		this.props = "dias carro valor cliente";
	}
        @Override
        public String toString(){
            return carro.get("marca") + " " + carro.get("modelo") + " Esta alugado a " + cliente.get("nome") + " por " + dias + " dias."; 
        }
}
