package aed.indexedlist;
import es.upm.aedlib.indexedlist.*;

public class Utils {
  public static <E> IndexedList<E> deleteRepeated(IndexedList<E> l) {
	  IndexedList<E> res = new ArrayIndexedList<E>();
	  for(int i = 0; i < l.size(); i ++) {
		  boolean bool = true;
		  int j=0;
		  for(j=0; j< res.size() && bool; j ++) {
			  if(l.get(i).equals(res.get(j))) {
				  bool = false;
			  }
		  }
		  if(bool) {
			  res.add(j, l.get(i));
		  }
	  }
		return res;
	} 
}
