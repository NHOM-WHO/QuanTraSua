package Models;

public class Employees {
	private int ID;
	private String username;
	private String password;
	private String name;
	private String phone;
	@Override
	public String toString() {
		return "Employees [ID=" + ID + ", username=" + username + ", password=" + password + ", name=" + name
				+ ", phone=" + phone + ", permission=" + permission + ", salary=" + salary + "]";
	}
	private String permission;
	private String salary;
	
	 public Employees(int ID,String username, String password,String name,String phone,String permission,String salary) {
	        super();
	        this.ID =ID;
	        this.username = username;
	        this.password = password;
	        this.name = name;
	        this.permission = permission;
	        this.setPhone(phone);
	        this.salary = salary;
	        
	    }
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}


	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
}
