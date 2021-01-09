package util;

import java.sql.SQLException;

import com.mysql.jdbc.Statement;
//Êý¾Ý¿âÉ¾³ý²Ù×÷
public class Stuoperate {
	private static java.sql.Statement stmt=null;
	public static void deleteTable(String lpn,String name) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="DELETE FROM rental_info WHERE lpn='"+lpn+"' and name='"+name+"'";
		stmt=jdbc.conn.createStatement();
		stmt.executeUpdate(sql);
		}
	public static void deleteTable2(String lpn) throws SQLException {
		JDBCUtil jdbc=new JDBCUtil();
		String sql="DELETE FROM bicycle_info WHERE lpn="+lpn;
		stmt=jdbc.conn.createStatement();
		stmt.executeUpdate(sql);
		}
}
