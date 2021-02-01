package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ProteinGraphic {

  static final int height = 1200;
  static final int width = 1200;
  static final int cellSize = 45;
  int currentPointX = 600;
  int currentPointY = 600;
  int lastPointX = 0;
  int lastPointY = 0;
  BufferedImage image;
  Graphics2D g2;
  int picNumber;

  public ProteinGraphic(int pictureNumber) {
    this.picNumber = pictureNumber;
    this.image = new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    this.g2 = image.createGraphics();
    this.g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
    this.g2.setColor(Color.YELLOW);
    this.g2.fillRect(0, 0, width, height);
  }

  public void saveToFile() {
    String folder = "ProteinFolding/src/foldingImages/";
    String filename = "bild" + this.picNumber + ".png";
    if (new File(folder).exists() == false) {
      new File(folder).mkdirs();
    }

    try {
      ImageIO.write(this.image, "png", new File(folder + File.separator + filename));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void drawFirstAcid(int type){
    String label = "0";
    Font font = new Font("Serif", Font.PLAIN, 25);
    if (type == 0) {
      this.g2.setColor(new Color(255, 0, 0));
      this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
      this.g2.setColor(new Color(0, 0, 0));
      g2.setFont(font);
      FontMetrics metrics = g2.getFontMetrics();
      int ascent = metrics.getAscent();
      int labelWidth = metrics.stringWidth(label);
      g2.drawString(label, currentPointX + cellSize/4  , currentPointY + cellSize/2  );
    } else if (type == 1) {
      this.g2.setColor(new Color(0, 0, 255));
      this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
      this.g2.setColor(new Color(255, 255, 255));
      g2.setFont(font);
      FontMetrics metrics = g2.getFontMetrics();
      int ascent = metrics.getAscent();
      int labelWidth = metrics.stringWidth(label);
      g2.drawString(label, currentPointX +cellSize/4 , currentPointY + cellSize / 2  );
    }
  }

  public void draw(String direction, int type, String paramLabel,boolean overlap) {
    // type 0 = hydrophob (good)
    // type 1 = hydrophil (bad)

    String label = paramLabel;
    Font font = new Font("Serif", Font.PLAIN, 25);
    int distance = 80;

      switch (direction) {
        case "ost":
          this.lastPointX = currentPointX;
          this.lastPointY = currentPointY;
          this.currentPointX +=  distance;
          g2.setColor(Color.BLACK);
          g2.drawLine(lastPointX +22 ,lastPointY + 22,currentPointX +22 ,currentPointY +22);
          if (type == 0 ) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(255, 0, 0));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(0, 0, 0));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          } else if (type == 1) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(0, 0, 255));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(255, 255, 255));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          }

          break;
        case "west":
          this.lastPointX = currentPointX;
          this.lastPointY = currentPointY;
          this.currentPointX -= distance;
          g2.setColor(Color.BLACK);
          g2.drawLine(lastPointX +22 ,lastPointY + 22,currentPointX +22 ,currentPointY +22);
          if (type == 0) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(255, 0, 0));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(0, 0, 0));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          } else if (type == 1) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(0, 0, 255));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(255, 255, 255));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          }
          break;
        case "süd":
          this.lastPointX = currentPointX;
          this.lastPointY = currentPointY;
          this.currentPointY -= distance;
          g2.setColor(Color.BLACK);
          g2.drawLine(lastPointX +22 ,lastPointY + 22,currentPointX +22 ,currentPointY +22);
          if (type == 0) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(255, 0, 0));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(0, 0, 0));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          } else if (type == 1) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(0, 0, 255));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(255, 255, 255));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
          }
          break;
        case "nord":
          this.lastPointX = currentPointX;
          this.lastPointY = currentPointY;
          this.currentPointY +=  distance;
          g2.setColor(Color.BLACK);
          g2.drawLine(lastPointX +22 ,lastPointY + 22,currentPointX +22 ,currentPointY +22);
          if (type == 0) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(255, 0, 0));
            }

            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(0, 0, 0));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
            break;
          } else if (type == 1) {
            if (overlap == true){
              this.g2.setColor(new Color(128,128,128));
            }else{
              this.g2.setColor(new Color(0, 0, 255));
            }
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            this.g2.setColor(new Color(255, 255, 255));
            g2.setFont(font);
            FontMetrics metrics = g2.getFontMetrics();
            int ascent = metrics.getAscent();
            int labelWidth = metrics.stringWidth(label);
            g2.drawString(label, currentPointX + cellSize/2  , currentPointY + cellSize/2 );
            break;
          }
          break;
        default:
          break;
      }
    }
    public void printCandidateInformation (int hydroContacts, int overlaps, double fitness){
    Font font = new Font("Serif", Font.PLAIN, 30);

    int hydroC = hydroContacts;
    int overl = overlaps;
    double fit = fitness;

    this.g2.setColor(Color.BLACK);
    String label ="Hydrophobcontacts: " + hydroC;
    this.g2.setFont(font);
    FontMetrics metrics = this.g2.getFontMetrics();
    int ascent = metrics.getAscent();
    int labelWidth = metrics.stringWidth(label);
    this.g2.drawString(label, 30  , 55 );
    label = "Overlappings: " + overl;
    this.g2.drawString(label, 30, 100);
    label = "Fitness: " + fit;
    this.g2.drawString(label, 30, 150);


    }
}
