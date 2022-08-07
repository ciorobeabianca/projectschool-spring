package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

import controller.IImageController;
import controller.ImageController;
import controller.ImageUtil;
import model.IImage;
import model.IPixel;
import model.Image;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;
import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;

/**
 * A class that represents the tests for the ImageController and its methods.
 */
public class ImageControllerTest {
  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IImage img;
  private StringBuilder ap;
  private ImageView view;
  private ImageModel model;
  Map<String, IImage> model1;
  ImageModel imgModel1;

  @Before
  public void setUp() {
    pix1 = new Pixel(23, 15, 67);
    pix2 = new Pixel(13, 136, 77);
    pix3 = new Pixel(145, 15, 98);
    pix4 = new Pixel(223, 97, 60);

    IPixel[][] arr1 = new IPixel[2][2];
    arr1[0][0] = pix1;
    arr1[0][1] = pix2;
    arr1[1][0] = pix3;
    arr1[1][1] = pix4;
    img = new Image(arr1);

    ap = new StringBuilder();
    view = new ImageViewImpl(ap);
    model = new ImageModelImpl();

    model1 = new HashMap<String, IImage>();
    model1.put("Image1", img);
    imgModel1 = new ImageModelImpl(model1);


  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullReadable() {
    IImageController i = new ImageController(null, ap, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    Readable rd = new StringReader("");
    IImageController i = new ImageController(rd, null, model, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullView() {
    Readable rd = new StringReader("1, 2, 3");
    IImageController i = new ImageController(rd, ap, model, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    Readable rd = new StringReader("1, 2, 3");
    IImageController i = new ImageController(rd, ap, null, view);
  }

  @Test(expected = IllegalArgumentException.class)
  public void TestNoValidInput() {
    Readable rd = new StringReader("1, 2, 3");
    IImageController i = new ImageController(rd, ap, model, view);
    i.goCommands();
  }

  @Test(expected = NoSuchElementException.class)
  public void TestNoNextInput() {
    Map<String, IImage> model1 = new HashMap<String, IImage>();
    model1.put("Image1", img);
    ImageModel imgModel1 = new ImageModelImpl(model1);
    Readable rd = new StringReader("brighten 20 Image1");
    IImageController i = new ImageController(rd, ap, imgModel1, view);
    i.goCommands();
  }

  @Test(expected = NoSuchElementException.class)
  public void TestNoNextInput2() {
    Map<String, IImage> model1 = new HashMap<String, IImage>();
    model1.put("Image1", img);
    ImageModel imgModel1 = new ImageModelImpl(model1);
    Readable rd = new StringReader("brighten 20");
    IImageController i = new ImageController(rd, ap, imgModel1, view);
    i.goCommands();
  }

  @Test
  public void testBrighten() {
    Readable rd = new StringReader("brighten 20 Image1 Image1-brighter");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

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

    c.goCommands();
    IImage imgup1 = model1.get("Image1-brighter");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup1, imgBright);

  }

  @Test
  public void TestFlipVertical() {
    Readable rd = new StringReader("vertical-flip Image1 Image1-vertical-flip");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pix3;
    arr[0][1] = pix4;
    arr[1][0] = pix1;
    arr[1][1] = pix2;
    IImage imgFlip = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-vertical-flip");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgFlip);

  }

  @Test
  public void TestHorizontalFlip() {

    Readable rd = new StringReader("horizontal-flip Image1 Image1-horizontal-flip");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pix2;
    arr[0][1] = pix1;
    arr[1][0] = pix4;
    arr[1][1] = pix3;
    IImage imgFlip = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-horizontal-flip");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgFlip);

  }

  @Test
  public void TestRedComponent() {
    IPixel pix11 = new Pixel(23, 15, 67);
    IPixel pix22 = new Pixel(13, 136, 77);
    IPixel pix33 = new Pixel(145, 15, 98);
    IPixel pix44 = new Pixel(223, 97, 60);

    IPixel[][] arr2 = new IPixel[2][2];
    arr2[0][0] = pix11;
    arr2[0][1] = pix22;
    arr2[1][0] = pix33;
    arr2[1][1] = pix44;
    IImage img2 = new Image(arr2);

    imgModel1.add("ImageR", img2);

    Readable rd = new StringReader("red-component ImageR ImageR-red-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

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

    c.goCommands();
    IImage imgup2 = imgModel1.getKey("ImageR-red-component");

    Assert.assertEquals(img2, imgModel1.getKey("ImageR"));
    Assert.assertEquals(imgup2, imgRed);
  }

  @Test
  public void TestBlueComponent() {
    Readable rd = new StringReader("blue-component Image1 Image1-blue-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

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

    c.goCommands();
    IImage imgup2 = model1.get("Image1-blue-component");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgBlue);

  }

  @Test
  public void TestGreenComponent() {
    Readable rd = new StringReader("green-component Image1 Image1-green-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(15, 15, 15);
    IPixel pixel2 = new Pixel(136, 136, 136);
    IPixel pixel3 = new Pixel(15, 15, 15);
    IPixel pixel4 = new Pixel(97, 97, 97);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgGreen = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-green-component");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgGreen);

  }

  @Test
  public void TestValueComponent() {
    Readable rd = new StringReader("value-component Image1 Image1-value-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(67, 67, 67);
    IPixel pixel2 = new Pixel(136, 136, 136);
    IPixel pixel3 = new Pixel(145, 145, 145);
    IPixel pixel4 = new Pixel(223, 223, 223);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgVal = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-value-component");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgVal);

  }

  @Test
  public void TestLumaComponent() {

    Readable rd = new StringReader("luma-component Image1 Image1-luma-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

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

    c.goCommands();
    IImage imgup2 = model1.get("Image1-luma-component");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  @Test
  public void TestIntensityComponent() {

    Readable rd = new StringReader("intensity-component Image1 Image1-intensity-component");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(35, 35, 35);
    IPixel pixel2 = new Pixel(75, 75, 75);
    IPixel pixel3 = new Pixel(86, 86, 86);
    IPixel pixel4 = new Pixel(126, 126, 126);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-intensity-component");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  // two operations together
  @Test
  public void TestBrightFlip() {
    Readable rd = new StringReader("brighten 20 Image1 Image1-brighter " +
            "vertical-flip Image1 Image1-vertical-flip");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

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

    IPixel[][] arr2 = new IPixel[2][2];
    arr[0][0] = pix3;
    arr[0][1] = pix4;
    arr[1][0] = pix1;
    arr[1][1] = pix2;
    IImage imgFlip = new Image(arr);

    c.goCommands();

    IImage imgup1 = imgModel1.getKey("Image1-brighter");
    IImage imgup20 = imgModel1.getKey("Image1-vertical-flip");

    Assert.assertEquals(imgup20, imgFlip);
    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup1, imgBright);

  }

  // file does not exist for Load
  @Test(expected = IllegalArgumentException.class)
  public void testLoad() {
    ImageUtil.loadPPM("aa");

  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidImage() {
    ImageUtil.loadPPM("aa");
  }

  // not a ppm file for load
  @Test(expected = IllegalArgumentException.class)
  public void testLoad2() {
    ImageUtil.loadPPM("apollonian_gasket.ascii");

  }

  // commented out the test because of the Java Style but it runs
  /*
  @Test
  public void loadSaveTest() {
    IPixel[][] img = ImageUtil.loadPPM("Res/kid.ppm");
    IImage i = new Image(img);
    ImageUtil.savePPM("Res/newFile.ppm", i);
  }

   */

}