package view;

import controller.Controller_Labb5;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import model.Model_Labb5;


public class View_Labb5 extends VBox {

    private final Model_Labb5 model;
    
    private final BorderPane borderPane;
    
    private final MenuBar menuBar;
    private final Menu menu_File;
    private final MenuItem loadImageMenuItem;
    protected final MenuItem menuItem_saveImage;
    private final MenuItem menuItem_Close;
    private final Menu menu_Generate;
    private final MenuItem menuItem_ChangeBrightness;
    private final MenuItem menuItem_ShowHistogram;
    private final MenuItem menuItem_InvertColor;
    private final MenuItem menuItem_ChangeContrast;
    private final Menu menu_Help;
    private final MenuItem menuItem_About;

    private final HBox hBoxUI;
    private final VBox vBox_leftUILocation;
    private final Label label_Window;
    private final Slider slider_Window;
    private final Button button_Brighten;
    private final Button button_Darken;
    private final Label label_Level;
    private final Slider slider_Level;
    private  ImageView imageView_rightUILocation;
    private final HBox hBox_BottomParent;
    private final AnchorPane anchorPane_BottomParent;
    private Label label_userInfo;
    private Label label_emptyspace;
    private Image image;

    protected LineChart histogramChart;
    protected CategoryAxis xAxis;
    protected NumberAxis yAxis;
    
    private Alert alert;

    public View_Labb5(Model_Labb5 model) {
        this.model = model;
        Controller_Labb5 controller = new Controller_Labb5(model, this);

        borderPane = new BorderPane();
        
        menuBar = new MenuBar();
        
        menu_File = new Menu();
        loadImageMenuItem = new MenuItem();
        menuItem_saveImage = new MenuItem();
        menuItem_Close = new MenuItem();
        
        menu_Generate = new Menu();                    //image manipulation options
        menuItem_ChangeBrightness = new MenuItem();
        menuItem_ShowHistogram = new MenuItem();
        menuItem_InvertColor = new MenuItem();
        menuItem_ChangeContrast = new MenuItem();
        
        menu_Help = new Menu();
        menuItem_About = new MenuItem();
        
        initMenu();
        
        hBoxUI = new HBox();
        vBox_leftUILocation = new VBox();
        label_Window = new Label();
        slider_Window = new Slider(0,255,255);
        label_Level = new Label();
         slider_Level = new Slider(0,255,128);
        button_Brighten = new Button();
        button_Darken = new Button();
        imageView_rightUILocation = new ImageView();
        hBox_BottomParent = new HBox();
        anchorPane_BottomParent = new AnchorPane();
        label_userInfo = new Label();
        label_emptyspace = new Label();

        borderPane.setPrefHeight(720.0);
        borderPane.setPrefWidth(900.0);
        
        xAxis = new CategoryAxis();
        yAxis = new NumberAxis();
        histogramChart = new LineChart(xAxis, yAxis);

        
        BorderPane.setAlignment(menuBar, javafx.geometry.Pos.CENTER);
        BorderPane.setMargin(menuBar, new Insets(0.0, 0.0, 10.0, 0.0));
        borderPane.setTop(menuBar);
        BorderPane.setAlignment(hBoxUI, javafx.geometry.Pos.CENTER);
        
        initMainHbox();
        
        borderPane.setCenter(hBoxUI);
        BorderPane.setAlignment(hBox_BottomParent, javafx.geometry.Pos.CENTER);
        
        initBottomHbox();
        borderPane.setBottom(hBox_BottomParent);

        
        hBoxUI.getChildren().add(vBox_leftUILocation);
        hBoxUI.getChildren().add(imageView_rightUILocation);
        anchorPane_BottomParent.getChildren().add(label_userInfo);
        hBox_BottomParent.getChildren().add(anchorPane_BottomParent);
        addEventHandlers(controller);
        getChildren().add(borderPane);
        
    }

    void showAlert(String message) {
        alert.setHeaderText("");
        alert.setTitle("Alert!");
        alert.setContentText(message);
        alert.show();
    }
    
