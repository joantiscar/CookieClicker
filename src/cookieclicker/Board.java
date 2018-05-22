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
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;

public class Board extends JPanel {

    private informacioPartida partida = new informacioPartida();
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
    private int granjes;
    private double cookies_actuals = 0;
    private int frame_width = 1200;
    private int frame_height = 900;
    private int cost_cursor = 15;
    private int cost_abueles = 100;
    private int cost_granjes = 1100;
    private Image guardar;
    private Image cargar;
    private Image granja;
    //Posicions
    //cursors
    private int cursors_y = (frame_height * 15) / 100;
    private int abueles_y = (frame_height * 30) / 100;
    private int granjes_y = (frame_height * 45) / 100;
    private int posicioX = (frame_width * 72) / 100;
    public Board(JLabel statusbar) {

        this.statusbar = statusbar;

        setDoubleBuffered(true);
        initBoard();
        addMouseListener(new CookiesAdapter());
        newGame();
        Timer timer = new Timer();
        timer.schedule(new sumarCookies(), 0, 1000);
//        cookies_actuals = partida.getCookies_actuals();
//        cursors = partida.getCursors();
//        abueles = partida.getAbueles();
        calcularCps();
        calcularCostos();

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
        ImageIcon iGuardar = new ImageIcon("src/cookieclicker/img/guardar.png");
        guardar = iGuardar.getImage();
        ImageIcon iCargar = new ImageIcon("src/cookieclicker/img/cargar.png");
        cargar = iCargar.getImage();
        ImageIcon iGranja = new ImageIcon("src/cookieclicker/img/granja.png");
        granja = iGranja.getImage();
    }

    @Override
    public void paintComponent(Graphics g) {
        //dibuixem les imatges
        g.drawImage(background, 0, 0, frame_width, frame_height, null);
        g.drawImage(big_cookie, 50, 200, 128, 128, null);
        g.drawImage(background_button, posicioX, cursors_y, 300, 130, null);
        g.drawImage(cursor, posicioX + 5, cursors_y + 12, 100, 100, null);
        g.drawImage(background_button, posicioX, abueles_y, 300, 130, null);
        g.drawImage(abuela, posicioX + 5, abueles_y + 12, 100, 100, null);
        g.drawImage(small_cookie, posicioX + 110, cursors_y + 75, 32, 32, null);
        g.drawImage(small_cookie, posicioX + 110, abueles_y + 75, 32, 32, null);
        g.drawImage(background_button, posicioX, granjes_y, 300, 130, null);
        g.drawImage(granja, posicioX + 5, granjes_y + 12, 100, 100, null);
        g.drawImage(small_cookie, posicioX + 110, granjes_y + 75, 32, 32, null);
        g.drawImage(guardar, 500, 80, 100, 100, null);
        g.drawImage(cargar, 750, 80, 100, 100, null);
        //dibuixem els textos
        DecimalFormat df = new DecimalFormat("#.##");
        int fontSize = 27;
        g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
        //coses que han d'estar escrites en blanc
        g.setColor(Color.white);
        g.drawString("COOKIES:  " + df.format(cookies_actuals), 60, 60);
        g.drawString("Guardar", 500, 50);
        g.drawString("Cargar", 750, 50);
        g.drawString("Per segon:  " + df.format(cps), 60, 160);
        g.drawString("Cursors", posicioX + 110, cursors_y + 60);
        g.drawString("Abueles", posicioX + 110, abueles_y + 60);
        g.drawString("Granjes", posicioX + 110, granjes_y + 60);
        //coses que han d'estar en gris
        g.setColor(Color.DARK_GRAY);
        g.drawString(Integer.toString(cursors), posicioX + 250, cursors_y + 60);
        g.drawString(Integer.toString(abueles), posicioX + 250, abueles_y + 60);
        g.drawString(Integer.toString(granjes), posicioX + 250, granjes_y + 60);
        //Coses que han d'estar en roig o en verd
        if (cost_cursor <= cookies_actuals) {
            g.setColor(Color.green);
            g.drawString(Integer.toString(cost_cursor), posicioX + 150, cursors_y + 105);
        } else {
            g.setColor(Color.red);
            g.drawString(Integer.toString(cost_cursor), posicioX + 150, cursors_y + 105);
        }
        if (cost_abueles <= cookies_actuals) {
            g.setColor(Color.green);
            g.drawString(Integer.toString(cost_abueles), posicioX + 150, abueles_y + 105);
        } else {
            g.setColor(Color.red);
            g.drawString(Integer.toString(cost_abueles), posicioX + 150, abueles_y + 105);
        }
        if (cost_granjes <= cookies_actuals) {
            g.setColor(Color.green);
            g.drawString(Integer.toString(cost_granjes), posicioX + 150, granjes_y + 105);
        } else {
            g.setColor(Color.red);
            g.drawString(Integer.toString(cost_granjes), posicioX + 150, granjes_y + 105);
        }

    }

