package com.fernbanklinks.tableModels;

import com.fernbanklinks.main.Competition;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;

/**
 * This table model is used for the Audience Display. It does not allow
 * its data to be edited dynamically from the GUI. Instead, it takes the
 * data from a ScoreEntryTableModel, sorts it, and displays it to the
 * audience.
 */
public class AudienceDisplayTableModel extends AbstractTableModel  {
    private String[] columnNames = {"Rank", "Team Name", "Run 1",
            "Run 2", "Run 3", "High Score"};

    private Object[][] data;

    Competition competition;

    /**
     * Sets up the audience table model to have a reference to
     * the current competition, so we can pull data to display.
     *
     * @param competition1
     * The current FLL Competition.
     */
    public AudienceDisplayTableModel(Competition competition1){
        competition = competition1;

        Object[][] teamData = competition.getJTableData();
        data = new Object[teamData.length + 1][6];

        data[0] = columnNames;

        for(int i = 1; i < teamData.length + 1; i++){
            data[i] = teamData[i-1];
        }

    }

    public int getColumnCount(){
        return columnNames.length;
    }

    public int getRowCount(){
        return data.length;
    }

    public Object getValueAt(int row, int column){
        return data[row][column];
    }

    public boolean isCellEditable(int row, int col){ return false; } //NO CELLS NEED TO BE GUI EDITED

    /**
     * Updates the data on the current table with some new data. This should be
     * called every time we get a new score.
     *
     * @param newData
     */
    public void updateData(Object[][] newData){

        for(int row = 0; row < data.length; row++){
            for(int col = 0; col < data[0].length; col++){
                System.out.print(newData[row][col]);

                /*
                We use this show the updates show up automatically
                on the audience display.
                 */
                setValueAt(newData[row][col], row, col);
            }
            System.out.println();
        }

    }

    /**
     * Overrides the blank implementation for an AbstractTableModel.
     *
     * @param value
     * The value that is being inserted into the table.
     *
     * @param row
     * The row that the new data should be placed into.
     *
     * @param col
     * The column that the new data should be placed into.
     */
    public void setValueAt(Object value, int row, int col){
        data[row][col] = value.toString();
    }

    /**
     * This method uses an insertion sort to rank the teams by their high score.
     */
    public void updateRankings(){
       //Get High Scores
        for(int i = 1; i < data.length; i++){
            int run1 = Integer.parseInt(data[i][2].toString());
            int run2 = Integer.parseInt(data[i][3].toString());
            int run3 = Integer.parseInt(data[i][4].toString());

            int[] scores = {run1, run2, run3};
            Arrays.sort(scores); //This will sort the scores.

            setValueAt(scores[2], i, 5); //Set the high score.
        }

        //Rank the Rows with Insertion Sort
        //We start at 2 because row 0 is the column names.
        for(int row1 = 2; row1 < data.length; row1++){
            for(int row2 = row1; row2 > 1; row2--){
                if(Integer.parseInt(data[row2][5].toString()) > Integer.parseInt(data[row2-1][5].toString())){
                    Object[] temp = data[row2];
                    data[row2] = data[row2-1];
                    data[row2-1] = temp;
                }
            }
        }

        //Places the ranks into the table.
        for(int row = 1; row < data.length; row++){
            setValueAt(row, row, 0);
        }
    }

}
