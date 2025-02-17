//==========================================
	package dados;
	import java.util.ArrayList;
	import negocio.Carro;
	import dados.IRepoCarro;
	import negocio.PesquisaCarros;
//==========================================
public class RepoCarro extends PesquisaCarros implements IRepoCarro {
    public RepoCarro(Carro[] carros) {
        super(carros);
    }

    public boolean haveCarro(String placa) {
        for (Carro carro : carros) {
            if (carro.placa.equals(placa)) {
                return true;
            }
        }
        return false;
    }

    public void delCarro(String placa) {
        ArrayList<Carro> carrosList = new ArrayList<>();
        for (Carro carro : carros) {
            if (!carro.placa.equals(placa)) {
                carrosList.add(carro);
            }
        }
        carros = carrosList.toArray(new Carro[0]);
    }

    public void addCarro(String placa, String marca, String modelo, int ano) {
        if (!haveCarro(placa)) {
            Carro novoCarro = new Carro(marca, modelo, ano, placa);
            ArrayList<Carro> carrosList = new ArrayList<>();
            for (Carro carro : carros) {
                carrosList.add(carro);
            }
            carrosList.add(novoCarro);
            carros = carrosList.toArray(new Carro[0]);
        }
    }

    public Carro getCarro(String placa) {
        for (Carro carro : carros) {
            if (carro.placa.equals(placa)) {
                return carro;
            }
        }
        return null;
    }

    public Carro[] getFrota() { return carros; }
    public int getContagem() { return carros.length; }

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
}
