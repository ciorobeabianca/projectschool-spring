package controller;

import model.IImage;
import model.IPixel;
import model.Pixel;

/**
 * An abstract class that represents a filter that can be applied to a pixel from a picture. This
 * class implements only one method which applies the filter on a provided Pixel.
 */
public abstract class Filter {

  protected final double[][] kernel;

  /**
   * Constructs a filter using a kernel(2d array of double values) that is provided as an
   * argument.
   *
   * @param kernel the kernel that is applied to the provided pixel and the pixels surrounding it in
   *               order to create a blur effect
   * @throws IllegalArgumentException if the kernel is null, has an even length or does not have
   *                                  equal width and height
   */
  protected Filter(double[][] kernel) throws IllegalArgumentException {
    if (kernel.length % 2 == 0 || kernel.length != kernel[0].length || kernel == null) {
      throw new IllegalArgumentException("Invalid kernel");
    }
    this.kernel = kernel;
  }

  /**
   * Applies this filter to the pixel at the provided position in the given image.
   *
   * @param img the image in which the pixel that should be filtered is
   * @param r   the row of the pixel in the image
   * @param c   the column of the pixel in the image
   * @return returns a new pixel that represents the filter applied
   * @throws IllegalArgumentException throws an exception if the position is not within the
   *                                  dimensions of the image
   */
  public IPixel applyFilter(IImage img, int r, int c) throws IllegalArgumentException {
    if (r < 0 || r > img.getHeight() || c < 0 || c > img.getWidth()) {
      throw new IllegalArgumentException("Invalid position");
    }
    int size = kernel.length;
    int red = 0;
    int green = 0;
    int blue = 0;
    int a = 0;
    for (int i = r - size / 2; i <= r + size / 2; i++) {
      int b = 0;
      for (int j = c - size / 2; j <= c + size / 2; j++) {

        if (i < 0 || j < 0 || i >= img.getHeight() || j >= img.getWidth()) {
          b++;
          continue;
        }

        IPixel oldPixel = img.getPixelAt(i, j);
        red += kernel[a][b] * oldPixel.getRed();
        green += kernel[a][b] * oldPixel.getGreen();
        blue += kernel[a][b] * oldPixel.getBlue();

        b++;
      }
      a++;
    }
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

    return new Pixel(red, green, blue);
  }
}