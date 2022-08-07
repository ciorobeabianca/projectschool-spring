package model;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import controller.Blur;
import controller.GreyScale;
import controller.SepiaTone;
import controller.Sharpen;

import static java.util.Objects.hash;

/**
 * A class that represents an Image and implements the interface IImage, thus, containing all the
 * methods of the interface. The image is represented by a 2D array of pixels.
 */
public class Image implements IImage {
  protected IPixel[][] pixels;

  /**
   * Constructs an Image through a 2D array of pixels passed in as an argument.
   *
   * @param pixels an 2D array of pixels
   * @throws IllegalArgumentException if the argument (the array of pixels) is null
   */
  public Image(IPixel[][] pixels) throws IllegalArgumentException {
    if (pixels == null) {
      throw new IllegalArgumentException("Null array of pixels");
    }
    this.pixels = createImage(pixels);
  }

  //populate image with pixel
  // create board check everything is filled nothing should be null
  private IPixel[][] createImage(IPixel[][] pixelsArray) {
    IPixel[][] arr = new IPixel[pixelsArray.length][pixelsArray[0].length];
    Objects.requireNonNull(pixelsArray);
    for (int i = 0; i < pixelsArray.length; i++) {
      Objects.requireNonNull(pixelsArray[i]);
      for (int j = 0; j < pixelsArray[i].length; j++) {
        Objects.requireNonNull(pixelsArray[i][j]);
        arr[i][j] = pixelsArray[i][j];
      }
    }
    return arr;
  }

  /**
   * Takes in a position and returns the pixel at the given row and column from the array that
   * represents this image.
   *
   * @param i the row index of the pixel in the array(image)
   * @param j the column index of the pixel in the array(image)
   * @return returns the pixel at the provided position from the array that represents the image
   * @throws IllegalArgumentException throws an exception if the given position is not valid
   */
  public IPixel getPixelAt(int i, int j) throws IllegalArgumentException {
    if (i >= this.getHeight() || i < 0 || j >= this.getWidth() || j < 0) {
      throw new IllegalArgumentException("Invalid argument");
    }
    return this.pixels[i][j];
  }

  @Override
  public int getWidth() {
    return pixels[0].length;
  }

  @Override
  public int getMaxColVal() {
    int max = 0;
    for (int i = 0; i < this.getHeight(); i++) {
      for (int j = 0; j < this.getWidth(); j++) {
        int red = this.getPixelAt(i, j).getRed();
        int blue = this.getPixelAt(i, j).getBlue();
        int green = this.getPixelAt(i, j).getGreen();
        int maxVal = Math.max(Math.max(red, blue), green);
        if (maxVal > max) {
          max = maxVal;
        }
      }
    }
    return max;
  }

  @Override
  public IImage copyImage() {
    IPixel[][] copyImg = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        copyImg[i][j] = this.pixels[i][j];
      }
    }
    IImage imgNew = new Image(copyImg);
    return imgNew;
  }

  @Override
  public IImage blur() {
    Blur b = new Blur();
    IPixel[][] copyImg = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        IPixel pix = b.applyFilter(this, i, j);
        copyImg[i][j] = pix;
      }
    }
    IImage imgNew = new Image(copyImg);
    return imgNew;
  }

  @Override
  public IImage sharpen() {
    IPixel[][] copyImg = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        IPixel pix = new Sharpen().applyFilter(this, i, j);
        copyImg[i][j] = pix;
      }
    }
    return new Image(copyImg);
  }

  @Override
  public IImage greyScale() {
    IPixel[][] copyImg = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        IPixel pix = new GreyScale().applyColorTrans(pixels[i][j]);
        copyImg[i][j] = pix;
      }
    }
    return new Image(copyImg);
  }

  @Override
  public IImage sepiaTone() {
    IPixel[][] copyImg = new IPixel[pixels.length][pixels[0].length];
    for (int i = 0; i < pixels.length; i++) {
      for (int j = 0; j < pixels[i].length; j++) {
        IPixel pix = new SepiaTone().applyColorTrans(pixels[i][j]);
        copyImg[i][j] = pix;
      }
    }
    return new Image(copyImg);
  }

  @Override
  public int getHeight() {
    return pixels.length;
  }

  // overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }

    if (!(o instanceof Image)) {
      return false;
    }

    Image that = (Image) o;
    int count = 0;
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        if (!(this.pixels[i][j].equals(that.pixels[i][j]))) {
          count++;
        }
      }
    }
    return (count == 0);
  }

  // overrides the hash code
  @Override
  public int hashCode() {
    return hash(this.pixels);
  }

  @Override
  public BufferedImage createBufferImage() {
    BufferedImage b = new BufferedImage(this.getWidth(), this.getHeight(),
            BufferedImage.TYPE_INT_RGB);
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        Color rgb = new Color(this.pixels[i][j].getRed(), this.pixels[i][j].getGreen(),
                this.pixels[i][j].getBlue());
        b.setRGB(i, j, rgb.getRGB());
      }
    }
    return b;
  }

  @Override
  public List<Integer> getRedValues() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        list.add(this.pixels[i][j].getRed());
      }
    }
    return list;
  }

  @Override
  public List<Integer> getGreenValues() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        list.add(this.pixels[i][j].getGreen());
      }
    }
    return list;
  }

  @Override
  public List<Integer> getBlueValues() {
    List<Integer> list = new ArrayList<>();
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        list.add(this.pixels[i][j].getBlue());
      }
    }
    return list;
  }

  @Override
  public boolean isGreyScale() {
    List<Integer> list = new ArrayList<>();
    int c = 0;
    for (int i = 0; i < this.pixels.length; i++) {
      for (int j = 0; j < this.pixels[i].length; j++) {
        int red = this.pixels[i][j].getRed();
        int blue = this.pixels[i][j].getBlue();
        int green = this.pixels[i][j].getGreen();
        if (red != blue || blue != green || red != green) {
          c++;
        }
      }
    }
    return (c == 0);
  }

  private int frequencyHelper(List<Integer> values, int value) {
    int ct = 0;
    for (int i = 0; i < values.size(); i++) {
      if (value == values.get(i)) {
        ct++;
      }
    }
    return ct;
  }

  /**
   * creates a list with the frequencies of the provided list of values.
   *
   * @param values list of values whose frequency needs to be calculated
   * @return List of integers representing the frequency of the provided values
   */
  public List<Integer> frequency(List<Integer> values) {
    List<Integer> f = new ArrayList<Integer>();
    for (int i = 0; i <= 256; i++) {
      int x = frequencyHelper(values, i);
      f.add(x);
    }
    return f;
  }
}
