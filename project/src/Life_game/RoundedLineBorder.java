package Life_game;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class RoundedLineBorder extends JPanel {

  public RoundedLineBorder() {
    super(true);
    setLayout(new BorderLayout());

    JLabel label = new JLabel("Rounded Corners");

    label.setHorizontalAlignment(JLabel.CENTER);

    LineBorder line = new LineBorder(Color.blue, 10, true);

    label.setBorder(line);

    add(label, BorderLayout.CENTER);
  }

  public static void main(String s[]) {
    JFrame frame = new JFrame("Rounded Line Border");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 200);
    frame.setContentPane(new RoundedLineBorder());
    frame.setVisible(true);
  }
}
