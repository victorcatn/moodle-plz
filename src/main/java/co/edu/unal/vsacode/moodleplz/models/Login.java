package co.edu.unal.vsacode.moodleplz.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Login {

    private String document;
    private String password;

    public Login(){

    }

    public Login(String document, String password) {
        this.document = document;
        this.password = password;
    }


    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPassword() {
        return password;
    }

    public void setPasword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Login{" +
                "document='" + document + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
