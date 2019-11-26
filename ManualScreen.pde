import java.util.*;

public class ManualScreen extends AppScreen{
    Torus torus;
    Moon moon;
    Ocean ocean;
    Star star;
    Flower flower;

    Slider deltaSlider, tethaSlider, gammaSlider, betaSlider, alphaSlider;
    boolean interactionEnabled = false;

    ArrayList<Wave> waves = new ArrayList<Wave>();

    Wave alphaWave = new Wave(20 , "alpha", 0);
    Wave betaWave = new Wave(20 , "beta", 1);
    Wave gammaWave = new Wave(20 , "gamma", 2);
    Wave tethaWave = new Wave(20 , "tetha", 3);
    Wave deltaWave = new Wave(20 , "delta", 4);

    int dominantWave = 0; // 0 delta, 1 tetha, 2 gamma, 3 beta

    Button backButton;

    public ManualScreen () {
        PVector backButtonImageSize = new PVector(width/15,height/20);
        PVector backButtonPosition = new PVector(20, height-40);
        PImage back = loadImage("back.png", "png");
        backButton = new Button(backButtonImageSize, backButtonPosition, back, backButtonImageSize, "", false);

        waves.add(alphaWave);
        waves.add(betaWave);
        waves.add(gammaWave);
        waves.add(tethaWave);
        waves.add(deltaWave);

        PVector alphaSliderPosition = new PVector(120, (height * 3 / 4));
        alphaSlider = new Slider(alphaSliderPosition, 20, "Alpha", 20, 100, new PVector(width - 140, 0));
        PVector betaSliderPosition = new PVector(120, (height * 3 / 4) + 50);
        betaSlider = new Slider(betaSliderPosition, 20, "Beta", 20, 100, new PVector(width - 140, 0));
        PVector gammaSliderPosition = new PVector(120, (height * 3 / 4) + 100);
        gammaSlider = new Slider(gammaSliderPosition, 20, "Gamma", 20, 100, new PVector(width - 140, 0));
        PVector tethaSliderPosition = new PVector(120, (height * 3 / 4) + 150);
        tethaSlider = new Slider(tethaSliderPosition, 20, "Tetha", 20, 100, new PVector(width - 140, 0));
        PVector deltaSliderPosition = new PVector(120, (height * 3 / 4) + 200);
        deltaSlider = new Slider(deltaSliderPosition, 20, "Delta", 20, 100, new PVector(width - 140, 0));
        
        torus = new Torus(5, 25, 10); // 20, 100, 30 are standard
        moon = new Moon(180 , 170, 10, 0.02, 300);
        ocean = new Ocean(20, 0.002, 100, 150);
        star = new Star(new PVector(width / 2, 300), 180, 15, 10, 4, 10);
        // PVector position, float edgeLength, int angleDivision, int numberOfEdgePoints, float pointSize,  int shapeRepetitionNumber
        flower = new Flower(new PVector(width /2, 300), 170, 5, 0.5, 10, 7, 3);
        // Flower (PVector position, float radius, int numberOfFlowers, float rotationSpeedDegPerSec, float radiusOffest, int numberOfStarPoints, int numberOfStarPointsOffset) 
        // for now we have Delta -> Torus, Tetha -> moon, Gamma -> ocean


        /////////////////////////////////////////////////////

        // IDEA OF FACDING AND HAVING SLIDER VALUES MONITORED FOR FADING INSTEAD OF MORPHING FOR NOW

        ////////////////////////////////////////////////////
    }

    void display() {
        backButton.display();

        alphaWave.waveValue = alphaSlider.drawSlider();
        betaWave.waveValue = betaSlider.drawSlider();
        gammaWave.waveValue = gammaSlider.drawSlider();
        tethaWave.waveValue = tethaSlider.drawSlider();
        deltaWave.waveValue = deltaSlider.drawSlider();

        shapeManager();   
    }

    void shapeManager() {
        findDominantWave();

        if(dominantWave == 0) { // delta
            flower.setAlpha(alphaWave.waveValue);
            flower.display(alphaWave.waveTransparency);
        } else if(dominantWave == 1) {
            star.setBeta(betaWave.waveValue);
            star.display(betaWave.waveTransparency);
        } else if(dominantWave == 2) {
            ocean.setGamma(gammaWave.waveValue);
            ocean.updateShape(gammaWave.waveTransparency);
        } else if(dominantWave == 3) {
            moon.setTetha(tethaWave.waveValue);
            moon.updateShape(tethaWave.waveTransparency);
        } else if(dominantWave == 4) {
            torus.setDelta(deltaWave.waveValue);
            torus.updateShape(interactionEnabled, deltaWave.waveTransparency);
        }
    }

    void mouseDragHandler(MouseEvent event) {
        // println("I'm dragged");
        deltaSlider.mouseDragged();
        tethaSlider.mouseDragged();
        gammaSlider.mouseDragged();
        betaSlider.mouseDragged();
        alphaSlider.mouseDragged();
    }

    void mouseMoveHandler() {
        if(mousePressed && (mouseButton == LEFT)) {
            deltaSlider.mouseDragged();
            tethaSlider.mouseDragged();
            gammaSlider.mouseDragged();
            betaSlider.mouseDragged();
            alphaSlider.mouseDragged();
        } 
    }

    void findDominantWave() {
        
        Collections.sort(waves);
        dominantWave = waves.get(0).waveIndex;

        
        int difference = waves.get(0).waveValue - waves.get(1).waveValue;
        if(difference < 10) {
            waves.get(0).setTransparency(map(difference, 0, 10, 0.5, 1));
            waves.get(1).setTransparency(map(difference, 0, 10, 0.5, 0));
        }
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


