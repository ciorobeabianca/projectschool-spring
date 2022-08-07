package comspringdemo.projectschoolspring;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;
import view.HistogramCol;

/**
 * represents the test class for our histogram class.
 */
public class TestHistogram {
  private static void createAndShowHistogramForRed() {
    List<Integer> list1 = new ArrayList<Integer>();
    List<Integer> list2 = new ArrayList<Integer>();
    List<Integer> list3 = new ArrayList<Integer>();
    Random random = new Random();
    IPixel pixel1 = new Pixel(255, 255, 255);
    IPixel pixel2 = new Pixel(255, 255, 255);
    IPixel pixel3 = new Pixel(255, 255, 255);
    IPixel pixel4 = new Pixel(255, 255, 255);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage img = new Image(arr);

    HistogramCol mainPanel = new HistogramCol();
    mainPanel.setRedValues(img, 10, 10);

    JFrame frame = new JFrame("DrawGraph");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(mainPanel);
    frame.pack();
    frame.setLocationByPlatform(true);
    frame.setVisible(true);
  }


  /**
   * Runs the program and checks if the histogram works for a white image.
   *
   * @param args the arguments
   */
  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        createAndShowHistogramForRed();
      }
    });
  }
}
