package controller;

import model.IPixel;
import model.Pixel;

/**
 * An abstract class that represents a color transformation that can be applied to a pixel. This
 * class implements only one method which applies the color transformation on a provided Pixel.
 */
public abstract class ColorTrans {
  protected final double[][] kernel;

  /**
   * Constructs a color transformation using a kernel(2d array of double values) that is provided as
   * an argument.
   *
   * @param kernel the kernel that is applied to the provided pixel
   * @throws IllegalArgumentException if the kernel is null or if it is not 3X3.
   */
  public ColorTrans(double[][] kernel) throws IllegalArgumentException {
    if (kernel.length != 3 || kernel.length != kernel[0].length || kernel == null) {
      throw new IllegalArgumentException("Invalid kernel");
    }
    this.kernel = kernel;
  }

  /**
   * Applies the color transformation on a provided Pixel (applies the values of the kernel to the
   * rgb values of the pixel).
   *
   * @param p the pixel that is being processed
   * @return returns a new pixel that reflects the color transformation
   */
  public IPixel applyColorTrans(IPixel p) {
    int red = p.getRed();
    int blue = p.getBlue();
    int green = p.getGreen();
    int r;
    int g;
    int b;
    r = (int) (red * kernel[0][0] + green * kernel[0][1] + blue * kernel[0][2]);
    g = (int) (red * kernel[1][0] + green * kernel[1][1] + blue * kernel[1][2]);
    b = (int) (red * kernel[2][0] + green * kernel[2][1] + blue * kernel[2][2]);
    if (r < 0) {
      r = 0;
    }
    if (r > 255) {
      r = 255;
    }
    if (g < 0) {
      g = 0;
    }
    if (g > 255) {
      g = 255;
    }
    if (b < 0) {
      b = 0;
    }
    if (b > 255) {
      b = 255;
    }
    return new Pixel(r, g, b);
  }
}
