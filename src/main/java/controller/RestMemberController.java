package controller;

import jdk.jshell.Snippet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import spring.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.status;

@RestController
@Component
public class RestMemberController {
    @Autowired
    private MemberDao memberDao;
    @Autowired
    private MemberRegisterService registerService;
    @GetMapping("/api/members")
    public List<Member> members(){
        return memberDao.selectAll();
    }
    @GetMapping("/api/members/{id}")
//    public Member member(@PathVariable(value = "id") long id, HttpServletResponse response)throws IOException {
//        Member member = memberDao.selectById(id);
//        if(member==null){
//            response.sendError(HttpServletResponse.SC_NOT_FOUND);
//            return null;
//        }
//        return member;
//    }
    public ResponseEntity<Member> member(@PathVariable(value = "id")Long id){
        Member member = memberDao.selectById(id);
        if(member==null){
            throw new MemberNotFoundException();
        }
        return ResponseEntity.status(HttpStatus.OK).body(member);
        //return status(HttpStatus.OK).body(member);
    }
    @PostMapping("/api/members")
    public ResponseEntity<Object> newMember(@RequestBody @Valid RegisterRequest regReq,  Errors errors) throws IOException{
        if(errors.hasErrors()){
            String Errorcodes = errors.getAllErrors().stream().map(error->error.getCodes()[0]).collect(Collectors.joining(","));
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse("errorCodes = "+Errorcodes));
        }
        try{
            Long newMemberId = registerService.regist(regReq);
            return ResponseEntity.status(HttpServletResponse.SC_CREATED).header("Location","/api/members/"+newMemberId).build();


        }
        catch(DuplicateMemberException e){
            return ResponseEntity.status(HttpServletResponse.SC_CONFLICT).build();
        }
    }
    @ExceptionHandler(MemberNotFoundException.class)
    public ResponseEntity<ErrorResponse> HandleNodata(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse("no member"));
    }
    @InitBinder
    protected void InitBiner(WebDataBinder biner){
        biner.setValidator(new RegisterRequestValidator());
    }
}
