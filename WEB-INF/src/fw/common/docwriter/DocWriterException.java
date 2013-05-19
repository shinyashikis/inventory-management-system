package fw.common.docwriter;

/**
 * <p>文書ファイル作成例外</p>
 */
public class DocWriterException extends Exception {
	private static final long serialVersionUID = -8161391203023936274L;

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	public DocWriterException() {
		super();
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param message
	 */
	public DocWriterException(String message) {
		super(message);
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param cause
	 */
	public DocWriterException(Throwable cause) {
		super(cause);
	}

	/**
	 * <p>コンストラクタ</p>
	 * @param message
	 * @param cause
	 */
	public DocWriterException(String message, Throwable cause) {
		super(message, cause);
	}
}
