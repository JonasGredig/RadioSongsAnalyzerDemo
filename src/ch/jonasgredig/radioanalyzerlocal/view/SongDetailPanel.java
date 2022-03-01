package ch.jonasgredig.radioanalyzerlocal.view;

import ch.jonasgredig.radioanalyzerlocal.model.Song;

import javax.swing.*;
import java.awt.*;

public class SongDetailPanel extends JPanel {

    JLabel id = new JLabel();
    JLabel artist = new JLabel();
    JLabel songName = new JLabel();
    JLabel timeStamp = new JLabel();

    public SongDetailPanel(Song song) {
        super();
        setLayout(new GridLayout(7, 1));
        add(id);
        add(artist);
        add(songName);
        add(timeStamp);
        add(new JLabel(""));
        add(new JLabel(""));
        add(new JLabel(""));
        updatePanel(song);
    }

    public void updatePanel(Song song) {
        id.setText("ID: " + song.getId());
        artist.setText("Artist: " + song.getArtist());
        songName.setText("Songname: " + song.getSongName());
        timeStamp.setText("Time: " + song.getTimestamp());
    }

}
