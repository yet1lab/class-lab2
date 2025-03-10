//==========================================
	import dados.RepoCarro;
        import dados.RepoCliente;
        import dados.RepoManutencao;
	import negocio.*;
	import java.util.Set;
        import negocio.Fachada;
        import gui.menu.*;

//==========================================
public class Main {
	public static void main(String[] args) {
                Fachada fachada = new Fachada();
                
                
                Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                    System.out.println("Aplicação finalizando! Executando código...");

                }));
                MenuOption tela = new MenuOption();
		RepoCarro carros = fachada.get("carros");
                RepoManutencao manut = fachada.get("manutencoes");
                RepoCliente clientes = fachada.get("clientes");
                    

		carros
			.add("lamborguini", "huracan", 2024, "AAA-0002")
			.add("toyota", "GT86", 2024, "AAA-0001")
			.add("fiat", "uno", 2003, "AAA-0000");
                                
                clientes
                        .add("Adriano Lucas", "112.111.124-48", "adrienlukerbtc@gmail.com", 19,"(81) 98306-9423")
                        .add("Vinicius Marques", "112.111.124-43", "vinimarques@gmail.com", 20,"(81) 98347-1222")
                        .add("Emmanuel", "123.456.789-98", "emmanuel@gmail.com", 20, "(11) 92838-2838");
		
                
		
		Set<Carro> conjuntoAll  = carros.src(); 
		int tamanho             = carros.src().size();
		Set<Carro> conjunto     = carros.get("ano",2024); 
		Carro carro             = carros.obj("placa","AAA-0000"); 
                
                
                manut.add(carro,0);
                
                
                System.out.println(clientes.src());                
                

		my.print("%s ; %s ; %s ; %s %n", tamanho, conjuntoAll, conjunto, carro);
                
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOption().setVisible(true);
            }
        });
	}
}
