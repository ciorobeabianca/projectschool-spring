package controller;

import java.io.IOException;
import java.util.Scanner;

import model.IImage;
import model.ImageModel;
import view.ImageView;

/**
 * A class that represents a controller for the commands that can be run on the ImageModel, which
 * are provided by the user through a Readable object.
 */
public class ImageController implements IImageController {
  private final Scanner in;
  private final Appendable out;
  private ImageModel model;
  private final ImageView view;

  /**
   * Constructs an ImageController using a Readable object, which specifies the commands,
   * an Appendable object, an ImageModel, which holds all the images and an ImageView.
   *
   * @param in    a Readable object from where we read the commands
   * @param out   an Appendable object that represents an output destination
   * @param model the ImageModel that holds all the images and their names in a map
   * @param view  the view of the ImageModel
   */
  public ImageController(Readable in, Appendable out, ImageModel model, ImageView view) {
    if (in == null || out == null || model == null || view == null) {
      throw new IllegalArgumentException("Null argument");
    } else {
      this.in = new Scanner(in);
      this.out = out;
      this.model = model;
      this.view = view;
    }
  }

  /**
   * Runs the commands provided by the user on the Image Model.
   */
  public void goCommands() {
    String fileName;
    String key;

    try {
      while (this.in.hasNext()) {
        String command = this.in.next();
        switch (command) {
          case "load":
            String file = this.in.next();
            fileName = this.in.next();
            LoadSave l = new Load(file, fileName);
            l.runLoadSave(this.model);
            break;
          case "save":
            fileName = this.in.next();
            key = this.in.next();
            LoadSave s = new Save(fileName, key);
            s.runLoadSave(this.model);
            break;
          case "brighten":
            int inc = this.in.nextInt();
            key = this.in.next();
            IImage img2;
            if (inc > 0) {
              img2 = new Brighten(key, inc).runCommand(this.model);
            } else {
              img2 = new Darken(key, Math.abs(inc)).runCommand(this.model);
            }
            fileName = this.in.next();
            this.model.add(fileName, img2);
            break;
          case "vertical-flip":
            key = this.in.next();
            IImage img3 = new VerticalFlip(key).runCommand(this.model);
            fileName = this.in.next();
            this.model.add(fileName, img3);
            break;

          case "horizontal-flip":
            key = this.in.next();
            IImage img4 = new HorizontalFlip(key).runCommand(this.model);
            fileName = this.in.next();
            this.model.add(fileName, img4);
            break;

          case "red-component":
            key = this.in.next();
            fileName = this.in.next();
            IImage img5 = new RedComponent(key).runCommand(this.model);
            this.model.add(fileName, img5);
            break;
          case "green-component":
            key = this.in.next();
            fileName = this.in.next();
            IImage img6 = new GreenComponent(key).runCommand(this.model);
            this.model.add(fileName, img6);
            break;

          case "blue-component":
            key = this.in.next();
            fileName = this.in.next();
            IImage img7 = new BlueComponent(key).runCommand(this.model);
            this.model.add(fileName, img7);
            break;

          case "value-component":
            key = this.in.next();
            fileName = this.in.next();
            IImage img8 = new ValueComponent(key).runCommand(this.model);
            this.model.add(fileName, img8);
            break;

          case "luma-component":
            key = this.in.next();
            fileName = this.in.next();
            IImage img9 = new LumaComponent(key).runCommand(this.model);
            this.model.add(fileName, img9);
            break;

          case "intensity-component":
            key = this.in.next();
            IImage img10 = new IntensityComponent(key).runCommand(this.model);
            fileName = this.in.next();
            this.model.add(fileName, img10);
            break;
          case "blur":
            key = this.in.next();
            IImage img11 = this.model.blur(key);
            fileName = this.in.next();
            this.model.add(fileName, img11);
            //System.out.println("blur");
            break;
          case "sharpen":
            key = this.in.next();
            IImage img12 = this.model.getKey(key).sharpen();
            fileName = this.in.next();
            this.model.add(fileName, img12);
            break;
          case "greyscale":
            key = this.in.next();
            IImage img13 = this.model.getKey(key).greyScale();
            fileName = this.in.next();
            this.model.add(fileName, img13);
            break;
          case "sepia-tone":
            key = this.in.next();
            IImage img14 = this.model.getKey(key).sepiaTone();
            fileName = this.in.next();
            this.model.add(fileName, img14);
            break;
          case "quit":
            return;
          default:
            throw new IllegalArgumentException("Invalid command");
        }
      }
      return;
    } catch (IOException e) {
      throw new IllegalStateException();
    }
  }
}
