package com.company;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class ProteinGraphic {

  static final int height = 1000;
  static final int width = 1000;
  static final int cellSize = 10;
  int currentPointX = 500;
  int currentPointY = 500;
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
      ImageIO.write(image, "png", new File(folder + File.separator + filename));
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void drawFirstAcid(int type){
    if (type == 0) {
      this.g2.setColor(new Color(255, 0, 0));
      this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
    } else if (type == 1) {
      this.g2.setColor(new Color(0, 0, 255));
      this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
    }
  }

  public void draw(String direction, int type) {
    // type 0 = hydrophob (good)
    // type 1 = hydrophil (bad)

      switch (direction) {
        case "ost":
          if (type == 0) {
            this.currentPointX += this.currentPointX + 20;
            this.g2.setColor(new Color(255, 0, 0));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          } else if (type == 1) {
            this.currentPointX += this.currentPointX + 20;
            this.g2.setColor(new Color(0, 0, 255));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          }
          break;
        case "west":
          if (type == 0) {
            this.currentPointX += this.currentPointX - 20;
            this.g2.setColor(new Color(255, 0, 0));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          } else if (type == 1) {
            this.currentPointX += this.currentPointX - 20;
            this.g2.setColor(new Color(0, 0, 255));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          }
          break;
        case "süd":
          if (type == 0) {
            this.currentPointY += this.currentPointY - 20;
            this.g2.setColor(new Color(255, 0, 0));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          } else if (type == 1) {
            this.currentPointY += this.currentPointY - 20;
            this.g2.setColor(new Color(0, 0, 255));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
          }
          break;
        case "nord":
          if (type == 0) {
            this.currentPointY += this.currentPointY + 20;
            this.g2.setColor(new Color(255, 0, 0));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            break;
          } else if (type == 1) {
            this.currentPointY += this.currentPointY + 20;
            this.g2.setColor(new Color(0, 0, 255));
            this.g2.fillRect(this.currentPointX, this.currentPointY, this.cellSize, this.cellSize);
            break;
          }
          break;
        default:
          break;
      }
    }
}
