public class user {

    private int userID;
    private String userName;

    //Constructor
    public user (int ID, String name) {
        this.userName = name;
        this.userID = ID;
    }

    //Getters
    public String getUserName() {
        return userName;
    }
    public int getUserID() {
        return userID;
    }

    //Setters
    public void setUserName(String input) {
        this.userName = input;
    }
}
