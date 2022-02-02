package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.LineBorder;

public class LabelMover {

    public static void main(String[] args) {
        new LabelMover();
    }

    public LabelMover() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                }

                JFrame frame = new JFrame("Test");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new BasePane());
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }

        });
    }

    public class BasePane extends JPanel {

        private JLabel label;

        public BasePane() {
            setLayout(new GridLayout(2, 2));
            MouseHandler handler = new MouseHandler();
            for (int index = 0; index < 4; index++) {
                CellPane pane = new CellPane();
                pane.addMouseListener(handler);
                add(pane);
            }
            label = new JLabel("Move");
            label.setHorizontalAlignment(JLabel.CENTER);
            ((CellPane)getComponent(0)).add(label);
        }

        public class MouseHandler extends MouseAdapter {

            @Override
            public void mousePressed(MouseEvent e) {
                Component source = e.getComponent();
                if (!label.getParent().equals(source)) {
                    ((CellPane)source).add(label);
                    revalidate();
                    repaint();
                }
            }

        }

        public class CellPane extends JPanel {

            public CellPane() {
                setLayout(new BorderLayout());
                setBorder(new LineBorder(Color.GRAY));
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(50, 50);
            }

        }        
    }    
}