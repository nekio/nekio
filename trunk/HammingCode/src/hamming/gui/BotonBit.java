package hamming.gui;

import hamming.hammingcode.Globales;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

class BotonBit{
    private GUI gui;
    private JButton btnBit;
    private int indice;

    public JPanel crear(GUI gui, int indice){
        this.gui = gui;
        this.indice=indice;

        JPanel pnlBoton = new JPanel(new BorderLayout());
        btnBit = new JButton(Globales.CERO);
        btnBit.setBackground(Color.WHITE);

        JLabel lblIndice = new JLabel(String.valueOf(this.indice));
        lblIndice.setHorizontalAlignment(SwingConstants.CENTER);

        pnlBoton.add(lblIndice,"North");
        pnlBoton.add(btnBit,"Center");

        escuchador();

        return pnlBoton;
    }

    private void escuchador(){
        btnBit.addActionListener(new ActionListener(){
        @Override
            public void actionPerformed(ActionEvent evt){
                if(btnBit.getText().equals(Globales.CERO)){
                    btnBit.setText(Globales.UNO);
                    gui.getDto().getBitsOriginales().set(indice-1, true);
                }else if(btnBit.getText().equals(Globales.UNO)){
                    btnBit.setText(Globales.CERO);
                    gui.getDto().getBitsOriginales().set(indice-1, false);
                }                    

                gui.actualizarValores();
            }
        });
    }
}