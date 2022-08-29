package config;

import controller.ChangePwdController;
import controller.LoginController;
import controller.LogoutController;
import controller.RegisterController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import spring.AuthService;
import spring.ChangePasswordService;
import spring.MemberRegisterService;
import survey.SurveyController;

@Configuration
@ComponentScan({"spring"})
public class ControllerConfig {
    @Autowired
    private MemberRegisterService memberRegisterService;
    @Autowired
    private AuthService authService;
    @Autowired
    private ChangePasswordService changePasswordService;
    @Bean
    public RegisterController registerController(){
        RegisterController controller = new RegisterController();
        controller.setMemberRegisterService(memberRegisterService);
        return controller;
    }
    @Bean
    public SurveyController surveyController(){
        return new SurveyController();
    }
    @Bean
    public LoginController loginController(){
        LoginController login= new LoginController();
        login.setAuthService(authService);
        return login;
    }
    @Bean
    public LogoutController logoutController(){
        return new LogoutController();
    }
    @Bean
    public ChangePwdController changePwdController(){
        ChangePwdController controller = new ChangePwdController();
        return new ChangePwdController();
    }
}
