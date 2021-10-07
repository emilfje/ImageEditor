/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Histogram;

import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;


/**
 *
 * @author Emil
 */
public class Histogram {
    
    private final int alpha[];
    private final int red[];
    private final int green[];
    private final int blue[];
    private final CategoryAxis xAxis; 
    private final NumberAxis yAxis; 
    private XYChart.Series seriesAlpha;
    private XYChart.Series seriesRed;
    private XYChart.Series seriesGreen;
    private XYChart.Series seriesBlue;
    private final LineChart<String,Number> histogram;
    
    /**
     * Creates an object to generate and hold a PixelMatrix
     */
    public Histogram(){
        this.alpha = new int[256];
        this.green = new int[256];
        this.red = new int[256];
        this.blue = new int[256];
        this.xAxis = new CategoryAxis();
        this.yAxis = new NumberAxis();
        this.histogram = new LineChart<>(xAxis, yAxis); 
    }
    
    private void initARGB(){
        for (int i = 0; i < 256; i++) {
                alpha[i] = red[i] = green[i] = blue[i] = 0;
        }
    }
    
    /**
     * Generates a PixelMatrix from the image
     * @param image Image to be converted into a PixelMatrix
     * @return A LineChart
     */
    public LineChart doHistogram(Image image){
        this.histogram.getData().clear();
        generateHistogram(image);
        this.histogram.getData().addAll(
                    //this.getSeriesAlpha(),
                    this.getSeriesRed(),
                    this.getSeriesGreen(),
                    this.getSeriesBlue());

        return this.histogram;
    }
    
    private void generateHistogram(Image image){
        initARGB();
        PixelReader pixelReader = image.getPixelReader();

        for (int y = 0; y < image.getHeight(); y++) {
                for (int x = 0; x < image.getWidth(); x++) {
                    int argb = pixelReader.getArgb(x, y);
                    int a = (0xff & (argb >> 24));
                    int r = (0xff & (argb >> 16));
                    int g = (0xff & (argb >> 8));
                    int b = (0xff & argb);
                    this.alpha[a]++;
                    this.red[r]++;
                    this.green[g]++;
                    this.blue[b]++;
                }
            }
        
            this.seriesAlpha = new XYChart.Series();
            this.seriesRed = new XYChart.Series();
            this.seriesGreen = new XYChart.Series();
            this.seriesBlue = new XYChart.Series();
            this.seriesAlpha.setName("alpha");
            this.seriesRed.setName("red");
            this.seriesGreen.setName("green");
            this.seriesBlue.setName("blue");
 
            //copy alpha[], red[], green[], blue[]
            //to seriesAlpha, seriesRed, seriesGreen, seriesBlue
            for (int i = 0; i < 256; i++) {
                this.seriesAlpha.getData().add(new XYChart.Data(String.valueOf(i), this.alpha[i]));
                this.seriesRed.getData().add(new XYChart.Data(String.valueOf(i), this.red[i]));
                this.seriesGreen.getData().add(new XYChart.Data(String.valueOf(i), this.green[i]));
                this.seriesBlue.getData().add(new XYChart.Data(String.valueOf(i), this.blue[i]));
            }
    }

    /**
     * Getter for Alpha 
     * @return the data items of Alpha
     */
    public XYChart.Series getSeriesAlpha() {
        return this.seriesAlpha;
    }
 
    /**
     * Getter for Red
     * @return the data items of Red
     */
    public XYChart.Series getSeriesRed() {
        return this.seriesRed;
    }
 
    /**
     * Getter for Green
     * @return the data items of Green
     */
    public XYChart.Series getSeriesGreen() {
       return this.seriesGreen;
    }
 
    /**
     * Getter for Blue
     * @return the data items of Blue
     */
    public XYChart.Series getSeriesBlue() {
        return this.seriesBlue;
    }
}
