package www.linkedlist.com;

class InvalidIndexException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public InvalidIndexException(){}
	public InvalidIndexException(String msg){
		super(msg);
	}
}
