package controller;

import java.io.IOException;

import model.ImageModel;

/**
 * An interface that represents the command design pattern for the load and save methods.
 */
public interface LoadSave {
  /**
   * Applies the load and save methods to a given model passed as an argument.
   *
   * @param m the model that is being processed
   * @throws IOException if the reading or writing of an image is not possible
   */
  void runLoadSave(ImageModel m) throws IOException;
}
