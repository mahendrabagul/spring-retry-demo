package info.mahendrabagul.springboot2.exception;

public class MyRetryException extends Exception {

	private static final long serialVersionUID = -4017710071293503057L;

	public MyRetryException(String message) {
		super(message);
	}
}