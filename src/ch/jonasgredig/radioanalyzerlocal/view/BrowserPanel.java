package ch.jonasgredig.radioanalyzerlocal.view;

import ch.jonasgredig.radioanalyzerlocal.SongsReader;
import ch.jonasgredig.radioanalyzerlocal.model.Song;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.List;


public class BrowserPanel extends JPanel {

    private List<Song> songs;
    private int songIndex = 0;

    private JButton first = new JButton("<<<");
    private JButton last = new JButton(">>>");
    private JButton previous = new JButton("<");
    private JButton next = new JButton(">");
    private JButton random = new JButton("¿ Random ?");

    private SongDetailPanel songDetailPanel;

    public BrowserPanel() {
        super();
        TitledBorder border = BorderFactory.createTitledBorder("Song Browser");
        border.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBorder(border);
        this.songs = SongsReader.getSongs();
        this.songDetailPanel = new SongDetailPanel(songs.get(0));
        this.setLayout(new BorderLayout());
        this.add(this.songDetailPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BorderLayout());

        this.previous.addActionListener(e -> previousAction());

        this.next.addActionListener(e -> nextAction());

        this.random.addActionListener(e -> randomAction());
        this.first.addActionListener(e -> firstAction());
        this.last.addActionListener(e -> lastAction());

        buttonPanel.add(this.previous, BorderLayout.WEST);
        JPanel innerButtonPanel = new JPanel();

        innerButtonPanel.setLayout(new BorderLayout());
        innerButtonPanel.add(this.random, BorderLayout.CENTER);
        innerButtonPanel.add(this.first, BorderLayout.WEST);
        innerButtonPanel.add(this.last, BorderLayout.EAST);

        buttonPanel.add(innerButtonPanel, BorderLayout.CENTER);
        buttonPanel.add(this.next, BorderLayout.EAST);
        handleButtons(songIndex);
        this.add(buttonPanel, BorderLayout.SOUTH);
    }

    private void randomAction() {
        int min = 0;
        int max = songs.size()-1;
        this.songIndex = (int)Math.floor(Math.random()*(max-min+1)+min);
        this.songDetailPanel.updatePanel(songs.get(this.songIndex));
        handleButtons(this.songIndex);
        repaint();
    }

    private void nextAction() {
        this.songIndex++;
        this.songDetailPanel.updatePanel(songs.get(this.songIndex));
        this.handleButtons(songIndex);
        this.repaint();
    }

    private void previousAction() {
        this.songIndex--;
        this.songDetailPanel.updatePanel(songs.get(this.songIndex));
        this.handleButtons(songIndex);
        this.repaint();
    }

    private void lastAction() {
        this.songIndex = songs.size()-1;
        this.songDetailPanel.updatePanel(songs.get(this.songIndex));
        this.handleButtons(songIndex);
        this.repaint();
    }

    private void firstAction() {
        this.songIndex = 0;
        this.songDetailPanel.updatePanel(songs.get(this.songIndex));
        this.handleButtons(songIndex);
        this.repaint();
    }

    private void handleButtons(int songIndex) {
        if (songIndex <= 0) {
            this.previous.setEnabled(false);
            this.first.setEnabled(false);
        } else {
            this.previous.setEnabled(true);
            this.first.setEnabled(true);
        }
        if (songIndex < (this.songs.size() - 1)) {
            this.next.setEnabled(true);
            this.last.setEnabled(true);
        } else {
            this.next.setEnabled(false);
            this.last.setEnabled(false);
        }
    }
}
