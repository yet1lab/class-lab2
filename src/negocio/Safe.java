//========================================================
//             SAFE CLASS - ERROR HANDLING
//========================================================
package negocio;

public class Safe {
	private Object code;
	private String mensage;
	private boolean test;

	public void test(boolean test, String msg){
		this.test = test;
		this.mensage = msg;
	}

	public <T> Safe(TReturn<T> lambda) { 
		test(true, "");
		code = lambda; 
	}

	public <T> Safe(VoidReturn lambda) {
		test(true, "");
		code = (TReturn<T>) () -> { lambda.run(); return null; };
	}

	@SuppressWarnings("unchecked")
	public <T> T except(String msg) {
		if (this.test){
			try { return ((TReturn<T>) code).run(); } 
			catch (Exception e) { System.out.printf("[Error] %s %n", msg); System.exit(1); return null; } 
		} 
		else { System.out.printf("[Error] %s %n", mensage); System.exit(1); return null; }
	}
//==========================================
	@FunctionalInterface
	public interface TReturn<T> { T run() throws Exception; }

	@FunctionalInterface
	public interface VoidReturn { void run() throws Exception; }
}
