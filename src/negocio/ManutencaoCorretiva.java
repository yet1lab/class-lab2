//==========================================
	package negocio;
//==========================================
public class ManutencaoCorretiva extends Manutencao {    
    Carro carro; // O carro que foi para manutenção
    int nivelDeDano; // A peça que foi consertada
    double valorDoConserto; // Valor total do conserto

    public void ManuntencaoCorretiva(Carro carro, int nivelDeDano){
        this.Carro = carro;
        this.nivelDeDano = nivelDeDano;
        this.valorDoConserto = gerarMulta(nivelDeDano);
    }
    
    public double gerarMulta(int nivel) {
        switch(nivel){
            case 1:
                return 100.00;
            case 2:
                return 200.00;
            case 3:
                return 300.00;
            case 4:
                return 400.00;
            default:
                return 500.00;
        }
    }

    public String ToString(){
        return "O cliente está devendo: "+ valorDoConserto;
    }
}
