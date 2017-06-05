package com.fernbanklinks.tableModels;

import com.fernbanklinks.main.Competition;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.util.Arrays;

/**
 * Created by Jonathan on 6/4/2017.
 */
public class AudienceDisplayTableModel extends AbstractTableModel  {
    private String[] columnNames = {"Rank", "Team Name", "Run 1",
            "Run 2", "Run 3", "High Score"};

    private Object[][] data;

    Competition competition;

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

    public boolean isCellEditable(int row, int col){ return false; }

    public void updateData(Object[][] newData){

        for(int row = 0; row < data.length-1; row++){
            for(int col = 0; col < data[0].length; col++){
                System.out.print(newData[row][col]);
                setValueAt(newData[row][col], row, col);
            }
            System.out.println();
        }

    }

    public void setValueAt(Object value, int row, int col){
        data[row][col] = value.toString();
    }

    public void updateRankings(){
       //Get High Scores
        for(int i = 1; i < data.length; i++){
            int run1 = Integer.parseInt(data[i][2].toString());
            int run2 = Integer.parseInt(data[i][3].toString());
            int run3 = Integer.parseInt(data[i][4].toString());

            int[] scores = {run1, run2, run3};
            Arrays.sort(scores);

            setValueAt(scores[2], i, 5);
        }

        //Rank the Rows
        for(int row1 = 2; row1 < data.length; row1++){
            for(int row2 = row1; row2 > 1; row2--){
                if(Integer.parseInt(data[row2][5].toString()) > Integer.parseInt(data[row2-1][5].toString())){
                    Object[] temp = data[row2];
                    data[row2] = data[row2-1];
                    data[row2-1] = temp;
                }
            }
        }

        for(int row = 1; row < data.length; row++){
            data[row][0] = row;
        }


    }




}
