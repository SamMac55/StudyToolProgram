import java.util.Scanner;

class Main{
    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        //ask user how much they study
        System.out.print("How many questions will you be studying?: ");
        int num = kb.nextInt();//save answer
        Interface newinterface = new Interface();//make the interface 
        newinterface.playGame(num);//start the game
        kb.close();
        //ask questions
        newinterface.newQuestion();
        
        
               

}
}

