
public class Client {
	String name;
	String email;
	String phone;
	String address;
	
	Client(String name, String email, String phone, String address){
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	
	Client(String name, String phone){
		this.name = name;
		this.phone = phone;
	}
	
}
