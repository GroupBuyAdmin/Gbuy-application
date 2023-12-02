/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package gbuysytem;

import javax.swing.SwingUtilities;

import gbuysytem.GUI.Mainframe;

public class GBuySytem {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new 
        Runnable() {
            @Override
            public void run() {
                new  Mainframe ();
            }
        });
    }
}
