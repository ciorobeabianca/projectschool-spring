package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import model.IImage;
import model.ImageModel;
import view.GUI;

/**
 * represents the controller for our GUI. It implements
 * ActionListener to create a GUI version of our model. It takes in
 * view and model and delegates according to what the user enters.
 */
public class GUIController implements ActionListener {

  private final GUI view;
  private final ImageModel model;

  /**
   * Creates a GUIController object with a model and a view. It also
   * loads the image and displays it.
   *
   * @param model IImage model object
   * @param view  GUI view object
   */
  public GUIController(ImageModel model, GUI view) {
    this.model = model;
    this.view = view;
    this.view.setListener(this);

    if (view.returnKey() == null) {
      File f = this.view.actionOnFileChooser("load image");
      if (f == null) {
        this.view.writeMessage("Was not able to load the image");
      } else {
        view.setKey(f.getAbsolutePath().substring(0, f.getAbsolutePath().length() - 4));
        LoadSave l = new Load(f.getAbsolutePath(), view.returnKey());
        try {
          l.runLoadSave(this.model);
          this.view.displayImage(this.model.getKey(view.returnKey()));
        } catch (IOException exc) {
          this.view.writeMessage("Was not able to load the image");
        }
      }
    }

  }

  @Override
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();
    IImage img;
    if (command.equals("Load")) {
      File f = this.view.actionOnFileChooser("load image");
      if (f == null) {
        this.view.writeMessage("Was not able to load the image");
      } else {
        LoadSave l = new Load(f.getAbsolutePath(), view.returnKey());
        try {
          l.runLoadSave(this.model);
          view.displayImage(this.model.getKey(view.returnKey()));
        } catch (IOException exc) {
          this.view.writeMessage("Was not able to load the image");
        }
      }
    } else {
      switch (command) {
        case "Blur":
          img = this.model.blur(view.returnKey());
          this.model.add(view.returnKey() + " blur", img);
          view.setKey(view.returnKey() + " blur");
          view.displayImage(img);
          break;
        case "Sharpen":
          img = this.model.sharpen(view.returnKey());
          this.model.add(view.returnKey() + " sharpen", img);
          view.setKey(view.returnKey() + " sharpen");
          view.displayImage(img);
          break;
        case "Sepia":
          img = this.model.sepiaTone(view.returnKey());
          this.model.add(view.returnKey() + " sepia", img);
          view.setKey(view.returnKey() + " sepia");
          view.displayImage(img);
          break;
        case "Greyscale":
          img = this.model.greyScale(view.returnKey());
          this.model.add(view.returnKey() + " grey", img);
          view.setKey(view.returnKey() + " grey");
          view.displayImage(img);
          break;
        case "Brighten":
          img = new Brighten(view.returnKey(), 20).runCommand(this.model);
          this.model.add(view.returnKey() + " bright", img);
          view.setKey(view.returnKey() + " bright");
          view.displayImage(img);
          break;
        case "Darken":
          img = new Brighten(view.returnKey(), -20).runCommand(this.model);
          this.model.add(view.returnKey() + " dark", img);
          view.setKey(view.returnKey() + " dark");
          view.displayImage(img);
          break;
        case "Horizontal Flip":
          img = new HorizontalFlip(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " horizontal", img);
          view.setKey(view.returnKey() + " horizontal");
          view.displayImage(img);
          break;
        case "Vertical Flip":
          img = new VerticalFlip(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " vertical", img);
          view.setKey(view.returnKey() + " vertical");
          view.displayImage(img);
          break;
        case "Red Component":
          img = new RedComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " red", img);
          view.setKey(view.returnKey() + " red");
          view.displayImage(img);
          break;
        case "Blue Component":
          img = new BlueComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " blue", img);
          view.setKey(view.returnKey() + " blue");
          view.displayImage(img);
          break;
        case "Green Component":
          img = new GreenComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " green", img);
          view.setKey(view.returnKey() + " green");
          view.displayImage(img);
          break;
        case "Value Component":
          img = new ValueComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " value", img);
          view.setKey(view.returnKey() + " value");
          view.displayImage(img);
          break;
        case "Luma Component":
          img = new LumaComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " luma", img);
          view.setKey(view.returnKey() + " luma");
          view.displayImage(img);
          break;
        case "Intensity Component":
          img = new IntensityComponent(view.returnKey()).runCommand(this.model);
          this.model.add(view.returnKey() + " intensity", img);
          view.setKey(view.returnKey() + " intensity");
          view.displayImage(img);
          break;
        case "Save":
          File f = this.view.actionOnFileChooser("save image");
          if (f == null) {
            this.view.writeMessage("Was not able to save the image");
          } else {
            LoadSave l = new Save(f.getAbsolutePath(), view.returnKey());
            try {
              l.runLoadSave(this.model);
            } catch (IOException exc) {
              this.view.writeMessage("Was not able to save the image");
            }
          }
          break;
        default:
          break;
      }
    }
  }

  public void start() {
    this.view.displayView();
  }


}
