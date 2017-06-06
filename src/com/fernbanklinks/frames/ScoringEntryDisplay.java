package com.fernbanklinks.frames;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class ScoringEntryDisplay extends JFrame {
    private JTable table;

    public ScoringEntryDisplay(JTable workingTable){
        table = workingTable;
        table.setCellSelectionEnabled(true);

        setSize(600, 600);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);


        setLayout(new FlowLayout());


        add(table);
    }


}
