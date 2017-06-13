package com.fernbanklinks.main;

import com.fernbanklinks.frames.AudienceScoringDisplayFrame;
import com.fernbanklinks.labels.MatchClock;
import com.fernbanklinks.labels.MatchCounter;
import com.fernbanklinks.tableModels.AudienceDisplayTableModel;
import com.fernbanklinks.tableModels.ScoreEntryTableModel;
import com.fernbanklinks.frames.ScoringEntryDisplay;

import javax.swing.*;

/**
 * This class is used to represent an actual FLL Tournament. A Competition creates
 * an Audience Display and a Score Entry Display, and manages the list of teams.
 */
public class Competition {
    private Team[] teams;

    private AudienceScoringDisplayFrame audienceDisplay;
    private ScoringEntryDisplay scoreEntryFrame;

    private AudienceDisplayTableModel audienceModel;
    private ScoreEntryTableModel scoreModel;

    private JTable audienceTable;
    private JTable entryTable;

    /**
     * This constructor will initialize the JTables with the entire list of teams.
     *
     * @param names
     * The names of the teams participating in the competition.
     */
    public Competition(String[] names, MatchCounter counter, MatchClock clock) {
        teams = new Team[names.length];

        for (int i = 0; i < names.length; i++) {
            teams[i] = new Team(names[i]);
        }

        audienceModel = new AudienceDisplayTableModel(this);
        scoreModel = new ScoreEntryTableModel(this);

        audienceTable = new JTable(audienceModel);
        entryTable = new JTable(scoreModel);

        try {
            audienceDisplay = new AudienceScoringDisplayFrame(audienceTable, counter, clock);
            scoreEntryFrame = new ScoringEntryDisplay(entryTable, counter, clock);

            audienceDisplay.setVisible(true);
            scoreEntryFrame.setVisible(true);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * This constructor will initialize a competition of x teams with blank name spots.
     *
     * @param numOfTeams
     * The number of teams participating in the competition.
     */
    public Competition(int numOfTeams, MatchCounter counter, MatchClock clock) {
        teams = new Team[numOfTeams];

        //Sets each of the team names to blanks.
        for (int i = 0; i < numOfTeams; i++) {
            teams[i] = new Team("");
        }

        audienceModel = new AudienceDisplayTableModel(this);
        scoreModel = new ScoreEntryTableModel(this);

        audienceTable = new JTable(audienceModel);
        entryTable = new JTable(scoreModel);

        //This is neccesary because grabbing the logo can lead to an ImageIO exception.
        try {
            audienceDisplay = new AudienceScoringDisplayFrame(audienceTable, counter, clock);
            scoreEntryFrame = new ScoringEntryDisplay(entryTable, counter, clock);

            audienceDisplay.setVisible(true);
            scoreEntryFrame.setVisible(true);
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }

    /**
     * Returns a 2-D array that will be used in the JTables to show the teams.
     * @return
     */
    public Object[][] getJTableData(){
        Object[][] returnArray = new Object[teams.length][6];

        for(int row = 0; row < teams.length; row++){
            returnArray[row] = teams[row].getArrayRepresentation();
            //System.out.println(returnArray[row].toString());
        }

        return returnArray;
    }

    /**
     * Updates the audience display with a sorted version of our data.
     *
     * @param newData
     * A 2-D array of the data on the score entry display. This includes
     * the headers.
     */
    public void updateDisplay(Object[][] newData){
       // sortTeams();
        audienceModel.updateData(newData);
        audienceModel.updateRankings();

        audienceTable.repaint(); //Redraws the Table to show our updated rankings.
    }

}
