package Database;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Properties;

/**
 * @auther flypig
 * @date 2022/10/4
 * @package_name PACKAGE_NAME
 * @project studyWork
 * @function:
 */
public class JDBCConnectBase {
    public static void main(String[] args) throws Exception{
        //1.加载配置文件
        InputStream is = JDBCConnectBase.class.getClassLoader().getResourceAsStream("jdbc.properties"); //注意读取路jing；ConnectionTest为当前所在类
        Properties pros = new Properties();
        pros.load(is);

        //2.读取配置信息
        String user = pros.getProperty("user");
        String password = pros.getProperty("password");
        String url = pros.getProperty("url");
        String driverClass = pros.getProperty("driverClass");

        //3.加载驱动
        Class.forName(driverClass);

        //4.获取连接
        Connection conn = DriverManager.getConnection(url, user, password);
        System.out.println(conn);

    }

    //后续的操作
    //通用的增删改操作
    public void update(String sql, Object...args) {//sql中占位符的个数与可变形参的个数一只
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //1.获取数据库连接
            //conn = Utils.getConnection();

            //2.预编译sql语句，返回PreparedStatement的实例
            ps = conn.prepareStatement(sql);

            //3.填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);// 小心参数声明错误
            }
            //4.执行
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //5.资源的关闭
            //Utils.closeResource(conn, ps);
        }
    }
}
