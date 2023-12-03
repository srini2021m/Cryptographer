package encryption;

//Name: Kalki Srinivasan
//concrete strategy class for caesar encryption algorithm

public class CAESARstrategy implements strategyInterface{
	char currentChar;
	int currentCharASCII;
	int outputCharASCII;
	String strategyOutputEncrypt= "";
	String strategyOutputDecrypt = "";
	
	public CAESARstrategy() {}
	
	public String encrypt(String text, int key) {
		strategyOutputEncrypt= "";
		
		//for loop to convert inputted text to characters and convert each character to ascii
		//once converted to ascii, add key to get cipher characters
		//convert and return as string
		for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            currentCharASCII = currentChar;
            outputCharASCII= currentCharASCII+key;
            
            if ( 32 > outputCharASCII || outputCharASCII > 126) {
            	// if the ascii code of a character is outside of the 32-126 range
            	outputCharASCII = (outputCharASCII -32) % 95 + 32 ; //wrap around them and apply offset
            	strategyOutputEncrypt+=String.valueOf((char)outputCharASCII);
            } else {
            strategyOutputEncrypt+=String.valueOf((char)outputCharASCII);
            }
            
        }
		return strategyOutputEncrypt;
		
	}
	
	public String decrypt(String text, int key) {
		strategyOutputDecrypt = "";
		
		//for loop to convert inputted text to characters and convert each character to ascii
		//once converted to ascii, subtract key to decrypt and find original characters 
		//convert and return as string
		for (int i = 0; i < text.length(); i++) {
            currentChar = text.charAt(i);
            currentCharASCII = currentChar;
            outputCharASCII= currentCharASCII-key;
            
            if ( 32 > outputCharASCII || outputCharASCII > 126) {
            	// if the ascii code of a character is outside of the 32-126 range
            	outputCharASCII = ((outputCharASCII -32) % 95) +95 + 32 ;//wrap around them and apply offset
            	strategyOutputDecrypt+=String.valueOf((char)outputCharASCII);
            } else {
            strategyOutputDecrypt+=String.valueOf((char)outputCharASCII);
            }
        }
		return strategyOutputDecrypt;
	}
	
}