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
public class informacioPartida implements Serializable {

    private double cookies_actuals = 0;
    private int cursors = 0;
    private int abueles = 0;
    private int granjes = 0;
    private int mines = 0;
    private int fabriques = 0;
    private int bancs = 0;

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
        return "Objecte{" + "cookies=" + cookies_actuals + ", cadena=" + '}';
    }

    public int getGranjes() {
        return granjes;
    }

    public void setGranjes(int granjes) {
        this.granjes = granjes;
    }

    public int getMines() {
        return mines;
    }

    public void setMines(int mines) {
        this.mines = mines;
    }

    public int getFabriques() {
        return fabriques;
    }

    public void setFabriques(int fabriques) {
        this.fabriques = fabriques;
    }

    public int getBancs() {
        return bancs;
    }

    public void setBancs(int bancs) {
        this.bancs = bancs;
    }

}
