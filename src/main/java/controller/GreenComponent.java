package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that visualizes the green component of an image in the model.
 */
public class GreenComponent implements ICommand {
  private String filename;

  /**
   * Constructs a GreenComponent using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public GreenComponent(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    String green = "Green";
    return m.visualizeCompRGB(filename, green);
  }
}

