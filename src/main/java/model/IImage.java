package model;

import java.awt.image.BufferedImage;
import java.util.List;

/**
 * An interface that represents an Image and the methods that operate on this type of object.
 */
public interface IImage {
  /**
   * Takes in a position and returns the pixel at the given row and column from the array that
   * represents this image.
   *
   * @param i the row index of the pixel in the array(image)
   * @param j the column index of the pixel in the array(image)
   * @return returns the pixel at the provided position from the array that represents the image
   * @throws IllegalArgumentException throws an exception if the given position is not valid
   */
  IPixel getPixelAt(int i, int j) throws IllegalArgumentException;

  /**
   * Gives the height of this picture (the number of rows in the array of pixels).
   *
   * @return the number of rows in the array of pixels
   */
  int getHeight();

  /**
   * Gives the width of this picture (the number of columns in the array of pixels).
   *
   * @return the number of columns in the array of pixels
   */
  int getWidth();

  /**
   * Gives the maximum color value of this IImage.
   *
   * @return the maximum color value of this IImage
   */
  int getMaxColVal();

  /**
   * Copies this IImage into a new variable of the same type and returns it.
   *
   * @return returns a copy of this IImage
   */
  IImage copyImage();

  /**
   * Blurs an image by blurring each pixel in the array that represents an image.
   *
   * @return returns a new image that represents the blurring effect
   */
  IImage blur();

  /**
   * Sharpens an image by sharpening each pixel in the array that represents an image.
   *
   * @return returns a new image that represents the sharpening effect
   */
  IImage sharpen();

  /**
   * Transforms to greyscale an image by applying the color transformation to each pixel in the
   * array that represents an image.
   *
   * @return returns a new image that represents the greyscale color transformation
   */
  IImage greyScale();

  /**
   * Transforms to sepia tone an image by applying the color transformation to each pixel in the
   * array that represents an image.
   *
   * @return returns a new image that represents the sepia-tone color transformation
   */
  IImage sepiaTone();

  /**
   * Creates a buffered image with the pixels of this image.
   *
   * @return a buffered image that represents the values of this image
   */
  BufferedImage createBufferImage();

  /**
   * Returns the list of red values of all the pixels in the image.
   *
   * @return list of red values of all the pixels in the image
   */
  List<Integer> getRedValues();

  /**
   * Returns the list of green values of all the pixels in the image.
   *
   * @return list of green values of all the pixels in the image
   */
  List<Integer> getGreenValues();

  /**
   * Returns the list of blue values of all the pixels in the image.
   *
   * @return list of blue values of all the pixels in the image
   */
  List<Integer> getBlueValues();

  /**
   * determines if the image is grey scaled.
   *
   * @return true if the image is grey scaled, false otherwise
   */
  boolean isGreyScale();

  /**
   * creates a list with the frequencies of the provided list of values.
   *
   * @param values list of values whose frequency needs to be calculated
   * @return List of integers representing the frequency of the provided values
   */
  List<Integer> frequency(List<Integer> values);

}
