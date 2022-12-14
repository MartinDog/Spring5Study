package spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthService {
    @Autowired
    private MemberDao memberDao;
    public AuthInfo authentication(String email, String password){
        Member member = memberDao.selectByEmail(email);
        if(member==null){
            throw new WrongIdPasswordException();
        }
        if(!member.matchPassword(password)){
            throw new WrongIdPasswordException();
        }
        return new AuthInfo(member.getId(),member.getPassword(),member.getEmail());
    }
}
