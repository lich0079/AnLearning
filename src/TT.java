import java.util.*;
import java.sql.*;
public class TT {

	/**
	 * @param args
	 */
	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
		
		Connection con;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://FUCK\\SQLEXPRESS:1433;databaseName=javasql";
		String user="sa";
		String pwd="760079";
		
		try{
			System.out.println("�������ݿ���...");
			Class.forName(driver);
			con=DriverManager.getConnection(url,user,pwd);
			System.out.println("������");
			
			Statement stmt=con.createStatement();
			String query="select id,name from emp";
			stmt.execute(query);
			ResultSet rs=stmt.getResultSet();
			
			while(rs.next()){
				System.out.println(Integer.toString(rs.getInt("id"))+"    "+rs.getString("name"));
			}
			
			rs.close();
			stmt.close();
			con.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    	
	}

}
