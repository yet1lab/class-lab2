/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dados;

/**
 *
 * @author vini
 */

public interface ICarro {
    void setValor(int valor);
    void setDados(String marca, String modelo, int ano, String placa);
    void setDisponivel();
    void setIndisponivel();
    void setEstado(String estado);
}
