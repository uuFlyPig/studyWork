package Database;

import org.apache.commons.dbutils.QueryRunner;

/**
 * @auther flypig
 * @date 2022/10/4
 * @package_name PACKAGE_NAME
 * @project studyWork
 * @function:
 */
public class DbUtilTest {
    public static void main(String[] args) {
        QueryRunner runner = new QueryRunner();
        //Connection conn = JDBCUtils.getConnection3();
        String sql = "insert into customers(name,email,birth)values(?,?,?)";
        //int count = runner.update(conn, sql, "何成飞", "he@qq.com", "1992-09-08");

        //System.out.println("添加了" + count + "条记录");

        //JDBCUtils.closeResource(conn, null);

    }

}
