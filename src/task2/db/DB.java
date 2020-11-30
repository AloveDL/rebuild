package task2.db;


import task2.entity.Person;

import java.sql.*;
import java.util.Vector;

public class DB {

	private final String URL="jdbc:mysql://localhost:3306/chat";
	private final String NAME="root";
	private final String PASSWORD="200104298534sdd";
	private Statement statement;

	public DB()throws Exception{
		Class.forName("com.mysql.jdbc.Driver");
		//2.获得数据库的连接
		Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);

		this.statement = conn.createStatement();
	}

	public Person Query(String name) throws SQLException {
		ResultSet rs = statement.executeQuery("select * from person where name='"+name+"'");
		while(rs.next()){
			Person person = new Person(rs.getString("name"),rs.getString("phonenumber"),rs.getString("gender"));
			return person;
		}
		return null;
	}

	public Vector<Person> QueryAll() throws SQLException {
		Vector<Person> vperson = new Vector<Person>();
		ResultSet rs = statement.executeQuery("select * from person");
		while(rs.next()){//如果对象中有数据，就会循环打印出来
			Person person = new Person(rs.getString("name"),rs.getString("phonenumber"),rs.getString("gender"));
			vperson.addElement(person);
		}
		return vperson;
	}

	public void Update(String name ,String newname,String newgender,String newnumber) throws SQLException {
		statement.executeUpdate("update person set name='"+newname+"',gender='"+newgender+"',phonenumber='"+newnumber+"' where name='"+name+"'");
	}

	public void Delete(String name) throws SQLException {
		statement.executeUpdate("DELETE from person where name='"+name+"'");
	}

	public void Insert(String name,String gender,String number) throws SQLException {
		statement.executeUpdate("insert into person (name,gender,phonenumber) value ('"+name+"','"+gender+"','"+number+"')");
	}
	public boolean isexist(String name) throws SQLException {
		ResultSet rs = statement.executeQuery("select * from person where name='"+name+"'");
		try{
			if(rs.next()){
				return true;
			}
		}catch (SQLException throwables){
			return false;
		}
		return false;
	}
	public  boolean login(String username,String password) throws SQLException {
		ResultSet rs = statement.executeQuery("select username,password from user where username='" +username+ "' and "+"password='"+password+"'");
		try{
			if(rs.next()){
			return true;
		}
		}catch (SQLException throwables){
			return false;
		}
		return false;
	}

}
