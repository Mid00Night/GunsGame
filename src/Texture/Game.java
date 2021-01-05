package Texture;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;


public class Game extends JFrame implements ActionListener {
 Dimension tk ;
    JPanel jp, cont, jp2, jp1, howto;
    JLabel jLabel1 = new JLabel();
    JLabel jLabel2 = new JLabel();
    JLabel jLabel3 = new JLabel();
    JLabel jLabel4 = new JLabel();
    JButton single, quit, high, back, normal, hard, multi, how, full,min;
    CardLayout card = new CardLayout();

    public static void main(String[] args) throws IOException {
        gameplay();

    }

    public Game() {
        
        cont = new JPanel();
        jp = new JPanel();
        jp1 = new JPanel();
        jp2 = new JPanel();
        howto = new JPanel();
        GridLayout layout1 = new GridLayout(11, 1);
        layout1.setVgap(25);
       
       
        cont.setLayout(card);
        jp.setLayout(null);
        jp2.setLayout(null);
        jp1.setLayout(null);
        howto.setLayout(null);

        full = new GradientButton("Max");
        full.setActionCommand("full");
        full.addActionListener(this);
        full.setFont(new Font("Felix Titling", 1, 12));
        full.setForeground(Color.WHITE);
        jp.add(full);
        full.setBounds(600, 30, 70, 40);
        
        min = new GradientButton("Min");
        min.setActionCommand("min");
        min.addActionListener(this);
        min.setFont(new Font("Felix Titling", 1, 12));
        min.setForeground(Color.WHITE);
        jp.add(min);

        single = new GradientButton("Single Player");
        single.setActionCommand("play");
        single.addActionListener(this);
        single.setFont(new Font("Felix Titling", 1, 14));
        single.setForeground(Color.WHITE);
        jp.add(single);
        single.setBounds(250, 40, 150, 70);

        multi = new GradientButton("Multiplayer");
        multi.setActionCommand("mult");
        multi.addActionListener(this);
        multi.setFont(new Font("Felix Titling", 1, 14));
        multi.setForeground(Color.WHITE);
        jp.add(multi);
        multi.setBounds(250, 170, 150, 70);

        how = new GradientButton("Instraction");
        how.setActionCommand("how");
        how.addActionListener(this);
        how.setFont(new Font("Felix Titling", 1, 14));
        how.setForeground(Color.WHITE);
        jp.add(how);
        how.setBounds(250, 300, 150, 70);

        quit = new GradientButton("Quit");
        quit.setActionCommand("stop");
        quit.addActionListener(this);
        quit.setFont(new Font("Felix Titling", 1, 14));
        quit.setForeground(Color.WHITE);
        jp.add(quit);
        quit.setBounds(250, 430, 150, 70);

        high = new GradientButton("high score");
        high.setActionCommand("high");
        high.addActionListener(this);
        high.setFont(new Font("Felix Titling", 1, 14));
        high.setForeground(Color.WHITE);
        jp.add(high);
        high.setBounds(250, 560, 150, 70);

        jLabel1.setIcon(new ImageIcon("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\Assets\\zombie-halloween-background-vector-26154447.jpg")); // NOI18N
        
        jp.add(jLabel1);
        jLabel1.setBounds(0, 0, 700, 700);
//
        normal = new GradientButton("normal");
        normal.setActionCommand("normal");
        normal.addActionListener(this);
        normal.setFont(new Font("Felix Titling", 1, 14));
        normal.setForeground(Color.WHITE);
        jp2.add(normal);
        normal.setBounds(250, 40, 150, 70);

        hard = new GradientButton("hard");
        hard.setActionCommand("hard");
        hard.addActionListener(this);
        hard.setFont(new Font("Felix Titling", 1, 14));
        hard.setForeground(Color.WHITE);
        jp2.add(hard);
        hard.setBounds(250, 170, 150, 70);

        back = new GradientButton("back");
        back.setActionCommand("back");
        back.addActionListener(this);
        back.setFont(new Font("Felix Titling", 1, 14));
        back.setForeground(Color.WHITE);
        jp2.add(back);
        back.setBounds(250, 430, 150, 70);

        jLabel2.setIcon(new ImageIcon("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\Assets\\zombie-halloween-background-vector-26154447.jpg")); // NOI18N
        jp2.add(jLabel2);
        jLabel2.setBounds(0, 0, 700, 700);
        //this.setLayout(null);
        cont.add(jp);
        cont.add(jp2);

        this.add(cont);

        //card.show(cont,"2");
        tk = Toolkit.getDefaultToolkit().getScreenSize();
        setTitle("Guns Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 700);

        setLocationRelativeTo(null);
        setVisible(true);
        setFocusable(true);

    }

    public void play() {
        new Anim();
        this.dispose();
    }

    public void full_screan() {
        tk = Toolkit.getDefaultToolkit().getScreenSize();
       
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        
       
       // Toolkit tk = Toolkit.getDefaultToolkit();
        setBounds(100, 100, (int) tk.getWidth() , (int) tk.getHeight());
         setLocationRelativeTo(null);
        
        
        
        min.setBounds((int) tk.getWidth()-150, 30, 70, 40);
    
         full.setBounds(-100, 30, 70, 40);
        
        single.setBounds((int) (tk.getWidth()/2)-75, 40, 150, 70);
        
        multi.setBounds((int) (tk.getWidth()/2)-75, 170, 150, 70);

       
        how.setBounds((int) (tk.getWidth()/2)-75, 300, 150, 70);

        
        quit.setBounds((int) (tk.getWidth()/2)-75, 430, 150, 70);

        high.setBounds((int) (tk.getWidth()/2)-75, 560, 150, 70);

     
        jLabel1.setBounds(50, 0 ,(int) tk.getWidth(),(int) tk.getHeight());
//
       
        normal.setBounds((int) (tk.getWidth()/2)-75, 40, 150, 70);

      
        hard.setBounds((int) (tk.getWidth()/2)-75, 170, 150, 70);

        
        back.setBounds((int) (tk.getWidth()/2)-75, 430, 150, 70);

       
        jLabel2.setBounds(50, 0 ,(int) tk.getWidth(),(int) tk.getHeight());
        this.setSize((int) tk.getWidth(), (int) tk.getHeight());
    }
    public void min_screan() {
        tk = Toolkit.getDefaultToolkit().getScreenSize();
         full.setBounds(600, 30, 70, 40);
    
        single.setBounds(250, 40, 150, 70);

      
        multi.setBounds(250, 170, 150, 70);


        how.setBounds(250, 300, 150, 70);

    
        quit.setBounds(250, 430, 150, 70);

  
        high.setBounds(250, 560, 150, 70);

        jLabel1.setBounds(0, 0, 700, 700);
//
        normal.setBounds(250, 40, 150, 70);

    
        hard.setBounds(250, 170, 150, 70);

      
        back.setBounds(250, 430, 150, 70);

        jLabel2.setBounds(0, 0, 700, 700);
        this.setSize(700,700);
    }

    public void play(boolean a) {
        new Anim(a);
        this.dispose();
    }

    public void playmult() {
        new Anim(1);
        this.dispose();
    }

    public void stop() {
        System.exit(1);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand() == "play") {
            //  jp1.removeAll();
            cont.removeAll();
            cont.repaint();

//            jp2.add(ea); // normal
//            jp2.add(f); //hard 
//           jp2.add(d); //back

            //System.out.println(cont.getComponent(0));
            cont.add(jp2);

            cont.repaint();

            this.repaint();
            this.setVisible(true);
        } else if (e.getActionCommand() == "stop") {
            stop();
        }  else if (e.getActionCommand() == "full") {
            full_screan();
        }  else if (e.getActionCommand() == "min") {
            min_screan();
        } else if (e.getActionCommand() == "high") {

            cont.removeAll();
            cont.repaint();
            String score[] = null;
            try {
                score = WriteFile.getHigh();
            } catch (IOException ex) {
                Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            }
            JLabel row[] = new JLabel[score.length];

            for (int i = 0; i < score.length; i += 2) {
                System.out.println(i);
                row[i] = new JLabel(score[i] + " :" + score[i + 1]);
                  row[i].setFont(new Font("Times New Roman", 1, 14));
            row[i].setForeground(Color.WHITE);
                 row[i].setBounds( 280, 30*i+50, 150, 70);
                 
                jp1.add(row[i]);
            }
            

            jp1.add(back);
            //System.out.println(cont.getComponent(0));
            jLabel4.setIcon(new ImageIcon("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\Assets\\zombie-halloween-background-vector-26154447.jpg")); // NOI18N
            jp1.add(jLabel4);
            
            jLabel4.setBounds(0, 0 ,(int) tk.getWidth(),700);
            cont.add(jp1);

            cont.repaint();

            this.repaint();
            this.setVisible(true);
        } else if (e.getActionCommand() == "back") {
            jp1.removeAll();
            //System.out.println(tk.getWidth()); tkw=1366,,,tkh=768
            cont.removeAll();
            cont.repaint();
            cont.add(jp);
            this.setVisible(true);
        } else if (e.getActionCommand() == "hard") {
            play();
        } else if (e.getActionCommand() == "normal") {
            play(true);
        } else if (e.getActionCommand() == "mult") {
            playmult();
        } else if (e.getActionCommand() == "how") {
            howto.removeAll();
            cont.removeAll();
            JLabel row = new JLabel("press [ left ] to move left ,and [ right ] to move right");
           row.setFont(new Font("Times New Roman", 1, 12));
            row.setForeground(Color.WHITE);
            row.setBounds(250, 50, 600, 70);
            JLabel row1 = new JLabel("press [p] to pause");
           row1.setFont(new Font("Times New Roman", 1, 14));
            row1.setForeground(Color.WHITE);
            row1.setBounds(250, 100, 600, 70);
            JLabel row2 = new JLabel("Player1 (single, muliplayer) press [ space ]to fire");
            row2.setFont(new Font("Times New Roman", 1, 14));
            row2.setForeground(Color.WHITE);
            row2.setBounds(250, 150, 600, 70);
            JLabel row3 = new JLabel("Player2 (muliplayer) press [ f ]to fire");
           row3.setFont(new Font("Times New Roman", 1, 14));
            row3.setForeground(Color.WHITE);
            row3.setBounds(250, 200, 600, 70);
            JLabel row4 = new JLabel("press [ esc ] to go to Main menu");
            row4.setFont(new Font("Times New Roman", 1, 14));
            row4.setForeground(Color.WHITE);
            row4.setBounds(250, 250, 600, 70);
            JLabel row5 = new JLabel("press [ alt + f4 ] to Quit");
            row5.setFont(new Font("Times New Roman", 1, 14));
            row5.setForeground(Color.WHITE);
            row5.setBounds(250, 300, 600, 70);

            jLabel3.setIcon(new ImageIcon("C:\\Users\\Altaier.DESKTOP-532GCGK\\Documents\\NetBeansProjects\\FinalGame\\Assets\\zombie-halloween-background-vector-26154447.jpg")); // NOI18N

            howto.add(row);
            howto.add(row1);
            howto.add(row2);
            howto.add(row3);
            howto.add(row4);
            howto.add(row5);
            howto.add(back);
            howto.add(jLabel3);
            jLabel3.setBounds(0, 0 ,(int) tk.getWidth(),(int) tk.getHeight()-68);
            cont.repaint();
            cont.add(howto);
            this.setVisible(true);
        }
    }

    public static void gameplay() {
        Game g = new Game();
    }
}
