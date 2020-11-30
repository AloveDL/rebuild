package task2.entity;


public class Person {
	private String _name;
	private String _phoneNumber;
	private String _gender;

	public Person(String name,String phoneNumber,String gender){
		_name = name;
		_phoneNumber = phoneNumber;
		_gender = gender;
	}

	public Person(){

	}

	public String getName() {
		return _name;
	}

	public void setName(String _name) {
		this._name = _name;
	}

	public String getPhoneNumber() {
		return _phoneNumber;
	}

	public void setPhoneNumber(String _phoneNumber) {
		this._phoneNumber = _phoneNumber;
	}

	public String getGender() {
		return _gender;
	}

	public void setGender(String gender) {
		this._gender = gender;
	}
}
