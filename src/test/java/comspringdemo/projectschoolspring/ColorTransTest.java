package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import controller.GreyScale;
import controller.SepiaTone;
import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * A class that represents the tests for the classes that extend ColorTrans.
 */
public class ColorTransTest {

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
    GreyScale g = new GreyScale(kernelEvenArray);

  }

  // even kernel for sepia tone
  @Test(expected = IllegalArgumentException.class)
  public void testConstructor0() {
    double[][] kernelEvenArray = new double[][]{
            {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
            {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0}};
    SepiaTone s = new SepiaTone(kernelEvenArray);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor2() {
    double[][] kernelNull = {};
    GreyScale g = new GreyScale(kernelNull);

  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor3() {
    double[][] kernelNull = {};
    SepiaTone s = new SepiaTone(kernelNull);

  }

  // test sepiatone for a pixel
  @Test
  public void testSepiaTonePixel() {
    SepiaTone b = new SepiaTone();
    IPixel pixExe = b.applyColorTrans(pix1);
    IPixel pixNew = new Pixel(16, 15, 11);
    Assert.assertEquals(pixExe, pixNew);
  }


  // test sepiatone for a pixel
  @Test
  public void testSepiaTonePixel1() {
    SepiaTone b = new SepiaTone();
    IPixel pixExe = b.applyColorTrans(pix4);
    IPixel pixNew = new Pixel(21, 19, 14);
    Assert.assertEquals(pixExe, pixNew);
  }


  // test sepiatone for the whole image
  @Test
  public void testSepiaToneImage() {
    IImage iSepia = img.sepiaTone();
    IPixel pixExe = iSepia.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(16, 15, 11);
    Assert.assertEquals(pixExe, pixNew);

    IPixel pixExe1 = iSepia.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(16, 15, 11);
    Assert.assertEquals(pixExe1, pixNew1);

    IPixel pixExe2 = iSepia.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(21, 19, 14);
    Assert.assertEquals(pixExe2, pixNew2);
  }

  // test sepiatone for the model
  @Test
  public void testSepiaToneModel() {
    IImage iSepia = imgModel1.sepiaTone("Image1");
    IPixel pixExe = iSepia.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(16, 15, 11);
    Assert.assertEquals(pixExe, pixNew);

    IPixel pixExe1 = iSepia.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(16, 15, 11);
    Assert.assertEquals(pixExe1, pixNew1);

    IPixel pixExe2 = iSepia.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(21, 19, 14);
    Assert.assertEquals(pixExe2, pixNew2);
  }


  // test greyScale for a pixel
  @Test
  public void testGreyScalePixel() {
    GreyScale b = new GreyScale();
    IPixel pixExe = b.applyColorTrans(pix1);
    IPixel pixNew = new Pixel(14, 14, 14);
    Assert.assertEquals(pixExe, pixNew);
  }


  // test greyScale for a pixel
  @Test
  public void testGreyScalePixel1() {
    GreyScale b = new GreyScale();
    IPixel pixExe = b.applyColorTrans(pix4);
    IPixel pixNew = new Pixel(16, 16, 16);
    Assert.assertEquals(pixExe, pixNew);
  }

  // test greyScale for the whole image
  @Test
  public void testGreyscaleImage() {
    IImage iGrey = img.greyScale();
    IPixel pixExe = iGrey.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(14, 14, 14);
    Assert.assertEquals(pixExe, pixNew);

    IPixel pixExe1 = iGrey.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(14, 14, 14);
    Assert.assertEquals(pixExe1, pixNew1);

    IPixel pixExe2 = iGrey.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(16, 16, 16);
    Assert.assertEquals(pixExe2, pixNew2);
  }

  // test greyScale for the model
  @Test
  public void testGreyscaleModel() {
    IImage iGrey = imgModel1.greyScale("Image1");
    IPixel pixExe = iGrey.getPixelAt(0, 0);
    IPixel pixNew = new Pixel(14, 14, 14);
    Assert.assertEquals(pixExe, pixNew);

    IPixel pixExe1 = iGrey.getPixelAt(0, 1);
    IPixel pixNew1 = new Pixel(14, 14, 14);
    Assert.assertEquals(pixExe1, pixNew1);

    IPixel pixExe2 = iGrey.getPixelAt(1, 1);
    IPixel pixNew2 = new Pixel(16, 16, 16);
    Assert.assertEquals(pixExe2, pixNew2);
  }

}


