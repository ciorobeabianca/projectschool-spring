package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import controller.Blur;
import controller.Sharpen;
import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * A class that represents the tests for the classes that extend Filter.
 */
public class FilterTest {

  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IImage img;
  ImageModel imgModel1;
  Map<String, IImage> model1;


  @Before
  public void setUp() {
    pix1 = new Pixel(8, 16, 8);
    pix2 = new Pixel(8, 16, 8);
    pix3 = new Pixel(8, 16, 8);
    pix4 = new Pixel(16, 16, 16);

    IPixel[][] arr1 = new IPixel[2][2];
    arr1[0][0] = pix1;
    arr1[0][1] = pix2;
    arr1[1][0] = pix3;
    arr1[1][1] = pix4;
    img = new Image(arr1);

    model1 = new HashMap<String, IImage>();
    model1.put("Image1", img);
    imgModel1 = new ImageModelImpl(model1);
  }

  // even kernel
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor1() {
    double[][] kernelEvenArray = new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0}};
    Blur kernelEven = new Blur(kernelEvenArray);

  }

  // null kernel
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    double[][] kernelNull = {};
    Blur b = new Blur(kernelNull);
  }

  // null kernel with sharpen object
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    double[][] kernelNull = {};
    Sharpen b = new Sharpen(kernelNull);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyFilter() {
    Blur b = new Blur();
    IPixel pixExe = b.applyFilter(img, -1, -4);
  }


  @Test(expected = IllegalArgumentException.class)
  public void testApplyFilter2() {
    Blur b = new Blur();
    IPixel pixExe = b.applyFilter(img, 1, 81);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyFilter3() {
    Sharpen s = new Sharpen();
    IPixel pixExe = s.applyFilter(img, 100, 4);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testApplyFilter4() {
    Sharpen s = new Sharpen();
    IPixel pixExe = s.applyFilter(img, -1, -4);
  }

  // test for a single pixel
  @Test
  public void testBlurPixel() {
    Blur b = new Blur();
    IPixel pixExe = b.applyFilter(img, 0, 0);
    IPixel pixNew = new Pixel(5, 9, 5);
    Assert.assertEquals(pixNew, pixExe);
  }


  // test for the whole image
  @Test
  public void testBlurImage() {
    IImage iBlur = img.blur();
    IPixel pixExe = iBlur.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(5, 9, 5);
    Assert.assertEquals(pixNew, pixExe);
    IPixel pixExe1 = iBlur.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(5, 9, 5);
    Assert.assertEquals(pixNew1, pixExe1);
  }

  // test blurring the model
  @Test
  public void testBlurModel() {
    IImage iBlur = imgModel1.blur("Image1");
    IPixel pixExe = iBlur.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(5, 9, 5);
    Assert.assertEquals(pixNew, pixExe);
    IPixel pixExe1 = iBlur.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(5, 9, 5);
    Assert.assertEquals(pixNew1, pixExe1);
  }

  // test for sharpening a pixel
  @Test
  public void testSharpenPixel() {
    Sharpen s = new Sharpen();
    IPixel pixExe = s.applyFilter(img, 0, 0);
    IPixel pixNew = new Pixel(16, 28, 16);
    Assert.assertEquals(pixNew, pixExe);
  }

  // test for the whole image
  @Test
  public void testSharpenImage() {
    IImage iSharp = img.sharpen();
    IPixel pixExe = iSharp.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(16, 28, 16);
    Assert.assertEquals(pixNew, pixExe);
    IPixel pixExe1 = iSharp.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(16, 28, 16);
    Assert.assertEquals(pixNew1, pixExe1);
    IPixel pixExe2 = iSharp.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(22, 28, 22);
    Assert.assertEquals(pixNew2, pixExe2);
  }

  // test for sharpening the model
  @Test
  public void testSharpenModel() {
    IImage iSharp = imgModel1.sharpen("Image1");
    IPixel pixExe = iSharp.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(16, 28, 16);
    Assert.assertEquals(pixNew, pixExe);
    IPixel pixExe1 = iSharp.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(16, 28, 16);
    Assert.assertEquals(pixNew1, pixExe1);
    IPixel pixExe2 = iSharp.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(22, 28, 22);
    Assert.assertEquals(pixNew2, pixExe2);
  }

}

