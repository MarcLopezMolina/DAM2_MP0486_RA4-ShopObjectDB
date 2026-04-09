package model;

import main.Logable;
import javax.persistence.*;
import dao.*;

@Entity
public class Employee extends Person implements Logable
{
	private int employeeId;
	private String password;
	
	@Transient
	private Dao dao = new DaoImplObjectDB(); //Connection using ObjectDB

	public Employee(String name)
	{
		super(name);
	}

	public Employee(int employeeId, String name, String password)
	{
		super(name);
		this.employeeId = employeeId;
		this.password = password;
	}

	public Employee()
	{
		super();
	}

	public int getEmployeeId()
	{
		return employeeId;
	}

	public void setEmployeeId(int employeeId)
	{
		this.employeeId = employeeId;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	/**
	 * @param user from application, password from application
	 * @return true if credentials are correct or false if not
	 */
	@Override
	public boolean login(int user, String password)
	{
//		if (USER == user && PASSWORD.equals(password)) {
//			return true;
//		} 
		boolean success = false;

		// connect to data
		dao.connect();

		// get employee data
		if (dao.getEmployee(user, password) != null)
		{
			success = true;
		}

		// disconnect data
		dao.disconnect();
		return success;
	}

}
