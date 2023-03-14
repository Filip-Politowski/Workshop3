package pl.coderslab.entity;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;



    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
    }
    public User() {


    }

    public void setPassword(String password) {
        this.password = password;
    }



    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }






    @Override
    public String toString() {
        return
                "id = " + id +
                        ", name = " + name +
                        ", email = " + email +
                        ", password = " + password

                ;
    }


}

