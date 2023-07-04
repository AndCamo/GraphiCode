package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

public class UserBean {

    public UserBean() {}

    public UserBean(String name, String surname, String eMail, String password,
                    String phoneNumber, String nation, String birthDate){
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        setPassword(password);
        this.nation = nation;
        this.phoneNumber = phoneNumber;
        setBirthDate(birthDate);
        this.admin = false;
    }

    public UserBean(int id, String name, String surname, String eMail, String password,
                    String phoneNumber, String nation, String birthDate){
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.eMail = eMail;
        setPassword(password);
        this.nation = nation;
        this.phoneNumber = phoneNumber;
        setBirthDate(birthDate);
        this.admin = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthDate() {
        return birthDate.toString();
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = LocalDate.parse(birthDate);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        MessageDigest digest = null;
        try {
            digest = MessageDigest.getInstance("SHA-1");
            digest.reset();
            digest.update(password.getBytes(StandardCharsets.UTF_8));
            this.password = String.format("%040x", new BigInteger(1, digest.digest()));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean checkPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        digest.reset();
        digest.update(password.getBytes(StandardCharsets.UTF_8));
        String hashedPass = String.format("%040x", new BigInteger(1, digest.digest()));

        return hashedPass.equals(this.password);
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    
    private int id;
    private String name, surname, eMail, password, phoneNumber, nation;
    private boolean admin;
    private LocalDate birthDate;
}
