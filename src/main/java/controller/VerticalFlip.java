package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that flips vertically an image in the image model.
 */
public class VerticalFlip implements ICommand {
  private String filename;

  /**
   * Constructs a VerticalFlip using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public VerticalFlip(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.flipVertically(filename);
  }
}
