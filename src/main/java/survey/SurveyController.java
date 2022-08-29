package survey;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    @GetMapping
    public ModelAndView form(){
        ModelAndView model = new ModelAndView();
        List<Question> questions=createQuestions();
        model.addObject("questions",questions);
        model.setViewName("/survey/surveyForm");
        return model;
    }
    public List<Question>createQuestions(){
        Question q1 = new Question("당신의 역할은 무엇이빈까?", Arrays.asList("서버","프론트","풀스택"));
        Question q2 = new Question("당신이 선호하는 개발 도구는 무엇입니까?", Arrays.asList("이클립스","인텔리젱","서브라임"));
        Question q3 = new Question("아무말이나 해주세요");
        return Arrays.asList(q1,q2,q3);

    }
    @GetMapping("/test1")
    public String submit(@ModelAttribute("ansData")AnswerdData data){
        return "survey/submitted";
    }
//    @GetMapping("/survey")
//    public String form(){
//        return "survey/surveyForm";
//    }
//    @PostMapping("/survey")
//    public String submit(@ModelAttribute("ansData")AnswerdData data){
//        System.out.println(data.toString());
//        return "survey/submitted";
//    }
}
