
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
public class openFile extends JPanel {
private BufferedImage image = null;
public BufferedImage getImage(){
	JFileChooser filechooser= new JFileChooser();
    filechooser.setDialogTitle("Choose Your File");
    filechooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Images", "PNG"));
    filechooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg"));
    filechooser.setAcceptAllFileFilterUsed(false);
    if(filechooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION)
    {
        File file = filechooser.getSelectedFile();
        try
        {   
           image =ImageIO.read(file);
           repaint();
     
        }
        catch(IOException e)
        {

        }
}
    return image;
}
@Override
protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	if (image != null){
		g.drawImage(image, 0, 0, this);
	revalidate();
	repaint();
	}
}

}