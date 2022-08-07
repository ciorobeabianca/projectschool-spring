package model;

import static java.util.Objects.hash;

/**
 * A class that represents a Pixel and implements the IPixel interface, inheriting all its methods.
 * A Pixel is represented by three integer values which are the components for red, green and blue.
 */
public class Pixel implements IPixel {
  private int red;
  private int blue;
  private int green;

  /**
   * Constructs a Pixel using the three color components which are passed in as arguments.
   *
   * @param red   the component of the color red
   * @param green the component of the color green
   * @param blue  the component of the color blue
   * @throws IllegalArgumentException throws an exception if any of the values are negative or
   *                                  greater than 255 (which is the maximum value for a color).
   */
  public Pixel(int red, int green, int blue) throws IllegalArgumentException {
    if (red >= 0 && red <= 255 && blue >= 0 && blue <= 255 && green >= 0 && green <= 255) {
      this.red = red;
      this.blue = blue;
      this.green = green;
    } else {
      throw new IllegalArgumentException("Invalid color");
    }
  }

  public int getRed() {
    return this.red;
  }

  public int getGreen() {
    return this.green;
  }

  public int getBlue() {
    return this.blue;
  }

  @Override
  public IPixel visualizeVal() {
    int red = this.getRed();
    int blue = this.getBlue();
    int green = this.getGreen();
    int max = Math.max(Math.max(red, blue), green);
    return new Pixel(max, max, max);
  }

  @Override
  public IPixel visualizeInt() {
    int red = this.getRed();
    int blue = this.getBlue();
    int green = this.getGreen();
    int avg = (red + green + blue) / 3;
    return new Pixel(avg, avg, avg);
  }

  @Override
  public IPixel visualizeL() {
    int red = this.getRed();
    int blue = this.getBlue();
    int green = this.getGreen();
    int luma = (int) (0.2126 * red + 0.7152 * green + 0.0722 * blue);
    return new Pixel(luma, luma, luma);
  }

  @Override
  public void bright(int b) {
    int red = this.getRed() + b;
    int blue = this.getBlue() + b;
    int green = this.getGreen() + b;
    if (red < 0) {
      red = 0;
    }
    if (red > 255) {
      red = 255;
    }

    if (green < 0) {
      green = 0;
    }
    if (green > 255) {
      green = 255;
    }

    if (blue < 0) {
      blue = 0;
    }
    if (blue > 255) {
      blue = 255;
    }

    this.red = red;
    this.blue = blue;
    this.green = green;
  }

  @Override
  public void dark(int d) {
    this.bright(-d);
  }

  @Override
  public IPixel visualizeRed() {
    int red = this.getRed();
    return new Pixel(red, red, red);
  }

  @Override
  public IPixel visualizeBlue() {
    int blue = this.getBlue();
    return new Pixel(blue, blue, blue);
  }

  @Override
  public IPixel visualizeGreen() {
    int green = this.getGreen();
    return new Pixel(green, green, green);
  }

  // overrides the equals method
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Pixel)) {
      return false;
    }

    IPixel pixel = (Pixel) o;
    return (this.getRed() == pixel.getRed() && this.getBlue() == pixel.getBlue() && this.getGreen()
            == pixel.getGreen());
  }

  // overrides the hash code
  @Override
  public int hashCode() {
    return hash(this.getRed(), this.getGreen(), this.getBlue());
  }
}


