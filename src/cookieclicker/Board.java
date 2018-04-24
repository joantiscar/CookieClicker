/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookieclicker;


import java.awt.Dimension;
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
    private JLabel statusbar;
    private int cps;
    private int cursors;
    private int cookies_actuals =0;
    
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
        
        setPreferredSize(new Dimension(800, 600));        
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
    }

    @Override
    public void paintComponent(Graphics g) {
        g.drawImage(background, 0, 0, 800, 600, null);
        g.drawImage(big_cookie, 50, 200, 128, 128, null);
        g.drawImage(background_button, 500, 200, 200, 130, null);
        g.drawImage(cursor, 500, 215, 100, 100, null);
        
        
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
            
            if(x >= 500 && y >= 200 && x <= 500+200 && y <= 200 + 130){
            cursors++;
            calcularCps();
            refreshStatusBar();
            }
          

            
            
            
            
        }
    }
    public void calcularCps(){
        cps = cursors*1;
    }
    
    class sumarCookies extends TimerTask {
    public void run() {
       cookies_actuals = cookies_actuals + cps;
       refreshStatusBar();
       
    }
}
    
    public void refreshStatusBar(){
        statusbar.setText("Galletes: " +Integer.toString(cookies_actuals)+"     Cursors: " +Integer.toString(cursors));
    }
    private void newGame(){
        
        
        
    }
    
}

