package Controls;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Views.connect;

public class Employees {
	
	public static ArrayList<Models.Employees> getAllEmployees() throws SQLException {
		ArrayList<Models.Employees> list = new ArrayList<>();
		Connection conn= connect.getConnection();
		try {
			Statement st=conn.createStatement();
			ResultSet rs=st.executeQuery("SELECT * FROM Employees");
			while(rs.next()) {
				int ID=rs.getInt("ID");
				String username=rs.getString("USERNAME");
				String password=rs.getString("PASSWORD");
				String name=rs.getString("NAME");
				String phone=rs.getString("PHONENUMBER");
				String permission=rs.getString("PERMISSION");
				String salary=rs.getString("SALARY");
				Models.Employees employees= new Models.Employees(0, username, password, name, phone, permission, salary);
				list.add(employees);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
		}
		return list;
	}
	 public static void insert(Models.Employees std)  {
	        
		 	
	        try {
	            //lay tat ca danh sach sinh vien
	        	Connection conn= connect.getConnection();
	     
	            //query
	            String sql = "insert into Employees(ID,USERNAME,PASSWORD,NAME,PHONE,PERMISSION,SALARY) values(?, ?, ?, ?, ?,?,?)";
	            PreparedStatement statement = conn.prepareCall(sql);
	            
	            statement.setInt(1, std.getID());
	            statement.setString(2, std.getUsername());
	            statement.setString(3, std.getPassword());
	            statement.setString(4, std.getName());
	            statement.setString(5, std.getPhone());
	            statement.setString(6, std.getPermission());
	            statement.setString(7, std.getSalary());
	            
	            
	            statement.execute();
	        } catch (SQLException e) {
	        	System.err.println(e.getMessage()+"/n"+e.getClass()+"/n"+e.getCause());
	        } 
	            
	           
	        
	        //ket thuc.
	    }
	    
//	    public static void update(Student std) {
//	        Connection connection = null;
//	        PreparedStatement statement = null;
//	        
//	        try {
//	            //lay tat ca danh sach sinh vien
//	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
//	            
//	            //query
//	            String sql = "update student set fullname=?,gender=?,age=?,email=?,phone_number=? where id = ?";
//	            statement = connection.prepareCall(sql);
//	            
//	            statement.setString(1, std.getFullname());
//	            statement.setString(2, std.getGender());
//	            statement.setInt(3, std.getAge());
//	            statement.setString(4, std.getEmail());
//	            statement.setString(5, std.getPhoneNumber());
//	            statement.setInt(6, std.getId());
//	            
//	            statement.execute();
//	        } catch (SQLException ex) {
//	            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	        } finally {
//	            if(statement != null) {
//	                try {
//	                    statement.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	            
//	            if (connection != null) {
//	                try {
//	                    connection.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	        }
//	        //ket thuc.
//	    }
//	    
//	    public static void delete(int id) {
//	        Connection connection = null;
//	        PreparedStatement statement = null;
//	        
//	        try {
//	            //lay tat ca danh sach sinh vien
//	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
//	            
//	            //query
//	            String sql = "delete from student where id = ?";
//	            statement = connection.prepareCall(sql);
//	            
//	            statement.setInt(1, id);
//	            
//	            statement.execute();
//	        } catch (SQLException ex) {
//	            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	        } finally {
//	            if(statement != null) {
//	                try {
//	                    statement.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	            
//	            if (connection != null) {
//	                try {
//	                    connection.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	        }
//	        //ket thuc.
//	    }
//	    
//	    public static List<Student> findByFullname(String fullname) {
//	        List<Student> studentList = new ArrayList<>();
//	        
//	        Connection connection = null;
//	        PreparedStatement statement = null;
//	        
//	        try {
//	            //lay tat ca danh sach sinh vien
//	            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/student_management", "root", "");
//	            
//	            //query
//	            String sql = "select * from student where fullname like ?";
//	            statement = connection.prepareCall(sql);
//	            statement.setString(1, "%"+fullname+"%");
//	            
//	            ResultSet resultSet = statement.executeQuery();
//	            
//	            while (resultSet.next()) {                
//	                Student std = new Student(resultSet.getInt("id"), 
//	                        resultSet.getString("fullname"), resultSet.getString("gender"), 
//	                        resultSet.getString("email"), resultSet.getString("phone_number"), 
//	                        resultSet.getInt("age"));
//	                studentList.add(std);
//	            }
//	        } catch (SQLException ex) {
//	            Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	        } finally {
//	            if(statement != null) {
//	                try {
//	                    statement.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	            
//	            if (connection != null) {
//	                try {
//	                    connection.close();
//	                } catch (SQLException ex) {
//	                    Logger.getLogger(StudentModify.class.getName()).log(Level.SEVERE, null, ex);
//	                }
//	            }
//	        }
//	        //ket thuc.
//	        
//	        return studentList;
//	    }
}
