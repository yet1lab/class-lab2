//==========================================
	package negocio;
	import negocio.my;
	import negocio.Carro;
	import java.util.ArrayList;
//==========================================
public abstract class PesquisaCarros {
	protected ArrayList<Carro> carros;
	protected ArrayList<Carro> pesquisa;

	public ArrayList<Carro> getPesquisa() {
		ArrayList<Carro> temp = this.pesquisa; 
		this.pesquisa = this.carros; 
		return temp;
	}

	public boolean haveCarro(String placa) {
		return this.carros.stream().anyMatch(x -> x.placa.equals(placa));
	}

	public void getAno(int ano){
		pesquisa = my.filter(this.pesquisa, x -> x.ano, ano);
	}

// my.find()
// my.get(array, "prop1 prop2")
// my.set(array, "prop1 prop2")

/*
    public abstract void getPlaca(String placa);
    public abstract void getMarca(String marca);
    public abstract void getModelo(String modelo);
    public abstract void getEstado(String status);
*/
}
