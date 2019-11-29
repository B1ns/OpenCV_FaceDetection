package src;

import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;

public class CameraFrame extends JFrame {

	private static final long seriaVersionUID = 1L;
	
	private CameraDetector detector;
	private CameraPanel cameraPanel;

	public CameraFrame() {
		super("OpenCV for ¾ó±¼ ÀÎ½Ä AI");

		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		detector = new CameraDetector();
		cameraPanel = new CameraPanel();

		setContentPane(cameraPanel);

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setVisible(true);
	}

	public void displayScreen() {

		Mat webCamera = new Mat();
		VideoCapture videoCapture = new VideoCapture(0);

		if (videoCapture.isOpened()) {

			while (true) {

				videoCapture.read(webCamera);

				if (!webCamera.empty()) {
					setSize(webCamera.width() + 50, webCamera.height() + 70);
					webCamera = detector.detect(webCamera);

					cameraPanel.convertMatToImage(webCamera);
					cameraPanel.repaint();

				} else {
					System.out.println("¿¡·¯´Ù µµ¸ÁÃÄ ~!");
					break;
				}

			}
		}
	}

}
