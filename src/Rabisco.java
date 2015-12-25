
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComponent;
import java.awt.image.BufferedImage;

@SuppressWarnings("serial")
public class Rabisco extends JComponent implements MouseListener, MouseMotionListener {
	private Color cor = Color.BLACK;
	boolean balde = false;
	boolean pincel = true;
	Graphics2D graphics2D;
	int xAtual, xVelho, yAtual, YVelho;
	private BufferedImage image = new BufferedImage(1081, 751, BufferedImage.TYPE_INT_ARGB);
	boolean imageLoad = true;

	public Rabisco() throws AWTException {
		addMouseListener(this);
		addMouseMotionListener(this);
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public Color getCor() {
		return cor;
	}

	public void setBalde(boolean balde) {
		this.balde = balde;
	}

	public void setPincel(boolean pincel) {
		this.pincel = pincel;
	}

	public boolean getBalde() {
		return balde;
	}

	public boolean getPincel() {
		return pincel;
	}

	public void clear() {
		graphics2D.setPaint(Color.white);
		graphics2D.fillRect(0, 0, getSize().width, getSize().height);
		graphics2D.setPaint(Color.black);
		repaint();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		// System.out.print("hey");
		graphics2D = (Graphics2D) image.getGraphics();
		graphics2D.draw((Shape) new Rectangle2D.Double(1080, 750, 1080, 750));
		if (image == null) {
			image = new BufferedImage(getSize().width, getSize().height, BufferedImage.TYPE_INT_RGB);
			graphics2D = (Graphics2D) image.createGraphics();
			graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			clear();
		}
		g.drawImage(image, 0, 0, null);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		if (pincel == true) {
			graphics2D.setPaint(cor);
			xAtual = e.getX();
			yAtual = e.getY();
			if (graphics2D != null)
				graphics2D.drawLine(xVelho, YVelho, xAtual, yAtual);
			repaint();
			xVelho = xAtual;
			YVelho = yAtual;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (pincel == true)
			xVelho = e.getX();
		YVelho = e.getY();
	}

	public BufferedImage getImage() {
		return image;
	}

	public void setImage(BufferedImage image) {
		this.image = image;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		int bgcolor = image.getRGB(e.getX(), e.getY());
		xAtual = e.getX();
		yAtual = e.getY();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (balde == true) {
			graphics2D.setColor(cor);
			int x, y;
			Color color = graphics2D.getColor();
			int bgcolor = image.getRGB(e.getX(), e.getY());
			try {
				x = e.getX();
				y = e.getY();
				preencher(x, y, bgcolor, color);
			} catch (AWTException ex) {
				Logger.getLogger(Rabisco.class.getName()).log(Level.SEVERE, null, ex);
			}
			graphics2D.setColor(cor);
		}
	}

	public void preencher(int x, int y, int bgcolor, Color color) throws AWTException {
		ArrayList<Point> point = new ArrayList<Point>();
		point.add(new Point(x, y));
		try {
			while (point.size() > 0) {
				Point p = point.remove(0); // reseta array para preencher
				x = p.x;
				y = p.y;
				if (bgcolor == image.getRGB(x, y)) {
					graphics2D.drawLine(x, y, x, y); // enquanto o bgcolor do
														// ponto em que foi
														// clicado for igual aos
														// pontos proximos
					point.add(new Point(x + 1, y));// move-se direita
					point.add(new Point(x - 1, y));// move-se esquerda
					point.add(new Point(x, y + 1));// para cima
					point.add(new Point(x, y - 1));// para baixo
				}
			}
			repaint();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}
}