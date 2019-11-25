package src;

import java.awt.BorderLayout;
import java.awt.Image;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagePanel extends JPanel{
	
	private static final long seriaVersionUID = 1L;
	private JLabel imageLabel;
	private ImageIcon transformedImageIcon;
	
	public ImagePanel() { 
		this.imageLabel = new JLabel();
		
		setLayout(new BorderLayout());
		
		setBorder(BorderFactory.createEmptyBorder(Constraints.IAMGE_LABEL_BORDER, Constraints.IAMGE_LABEL_BORDER, 
				Constraints.IAMGE_LABEL_BORDER, Constraints.IAMGE_LABEL_BORDER));
		
		add(imageLabel, BorderLayout.CENTER);
		
	}
	
	public void updateIamge(final Image image) {
		imageLabel.setIcon(new ImageIcon(scaleImage(image)));
	}
	
	private Image scaleImage(Image image) {
		return image.getScaledInstance(700, 500, Image.SCALE_SMOOTH);
	}
	
	public void loadImage(File file) {
		this.transformedImageIcon = new ImageIcon(file.getAbsolutePath());
		Image image = transformedImageIcon.getImage();
		
		updateIamge(image);
	}
	
}
