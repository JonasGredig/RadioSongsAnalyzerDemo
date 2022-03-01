package ch.jonasgredig.radioanalyzerlocal.view;

import javax.swing.*;
import java.awt.*;

public class Analyzer {

    public Analyzer() {
        JFrame jFrame = new JFrame("Radio Analyzer");
        jFrame.setMinimumSize(new Dimension(800, 800));
        jFrame.setLayout(new BorderLayout());
        JPanel mainpanel = new JPanel();
        mainpanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        mainpanel.setLayout(new BorderLayout(5,5));
        JLabel title = new JLabel("Radio Analyzer");
        title.setFont(new Font("Helvetica", Font.BOLD, 30));
        mainpanel.add(title, BorderLayout.NORTH);
        mainpanel.add(new BrowserPanel(), BorderLayout.CENTER);
        mainpanel.add(new StatsPanel(), BorderLayout.EAST);
        mainpanel.add(new TablePanel(), BorderLayout.SOUTH);
        jFrame.add(mainpanel);
        jFrame.pack();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setVisible(true);
    }
}
