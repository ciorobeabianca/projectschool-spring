package controller;

import model.IImage;
import model.ImageModel;

/**
 * An interface which represents a command that can be processed on an ImageModel.
 */
public interface ICommand {
  /**
   * Calls this command on the model which is provided as an argument.
   *
   * @param m the ImageModel that the command is called on
   * @return the updated IImage produced by the command
   */
  IImage runCommand(ImageModel m);
}
