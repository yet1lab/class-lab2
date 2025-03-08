//==========================================
//        REPOSITORIO DE CARROS
//==========================================
package dados;
import negocio.Carro;
//==========================================
public class RepoCarro extends LSetMap<Carro> {
	private static RepoCarro instance;

	private RepoCarro() { super(Carro.class); }

	public static RepoCarro getInstance(){
		if (instance == null){ instance = new RepoCarro(); }
		return instance;
	};
}

