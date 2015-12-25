import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
@SuppressWarnings("serial")
public class Janela extends JFrame {
	private JMenuBar menu;
	private JMenu arquivo;
	private JMenu ferramentas;
	private JMenuItem paleta;
	private JMenuItem novo;
    private JMenuItem abrir;
    private JMenuItem sair;
    private JMenuItem salvar;
    private JMenuItem balde;
    private JMenuItem limpar;
    private JMenuItem pincel;
    private Rabisco tela;
	public Janela() throws AWTException {
		tela = new Rabisco();
		tela.setBorder(BorderFactory.createLineBorder(Color.red));
		setContentPane(tela);
		itens();
		menuBar();
		acoes();
		configurarTela();
	}

	public void itens(){
		  java.net.URL imageURL = this.getClass().getResource("/add_file.png");
		     //System.out.println(imageURL); //imageURL is printing correctly in console
		     ImageIcon imageAddFile = new ImageIcon(imageURL);
		     novo = new JMenuItem("Novo Arquivo");
		     novo.setIcon(imageAddFile);
		     imageURL = this.getClass().getResource("/open.png");
		     ImageIcon imageOpenFile = new ImageIcon(imageURL);
		     abrir = new JMenuItem("Abrir...");
		     abrir.setIcon(imageOpenFile);
		     imageURL = this.getClass().getResource("/save.png");
		     ImageIcon imageSaveFile = new ImageIcon(imageURL);
		     salvar = new JMenuItem("Salvar como...");
		     salvar.setIcon(imageSaveFile);
		     imageURL = this.getClass().getResource("/exit.png");
		     ImageIcon imageExit = new ImageIcon(imageURL);
		     sair = new JMenuItem("Sair");
		     sair.setIcon(imageExit);
		     imageURL = this.getClass().getResource("/fill.png");
		     ImageIcon imageFill = new ImageIcon(imageURL);
		     balde = new JMenuItem("Preencher...");
		     balde.setIcon(imageFill);
		     imageURL = this.getClass().getResource("/clear.png");
		     ImageIcon imageClear = new ImageIcon(imageURL);
		     limpar = new JMenuItem("Limpar");
		     limpar.setIcon(imageClear);
		     imageURL = this.getClass().getResource("/brush.png");
		     ImageIcon imageBrush = new ImageIcon(imageURL);
		     pincel = new JMenuItem("Pincel");
		     pincel.setIcon(imageBrush);
		     imageURL = this.getClass().getResource("/paleta.png");
		     ImageIcon paletaIcone = new ImageIcon(imageURL);
		     paleta = new JMenuItem("Paleta de Cores");
		     paleta.setIcon(paletaIcone);
	}
	public void menuBar(){
		menu = new JMenuBar();
		arquivo = new JMenu("Arquivo");
		ferramentas = new JMenu("Ferramentas");
		arquivo.add(novo);
		arquivo.add(abrir);
		arquivo.add(salvar);
		arquivo.add(sair);
		ferramentas.add(pincel);
		ferramentas.add(balde);
		ferramentas.add(paleta);
		ferramentas.add(limpar);
		menu.add(arquivo);
		menu.add(ferramentas);
		setJMenuBar(menu);
	}
	public void acoes(){
		salvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile save = new saveFile();
				save.save(tela);
			}
		});
		sair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Vc gostaria de salvar a imagem?","Warning",dialogButton);
				if(dialogResult == JOptionPane.YES_OPTION){
					saveFile save = new saveFile();
					save.save(tela);
					dispose();
				}
				else{
					dispose();
				}
			}
		});
		abrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile imagePane = new openFile();
				tela.setImage(imagePane.getImage());
				configurarTela();
			}
		});
		balde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				java.net.URL imageURL = this.getClass().getResource("/fill.png");
				ImageIcon imageFill = new ImageIcon(imageURL);
				setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
						imageFill.getImage(),
						new Point(0,0),"custom cursor"));
				tela.setBalde(true);
				tela.setPincel(false);
			}
		});
		
		novo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					new Janela();
				} catch (AWTException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		paleta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color c;
				c = JColorChooser.showDialog(
						((Component) arg0.getSource()).getParent(),
						"Color Picker", Color.black);
				
				tela.setCor(c);
				
			}
		});
		pincel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setCursor(Cursor.getDefaultCursor());
				tela.setBalde(false);
				tela.setPincel(true);
			}
		});
		limpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tela.clear();
			}
		});
		
		
	}
	public void configurarTela(){
		pack();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //assim nao fecha as outras janelas filhas
		setVisible(true);
		getContentPane().setBackground(Color.white);
		setSize(1080, 750);
	}

	public static void main(String[] args) throws AWTException {
		Janela j = new Janela();
		
	}
	
}
