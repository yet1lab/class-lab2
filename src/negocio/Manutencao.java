//==========================================
	package negocio;
public class Manutencao {    
    Carro carro; // O carro que foi para manutenção
    double valorDoConserto; // Valor total do conserto
    int IdManutencao;
    static int proximoId = 1;

    public void ManuntencaoCorretiva(Carro carro, int nivelDeDano){
        this.carro = carro;
        carro.nivelDeDano = 0;
        this.valorDoConserto = gerarMulta(nivelDeDano);
        this.IdManutencao = proximoId++;
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

    public Carro getCarro() {
        return carro;
    }

    public void setCarro(Carro carro) {
        this.carro = carro;
    }

    public double getValorDoConserto() {
        return valorDoConserto;
    }

    public void setValorDoConserto(double valorDoConserto) {
        this.valorDoConserto = valorDoConserto;
    }

    public int getIdManutencao() {
        return IdManutencao;
    }

    public void setIdManutencao(int IdManutencao) {
        this.IdManutencao = IdManutencao;
    }

    public static int getProximoId() {
        return proximoId;
    }

    public static void setProximoId(int proximoId) {
        Manutencao.proximoId = proximoId;
    }

    public String ToString(){
        return "O cliente está devendo: "+ valorDoConserto;
    }
}