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

import herramientas.Alfabeto.Espanol;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.util.List;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;

public class Graficador extends ApplicationFrame{
    private String titulo;
    private List<Espanol> simbolos;
    private List<Float> porcentajes;
    
    public Graficador(Point punto, String titulo, List<Espanol> simbolos, List<Float> porcentajes){
        super(titulo);
        
        this.titulo = titulo;
        this.simbolos = simbolos;
        this.porcentajes = porcentajes;

        CategoryDataset conjuntoDatos = crearConjuntoDatos();
        JFreeChart grafica = crearGrafica(conjuntoDatos);
        ChartPanel panelGrafica = new ChartPanel(grafica);
        panelGrafica.setPreferredSize(new Dimension(850, 270));
        setContentPane(panelGrafica);
        
        this.pack();
        this.setLocation(punto);
        this.setVisible(true);

    }
    
    private CategoryDataset crearConjuntoDatos() {
        DefaultCategoryDataset conjuntoDatos = new DefaultCategoryDataset();
        
        String simbolo = null;
        float porcentaje = 0;
        for(int i=0; i<simbolos.size(); i++){
            simbolo = simbolos.get(i).name();
            porcentaje = porcentajes.get(i);
            
            conjuntoDatos.addValue(porcentaje, simbolo, "");
        }
        
        return conjuntoDatos;
        
    }

    private JFreeChart crearGrafica(final CategoryDataset dataset) {
        final JFreeChart grafica = ChartFactory.createBarChart(
            titulo,
            "Simbolos Probables",
            "Porcentaje",
            dataset,
            PlotOrientation.VERTICAL,
            true,
            true,
            false
        );

        grafica.setBackgroundPaint(Color.white);

        CategoryPlot plot = grafica.getCategoryPlot();
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);

        NumberAxis rangoEjes = (NumberAxis) plot.getRangeAxis();
        rangoEjes.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);

        CategoryAxis dominioEjes = plot.getDomainAxis();
        dominioEjes.setCategoryLabelPositions(
            CategoryLabelPositions.createUpRotationLabelPositions(Math.PI / 6.0)
        );
        
        return grafica;
    }
}
