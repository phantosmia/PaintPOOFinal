import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

public class saveFile {
	public void save(Component comp) {
		JFrame temp_frame = null;
		if (comp instanceof JFrame) {
			temp_frame = (JFrame) comp;
			comp = temp_frame.getContentPane();
		}
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("PNG Images", "PNG"));
		fileChooser.addChoosableFileFilter(new FileNameExtensionFilter("JPEG Images", "jpg"));
		fileChooser.setAcceptAllFileFilterUsed(false);
		if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			BufferedImage img = new BufferedImage(comp.getWidth(), comp.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = img.createGraphics();
			g2d.setColor(Color.WHITE);
			g2d.fillRect(0, 0, img.getWidth(), img.getHeight());
			comp.printAll(g2d);
			g2d.dispose();
			try {
				String ext = ((FileNameExtensionFilter) fileChooser.getFileFilter()).getExtensions()[0];
				ImageIO.write(img, ext, new File((fileChooser.getSelectedFile().getAbsolutePath() + "." + ext)));
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}
}