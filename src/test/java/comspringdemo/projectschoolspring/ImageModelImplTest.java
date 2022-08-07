package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the fields and methods of the ImageModelImpl class.
 */
public class ImageModelImplTest {

  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IPixel pix5;
  IPixel pix6;
  IPixel pix7;
  IPixel pix8;
  IImage img;
  ImageModel imgModel1;
  Map<String, IImage> model1;
  IImage img2;


  @Before
  public void setUp() {
    pix1 = new Pixel(23, 15, 67);
    pix2 = new Pixel(13, 136, 77);
    pix3 = new Pixel(145, 15, 98);
    pix4 = new Pixel(223, 97, 60);

    pix5 = new Pixel(13, 3, 20);
    pix6 = new Pixel(3, 6, 100);
    pix7 = new Pixel(19, 7, 30);
    pix8 = new Pixel(83, 23, 90);

    IPixel[][] arr1 = new IPixel[2][2];
    arr1[0][0] = pix1;
    arr1[0][1] = pix2;
    arr1[1][0] = pix3;
    arr1[1][1] = pix4;
    img = new Image(arr1);

    IPixel[][] arr2 = new IPixel[2][2];
    arr2[0][0] = pix5;
    arr2[0][1] = pix6;
    arr2[1][0] = pix7;
    arr2[1][1] = pix8;
    img2 = new Image(arr2);

    model1 = new HashMap<String, IImage>();
    model1.put("Image1", img);
    model1.put("Image2", img2);
    imgModel1 = new ImageModelImpl(model1);

  }

  // when the map provided is null
  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    ImageModel i = new ImageModelImpl(null);
  }

  // when the image provided is not in the Map
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() {
    imgModel1.brighten("Image", 20);
  }

  // when the image provided is not in the Map
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage1() {
    imgModel1.flipVertically("Image");
  }

  @Test
  public void testBrighten() {
    IPixel pixel1 = new Pixel(43, 35, 87);
    IPixel pixel2 = new Pixel(33, 156, 97);
    IPixel pixel3 = new Pixel(165, 35, 118);
    IPixel pixel4 = new Pixel(243, 117, 80);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgBright = new Image(arr);

    Assert.assertEquals(imgModel1.brighten("Image1", 20), imgBright);

  }

  @Test
  public void testDarken() {
    IPixel pixel1 = new Pixel(13, 5, 57);
    IPixel pixel2 = new Pixel(3, 126, 67);
    IPixel pixel3 = new Pixel(135, 5, 88);
    IPixel pixel4 = new Pixel(213, 87, 50);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgDark = new Image(arr);

    Assert.assertEquals(imgModel1.darken("Image1", 10), imgDark);
  }

  @Test
  public void testVisualizeVal() {
    IPixel pixel1 = new Pixel(20, 20, 20);
    IPixel pixel2 = new Pixel(100, 100, 100);
    IPixel pixel3 = new Pixel(30, 30, 30);
    IPixel pixel4 = new Pixel(90, 90, 90);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgVal = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeValue("Image2"), imgVal);
  }

  @Test
  public void testVisualizeInt() {

    IPixel pixel1 = new Pixel(12, 12, 12);
    IPixel pixel2 = new Pixel(36, 36, 36);
    IPixel pixel3 = new Pixel(18, 18, 18);
    IPixel pixel4 = new Pixel(65, 65, 65);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeIntensity("Image2"), imgInt);
  }

  @Test
  public void testVisualizeLuma() {
    IPixel pixel1 = new Pixel(20, 20, 20);
    IPixel pixel2 = new Pixel(105, 105, 105);
    IPixel pixel3 = new Pixel(48, 48, 48);
    IPixel pixel4 = new Pixel(121, 121, 121);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeLuma("Image1"), imgInt);
  }

  @Test
  public void testFlipHorizontal() {
    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pix2;
    arr[0][1] = pix1;
    arr[1][0] = pix4;
    arr[1][1] = pix3;
    IImage imgFlip = new Image(arr);

    Assert.assertEquals(imgModel1.flipHorizontally("Image1"), imgFlip);
  }

  @Test
  public void testFlipVertical() {
    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pix3;
    arr[0][1] = pix4;
    arr[1][0] = pix1;
    arr[1][1] = pix2;
    IImage imgFlip = new Image(arr);

    Assert.assertEquals(imgModel1.flipVertically("Image1"), imgFlip);
  }

  @Test
  public void testVisRedComponent() {
    IPixel pixel1 = new Pixel(23, 23, 23);
    IPixel pixel2 = new Pixel(13, 13, 13);
    IPixel pixel3 = new Pixel(145, 145, 145);
    IPixel pixel4 = new Pixel(223, 223, 223);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgRed = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeCompRGB("Image1", "Red"), imgRed);
  }

  @Test
  public void testVisGreenComponent() {
    IPixel pixel1 = new Pixel(3, 3, 3);
    IPixel pixel2 = new Pixel(6, 6, 6);
    IPixel pixel3 = new Pixel(7, 7, 7);
    IPixel pixel4 = new Pixel(23, 23, 23);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgGreen = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeCompRGB("Image2", "Green"), imgGreen);
  }

  @Test
  public void testVisBlueComponent() {
    IPixel pixel1 = new Pixel(67, 67, 67);
    IPixel pixel2 = new Pixel(77, 77, 77);
    IPixel pixel3 = new Pixel(98, 98, 98);
    IPixel pixel4 = new Pixel(60, 60, 60);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgBlue = new Image(arr);

    Assert.assertEquals(imgModel1.visualizeCompRGB("Image1", "Blue"), imgBlue);
  }

  @Test
  public void testAdd() {
    IPixel pixel1 = new Pixel(65, 63, 147);
    IPixel pixel2 = new Pixel(90, 87, 94);
    IPixel pixel3 = new Pixel(56, 93, 56);
    IPixel pixel4 = new Pixel(200, 10, 60);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;

    IImage imgNew = new Image(arr);

    imgModel1.add("Image3", imgNew);
    Assert.assertEquals(imgModel1.getKey("Image3"), imgNew);
  }

  @Test
  public void testGetKey() {
    Assert.assertEquals(img, imgModel1.getKey("Image1"));
  }
}


