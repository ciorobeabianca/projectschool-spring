package view;

import java.io.IOException;

/**
 * A class that represents the view for an Image and implements the ImageView interface.
 */
public class ImageViewImpl implements ImageView {
  public Appendable out;

  /**
   * Constructs an ImageView using an Appendable object to which the information passed to the view
   * is being rendered.
   *
   * @param out the Appendable object that will store the information passed by the model
   * @throws IllegalArgumentException throws an exception if the Appendable is null
   */
  public ImageViewImpl(Appendable out) throws IllegalArgumentException {
    if (out == null) {
      throw new IllegalArgumentException("Null model or Appendable");
    }
    this.out = out;
  }

  @Override
  public void renderMessage(String mess) throws IOException {
    this.out.append(mess);
  }
}
