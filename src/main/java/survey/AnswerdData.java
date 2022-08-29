package survey;

import java.util.List;

public class AnswerdData {
    public String toString(){
        StringBuilder sb=new StringBuilder();
        for(String s:responses){
            sb.append(" "+s);
        }
        sb.append(" "+res.getLocation()+" "+res.getAge());
        return sb.toString();
    }
    private List<String> responses;
    private Respondent res;

    public void setResponses(List<String> responses) {
        this.responses = responses;
    }

    public void setRes(Respondent res) {
        this.res = res;
    }

    public List<String> getResponses() {
        return responses;
    }

    public Respondent getRes() {
        return res;
    }
}
