package com.fernbanklinks.tableModels;

import com.fernbanklinks.main.Competition;

import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;

public class ScoreEntryTableModel extends AbstractTableModel implements TableModelListener{

    private static final long serialVersionUID = 2801079444571157211L;
    
    private String[] columnNames = { "Rank", "Team Name", "Run 1",
                                   "Run 2", "Run 3", "High Score"};
    private Object[][] data;

    Competition competition;

    public ScoreEntryTableModel(Competition competition1){
        super();

        competition = competition1;

        Object[][] teamData = competition.getJTableData();
        data = new Object[teamData.length + 1][6];

        data[0] = columnNames;

        for(int i = 1; i < teamData.length + 1; i++){
            data[i] = teamData[i-1];
        }

        addTableModelListener(this);
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

    public void setValueAt(Object value, int row, int col){
        String val = value.toString();
        fireTableCellUpdated(row, col);

        if(row >= 1 && col >= 2){
            try {
                data[row][col] = Integer.parseInt(val);
                competition.updateDisplay(data);

                //competition.teams[row-1].updateScores(col, Integer.parseInt(val));
                //data[row][5] = competition.teams[row-1].getHighScore();


            } catch(Exception e){
                //Change Nothing
            }
        } else if(row >= 1 && col == 1) {
            data[row][col] = val;
            competition.updateDisplay(data);
        } else {
            data[row][col] = val;
        }
    }

    public boolean isCellEditable(int row, int col){
        return col >= 1 && col != 5;
    }


    public void tableChanged(TableModelEvent event){
        System.out.println("METHOD RAN" + event.getType());
        competition.updateDisplay(data);

    }

    @Override
    public void fireTableCellUpdated(int row, int column) {
        //System.out.println(row);

        tableChanged(new TableModelEvent(this, row, row, column, TableModelEvent.UPDATE));
    }


}