    private void initMenu(){
        menu_File.setText("File");
        loadImageMenuItem.setText("Load image");
        menuItem_saveImage.setText("Save image");
        menuItem_Close.setText("Close");
        menu_File.getItems().add(loadImageMenuItem);
        menu_File.getItems().add(menuItem_saveImage);
        menu_File.getItems().add(menuItem_Close);
               
        menu_Generate.setText("Generate");
        menuItem_ChangeBrightness.setText("Change Brightness");
        menuItem_ShowHistogram.setText("Show Histogram");
        menuItem_InvertColor.setText("Invert Colors");
        menuItem_ChangeContrast.setText("Change Contrast");
        menu_Generate.getItems().add(menuItem_ChangeBrightness);
        menu_Generate.getItems().add(menuItem_ShowHistogram);
        menu_Generate.getItems().add(menuItem_InvertColor);
        menu_Generate.getItems().add(menuItem_ChangeContrast);
        
        menu_Help.setText("Help");
        menuItem_About.setText("About");
        menu_Help.getItems().add(menuItem_About);
        
        
        menuBar.getMenus().add(menu_File);
        menuBar.getMenus().add(menu_Generate);
        menuBar.getMenus().add(menu_Help);
    }
    
    private void initMainHbox(){
        
        hBoxUI.setPrefHeight(100.0);
        hBoxUI.setPrefWidth(200.0);

        vBox_leftUILocation.setPrefHeight(696.0);
        vBox_leftUILocation.setPrefWidth(512.0);

        vBox_leftUILocation.setPadding(new Insets(10.0));

        imageView_rightUILocation.setFitHeight(512.0);
        imageView_rightUILocation.setFitWidth(512.0);
    }
    
        private void initBottomHbox(){
            
            hBox_BottomParent.setPrefHeight(48.0);
            hBox_BottomParent.setPrefWidth(900.0);

            label_userInfo.setLayoutX(14.0);
            label_userInfo.setLayoutY(13.0);
            label_userInfo.setText("User Information");
        }

    public void showHistogram()
    {
        this.xAxis.setEndMargin(255.0);
        this.xAxis.setPrefHeight(29.0);
        this.xAxis.setPrefWidth(467.0);
        this.xAxis.setSide(javafx.geometry.Side.BOTTOM);
        this.xAxis.setStartMargin(0.0);

        this.yAxis.setSide(javafx.geometry.Side.LEFT);
        this.yAxis.setTickLabelGap(1.0);
        this.yAxis.setUpperBound(55000);
        this.yAxis.setLowerBound(0);
        this.histogramChart.setPadding(new Insets(10.0));
        this.histogramChart.setCreateSymbols(false);
        this.vBox_leftUILocation.getChildren().add(histogramChart);
    }

public void addListeners(Controller_Labb5 controller){
        ChangeListener<Number> levelListener = new ChangeListener<Number>(){
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            controller.handleContrast();
            setUserInfo("window: " + (int) slider_Window.getValue() + " Level: " + (int) slider_Level.getValue());
        }
    };
    slider_Level.valueProperty().addListener(levelListener);

