//imports..duh
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.util.Random;
import java.util.ArrayList;

class Interface implements ActionListener{
    //random things that need a large scope
        Blooket newGame;
        question currentQues;
    // initalize buttons and the userAns string
        JButton one;
        JButton two ;
        JButton three;
        JButton four;
        JLabel myLabel;
        JButton continueButton = new JButton ("Check Answer");
        JButton nextquestionButton = new JButton("Next Question");
        String userAns = ""; 
        //initializing all the gross window looking stuff
        JFrame myFrame = new JFrame();
        Border myBorder = BorderFactory.createEmptyBorder(10,10,10,10);
        JPanel contentPane = (JPanel)myFrame.getContentPane();
        JPanel myPanel = new JPanel();
        JPanel gridJPanel = new JPanel();
        Border sides = BorderFactory.createEmptyBorder(5,5,5,5);


        //constructor to create the initial interace, simple panel
        public Interface(){
            myFrame.setTitle("Blooket");
            myFrame.setSize(800, 700);
            contentPane.setBackground(new Color(0,0,0));
            myFrame.setBackground(new Color(0,0,0));
            myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
            contentPane.setBorder(myBorder); 
            myPanel.setLayout(new FlowLayout());
            gridJPanel.setLayout(new GridLayout(2,2,5,5));
            gridJPanel.setBorder(sides);
            gridJPanel.setBackground(new Color(0,0,0));
            myLabel = new JLabel();
            myLabel.setForeground(new Color(0,0,0));
            
        }


        //creates new instance of the blooket type just cause we need that 
        public void playGame(int num){
            //num comes from the number users entered (how many questions they have)
            newGame = new Blooket(num);
            
            
        }

        //actually asking the human the question, resetting the question label adding random answers to buttons etc.
        public question newQuestion(){
            currentQues = newGame.askQuestion();
            String currentans = currentQues.getAns();
            myLabel.setText(currentQues.getQuestion());
            contentPane.remove(myLabel);
            myPanel.add(myLabel);
            //makes things easier to access
            ArrayList<question> tempquesbank = newGame.getQuestionBank();
            //randomize the answer choices
            Random rand = new Random();
            int randIndex = rand.nextInt(4);
            int dontdoagain;//this is gonna be the thing taht will make sure that the correct answer is an answer choice
            switch (randIndex){
                case 0:
                    one = new JButton(currentans);
                    dontdoagain = 0;
                    break;
                case 1:
                    two= new JButton(currentans);
                    dontdoagain=1;
                    break;
                case 2:
                    three = new JButton(currentans);
                    dontdoagain=2;
                    break;
                case 3:
                    four = new JButton(currentans);
                    dontdoagain=3;
                    break;
                default:
                    one = new JButton(currentans);
                    dontdoagain=0;
                    break;
            }
            //make an arrayList for the answer choices that we've already put on buttons, (works less when the question count <10)
            ArrayList<Integer> alreadydone = new ArrayList<Integer>();
            for (int i = 0; i <4; i++){
                randIndex =rand.nextInt(tempquesbank.size());
                alreadydone.add(randIndex);
                while(tempquesbank.get(randIndex).equals(currentQues)||done(alreadydone,randIndex)){
                    randIndex =rand.nextInt(tempquesbank.size());
                    continue;  
                }
                if(dontdoagain==i){
                    i++;
                }
                switch (i){
                case 0:
                    one = new JButton(tempquesbank.get(randIndex).getAns());
                    break;
                case 1:
                    two = new JButton(tempquesbank.get(randIndex).getAns());
                    break;
                case 2:
                    three = new JButton(tempquesbank.get(randIndex).getAns());
                    break;
                case 3:
                    four = new JButton(tempquesbank.get(randIndex).getAns());
                    break;
                }
            }
            //adding the buttons
            one.setBackground(new Color(0,0,0));
            two.setBackground(new Color(0,0,0));
            three.setBackground(new Color(0,0,0));
            four.setBackground(new Color(0,0,0));
            one.setForeground(new Color(255,255,255));
            two.setForeground(new Color(255,255,255));
            three.setForeground(new Color(255,255,255));
            four.setForeground(new Color(255,255,255));
            myLabel.setSize(50,50);

            gridJPanel.add(one);
            gridJPanel.add(two);
            gridJPanel.add(three);
            gridJPanel.add(four);
            // add all child panels to the main panel
            contentPane.add(myPanel);
            contentPane.add(gridJPanel);
            contentPane.add(continueButton);
            contentPane.add(nextquestionButton);
            continueButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            nextquestionButton.setAlignmentX(Component.CENTER_ALIGNMENT);
            // make panel visible
            myFrame.setVisible(true);
            one.addActionListener(this);
            two.addActionListener(this);
            three.addActionListener(this);
            four.addActionListener(this);
            continueButton.addActionListener(this);
            nextquestionButton.addActionListener(this);
            return currentQues;
        }
        //action preformed methods
        public void actionPerformed(ActionEvent event){
            // find the source of event and what to do with that information. 
            //figuring out what the human answered 
            if (event.getSource()== one){
                userAns += one.getText();
            }else if (event.getSource()== two){
                userAns += two.getText();
            }else if (event.getSource()== three){
                userAns += three.getText();
            }else if (event.getSource()== four){
                userAns += four.getText();
            }
            //making sure they actually answered the question (no checking answer if they havent answered)
            if(!userAns.equals("")){
                if (event.getSource()== continueButton ){
                    if(newGame.checkQuestion(currentQues, userAns)){
                        JOptionPane.showMessageDialog(null,"Correct!!");
                        userAns= "";
                    }else{
                    
                        JOptionPane.showMessageDialog(null,"Incorrect: " + currentQues.getAns());
                        userAns="";
                    }
                    
                }
            }
            //when they move on to the next question we need to reset all the text and buttons
            if(event.getSource()==nextquestionButton){
                
                myLabel.setText(null);
                gridJPanel.remove(one);
                gridJPanel.remove(two);
                gridJPanel.remove(three);
                gridJPanel.remove(four);
                //keeps asking them questions till they close the window
                newQuestion();
            }
        
        }
            //used in the ask question method to check if we have already used a certain answer
            private boolean done(ArrayList<Integer> check, int find){
                for (int i = 0; i<check.size(); i++){
                    if(check.get(i)==find){
                        return true;
                    }
                }
                return false;
            }
        }
    
