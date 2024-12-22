import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Blooket{
    private Scanner kb = new Scanner(System.in);
    private ArrayList<question> questionBank = new ArrayList<question>();
    public boolean isCorrect;
    public String questionAsked;
    //make the amount of questions users want
    public Blooket(int numQuestions){
        String ques;
        String ans;
        //ensure that they have 5 questions minimum
        while(numQuestions<5){
            System.out.print("You need more questions for better quality: ");
            numQuestions=kb.nextInt();
        }
        //make instances of question class for each querstion
        for(int i = 0; i<numQuestions; i++){
            System.out.println("\nWhat is question number " + (i+1)+ "?: ");
            ques=kb.nextLine();
            System.out.println("\nWhat is the answer to question "+ (i+1) + "?: ");
            ans = kb.nextLine();
            questionBank.add(initializer(ques,ans));
        
        }
    }
    //idk if i use this but just tells us how many questiosn there are
    public int bankSize(){
        return questionBank.size();
    }

    //make the bank of questions, actaully makes the questions
    private question initializer(String ques, String ans){
        question blookques = new question(ques,ans);
        return blookques;
    }
    //get question bank
    public ArrayList<question> getQuestionBank(){
        return questionBank;
    }
    //get the question they want i dont think this is used in final code tbh but cutesie little function
    public String getThisQuestion(int num){
        question getter = questionBank.get(num);
        return getter.toString();
    }
    //important one fr gets a random question and sends it to interface
    public question askQuestion(){
        question tempques = null;
        shuffle(questionBank);
        Random rand = new Random();
        int randIndex = rand.nextInt(questionBank.size());
        tempques = questionBank.get(randIndex);
        return tempques;
        
    }
    //checks to see if they were right or wrong
    public boolean checkQuestion(question ques, String answer){
        if(ques.getAns().equals(answer)){
            return true;
        }
        return false;
    }
    //shuffles the question bank so that we can get a random question each time
    public void shuffle(ArrayList<question>arr){
        Random rand = new Random();
        for (int i = 0; i < arr.size(); i++) {
                int randIndex = rand.nextInt(arr.size());
                question set =  arr.get(randIndex);
                arr.set(randIndex,arr.get(i));
                arr.set(i,set);
            }
      }
}