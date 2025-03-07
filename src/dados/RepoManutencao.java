//==========================================
	package dados;
	import java.util.ArrayList;
	import negocio.Manutencao;
//==========================================
public class RepoManutencao {    

    public static ArrayList<Manutencao> ManutencoesFeitas = new ArrayList<>();

    // Método para adicionar uma manutenção à lista
    public static void addManutencao(Manutencao manutencao) {
        ManutencoesFeitas.add(manutencao);
    }

    // Método para buscar uma manutenção pelo ID
    public static Manutencao getManutencao(int id) {
        for (Manutencao manutencao : ManutencoesFeitas) {
            if (manutencao.getIdManutencao() == id) {
                return manutencao;
            }
        }
        return null; // Retorna null se não encontrar a manutenção
    }

    // Método para remover uma manutenção pelo ID
    public static void delManutencao(int id) {
        ManutencoesFeitas.removeIf(manutencao -> (manutencao.getIdManutencao() == id));
    }

    // Método para listar todas as manutenções
    public static void listarManutencoes() {
        for (Manutencao manutencao : ManutencoesFeitas) {
            System.out.println(manutencao);
        }
    }
}