package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

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
 * tests class for the new methods added like sepia tone, blur etc.
 */
public class ImageControlllerTest2 {

  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IImage img;
  ImageModel imgModel1;
  Map<String, IImage> model1;
  Appendable ap;
  ImageView view;
  ImageModel model;

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


    ap = new StringBuilder();
    view = new ImageViewImpl(ap);
    model = new ImageModelImpl();

  }

  @Test
  public void TestBlur() {
    Readable rd = new StringReader("blur Image1 Image1-blur");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(5, 9, 5);
    IPixel pixel2 = new Pixel(5, 9, 5);
    IPixel pixel3 = new Pixel(5, 9, 5);
    IPixel pixel4 = new Pixel(6, 9, 6);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-blur");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  @Test
  public void TestSharpen() {
    Readable rd = new StringReader("sharpen Image1 Image1-sharpen");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(16, 28, 16);
    IPixel pixel2 = new Pixel(16, 28, 16);
    IPixel pixel3 = new Pixel(16, 28, 16);
    IPixel pixel4 = new Pixel(22, 28, 22);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-sharpen");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  @Test
  public void TestSepiaTone() {
    Readable rd = new StringReader("sepia-tone Image1 Image1-sepia-tone");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(16, 15, 11);
    IPixel pixel2 = new Pixel(16, 15, 11);
    IPixel pixel3 = new Pixel(16, 15, 11);
    IPixel pixel4 = new Pixel(21, 19, 14);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-sepia-tone");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  @Test
  public void TestGreyScale() {
    Readable rd = new StringReader("greyscale Image1 Image1-greyscale");
    IImageController c = new ImageController(rd, ap, imgModel1, view);

    IPixel pixel1 = new Pixel(14, 14, 14);
    IPixel pixel2 = new Pixel(14, 14, 14);
    IPixel pixel3 = new Pixel(14, 14, 14);
    IPixel pixel4 = new Pixel(16, 16, 16);

    IPixel[][] arr = new IPixel[2][2];
    arr[0][0] = pixel1;
    arr[0][1] = pixel2;
    arr[1][0] = pixel3;
    arr[1][1] = pixel4;
    IImage imgInt = new Image(arr);

    c.goCommands();
    IImage imgup2 = model1.get("Image1-greyscale");

    Assert.assertEquals(img, model1.get("Image1"));
    Assert.assertEquals(imgup2, imgInt);

  }

  // file does not exist for LoadOtherType
  @Test(expected = javax.imageio.IIOException.class)
  public void testLoadOther() throws IOException {
    ImageUtil.loadOtherType("aa");
  }

  /*
  // Commented out because of JavaDoc but it works
  @Test
  public void loadSaveOthersTest() throws IOException {
    IImage img = ImageUtil.loadOtherType("res/kid.jpg");
    ImageUtil.saveOthers("res/newFile.jpg", img);
  }
   */

  /*
  // file does not exist for LoadOtherType
  @Test(expected = IllegalArgumentException.class)
  public void testSaveOthers() throws IOException {
    IImage img = ImageUtil.loadOtherType("Res/kids.jpg");
    ImageUtil.saveOthers("aa",img);
  }

   */
}
