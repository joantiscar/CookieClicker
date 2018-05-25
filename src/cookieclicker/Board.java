/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cookieclicker;


//import de les llibreries que ens faran falta
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;
import java.text.*;

public class Board extends JPanel {

    //declarem les variables que ens faran falta per al programa
    private informacioPartida partida = new informacioPartida();
    private boolean inGame;
    
    //Declarem totes les imatges que farem servir, amb la classe Image
    
    private Image big_cookie;
    private Image background;
    private Image cursor;
    private Image background_button;
    private Image small_cookie;
    private Image abuela;
    private Image guardar;
    private Image cargar;
    private Image granja;
    private Image mina;
    private Image separador;
    private Image informacio;
    private Image separador_horizontal;
    private Image fabrica;
    private Image banc;
    private Image abuela_petit;
    private Image granja_petit;
    private Image mina_petit;
    private Image fabrica_petit;
    private Image banc_petit;
    private Image creu;
    private Image informacio_pantalla;
    
    //Declarem tots els ints i els doubles que necessitarem per a fer els calculs sobre el joc
    private double cps;
    private int cursors;
    private int abueles;
    private int granjes;
    private int mines;
    private int fabriques;
    private int bancs;
    private double cookies_actuals = 0;
    private int frame_width = 1200;
    private int frame_height = 900;
    private int cost_cursor = 15;
    private int cost_abueles = 100;
    private int cost_granjes = 1100;
    private int cost_mines = 12000;
    private int cost_fabriques = 130000;
    private int cost_bancs = 1400000;
    
    
    //Declarem el boolean menu_informacio per a dictaminar si l'usuari esta mirant l'apartat d'informacio

    private boolean menu_informacio = false;
    //Esta linea millor no tocarla
    private JLabel statusbar;
    
    
    
    
    
    //Declarem les posicions relatives d'alguns objectes, per a facilitarnos la vida mes tard
    private int cursors_y = (frame_height * 1) / 100;
    private int abueles_y = (frame_height * 15) / 100;
    private int granjes_y = (frame_height * 30) / 100;
    private int mines_y = (frame_height * 45) / 100;
    private int fabriques_y = (frame_height * 60) / 100;
    private int bancs_y = (frame_height * 75) / 100;
    //Ja que casi tots els objectes s'alineen a la mateixa x, la fiquem a una variable per a simplificar el codi
    private int posicioX = (frame_width * 72) / 100;

    
    
    
    //Declarem el "Board"
    public Board(JLabel statusbar) {

        //Tot el que fiquem aqui, es fara al començar la partida
        this.statusbar = statusbar;
        setDoubleBuffered(true);
        //Aquest metode es el que ens carregara i dibuixara les imatges
        initBoard();
        //Instanciem el Listener del ratoli i el "timer"
        addMouseListener(new CookiesAdapter());
        newGame();
        Timer timer = new Timer();
        timer.schedule(new sumarCookies(), 0, 1000);
        //Aquestes dos linees seran necesaries si vols fer que el joc comence carregant la partida de forma automatica, pero com al final no lo he fet aixi, les deixo comentades.
        //calcularCps();
        //calcularCostos();

    }

    private void initBoard() {

        loadImage();
    }

