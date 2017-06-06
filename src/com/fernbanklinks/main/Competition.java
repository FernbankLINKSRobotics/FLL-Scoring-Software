package com.fernbanklinks.main;

import com.fernbanklinks.frames.AudienceScoringDisplayFrame;
import com.fernbanklinks.tableModels.AudienceDisplayTableModel;
import com.fernbanklinks.tableModels.ScoreEntryTableModel;
import com.fernbanklinks.frames.ScoringEntryDisplay;

import javax.swing.*;

/**
 * This class should be used to represent an actual FLL Competition!
 */
public class Competition {
    public Team[] teams;

    private AudienceScoringDisplayFrame audienceDisplay;
    private ScoringEntryDisplay scoreEntryFrame;

    private AudienceDisplayTableModel audienceModel;
    private ScoreEntryTableModel scoreModel;


     JTable audienceTable;
    JTable entryTable;

    public Competition(String[] names) {
        teams = new Team[names.length];

        for (int i = 0; i < names.length; i++) {
            teams[i] = new Team(names[i]);
        }

        audienceModel = new AudienceDisplayTableModel(this);
        scoreModel = new ScoreEntryTableModel(this);

        audienceTable = new JTable(audienceModel);
        entryTable = new JTable(scoreModel);

        try {
            audienceDisplay = new AudienceScoringDisplayFrame(audienceTable);
            scoreEntryFrame = new ScoringEntryDisplay(entryTable);

            audienceDisplay.setVisible(true);
            scoreEntryFrame.setVisible(true);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public Competition(int numOfTeams) {
        teams = new Team[numOfTeams];

        for (int i = 0; i < numOfTeams; i++) {
            teams[i] = new Team("");
        }

        audienceModel = new AudienceDisplayTableModel(this);
        scoreModel = new ScoreEntryTableModel(this);

        audienceTable = new JTable(audienceModel);
        entryTable = new JTable(scoreModel);

        try {
            audienceDisplay = new AudienceScoringDisplayFrame(audienceTable);
            scoreEntryFrame = new ScoringEntryDisplay(entryTable);

            audienceDisplay.setVisible(true);
            scoreEntryFrame.setVisible(true);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void sortTeams(){
        Team temp;

        for(int i = 1; i < teams.length; i++){
            for(int j = i; j > 0; j--){
                if(teams[j].getHighScore() > teams[j-1].getHighScore()){
                    temp = teams[j];
                    teams[j] = teams[j-1];
                    teams[j-1] = temp;
                }
            }
        }
    }

    public Object[][] getJTableData(){
        Object[][] returnArray = new Object[teams.length][6];

        for(int row = 0; row < teams.length; row++){
            returnArray[row] = teams[row].getArrayRepresentation();
            //System.out.println(returnArray[row].toString());
        }

        return returnArray;
    }

    public void updateDisplay(Object[][] newData){
       // sortTeams();
        audienceModel.updateData(newData);
        audienceModel.updateRankings();

        audienceTable.repaint();
    }

}
