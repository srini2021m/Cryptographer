package encryption;

//Name: Kalki Srinivasan
//concrete strategy class for simple copy encryption algorithm which doesn't change text

public class COPYstrategy implements strategyInterface{
	
	public COPYstrategy() {}
	
	public String encrypt(String text, int key) {
		//just returns inputted text
		return text;
	}
	
	public String decrypt(String text, int key) {
		//just returns inputted text
		return text;
	}
	
	
}