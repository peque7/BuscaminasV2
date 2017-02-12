/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import Formulario.Ventana;
import java.awt.Color;
import java.util.Random;
import javax.swing.JButton;

/**
 *
 * @author pequesillo
 */
public class Celdas extends JButton{
    private int x;
    private int y;
    private int tipoCelda;
    private boolean visible;

    public boolean getVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    private Color color[];

    
    
    public Celdas(int x, int y) {
        this.x = x;
        this.y = y;
        this.visible = false;
        this.color = new Color[]{Color.BLUE,Color.ORANGE,Color.YELLOW,Color.BLACK,Color.PINK,Color.RED,Color.GRAY,Color.GREEN};
        this.setBackground(new java.awt.Color(0, 0, 204));
        this.setFont(new java.awt.Font("Tahoma",1,12));
        this.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celdaActionPerformed(evt);
            }
        });
    }
    
    public void setTipoCelda(int tipoCelda) {
        this.tipoCelda = tipoCelda;
    }
    private void celdaActionPerformed(java.awt.event.ActionEvent evt){
        click();
        int count = 0;
        for (int i = 0; i < Ventana.fila; i++) {
            for (int j = 0; j < Ventana.columnas; j++) {
                if (Ventana.celda[i][j].getVisible()) {
                     count++;
                }
            }
        }
        if (count == Ventana.fila * Ventana.columnas - Ventana.minas) {
            Ventana.gano = true;
            
        }
        
    }
    public void click(){
        if (!visible && Ventana.gano == false) {
            this.visible = true;
            this.setBackground(new java.awt.Color(240,240,240));
            switch(this.tipoCelda){
                case 0:  
                    for (int i = 0; i < Ventana.fila; i++) {
                        for (int j = 0; j < Ventana.columnas; j++) {
                            if (Ventana.celda[i][j].getTipocelda() == 0) {
                                Ventana.celda[i][j].setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mina2.png")));
                                Ventana.celda[i][j].setBackground(new java.awt.Color(240,240,240));
                            }
                         }
                    }
                    Ventana.gano = true;
                    this.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/mina2.png")));
                    break;
                case 1:
                    int count = 0;
                    this.setBackground(new java.awt.Color(240,240,240));
                    for (int i = -1; i <= 1; i++) {
                        if (x+i>=0 && x+i<Ventana.fila) {
                            for (int j = -1; j <= 1; j++) {
                                System.out.println("y="+i+" "+i+" "+Ventana.columnas);
                                if((y+j>=0 && (y+j)<Ventana.columnas) && Ventana.celda[x+i][y+j].getTipocelda() == 0){
                                    count++;
                                }
                            }
                        }
                    }
                    this.setText(""+count);
                    this.setForeground(this.color[count]);
                    break;  
                default:
                    for (int i = -1; i <= 1; i++) {
                      if (x+i>=0 && x+i<Ventana.fila) {
                            for (int j = -1; j <= 1; j++) {
                                System.out.println("y="+i+" "+i+" "+Ventana.columnas);
                                if((y+j>=0 && (y+j)<Ventana.columnas) && Ventana.celda[x+i][y+j].getTipocelda() != 0){
                                    if (!Ventana.celda[x+1][y+1].getVisible()) {
                                        Ventana.celda[x+i][y+j].click();
                                    }
                                }
                            }
                        }  
                    }
            }
        }
    }
    public int getTipocelda() {
      return tipoCelda;
    }
 }
