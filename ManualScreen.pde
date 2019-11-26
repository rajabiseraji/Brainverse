public class ManualScreen extends AppScreen{
    Torus torus;
    Moon moon;
    Ocean ocean;

    Slider slider;
    boolean interactionEnabled = false;
    int sliderValue = 20;

    Button backButton;

    public ManualScreen () {
        PVector backButtonImageSize = new PVector(width/15,height/20);
        PVector backButtonPosition = new PVector(20, height-40);
        PImage back = loadImage("back.png", "png");
        backButton = new Button(backButtonImageSize, backButtonPosition, back, backButtonImageSize, "", false);

        torus = new Torus(5, 25, 10); // 20, 100, 30 are standard
        PVector sliderPosition = new PVector(120, height - 50);
        slider = new Slider(sliderPosition, 20, "Manual", 20, 20, new PVector(width - 140, 0));

        moon = new Moon(180 , 170, 10, 0.02, 300);
        ocean = new Ocean(20, 0.002, 100, 150);
    }

    void display() {
        backButton.display();

        sliderValue = slider.drawSlider();
        // torus.setDelta(sliderValue);
        // torus.updateShape(interactionEnabled);
        // Torus
        
        // moon.setTetha(sliderValue);
        // moon.updateShape();
        
        // Moon

        // Ocean
        // ocean.setGamma(sliderValue);
        // ocean.updateShape();
    }

    void mouseDragHandler(MouseEvent event) {
        // println("I'm dragged");
        slider.mouseDragged();
    }

    void mouseMoveHandler() {
        if(mousePressed && (mouseButton == LEFT)) {
            slider.mouseDragged();
        } 
    }

    void mouseClickHandler(MouseEvent event) {
        backButton.mouseClickHandler(event);
    }

}
