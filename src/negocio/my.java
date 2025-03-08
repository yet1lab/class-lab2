//==========================================
//       MINHA BIBLIOTECA BASICA
//==========================================
	package negocio;
	import java.util.Scanner;
	import java.util.Arrays;
	import java.util.ArrayList;
	import java.util.function.Function;
//==========================================
public class my {
	public static void print(String msg, Object... args){ 
		System.out.printf(msg,args);
	}

	public static Scanner read(String msg, Object... args){ 
		print(msg,args); return new Scanner(System.in);
	}

	public static boolean havestr(String valor, String texto){
		return Arrays.asList(texto.split("\\s+")).contains(valor);
	}

	public static <O,T> ArrayList<O>
	filter(ArrayList<O> array, Function<O,T> lambdaGet, T valor){
		array.removeIf(obj -> lambdaGet.apply(obj) != valor);
		return array;
	}
}

