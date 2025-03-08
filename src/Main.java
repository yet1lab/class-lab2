//==========================================
	import dados.RepoCarro;
	import negocio.*;
//==========================================
public class Main {
	public static void main(String[] args) {
		RepoCarro carros = RepoCarro.getInstance(); 

		carros
			.add("lamborguini", "huracan", 2024, "AAA-0002")
			.add("toyota", "GT86", 2024, "AAA-0001")
			.add("fiat", "uno", 2003, "AAA-0000")
			.pin("modelo","GT86").del(0)
			.all(0);
		
		carros.obj(0).set("ano",1970);

		my.print("%s %n", carros.all(0).get(0));
	}
}
