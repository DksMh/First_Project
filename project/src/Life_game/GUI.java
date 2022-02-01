package Life_game;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.Random;
import java.lang.InterruptedException;

public class GUI {

    static int seconds = 0;
    static int buttonIndex = 0;
    int[] movesArr = new int[10];
    int numberOfButtons  = 18;
    Thread thread = new Thread();
    boolean[] logicArr   = new boolean[numberOfButtons];
    JFrame frame         = new JFrame("Memory Builder");
    JPanel buttonPanel   = new JPanel();
    JPanel panelBot      = new JPanel(new BorderLayout());
    GridLayout grid      = new GridLayout(0, 3) {{ setHgap(8); setVgap(8); }};
    JLabel scoreLabel    = new JLabel("Score");
    static JLabel timeLabel     = new JLabel("0");
    JButton button[]     = new JButton[numberOfButtons];
    Listener listener    = new Listener();
    Timer displayTimer   = new Timer(1000, listener.new DisplayTimeListener());
    Timer buttonTimer    = new Timer(300, listener.new ButtonDisplayListener());
    Color buttonColor;

    public GUI() {

        //Setup frame
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create button panel
        buttonPanel.setLayout(grid);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        addButtonPanel();

        //create label pane
        panelBot.add(timeLabel, BorderLayout.WEST);
        panelBot.add(scoreLabel, BorderLayout.EAST);
    }

    public void centerFrame() {

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((dim.width - frame.getWidth()) / 2,
                (dim.height - frame.getHeight()) / 2);
    }

    public void addButtonPanel() {

        for (int i=0; i < button.length; i++){
            button[i] = new JButton();
            button[i].addActionListener(listener.new ButtonListener());
            button[i].setText("Button " + (i + 1));
            button[i].setName(new Integer(i).toString());
            buttonPanel.add(button[i]);
        }

        buttonColor = button[0].getBackground();
    }


    public void createGUI() {
        frame.add(buttonPanel);
        frame.add(new JSeparator());
        frame.add(panelBot);        
        frame.pack();
        centerFrame();
        buttonPanel.setVisible(true);
        frame.setVisible(true);
        displayTimer.start();
        runGame();
    }

    public void runGame() {

        boolean isGameOver = false;
        int difficulty = 3;
        Random random = new Random();

        //generate and display random sequence
        for(int i = 0; i < difficulty; i++) {
            movesArr[i] = random.nextInt(numberOfButtons);
            System.out.println(movesArr[i]);
        }
        new BlinkThread().run();

    }



    public static void main(String[] args) {
       SwingUtilities.invokeLater(new Runnable(){
           public void run(){
               new GUI().createGUI();
            }
       });
    }

    public class Listener {


    boolean flag = true;

    public class ButtonDisplayListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
            if (flag){
               button[buttonIndex].setBackground(Color.red);
               flag = !flag;
            }
            else button[buttonIndex].setBackground(buttonColor);

        }
    }

    public class DisplayTimeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            GUI.timeLabel.setText(new Integer(seconds++).toString());
        }
    }

    public class ButtonListener implements ActionListener {

        public void actionPerformed(ActionEvent e) {
        }
    }

 }

 class BlinkThread implements Runnable {
     public void run(){

         for(int i: movesArr){
            try{
                Thread.sleep(200);
                System.out.println("echo");
                button[i].setBackground(Color.RED);
                Thread.sleep(200);
                button[i].setBackground(buttonColor);
            }
            catch(Exception ex){}
        }

     }
 }
}