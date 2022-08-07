package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.IPixel;
import model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Test class for the fields and methods of the Pixel class.
 */
public class PixelTest {
  IPixel pix1;
  IPixel pix2;
  IPixel pix3;
  IPixel pix4;
  IPixel pix5;

  @Before
  public void setUp() {
    pix1 = new Pixel(23, 15, 67);
    pix2 = new Pixel(13, 136, 77);
    pix3 = new Pixel(145, 15, 98);
    pix4 = new Pixel(223, 97, 60);
    pix5 = new Pixel(130, 53, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor() {
    IPixel pix = new Pixel(-1, 20, 10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor2() {
    IPixel pix = new Pixel(10, -200, 78);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor3() {
    IPixel pix = new Pixel(67, 20, -10);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor4() {
    IPixel pix = new Pixel(100, 260, 17);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor5() {
    IPixel pix = new Pixel(300, 26, 140);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testExcConstructor6() {
    IPixel pix = new Pixel(12, 200, 400);
  }

  @Test
  public void testGetRed() {
    Assert.assertEquals(pix1.getRed(), 23, 0.01);
  }

  @Test
  public void testGetRed2() {
    Assert.assertEquals(pix2.getRed(), 13, 0.001);
  }

  @Test
  public void testGetBlue() {
    Assert.assertEquals(pix3.getBlue(), 98, 0.001);
  }

  @Test
  public void testGetBlue2() {
    Assert.assertEquals(pix4.getBlue(), 60, 0.001);
  }

  @Test
  public void testGetGreen() {
    Assert.assertEquals(pix5.getGreen(), 53, 0.01);
  }

  @Test
  public void testGetGreen2() {
    Assert.assertEquals(pix4.getGreen(), 97, 0.01);
  }

  @Test
  public void testVisualizeVal() {
    Assert.assertEquals(pix1.visualizeVal(), new Pixel(67, 67, 67));
  }

  @Test
  public void testVisualizeVal2() {
    Assert.assertEquals(pix4.visualizeVal(), new Pixel(223, 223, 223));
  }

  @Test
  public void testVisualizeIntensity() {
    Assert.assertEquals(pix1.visualizeInt(), new Pixel(35, 35, 35));
  }

  @Test
  public void testVisualizeLuma() {
    int luma = (int) (0.2126 * 145 + 0.7152 * 15 + 0.0722 * 98);
    Assert.assertEquals(pix3.visualizeL(), new Pixel(luma, luma, luma));
  }

  @Test
  public void testVisualizeLuma2() {
    int luma = (int) (0.2126 * 130 + 0.7152 * 53 + 0.0722 * 200);
    Assert.assertEquals(pix5.visualizeL(), new Pixel(luma, luma, luma));
  }

  @Test
  public void testVisualizeRed() {
    Assert.assertEquals(pix4.visualizeRed(), new Pixel(223, 223, 223));
  }

  @Test
  public void testVisualizeGreen() {
    Assert.assertEquals(pix3.visualizeGreen(), new Pixel(15, 15, 15));
  }

  @Test
  public void testVisualizeBlue() {
    Assert.assertEquals(pix2.visualizeBlue(), new Pixel(77, 77, 77));
  }

  @Test
  public void brightBy() {
    pix1.bright(10);
    Assert.assertEquals(pix1, new Pixel(33, 25, 77));
  }

  @Test
  public void darkBy() {
    pix2.dark(10);
    Assert.assertEquals(pix2, new Pixel(3, 126, 67));
  }
}