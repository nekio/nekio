package gui;

import cesar.Alfabeto;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class BotonSimbolo{
    private Ventana gui;
    private JButton btnSimbolo;
    private int indice;

    public JPanel crear(Ventana gui, int indice){
        this.gui = gui;
        this.indice=indice;
        
        String simbolo = Alfabeto.Espanol.values()[indice].name();

        JPanel pnlBoton = new JPanel(new BorderLayout());
        setBtnSimbolo(new JButton(" "));
        getBtnSimbolo().setBackground(Color.WHITE);
        pnlBoton.add(getBtnSimbolo(),"Center");

        JLabel lblIndice = new JLabel(simbolo);
        lblIndice.setHorizontalAlignment(SwingConstants.CENTER);
        pnlBoton.add(lblIndice,"North");

        escuchador();

        return pnlBoton;
    }

    private void escuchador(){
        getBtnSimbolo().addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){                               
                gui.actualizarValores(indice);
            }
        });
    }

    public JButton getBtnSimbolo() {
        return btnSimbolo;
    }

    public void setBtnSimbolo(JButton btnSimbolo) {
        this.btnSimbolo = btnSimbolo;
    }
}

