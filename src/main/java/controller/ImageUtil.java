package controller;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileInputStream;

import javax.imageio.ImageIO;

import model.IImage;
import model.Image;
import model.IPixel;
import model.ImageModel;
import model.ImageModelImpl;
import model.Pixel;
import view.GUI;
import view.ImageView;
import view.ImageViewImpl;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 * Feel free to change this method as required.
 */
public class ImageUtil {

  /**
   * Read an image file in the PPM format and print the colors.
   *
   * @param filename the path of the file.
   */
  public static void readPPM(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      System.out.println("File " + filename + " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    System.out.println("Width of image: " + width);
    int height = sc.nextInt();
    System.out.println("Height of image: " + height);
    int maxValue = sc.nextInt();
    System.out.println("Maximum value of a color in this file (usually 255): " + maxValue);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        System.out.println("Color of pixel (" + j + "," + i + "): " + r + "," + g + "," + b);
      }
    }
  }

  /**
   * Loads an image from a provided file and returns it.
   *
   * @param filename the name of the file from where the image is being loaded
   * @return returns the image loaded from the file
   * @throws IOException if the reading of the image is not possible
   */
  public static IImage loadOtherType(String filename) throws IOException {
    BufferedImage img = ImageIO.read(new File(filename));

    int height = img.getHeight();
    int width = img.getWidth();

    IPixel[][] pixels = new IPixel[height][width];

    for (int j = 0; j < height; j++) {
      for (int i = 0; i < width; i++) {
        Color col = new Color(img.getRGB(i, j));
        IPixel pix = new Pixel(col.getRed(), col.getGreen(), col.getBlue());
        pixels[i][j] = pix;
      }
    }

    IImage i = new Image(pixels);
    return i;
  }

  /**
   * Loads an image(an array of pixels) from a provided file and returns it.
   *
   * @param filename the name of the file from where the image is being loaded
   * @return returns an array of pixels which represents the image loaded from the file
   * @throws IllegalArgumentException throws an exception if the file is not found or if it is not
   *                                  a valid ppm file
   */
  public static IPixel[][] loadPPM(String filename) throws IllegalArgumentException {

    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file");
    }

    StringBuilder builder = new StringBuilder();

    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid ppm file");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();
    IPixel[][] pixels = new Pixel[height][width];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        pixels[i][j] = new Pixel(r, g, b);
      }
    }
    return pixels;
  }

  /**
   * Saves a provided image in the file that is passed in as an argument.
   *
   * @param fileName the name of the file where the given image is going to be stored
   * @param img      the image that is going to be saved in the given file
   * @throws IllegalArgumentException if the writing of the image is not possible
   */
  public static void saveOthers(String fileName, IImage img) throws IllegalArgumentException {
    BufferedImage bi;
    File outputfile;

    if (img == null) {
      throw new IllegalArgumentException("Invalid image");
    }
    int w = img.getWidth();
    int h = img.getHeight();
    String type = fileName.substring(fileName.length() - 3, fileName.length());

    try {
      switch (type) {
        case "ppm":
          savePPM(fileName, img);
          break;
        case "png":
          bi = img.createBufferImage();
          outputfile = new File(fileName);
          ImageIO.write(bi, "png", outputfile);
          break;
        case "jpg":
          //bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
          bi = img.createBufferImage();
          outputfile = new File(fileName);
          ImageIO.write(bi, "jpg", outputfile);
          break;
        default:
          bi = img.createBufferImage();
          outputfile = new File(fileName);
          ImageIO.write(bi, "bmp", outputfile);
          break;
      }
    } catch (IOException exc) {
      throw new IllegalThreadStateException();
    }
  }


  /**
   * Saves a provided image in the file that is passed in as an argument.
   *
   * @param fileName the name of the file where the given image is going to be stored
   * @param img      the image that is going to be saved in the given file
   * @throws IllegalArgumentException throws an exception if the file is not valid or if the given
   *                                  image is null
   */
  public static void savePPM(String fileName, IImage img) throws IllegalArgumentException {
    if (img == null) {
      throw new IllegalArgumentException("Invalid image");
    }
    StringBuilder builder = new StringBuilder();
    builder.append("P3 + \n");
    builder.append(img.getWidth() + " " + img.getHeight() + '\n');
    builder.append(img.getMaxColVal() + '\n');
    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        float red = img.getPixelAt(i, j).getRed();
        float blue = img.getPixelAt(i, j).getBlue();
        float green = img.getPixelAt(i, j).getGreen();
        builder.append(red + " " + green + " " + blue + " ");
      }
      builder.append('\n');
    }
    try {
      FileOutputStream fileOut = new FileOutputStream(fileName);
      byte[] b = (builder.toString()).getBytes();
      try {
        fileOut.write(b);
        fileOut.close();
      } catch (IOException e) {
        throw new IllegalArgumentException();
      }
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("Invalid file");
    }
  }

  //demo main

  /**
   * Calls the constructor on a default model for an image.
   */

  public static void main2(String[] args) {
    try {
      Readable rd = new FileReader("res/ScriptCommands");
      Appendable ap = System.out;
      ImageModel model = new ImageModelImpl();
      ImageView view = new ImageViewImpl(ap);
      ImageController controller = new ImageController(rd, ap, model, view);
      controller.goCommands();
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
  }


  /**
   * Runs the program using the provided arguments.
   * @param args the arguments that determine the manner in which the program will be run
   */
  public static void main(String[] args) {
    if (args.length == 0) {
      ImageModel m = new ImageModelImpl();
      GUI view = new GUI();
      ActionListener controller = new GUIController(m, view);
      ((GUIController) controller).start();
    } else if (args[0].equalsIgnoreCase("-file")) {
      if (args.length < 2) {
        throw new IllegalArgumentException("Please enter txt file name after '-file' "
                + "for file-based scripting");
      }
      try {
        Readable rd = new FileReader(args[1]);
        Appendable ap = System.out;
        ImageModel model = new ImageModelImpl();
        ImageView view = new ImageViewImpl(ap);
        ImageController controller = new ImageController(rd, ap, model, view);
        controller.goCommands();
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      }
    } else if (args[0].equalsIgnoreCase("-text")) {
      Readable rd = new InputStreamReader(System.in);
      Appendable ap = System.out;
      ImageModel model = new ImageModelImpl();
      ImageView view = new ImageViewImpl(ap);
      ImageController controller = new ImageController(rd, ap, model, view);
      controller.goCommands();
    } else {
      throw new IllegalArgumentException("not accepted command");

    }
  }

}