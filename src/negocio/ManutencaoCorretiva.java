//==========================================
	package negocio;
public class ManutencaoCorretiva {    
    Carro carro; // O carro que foi para manutenção
    double valorDoConserto; // Valor total do conserto

    public void ManuntencaoCorretiva(Carro carro, int nivelDeDano){
        this.carro = carro;
        carro.nivelDeDano = 0;
        this.valorDoConserto = gerarMulta(nivelDeDano);
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

    public String ToString(){
        return "O cliente está devendo: "+ valorDoConserto;
    }
}