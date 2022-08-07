package model;

/**
 * An interface that represents the ImageModel and all the methods that it can implement.
 */
public interface ImageModel {
  /**
   * Brightens the image at the given key in this model by the provided value and returns the
   * updated version of it.
   *
   * @param imageName the key of the image in the map that represents the model
   * @param brightBy  the value by which the image is brightened
   * @return returns the brightened image
   * @throws IllegalArgumentException throws an exception if the provided image name is not a valid
   *                                  key
   */
  IImage brighten(String imageName, int brightBy) throws IllegalArgumentException;

  /**
   * Darkens the image at the given key in this model by the provided value and returns the
   * updated version of it.
   *
   * @param imageName the key of the image in the map that represents the model
   * @param darkBy    the value by which the image is darkened
   * @return returns the darkened image
   * @throws IllegalArgumentException throws an exception if the provided image name is not a valid
   *                                  key
   */
  IImage darken(String imageName, int darkBy) throws IllegalArgumentException;

  /**
   * Flips the image at the given key in this model horizontally and returns the
   * updated version of it.
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns the image flipped horizontally
   * @throws IllegalArgumentException throws an exception if the provided image name is not a valid
   *                                  key
   */
  IImage flipHorizontally(String imageName) throws IllegalArgumentException;

  /**
   * Flips the image at the given key in this model vertically and returns the
   * updated version of it.
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns the image flipped vertically
   * @throws IllegalArgumentException throws an exception if the provided image name is not a valid
   *                                  key
   */
  IImage flipVertically(String imageName) throws IllegalArgumentException;

  /**
   * Visualizes a provided color in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @param color     the color of the component that is being visualized in the image
   * @return returns an IImage that contains pixels with updated color values
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  IImage visualizeCompRGB(String imageName, String color) throws IllegalArgumentException;

  /**
   * Visualizes the value in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         maximum value)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  IImage visualizeValue(String imageName) throws IllegalArgumentException;

  /**
   * Visualizes the intensity in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         average value of the three components for each pixel)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  IImage visualizeIntensity(String imageName) throws IllegalArgumentException;

  /**
   * Visualizes the luma in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         value of the luma for each pixel)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  IImage visualizeLuma(String imageName) throws IllegalArgumentException;

  /**
   * Adds a new element (an image name and an image) to the map of the ImageModel.
   *
   * @param key the name of the added image
   * @param img the image that is being added
   * @throws IllegalArgumentException throws an exception if the provided image is null
   */
  void add(String key, IImage img) throws IllegalArgumentException;

  /**
   * Gets the image at the provided key in the map that represents the model.
   *
   * @param key the key of the image in the map of the model
   * @return the image at the provided key
   * @throws IllegalArgumentException throws an exception if the provided key is invalid
   */
  IImage getKey(String key) throws IllegalArgumentException;

  /**
   * Blurs an image by blurring each pixel in the array that represents an image.
   *
   * @param filename the key of the image in the map of the model
   * @return returns a new image that represents the blurring effect
   * @throws IllegalArgumentException throws an exception if the provided key is invalid
   */
  IImage blur(String filename) throws IllegalArgumentException;

  /**
   * Sharpens an image by sharpening each pixel in the array that represents an image.
   *
   * @param filename the key of the image in the map of the model
   * @return returns a new image that represents the sharpening effect
   * @throws IllegalArgumentException throws an exception if the provided key is invalid
   */
  IImage sharpen(String filename) throws IllegalArgumentException;

  /**
   * Transforms to greyscale an image by applying the color transformation to each pixel in the
   * array that represents an image.
   *
   * @param filename the key of the image in the map of the model
   * @return returns a new image that represents the greyscale color transformation
   * @throws IllegalArgumentException throws an exception if the provided key is invalid
   */
  IImage greyScale(String filename) throws IllegalArgumentException;

  /**
   * Transforms to sepia tone an image by applying the color transformation to each pixel in the
   * array that represents an image.
   *
   * @param filename the key of the image in the map of the model
   * @return returns a new image that represents the sepia-tone color transformation
   * @throws IllegalArgumentException throws an exception if the provided key is invalid
   */
  IImage sepiaTone(String filename) throws IllegalArgumentException;

  boolean isEmpty();

}
