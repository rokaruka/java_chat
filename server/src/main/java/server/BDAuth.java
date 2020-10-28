package server;

public class BDAuth implements AuthService {
    @Override
    public String getNicknameByLoginAndPassword(String login, String password) {
        return BDHandler.getNicknameByLoginAndPassword(login,password);
    }

    @Override
    public boolean registration(String login, String password, String nickname) {
        return BDHandler.registration(login,password,nickname);
    }
}
