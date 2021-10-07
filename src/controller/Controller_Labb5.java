package controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import model.Model_Labb5;
import view.View_Labb5;

public class Controller_Labb5 {

    private final Model_Labb5 model;
    private final View_Labb5 view;
    
    
    
    public Controller_Labb5(Model_Labb5 model, View_Labb5 view) 
    {
        this.model = model;
        this.view = view;
    }
    
     public void handleLoadImage(ActionEvent event) {
        BufferedImage b_image = model.openAndReadFile();
        if (b_image != null)
        {
            Image image = SwingFXUtils.toFXImage(b_image, null);          
            model.saveImageMatrix(image);
            view.setImageView(image);
            view.setImage(image);
            view.setUserInfo("Image loaded!");
        }
        else
        {
            view.setUserInfo("Invalid format!");
            view.showMessage("Invalid Format! No image loaded");
        }
    }
    public void handleClose(ActionEvent event){
        Platform.exit();
    }
    
    public void handleHistogram(ActionEvent event){
        view.clearUITools();
        view.setHistogram(model.generateHistogram(view.getImage()));
        view.showHistogram();
        view.setUserInfo("Histogram generated!");
    }
    
    public void handleInvertColor(ActionEvent event){
        view.setImage(model.invertColor(view.getImage()));
        view.setImageView(view.getImage());
        view.setUserInfo("Colors inverted!");
    }
    
    public void handleBrightnessPress(ActionEvent event)
    {
        view.setImage(model.Brighten(view.getImage()));
        view.setImageView(view.getImage());
        view.setUserInfo("Image brigthend!");
    }
    
    public void handleDarkenPress(ActionEvent event)
    {
        view.setImage(model.Darken(view.getImage()));
        view.setImageView(view.getImage());
        view.setUserInfo("Image darkend!");
    }
    
    public void handleSaturation(ActionEvent event){
        view.initBrightnessButtons(this);
    }
    
    public void handleContrastSliders(){
        view.clearUITools();
        view.initSliders(this);
    }
    
public void handleContrast(){
        model.setLevelContrast(view.getSlider_LevelValue());
        model.setWindowContrast(view.getSlider_WindowValue());
        view.setImage(model.changeContrast(view.getImage()));
        view.setImageView(view.getImage());
    }
    
    
    public void handleSave(ActionEvent event) throws IOException{
        model.saveToFile(view.getImage());
    }
}
