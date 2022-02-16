package Miniplan.GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GUI {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame jFrame = new JFrame();

            jFrame.setTitle("Miniplan erstellen");
            jFrame.setSize(400, 400);
            jFrame.setLocationRelativeTo(null);
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setVisible(true);

            Container contentPane = jFrame.getContentPane();
            contentPane.setLayout(new FlowLayout());

            JButton jButton = new JButton("Speichern");
            jButton.setToolTipText("Speichern");

            contentPane.add(jButton);

            jButton.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("test");
                }

                @Override
                public void mousePressed(MouseEvent e) {

                }

                @Override
                public void mouseReleased(MouseEvent e) {

                }

                @Override
                public void mouseEntered(MouseEvent e) {

                }

                @Override
                public void mouseExited(MouseEvent e) {

                }
            });
        });
    }
}
