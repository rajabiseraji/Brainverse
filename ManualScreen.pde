public class ManualScreen extends AppScreen{
    Torus torus;
    Moon moon;
    Ocean ocean;
    Star star;

    Slider deltaSlider, tethaSlider, gammaSlider, betaSlider;
    boolean interactionEnabled = false;
    int deltaSliderValue = 20;
    int tethaSliderValue = 20;
    int gammaSliderValue = 20;
    int betaSliderValue = 20;
    int dominantWave = 0; // 0 delta, 1 tetha, 2 gamma, 3 beta

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
        PVector betaSliderPosition = new PVector(120, (height * 3 / 4) + 150);
        betaSlider = new Slider(betaSliderPosition, 20, "Beta", 20, 100, new PVector(width - 140, 0));
        
        torus = new Torus(5, 25, 10); // 20, 100, 30 are standard
        moon = new Moon(180 , 170, 10, 0.02, 300);
        ocean = new Ocean(20, 0.002, 100, 150);
        star = new Star(new PVector(width / 2, 300), 120, 20, 40, 2, 10);
        // PVector position, float edgeLength, int angleDivision, int numberOfEdgePoints, float pointSize,  int shapeRepetitionNumber
        // for now we have Delta -> Torus, Tetha -> moon, Gamma -> ocean
    }

    void display() {
        backButton.display();

        deltaSliderValue = deltaSlider.drawSlider();
        tethaSliderValue = tethaSlider.drawSlider();
        gammaSliderValue = gammaSlider.drawSlider();
        betaSliderValue = betaSlider.drawSlider();

        shapeManager();   
    }

    void shapeManager() {
        findDominantWave();

        if(dominantWave == 0) { // delta
            torus.setDelta(deltaSliderValue);
            torus.updateShape(interactionEnabled);
        } else if(dominantWave == 1) {
            // tetha
            moon.setTetha(tethaSliderValue);
            moon.updateShape();
        } else if(dominantWave == 2) {
            ocean.setGamma(gammaSliderValue);
            ocean.updateShape();
        } else if(dominantWave == 3) {
            star.display();
        }
    }

    void mouseDragHandler(MouseEvent event) {
        // println("I'm dragged");
        deltaSlider.mouseDragged();
        tethaSlider.mouseDragged();
        gammaSlider.mouseDragged();
        betaSlider.mouseDragged();
    }

    void mouseMoveHandler() {
        if(mousePressed && (mouseButton == LEFT)) {
            deltaSlider.mouseDragged();
            tethaSlider.mouseDragged();
            gammaSlider.mouseDragged();
            betaSlider.mouseDragged();
        } 
    }

    void findDominantWave() {
        if(deltaSliderValue > tethaSliderValue && deltaSliderValue > gammaSliderValue && deltaSliderValue > betaSliderValue)
            dominantWave = 0;
        else if(tethaSliderValue > deltaSliderValue && tethaSliderValue > gammaSliderValue && tethaSliderValue > betaSliderValue)
            dominantWave = 1;
        else if(gammaSliderValue > deltaSliderValue && gammaSliderValue > tethaSliderValue && gammaSliderValue > betaSliderValue)
            dominantWave = 2;
        else if(betaSliderValue > deltaSliderValue && betaSliderValue > tethaSliderValue && betaSliderValue > gammaSliderValue)
            dominantWave = 3;
        else 
            dominantWave = 0;
    }

    void mouseClickHandler(MouseEvent event) {
        backButton.mouseClickHandler(event);
    }

    void keyHandler(KeyEvent event) {
        if(key == 'i') {
            switchInteraction();
        }
    }

    
    void switchInteraction() {
        if(interactionEnabled)
            interactionEnabled = false;
        else 
            interactionEnabled = true;
    }

}
