package encryption;

//Name: Kalki Srinivasan
//concrete strategy class for scytale encryption algorithm

public class SCYTALEstrategy implements strategyInterface {
String strategyOutputEncrypt="";
	
	public SCYTALEstrategy() {}
	
	public String encrypt(String text, int key) {
		strategyOutputEncrypt="";
		
		//for loop to go through text and split into rowns and columns based on the key value
		//put rows and columns together to output the ciphered text
        for (int i = 0; i < key; i++){
            for (int j = 0; i+j < text.length(); j += key){
                strategyOutputEncrypt += String.valueOf(text.charAt(i+j));
            }
            
        }
        return strategyOutputEncrypt;
	}
	
	public String decrypt(String text, int key) {
	    // Determine the number of rows and columns
	    int numRows = (int) Math.ceil((double) text.length() / key);
	    int numCols = key;

	    // Create a 2D array to represent the scytale grid
	    char[][] grid = new char[numRows][numCols];
	    int index = 0;

	    // Fill the grid with the encrypted characters from scytale cipher text
	    for (int i = 0; i < numCols; i++) {
	        for (int j = 0; j < numRows; j++) {
	            if (index < text.length()) {
	                grid[j][i] = text.charAt(index);
	                index++;
	            }
	        }
	    }

	    // Read the characters from the grid in order
	    StringBuilder strategyOutputDecrypt = new StringBuilder();
	    for (int i = 0; i < numRows; i++) {
	        for (int j = 0; j < numCols; j++) {
	            if (grid[i][j] != 0) {
	            	strategyOutputDecrypt.append(grid[i][j]);
	            }
	        }
	    }

	    return strategyOutputDecrypt.toString();
	}

}