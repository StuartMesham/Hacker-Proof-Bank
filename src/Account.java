
import java.text.DecimalFormat;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Stu
 */
public class Account {
    public static final int TYPE_BASIC = 0;
    public static final int TYPE_STANDARD = 1;
    public static final int TYPE_PREMIUM = 2;
    
    private String holder;
    private int type;
    private int number;
    private String branchName;
    private String branchCode;
    private String bankName;
    private double balance = 0;

    public Account(String holder, int type, int number, String branchName, String branchCode, String bankName) {
        this.holder = holder;
        this.type = type;
        this.number = number;
        this.branchName = branchName;
        this.branchCode = branchCode;
        this.bankName = bankName;
    }
    
    public Account(String fromFile){
        String[] parts = fromFile.split("\n");
        setHolder(parts[0]);
        setType(Integer.parseInt(parts[1]));
        setNumber(Integer.parseInt(parts[2]));
        setBranchName(parts[3]);
        setBranchCode(parts[4]);
        setBankName(parts[5]);
        balance = Double.parseDouble(parts[6]);
    }
    
    public String forFile(){
        return holder + "\n"
                + type + "\n"
                + number + "\n"
                + branchName + "\n"
                + branchCode + "\n"
                + bankName + "\n"
                + balance;
    }

    public String getBankName() {
        return bankName;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public String getBranchName() {
        return branchName;
    }
    
    public double getBalance(){
        return balance;
    }

    public String getHolder() {
        return holder;
    }

    public int getNumber() {
        return number;
    }

    public int getType() {
        return type;
    }
    
    public String getTypeString(){
        switch (getType()){
            case TYPE_BASIC:
                return "Basic";
            case TYPE_STANDARD:
                return "Standard";
            case TYPE_PREMIUM:
                return "Premium";
            default:
                return "Invalid";
        }
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setType(int type) {
        this.type = type;
    }
    
    public void desposit(double sum){
        balance += sum;
    }
    
    public void withdraw(double sum){
        balance -= sum;
        if(balance < 0){
            balance -= 15;
        }
    }
    
    public String displayBalance(){
        DecimalFormat df = new DecimalFormat("0.00");
        return "R" + df.format(balance);
    }

    @Override
    public String toString() {
        return String.format("%-20s %20s %n", "Holder", getHolder())
                + String.format("%-20s %20s %n", "Type", getTypeString())
                + String.format("%-20s %20s %n", "Account number", getNumber())
                + String.format("%-20s %20s %n", "Branch name", getBranchName())
                + String.format("%-20s %20s %n", "Branch code", getBranchCode())
                + String.format("%-20s %20s %n", "Bank name", getBankName())
                + String.format("%-20s %20s", "Balance", displayBalance());
    }
}
