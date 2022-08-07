package controller;

/**
 * A class that represents the sepia tone color transformation and that extends the abstract class
 * ColorTrans, inheriting its constructor and method.
 */
public class SepiaTone extends ColorTrans {

  /**
   * Constructs a sepia tone color transformation using the constructor of the extended abstract
   * class.
   *
   * @param kernel the kernel that is applied to the provided pixel
   * @throws IllegalArgumentException if the kernel is null or if it is not 3X3.
   */
  public SepiaTone(double[][] kernel) {
    super(kernel);
  }

  /**
   * Constructs a sepia tone color transformation using a default kernel.
   */
  public SepiaTone() {
    super(new double[][]{{0.393, 0.769, 0.189}, {0.349, 0.686, 0.168}, {0.272, 0.534, 0.131}});
  }
}
