package controller;

import java.io.IOException;

import model.IImage;
import model.Image;
import model.ImageModel;

/**
 * A class that represents the load command of an image from a file.
 */
public class Load implements LoadSave {
  private String file;
  private String key;

  /**
   * Constructs a load object using a file name and a key.
   *
   * @param file the file from where the image is loaded
   * @param key  the key tha the loaded image will have in the map of the model
   */
  public Load(String file, String key) {
    this.file = file;
    this.key = key;
  }

  @Override
  public void runLoadSave(ImageModel m) throws IOException {
    if (this.file.substring(this.file.length() - 3, this.file.length()).equals("ppm")) {
      IImage img = new Image(ImageUtil.loadPPM(file));
      m.add(key, img);
    } else {
      IImage img = ImageUtil.loadOtherType(file);
      m.add(key, img);
    }
  }
}
