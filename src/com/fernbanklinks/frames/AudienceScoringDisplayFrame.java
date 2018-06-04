package com.fernbanklinks.frames;

import com.fernbanklinks.labels.MatchClock;
import com.fernbanklinks.labels.MatchCounter;
import com.fernbanklinks.main.Main;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class AudienceScoringDisplayFrame extends JFrame implements ActionListener, KeyListener {

	private static final long serialVersionUID = -2714794756289966809L;

	private JTable table;

    private JLabel label = new JLabel("Fernbank LINKS FLL Summer Camp Tournament"),
                    icon = new JLabel();

    private MatchClock clock;

    private JPanel mainPanel = new JPanel(),
                   topPanel = new JPanel();

    private MatchCounter matchDisplay;


    public AudienceScoringDisplayFrame(JTable tableToDisplay, MatchCounter counter, MatchClock matchClock) throws Exception{


        setLayout(new FlowLayout());

        matchDisplay = counter;
        clock = matchClock;

        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        table = tableToDisplay;

        setupTable();
        setupTopPanel(paddingBorder);

        setSize(1200, 800);

        //Enables us to use the keyboard
        addKeyListener(this);

        setResizable(false);

        //Full-Screen Stuff
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//Makes the Close Button do Nothing

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        label.setFont(new Font("Bebas Neue", Font.BOLD, 70));
        label.setBorder(paddingBorder);
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(CENTER_ALIGNMENT);

        mainPanel.add(topPanel);
        mainPanel.add(label);
        mainPanel.add(table);


        add(mainPanel);
    }

    //This method will setup the JTable that will be displayed on the screen
    private void setupTable(){
        table.setGridColor(Color.BLACK);
        table.setPreferredSize(new Dimension(1400, 720));
        table.getColumnModel().getColumn(0).setPreferredWidth(150);
        table.getColumnModel().getColumn(1).setPreferredWidth(500);


        for(int i = 2; i < 5; i++){
            table.getColumnModel().getColumn(i).setPreferredWidth(150);
        }
        table.getColumnModel().getColumn(5).setPreferredWidth(150);

        //table.setPreferredSize(new Dimension(1100,900));

        int fontSize = 40;
        if((Main.kteamCount-10) <= 0) { fontSize = 40; }
        else { fontSize = (int) 40-(Main.kteamCount-10); }
        
        table.setFont(new Font("Oswald", Font.PLAIN, fontSize));
        table.setRowHeight((int) 720/(Main.kteamCount+1));
        table.setAlignmentX(CENTER_ALIGNMENT);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);


        //table.getCellRenderer(0, 0).getTableCellRendererComponent()
        table.getTableHeader().setBackground(new Color(19, 57, 192));

        table.repaint();

    }

    //Replaces the Table with the Logo for the last round
    private void replaceTableWithLogo() throws IOException{
        remove(table);

        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource("com/fernbanklinks/main/logo.png"));
        Image image1 = image.getScaledInstance(250, 175, Image.SCALE_SMOOTH);

        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);


        icon.setIcon(new ImageIcon(image1));
        icon.setSize(200, 140);
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setBorder(paddingBorder);

        add(icon);
    }

    private void setupTopPanel(Border padding) throws IOException{
        //topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
        topPanel.setLayout(new GridLayout(1, 3, 3, 3));

        BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource("com/fernbanklinks/main/logo.png"));
        Image image1 = image.getScaledInstance(250, 175, Image.SCALE_SMOOTH);

        icon.setIcon(new ImageIcon(image1));
        icon.setSize(200, 140);
        icon.setHorizontalAlignment(JLabel.CENTER);
        icon.setBorder(padding);


        clock.setFont(new Font("Bebas Neue", Font.BOLD, 150));
        clock.setHorizontalAlignment(JLabel.CENTER);

        //tempMatchCounter.setAlignmentX(RIGHT_ALIGNMENT);



        topPanel.add(matchDisplay);
        topPanel.add(icon);
        topPanel.add(clock);

    }

    public void actionPerformed(ActionEvent event){

    }

    public void keyTyped(KeyEvent event){

    }

    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_Q
        || event.getKeyCode() == KeyEvent.VK_ESCAPE){
            System.exit(0);
        } 
        /*
        else if(event.getKeyCode() == KeyEvent.VK_F){
            try {
                replaceTableWithLogo();
            } catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
        */
    }

    public void keyReleased(KeyEvent event){

    }
}
