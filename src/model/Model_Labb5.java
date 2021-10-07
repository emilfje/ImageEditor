package model;

import Histogram.Histogram;
import functions.Blur;
import functions.Brighten;
import functions.ChangeContrast;
import functions.Darken;
import functions.InvertColor;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.chart.LineChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;


public class Model_Labb5 {
    
    private FileChooser fileChooser;
    private Stage stage;
    private final InvertColor invertColor;
    private final Brighten brighten;
    private final Darken darken;
    private final Histogram histogram;
    private final ChangeContrast changeContrast;
    private int[][] PixelMatrix;
    
    public Model_Labb5() {
        this.invertColor = new InvertColor();
        this.brighten = new Brighten();
        this.darken = new Darken();
        this.histogram = new Histogram();
        this.changeContrast = new ChangeContrast();
    }

    public BufferedImage openAndReadFile() 
    {
        BufferedImage image = null;
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        File file = fileChooser.showOpenDialog(stage);

        if (file != null)
        {
            try {
                image = ImageIO.read(file);
            }
            catch (IOException ie) 
            {
                System.out.println("unable to read image");
            }
            finally{
               
            }
        }
        return image;
    }
    
    public void saveToFile(Image image) throws FileNotFoundException, IOException{
        BufferedImage bi =  SwingFXUtils.fromFXImage(image, null);
        fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("PNG files (.png)", ".png");
        fileChooser.getExtensionFilters().add(extFilter);
        File outPutfile = fileChooser.showSaveDialog(stage);
        fileChooser.selectedExtensionFilterProperty();
        ImageIO.write(bi, "png", outPutfile);
    }
    
    public Image Brighten(Image image){
        imageToMatrix(image);
        this.PixelMatrix = brighten.manipulateImage(this.PixelMatrix);
        return matrixToImage();
    }
    
    public Image Darken(Image image){
        imageToMatrix(image);
        this.PixelMatrix = darken.manipulateImage(this.PixelMatrix);
        return matrixToImage();
    }
    
    public Image invertColor(Image image){
        imageToMatrix(image);
        this.PixelMatrix = invertColor.manipulateImage(this.PixelMatrix);
        return matrixToImage();
    }

    public LineChart generateHistogram(Image image){ 
        return histogram.doHistogram(image);
    }
        
    public void setWindowContrast(double Window){
        changeContrast.setWindow(Window);
    }

    public void setLevelContrast(double level){
        changeContrast.setLevel(level);
    }
    
    public Image changeContrast(Image image){
        imageToMatrix(image);
        this.PixelMatrix = changeContrast.manipulateImage(this.PixelMatrix);
        return matrixToImage();
    }
        
    private void initMatrix(){
        for(int x=0 ; x < PixelMatrix.length; ++x)
            for(int y=0; y<PixelMatrix[x].length;++y)
                this.PixelMatrix[x][y] = 0;
    }
    
    public void saveImageMatrix(Image image){
        changeContrast.saveMatrix(imageToMatrix(image));
    }
    private int[][] imageToMatrix(Image image)
    {
        PixelReader pixelReader = image.getPixelReader();
        this.PixelMatrix = new int[(int)(image.getWidth())][(int)(image.getHeight())];
        this.initMatrix();
        for(int x = 0; x < image.getWidth(); x++){
            for(int y = 0; y < image.getHeight(); y++){
                this.PixelMatrix[x][y] = pixelReader.getArgb(x, y);
            }
        }
        return this.PixelMatrix;
    }
    
    private Image matrixToImage()
    {
        BufferedImage bufImage = new BufferedImage(PixelMatrix.length, PixelMatrix[0].length, BufferedImage.TYPE_INT_ARGB);

        for(int x=0; x<PixelMatrix.length; x++)
        {
            for(int y=0; y<PixelMatrix[x].length; y++)
            {
               bufImage.setRGB(x, y, PixelMatrix[x][y]);
            }
        }
        Image image = SwingFXUtils.toFXImage(bufImage, null);
        return image;
    }
}
