package org.emp.gl.clients;

import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private TimerService timerService;
    private JLabel labelHeure;
    private JLabel labelMinutes;
    private JLabel labelSecondes;

    public HorlogeGraphique(TimerService timerService) {
        this.timerService = timerService;

       
        setTitle("Horloge - Observer Pattern");
        setSize(600, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

      
        this.timerService.addTimeChangeListener(this);

        
        creerInterface();

        setVisible(true);
    }

    private void creerInterface() {
     
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

                
                GradientPaint gp = new GradientPaint(
                        0, 0, new Color(59, 130, 246),
                        0, getHeight(), new Color(147, 51, 234)
                );
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout());

       
        JLabel titre = new JLabel("⏰ Horloge du Service Timer", SwingConstants.CENTER);
        titre.setFont(new Font("Arial", Font.BOLD, 28));
        titre.setForeground(Color.WHITE);
        titre.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(titre, BorderLayout.NORTH);

        
        JPanel panelHeure = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 20));
        panelHeure.setOpaque(false);

       
        labelHeure = creerLabelTemps("00");
        panelHeure.add(labelHeure);
        panelHeure.add(creerSeparateur());

        
        labelMinutes = creerLabelTemps("00");
        panelHeure.add(labelMinutes);
        panelHeure.add(creerSeparateur());

        
        labelSecondes = creerLabelTemps("00");
        panelHeure.add(labelSecondes);

        mainPanel.add(panelHeure, BorderLayout.CENTER);

        
        JLabel footer = new JLabel("Pattern Observer • PropertyChangeSupport", SwingConstants.CENTER);
        footer.setFont(new Font("Arial", Font.PLAIN, 14));
        footer.setForeground(new Color(200, 200, 255));
        footer.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        mainPanel.add(footer, BorderLayout.SOUTH);

        add(mainPanel);

        
        mettreAJourAffichage();
    }

    private JLabel creerLabelTemps(String texte) {
        JLabel label = new JLabel(texte, SwingConstants.CENTER);
        label.setFont(new Font("Monospaced", Font.BOLD, 80));
        label.setForeground(Color.WHITE);
        label.setPreferredSize(new Dimension(120, 100));

      
        label.setOpaque(true);
        label.setBackground(new Color(255, 255, 255, 30));
        label.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(255, 255, 255, 100), 2, true),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        return label;
    }

    private JLabel creerSeparateur() {
        JLabel sep = new JLabel(":", SwingConstants.CENTER);
        sep.setFont(new Font("Monospaced", Font.BOLD, 60));
        sep.setForeground(Color.WHITE);
        return sep;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        propertyChange(evt.getPropertyName(), evt.getOldValue(), evt.getNewValue());
    }

    @Override
    public void propertyChange(String prop, Object oldValue, Object newValue) {
       
        SwingUtilities.invokeLater(this::mettreAJourAffichage);
    }

    private void mettreAJourAffichage() {
        labelHeure.setText(String.format("%02d", timerService.getHeures()));
        labelMinutes.setText(String.format("%02d", timerService.getMinutes()));
        labelSecondes.setText(String.format("%02d", timerService.getSecondes()));
    }
}