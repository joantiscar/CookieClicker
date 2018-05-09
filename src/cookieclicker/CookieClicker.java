/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookieclicker;

/**
 *
 * @author joantiscar
 */

import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;


public class CookieClicker extends JFrame {

    int FRAME_WIDTH = 1200;
    int FRAME_HEIGHT = 900;



    private final JLabel statusbar;
    
    public CookieClicker() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("CookieClicker");

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new Board(statusbar));

        setResizable(false);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {                
                JFrame ex = new CookieClicker();
                ex.setVisible(true);                
            }
        });
    }
}