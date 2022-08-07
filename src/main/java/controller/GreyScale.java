package controller;

/**
 * A class that represents the greyscale color transformation and that extends the abstract class
 * ColorTrans, inheriting its constructor and method.
 */
public class GreyScale extends ColorTrans {

  /**
   * Constructs a greyscale color transformation using the constructor of the extended abstract
   * class.
   *
   * @param kernel the kernel that is applied to the provided pixel
   * @throws IllegalArgumentException if the kernel is null or if it is not 3X3.
   */
  public GreyScale(double[][] kernel) throws IllegalArgumentException {
    super(kernel);
  }

  /**
   * Constructs a greyscale color transformation using a default kernel.
   */
  public GreyScale() {
    super(new double[][]{{0.2126, 0.7512, 0.0722}, {0.2126, 0.7512, 0.0722},
      {0.2126, 0.7512, 0.0722}});
  }
}
