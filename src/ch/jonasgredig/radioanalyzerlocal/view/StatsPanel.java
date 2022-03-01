package ch.jonasgredig.radioanalyzerlocal.view;

import ch.jonasgredig.radioanalyzerlocal.SongsReader;
import ch.jonasgredig.radioanalyzerlocal.model.Song;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.util.*;
import java.util.List;

public class StatsPanel extends JPanel {

    public StatsPanel() {
        setLayout(new GridLayout(12, 1));
        TitledBorder border = BorderFactory.createTitledBorder("Songs Statistic");
        border.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBorder(border);

        add(new JLabel("Entries: " + SongsReader.getSongs().size()));
        add(new JLabel(""));
        Map.Entry<String, Integer> favouriteArtist = getFavouriteArtist();
        add(new JLabel("Favourite Artist"));
        add(new JLabel(favouriteArtist.getKey()));
        add(new JLabel("Count all Songs: " + favouriteArtist.getValue()));
        Map.Entry<String, Integer> favouriteSongFromArtist = getFavouriteSongFromArtist(favouriteArtist.getKey());
        add(new JLabel("Most played: " + favouriteSongFromArtist.getKey()));
        add(new JLabel("Count: " + favouriteSongFromArtist.getValue()));
        add(new JLabel(""));

        Map.Entry<String, Integer> favouriteSong = getFavouriteSong();
        add(new JLabel("Favourite Song"));
        add(new JLabel(favouriteSong.getKey() + " - " + getFavouriteArtistFromSong(favouriteSong.getKey()).getKey()));
        add(new JLabel("Count: " + favouriteSong.getValue()));
    }

    private Map.Entry<String, Integer> getFavouriteSongFromArtist(String artist) {
        List<Song> songs = SongsReader.getSongs();
        List<Song> filteredSongs = new ArrayList();
        for (Song song : songs) {
            if (song.getArtist().equals(artist)) {
                filteredSongs.add(song);
            }
        }
        HashMap<String, Integer> counts = SongsReader.getUniqueSongsWithCount(filteredSongs);
        return getMaxEntry(counts);
    }

    private Map.Entry<String, Integer> getFavouriteArtistFromSong(String song) {
        List<Song> songs = SongsReader.getSongs();
        List<Song> filteredArtistsongs = new ArrayList();
        for (Song singleSong : songs) {
            if (singleSong.getSongName().equals(song)) {
                filteredArtistsongs.add(singleSong);
            }
        }
        HashMap<String, Integer> counts = SongsReader.getUniqueArtistsWithCount(filteredArtistsongs);
        return getMaxEntry(counts);
    }

    private Map.Entry<String, Integer> getFavouriteArtist() {
        List<Song> songs = SongsReader.getSongs();
        HashMap<String, Integer> artistCounts = SongsReader.getUniqueArtistsWithCount();
        return getMaxEntry(artistCounts);
    }

    private Map.Entry<String, Integer> getFavouriteSong() {
        HashMap<String, Integer> uniqueSongsCounts = SongsReader.getUniqueSongsWithCount();
        return getMaxEntry(uniqueSongsCounts);
    }

    private Map.Entry<String, Integer> getMaxEntry(HashMap<String, Integer> artistCounts) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : artistCounts.entrySet()) {
            if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                if (!entry.getKey().equals("RSO NACHTMIX")) {
                    maxEntry = entry;
                }
            }
        }
        return maxEntry;
    }

}
