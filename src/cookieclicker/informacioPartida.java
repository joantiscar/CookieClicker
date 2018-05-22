/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookieclicker;

import java.io.Serializable;

/**
 *
 * @author joantiscar
 */
public class informacioPartida implements Serializable{
   private String nom= "";
   private double cookies_actuals= 0;
   private int cursors = 0;
   private int abueles = 0;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getCookies_actuals() {
        return cookies_actuals;
    }

    public void setCookies_actuals(double cookies_actuals) {
        this.cookies_actuals = cookies_actuals;
    }

    public int getCursors() {
        return cursors;
    }

    public void setCursors(int cursors) {
        this.cursors = cursors;
    }

    public int getAbueles() {
        return abueles;
    }

    public void setAbueles(int abueles) {
        this.abueles = abueles;
    }
public String toString() {
        return "Objecte{" + "cookies=" + cookies_actuals + ", cadena=" +nom + '}';
}
   
   
}
