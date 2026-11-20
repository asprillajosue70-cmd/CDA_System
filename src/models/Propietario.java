
package models;

public class Propietario {

    private int id;
    private String name;
    private String document;
    private String phone;
    private String email;

    // Constructor
    public Propietario(int id, String name, String document,
                       String phone, String email) {

        this.id = id;
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.email = email;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDocument() {
        return document;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

