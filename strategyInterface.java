package encryption;

//Name: Kalki Srinivasan
//interface to hold declarations of concrete strategy methods
// help choose between the different the encryption algorithms

public interface strategyInterface{
	public String encrypt(String text, int key);
	public String decrypt(String text, int key);
}