    class CookiesAdapter extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {

            int x = e.getX();
            int y = e.getY();

            if (x >= 20 && y >= 200 && x <= 128 + 20 && y <= 128 + 200) {
                cookies_actuals++;
                refreshStatusBar();
                repaint();
            }

            if (x >= posicioX && y >= cursors_y && x <= posicioX + 300 && y <= cursors_y + 130) {
                if (cookies_actuals >= cost_cursor) {
                    cursors++;
                    cookies_actuals = cookies_actuals - cost_cursor;
                    cost_cursor = (int) (cost_cursor * 1.15);
                    repaint();

                    calcularCps();
                    refreshStatusBar();
                }

            }
            if (x >= posicioX && y >= abueles_y && x <= posicioX + 300 && y <= abueles_y + 130) {
                if (cookies_actuals >= cost_abueles) {
                    abueles++;
                    cookies_actuals = cookies_actuals - cost_abueles;
                    cost_abueles = (int) (cost_abueles * 1.15);
                    repaint();
                    calcularCps();
                    refreshStatusBar();
                }

            }
            if (x >= posicioX && y >= granjes_y && x <= posicioX + 300 && y <= granjes_y + 130) {
                if (cookies_actuals >= cost_granjes) {
                    granjes++;
                    cookies_actuals = cookies_actuals - cost_granjes;
                    cost_granjes = (int) (cost_granjes * 1.15);
                    repaint();
                    calcularCps();
                    refreshStatusBar();
                }

            }
            if (x >= 500 && y >= 80 && x <= 600 && y <= 180) {
                guardarPartida();
            }
            if (x >= 750 && y >= 80 && x <= 850 && y <= 180) {
                cargarPartida();
                calcularCps();
                calcularCostos();

             

            }

        }
    }

    private void guardarPartida() {
        try {
            FileOutputStream saveFile = new FileOutputStream("SaveObj.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            partida.setCursors(cursors);
            partida.setAbueles(abueles);
            partida.setGranjes(granjes);
            partida.setCookies_actuals(cookies_actuals);
            save.writeObject(partida);
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cargarPartida() {
        try {
            FileInputStream saveFile = new FileInputStream("SaveObj.sav");

            ObjectInputStream save = new ObjectInputStream(saveFile);
            partida = (informacioPartida) save.readObject();
            save.close(); 
            carregarVariables();
            calcularCps();
            calcularCostos();
            repaint();
           
        } catch (Exception exc) {
            exc.printStackTrace(); // If there was an error, print the info.
        }

    }
        private void carregarVariables(){
            cursors = partida.getCursors();
            abueles = partida.getAbueles();
            granjes = partida.getGranjes();
            cookies_actuals = partida.getCookies_actuals();
            
            
        }
    public void calcularCps() {
        cps = (cursors * 0.2 + abueles * 1 + granjes * 8);
    }

    public void calcularCostos() {
        if (cursors == 0) {
            cost_cursor = 15;
        } else {
            cost_cursor = 15;
            for (int i = 0; i <= cursors; i++) {
                cost_cursor = (int) (cost_cursor * 1.15);
            }

        }
        if (abueles == 0) {
            cost_abueles = 100;
        } else {
            cost_abueles = 100;
            for (int i = 0; i < abueles; i++) {
                cost_abueles = (int) (cost_abueles * 1.15);

            }

        }
        if (granjes == 0) {
            cost_granjes = 1100;
        } else {
            cost_granjes = 1100;
            for (int i = 0; i < granjes; i++) {
                cost_granjes = (int) (cost_granjes * 1.15);

            }

        }
    }

    class sumarCookies extends TimerTask {

        public void run() {

            cookies_actuals = cookies_actuals + cps;
            repaint();

        }
    }

    public void refreshStatusBar() {
        statusbar.setText("Galletes: " + Double.toString(cookies_actuals).substring(0, Double.toString(cookies_actuals).indexOf('.') + 2) + "     Cursors: " + Integer.toString(cursors) + "     Abueles: " + Integer.toString(abueles) + "     CPS: " + Double.toString(cps).substring(0, Double.toString(cps).indexOf('.') + 2));
    }

    private void newGame() {

    }

}
