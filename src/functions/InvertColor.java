/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;

import javafx.scene.paint.Color;

/**
 *
 * @author Emil
 */
public class InvertColor extends Manipulate {
    
    public InvertColor(){
        super();
    }
    
    @Override
    public int[][] manipulateImage(int[][] PixelMatrix) {
        
        WritableImage wImage = new WritableImage(
            (int)PixelMatrix.length,
            (int)PixelMatrix[0].length);

        PixelWriter pixelWriter = wImage.getPixelWriter();
        PixelReader pixelReader = wImage.getPixelReader();
        for (int x = 0; x < PixelMatrix.length; x++) 
        {
            for (int y = 0; y < PixelMatrix[x].length; y++) 
            {
                int argb = pixelReader.getArgb(x, y);
                int a = (0xff & (argb >> 24));
                int r = (0xff & (PixelMatrix[x][y] >> 16));
                int g = (0xff & (PixelMatrix[x][y] >> 8));
                int b = (0xff & PixelMatrix[x][y]);

                Color color = Color.rgb(r, g, b, 1);

                color = color.invert();
               
                int a2 = 255;
                int r2 = (int) (color.getRed()*255);
                int g2 = (int) (color.getGreen()*255);
                int b2 = (int) (color.getBlue()*255);
                int argb2 = (a2 << 24) | (r2 << 16) | (g2 << 8) | (b2);   
                pixelWriter.setArgb(x, y, argb2);       
                PixelMatrix[x][y] = pixelReader.getArgb(x, y);
            }
        }
        return PixelMatrix;
    }  
}
