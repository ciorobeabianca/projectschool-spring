package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that darkens by a given value an image in the
 * image model.
 */
public class Darken implements ICommand {
  private String filename;
  private int darkBy;

  /**
   * Constructs a Darken command using a file name and a value by which the image is
   * darkened, which are passed as arguments.
   *
   * @param filename the file name which represents a key from the map of the image model
   * @param darkBy   the value by which the image is darkened
   */
  public Darken(String filename, int darkBy) {
    this.filename = filename;
    this.darkBy = darkBy;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.darken(filename, darkBy);
  }
}
