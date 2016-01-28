package gear.web.valueobject;

public class UserInfo {
    private String sessionId;
    private String username;

    public String getSessionId() {
        return this.sessionId;
    }

    public String getUsername() {
        return this.username;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
