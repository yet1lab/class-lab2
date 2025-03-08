//==========================================
	package dados;
	import negocio.Carro;
	import java.util.ArrayList;
//==========================================
public interface IRepoCarro {
  Carro getCarro(String placa);
  void delCarro(String placa);
  void addCarro(String placa, String marca, String modelo, int ano);  
	boolean haveCarro(String placa);
  
	int getContagem();
  ArrayList<Carro> getFrota();  
}