    private void loadImage() {

        //Per a carregar una imatge, cal fer servir aquestes linees:
        //ImageIcon "nom_del_image_icon" = new ImageIcon("directori/de/la/imatge.png");
        //"nom_de_la_image" = " nom del image icon".getImage();
        //Cal mencionar que es recomanable anomenar tots els imageIcon començant amb una i, per a fer mes facil identificarlos
        
        
        //Encara que l'ordre en el que carreguem les imatges no importa, sempre esta be organitzarles
        //Primer carreguem el background i els separadors:
        ImageIcon iBackground = new ImageIcon("src/cookieclicker/img/background.png");
        background = iBackground.getImage();
        ImageIcon iBackground_button = new ImageIcon("src/cookieclicker/img/background_button.png");
        background_button = iBackground_button.getImage();
        ImageIcon iSeparador = new ImageIcon("src/cookieclicker/img/separador.png");
        separador = iSeparador.getImage();
        ImageIcon iSeparador_Horizontal = new ImageIcon("src/cookieclicker/img/separador_horizontal.png");
        separador_horizontal = iSeparador_Horizontal.getImage();
        
        //Ara carreguem les imatges de les "opcions" (informacio, carregar i guardar)
        ImageIcon iGuardar = new ImageIcon("src/cookieclicker/img/guardar.png");
        guardar = iGuardar.getImage();
        ImageIcon iCargar = new ImageIcon("src/cookieclicker/img/cargar.png");
        cargar = iCargar.getImage();
        ImageIcon iInformacio = new ImageIcon("src/cookieclicker/img/informacio.png");
        informacio = iInformacio.getImage();
         ImageIcon iCreu = new ImageIcon("src/cookieclicker/img/x.png");
        creu = iCreu.getImage();
        ImageIcon iInformacioPantalla = new ImageIcon("src/cookieclicker/img/informacio_pantalla.png");
        informacio_pantalla = iInformacioPantalla.getImage();
        
        //Ara carregarem les imatges dels objectes, i de la seva versio petita (si en tenen) per a el centre de la pantalla
        ImageIcon iBig_gookie = new ImageIcon("src/cookieclicker/img/big_cookie.png");
        big_cookie = iBig_gookie.getImage();
        ImageIcon iSmall_Cookie = new ImageIcon("src/cookieclicker/img/small_Cookie.png");
        small_cookie = iSmall_Cookie.getImage();
        ImageIcon iCursor = new ImageIcon("src/cookieclicker/img/cursor.png");
        cursor = iCursor.getImage();
        ImageIcon iAbuela = new ImageIcon("src/cookieclicker/img/abuela.png");
        abuela = iAbuela.getImage();
        ImageIcon iAbuelaPetit = new ImageIcon("src/cookieclicker/img/abuela_petit.png");
        abuela_petit = iAbuelaPetit.getImage();
        ImageIcon iGranja = new ImageIcon("src/cookieclicker/img/granja.png");
        granja = iGranja.getImage();
        ImageIcon iGranjaPetit = new ImageIcon("src/cookieclicker/img/granja_petit.png");
        granja_petit = iGranjaPetit.getImage();
        ImageIcon iMina = new ImageIcon("src/cookieclicker/img/mina.png");
        mina = iMina.getImage();
        ImageIcon iMinaPetit = new ImageIcon("src/cookieclicker/img/mina_petit.png");
        mina_petit = iMinaPetit.getImage();
        ImageIcon iFabrica = new ImageIcon("src/cookieclicker/img/fabrica.png");
        fabrica = iFabrica.getImage();
        ImageIcon iFabricaPetit = new ImageIcon("src/cookieclicker/img/fabrica_petit.png");
        fabrica_petit = iFabricaPetit.getImage();
        ImageIcon iBanc = new ImageIcon("src/cookieclicker/img/banc.png");
        banc = iBanc.getImage();
        ImageIcon iBancPetit = new ImageIcon("src/cookieclicker/img/banc_petit.png");
        banc_petit = iBancPetit.getImage();
       
    }

