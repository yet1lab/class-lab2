package negocio;

import dados.*;

public class Fachada extends LBox<Fachada> {
    private static RepoAluguel alugueis;
    private static RepoCarro carros;
    private static RepoCliente clientes;
    private static RepoManutencao manutencoes;
    
    public Fachada(){        
        this.alugueis = RepoAluguel.getInstance();
        this.carros = RepoCarro.getInstance();
        this.clientes = RepoCliente.getInstance();
        this.manutencoes = RepoManutencao.getInstance();
        
        
        this.getters = "alugueis carros clientes manutencoes";
        this.setters = "alugueis carros clientes manutencoes";
    }
    
    
    
}
