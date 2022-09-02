package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import spring.Member;
import spring.MemberDao;

import java.util.List;

@Controller
public class MemberListController {
    @Autowired
    private MemberDao memberDao;
    @RequestMapping("/members")
    public String list(@ModelAttribute("cmd") ListCommand listcommand,Errors errors, Model model ){
        if(errors.hasErrors()){
//            errors.rejectValue("from","noMatching");
//            errors.rejectValue("today","currentPassword");
            return "member/memberList";
        }
        if(listcommand.getFrom()!=null&&listcommand.getToday()!=null){
            List<Member> members = memberDao.selectByRegdate(listcommand.getFrom(),listcommand.getToday());
            model.addAttribute("members",members);
        }
        return "member/memberList";
    }
}
