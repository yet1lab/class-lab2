/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author Vinícius Marques
 */

public abstract class PesquisaCarros {
    protected Carro[] carros;
    protected Carro[] pesquisa;

    public PesquisaCarros(Carro[] carros) {
        this.carros = carros;
        this.pesquisa = new Carro[0];
    }

    public Carro[] getPesquisa() {
        return pesquisa;
    }

    public abstract void getAno(int ano);
    public abstract void getPlaca(String placa);
    public abstract void getMarca(String marca);
    public abstract void getModelo(String modelo);
    public abstract void getEstado(String status);
}
