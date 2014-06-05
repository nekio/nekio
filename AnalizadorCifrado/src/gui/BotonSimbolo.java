package gui;

/**
 * 07/Jun/2014
 * LANIA - MRYSI
 * Seguridad en Redes
 * 
 * @author LI. Emiliano Anastasio Landa
 *         eanastasio@veracruz.gob.mx
 * 
 * @author ISC. Sinesio Ivan Carrillo Heredia 
 *         ivan.carrillo@si-ti.com.mx
 * 
 */

import herramientas.Alfabeto;
import herramientas.Alfabeto.Espanol;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class BotonSimbolo{
    private Ventana gui;
    private JButton btnSimbolo;
    private List<Espanol> probables;
    
    private String textoBoton;
    private int indiceSimbolo;
    private int recorredorProbables;

    public JPanel crear(Ventana gui, List<Espanol> probables, Espanol masProbable, int indiceSimbolo){
        this.gui = gui;
        this.indiceSimbolo=indiceSimbolo;
        this.probables = probables;
        this.recorredorProbables = 0;
        
        String simbolo = Alfabeto.Espanol.values()[indiceSimbolo].name();

        JPanel pnlBoton = new JPanel(new BorderLayout());
        
        textoBoton = " ";
        if(probables != null)
            textoBoton = masProbable.name();

        btnSimbolo = new JButton(textoBoton);
        btnSimbolo.setBackground(Color.WHITE);
        pnlBoton.add(btnSimbolo,"Center");

        JLabel lblIndice = new JLabel(simbolo);
        lblIndice.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBoton.add(lblIndice,"North");

        escuchador();

        return pnlBoton;
    }

    private void escuchador(){
        btnSimbolo.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(recorredorProbables < probables.size()-1)
                    recorredorProbables++;
                else
                    recorredorProbables = 0;
                
                textoBoton = probables.get(recorredorProbables).name();
                        
                btnSimbolo.setText(textoBoton);
            }
        });
    }
}

