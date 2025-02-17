//==========================================
	package dados;
	import negocio.Carro;
//==========================================
public interface IRepoCarro {
  Carro getCarro(String placa);
  void delCarro(String placa);
  void addCarro(String placa, String marca, String modelo, int ano);  
	boolean haveCarro(String placa);
  
  Carro[] getFrota();  
	int getContagem();
}
