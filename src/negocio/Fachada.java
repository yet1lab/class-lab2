//====================================================
//                     FACHADA
//====================================================
package negocio;

import dados.RepoCarro;
import dados.RepoAluguel;
import dados.RepoCliente;
import dados.RepoManutencao;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
//====================================================
public class Fachada extends LBox<Fachada> {
    private static RepoCarro carros;
    private static RepoAluguel alugueis;
    private static RepoCliente clientes;
    private static RepoManutencao manutencoes;

    public Fachada(){
        this.carros = RepoCarro.getInstance();
        this.alugueis = RepoAluguel.getInstance();
        this.clientes = RepoCliente.getInstance();
        this.manutencoes = RepoManutencao.getInstance();

        this.getters = "alugueis carros clientes manutencoes";
        this.setters = "alugueis carros clientes manutencoes";
  }

    public void loadData(){
        carros.fromFile("0_carro.dat");
        alugueis.fromFile("0_aluguel.dat");
        clientes.fromFile("0_cliente.dat");
        manutencoes.fromFile("0_manutencao.dat");
    }

    public void saveData(){
        carros.toFile("0_carro.dat");
        alugueis.toFile("0_aluguel.dat");
        clientes.toFile("0_cliente.dat");
        manutencoes.toFile("0_manutencao.dat");
    }
    
}