package com.caifulif.whstudying.mvp.modle;

/**
 * Created by 皓 on 2018/4/3.
 */

public class User {

    private String username;

    private String usepwd;
    private String tokenId;
    private String status;
    private String loginHtml;
    private String userNumber;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsepwd() {
        return usepwd;
    }

    public void setUsepwd(String usepwd) {
        this.usepwd = usepwd;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLoginHtml() {
        return loginHtml;
    }

    public void setLoginHtml(String loginHtml) {
        this.loginHtml = loginHtml;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }
}
