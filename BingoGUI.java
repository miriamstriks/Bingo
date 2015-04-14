//Miriam Summers
//Lab Section 1

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;


public class BingoGUI extends JFrame implements ActionListener {
   
   private int numOfPlayers;
   private TextArea[] textAreas;
   private JFrame displayCard;
   private Container contentPane;
   
   /**
    * Sole constructor
    * @param players    number of Bingo games to display
    */
   public BingoGUI(){
      numOfPlayers = 0;
      displayCard = new JFrame("BINGO");
     
      //add menus and action listeners
      JMenuBar menuBar = new JMenuBar();
      JMenu file, play;
      JMenuItem newGame, quit, callNumber;
      file = new JMenu("File");
      play = new JMenu("Play");
      menuBar.add(file);
      menuBar.add(play);
      newGame = new JMenuItem("New Game");
      quit = new JMenuItem("Quit");
      callNumber = new JMenuItem("Call Number");
      callNumber.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
      newGame.addActionListener(this);
      quit.addActionListener(this);
      callNumber.addActionListener(this);
      file.add(newGame);
      file.add(quit);
      file.addSeparator();
      play.add(callNumber);
      play.addSeparator();
      
      displayCard.setSize(250*2,210);
      displayCard.setLocation(25, 100);
      displayCard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      contentPane = displayCard.getContentPane();
      contentPane.add(menuBar, BorderLayout.PAGE_START);
      displayCard.setVisible(true);
   }
      
    public void redraw(){
      //create a textArea for each player
      displayCard.setSize(250*numOfPlayers,210);
      JPanel textAreaPanel = new JPanel();
      textAreaPanel.setLayout(new GridLayout(1,numOfPlayers));
      textAreas = new TextArea[numOfPlayers];
      for(int i=0; i < numOfPlayers; i++){
         textAreas[i] = new TextArea();
         textAreas[i].setFont(new Font("Monospaced", Font.PLAIN,14));
         textAreas[i].setBackground(Color.BLACK);
         Random colorGenerator = new Random();
         textAreas[i].setForeground(new Color(colorGenerator.nextInt(256),colorGenerator.nextInt(256),colorGenerator.nextInt(256)).brighter().brighter());
         textAreaPanel.add(textAreas[i]);
      }
      contentPane.add(textAreaPanel);
   }
   
   public static void main (String[] args){

   } 
   
   public TextArea[] getTextAreas(){
      return textAreas;
   }
   
   public int getNumberOfPlayers(){
      return numOfPlayers;
   }
   
   public void setNumberOfPlayers(int n){
      numOfPlayers = n;
   }
   
   
   BingoGame bingogame;
   public void actionPerformed(ActionEvent event) {
      String  selectedMenu = event.getActionCommand();
      if (selectedMenu.equals("New Game")) {
        bingogame = new BingoGame();
        bingogame.newGame(); 
      } 
      else if (selectedMenu.equals("Quit"))
         System.exit(0);
      else if (selectedMenu.equals("Call Number"))
         try {
            bingogame.callNumber();
         } catch (Exception e) {
              JOptionPane.showMessageDialog(null, "Start a new game to call a number.");
        }
      } //actionPerformed
   
}
