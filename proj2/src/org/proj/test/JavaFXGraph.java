//package org.proj.test;
//
//import static org.proj.Resource.FRAME_HEIGHT;
//import static org.proj.Resource.FRAME_WIDTH;
//
//import java.awt.BorderLayout;
//import java.awt.Container;
//
//import javax.swing.JButton;
//import javax.swing.JFrame;
//import javax.swing.JPanel;
//
//import javafx.application.Platform;
//import javafx.embed.swing.JFXPanel;
//import javafx.scene.Scene;
//import javafx.scene.chart.CategoryAxis;
//import javafx.scene.chart.LineChart;
//import javafx.scene.chart.NumberAxis;
//import javafx.scene.chart.XYChart;
//
//public class JavaFXGraph extends JPanel{
//	private static final long serialVersionUID = 1L;
//	public void initAndLoadWebView(JFXPanel pnCenter) {
////		public void initAndLoadWebView() {
//		
//		// Frame에 pnCenter 붙임
//		this.add(pnCenter);
//		// x축은 카테고리로 설정
//		final CategoryAxis xAxis = new CategoryAxis();
//		// y축은 숫자
//		final NumberAxis yAxis = new NumberAxis(0,100,10);
//		yAxis.setUpperBound(100);
//		yAxis.setTickUnit(100/10);
//		// x축에 Month 라벨 추가
//		xAxis.setLabel("Month");
//		
//        // LineChart 생성 (X축 String, y축 Number)
//        final LineChart<String, Number> chart = new LineChart<>(xAxis,yAxis);
//        // 그래프 제목
//        
//        XYChart.Series series = new XYChart.Series();
//       // 그래프(series) 데이터 이름
//        series.setName("My graph");
//        // 데이터 추가
//        series.getData().add(new XYChart.Data("Jan", 23));
//        series.getData().add(new XYChart.Data("Feb", 14));
//        series.getData().add(new XYChart.Data("Mar", 15));
//        series.getData().add(new XYChart.Data("Apr", 24));
//        series.getData().add(new XYChart.Data("May", 34));
//        series.getData().add(new XYChart.Data("Jun", 36));
//        series.getData().add(new XYChart.Data("Jul", 22));
//        series.getData().add(new XYChart.Data("Aug", 45));
//        series.getData().add(new XYChart.Data("Sep", 43));
//        series.getData().add(new XYChart.Data("Oct", 17));
//        series.getData().add(new XYChart.Data("Nov", 29));
//        series.getData().add(new XYChart.Data("Dec", 25));
//        
//        chart.getData().add(series);
//        // scene에 chart를 붙임
//        Scene scene  = new Scene(chart,800,600);
//        // 판낼에 scene 붙임
//        pnCenter.setScene(scene);
//        
//	}
//	
//	public void display() {
//		JFXPanel pnCenter = new JFXPanel();
//		Platform.runLater(new Runnable() {
//			public void run() {
//				try {
//					Thread.sleep(100);
//					initAndLoadWebView(pnCenter);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//			}
//		});
//		
////		initAndLoadWebView(pnCenter);
//	}
//	public static void main(String[] args) {
//		new Test03();
//	}
//}
//
//class Test03 extends JFrame {
//	Container contentPane;
//	JButton btn = new JButton("btn");
//	public Test03() {
//		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
//		contentPane = getContentPane();
////		contentPane.setLayout(null);
//		JavaFXGraph fx = new JavaFXGraph();
//		fx.display();
////		this.add(cp,BorderLayout.CENTER);
//		this.add(fx,BorderLayout.CENTER);
//		this.add(btn,BorderLayout.SOUTH);
//		this.setVisible(true);
//	}
//	
//	
//}
