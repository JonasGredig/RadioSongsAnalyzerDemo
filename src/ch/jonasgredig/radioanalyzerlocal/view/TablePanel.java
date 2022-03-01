package ch.jonasgredig.radioanalyzerlocal.view;

import ch.jonasgredig.radioanalyzerlocal.SongsReader;
import ch.jonasgredig.radioanalyzerlocal.model.Song;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.List;


public class TablePanel extends JPanel {
    private JTextField filterTextField = new JTextField();

    private JTable table;
    private String column[] = {"ID", "ARTIST", "SONGNAME", "TIMESTAMP"};

    private TableRowSorter<TableModel> sorter;


    public TablePanel() {
        TitledBorder border = BorderFactory.createTitledBorder("Table");
        border.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setBorder(border);

        List<Song> songs = SongsReader.getSongs();
        String data[][] = new String[songs.size()][4];
        setLayout(new BorderLayout());
        data = fillData(songs, data);
        table = new JTable(data, column);
        JPanel textfields = new JPanel();
        textfields.setLayout(new FlowLayout());

        filterTextField.setColumns(30);
        filterTextField.addActionListener(e -> onChange());

        JPanel filterPanel = new JPanel();
        filterPanel.setLayout(new BorderLayout());
        filterPanel.add(new JLabel("Filter: "), BorderLayout.WEST);
        filterPanel.add(filterTextField, BorderLayout.CENTER);
        textfields.add(filterPanel);

        TableModel model = new DefaultTableModel(fillData(songs, new String[songs.size()][4]), column) {
            public Class getColumnClass(int column) {
                Class returnValue;
                if ((column >= 0) && (column < getColumnCount())) {
                    returnValue = getValueAt(0, column).getClass();
                } else {
                    returnValue = Object.class;
                }
                return returnValue;
            }
        };
        sorter = new TableRowSorter<TableModel>(model);

        table.setRowSorter(sorter);
        table.getColumnModel().getColumn(0).setWidth(25);
        table.getColumnModel().getColumn(0).setMinWidth(25);
        table.getColumnModel().getColumn(0).setMaxWidth(105);
        table.getColumnModel().getColumn(1).setWidth(180);
        table.getColumnModel().getColumn(1).setMinWidth(180);
        table.getColumnModel().getColumn(2).setWidth(180);
        table.getColumnModel().getColumn(2).setMinWidth(180);
        table.getColumnModel().getColumn(3).setWidth(120);
        table.getColumnModel().getColumn(3).setMinWidth(120);
        JScrollPane scrollableTextArea = new JScrollPane(table);

        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(textfields, BorderLayout.NORTH);
        add(scrollableTextArea, BorderLayout.SOUTH);
    }

    private String[][] fillData(List<Song> songs, String[][] data) {
        int i = 0;
        for (Song song : songs) {
            data[i][0] = String.valueOf(song.getId());
            data[i][1] = song.getArtist();
            data[i][2] = song.getSongName();
            data[i][3] = song.getTimestamp();
            i++;
        }
        return data;
    }

    private void onChange() {

        if (filterTextField.getText().equals("")) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("\\b" + filterTextField.getText().toUpperCase() + "\\b"));
        }
    }
}
