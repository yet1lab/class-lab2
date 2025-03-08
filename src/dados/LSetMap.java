//========================================================
//                   LSETMAP FINAL
//========================================================
package dados;

import java.util.Set;
import java.util.HashSet;
import java.lang.reflect.Constructor;
//========================================================
@SuppressWarnings("unchecked")
public class LSetMap<T> extends LSet3_Tools<T> implements LSetBook<T> {
	public LSetMap(Class<T> type) {
		this.type = type;
		this.factory = (Constructor<T>) type.getConstructors()[0];
	}
//========================================================
	public LSetBook<T> to(int... pag){  return send(this.book.get(0), pag); }
	public LSetBook<T> rst(int... pag){ return send(new HashSet<>(), pag); }
	public LSetBook<T> all(int... pag){ return send(this.src, pag); }
	
	public LSetBook<T> inv(int... pag){
		Set<T> inv = new HashSet<>(src);
		inv.removeAll(book.get(0));
		return send(inv, pag);
	}
//========================================================
	public <G> LSetBook<T> pin(String prop, G val){ return pick(0b0011, prop, val); }
	public <G> LSetBook<T> not(String prop, G val){ return pick(0b1100, prop, val); }
	
	public <G> LSetBook<T> sel(String prop, G val){ return pick(0b0010, prop, val); }
	public <G> LSetBook<T> mix(String prop, G val){ return pick(0b0111, prop, val); }
	public <G> LSetBook<T> alt(String prop, G val){ return pick(0b0101, prop, val); }
	public <G> LSetBook<T> cut(String prop, G val){ return pick(0b0100, prop, val); }
	public <G> LSetBook<T> lim(String prop, G val){ return pick(0b0001, prop, val); }

	public <G> LSetBook<T> sep(String prop, G val){ return pick(0b1101, prop, val); }
	public <G> LSetBook<T> out(String prop, G val){ return pick(0b1000, prop, val); }
	public <G> LSetBook<T> eqv(String prop, G val){ return pick(0b1010, prop, val); }
	public <G> LSetBook<T> esc(String prop, G val){ return pick(0b1011, prop, val); }
	public <G> LSetBook<T> ign(String prop, G val){ return pick(0b1110, prop, val); }
}
