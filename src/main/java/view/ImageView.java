package view;

import java.io.IOException;

/**
 * Represents an interface that is the view of an Image object.
 */
public interface ImageView {
  /**
   * Renders the provided message to the appendable object of the view.
   *
   * @param mess the message that is being rendered
   * @throws IOException throws an exception if a message cannot be rendered to the Appendable
   */
  void renderMessage(String mess) throws IOException;
}
