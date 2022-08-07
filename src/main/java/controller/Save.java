package controller;

import model.ImageModel;

/**
 * A class that represents the save command of an image to a file.
 */
public class Save implements LoadSave {
  private String file;
  private String key;

  /**
   * Constructs a save object using a file name and a key.
   *
   * @param file the file where the image is saved
   * @param key  the key tha the image that is being saved in the map of the model
   */
  public Save(String file, String key) {
    this.file = file;
    this.key = key;
  }

  @Override
  public void runLoadSave(ImageModel m) throws IllegalArgumentException {
    ImageUtil.saveOthers(file, m.getKey(key));
  }
}
