package kz.mekhnin.spring.headhunter.api.viewModels;

public class SignViewModel {

    private String email;

    private String password;

    public String getName() {
        return email;
    }

    public void setName(String name) {
        this.email = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
