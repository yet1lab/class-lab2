//========================================================
//             LSETMAP LEVEL 0 - BASE
//========================================================
package dados;

import java.util.Set;
import java.util.Map;
import java.util.List;
import java.util.HashSet;
import java.util.HashMap;
import java.util.ArrayList;
import java.lang.reflect.Constructor;
//========================================================
public abstract class LSet0_Base<T> {
	protected Set<T>                           src = new HashSet<>();
	protected List<Set<T>>                     book = new ArrayList<>();
	protected Map<String, Map<Object, Set<T>>> index = new HashMap<>();
	protected Map<Object, Set<T>>              map = new HashMap<>();
	protected Set<T>                           set = new HashSet<>();
	protected Class<T>                         type;
	protected Constructor<T>                   factory;

	{ this.book.add(new HashSet<>()); }
}
