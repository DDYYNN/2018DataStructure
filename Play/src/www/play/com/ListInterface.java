package www.play.com;

class ListIndexOutOfBoundsException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;}

class ListException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
}


public interface ListInterface {
	public boolean isEmpty();
	public int size();
	public void add(int index, Object item) throws ListIndexOutOfBoundsException,
				ListException;
		public Object get(int index)
			throws ListIndexOutOfBoundsException;
		public void remove(int index)
			throws ListIndexOutOfBoundsException;
		public void removeAll( );

}
