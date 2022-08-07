package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that brightens by a given value an image in the
 * image model.
 */
public class Brighten implements ICommand {
  private String filename;
  private int brightBy;

  /**
   * Constructs a Brighten command using a file name and a value by which the image is
   * brightened, which are passed as arguments.
   *
   * @param filename the file name which represents a key from the map of the image model
   * @param brightBy the value by which the image is brightened
   */
  public Brighten(String filename, int brightBy) {
    this.filename = filename;
    this.brightBy = brightBy;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.brighten(filename, brightBy);
  }
}
