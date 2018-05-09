/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookieclicker;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;




public class Board extends JPanel {
    private boolean inGame;
    private Image big_cookie;
    private Image background;
    private Image cursor;
    private Image background_button;
    private Image small_cookie;
    private Image abuela;
    private JLabel statusbar;
    
    private double cps;
    private int cursors;
    private int abueles;
    private double cookies_actuals =0;
    private int frame_width=1200;
    private int frame_height=900;
    private int cost_cursor = 15;
    private int cost_abueles = 100;
    
    //Posicions
    //cursors
    private int cursors_x = (frame_width*70)/100;
    private int cursors_y = (frame_height*20)/100;
    private int abueles_x = (frame_width*70)/100;
    private int abueles_y = (frame_height*40)/100;
    
    
    
    public Board(JLabel statusbar) {
      
        this.statusbar = statusbar;

        setDoubleBuffered(true);
        initBoard();
        addMouseListener(new CookiesAdapter());
        newGame();
        Timer timer = new Timer();
        timer.schedule(new sumarCookies(), 0, 1000);
    }

    
    private void initBoard() {
        
        loadImage();     
    }
    
    private void loadImage() {
        
        ImageIcon iBig_gookie = new ImageIcon("src/cookieclicker/img/big_cookie.png");
        big_cookie = iBig_gookie.getImage();    
        ImageIcon iBackground = new ImageIcon("src/cookieclicker/img/background.png");
        background = iBackground.getImage();
        ImageIcon iBackground_button = new ImageIcon("src/cookieclicker/img/background_button.png");
        background_button = iBackground_button.getImage();
        ImageIcon iCursor = new ImageIcon("src/cookieclicker/img/cursor.png");
        cursor = iCursor.getImage();
        ImageIcon iAbuela = new ImageIcon("src/cookieclicker/img/abuela.png");
        abuela = iAbuela.getImage();
        ImageIcon iSmall_Cookie = new ImageIcon("src/cookieclicker/img/small_Cookie.png");
        small_cookie = iSmall_Cookie.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        int fontSize = 27;
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        g.setColor(Color.gray);
        g.drawImage(background, 0, 0, frame_width, frame_height, null);
        g.drawImage(big_cookie, 50, 200, 128, 128, null);
        g.drawImage(background_button, cursors_x, cursors_y, 300, 130, null);
        g.drawImage(cursor, cursors_x+5, cursors_y+12, 100, 100, null);
        g.drawImage(background_button, abueles_x, abueles_y, 300, 130, null);
        g.drawImage(abuela, abueles_x+5, abueles_y+12, 100, 100, null);
        g.drawString("Cursors", cursors_x+110, cursors_y+60);
        g.drawString("Abueles", cursors_x+110, abueles_y+60);
        g.drawString(Integer.toString(cost_cursor), cursors_x+200, cursors_y+105);
        g.drawString(Integer.toString(cost_abueles), abueles_x+200, abueles_y+105);
        g.drawImage(small_cookie, cursors_x+250, cursors_y+75, 32, 32, null);
        g.drawImage(small_cookie, abueles_x+250, abueles_y+75, 32, 32, null);
        
        
    }
    class CookiesAdapter extends MouseAdapter {
        
        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            if(x >= 20 && y >= 200 && x <= 128+20 && y <= 128 + 200){
                cookies_actuals++;
                refreshStatusBar();
            }
            
            if(x >= cursors_x && y >= cursors_y && x <= cursors_x+300 && y <= cursors_y + 130){
                if(cookies_actuals >=cost_cursor){
                    cursors++;
                    cookies_actuals = cookies_actuals-cost_cursor;
                    cost_cursor = (int) (cost_cursor * 1.15);
                    repaint();
                    
                calcularCps();
                refreshStatusBar();
                }
            
            }
          if(x >= abueles_x && y >= abueles_y && x <= abueles_x+300 && y <= abueles_y + 130){
                if(cookies_actuals >=cost_abueles){
                    abueles++;
                    cookies_actuals = cookies_actuals-cost_abueles;
                    cost_abueles = (int) (cost_abueles * 1.15);
                    repaint();
                    calcularCps();
                refreshStatusBar();
                }
            
            }

            
            
            
            
        }
    }
    public void calcularCps(){
        cps = (cursors*0.2 + abueles * 1);
    }
    
    class sumarCookies extends TimerTask {
    public void run() {
       cookies_actuals = cookies_actuals + cps;
       refreshStatusBar();
       
    }
}
    
    public void refreshStatusBar(){
        statusbar.setText("Galletes: " +Double.toString(cookies_actuals).substring(0, Double.toString(cookies_actuals).indexOf('.')+2)+"     Cursors: " +Integer.toString(cursors) +"     Abueles: " +Integer.toString(abueles) + "     CPS: " +Double.toString(cps).substring(0, Double.toString(cps).indexOf('.')+2));
    }
    private void newGame(){
        
        
        
    }
    
}

