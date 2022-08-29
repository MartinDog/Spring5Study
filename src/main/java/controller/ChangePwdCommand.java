package controller;

import org.springframework.beans.factory.annotation.Autowired;
import spring.MemberDao;

public class ChangePwdCommand {
    private String currentPassword;
    private String newPassword;
    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public String getCurrentPassword(){
        return currentPassword;
    }

}
