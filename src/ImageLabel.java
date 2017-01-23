import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

class ImageLabel extends JLabel {
    private ImageIcon img;
 
    public void setImageIcon(ImageIcon img) {
        this.img = img;
    }
 
    @Override
    protected void paintComponent(Graphics g) {
        if (img != null) {
            g.drawImage(img.getImage(), 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

}


