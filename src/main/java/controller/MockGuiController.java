package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import model.IImage;
import view.GUI;

/**
 * A mock of the GUIController that helps verify the functionality of the commands from the
 * action listeners.
 */
public class MockGuiController implements ActionListener {
  GUI view;
  final StringBuilder log;

  /**
   * Constructs a mock controller using a GUI view and a log that will keep track of the commands.
   *
   * @param view the GUI view
   * @param log  the log that keeps track of the commands
   */
  public MockGuiController(GUI view, StringBuilder log) {
    this.view = view;
    view.setListener(this);
    this.log = Objects.requireNonNull(log);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    IImage img;
    if (command.equals("Load")) {
      log.append("Load");
    } else {
      switch (command) {
        case "Blur":
          log.append("Blur");
          break;
        case "Sharpen":
          log.append("Sharpen");
          break;
        case "Sepia":
          log.append("Sepia");
          break;
        case "Greyscale":
          log.append("Greyscale");
          break;
        case "Brighten":
          log.append("Brighten");
          break;
        case "Darken":
          log.append("Darken");
          break;
        case "Horizontal Flip":
          log.append("Horizontal Flip");
          break;
        case "Vertical Flip":
          log.append("Vertical Flip");
          break;
        case "Red Component":
          log.append("Red Component");
          break;
        case "Blue Component":
          log.append("Blue Component");
          break;
        case "Green Component":
          log.append("Green Component");
          break;
        case "Value Component":
          log.append("Value Component");
          break;
        case "Luma Component":
          log.append("Luma Component");
          break;
        case "Intensity Component":
          log.append("Intensity Component");
          break;
        case "Save":
          log.append("Save");
          break;
        default:
          break;
      }
    }
  }
}
