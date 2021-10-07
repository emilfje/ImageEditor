
package functions;

import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;

public class ChangeContrast extends Manipulate{

    private double window;
    private double level;
    private int A;
    private int B;
    private final double yMin;
    private int[][] savePixelMatrix;
    
    public ChangeContrast(){
        super();
        this.window = 0;
        this.level = 0;
        this.A = 0;
        this.B = 0;
        this.yMin = -16777216;
    }
    
    @Override
    public int[][] manipulateImage(int[][] PixelMatrix) {

        int Abound = (int) ((level - (window/2)));   //left bound
        int Bbound = (int) ((level + (window/2)));   //right bound

        if(Abound < 0){
            this.A =0;
        }else this.A = (int) Abound;  
        
        if(Bbound > 255){
            this.B = 255;
        }else this.B = (int) Bbound;
        
        WritableImage wImage = new WritableImage(
            (int)PixelMatrix.length,
            (int)PixelMatrix[0].length);
        PixelReader pixelReader = wImage.getPixelReader();
        
        for (int x = 0; x < PixelMatrix.length; x++) 
        {
            for (int y = 0; y < PixelMatrix[x].length; y++) 
            {
                int argb = pixelReader.getArgb(x, y);
                int a = (0xff & (argb >> 24));
                int r = (0xff & (this.savePixelMatrix[x][y] >> 16));
                int g = (0xff & (this.savePixelMatrix[x][y] >> 8));
                int b = (0xff & this.savePixelMatrix[x][y]);

                double blue = (b);
                
                if(blue <= this.A) 
                    PixelMatrix[x][y] = (a << 24);
                
                else if(blue >= this.B) 
                    PixelMatrix[x][y] = (int)this.yMin;
                
                else 
                    PixelMatrix[x][y] = this.savePixelMatrix[x][y];
            }
        }
        return PixelMatrix;
    }

    public void setLevel(double level){
        this.level = level;
    }
    
    public void setWindow(double window){
        this.window = window;
    }
    
    public void saveMatrix(int[][] PixelMatrix){
        this.savePixelMatrix = PixelMatrix;
    }
    
}
