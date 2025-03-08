//==========================================
	package dados;
	import negocio.my;
	import negocio.Carro;
	import dados.IRepoCarro;
	import java.util.ArrayList;
	import negocio.PesquisaCarros;
//==========================================
public class RepoCarro extends PesquisaCarros implements IRepoCarro {
	public ArrayList<Carro> getFrota() { return this.carros; }
	public int getContagem() { return this.carros.size(); }

	public void delCarro(String placa) {
		Carro carro = this.getCarro(placa);
		this.carros.remove(carro);
	}

	public Carro getCarro(String placa) {
		for (Carro carro : carros) {
			if (carro.placa.equals(placa)) { return carro; }
		}
		return null;
	}

	public void addCarro(String placa, String marca, String modelo, int ano) {
		if (!haveCarro(placa)) {
			carros.add(new Carro(marca, modelo, ano, placa));
		}
	}


/*
    public void getAno(int ano) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.ano == ano) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    public void getPlaca(String placa) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.placa.equals(placa)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    public void getMarca(String marca) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.marca.equals(marca)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    public void getModelo(String modelo) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.modelo.equals(modelo)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }
 
    public void getEstado(String status) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.estado.equals(status)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }
*/
}
