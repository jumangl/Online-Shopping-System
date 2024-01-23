public class User {
    protected String username;  //Initialize the userName
    protected String password;  //Initialize the user Password

    public User(String username, String password){   //Constructor to get the username and password
        this.username = username;
        this.password = password;
    }

    public String getUsername() {  //getters and setters for the username and password
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
