package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.IImage;
import model.IPixel;
import model.Image;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the fields and methods of the Image class.
 */
public class ImageTest {
  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IPixel pix5;
  IPixel pix6;
  IPixel pix7;
  IPixel pix8;
  IPixel pix9;
  IPixel pix10;
  IPixel pix11;
  IPixel pix12;
  IPixel pix13;
  IPixel[][] pixels;
  IPixel[][] pixels2;
  IImage img;
  IImage img2;

  @Before
  public void setUp() {
    pix1 = new Pixel(10, 178, 99);
    pix2 = new Pixel(13, 46, 94);
    pix3 = new Pixel(124, 64, 73);
    pix4 = new Pixel(145, 94, 25);
    pix5 = new Pixel(98, 45, 33);
    pix6 = new Pixel(74, 70, 46);
    pix7 = new Pixel(25, 49, 33);
    pix8 = new Pixel(56, 145, 200);
    pix9 = new Pixel(213, 34, 98);
    pixels = new Pixel[3][3];
    pixels[0][0] = pix1;
    pixels[0][1] = pix2;
    pixels[0][2] = pix3;
    pixels[1][0] = pix4;
    pixels[1][1] = pix5;
    pixels[1][2] = pix6;
    pixels[2][0] = pix7;
    pixels[2][1] = pix8;
    pixels[2][2] = pix9;
    img = new Image(pixels);

    pix10 = new Pixel(71, 56, 98);
    pix11 = new Pixel(125, 63, 123);
    pix12 = new Pixel(76, 105, 204);
    pix13 = new Pixel(78, 137, 95);
    pixels2 = new Pixel[2][2];
    pixels2[0][0] = pix10;
    pixels2[0][1] = pix11;
    pixels2[1][0] = pix12;
    pixels2[1][1] = pix13;
    img2 = new Image(pixels2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testConstructor() {
    IImage img = new Image(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtExc() {
    Assert.assertEquals(img.getPixelAt(-2, 0), pix7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtExc2() {
    Assert.assertEquals(img2.getPixelAt(1, 3), pix7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtExc3() {
    Assert.assertEquals(img.getPixelAt(4, 0), pix7);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testGetPixelAtExc4() {
    Assert.assertEquals(img.getPixelAt(0, -1), pix7);
  }

  @Test
  public void testGetPixelAt() {
    Assert.assertEquals(img.getPixelAt(2, 0), pix7);
  }

  @Test
  public void testGetPixelAt2() {
    Assert.assertEquals(img.getPixelAt(1, 0), pix4);
  }

  @Test
  public void testGetPixelAt3() {
    Assert.assertEquals(img.getPixelAt(0, 0), pix1);
  }

  @Test
  public void testGetHeight() {
    Assert.assertEquals(img.getHeight(), 3);
  }

  @Test
  public void testGetHeight2() {
    Assert.assertEquals(img2.getHeight(), 2);
  }

  @Test
  public void testGetWidth() {
    Assert.assertEquals(img.getWidth(), 3);
  }

  @Test
  public void testGetWidth2() {
    Assert.assertEquals(img2.getWidth(), 2);
  }

}
