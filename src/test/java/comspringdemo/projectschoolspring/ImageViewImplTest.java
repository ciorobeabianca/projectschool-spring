package comspringdemo.projectschoolspring;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import view.ImageView;
import view.ImageViewImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * A class for the methods and fields of the ImageViewImpl.
 */
public class ImageViewImplTest {
  public Appendable ap;
  public ImageView tw;

  @Before
  public void setUp() {
    ap = new StringBuilder();
    tw = new ImageViewImpl(ap);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testNullAppendable() {
    new ImageViewImpl(null);
  }


  @Test
  public void testGetInput() throws IOException {
    String finalOut = "Hello \n";
    tw.renderMessage("Hello ");
    tw.renderMessage("\n");
    Assert.assertEquals(finalOut, ap.toString());
  }

  /**
   * A class that throws an IOException for each method of the Appendable interface, which it
   * implements.
   */
  public class AppendableException implements Appendable {

    @Override
    public Appendable append(CharSequence csq) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(CharSequence csq, int start, int end) throws IOException {
      throw new IOException();
    }

    @Override
    public Appendable append(char c) throws IOException {
      throw new IOException();
    }
  }

  // tests the exception thrown by renderMessage
  @Test(expected = IOException.class)
  public void testRenderMessExc() throws IOException {
    Appendable out = new AppendableException();
    ImageView v = new ImageViewImpl(out);
    try {
      v.renderMessage("Hello!");
      Assert.fail();
    } catch (IOException e) {
      throw new IOException();
    }
  }

}