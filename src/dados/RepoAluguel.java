//==========================================
//        REPOSITORIO DE CARROS
//==========================================
package dados;
import negocio.Aluguel;
//==========================================
public class RepoAluguel extends LSetMap<Aluguel> {
	private static RepoAluguel instance;

	private RepoAluguel() { super(Aluguel.class); }

	public static RepoAluguel getInstance(){
		if (instance == null){ instance = new RepoAluguel(); }
		return instance;
	};
}

