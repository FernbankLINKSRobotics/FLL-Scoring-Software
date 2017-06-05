package com.fernbanklinks.frames;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;

/**
 * Created by Jonathan on 6/1/2017.
 */
public class AudienceScoringDisplayFrame extends JFrame implements ActionListener, KeyListener {
    private JTable table;

    private JLabel label = new JLabel("Fernbank LINKS FLL Summer Camp Tournament"),
                    icon = new JLabel();

    private JPanel mainPanel = new JPanel();

    public AudienceScoringDisplayFrame(JTable tableToDisplay) throws Exception{
        setLayout(new FlowLayout());

        Border paddingBorder = BorderFactory.createEmptyBorder(10,10,10,10);

        table = tableToDisplay;

        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.setPreferredSize(new Dimension(1100,900));

        table.setFont(new Font("Oswald", Font.PLAIN, 20));
        table.setRowHeight(30);
        table.setAlignmentX(CENTER_ALIGNMENT);
        table.setCellSelectionEnabled(false);
        table.setFocusable(false);

        setSize(1200, 800);
        addKeyListener(this);

        setResizable(false);

        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setUndecorated(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        label.setFont(new Font("Bebas Neue Regular", Font.PLAIN, 30));
        label.setBorder(paddingBorder);
        //label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setAlignmentX(CENTER_ALIGNMENT);
        String imageName = "C:/Users/Jonathan/Documents/General Code/LINKS_logo_tp.png";

        ImageIcon image = new ImageIcon(ImageIO.read(new File(imageName)));
        Image image1 = image.getImage().getScaledInstance(224, 130, Image.SCALE_SMOOTH);
        image = new ImageIcon(image1);

        icon.setIcon(image);
        icon.setSize(200, 100);
        //icon.setHorizontalAlignment(SwingConstants.CENTER);
        icon.setAlignmentX(CENTER_ALIGNMENT);
        icon.setBorder(paddingBorder);

        mainPanel.add(icon);
        mainPanel.add(label);

        mainPanel.add(table);


        add(mainPanel);


    }

    public void actionPerformed(ActionEvent event){

    }

    public void keyTyped(KeyEvent event){

    }

    public void keyPressed(KeyEvent event){
        if(event.getKeyCode() == KeyEvent.VK_Q){
            System.exit(0);
        } else if(event.getKeyCode() == KeyEvent.VK_F){
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setUndecorated(true);
        }
    }

    public void keyReleased(KeyEvent event){

    }
}