    @Override
    public void paintComponent(Graphics g) {
        //Aquest metode ens dibuixara totes les imatges en pantalla, per aixo aqui SI que es important l'ordre en el que estan cridats
        
        //El metode g.drawImage ens dibuixara les imatges. Cal pasarli per parametre el nom de la imatge (previament carregada al metode anterior)
        // 2 ints, el primer per a la posicio X on el volem dibuixar, i l'altre per a la Y, i despues 2 ints mes, per a l'ample i l'alçada de la imatge, respectivament.
        
        //Primer els backgrounds i despues els continguts, o es ficaran els un damunt dels altres
        
        //Primer comprovem si s'esta dins del menu d'informació, ja que alli nomes hi cal dibuixar dues coses
        if (menu_informacio == true) {
            //Si estem dins, dibuixem la informació i una "creu", per a sortir.
            g.drawImage(informacio_pantalla, 0, 0, frame_width, frame_height, null);
            g.drawImage(creu, 1100, 10, 80, 80, null);
        } else {
            
            //Si no estem al menú d'informació, dibuixem els components del joc!
            //Primer dibuixem el background i els separadors, ja que son els elements que no ens importa si algo es sobreposa damunt
            
            g.drawImage(background, 0, 0, frame_width, frame_height, null);
            g.drawImage(separador, 50, -35, 600, frame_height + 40, null);
            g.drawImage(separador, 525, -35, 600, frame_height + 40, null);
            g.drawImage(separador_horizontal, 355, -100, 465, 600, null);
            g.drawImage(separador_horizontal, 355, 30, 465, 600, null);
            g.drawImage(separador_horizontal, 355, 160, 465, 600, null);
            g.drawImage(separador_horizontal, 355, 290, 465, 600, null);
            g.drawImage(separador_horizontal, 355, 420, 465, 600, null);
            
            //Ara dibuixem les "opcions"
            g.drawImage(informacio, 400, 80, 100, 100, null);
            g.drawImage(guardar, 550, 80, 100, 100, null);
            g.drawImage(cargar, 700, 80, 100, 100, null);
            
            //Ara dibuixem tots els objectes. Cal comentar que els objectes que es poden comprar, tenen un fons.
            //Aquest fons te que estar dibuixat abans del objecte, sinos aquest es quedara baix del fons, i no es visualitzara
            
            
            g.drawImage(big_cookie, 50, 200, 250, 250, null);
            g.drawImage(background_button, posicioX, cursors_y, 300, 130 - 10, null);
            g.drawImage(cursor, posicioX + 5, cursors_y + 12, 100, 100, null);
            g.drawImage(background_button, posicioX, abueles_y, 300, 130, null);
            g.drawImage(abuela, posicioX + 5, abueles_y + 12, 100, 100, null);
            g.drawImage(small_cookie, posicioX + 110, cursors_y + 75, 32, 32, null);
            g.drawImage(small_cookie, posicioX + 110, abueles_y + 75, 32, 32, null);
            g.drawImage(background_button, posicioX, granjes_y, 300, 130, null);
            g.drawImage(granja, posicioX + 5, granjes_y + 12, 100, 100, null);
            g.drawImage(background_button, posicioX, mines_y, 300, 130, null);
            g.drawImage(mina, posicioX + 5, mines_y + 12, 100, 100, null);
            g.drawImage(small_cookie, posicioX + 110, granjes_y + 75, 32, 32, null);
            g.drawImage(background_button, posicioX, fabriques_y, 300, 130, null);
            g.drawImage(fabrica, posicioX + 5, fabriques_y + 12, 100, 100, null);
            g.drawImage(small_cookie, posicioX + 110, fabriques_y + 75, 32, 32, null);
            g.drawImage(background_button, posicioX, bancs_y, 300, 130, null);
            g.drawImage(banc, posicioX + 5, bancs_y + 12, 100, 100, null);
            g.drawImage(small_cookie, posicioX + 110, bancs_y + 75, 32, 32, null);
            
            //Ara dibuixarem els textos.
            //Aquesta linea es per a ajudarnos a truncar els numeros a 2 decimals al mostrarlos dins del joc
            DecimalFormat df = new DecimalFormat("#.##");
            //definim la variable fontSize amb el tamany que voldrem per a la lletra
            int fontSize = 27;
            //Definim la font del text...
            g.setFont(new Font("TimesRoman", Font.BOLD, fontSize));
            
            //Comencem dibuixant les coses que han d'estar escrites en blanc
            //Definim el color "blanc"...
            g.setColor(Color.white);
            //dibuixem els textos. Cal tindre en compte que el g.drawString nomes dibuixa strings, aixi que si volem representar variables
            //que son numeros, cal utilitzar un df.format o metodes semblants. 
            //Un cop li dius el contingut, cal introduir 2 ints, un per a la posicio X i un per a la Y
            g.drawString("COOKIES:  " + df.format(cookies_actuals), 60, 60);
            g.drawString("Informacio", 380, 50);
            g.drawString("Guardar", 550, 50);
            g.drawString("Cargar", 700, 50);
            g.drawString("Per segon:  " + df.format(cps), 60, 160);
            g.drawString("Cursors", posicioX + 110, cursors_y + 60);
            g.drawString("Abueles", posicioX + 110, abueles_y + 60);
            g.drawString("Granjes", posicioX + 110, granjes_y + 60);
            g.drawString("Mines", posicioX + 110, mines_y + 60);
            g.drawString("Fabriques", posicioX + 110, fabriques_y + 60);
            g.drawString("Bancs", posicioX + 110, bancs_y + 60);
            //Ara dibuixarem les coses que han d'estar en gris
            g.setColor(Color.DARK_GRAY);
            g.drawString(Integer.toString(cursors), posicioX + 250, cursors_y + 60);
            g.drawString(Integer.toString(abueles), posicioX + 250, abueles_y + 60);
            g.drawString(Integer.toString(granjes), posicioX + 250, granjes_y + 60);
            g.drawString(Integer.toString(mines), posicioX + 250, mines_y + 60);
            g.drawString(Integer.toString(fabriques), posicioX + 250, fabriques_y + 60);
            g.drawString(Integer.toString(bancs), posicioX + 250, bancs_y + 60);
            //Certs elements del joc tenen que ser de color roig o verd en funcio de si tens prou cookies per a comprarlos, aixi que amb uns cuants ifs,
            //Farem que nomes es veiguen verds cuan es poden comprar
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
            if (cost_mines <= cookies_actuals) {
                g.setColor(Color.green);
                g.drawString(Integer.toString(cost_mines), posicioX + 150, mines_y + 105);
            } else {
                g.setColor(Color.red);
                g.drawString(Integer.toString(cost_mines), posicioX + 150, mines_y + 105);
            }
            if (cost_fabriques <= cookies_actuals) {
                g.setColor(Color.green);
                g.drawString(Integer.toString(cost_fabriques), posicioX + 150, fabriques_y + 105);
            } else {
                g.setColor(Color.red);
                g.drawString(Integer.toString(cost_fabriques), posicioX + 150, fabriques_y + 105);
            }
            if (cost_bancs <= cookies_actuals) {
                g.setColor(Color.green);
                g.drawString(Integer.toString(cost_bancs), posicioX + 150, bancs_y + 105);
            } else {
                g.setColor(Color.red);
                g.drawString(Integer.toString(cost_bancs), posicioX + 150, bancs_y + 105);
            }

            //Ara dibuixarem els objectes al centre de la pantalla
            //Amb un for i un parell d'ifs, fem que es vaiguen dibuixant al lloc que toca, i que pari cuan arribi a un nombre establert,
            //Per a que no es surtigue de la "graella"
            for (int i = 0; i < abueles; i++) {
                if (i % 2 != 0) {
                    g.drawImage(abuela_petit, 360 + (i * 20) - 20, 270, 40, 40, null);
                }
                if (i % 2 == 0) {
                    g.drawImage(abuela_petit, 360 + (i * 20), 220, 40, 40, null);
                }
                if (i > 22) {
                    break;
                }
            }
            for (int i = 0; i < granjes; i++) {
                if (i % 2 != 0) {
                    g.drawImage(granja_petit, 360 + (i * 20) - 20, 400, 40, 40, null);
                }
                if (i % 2 == 0) {
                    g.drawImage(granja_petit, 360 + (i * 20), 350, 40, 40, null);
                }
                if (i > 22) {
                    break;
                }
            }
            for (int i = 0; i < mines; i++) {
                if (i % 2 != 0) {
                    g.drawImage(mina_petit, 360 + (i * 20) - 20, 530, 40, 40, null);
                }
                if (i % 2 == 0) {
                    g.drawImage(mina_petit, 360 + (i * 20), 480, 40, 40, null);
                }
                if (i > 22) {
                    break;
                }
            }
            for (int i = 0; i < fabriques; i++) {
                if (i % 2 != 0) {
                    g.drawImage(fabrica_petit, 360 + (i * 20) - 20, 660, 40, 40, null);
                }
                if (i % 2 == 0) {
                    g.drawImage(fabrica_petit, 360 + (i * 20), 610, 40, 40, null);
                }
                if (i > 22) {
                    break;
                }
            }
            for (int i = 0; i < bancs; i++) {
                if (i % 2 != 0) {
                    g.drawImage(banc_petit, 360 + (i * 20) - 20, 790, 40, 40, null);
                }
                if (i % 2 == 0) {
                    g.drawImage(banc_petit, 360 + (i * 20), 740, 40, 40, null);
                }
                if (i > 22) {
                    break;
                }
            }

        }
    }

