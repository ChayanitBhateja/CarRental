package exception;

public class UserExistException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UserExistException() {
		System.out.println("User Already Exists");
	}

}
