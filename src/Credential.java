
import java.security.MessageDigest;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stu
 */
public class Credential {
    private MessageDigest messageDigest;
    
    private String username;
    private String password;

    public Credential() {
        try {
            messageDigest = MessageDigest.getInstance("SHA-256");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Credential(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void updatePassword(String password) {
        this.password = hash(password);
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    public boolean checkPassword(String password){
        return hash(password).equals(this.password);
    }
    
    private String hash(String s){
        messageDigest.update(s.getBytes());
        return new String(messageDigest.digest());
    }
    
    public String forFile(){
        return "\r\n" + getUsername() + "\r\n" + getPassword();
    }
}
