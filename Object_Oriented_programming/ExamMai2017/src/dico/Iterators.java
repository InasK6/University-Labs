package dico;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Iterators<T> {
	
	
	public static <T>Iterator<T> concat (Iterator<?  extends T> it1, Iterator<?  extends T> it2){
		return new Iterator<T>() {
			private Iterator<?  extends T> i1=it1;
			private Iterator<?  extends T> i2=it2;
			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return (i1.hasNext() || i2.hasNext());
			}
			@Override
			public T next() {
				// TODO Auto-generated method stub
				if(i1.hasNext()) { 
					return i1.next();
				}
				if(i2.hasNext()) { 
					return i2.next();
				}
				else {
					throw new NoSuchElementException();
				}
			}
		};
		
		
	}
	/*private class DicoIterator() implements Iterator<T> {
		
	}*/

}