    ChangeListener<Number> windowListener = new ChangeListener<Number>(){
        @Override
        public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
            controller.handleContrast();
            setUserInfo("window: " + (int) slider_Window.getValue() + " Level: " + (int) slider_Level.getValue());
        }
    };
    slider_Window.valueProperty().addListener(windowListener);
    }

    public void initSliders(Controller_Labb5 controller){
        addListeners(controller);
        vBox_leftUILocation.getChildren().remove(histogramChart);
        label_Window.setText("Window:");

        slider_Window.setMajorTickUnit(64.0);
        slider_Window.setMax(255.0);
        slider_Window.setPrefHeight(61.0);
        slider_Window.setPrefWidth(403.0);
        slider_Window.setShowTickLabels(true);

        label_Level.setText("Level:");
        slider_Level.setLayoutX(10.0);
        slider_Level.setLayoutY(27.0);
        slider_Level.setMajorTickUnit(64.0);
        slider_Level.setMax(255.0);

        slider_Level.setPrefHeight(61.0);
        slider_Level.setPrefWidth(403.0);
        slider_Level.setShowTickLabels(true);

        vBox_leftUILocation.getChildren().add(label_Window);
        vBox_leftUILocation.getChildren().add(slider_Window);
        vBox_leftUILocation.getChildren().add(label_Level);
        vBox_leftUILocation.getChildren().add(slider_Level);
    }
    public void initBrightnessButtons(Controller_Labb5 controller)
    {
        button_Brighten.setText("Brighten");
        button_Brighten.setPrefWidth(100.0);
        button_Darken.setText("Darken");
        button_Darken.setPrefWidth(100.0);
        label_emptyspace.setPrefHeight(10);
        vBox_leftUILocation.getChildren().add(button_Brighten);
        vBox_leftUILocation.getChildren().add(label_emptyspace);
        vBox_leftUILocation.getChildren().add(button_Darken);
    }
    
    public void clearUITools()
    {
        if (vBox_leftUILocation.getChildren().contains(button_Brighten))
        {
            System.out.println("removing buttons");
            vBox_leftUILocation.getChildren().remove(button_Brighten);
            vBox_leftUILocation.getChildren().remove(label_emptyspace);
            vBox_leftUILocation.getChildren().remove(button_Darken);
        }
        if (vBox_leftUILocation.getChildren().contains(histogramChart))
        {
            System.out.println("removing histo");
            vBox_leftUILocation.getChildren().remove(histogramChart);
        }
        if (vBox_leftUILocation.getChildren().contains(slider_Level))
        {
            vBox_leftUILocation.getChildren().remove(label_Window);
            vBox_leftUILocation.getChildren().remove(slider_Window);
            vBox_leftUILocation.getChildren().remove(label_Level);
            vBox_leftUILocation.getChildren().remove(slider_Level);  
        }
    }

    private void addEventHandlers(Controller_Labb5 controller) {
        menuItem_Close.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.handleClose(event);
            }
        });

        loadImageMenuItem.setOnAction(new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
           controller.handleLoadImage(event);
        }
        });
        
        menuItem_ChangeBrightness.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                clearUITools();
                initBrightnessButtons(controller);
            }
        });
        
        button_Brighten.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                controller.handleBrightnessPress(event);
                System.out.println("Brighten pressed");
            }
        });
        
        button_Darken.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                controller.handleDarkenPress(event);
                System.out.println("Darken pressed");
            }
        });
        
        menuItem_ShowHistogram.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                controller.handleHistogram(event);
            }  
        });
        
        menuItem_InvertColor.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    controller.handleInvertColor(event);
                }
        });
        menuItem_saveImage.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    controller.handleSave(event);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(View_Labb5.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(View_Labb5.class.getName()).log(Level.SEVERE, null, ex);
                } 
            }
        });
        
        menuItem_ChangeContrast.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                controller.handleContrastSliders();
            }
        });
    }
        
        public double getSlider_LevelValue(){
            return this.slider_Level.getValue();
        }

        public double getSlider_WindowValue(){
            return this.slider_Window.getValue();
        }

        public void updateFromModel(){
            System.out.println("Updaterar från mdoel");
        }
        
        public void setImage(Image image)
        {
            this.image = image;
        }
        
        public Image getImage()
        {
            return this.image;
        }
        
        public void setImageView(Image image){
            this.imageView_rightUILocation.setImage(image);
        }

        public void updateImageView(ImageView imageview)
        {
            hBoxUI.getChildren().remove(imageView_rightUILocation);
            this.imageView_rightUILocation = imageview;
            hBoxUI.getChildren().add(imageView_rightUILocation);
        }
    
        public void setUserInfo(String string){
            this.label_userInfo.setText(string);
        }
        
        public void setHistogram(LineChart histogram){
            this.histogramChart = histogram;
        }
        
    public void showMessage(String message)
    {
        this.alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText("Message from application");
        alert.setContentText(message);
        alert.show();
    }
}
