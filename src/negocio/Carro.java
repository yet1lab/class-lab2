/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package negocio;

import dados.ICarro;

/**
 *
 * @author vini
 */

 public class Carro implements ICarro {
    private int ano;
    private String placa;
    private String marca;
    private String modelo;
    private int valor;
    private String estado;
    private boolean disponivel;

    public Carro(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
        this.disponivel = false;
        this.estado = "novo";
    }

    @Override
    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public void setDados(String marca, String modelo, int ano, String placa) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.placa = placa;
    }

    @Override
    public void setDisponivel() {
        this.disponivel = true;
    }

    @Override
    public void setIndisponivel() {
        this.disponivel = false;
    }

    @Override
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getAno() {
        return ano;
    }

    public String getPlaca() {
        return placa;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getValor() {
        return valor;
    }

    public String getEstado() {
        return estado;
    }

    public boolean isDisponivel() {
        return disponivel;
    }
}

