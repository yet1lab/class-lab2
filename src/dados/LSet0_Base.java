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
import java.io.*; // File, Serializable, FileInputStream, FileOutputStream, ObjectInputStream, ObjectOutputStream
import java.util.*; // Set, Map, List, HashSet, HashMap, ArrayList;
import java.lang.reflect.Constructor;
import negocio.Safe;
//========================================================
@SuppressWarnings("unchecked")
public abstract class LSet0_Base<T> {
	protected Set<T>                           src = new HashSet<>();
	protected List<Set<T>>                     book = new ArrayList<>();
	protected Map<String, Map<Object, Set<T>>> index = new HashMap<>();
	protected Map<Object, Set<T>>              map = new HashMap<>();
	protected Set<T>                           set = new HashSet<>();
	protected Class<T>                         type;
	protected Constructor<T>                   factory;

	{ this.book.add(new HashSet<>()); }
        
        //========================================================
        public void toFile(String path) {
            Safe code = new Safe(() -> {
                ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(path));
                stream.writeObject(this.src);
                stream.writeObject(this.index);
            });
            code.except("Falha ao salvar arquivo");
        }

        public void fromFile(String path) {
            File file = new File(path);

            Safe code = new Safe(() -> {
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                this.src = (Set<T>) stream.readObject();
                this.index = (Map<String, Map<Object, Set<T>>>) stream.readObject();
            });

            if (file.exists()) {
                code.except("Falha ao ler dados.bin");
            }
        }
}
