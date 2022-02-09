package org.proj.game.color;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameHowTo extends JPanel implements ActionListener{
   JPanel pan1 = new JPanel();
   JPanel pan2 = new JPanel();
   JPanel pan3 = new JPanel();
   
   private ImageIcon bgSK;
   private JLabel bgSkPan;

   private JButton next;
   private JButton prev;
   public JButton exit;

   private Font font1;
   
   int count = 0;

   public GameHowTo() {
//      setDefaultCloseOperation(EXIT_ON_CLOSE);
//      setTitle("주위 집중력 훈련 게임");
//      setSize(1024, 768);
//      this.setBounds(100, 100, 820, 530);
	   this.setBackground(new Color(37,9,9));
//      this.setBackground(new Color(0,0,0,0));
      this.setLayout(null);
      comm();
      first();
      mid();
      last();
      prev.addActionListener(this);
      next.addActionListener(this);
         
//      exit.addActionListener(new ActionListener() {
//         
//         @Override
//         public void actionPerformed(ActionEvent e) {
//            GameHowTo.this.setVisible(false);
//         }
//      });
      
   }

   public void comm() {
      bgSK = new ImageIcon("images/exWhite.png");
      bgSkPan = new JLabel(bgSK);
      bgSkPan.setBounds(0, 0, 820, 525);
//      TitledBorder tb = new TitledBorder(new LineBorder(Color.blue));
//      bgSkPan.setBorder(tb);
      bgSkPan.setLayout(null);
      
      font1 = new Font("맑은 고딕", Font.PLAIN, 24);

      next = new JButton("다음");
      next.setBounds(720, 440, 80, 60);
      prev = new JButton("이전");
      prev.setBounds(25, 440, 80, 60);
      exit = new JButton("종료");
      exit.setBounds(720, 50, 80, 60);
      
      next.setBackground(Color.orange);
      prev.setBackground(Color.pink);
      exit.setBackground(Color.red);

      bgSkPan.add(next);
      bgSkPan.add(prev);
      bgSkPan.add(exit);
      this.add(bgSkPan);
   }

   public void first() {
      pan1.setLayout(null);
      pan1.setBounds(130, 50, 570, 440);
      pan1.setBackground(Color.white);
      
      ImageIcon gameImg = new ImageIcon("images/m.png");
      JLabel gameImgPan = new JLabel(gameImg);
      gameImgPan.setBounds(10, 10, 550, 300);

      JLabel text = new JLabel("게임 설명 주절주절1");
      text.setFont(font1);
      text.setHorizontalAlignment(JLabel.CENTER);
      text.setBounds(10, 320, 550, 120);
      text.setOpaque(true);
      text.setBackground(Color.pink);

      pan1.add(text);
      pan1.add(gameImgPan);
      bgSkPan.add(pan1);
   }

   public void mid() {
      pan2.setLayout(null);
      pan2.setBounds(130, 50, 570, 440);
      pan2.setBackground(Color.white);
      
      ImageIcon gameImg = new ImageIcon("images/m.png");
      JLabel gameImgPan = new JLabel(gameImg);
      gameImgPan.setBounds(10, 10, 550, 300);
      
      JLabel text = new JLabel("게임 설명 주절주2");
      text.setFont(font1);
      text.setHorizontalAlignment(JLabel.CENTER);
      text.setBounds(10, 320, 550, 120);
      text.setOpaque(true);
      text.setBackground(Color.pink);
      
      pan2.setVisible(false);
      pan2.add(text);
      pan2.add(gameImgPan);
      bgSkPan.add(pan2);
   }

   public void last() {
      pan3.setLayout(null);
      pan3.setBounds(130, 50, 570, 440);
      pan3.setBackground(Color.white);

      ImageIcon gameImg = new ImageIcon("images/m.png");
      JLabel gameImgPan = new JLabel(gameImg);
      gameImgPan.setBounds(10, 10, 550, 300);
      
      JLabel text = new JLabel("게임 설명 주절주3");
      text.setFont(font1);
      text.setHorizontalAlignment(JLabel.CENTER);
      text.setBounds(10, 320, 550, 120);
      text.setOpaque(true);
      text.setBackground(Color.pink);
      
      pan3.setVisible(false);
      pan3.add(text);
      pan3.add(gameImgPan);
      bgSkPan.add(pan3);
   }

   @Override
   public void actionPerformed(ActionEvent e) {
      if(e.getSource() == prev) {
         count--;
      }
      if(e.getSource() == next) {
         count++;
      }
      
      if(e.getSource() == prev || e.getSource() == next) {
         if(count == 0) {
            prev.setVisible(false);
            next.setVisible(true); 
            pan1.setVisible(true);
            pan2.setVisible(false);
            pan3.setVisible(false);
            revalidate();
            repaint();
         } else if(count == 1) {
            prev.setVisible(true);
            next.setVisible(true);
            pan1.setVisible(false);
            pan2.setVisible(true);
            pan3.setVisible(false);
            revalidate();
            repaint();
         } else if(count == 2) {
            prev.setVisible(true);
            next.setVisible(false);
            pan1.setVisible(false);
            pan2.setVisible(false);
            pan3.setVisible(true);
            revalidate();
            repaint();
         }
      }
   }

//   public static void main(String[] args) {
//      new GameHowTo().setVisible(true);
//   }

}