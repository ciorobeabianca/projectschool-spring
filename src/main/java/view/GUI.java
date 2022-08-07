package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.IImage;

/**
 * This class represents our GUI view. This class creates a view that
 * displays menu items, the images and histograms. This also displays errors
 * messages. The view does not take in anything following the MVC design.
 */
public class GUI extends JFrame {
  private JPanel centralPanel;
  private JLabel display;
  private JPanel messBox;
  private JScrollPane scroll;
  private JMenuItem load;
  private JMenuItem save;
  private JMenuItem blur;
  private JMenuItem sharpen;
  private JMenuItem greyscale;
  private JMenuItem sepia;
  private JMenuItem greenComponent;
  private JMenuItem blueComponent;
  private JMenuItem redComponent;
  private JMenuItem lumaComponent;
  private JMenuItem intensityComponent;
  private JMenuItem valueComponent;
  private JMenuItem horizontalFlip;
  private JMenuItem verticalFlip;
  private JMenuItem brighten;
  private JMenuItem darken;
  private HistogramCol hist1;
  private HistogramCol hist2;
  private HistogramCol hist3;
  private String key;

  /**
   * Creates a GUI object, that represents the image manipulation process, by allowing the user to
   * load, save and modify the image with a given menu.
   */
  public GUI() {
    super();
    JScrollPane mainImageScrollPane;
    JPanel mainPanel;
    JPanel histPanel;
    JPanel commandPanel;
    JMenuBar menuBar;
    JMenu a;
    JMenu submenu;

    setTitle("Picture Editor");
    setSize(1280, 720);

    mainPanel = new JPanel();

    centralPanel = new JPanel();
    //for elements to be arranged vertically within this panel
    centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.PAGE_AXIS));
    //scroll bars around this main panel
    mainImageScrollPane = new JScrollPane(centralPanel);

    commandPanel = new JPanel();

    JMenu fileSubmenu = new JMenu("File");
    JMenuItem undo = new JMenuItem("Undo");
    fileSubmenu.add(undo);

    menuBar = new JMenuBar();
    a = new JMenu("Menu");
    submenu = new JMenu("Processes");
    load = new JMenuItem("Load");
    save = new JMenuItem("Save");

    blur = new JMenuItem("Blur");
    sharpen = new JMenuItem("Sharpen");

    greyscale = new JMenuItem("Greyscale");
    sepia = new JMenuItem("Sepia");

    greenComponent = new JMenuItem("Green Component");
    blueComponent = new JMenuItem("Blue Component");

    redComponent = new JMenuItem("Red Component");
    lumaComponent = new JMenuItem("Luma Component");

    intensityComponent = new JMenuItem("Intensity Component");
    valueComponent = new JMenuItem("Value Component");

    horizontalFlip = new JMenuItem("Horizontal Flip");
    verticalFlip = new JMenuItem("Vertical Flip");

    brighten = new JMenuItem("Brighten");
    darken = new JMenuItem("Darken");

    // sub = new JMenuItem[commands.length];
    menuBar.add(fileSubmenu);
    this.setJMenuBar(menuBar);

    commandPanel.setBorder(BorderFactory.createTitledBorder("Image Commands"));
    commandPanel.setLayout(new BoxLayout(commandPanel, BoxLayout.PAGE_AXIS));


    menuBar.setPreferredSize(new Dimension(getWidth(), 20));

    a.setMnemonic('I');
    menuBar.add(a);

    load.setActionCommand("Load");
    save.setActionCommand("Save");


    a.add(load);
    a.add(save);
    a.addSeparator();

    blur.setActionCommand("Blur");
    submenu.add(blur);

    sharpen.setActionCommand("Sharpen");
    submenu.add(sharpen);


    greyscale.setActionCommand("Greyscale");
    submenu.add(greyscale);

    sepia.setActionCommand("Sepia");
    submenu.add(sepia);

    greenComponent.setActionCommand("Green Component");
    submenu.add(greenComponent);

    blueComponent.setActionCommand("Blue Component");
    submenu.add(blueComponent);

    redComponent.setActionCommand("Red Component");
    submenu.add(redComponent);

    lumaComponent.setActionCommand("Luma Component");
    submenu.add(lumaComponent);

    intensityComponent.setActionCommand("Intensity Component");
    submenu.add(intensityComponent);

    valueComponent.setActionCommand("Value Component");
    submenu.add(valueComponent);

    horizontalFlip.setActionCommand("Horizontal Flip");
    submenu.add(horizontalFlip);

    verticalFlip.setActionCommand("Vertical Flip");
    submenu.add(verticalFlip);

    brighten.setActionCommand("Brighten");
    submenu.add(brighten);

    darken.setActionCommand("Darken");
    submenu.add(darken);

    a.add(submenu);


    display = new JLabel();
    display.setMinimumSize(new Dimension(10, 10));
    scroll = new JScrollPane(display);
    scroll.setPreferredSize(new Dimension(600, 600));

    histPanel = new JPanel();
    hist1 = new HistogramCol();
    hist2 = new HistogramCol();
    hist3 = new HistogramCol();
    histPanel.setLayout(new GridLayout(3, 1));
    histPanel.add(hist1);
    histPanel.add(hist2);
    histPanel.add(hist3);

    centralPanel.add(scroll);

    this.setLayout(new GridLayout(1, 2));
    this.add(centralPanel);
    this.add(histPanel);
    this.pack();

  }

  /**
   * displays the image provided as an argument alongside histograms that represent the frequencies
   * of the color values.
   *
   * @param image the image that is being displayed.
   */
  public void displayImage(IImage image) {
    ImageIcon icon;
    hist1.setRedValues(image, 10, 10);
    hist2.setGreenValues(image, 10, 10);
    hist3.setBlueValues(image, 10, 10);
    scroll.setPreferredSize(new Dimension(image.getWidth(), image.getHeight()));
    icon = new ImageIcon(image.createBufferImage());
    display.setIcon(icon);
    this.repaint();
  }


  /**
   * returns the key of the image.
   */
  public String returnKey() {
    if (this.key == null) {
      return null;
    }
    return this.key;
  }

  /**
   * returns the file choosen by the user, depending on the chosen command(save or load).
   *
   * @param type the command desired by the user (save or load)
   * @return returns a file depending on the user's choice
   */
  public File actionOnFileChooser(String type) {
    JFileChooser c = new JFileChooser(".");
    FileNameExtensionFilter f;
    int val;

    switch (type) {
      case "load image":
        f = new FileNameExtensionFilter("load image", "jpg", "png", "bmp", "ppm");
        c.setFileFilter(f);
        c.setFileHidingEnabled(false);
        val = c.showOpenDialog(GUI.this);
        if (val == JFileChooser.APPROVE_OPTION) {
          return c.getSelectedFile();
        } else {
          return null;
        }
      case "save image":
        c.setFileFilter(new Folder());
        val = c.showSaveDialog(GUI.this);
        if (val == JFileChooser.APPROVE_OPTION) {
          return c.getSelectedFile();
        } else {
          return null;
        }
      default:
        return null;
    }
  }

  private static class Folder extends javax.swing.filechooser.FileFilter {

    @Override
    public boolean accept(File f) {
      return f.isDirectory();
    }

    @Override
    public String getDescription() {
      return "You can only save the image in directories";
    }

  }

  /**
   * writes the error message to the user.
   *
   * @param m the error message
   */
  public void writeMessage(String m) {
    JLabel text = new JLabel();
    this.createMessageBox();
    this.messBox.invalidate();
    this.messBox.removeAll();
    text.setText(m);
    this.messBox.add(text);
    this.validate();
    this.repaint();
  }

  /**
   * creates a message box.
   */
  public void createMessageBox() {
    messBox = new JPanel();
    TitledBorder textBorder = BorderFactory.createTitledBorder(new LineBorder(Color.RED),
            "Message");
    textBorder.setTitleJustification(TitledBorder.CENTER);
    messBox.setBorder(textBorder);
    messBox.setPreferredSize(new Dimension(870, 80));
    centralPanel.add(messBox);
  }


  /**
   * makes the implemented GUI visible.
   */
  public void displayView() {
    this.setVisible(true);
  }

  /**
   * sets the key of the view to a provided one.
   *
   * @param k the given key
   */
  public void setKey(String k) {
    this.key = k;
  }


  /**
   * adds action Listeners to the JMenu items.
   *
   * @param listener the listener that takes in the action events
   */
  public void setListener(ActionListener listener) {
    lumaComponent.addActionListener(listener);
    load.addActionListener(listener);
    save.addActionListener(listener);
    darken.addActionListener(listener);
    blur.addActionListener(listener);
    brighten.addActionListener(listener);
    intensityComponent.addActionListener(listener);
    horizontalFlip.addActionListener(listener);
    valueComponent.addActionListener(listener);
    sharpen.addActionListener(listener);
    greenComponent.addActionListener(listener);
    verticalFlip.addActionListener(listener);
    greyscale.addActionListener(listener);
    sepia.addActionListener(listener);
    blueComponent.addActionListener(listener);
    redComponent.addActionListener(listener);
  }

}
