//==========================================
//       REPOSITORIO DE MANUTENCAO
//==========================================
package dados;
import negocio.Manutencao;
//==========================================
public class RepoManutencao extends LSetMap<Manutencao> {
	private static RepoManutencao instance;

	private RepoManutencao() { super(Manutencao.class); }

	public static RepoManutencao getInstance(){
		if (instance == null){ instance = new RepoManutencao(); }
		return instance;
	};
}
