package Record;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Test01 extends JFrame {
   private List<Double> score = new ArrayList<>();
   private static Vector<Info> record = new Vector<Info>();
   private List<String> date = new ArrayList<>();
   static {
      record.add(new Info("user1", 3, 5, "2022-01-02"));
      record.add(new Info("user1", 10, 10, "2022-01-03"));
      record.add(new Info("user1", 8, 15, "2022-01-04"));
      record.add(new Info("user1", 5, 15, "2022-01-05"));
   }
   
   public Test01() {
      toList(record);
      
//      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      setSize(500,500);
//      setVisible(true);
   }
   private void toList(Vector<Info> vector) {
      for(Info info : vector) {
         score.add(((double)info.getAnsgame()/info.getTotalgame())*100);
         date.add(info.getDate());
      }
   }
   
   public List<Double> getScore() {
      return score;
   }
   
   public List<String> getDate() {
      return date;
   }
   
   private void a() {
//      Container con = getContentPane();
      ResultPanel resultPan = new ResultPanel();
      
//      con.add(resultPan, BorderLayout.CENTER);
      
      ImageIcon bgImg;
      JLabel bgImgPan;
      bgImg = new ImageIcon("images/gamebg.png");
      bgImgPan = new JLabel(bgImg);
      
      JPanel p = new JPanel();
      
      bgImgPan.add(resultPan);
      bgImgPan.add(p);
      this.add(bgImgPan);
   }
   
   class ResultPanel extends JPanel{
      int a, b, c;
      
      public void paint(Graphics g) {
         g.clearRect(0, 0, getWidth(), getHeight());
         g.drawLine(50, 250, 350, 250);
         
         for(int i = 1; i<11; i++) {
            g.drawString(i*10 + "", 25, 255-(20*i));
            g.drawLine(50, 250-(20*i), 350, 250-(20*i));
         }
         
         g.drawLine(50, 20, 50, 250);
         g.drawString("예시", 100, 270);
      }
      
      void setScore(int a, int b, int c) {
         this.a = a;
         this.b = b;
         this.c = c;
      }
   }

//   public static void main(String[] args) {
//      Iterator iter = record.iterator();
//      for(int i = 0; i < record.size(); i++) {
//         System.out.println(record.get(i));
//      }
//      new Test01();
//   }

}