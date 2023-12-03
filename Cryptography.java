package encryption;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;
import java.util.*;

//Name: Kalki Srinivasan

/**
 * This class provides a graphical user interface that allows a user to enter
 * text and select an encryption algorithm and key to encrypt the text. The user
 * can also enter encrypted text, select an algorithm and key to decrypt the
 * text.
 *
 */
public class Cryptography extends Application{

	// Error message if the user does not enter a number for the key.
	private static final String KEY_ERROR_MESSAGE = "Enter a number for the key.";

	// The width of the text areas for entering encrypted and decrypted text,
	// in characters
	private static final int TEXT_WIDTH = 500;

	// The number of rows in the text areas for entering encrypted and decrypted text,
	// in characters
	private static final int TEXT_HEIGHT = 300;

	// The three kinds of encryption we know. These go in the menu.
	private static final String CAESAR = "Caesar cipher";
	private static final String SCYTALE = "Scytale";
	private static final String COPY = "Copy";

	// Where the un-encrypted text appears on the screen
	private TextArea plainTextArea = new TextArea();

	// Where the encrypted text appears on the screen
	private TextArea cipherTextArea = new TextArea();

	// The menu to select encryption algorithm.
	private ComboBox<String> encryptionMenu = new ComboBox<>();

	// The text field where the user enters the key to use
	private TextField keyField = new TextField();
	
	//create instance of strategy interface so that we can switch between the multiple concrete encryption strategies
	strategyInterface selectedStrategy;

	/**
	 * Constructs the user interface for the program.
	 */
	@Override
	public void start(Stage primaryStage) {


		// Create the two text areas for plain text and cipher text.
		Label ptitle = new Label("Plain Text:");
		Label ctitle = new Label("Cipher Text:");

		// Create the menu containing encryption options
		createMenu();

		// Add the buttons and key field to control the encryption and decryption.
		HBox buttonGroup = new HBox(5);
		Button encryptButton = createEncryptButton();
		Button decryptButton = createDecryptButton();
		Label klbl = new Label("Key:");
		
		buttonGroup.getChildren().addAll(encryptButton, decryptButton, encryptionMenu, klbl, keyField);

		// Add all the GUI elements to the display.
		VBox vb = new VBox(3);
		vb.getChildren().addAll(ptitle, plainTextArea, ctitle, cipherTextArea, buttonGroup);

		HBox root = new HBox();
		HBox.setMargin(vb, new Insets(10,10,10,10));
		root.getChildren().add(vb);

		Scene s = new Scene(root, TEXT_WIDTH, TEXT_HEIGHT);
		s.getRoot().setStyle("-fx-font-family: 'serif'");
		primaryStage.setScene(s);
		primaryStage.sizeToScene();
		primaryStage.show();
	}

	/**
	 * Creates the decrypt button.
	 * @return the button
	 */
	private Button createDecryptButton() {
		Button decryptButton = new Button("Decrypt");
		decryptButton.setOnMouseClicked(this::doDecrypt);
		return decryptButton;
	}

	/**
	 * Creates the encrypt button.
	 * @return the button
	 */
	private Button createEncryptButton() {
		Button encryptButton = new Button("Encrypt");
		encryptButton.setOnMouseClicked(this::doEncrypt); 
		return encryptButton;
	}

	/**
	 * Creates the menu containing the encryption algorithms to choose from.
	 */
	private void createMenu() {
		encryptionMenu.getItems().addAll(COPY,CAESAR,SCYTALE);
		encryptionMenu.setOnAction(this::itemStateChanged);
	}

	/**
	 * Selects the encryption algorithm to use
	 * @param selected the name of the algorithm
	 */
	private void selectEncryption(String selected) {
		if (selected.equals(COPY)) {
			this.selectedStrategy = new COPYstrategy();
		} else if (selected.equals(CAESAR)) {
			this.selectedStrategy = new CAESARstrategy();
		} else if (selected.equals(SCYTALE)) {
			this.selectedStrategy = new SCYTALEstrategy();
		}
	}

	/**
	 * Encrypts text using the currently selected algorithm
	 * @param plainText the text to encrypt
	 * @param key the key the algorithm should use
	 * @return the encrypted text
	 */
	private String encryptWithKey(String plainText, int key) {
		return selectedStrategy.encrypt(plainText,key);
	}

	/**
	 * Decrypts text using the currently selected algorithm
	 * @param cipherText the text to encrypt
	 * @param key the key the algorithm should use
	 * @return the decrypted text
	 */
	private String decryptWithKey(String cipherText, int key) {
		return selectedStrategy.decrypt(cipherText,key);
	}

	/**
	 * Define the action to take when the user clicks the decrypt button.
	 * @param e
	 */
	public void doDecrypt(MouseEvent evt) {

		try {
			// Get the key
			String keyText = keyField.getText();
			int key = Integer.parseInt(keyText);

			// Do the decryption.
			String decryptedText = decryptWithKey(cipherTextArea.getText(), key);

			// Display the decrypted text.
			plainTextArea.clear();
			plainTextArea.setText(decryptedText);
		} catch (NumberFormatException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(KEY_ERROR_MESSAGE);
			a.show();
		}

	}


	/**
	 * Define the action to take when the user clicks the encrypt button.
	 * @param evt
	 */
	public void doEncrypt(MouseEvent evt) {
		try {
			// Get the key
			String keyText = keyField.getText();
			int key = Integer.parseInt(keyText);

			// Do the encryption
			String encryptedText = encryptWithKey(plainTextArea.getText(), key);

			// Display the encrypted text
			cipherTextArea.clear();
			cipherTextArea.setText(encryptedText);
		} catch (NumberFormatException e) {
			Alert a = new Alert(AlertType.ERROR);
			a.setContentText(KEY_ERROR_MESSAGE);
			a.show();
		}
	}

	/**
	 * Define the action to take when the user changes which algorithm to use.
	 * @param evt
	 */
	public void itemStateChanged(ActionEvent evt) {

			String selected = ((ComboBox)evt.getSource()).getSelectionModel().getSelectedItem().toString();
			selectEncryption(selected);
	}



	/**
	 * The main method to start the program.
	 * 
	 * @param args None required
	 */
	public static void main(String[] args) {
		launch(args);
	}

}
