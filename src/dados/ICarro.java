//==========================================
	package dados;
//==========================================
public interface ICarro {
    void setValor(int valor);
    void setDados(String marca, String modelo, int ano);
    void setDisponivel();
    void setIndisponivel();
    void setEstado(String estado);
}
