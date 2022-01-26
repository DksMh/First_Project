package select_color_swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SelectColor extends JPanel implements ActionListener {
	private ImageIcon bgImg;
	private JLabel bgImgPan;

	private ImageIcon bgSK;
	private JLabel bgSKPan;

	private JButton btn1;
	private JButton btn2;
	private JButton btn3;

	private Font font1;
	private Font font2;
	private JLabel txtTitle;
	private JLabel txtColor;
	
	private JLabel checkLabel;
	private JLabel xLabel;

	private int w = 720;
	private int h = 425;
	private int x = (int) (w / 2);
	private int y = (int) (h / 2);

	SelectColorConsole scc;

	public SelectColor() {
		scc = new SelectColorConsole();
		System.out.println(scc.arrBtn[0]);
		this.setLayout(null);

		bgImg = new ImageIcon("images/gamebg.png");
		bgImgPan = new JLabel(bgImg);
		bgImgPan.setSize(1024, 768);

		bgSK = new ImageIcon("images/sk.png");
		bgSKPan = new JLabel(bgSK);
		bgSKPan.setBounds(150, 150, 720, 425);

		btn1 = new JButton();
		btn2 = new JButton();
		btn3 = new JButton();
		btn1.setBounds(100, 200, 100, 150);
		btn2.setBounds(300, 200, 100, 150);
		btn3.setBounds(500, 200, 100, 150);
		btn1.setBackground(scc.col[scc.arrBtn[0]]);
		btn2.setBackground(scc.col[scc.arrBtn[1]]);
		btn3.setBackground(scc.col[scc.arrBtn[2]]);
		btn1.addActionListener(this);
		btn2.addActionListener(this);
		btn3.addActionListener(this);
		
		ImageIcon checkIcon = new ImageIcon("images/o.png");
		checkLabel = new JLabel(checkIcon);
		ImageIcon xIcon = new ImageIcon("images/x.png");
		xLabel = new JLabel(xIcon);

		if (scc.ansColor == 0) {
			txtColor = new JLabel("������");
		} else if (scc.ansColor == 1) {
			txtColor = new JLabel("��Ȳ��");
		} else if (scc.ansColor == 2) {
			txtColor = new JLabel("�����");
		} else if (scc.ansColor == 3) {
			txtColor = new JLabel("�ʷϻ�");
		} else if (scc.ansColor == 4) {
			txtColor = new JLabel("�Ķ���");
		} else if (scc.ansColor == 5) {
			txtColor = new JLabel("��ȫ��");
		} else {
			txtColor = new JLabel("���ֻ�");
		}
		font1 = new Font("���� ���", Font.BOLD, 44);
		txtColor.setFont(font1);
		txtColor.setForeground(scc.paintTxt());
		txtColor.setBounds(300, 70, 500, 100);

		txtTitle = new JLabel("�˸��� ���� �������ּ���");
		font2 = new Font("���� ���", Font.BOLD, 20);
		txtTitle.setFont(font2);
		txtTitle.setForeground(Color.black);
		txtTitle.setBounds(250, 20, 500, 100);
		
		checkLabel.setBounds(670, 65, 150, 150);
		this.add(checkLabel);
		checkLabel.setVisible(false);
		xLabel.setBounds(670, 65, 150, 150);
		this.add(xLabel);
		xLabel.setVisible(false);

		bgSKPan.add(txtTitle);
		bgSKPan.add(txtColor);
		bgSKPan.add(btn1);
		bgSKPan.add(btn2);
		bgSKPan.add(btn3);
		bgImgPan.add(bgSKPan);
		this.add(bgImgPan);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btn1) {
			if (scc.ansColor == scc.arrBtn[0]) {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
		
		if (e.getSource() == btn2) {
			if (scc.ansColor == scc.arrBtn[1]) {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
		
		if (e.getSource() == btn3) {
			if (scc.ansColor == scc.arrBtn[2]) {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				checkLabel.setVisible(true);
				revalidate();
				repaint();
			} else {
//				JOptionPane.showMessageDialog(bgSKPan, "����");
				xLabel.setVisible(true);
				revalidate();
				repaint();
			}
		}
	}

}