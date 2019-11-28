package src;

import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class MainFrame extends JFrame {

	private static final long seriaVersionUID = 1L;
	private ImagePanel iamgePanel;
	private JFileChooser fileChooser;
	private FaceDetection faceDetetection;
	private File file;
	
	public MainFrame() {
		super(Constraints.APPLICATION_NAME);
		
		setJMenuBar(createMenuBar());
		
		this.iamgePanel = new ImagePanel();
		this.fileChooser = new JFileChooser();
		this.faceDetetection = new FaceDetection();
		
		add(iamgePanel, BorderLayout.CENTER);
		
		setSize(Constraints.FRAME_WIDTH, Constraints.FARME_HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(this);
		
	}

	public JMenuBar createMenuBar() {
		
		JMenuBar menuBar = new JMenuBar();
		
		JMenu fileMenu = new JMenu("파일");
		
		JMenuItem loadMenuItem = new JMenuItem("이미지 불러오기");
		JMenuItem detectMenuItem = new JMenuItem("얼굴 인식");
		JMenuItem exitMenuItem = new JMenuItem("종료 하기");
		
		fileMenu.add(loadMenuItem);
		fileMenu.add(detectMenuItem);
		fileMenu.add(exitMenuItem);
		
		loadMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
					MainFrame.this.file = fileChooser.getSelectedFile();
					MainFrame.this.iamgePanel.loadImage(MainFrame.this.file);
				}
				
			}
		});
		
		detectMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				MainFrame.this.faceDetetection.detectFaces(MainFrame.this.file, MainFrame.this.iamgePanel);
			}
		});
		
		
		exitMenuItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int action  = JOptionPane.showConfirmDialog(MainFrame.this , Constraints.EXIT_WARNING );
				
				if ( action == JOptionPane.OK_OPTION) {
					System.gc();
					System.exit(0);
				}
			}
		});
		
		JMenu developerMenu = new JMenu("Developer");
		JMenu aboutMenu = new JMenu("about");
		JMenu helpMenu = new JMenu("Help");

		
		JMenuItem developerItem = new JMenuItem("개발자");
		developerMenu.add(developerItem);
		
		developerItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				developDialog();
			}
		});
		
		
		menuBar.add(fileMenu);
		menuBar.add(developerMenu);
		menuBar.add(aboutMenu);
		menuBar.add(helpMenu);
		
		return menuBar;
	}
	
	private void developDialog() {
		
		
		ImageIcon icon = new ImageIcon(MainFrame.class.getResource("bins.png"));
		
		Image userImageData =  icon.getImage();
		Image userImage = userImageData.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		
		ImageIcon userIcon = new ImageIcon(userImage);
		
		
		JOptionPane.showMessageDialog(null, 
				"개발자 : 정빈", 
				"OpenCV를 이용한 얼굴인식 AI을 만든 사람은 ?", 
				JOptionPane.OK_OPTION,  
				userIcon);
	}
	
}




