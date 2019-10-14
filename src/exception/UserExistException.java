package exception;

public class UserExistException extends Exception {
	public UserExistException() {
		System.out.println("User Already Exists");
	}

}
