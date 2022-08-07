package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that visualizes the blue component of an image in the model.
 */
public class BlueComponent implements ICommand {
  private String filename;

  /**
   * Constructs a BlueComponent using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public BlueComponent(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    String blue = "Blue";
    return m.visualizeCompRGB(filename, blue);
  }
}
