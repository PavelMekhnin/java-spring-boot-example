package kz.mekhnin.spring.headhunter.api.viewModels.request;

// Account credentials to login
public class AccountCredentialsViewModel {

    private String login;

    private String password;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