    class CookiesAdapter extends MouseAdapter {
            //Aqui definirem el comportament del programa cuan l'usuari fa "click", depenent del lloc on faigue click, i de la situació
        @Override
        public void mousePressed(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            //Per a definir on esta el cursor al fer click, estan les variables X i Y.
            //Per a triar el lloc on volem que pasi algo, tenim que ficar que X i Y siguen mes grans que un valor, i mes petits d'un altre,
            //per a delimitar l'espai de la pantalla on volem que s'apliqui aquest tros de codi.
            
            //Primer farem que, si l'usuari esta dins del menu d'informacio, al fer click on es el boto de tancar, tanqui el menu.
            if (menu_informacio == true) {
                if (x >= 1100 && y >= 10 && x <= 1180 && y <= 90) {
                    menu_informacio = false;
                }
            } else {
                    //Si nO esta al menu, depenent de on es trobi el ratoli, el programa fara una cosa o una altra
                    //Si esta damunt de la cookie gran, sumara 1 a les teves galletes
                if (x >= 50 && y >= 200 && x <= 50 + 250 && y <= 250 + 200) {
                    cookies_actuals++;
                    //El metode repaint() s'encarrega de tornar a pintar el joc, fent que s'apliquin els canvis. Verem bastant aquest metode el que queda de programa
                    repaint();
                    
                }
                    //Si esta damunt d'un objecte, sumara 1 a la cantitat que tens d'aquest objecte, i augmentara el cost d'aquest objecte 
                    //Per a la proxima vegada que el vulgues comrar
                    //Tot aixo nomes te ho deixa fer si tens prou cookies per a comprarlo.
                    //El proces es el mateix per a la resta d'objectes
                if (x >= posicioX && y >= cursors_y && x <= posicioX + 300 && y <= cursors_y + 130) {
                    if (cookies_actuals >= cost_cursor) {
                        cursors++;
                        cookies_actuals = cookies_actuals - cost_cursor;
                        cost_cursor = (int) (cost_cursor * 1.15);
                        repaint();

                        calcularCps();
                    }

                }
                if (x >= posicioX && y >= abueles_y && x <= posicioX + 300 && y <= abueles_y + 130) {
                    if (cookies_actuals >= cost_abueles) {
                        abueles++;
                        cookies_actuals = cookies_actuals - cost_abueles;
                        cost_abueles = (int) (cost_abueles * 1.15);
                        repaint();
                        calcularCps();
                    }

                }
                if (x >= posicioX && y >= granjes_y && x <= posicioX + 300 && y <= granjes_y + 130) {
                    if (cookies_actuals >= cost_granjes) {
                        granjes++;
                        cookies_actuals = cookies_actuals - cost_granjes;
                        cost_granjes = (int) (cost_granjes * 1.15);
                        repaint();
                        calcularCps();
                    }

                }
                if (x >= posicioX && y >= mines_y && x <= posicioX + 300 && y <= mines_y + 130) {
                    if (cookies_actuals >= cost_mines) {
                        mines++;
                        cookies_actuals = cookies_actuals - cost_mines;
                        cost_mines = (int) (cost_mines * 1.15);
                        repaint();
                        calcularCps();
                    }

                }
                if (x >= posicioX && y >= fabriques_y && x <= posicioX + 300 && y <= fabriques_y + 130) {
                    if (cookies_actuals >= cost_fabriques) {
                        fabriques++;
                        cookies_actuals = cookies_actuals - cost_fabriques;
                        cost_fabriques = (int) (cost_fabriques * 1.15);
                        repaint();
                        calcularCps();
                    }

                }
                if (x >= posicioX && y >= bancs_y && x <= posicioX + 300 && y <= bancs_y + 130) {
                    if (cookies_actuals >= cost_bancs) {
                        bancs++;
                        cookies_actuals = cookies_actuals - cost_bancs;
                        cost_bancs = (int) (cost_bancs * 1.15);
                        repaint();
                        calcularCps();
                    }

                }
                //Si fa click al floppy, activara el metode per a guardar partida
                if (x >= 550 && y >= 80 && x <= 650 && y <= 180) {
                    guardarPartida();
                }
                //Si fa click al cd, activara el metode per a carregar partida, i farem que el programa s'adapti a les noves variables amb un parell de metodes mes
                if (x >= 700 && y >= 80 && x <= 800 && y <= 180) {
                    cargarPartida();
                    calcularCps();
                    calcularCostos();

                }
                //Si fa click a "informacio" s'activara el menu d'informació
                if (x >= 400 && y >= 80 && x <= 500 && y <= 180) {
                    menu_informacio = true;
                }

            }
        }
    }

