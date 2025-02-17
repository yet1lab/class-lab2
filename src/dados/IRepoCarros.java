/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

/**
 *
 * @author vini
 */

 public interface IRepoCarros {
    boolean haveCarro(String placa);
    void delCarro(String placa);
    void addCarro(String placa, String marca, String modelo, int ano);
    Carro getCarro(String placa);
    Carro[] getFrota();
    int getContagem();
}
