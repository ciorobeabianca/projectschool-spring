package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that visualizes the luma of an image in the
 * image model.
 */
public class LumaComponent implements ICommand {
  private String filename;

  /**
   * Constructs a LumaComponent using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public LumaComponent(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.visualizeLuma(filename);
  }
}
