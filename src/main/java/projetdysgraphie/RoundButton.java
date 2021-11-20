/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projetdysgraphie;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JButton;

/**
 *
 * @author dell
 */
class RoundButton extends JButton {
  public RoundButton(String label)
     {
         super(label);

          //Enlarge button to make a circle rather than an oval.
        Dimension size = getPreferredSize();
        size.width = size.height = Math.max(size.width, size.height);
         setPreferredSize(size);

        //This call causes the JButton *not* to paint the background
          //and allows us to paint a round background instead.
         setContentAreaFilled(false);
  }

     //Paint the round background and label.
  @Override
     protected void paintComponent(Graphics g)
    {
         
             //You might want to make the highlight color
             //a property of the RoundButton class.
         
             g.setColor(getBackground());
         g.fillRoundRect(0, 0, getWidth(), getHeight(), 15, 15);
          //This call will paint label and focus rectangle.
         super.paintComponent(g);
     }

     //Paint the border of the button using a simple stroke.
  @Override
     protected void paintBorder(Graphics g)
      {
          g.setColor(getBackground());
         g.drawOval(0, 0, getSize().width-1, getSize().height-1);
     }
}