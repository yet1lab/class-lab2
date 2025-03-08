//========================================================
//              LSETMAP LEVEL 2 - GENERAL
//========================================================
package dados;

import negocio.Safe; 
import java.util.Set; 
import java.util.HashSet; 
import java.util.Iterator; 
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet2_General<T> extends LSet1_Support<T> {
	public LSetBook<T> pag(int... pag){
		book.set(0, this.get(pag));
		return (LSetBook<T>) this;
	}
	public LSetBook<T> New(int n){
		for (int i = 0; i < n; i++) {
			this.book.add(new HashSet<>());
		} return (LSetBook<T>) this;
	}
	public LSetBook<T> rip(int n){
		int size = book.size();
		n = (n < size) ? n : size-1; 
	
		for (int i = 0; i < n; i++) {
			book.remove(size-1-n);
		} return (LSetBook<T>) this;
	}
//========================================================
	public <G> LSetBook<T> set(String prop, G val){
		for (T obj: book.get(0)) { ((LBox<T>) obj).set(prop, val); }
		return (LSetBook<T>) this;
	}
	public T obj(int... pag){
		Iterator<T> pages = this.get(pag).iterator();
		return (pages.hasNext()) ? pages.next() : null;
	}
	public Set<T> get(int... pag){ 
		Set<T> pages = new HashSet<>();
		for (int num: pag){ pages.addAll(book.get(num)); }
		return pages;
 	}
	public LSetBook<T> del(int... pag){
		Set<T> pages = this.get(pag);
		for (int num: pag){ book.get(num).clear(); }
		for (T obj: pages){ ((LBox) obj).rstIndex(); }
		this.src.removeAll(pages);
		book.get(0).removeAll(pages);

		return (LSetBook<T>) this;
	}
	public LSetBook<T> add(Object... args){
		Safe code = new Safe(()->{
			LBox.repo((LSetBook<T>) this);
			T instance = this.factory.newInstance(args);
			this.src.add(instance);
			LBox.repo(null);
		});
		code.except("Instancia de objeto falhou");
		return (LSetBook<T>) this;
  }
}
