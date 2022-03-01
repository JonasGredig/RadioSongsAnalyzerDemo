package ch.jonasgredig.radioanalyzerlocal;

import ch.jonasgredig.radioanalyzerlocal.model.Song;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SongsReader {

    private static List<Song> songs;

    public static List<Song> getSongs() {
        if (songs == null) {
            songs = load();
        }
        return songs;
    }

    public static HashMap<String, Integer> getUniqueSongsWithCount() {
        return getUniqueSongsWithCount(songs);
    }
    public static HashMap<String, Integer> getUniqueSongsWithCount(List<Song> songs) {
        HashMap<String, Integer> uniqueSongsCounts = new HashMap<>();
        for (Song song : songs) {
            if (uniqueSongsCounts.containsKey(song.getSongName())) {
                uniqueSongsCounts.put(song.getSongName(), uniqueSongsCounts.get(song.getSongName()) + 1);
            } else {
                uniqueSongsCounts.put(song.getSongName(), 1);
            }
        }
        return uniqueSongsCounts;
    }

    public static HashMap<String, Integer> getUniqueArtistsWithCount() {
        return getUniqueArtistsWithCount(songs);
    }

    public static HashMap<String, Integer> getUniqueArtistsWithCount(List<Song> songs) {
        HashMap<String, Integer> artistCounts = new HashMap<>();
        for (Song song : songs) {
            if (artistCounts.containsKey(song.getArtist())) {
                artistCounts.put(song.getArtist(), artistCounts.get(song.getArtist()) + 1);
            } else {
                artistCounts.put(song.getArtist(), 1);
            }
        }
        return artistCounts;
    }

    private static List<Song> load() {
        try {
            List<Song> songs = new ArrayList<>();
            BufferedReader csvReader = new BufferedReader(new FileReader("songs.csv"));
            String row;
            while ((row = csvReader.readLine()) != null) {
                String[] data = row.split("\",\"");
                songs.add(new Song(Integer.parseInt(data[0].replace("\"", "")), data[2].replace("\"", ""), data[1].replace("\"", ""), data[3].replace("\"", "")));
            }
            csvReader.close();

            return songs;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
