package src;

import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class CameraDetector {

	private CascadeClassifier cascadeClassifier;
	private MatOfRect detectedFaces;
	private Mat coloredImage;
	private Mat greyImage;
	
	public void setCascadeClassifier(CascadeClassifier cascadeClassifierLoad) {
		cascadeClassifier = cascadeClassifierLoad;
	}
	
	public CameraDetector() {
		this.detectedFaces = new MatOfRect();
		this.coloredImage = new Mat();
		this.greyImage = new Mat();
		this.cascadeClassifier = new CascadeClassifier("C:\\Users\\qlsl7\\Desktop\\haarcascade_frontalface_alt.xml");
	}
	
	public Mat detect(Mat inputFrame) {
		
		inputFrame.copyTo(coloredImage);
		inputFrame.copyTo(greyImage);
		
		Imgproc.cvtColor(coloredImage, greyImage, Imgproc.COLOR_BGR2GRAY);
		Imgproc.equalizeHist(greyImage, greyImage);
	
		cascadeClassifier.detectMultiScale(greyImage, detectedFaces);
		
		showFaceOnScreen();
		
		return coloredImage;
		
	}
	
	private void showFaceOnScreen() {
		for(Rect rect : detectedFaces.toArray()) {
			Imgproc.rectangle(coloredImage, new Point(rect.x, rect.y), 
					new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(100, 100, 250), 10);
		}
	}

	public void setCascadeClassifier(String string) {this .cascadeClassifier = cascadeClassifier;}

}
