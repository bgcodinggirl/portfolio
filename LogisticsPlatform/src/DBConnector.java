import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.swing.table.TableModel;

public class DBConnector {
	
	static Connection conn = null;
	static ResultSet result=null;
	static PreparedStatement state=null;
	static MyModel model=null;
	
	public static MyModel getAllDeliverers() {
		String sql="SELECT* FROM DELIVERERS";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static Connection getConnection() {
		
		try {
			Class.forName("org.h2.Driver");
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/LogisticsData","sa","");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return conn;
	}

	public static MyModel getAllCompanies() {
		String sql="SELECT* FROM COMPANIES";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static MyModel getAllOrders() {
		String sql="SELECT SH.ID,DESCRIPTION, COMPANY_FROM AS FROM_CMP,CS.NAME,CS.COUNTRY, SH.COMPANY_TO AS TO_CMP,C.NAME,C.COUNTRY, DELIVERER AS D_ID,D.FNAME,D.LNAME,ORDER_DATE,DELIVERY_TYPE,STATUS" + 
				" FROM SHIPMENT SH JOIN COMPANIES CS" + 
				" ON SH.COMPANY_FROM=CS.COMPANY_ID" + 
				" JOIN COMPANIES C" + 
				" ON SH.COMPANY_TO=C.COMPANY_ID" + 
				" JOIN DELIVERERS D" + 
				" ON SH.DELIVERER=D.ID";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static MyModel searchDeliverer(String ctg) {
		String sql="SELECT * FROM DELIVERERS WHERE CATEGORY='"+ctg+"'";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static MyModel searchCompany(String kind) {
		String sql="SELECT * FROM COMPANIES WHERE ABOUT='"+kind+"'";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static MyModel searchShipment(String type) {
		String sql="SELECT DESCRIPTION, COMPANY_FROM AS FROM_CMP,CS.NAME,CS.COUNTRY, SH.COMPANY_TO AS TO_CMP,C.NAME,C.COUNTRY, DELIVERER AS D_ID,D.FNAME,D.LNAME,ORDER_DATE,DELIVERY_TYPE,STATUS" + 
				" FROM SHIPMENT SH JOIN COMPANIES CS" + 
				" ON SH.COMPANY_FROM=CS.COMPANY_ID" + 
				" JOIN COMPANIES C" + 
				" ON SH.COMPANY_TO=C.COMPANY_ID" + 
				" JOIN DELIVERERS D" + 
				" ON SH.DELIVERER=D.ID"+
				" WHERE DELIVERY_TYPE='"+type+"'";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
	
	public static MyModel searchShipmentInfo(String fName,String lName,String country,String status) {
		String sql="SELECT DESCRIPTION,C.NAME AS FROM_COMPANY,C.CITY,C.COUNTRY AS FROM_DESTINATION,"
				+ "CS.NAME AS TO_COMPANY,CS.CITY,CS.COUNTRY AS TO_DESTINATION, D.FNAME, D.LNAME, STATUS " + 
				"FROM SHIPMENT SH JOIN COMPANIES C " + 
				"ON SH.COMPANY_FROM=C.COMPANY_ID " + 
				"JOIN COMPANIES CS " + 
				"ON SH.COMPANY_TO=CS.COMPANY_ID " + 
				"JOIN DELIVERERS D " + 
				"ON SH.DELIVERER=D.ID " + 
				"WHERE STATUS='"+status+"' AND D.FNAME='"+fName+"' AND D.LNAME='"+lName+"' AND CS.COUNTRY='"+country+"'";
		conn=getConnection();
		try {
			state=conn.prepareStatement(sql);
			result=state.executeQuery();
			model=new MyModel(result);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return model;
	}
}
