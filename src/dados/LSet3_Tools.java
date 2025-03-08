//========================================================
//               LSETMAP LEVEL 3 - TOOLS
//========================================================
package dados;
import negocio.LBox;

import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Collectors;

import java.util.function.Function;
import java.util.function.Consumer; 
import java.util.function.Predicate;
import java.util.function.BinaryOperator;
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet3_Tools<T> extends LSet2_General<T> {
	public int qnt(int... pag){ return this.get(pag).size(); }

	public int sum(String prop){ 
		int sum = 0;
		for (T obj: this.get(0)){ sum += ((int) ((LBox<T>) obj).get(prop)); }
		return sum;
	}
//========================================================
	public LSetBook<T> filter(Predicate<T> lambda){
		Stream<T> result = book.get(0).stream().filter(lambda);
		book.set(0,  result.collect(Collectors.toSet()) );
		return (LSetBook<T>) this;
	}
	public LSetBook<T> forEach(Consumer<T> lambda){
		book.get(0).stream().forEach(lambda);
		return (LSetBook<T>) this;
	}
	public <R> Set<R> map(Function<T,R> lambda){
		Stream<R> result = book.get(0).stream().map(lambda);
		return result.collect(Collectors.toSet());
	}
	public T reduce(T start, BinaryOperator<T> lambda){
		return book.get(0).stream().reduce(start, lambda);
	}
}
