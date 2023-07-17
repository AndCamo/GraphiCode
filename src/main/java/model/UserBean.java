package model;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return (this.nation != null) ? this.nation : "";
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getBirthDate() {
        return (this.birthDate != null) ? birthDate.toString() : "";
    }

    public void setBirthDate(String birthDate) {
        if (!birthDate.isEmpty() && birthDate != null)
            this.birthDate = LocalDate.parse(birthDate);
        else
            this.birthDate = null;
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

    public void setHashedPassword(String hashedPassword){
        this.password = hashedPassword;
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

    public boolean checkUserData(){
        final Pattern phone_regex = Pattern.compile("^\\d{10}$");
        final Pattern name_regex = Pattern.compile("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$");
        final Pattern nation_regex = Pattern.compile("^[a-zA-ZÀ-ÿ ]*$");
        final Pattern date_regex = Pattern.compile("^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$");

        Matcher matcher = null;
        boolean matchFlag = true;

        String userName = this.getName();
        matcher = name_regex.matcher(userName);
        if (!matcher.find() || userName.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATO NOME");
        }
        String userSurname = this.getSurname();
        matcher = name_regex.matcher(userSurname);
        if (!matcher.find() || userSurname.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATO COGNOME");
        }

        String birthDate = this.getBirthDate();
        matcher = date_regex.matcher(birthDate);
        if (!matcher.find() || birthDate.isEmpty() || birthDate == null){
            matchFlag = false;
            System.out.println("SBAGLIATA DATA");
        }

        String nation = this.getNation();
        matcher = nation_regex.matcher(nation);
        if (!matcher.find() || nation.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATA NAZIONE");
        }

        String phoneNumber = this.getPhoneNumber();
        matcher = phone_regex.matcher(phoneNumber);
        if (!matcher.find() || phoneNumber.isEmpty()){
            matchFlag = false;
            System.out.println("SBAGLIATO NUMERO");
        }
        return matchFlag;
    }

    
    private int id;
    private String name, surname, eMail, password, phoneNumber, nation;
    private boolean admin;
    private LocalDate birthDate;
}