    private void guardarPartida() {
        
        
            //Al guardar partida, l'objecte partida es guarda dins de partida.sav.
            //Cal dirli quines variables guardar a l'objecte
        try {
            FileOutputStream saveFile = new FileOutputStream("partida.sav");
            ObjectOutputStream save = new ObjectOutputStream(saveFile);
            partida.setCursors(cursors);
            partida.setAbueles(abueles);
            partida.setGranjes(granjes);
            partida.setMines(mines);
            partida.setFabriques(fabriques);
            partida.setBancs(bancs);
            partida.setCookies_actuals(cookies_actuals);
            save.writeObject(partida);
            save.close(); // This also closes saveFile.
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    private void cargarPartida() {
        //Per a carregar partida, carreguem l'objecte que hi ha guardat a partida.sav a l'objecte partida.
        //Un cop carregat, li fem adaptarse a les noves dades amb els metodes:
        // carregarVariables(), calcularCps() i calcularCostos().
        try {
            FileInputStream saveFile = new FileInputStream("partida.sav");

            ObjectInputStream save = new ObjectInputStream(saveFile);
            partida = (informacioPartida) save.readObject();
            save.close();
            carregarVariables();
            calcularCps();
            calcularCostos();
            repaint();

        } catch (Exception exc) {
            exc.printStackTrace();
        }

    }

    private void carregarVariables() {
        //Aquest metode canvia els valors actuals dels objectes i les de les cookies que tens per les del objecte partida
        cursors = partida.getCursors();
        abueles = partida.getAbueles();
        granjes = partida.getGranjes();
        mines = partida.getMines();
        fabriques = partida.getFabriques();
        bancs = partida.getBancs();
        cookies_actuals = partida.getCookies_actuals();

    }

    public void calcularCps() {
        //Aquest metode es crida sempre que es fa algun canvi a la cantitat d'objectes, i el que fa es calcular les galletes que fas per segon
        cps = (cursors * 0.2 + abueles * 1 + granjes * 8 + mines * 47 + fabriques * 260 + bancs * 1400);
    }

    public void calcularCostos() {
         //Aquest metode es crida sempre que es fa algun canvi a la cantitat d'objectes, i el que fa es calcular els costos dels objectes
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
        if (mines == 0) {
            cost_mines = 12000;
        } else {
            cost_mines = 12000;
            for (int i = 0; i < mines; i++) {
                cost_mines = (int) (cost_mines * 1.15);

            }

        }
        if (fabriques == 0) {
            cost_fabriques = 130000;
        } else {
            cost_fabriques = 130000;
            for (int i = 0; i < fabriques; i++) {
                cost_fabriques = (int) (cost_fabriques * 1.15);

            }

        }
        if (bancs == 0) {
            cost_bancs = 1400000;
        } else {
            cost_mines = 1400000;
            for (int i = 0; i < bancs; i++) {
                cost_bancs = (int) (cost_bancs * 1.15);

            }

        }
    }

    class sumarCookies extends TimerTask {

        public void run() {
             //cada segon es sumen les nostres "cookies per segon" (cps) al nostre total de cookies i es tornara a dibuixar el joc
            cookies_actuals = cookies_actuals + cps;
            repaint();

        }
    }

    private void newGame() {

    }

}
