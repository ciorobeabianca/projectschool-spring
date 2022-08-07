package controller;

/**
 * A class that represents the sharpen filter and that extends the abstract class Filter, inheriting
 * its constructor and method.
 */
public class Sharpen extends Filter {

  /**
   * Constructs a sharpen filter using a provided kernel and calling super from the constructor of
   * the abstract class it extends.
   *
   * @param kernel the kernel that is being applied to a pixel and the pixels surrounding it in a
   *               picture
   * @throws IllegalArgumentException if the kernel is null, has an even length or does not have
   *                                  equal width and height
   */
  public Sharpen(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  /**
   * Constructs a sharpen  filter using a default kernel.
   */
  public Sharpen() {
    super(new double[][]{{-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}});
  }
}
