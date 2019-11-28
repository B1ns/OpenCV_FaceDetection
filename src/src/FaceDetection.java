package src;

	
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;

public class FaceDetection {
	
	private CascadeClassifier cascadeClassifier;
	
	public FaceDetection() {
		
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
		this.cascadeClassifier = new CascadeClassifier(Constraints.CASCADE_CLASSIFIER);
		
	}
	
	public void detectFaces(File file, ImagePanel imagePanel) {
		
		Mat image = Imgcodecs.imread(file.getAbsolutePath(), Imgcodecs.IMREAD_COLOR);
		
		
		MatOfRect faceDetections = new MatOfRect();
		cascadeClassifier.detectMultiScale(image, faceDetections);
		
		for(Rect rect : faceDetections.toArray()) {
			Imgproc.rectangle(image, 
					new Point(rect.x, rect.y), 
					new Point(rect.x + rect.width, rect.y + rect.height),
					new Scalar(100, 100, 250),
					10);
		}
		
		BufferedImage bufferedReader = convertMatToImage(image);
		imagePanel.updateIamge(bufferedReader);
	}
	
	private BufferedImage convertMatToImage(Mat mat) {
		
		int type = BufferedImage.TYPE_BYTE_GRAY;
		
		if (mat.channels() > 1) {
			type = BufferedImage.TYPE_3BYTE_BGR;
		}
		
		int buffeSize = mat.channels() * mat.cols() * mat.rows();
		
		byte[] bytes = new byte[buffeSize];
 		
		mat.get(0,0, bytes);
		
		BufferedImage image = new BufferedImage(mat.cols(), mat.rows(), type);
		final byte[] targetPixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
		
		System.arraycopy(bytes, 0, targetPixels, 0, bytes.length);
		return image;
		
	}

}
