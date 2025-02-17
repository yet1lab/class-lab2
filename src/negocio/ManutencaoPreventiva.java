//==========================================
	package negocio;
	import java.util.Scanner;   
//==========================================
public class ManutencaoPreventiva extends Manutencao{
    Carro Carro; // O carro que foi para manutenção
    String[] peca; // A peça que foi consertada
    double valorDoConserto = 200.00; // Valor total do conserto
    String proximaData;
    
    public void ManuntecaoPreventiva() {
        gerarAlerta();
        alterarProximaData();
    }
    
    public String gerarAlerta() {
        return "Próxima manutenção em: "+ proximaData;
    }
    
    public void alterarProximaData() {
        Scanner my = new Scanner(System.in);
        /*this.Carro.setData(my.nextLine());*/
        this.proximaData = my.nextLine();
    }
}
