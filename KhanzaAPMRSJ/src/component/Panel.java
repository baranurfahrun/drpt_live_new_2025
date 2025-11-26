/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package component;

import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

/**
 *
 * @author root
 */
public class Panel extends JPanel{
    public Panel(){        
        setBackground(new Color(220,220,220));
        setBorder(new LineBorder(new Color(0,0,128)));
    }
}
