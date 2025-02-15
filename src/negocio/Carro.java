/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

/**
 *
 * @author vini
 */

public class Carro{

    String placa; //Placa do carro que servirá como ID
    String marca; //Marca do carro "Toyota", "Fiat"...
    String modelo; //Modelo do carro "Corolla", "Uno"...
    double precoDiaria; //Valor do custo da Diaria de aluguel
    boolean disponibilidade; // Se o carro está ou não disponível para aluguel
    String dataManutencaoPreventiva; // A data da manutenção preventiva
    String status; // Status é o estado atual do veículo: Disponível/Manutenção/Em uso
    //Cliente nomeDoCliente;

    public Carro(){

    }

    //INICIO GETTERS E SETTER
    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getPrecoDiaria() {
        return precoDiaria;
    }

    public void setPrecoDiaria(double precoDiaria) {
        this.precoDiaria = precoDiaria;
    }

    public boolean isDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(boolean disponibilidade) {
        this.disponibilidade = disponibilidade;
    }

    public String getDataManutencaoPreventiva() {
        return dataManutencaoPreventiva;
    }

    public void setDataManutencaoPreventiva(String dataManutencaoPreventiva) {
        this.dataManutencaoPreventiva = dataManutencaoPreventiva;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    //FIM GETTERS E SETTERS


    
}
