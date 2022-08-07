package model;

/**
 * An interface that represents a Pixel and the methods that operate on this type of object.
 */
public interface IPixel {
  /**
   * Determines and returns the value of the red component of this IPixel.
   *
   * @return the value of the red component of this IPixel
   */
  int getRed();

  /**
   * Determines and returns the value of the blue component of this IPixel.
   *
   * @return the value of the blue component of this IPixel
   */
  int getBlue();

  /**
   * Determines and returns the value of the green component of this IPixel.
   *
   * @return the value of the green component of this IPixel
   */
  int getGreen();

  /**
   * Visualizes the value of this Pixel(sets the value of each color to the maximum color value of
   * the three).
   *
   * @return the Pixel with the updated color values(all set to the maximum value of the three)
   */
  IPixel visualizeVal();

  /**
   * Visualizes the intensity of this Pixel(sets the value of each color to the average value
   * of the three components).
   *
   * @return the Pixel with the updated color values(all set to the average value of the three)
   */
  IPixel visualizeInt();

  /**
   * Visualizes the Luma of this Pixel(sets the value of each color to the value of the Luma
   * of the three components, which would be: 0.2126𝑟+0.7152𝑔+0.0722𝑏).
   *
   * @return the Pixel with the updated color values(all set to the average value of the three)
   */
  IPixel visualizeL();

  /**
   * Brightens this Pixel by the provided value (it increases each color value by the given number).
   *
   * @param b the value by which we brighten this Pixel
   */
  void bright(int b);

  /**
   * Darkens this Pixel by the provided value (it decreases each color value by the given number).
   *
   * @param d the value by which we darken this Pixel
   */
  void dark(int d);

  /**
   * Visualizes the red component (sets all the color values to the red component)
   * of this Pixel and returns the updated version.
   *
   * @return the updated pixel that has all the color values set to the red component
   */
  IPixel visualizeRed();

  /**
   * Visualizes the blue component (sets all the color values to the blue component)
   * of this Pixel and returns the updated version.
   *
   * @return the updated pixel that has all the color values set to the blue component
   */
  IPixel visualizeBlue();

  /**
   * Visualizes the green component (sets all the color values to the green component)
   * of this Pixel and returns the updated version.
   *
   * @return the updated pixel that has all the color values set to the green component
   */
  IPixel visualizeGreen();
}
