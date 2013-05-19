package fw.core.error;

/**
 * <p>処理要求無効例外</p>
 */
public class RequestInvalidException extends Exception {
	private static final long serialVersionUID = -312711597612683063L;

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public RequestInvalidException() {
		super();
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param message
	 */
	public RequestInvalidException(String message) {
		super(message);
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param cause
	 */
	public RequestInvalidException(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param message
	 * @param cause
	 */
	public RequestInvalidException(String message, Throwable cause) {
		super(message, cause);
	}
}
