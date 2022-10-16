package Database;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.Properties;

/**
 * @auther flypig
 * @date 2022/10/4
 * @package_name PACKAGE_NAME
 * @project studyWork
 * @function:
 */
public class DruidTest {
    public static void main(String[] args) throws Exception{
        Properties pro = new Properties();
        pro.load(DruidTest.class.getClassLoader().getResourceAsStream("druid.properties"));
        DataSource ds = DruidDataSourceFactory.createDataSource(pro);
        Connection conn = ds.getConnection();
        System.out.println(conn);
    }
}
