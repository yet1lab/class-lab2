//==========================================
	package gui;
        
        import negocio.Carro;
        import negocio.Manutencao;
        import negocio.Cliente;

//==========================================
public class Teste {
    public static void main(String[] args) {
        Carro carro1 = new Carro("toyota", "corola", 2020, "AAA1234");
        carro1.alugaCarro();
    }
}
