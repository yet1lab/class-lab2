/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import java.util.Scanner;   

/**
 *
 * @author vini
 */

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
        this.Carro.setData(my.nextLine());
    }
}
