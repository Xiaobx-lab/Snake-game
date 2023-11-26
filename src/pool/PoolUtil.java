package pool;

import java.io.InputStream;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.pool.DruidDataSourceFactory;

public class PoolUtil {
	//Druid 使用properties文件做配置文件，properties集合读配置文件
	private static Properties properties = new Properties();	
	private static DataSource dataSource;
	
	static {
		//类路径下的文件使用类加载器读取
		InputStream is = PoolUtil.class.getClassLoader().getResourceAsStream("druid.properties");
		try {
			properties.load(is);
			//druid 连接工厂创建连接池对象
			dataSource = DruidDataSourceFactory.createDataSource(properties);
		}  catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("PoolUtil 加载配置文件失败");
		}
	}
	public static DataSource getDatasource() {
		return dataSource;
	}
	/**
	 * 获取连接池里的代理对象
	 * @return JDBC连接对象在连接池里的代理对象
	 */
	public static Connection getCon() {
		try {
			return dataSource.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("druid连接池不想给你连接");
		}
	}
	//对JDBC里要关闭的资源进行关闭！ 如果增删改 就没有结果集，查询择优结果集
	public static void closeAll(ResultSet rs,Statement stmt,Connection connection) {
		if (rs!=null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (stmt!=null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (connection!=null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
