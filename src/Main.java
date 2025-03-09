//==========================================
	import dados.RepoCarro;
	import negocio.*;
	import java.util.Set;
        import negocio.Fachada;
        import gui.menu.MenuOption;
//==========================================
public class Main {
	public static void main(String[] args) {
                Fachada fachada = new Fachada();
                MenuOption tela = new MenuOption();
		RepoCarro carros = fachada.get("carros");
                

		carros
			.add("lamborguini", "huracan", 2024, "AAA-0002")
			.add("toyota", "GT86", 2024, "AAA-0001")
			.add("fiat", "uno", 2003, "AAA-0000");
		
		
		Set<Carro> conjuntoAll  = carros.src(); 
		int tamanho             = carros.src().size();
		Set<Carro> conjunto     = carros.get("ano",2024); 
		Carro carro             = carros.obj("placa","AAA-0000"); 

		my.print("%s ; %s ; %s ; %s %n", tamanho, conjuntoAll, conjunto, carro);
                
                java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuOption().setVisible(true);
            }
        });
	}
}
