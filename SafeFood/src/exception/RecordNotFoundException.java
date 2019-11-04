package exception;

public class RecordNotFoundException extends Exception{
	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException() {
		this("This is RecordNotFoundException...");
	}
}
