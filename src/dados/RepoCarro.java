/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dados;

import dados.IRepoCarros;
import java.util.ArrayList;

public class RepoCarros extends PesquisaCarros implements IRepoCarros {

    public RepoCarros(Carro[] carros) {
        super(carros);
    }

    @Override
    public boolean haveCarro(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void delCarro(String placa) {
        ArrayList<Carro> carrosList = new ArrayList<>();
        for (Carro carro : carros) {
            if (!carro.getPlaca().equals(placa)) {
                carrosList.add(carro);
            }
        }
        carros = carrosList.toArray(new Carro[0]);
    }

    @Override
    public void addCarro(String placa, String marca, String modelo, int ano) {
        if (!haveCarro(placa)) {
            Carro novoCarro = new Carro(marca, modelo, ano, placa);
            ArrayList<Carro> carrosList = new ArrayList<>();
            for (Carro carro : carros) {
                carrosList.add(carro);
            }
            carrosList.add(novoCarro);
            carros = carrosList.toArray(new Carro[0]);
        }
    }

    @Override
    public Carro getCarro(String placa) {
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                return carro;
            }
        }
        return null;
    }

    @Override
    public Carro[] getFrota() {
        return carros;
    }

    @Override
    public int getContagem() {
        return carros.length;
    }

    @Override
    public void getAno(int ano) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.getAno() == ano) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    @Override
    public void getPlaca(String placa) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.getPlaca().equals(placa)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    @Override
    public void getMarca(String marca) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.getMarca().equals(marca)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    @Override
    public void getModelo(String modelo) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.getModelo().equals(modelo)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }

    @Override
    public void getEstado(String status) {
        ArrayList<Carro> resultado = new ArrayList<>();
        for (Carro carro : carros) {
            if (carro.getEstado().equals(status)) {
                resultado.add(carro);
            }
        }
        pesquisa = resultado.toArray(new Carro[0]);
    }
}
