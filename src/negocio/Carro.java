//==========================================
	package negocio;
	import dados.ICarro;
//==========================================
public class Carro implements ICarro {
    public int ano;
    public int valor;
    public String placa;
    public String marca;
    public String modelo;
    public String estado;
    public boolean disponivel;

// PRINCIPAIS SETS
		public void setValor(int valor){ this.valor = valor; }
    public void setDados(String marca, String modelo, int ano) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

// CONSTRUTOR DA CLASSE
    public Carro(String marca, String modelo, int ano, String placa) {
        this.ano = ano;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.disponivel = true;
        this.estado = "Disponivel";
    }

// PROCESSOS DE ALUGUEL
    public void setDisponivel(){ this.disponivel = true; }
    public void setIndisponivel(){ this.disponivel = false; }
    public void setEstado(String estado){ this.estado = estado; }

    public void alugaCarro(){ this.setIndisponivel(); this.setEstado("Alugado"); }
    public void devolveCarro(){ this.setDisponivel(); this.setEstado("Disponivel"); }
}

