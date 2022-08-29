package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/edit/changePassword")
public class ChangePwdController {
    @Autowired
    private ChangePasswordService changePasswordService;


    @GetMapping
    public String form(@ModelAttribute("command") ChangePwdCommand pwdCmd, HttpSession session){
        AuthInfo authInfo = (AuthInfo) session.getAttribute("authInfo");
        if(authInfo==null){
            return "redirect:/login";
        }
        return "edit/changePwdForm";
    }
    @PostMapping
    public String submit(@ModelAttribute("command") ChangePwdCommand pwdCmd, Errors errors, HttpSession httpsession){
        new ChangePwdCommandValidator().validate(pwdCmd,errors);
        if(errors.hasErrors()){
            return "edit/changePwdForm";
        }
        AuthInfo authInfo = (AuthInfo) httpsession.getAttribute("authInfo");
        try{
            MemberDao memberDao = changePasswordService.memberDao;
            Member member = memberDao.selectByEmail(authInfo.getEmail());
            System.out.println("\ntest"+member.getPassword()+" "+pwdCmd.getCurrentPassword()+" "+ pwdCmd.getNewPassword());
            changePasswordService.changePassword(authInfo.getEmail(), pwdCmd.getCurrentPassword(), pwdCmd.getNewPassword());
            return "edit/changedPwd";
        }
        catch(WrongIdPasswordException e){
            errors.rejectValue("currentPassword","noMatching");
            return "edit/changePwdForm";
        }
    }
}
