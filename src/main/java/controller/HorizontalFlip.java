package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that flips horizontally an image in the image model.
 */
public class HorizontalFlip implements ICommand {
  private String filename;

  /**
   * Constructs a HorizontalFlip using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public HorizontalFlip(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.flipHorizontally(filename);
  }
}
