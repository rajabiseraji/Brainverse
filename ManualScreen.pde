public class ManualScreen extends AppScreen{
    Torus torus;
    Moon moon;
    Ocean ocean;

    Slider deltaSlider, tethaSlider, gammaSlider;
    boolean interactionEnabled = false;
    int deltaSliderValue = 20;
    int tethaSliderValue = 20;
    int gammaSliderValue = 20;

    Button backButton;

    public ManualScreen () {
        PVector backButtonImageSize = new PVector(width/15,height/20);
        PVector backButtonPosition = new PVector(20, height-40);
        PImage back = loadImage("back.png", "png");
        backButton = new Button(backButtonImageSize, backButtonPosition, back, backButtonImageSize, "", false);

        PVector deltaSliderPosition = new PVector(120, (height * 3 / 4));
        deltaSlider = new Slider(deltaSliderPosition, 20, "Delta", 20, 100, new PVector(width - 140, 0));
        PVector tethaSliderPosition = new PVector(120, (height * 3 / 4) + 50);
        tethaSlider = new Slider(tethaSliderPosition, 20, "Tetha", 20, 100, new PVector(width - 140, 0));
        PVector gammaSliderPosition = new PVector(120, (height * 3 / 4) + 100);
        gammaSlider = new Slider(gammaSliderPosition, 20, "Gamma", 20, 100, new PVector(width - 140, 0));
        
        torus = new Torus(5, 25, 10); // 20, 100, 30 are standard

        moon = new Moon(180 , 170, 10, 0.02, 300);
        ocean = new Ocean(20, 0.002, 100, 150);

        // for now we have Delta -> Torus, Tetha -> moon, Gamma -> ocean
    }

    void display() {
        backButton.display();

        deltaSliderValue = deltaSlider.drawSlider();
        tethaSliderValue = tethaSlider.drawSlider();
        gammaSliderValue = gammaSlider.drawSlider();
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
        deltaSlider.mouseDragged();
        tethaSlider.mouseDragged();
        gammaSlider.mouseDragged();
    }

    void mouseMoveHandler() {
        if(mousePressed && (mouseButton == LEFT)) {
            deltaSlider.mouseDragged();
            tethaSlider.mouseDragged();
            gammaSlider.mouseDragged();
        } 
    }

    void mouseClickHandler(MouseEvent event) {
        backButton.mouseClickHandler(event);
    }

}
