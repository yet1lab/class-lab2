//==========================================
//        REPOSITORIO DE CLIENTES
//==========================================
package dados;
import negocio.Cliente;
//==========================================
public class RepoCliente extends LSetMap<Cliente> {
	private static RepoCliente instance;

	private RepoCliente() { super(Cliente.class); }

	public static RepoCliente getInstance(){
		if (instance == null){ instance = new RepoCliente(); }
		return instance;
	};	
}
