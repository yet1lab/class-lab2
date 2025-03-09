//========================================================
//            LBOX - BASE FOR INTERNAL ELEMENTS
//========================================================
package negocio;

import dados.LSetMap;
import dados.LSetBook;

import java.util.Arrays;
import java.lang.reflect.Field;
//========================================================
@SuppressWarnings("unchecked")
public abstract class LBox<T> {
	protected LSetBook<T> repo;
	protected String props;
	protected String getters;
	protected String setters;
	protected static LSetBook<?> reporef;
//========================================================	
	public static <T> void repo(LSetBook<T> repo){ reporef = repo; }

	public <G> G get(String prop){
		return (getters.contains(prop)) ? ((G) localGet(prop)) : null;
	}
	public <G> void set(String prop, G val){
		if (setters.contains(prop)) { localSet(prop, val); } 
	}
	public void rstIndex() {
		if (this.repo!=null){
			Arrays.stream(props.split(" "))
				.forEach(prop -> ((LSetMap<T>) repo).drop( (T) this, prop, localGet(prop) ) );
		}
	}
//========================================================	
	protected <G> G localGet(String prop) {
    Safe code = new Safe(() -> field(prop).get(this));
    return code.except("A propriedade nao existe: " + prop);
	}

	protected <G> void localSet(String prop, G val) {
		repo = (repo!=null) ? repo : (LSetBook<T>) reporef;

		if (this.repo!=null){
			G old = localGet(prop);
			((LSetMap<T>) repo).drop((T) this, prop, old);
			((LSetMap<T>) repo).save((T) this, prop, (G) val);
		}
		Safe code = new Safe(() -> field(prop).set(this, val));
		code.except("Propriedade nao existe: " + prop);
	}

	protected Field field(String prop) {
		Safe code = new Safe(() -> {
			Field campo = this.getClass().getDeclaredField(prop);
			campo.setAccessible(true);
			return campo;
		});
		return code.except("A propriedade nao existe: " + prop);
	}
}
