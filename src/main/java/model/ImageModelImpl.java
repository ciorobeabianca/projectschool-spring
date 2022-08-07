package model;


import java.util.HashMap;
import java.util.Map;


/**
 * A class that represents the implementation of an ImageModel object (a hashmap of images) and that
 * inherits and overrides the methods of the interface which represent the commands that can be
 * processed on an object of this type (the model).
 */
public class ImageModelImpl implements ImageModel {
  private final Map<String, IImage> images;


  /**
   * Constructs an ImageModel using a map of images provided as an argument.
   *
   * @param images represents a hashmap that holds all the images in a model and their names
   * @throws IllegalArgumentException throws an exception if the hashmap of images is null
   */
  public ImageModelImpl(Map<String, IImage> images) throws IllegalArgumentException {
    if (images == null) {
      throw new IllegalArgumentException();
    }
    this.images = images;
  }

  /**
   * Constructs a default ImageModel using an empty hashmap for the images.
   */
  public ImageModelImpl() {
    this.images = new HashMap<>();
  }

  @Override
  public IImage brighten(String imageName, int brightBy) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }

    IImage image = images.get(imageName);
    IImage copyImg = image.copyImage();
    for (int i = 0; i < copyImg.getHeight(); i++) {
      for (int j = 0; j < copyImg.getWidth(); j++) {
        copyImg.getPixelAt(i, j).bright(brightBy);
      }
    }
    return copyImg;
  }

  @Override
  public IImage darken(String imageName, int darkBy) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    return this.brighten(imageName, -darkBy);
  }

  @Override
  public IImage flipHorizontally(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IPixel[][] flipPixels = new IPixel[image.getHeight()][image.getWidth()];
    int c;

    for (int i = 0; i < image.getHeight(); i++) {
      c = 0;
      for (int j = image.getWidth() - 1; j >= 0; j--) {
        flipPixels[i][c] = image.getPixelAt(i, j);
        c++;
      }
    }
    IImage newImg = new Image(flipPixels);
    return newImg;
  }

  @Override
  public IImage flipVertically(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IPixel[][] flipPixels = new IPixel[image.getHeight()][image.getWidth()];
    int c = 0;

    for (int i = image.getHeight() - 1; i >= 0; i--) {
      for (int j = 0; j < image.getWidth(); j++) {
        flipPixels[c][j] = image.getPixelAt(i, j);
      }
      c++;
    }
    IImage newImg = new Image(flipPixels);
    return newImg;
  }

  /**
   * Visualizes a provided color in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @param color     the color of the component that is being visualized in the image
   * @return returns an IImage that contains pixels with updated color values
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  public IImage visualizeCompRGB(String imageName, String color) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }

    IImage image = images.get(imageName);
    IPixel[][] copyImg = new IPixel[image.getHeight()][image.getWidth()];
    switch (color) {
      case "Red":
        for (int i = 0; i < image.getHeight(); i++) {
          for (int j = 0; j < image.getWidth(); j++) {
            copyImg[i][j] = image.getPixelAt(i, j).visualizeRed();
          }
        }
        return new Image(copyImg);
      case "Blue":
        for (int i = 0; i < image.getHeight(); i++) {
          for (int j = 0; j < image.getWidth(); j++) {
            copyImg[i][j] = image.getPixelAt(i, j).visualizeBlue();
          }
        }
        return new Image(copyImg);

      case "Green":
        for (int i = 0; i < image.getHeight(); i++) {
          for (int j = 0; j < image.getWidth(); j++) {
            copyImg[i][j] = image.getPixelAt(i, j).visualizeGreen();
          }
        }
        return new Image(copyImg);

      default:
        throw new IllegalArgumentException("Invalid color");
    }
  }

  /**
   * Visualizes the value in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         maximum value)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  public IImage visualizeValue(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }

    IImage image = images.get(imageName);
    IPixel[][] copyImg = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        copyImg[i][j] = image.getPixelAt(i, j).visualizeVal();
      }
    }
    return new Image(copyImg);
  }

  /**
   * Visualizes the intensity in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         average value of the three components for each pixel)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  public IImage visualizeIntensity(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }

    IImage image = images.get(imageName);
    IPixel[][] copyImg = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        copyImg[i][j] = image.getPixelAt(i, j).visualizeInt();
      }
    }
    return new Image(copyImg);
  }

  /**
   * Visualizes the luma in the image with the given key (passed in as an argument).
   *
   * @param imageName the key of the image in the map that represents the model
   * @return returns an IImage that contains pixels with updated color values (all set to the
   *         value of the luma for each pixel)
   * @throws IllegalArgumentException throws an exception if the provided color or the image name
   *                                  are not valid
   */
  public IImage visualizeLuma(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }

    IImage image = images.get(imageName);
    IPixel[][] copyImg = new IPixel[image.getHeight()][image.getWidth()];
    for (int i = 0; i < image.getHeight(); i++) {
      for (int j = 0; j < image.getWidth(); j++) {
        copyImg[i][j] = image.getPixelAt(i, j).visualizeL();
      }
    }
    return new Image(copyImg);
  }

  @Override
  public void add(String key, IImage img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException("Invalid image");
    }
    this.images.put(key, img);
  }

  @Override
  public IImage getKey(String key) throws IllegalArgumentException {
    if (!this.images.containsKey(key)) {
      throw new IllegalArgumentException("Invalid key");
    }
    return this.images.get(key);
  }

  @Override
  public IImage blur(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IImage blurImg = image.blur();
    return blurImg;
  }

  @Override
  public IImage sharpen(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IImage sharpenImg = image.sharpen();
    return sharpenImg;
  }

  @Override
  public IImage greyScale(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IImage sharpenImg = image.greyScale();
    return sharpenImg;
  }

  @Override
  public IImage sepiaTone(String imageName) throws IllegalArgumentException {
    if (!this.images.containsKey(imageName)) {
      throw new IllegalArgumentException("Invalid key");
    }
    IImage image = images.get(imageName);
    IImage sharpenImg = image.sepiaTone();
    return sharpenImg;
  }

  @Override
  public boolean isEmpty() {
    return this.images.isEmpty();
  }

}
