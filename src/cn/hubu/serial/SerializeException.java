package cn.hubu.serial;

public class SerializeException extends RuntimeException {

	public SerializeException() {
		super();
		
	}

	public SerializeException(String message) {
		super(message);
		
	}

	public SerializeException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// TODO Auto-generated constructor stub
	}

	public SerializeException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public SerializeException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -7371729159210682154L;

}
