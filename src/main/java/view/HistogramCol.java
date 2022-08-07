package view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import model.IImage;

/**
 * represents our Histograms. This class is responsible for
 * drawing histograms of the RGB components of a image.
 */

public class HistogramCol extends JPanel {

  private static final int w = 800;
  private static final int h = 650;
  private static final int gap = 20;
  private static final Stroke st = new BasicStroke(1f);
  private static final int point_w = 2;
  private List<Integer> list;
  private boolean isGrey;
  private Color col;
  private int max;
  private int y_hatch;
  private int x_hatch;

  /**
   * default constructor which creates a Histogram object.
   */

  public HistogramCol() {
    this.list = new ArrayList<Integer>();
    this.isGrey = false;
    this.col = Color.gray;
    this.max = 0;
    this.y_hatch = 10;
    this.x_hatch = 10;
  }

  /**
   * Sets the values of the histogram using the frequencies of the red values of the provided image
   * and the provided x and y hatch.
   *
   * @param img    the image that the histogram is representing
   * @param yHatch the amount of sections on the y-axis
   * @param xHatch the amount of sections on the x-axis
   */
  public void setRedValues(IImage img, int yHatch, int xHatch) {
    this.max = img.getRedValues().size();
    this.list = img.frequency(img.getRedValues());
    this.isGrey = img.isGreyScale();
    this.col = Color.red;
    this.y_hatch = yHatch;
    this.x_hatch = xHatch;
  }

  /**
   * Sets the values of the histogram using the frequencies of the green values of the provided
   * image and the provided x and y hatch.
   *
   * @param img    the image that the histogram is representing
   * @param yHatch the amount of sections on the y-axis
   * @param xHatch the amount of sections on the x-axis
   */
  public void setGreenValues(IImage img, int yHatch, int xHatch) {
    this.max = img.getGreenValues().size();
    this.list = img.frequency(img.getGreenValues());
    this.isGrey = img.isGreyScale();
    this.col = Color.green;
    this.y_hatch = yHatch;
    this.x_hatch = xHatch;
  }

  /**
   * Sets the values of the histogram using the frequencies of the blue values of the provided image
   * and the provided x and y hatch.
   *
   * @param img    the image that the histogram is representing
   * @param yHatch the amount of sections on the y-axis
   * @param xHatch the amount of sections on the x-axis
   */
  public void setBlueValues(IImage img, int yHatch, int xHatch) {
    this.max = img.getBlueValues().size();
    this.list = img.frequency(img.getBlueValues());
    this.isGrey = img.isGreyScale();
    this.col = Color.blue;
    this.y_hatch = yHatch;
    this.x_hatch = xHatch;
  }

  @Override
  protected void paintComponent(Graphics g) {

    super.paintComponent(g);
    Graphics2D hist = (Graphics2D) g;
    hist.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    hist.drawLine(gap, getHeight() - gap, gap, gap);
    hist.drawLine(gap, getHeight() - gap, getWidth() - gap, getHeight() - gap);


    for (int i = 0; i < y_hatch; i++) {
      int x0 = gap;
      int x1 = point_w + gap;
      int y0 = getHeight() - (((i + 1) * (getHeight() - gap * 2)) / y_hatch + gap);
      int y1 = y0;
      hist.drawLine(x0, y0, x1, y1);
    }

    for (int i = 0; i < list.size() - 1; i++) {
      //int x0 = (i + 1) * (getWidth() - gap * 2) / (list.size() - 1) + gap;
      int x0 = (i + 1) * (getWidth() - gap * 2) / x_hatch + gap;
      int x1 = x0;
      int y0 = getHeight() - gap;
      int y1 = y0 - point_w;
      hist.drawLine(x0, y0, x1, y1);
    }


    if (!isGrey) {
      helper(list, col, hist);
    } else {
      helper(list, Color.gray, hist);
    }

  }

  private void helper(List<Integer> color, Color col, Graphics2D hist) {
    double xScale = ((double) getWidth() - 2 * gap) / (color.size() - 1);
    double yScale = ((double) getHeight() - 2 * gap) / max;
    List<Point> points = new ArrayList<Point>();
    for (int i = 0; i < color.size(); i++) {
      //int x1 = (int) (i * xScale + gap);
      int x1 = (int) (i * xScale + gap);
      int y1 = (int) ((max - color.get(i)) * yScale + gap);
      //int y1 = (int) ((max - color.get(i)) * yScale + gap);
      points.add(new Point(x1, y1));
    }

    Stroke st = hist.getStroke();
    hist.setColor(col);
    hist.setStroke(st);
    for (int i = 0; i < points.size() - 1; i++) {
      int x1 = points.get(i).x;
      int y1 = points.get(i).y;
      int x2 = points.get(i + 1).x;
      int y2 = points.get(i + 1).y;
      hist.drawLine(x1, y1, x2, y2);
    }

    hist.setStroke(st);
    hist.setColor(col);
    for (int i = 0; i < points.size(); i++) {
      int x = points.get(i).x - point_w / 2;
      int y = points.get(i).y - point_w / 2;
      int pW = point_w;
      int pH = point_w;
      hist.fillOval(x, y, pW, pH);
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(w, h);
  }

}