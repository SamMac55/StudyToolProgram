public class question{
    private String question;
    private String ans;
    public question(String question, String answer){
        this.question = question;
        ans = answer;
    }
    public String getQuestion(){
        return question;
    }
    public String getAns(){
        return ans;
    }
    public String toString(){
        return "Question: "+question + ", Answer: " + ans;
    }

}