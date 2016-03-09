/**
 * Created by Ксения on 3/6/2016.
 */
public class User {
    private String login;
    private String password;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public boolean eqLogin(String log)
    {
        return this.getLogin().equals(log);
    }
    public boolean eqPassword(String pass)
    {
        return this.getPassword().equals(pass);
    }

    @Override
    public String toString() {
        return login + ' ' + password;
    }
}
