//========================================================
//           LOGICAL SET BOOK - DATA STRUCTURE
//========================================================
package dados;

import java.util.Set;
import java.util.function.Function;
import java.util.function.Consumer; 
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
//========================================================
@SuppressWarnings("unchecked")
public interface LSetBook<T> {
	T                obj(int... pag);         // get object of book[pag]
	Set<T>           get(int... pag);         // get the set book[pag]
	LSetBook<T>      del(int... pag);         // delete book[pag] in all
	LSetBook<T>      add(Object... args);     // add object in all
	<G> LSetBook<T>  set(String prop, G val); // delete book[pag] in all

	<G> Set<T>       src();                   // get object with prop=val
	<G> T            obj(String prop, G val); // get object with prop=val
	<G> Set<T>       get(String prop, G val); // get set with prop=val
	<G> LSetBook<T>  del(String prop, G val); // delete set with prop=val

	LSetBook<T> New(int n);       // add n void sets
	LSetBook<T> rip(int n);       // book.pop(pag)
	LSetBook<T> pag(int... pag);  // cur = book[pag]

	int qnt(int... pag);     // count elements in book[pag]
	int sum(String prop);    // return sum prop in book[0]
//========================================================
	LSetBook<T> filter(Predicate<T> lambda);
	LSetBook<T> forEach(Consumer<T> lambda);
	<R> Set<R>  map(Function<T,R> lambda);
	T           reduce(T start, BinaryOperator<T> lambda);
//========================================================
	LSetBook<T>  to(int... pag); // book[pag] = cur   << A
	LSetBook<T> inv(int... pag); // book[pag] = ~cur  << ~A
	LSetBook<T> all(int... pag); // book[pag] = all   << true
	LSetBook<T> rst(int... pag); // book[pag] = void  << false
 
	<G> LSetBook<T> pin(String prop, G val); // cur = filter  << B
	<G> LSetBook<T> not(String prop, G val); // cur = ~filter << ~B

	<G> LSetBook<T> sel(String prop, G val); // cur = cur & filter   << and
	<G> LSetBook<T> mix(String prop, G val); // cur = cur + filter   << or
	<G> LSetBook<T> alt(String prop, G val); // cur = cur \ filter   << xor
	<G> LSetBook<T> cut(String prop, G val); // cur = cur - filter   << dif
	<G> LSetBook<T> lim(String prop, G val); // cur = filter - cur   << dif

	<G> LSetBook<T> sep(String prop, G val); // cur = ~(cur & filter) << nand
	<G> LSetBook<T> out(String prop, G val); // cur = ~(cur + filter) << nor
	<G> LSetBook<T> eqv(String prop, G val); // cur = ~(cur \ filter) << xnor
	<G> LSetBook<T> ign(String prop, G val); // cur = ~(cur - filter) << ndif
	<G> LSetBook<T> esc(String prop, G val); // cur = ~(filter - cur) << ndif
}

