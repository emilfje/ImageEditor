/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package functions;

import javafx.scene.image.Image;

/**
 *
 * @author Emil
 */
public class Blur extends Manipulate{
    
    public Blur(){
        super();
    }

    @Override
    public int[][] manipulateImage(int[][] pixels) {
        System.out.println("jag är i changecontrast");
        
        pixels = new int[512][512];
        
        return pixels;
        
        
        
    }
    
    
    
    
}
