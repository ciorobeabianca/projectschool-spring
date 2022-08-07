package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that visualizes the red component of an image in the model.
 */
public class RedComponent implements ICommand {
  private String filename;

  /**
   * Constructs a RedComponent using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public RedComponent(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    String red = "Red";
    return m.visualizeCompRGB(filename, red);
  }
}
