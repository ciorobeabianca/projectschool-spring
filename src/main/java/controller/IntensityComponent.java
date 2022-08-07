package controller;

import model.IImage;
import model.ImageModel;

/**
 * A class that represents the command that visualizes the intensity of an image in the
 * image model.
 */
public class IntensityComponent implements ICommand {
  private String filename;

  /**
   * Constructs an IntensityComponent using a file name which is passed as an argument.
   *
   * @param filename the file name which represents a key from the map of the image model
   */
  public IntensityComponent(String filename) {
    this.filename = filename;
  }

  @Override
  public IImage runCommand(ImageModel m) {
    return m.visualizeIntensity(filename);

  }
}
