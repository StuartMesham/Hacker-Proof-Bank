/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stu
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class UserFile {
    
    private static String algorithm = "AES";

    private String username;
    private String password;
    private String text;

    public UserFile(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void append(String string) {
        text += string;
    }

    public void writeToFile() throws Exception {
        PrintWriter pr = new PrintWriter(new FileWriter("users/" + username + ".txt"));
        pr.write(encrypt(getText(), password));
        pr.close();
    }

    public void readFromFile() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("users/" + username + ".txt"));
        String raw = "";
        String line;
        while ((line = br.readLine()) != null) {
            raw += line;
        }
        br.close();
        setText(decrypt(raw, password));
    }

    //Performs Encryption
    public static String encrypt(String plainText, String password) throws Exception {
        //Generate key
        Key key = generateKey(password);
        
        //Encrypt text
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = chiper.doFinal(plainText.getBytes());
        String encryptedValue = Base64.getEncoder().encodeToString(encVal);
        return encryptedValue;
    }

    //Performs decryption
    public static String decrypt(String encryptedText, String password) throws Exception {
        //Generate key 
        Key key = generateKey(password);
        
        //Decrypt text
        Cipher chiper = Cipher.getInstance(algorithm);
        chiper.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = Base64.getDecoder().decode(encryptedText);
        byte[] decValue = chiper.doFinal(decordedValue);
        String decryptedValue = new String(decValue);
        return decryptedValue;
    }

    //generateKey() is used to generate a secret key for AES algorithm
    //Key is generated from a hash of the password supplied
    private static Key generateKey(String password) throws Exception {
        MessageDigest digester = MessageDigest.getInstance("MD5");
        digester.update((password + " that hackers will never know").getBytes());
        byte[] key = digester.digest();
        SecretKeySpec spec = new SecretKeySpec(key, "AES");
        return spec;
    }
}