//========================================================
//             LSETMAP LEVEL 1 - SUPPORT
//========================================================
package dados;

import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet1_Support<T> extends LSet0_Base<T> {
	public LSetBook<T> send(Set<T> val, int... pag){
		for (int pos : pag){
			this.book.set(pos, new HashSet<>(val));
		} return (LSetBook<T>) this;
	}

	public <G> void save(T obj, String prop, G val){
		index.putIfAbsent(prop, new HashMap<>());
		index.get(prop).putIfAbsent(val, new HashSet<>());
		index.get(prop).get(val).add(obj);
	}

	public <G> void drop(T obj, String prop, G val){
		if ( ! this.load(prop,val).isEmpty() ){
			this.set.remove(obj);
			if (this.set.isEmpty()){ this.map.remove(val); }
			if (this.map.isEmpty()){ this.index.remove(prop); }
    }
  }

	public <G> Set<T> load(String prop, G val){
		this.map = this.index.containsKey(prop) ? this.index.get(prop) : new HashMap<>();
		this.set = this.map.containsKey(val)  ? this.map.get(val)  : new HashSet<>();
		return this.set;
	}
	public <G> LSetBook<T> pick(int uaib, String prop, G val){
		Set<T> U = new HashSet<>(this.src);
		Set<T> A = new HashSet<>(this.book.get(0));
		Set<T> B = new HashSet<>(this.load(prop,val));
		Set<T> I = new HashSet<>(A);

		I.retainAll(B);
		U.removeAll(A); U.removeAll(B); 
		A.removeAll(I); B.removeAll(I); 

		U = ((uaib & 8) != 0) ? U : new HashSet<>();
		A = ((uaib & 4) != 0) ? A : new HashSet<>();
		I = ((uaib & 2) != 0) ? I : new HashSet<>();
		B = ((uaib & 1) != 0) ? B : new HashSet<>();

		U.addAll(A); U.addAll(B); U.addAll(I);
		this.book.set(0, new HashSet<>(U));
		return (LSetBook<T>) this;
	}
}
