package task2;


import com.mysql.cj.log.Log;
import org.junit.Assert;
import org.junit.Test;
import task2.GUI.Login;
import task2.GUI.Window;
import task2.db.DB;
import task2.entity.Person;

import java.util.Vector;

public class TEST {

	private DB db = new DB();

	public TEST() throws Exception {
	}

	@Test
	public void Query() throws Exception{
		Person person = db.Query("张飞");
		Assert.assertEquals(person.getGender().toString(),"男");
	}
	@Test
	public void QueryAll() throws Exception{
		Vector<Person> people = db.QueryAll();
		Assert.assertNotNull(people);
	}
	@Test
	public void Delete() throws Exception{
		db.Delete("张飞");
	}
	@Test
	public void Update() throws Exception{
		db.Update("关羽","关云长","男","12222222222");
		Person person = db.Query("关云长");
		Assert.assertNotNull(person);
	}
	@Test
	public void Insert() throws Exception{
		db.Insert("诸葛亮","男","12345678901");
	}
	@Test
	public void login() throws Exception {
		Login login = new Login();
		login.show();
	}
	@Test
	public void MainWindow() throws Exception {
		Window window = new Window();
		window.show();
	}
	public static void main(String[] args) throws Exception {
		Login login = new Login();
		login.show();
	}
}
