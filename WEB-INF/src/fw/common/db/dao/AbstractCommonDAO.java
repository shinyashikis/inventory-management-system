package fw.common.db.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import fw.common.db.dto.CommonDTO;

/**
 * <p>抽象DAO</p>
 */
abstract public class AbstractCommonDAO {

	/** <p>コネクション</p> */
	protected Connection conn;

	/**
	 * <p>デフォルトコンストラクタ</p>
	 */
	@SuppressWarnings("unused")
	private AbstractCommonDAO(){}

	/**
	 * <p>コンストラクタ</p>
	 * @param conn コネクション
	 */
	public AbstractCommonDAO(Connection conn){
		this.conn = conn;
	}

	/**
	 * <p>検索処理</p>
	 * @throws SQLException
	 */
	abstract public List<CommonDTO> select(String sql, List<Object> params) throws SQLException;

	/**
	 * <p>追加処理</p>
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	abstract public int insert(String sql, List<Object> params) throws SQLException;

	/**
	 * <p>更新処理</p>
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	abstract public int update(String sql, List<Object> params) throws SQLException;

	/**
	 * <p>削除処理</p>
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	abstract public int delete(String sql, List<Object> params) throws SQLException;

}
