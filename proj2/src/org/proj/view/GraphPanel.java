package org.proj.view;

import static org.proj.Resource.*;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GraphPanel extends JPanel {
   private int padding = 35;
   private int labelPadding = 15;// 라벨 패딩
   private Color lineColor = new Color(44, 102, 230, 180);
   private Color pointColor = new Color(100, 100, 100, 180);
   private Color gridColor = new Color(200, 200, 200, 200);
   private static final Stroke GRAPH_STROKE = new BasicStroke(2f); // 선두께
   private int pointWidth = 4; // 점크기
   private int numberYDivisions = 10; // y축 칸마다의 범위
   private List<Double> scores;
   private List<String> date;
   private int age;
   private double ageData;

   
   public GraphPanel(List<Double> scores, List<String> date, int age, double ageData) {
      this.scores = scores;
      this.date = date;	
      this.setBackground(Color.white);
      this.age = age;
      this.ageData = ageData;
      
   }

   @Override
//   protected void paintComponent(Graphics g) { // 스윙 컴포넌트가 자신의 모양을 그리는 메소드

   public void paint(Graphics g) {
	   super.paint(g);
	  Graphics2D g2 = (Graphics2D) g;
      g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

      double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (scores.size() - 1);
      double yScale = ((double) getHeight() - 2 * padding - labelPadding) /100;

      List<Point> graphPoints = new ArrayList<>();
      underAvgNum = 0;
      for (int i = 0; i < scores.size(); i++) {
         int x1 = (int) (i * xScale + padding + labelPadding);
         int y1 = (int) ((100 - scores.get(i)) * yScale + padding);
         graphPoints.add(new Point(x1, y1));
      }
     // .....중략
      
      
      
      
      
      // draw white background
      g2.setColor(Color.white); // 배경화면
      g2.fillRect(padding + labelPadding, padding, getWidth() - (2 * padding) - labelPadding,
            getHeight() - 2 * padding - labelPadding);
      g2.setColor(Color.BLACK);

      // create hatch marks and grid lines for y axis.
      for (int i = 0; i < numberYDivisions + 1; i++) {
         int x0 = padding + labelPadding;
         int x1 = pointWidth + padding + labelPadding;
         int y0 = getHeight()
               - ((i * (getHeight() - padding * 2 - labelPadding)) / numberYDivisions + padding + labelPadding);
         int y1 = y0;
         if (scores.size() > 0) {
            g2.setColor(gridColor);
            g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
            g2.setColor(Color.RED);
            String yLabel = i*10+"";
            FontMetrics metrics = g2.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
         } 	
         g2.drawLine(x0, y0, x1, y1);
      }

      // and for x axis
      for (int i = 0; i < scores.size(); i++) {
         if (scores.size() > 1) {
            int x0 = i * (getWidth() - padding * 2 - labelPadding) / (scores.size() - 1) + padding + labelPadding;
            int x1 = x0;
            int y0 = getHeight() - padding - labelPadding;
            int y1 = y0 - pointWidth;
            if ((i % ((int) ((scores.size() / 20.0)) + 1)) == 0) {
               g2.setColor(gridColor);
               g2.setColor(Color.BLACK);
               
               String xLabel = date.get(i);
               FontMetrics metrics = g2.getFontMetrics();
               int labelWidth = metrics.stringWidth(xLabel);
               g2.drawString(xLabel, x0 - labelWidth / 2, y0 + metrics.getHeight() + 3);
            }
            g2.drawLine(x0, y0, x1, y1);
         }
      }

      // create x and y axes
      g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
      g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);

      Stroke oldStroke = g2.getStroke();
      g2.setColor(lineColor);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         int y1 = graphPoints.get(i).y;
         int x2 = graphPoints.get(i + 1).x;
         int y2 = graphPoints.get(i + 1).y;
         g2.drawLine(x1, y1, x2, y2);
      }

      g2.setStroke(oldStroke);
      g2.setColor(pointColor);
      for (int i = 0; i < graphPoints.size(); i++) {
         int x = graphPoints.get(i).x - pointWidth / 2;
         int y = graphPoints.get(i).y - pointWidth / 2;
         int ovalW = pointWidth;
         int ovalH = pointWidth;
         g2.fillOval(x, y, ovalW, ovalH);
      }
      
      g2.setColor(Color.black);
      g2.setStroke(GRAPH_STROKE);
      for (int i = 0; i < graphPoints.size() - 1; i++) {
         int x1 = graphPoints.get(i).x;
         // 80은 평균점수
         int y1 = (int) ((100-ageData) * yScale + padding);
         int x2 = graphPoints.get(i + 1).x;
         int y2 = (int) ((100-ageData) * yScale + padding);
         g2.drawLine(x1, y1, x2, y2);
      }
      String str = age+"대 위험수준";
      g2.drawString(str, graphPoints.get(graphPoints.size()-2).x, (int) ((100-ageData) * yScale + padding) - 4);
   }

//   private static void createAndShowGui() {
//      List<Double> scores = new ArrayList<>();
//      List<String> date = new ArrayList<>();
//      
//      Test01 t1 = new Test01();
//      scores = t1.getScore();
//      date = t1.getDate();
//      
//      GraphPanel2 mainPanel = new GraphPanel2(scores, date);
//      mainPanel.setPreferredSize(new Dimension(800, 600));
//      JFrame frame = new JFrame("DrawGraph");
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//      frame.getContentPane().add(mainPanel);
//      frame.pack();
//      frame.setLocationRelativeTo(null);
//      frame.setVisible(true);
//   }

//   public static void main(String[] args) {
//      SwingUtilities.invokeLater(new Runnable() {
//         public void run() {
//            createAndShowGui();
//         }
//      });
//   }
}