import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.Vector; 
import java.util.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Final extends PApplet {




PImage img;
PFont firaSansBook;
PFont firaSansExtraBold;

int currentScreen = 0; // 0 title screen, 1 choose auto/manual, 2 main app screen manual, 3 main app screen auto
int elapsedTime = 0; 
Vector<AppScreen> appScreens = new Vector<AppScreen>();
public void setup() {
  
  background(0);
  // background(#525252);
  ManualScreen manualScreen = new ManualScreen();
  appScreens.add(manualScreen);
}

public void draw() {
  background(0);
  appScreens.get(currentScreen).display();
}

public void mouseDragged(MouseEvent event) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.mouseDragHandler(event);
}

// fallback for stupid MACs :))
public void mouseMoved() {
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.mouseMoveHandler();
}

public void keyPressed(KeyEvent event){
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.keyHandler(event);
}

public void changeScreen(int newScreenNumber) {
  currentScreen = newScreenNumber;
}


public void mouseClicked(MouseEvent event) {
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.mouseClickHandler(event);
}

abstract class AppScreen {
    public abstract void display();
}
public class BrainAtom {
    private float mass = 0;
    private float currentSpeed = 0;
    private float maxSpeed = 0;
    private PVector center;
    private PVector position;
    private float distanceToCentre = 0;
    private int atomColor = 0xffffff;

    public BrainAtom (float mass, float currentSpeed, float maxSpeed, PVector center, float distanceToCentre) {
        this.mass = mass;
        this.currentSpeed = currentSpeed;
        this.maxSpeed = maxSpeed;
        this.center = center;
        this.distanceToCentre = distanceToCentre;
        this.atomColor = atomColor;
    }

    public BrainAtom(PVector position) {
        this.position = position;
    }

    // setters
    public void setMass(float mass) {
        this.mass = mass;
    }
    public void setCurrentSpeed(float currentSpeed) {
        this.currentSpeed = currentSpeed;
    }
    public void setMaxSpeed(float maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
    public void setCenter(PVector center) {
        this.center = center;
    }
    public void setDistanceToCentre(float distanceToCentre) {
        this.distanceToCentre = distanceToCentre;
    }
    public void setPosition(PVector position) {
        this.position = position;
    }

    // getters
    public float getmass() {
        return this.mass;
    }
    public PVector getPosition() {
        return this.position;
    }
    public float getcurrentSpeed() {
        return this.currentSpeed;
    }
    public float getmaxSpeed() {
        return this.maxSpeed;
    }
    public PVector getcenter() {
        return this.center;
    }
    public float getdistanceToCentre() {
        return this.distanceToCentre;
    }


    public void display() {
        // it's temporary
        point(this.position.x, this.position.y);
    }



}
public class Button {

    PVector position;
    PVector size;
    PVector buttonImageSize;
    PImage buttonImage;
    boolean active = false;
    String buttonText;

    PShape buttonShape;

    private PFont firaSansBook;
    private PFont firaSansExtraBold;
    private PFont currentFont;
    
    public Button (PVector size, PVector position, PImage buttonImage, PVector buttonImageSize, String buttonText, boolean active) { // image button 
        this.size = size;
        this.position = position;
        this.active = active;
        this.buttonImageSize = buttonImageSize;

        this.firaSansBook = createFont("FiraSans-Book.otf", 15);
        this.firaSansExtraBold = createFont("FiraSans-ExtraBold.otf", 15);
        this.currentFont = firaSansBook;
        
        textFont(this.currentFont);
        textAlign(CENTER, CENTER);

        if(buttonImage != null)
            this.buttonImage = buttonImage;

        this.buttonText = buttonText;
        buttonShape = createShape(GROUP);
    }

    public void toggleButton() {
        this.active = !this.active;
        if(this.active)
            this.currentFont = firaSansExtraBold;
        else
            this.currentFont = firaSansBook;
    }

    public void deactivateButton() {
        this.active = false;
        this.currentFont = firaSansBook;
    }

    public void display() {
        textFont(this.currentFont);
        stroke(255, 255, 255, 0);
        fill(255, 255, 255, 0);
        rect(position.x, position.y, size.x, size.y);
        // mouseClickHandler();
        if (this.active)
            drawBorder();

        displayImage();
        displayText();
    }

    private void displayText() {
        fill(255, 255, 255); //text coloring
        text(this.buttonText, this.position.x + this.size.x / 2, this.position.y + this.size.y - 20);
    }

    private void displayImage() {
        if(this.buttonImage != null)
            image(this.buttonImage, position.x + (size.x - buttonImageSize.x)/2, position.y + (size.y - buttonImageSize.y)/2, this.buttonImageSize.x, this.buttonImageSize.y);
    }

    private void drawBorder() {
        stroke(255, 255, 255);
        strokeWeight(2);
        line(this.position.x, this.position.y + this.size.y, this.position.x + this.size.x, this.position.y + this.size.y);
    }

    public void mouseClickHandler(MouseEvent event) {
        // if(mousePressed) {
            if(event.getX() >= position.x && event.getX() < position.x + size.x) {
                if(event.getY() >= position.y && event.getY() < position.y + size.y) {
                    toggleButton();
                    // delay(100);
                }
            }
        // }
    }

    public PShape getShape(){
        return buttonShape;
    }
    
    public void updateShape(PShape newpattern){
    }

}
public class Flower {
    PVector position;
    float radius = 40;
    float radiusOffest = 10;
    int numberOfStarPoints = 7;
    int numberOfStarPointsOffset = 3;
    int numberOfFlowers = 5;
    float rotationSpeedDegPerSec = 0.5f;
    int p = 0;

    float transparency = 1;

    int alphaValue = 20;

    public float palinNoiseScale = 0.002f;
    public float palinNoiceValue = 0;

    Flower (PVector position, float radius, int numberOfFlowers, float rotationSpeedDegPerSec, float radiusOffest, int numberOfStarPoints, int numberOfStarPointsOffset) {
        this.position = position;
        this.radius = radius;
        this.numberOfFlowers = numberOfFlowers;
        this.rotationSpeedDegPerSec = rotationSpeedDegPerSec;
        this.radiusOffest = radiusOffest;
        this.numberOfStarPoints = numberOfStarPoints;
        this.numberOfStarPointsOffset = numberOfStarPointsOffset;
    }

    public void display(float transparency) {
        this.transparency = transparency;
        pushMatrix();
        translate(position.x, position.y);
        rotate(radians(millis() * rotationSpeedDegPerSec/100));
        drawFlowers(p);
        popMatrix();
        p++;
    }

    public void drawFlowers(int p) {
        for (int i = 0; i < numberOfFlowers; i++) {
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float innerRadius = map(noiseValue, 0, 1, this.radius, this.radius + this.radiusOffest);
            float outerRadius = map(noiseValue, 0, 1, this.radius + this.radiusOffest*2, this.radius + 3*this.radiusOffest);
            int numberOfPoints = (int)map(noiseValue, 0, 1, this.numberOfStarPoints, this.numberOfStarPoints + this.numberOfStarPointsOffset);
            drawFlower(innerRadius, outerRadius, numberOfPoints);
        }
    }

    public void drawFlower(float radius1, float radius2, int npoints) {
        noFill();
        stroke(0xffffffff, transparency * 255);
        pushMatrix();
            // translate(position.x, position.y);
            float angle = TWO_PI / npoints;
            float halfAngle = angle/2.0f;
            beginShape();
            curveVertex(0, 0);
            float a = 0;
            for (a = 0; a < TWO_PI; a += angle) {
                float sx = cos(a) * radius2;
                float sy = sin(a) * radius2;
                curveVertex(sx, sy);
                sx = cos(a+halfAngle) * radius1;
                sy = sin(a+halfAngle) * radius1;
                curveVertex(sx, sy);
            }
            //curveVertex(x + cos(a+halfAngle) * radius1, y + sin(a+halfAngle) * radius1);
            endShape();
        popMatrix();
    }

    public void setAlpha(int newAlphaValue) {
        this.alphaValue = newAlphaValue;
        // this.radius = map(newAlphaValue, 0, 100, 30, 160);
        this.numberOfFlowers = (int)map(newAlphaValue, 0, 100, 5, 30);
        this.rotationSpeedDegPerSec = map(newAlphaValue, 0, 100, 0.3f, 3);
        this.radiusOffest = map(newAlphaValue, 0, 100, 10, 50);
        this.numberOfStarPoints = (int)map(newAlphaValue, 0, 100, 5, 30);
        this.numberOfStarPointsOffset = (int)map(newAlphaValue, 0, 100, 2, 20);
    }

}


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

    public ManualScreen () {
        waves.add(alphaWave);
        waves.add(betaWave);
        waves.add(gammaWave);
        waves.add(tethaWave);
        waves.add(deltaWave);

        PVector alphaSliderPosition = new PVector(120, (height * 3 / 4) - 50);
        alphaSlider = new Slider(alphaSliderPosition, 20, "wave 1", 20, 100, new PVector(width - 160, 0));
        PVector betaSliderPosition = new PVector(120, (height * 3 / 4) );
        betaSlider = new Slider(betaSliderPosition, 20, "Wave 2", 20, 100, new PVector(width - 160, 0));
        PVector gammaSliderPosition = new PVector(120, (height * 3 / 4) + 50);
        gammaSlider = new Slider(gammaSliderPosition, 20, "Wave 3", 20, 100, new PVector(width - 160, 0));
        PVector tethaSliderPosition = new PVector(120, (height * 3 / 4) + 100);
        tethaSlider = new Slider(tethaSliderPosition, 20, "Wave 4", 20, 100, new PVector(width - 160, 0));
        PVector deltaSliderPosition = new PVector(120, (height * 3 / 4) + 150);
        deltaSlider = new Slider(deltaSliderPosition, 20, "Wave 5", 20, 100, new PVector(width - 160, 0));
        
        torus = new Torus(5, 25, 10, new PVector(width /2 , 320)); // 20, 100, 30 are standard
        moon = new Moon(180 , 170, 10, 0.02f, 300, new PVector(width /2 , 320));
        ocean = new Ocean(20, 0.002f, 100, 150, new PVector(width /2 , 320));
        star = new Star(new PVector(width / 2, 400), 180, 15, 10, 4, 10);
        // PVector position, float edgeLength, int angleDivision, int numberOfEdgePoints, float pointSize,  int shapeRepetitionNumber
        flower = new Flower(new PVector(width /2, 320), 170, 5, 0.5f, 10, 7, 3);
        // Flower (PVector position, float radius, int numberOfFlowers, float rotationSpeedDegPerSec, float radiusOffest, int numberOfStarPoints, int numberOfStarPointsOffset) 
    }

    public void display() {
        alphaWave.waveValue = alphaSlider.drawSlider();
        betaWave.waveValue = betaSlider.drawSlider();
        gammaWave.waveValue = gammaSlider.drawSlider();
        tethaWave.waveValue = tethaSlider.drawSlider();
        deltaWave.waveValue = deltaSlider.drawSlider();

        shapeManager();   
    }

    public void shapeManager() {
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

    public void mouseDragHandler(MouseEvent event) {
        // println("I'm dragged");
        deltaSlider.mouseDragged();
        tethaSlider.mouseDragged();
        gammaSlider.mouseDragged();
        betaSlider.mouseDragged();
        alphaSlider.mouseDragged();
    }

    public void mouseMoveHandler() {
        if(mousePressed && (mouseButton == LEFT)) {
            deltaSlider.mouseDragged();
            tethaSlider.mouseDragged();
            gammaSlider.mouseDragged();
            betaSlider.mouseDragged();
            alphaSlider.mouseDragged();
        } 
    }

    public void findDominantWave() {
        
        Collections.sort(waves);
        dominantWave = waves.get(0).waveIndex;

        
        int difference = waves.get(0).waveValue - waves.get(1).waveValue;
        if(difference < 10) {
            waves.get(0).setTransparency(map(difference, 0, 10, 0.5f, 1));
            waves.get(1).setTransparency(map(difference, 0, 10, 0.5f, 0));
        }
    }

    public void mouseClickHandler(MouseEvent event) {
        // backButton.mouseClickHandler(event);
    }

    public void keyHandler(KeyEvent event) {
        if(key == 'i') {
            switchInteraction();
        }
    }

    
    public void switchInteraction() {
        if(interactionEnabled)
            interactionEnabled = false;
        else 
            interactionEnabled = true;
    }

}




public class Moon {
    PVector position;
    public int circleDivisions = 18;
    public float outerCircleRadius = 180; /// the max outer circle radius of the torus
    public float innerCircleRadius = 170;
    public int tethaValue = 20; // again for now between 20 and 100
    public int startColorValue = 0xff3371a3;
    public int endColorValue = 0xffa4bad2;
    public int currentColorValue = startColorValue;
    public int step = 0x1;
    public float palinNoiseScale = 0.002f;
    public float palinNoiceValue = 0;
    int p = 0;
    
    float transparency = 1;

    public Moon () {
        /**
        TODO: other inits necessary
        */
        // step = (endColorValue - startColorValue) / (numberOfCircle * N / 2);
    }

    public Moon(float outerCircleRadius,float innerCircleRadius,int tethaValue,float palinNoiseScale, int circleDivisions, PVector position) {
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;
        this.tethaValue = tethaValue;
        this.palinNoiseScale = palinNoiseScale;
        this.circleDivisions = circleDivisions;
        this.position = position;

    }

    public void updateShape(float transparency) {
        // create the outer circle with points
        this.transparency = transparency;
        pushMatrix();
        translate(this.position.x, this.position.y);
        drawOuterCircle(p);
        drawInnerCircle(p);
        popMatrix();
        p++;
        // create the inner circle with points
        // animate the points so that they move a little bit up and down to and from the center of the circle
    }

    public void drawOuterCircle(int p) {
        int k = 0;
        while(k < circleDivisions) {
            float noiseValue = noise(p * k * palinNoiseScale, p * k * palinNoiseScale);
            float step = (noiseValue - 0.5f) * 10;
            float angle = TWO_PI * k / circleDivisions;
            float x = (outerCircleRadius + step) * cos(angle);
            float y = (outerCircleRadius + step) * sin(angle);
            fill(0xffffffff, transparency * noiseValue * 255);
            stroke(0xffffffff, 0);
            ellipse(x, y, 2, 2);
            k++;
        }
    }

    public void drawInnerCircle(int p) {
        int k = 0;
        while(k < circleDivisions) {
            float noiseValue = noise(p * k * palinNoiseScale, p * k * palinNoiseScale);
            float step = (noiseValue - 0.5f) * 10;
            float angle = TWO_PI * k / circleDivisions;
            float x = (innerCircleRadius + step) * cos(angle);
            float y = (innerCircleRadius + step) * sin(angle);
            fill(0xffffffff, transparency * noiseValue * 255);
            stroke(0xffffffff, 0);
            ellipse(x, y, 2, 2);
            k++;
        }
    }

    // public void increaseDelta() {
    //     // increasing delta would increase the number of circles and increasing 
    //     // the bigCircleRadius and decrease smallCircleRadius and also increase N!
    //     if(numberOfCircle < 100)
    //         numberOfCircle++;
    //     if(bigCircleRadius < 300)
    //         bigCircleRadius+=3;
    //     if(miniCircleRadius > 5)
    //         miniCircleRadius--;
    //     if(N < 300)
    //         N+=10;
    // }

    // public void decreaseDelta() {
    //     // so basically do the opposite of what you do in the increase
    //     if(numberOfCircle > 4)
    //         numberOfCircle--;
    //     if(bigCircleRadius > 10)
    //         bigCircleRadius-=3;
    //     if(miniCircleRadius < 100)
    //         miniCircleRadius++;
    //     if(N > 30)
    //         N-=10;
    // }

    public void setTetha(int newTethaValue) {
        this.tethaValue = newTethaValue;
        circleDivisions = (int)map(newTethaValue, 0, 100, 100, 500); // 0 -> 20 ... 1 up -> 1 up
        outerCircleRadius = map(newTethaValue, 0, 100, 40, 200);
        innerCircleRadius = map(newTethaValue, 0, 100, 30, 190); 
        palinNoiseScale = map(newTethaValue, 0, 100, 0.2f, 0.003f); // 30, 100
    }
}


public class Ocean {
    public PVector position;
    public int xyzSteps = 18;
    public float sphereRadius = 180; /// the max outer circle radius of the torus
    public int gammaValue = 20; // again for now between 20 and 100
    public int startColorValue = 0xff3371a3;
    public int endColorValue = 0xffa4bad2;
    public int currentColorValue = startColorValue;
    public int step = 0x1;
    public float palinNoiseScale = 0.002f;
    public float palinNoiceValue = 0;
    float vibrationStepSize = 10;
    int p = 0;

    float transparency = 1;
    

    PShape my_sphere;
    ArrayList<PVector> vertices = new ArrayList<PVector>();
    
    public Ocean () {
        /**
        TODO: other inits necessary
        */
        // step = (endColorValue - startColorValue) / (numberOfCircle * N / 2);
    }

    public Ocean(int gammaValue,float palinNoiseScale, int xyzSteps, float sphereRadius, PVector position) {
        this.palinNoiseScale = palinNoiseScale;
        this.gammaValue = gammaValue;
        this.palinNoiseScale = palinNoiseScale;
        this.xyzSteps = xyzSteps;
        this.sphereRadius = sphereRadius;
        this.position = position;
        // fill(0, 0, 0);
        my_sphere = createShape(SPHERE, this.sphereRadius);
        sphereDetail(120);
    }

    public void draw_sphere(){
        shape(my_sphere);
        my_sphere.setVisible(true);
    }
    
    public void draw_points(int p){
        getVertices(my_sphere, vertices);
        for(int i=0; i < vertices.size(); i++){
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float step = (noiseValue - 0.5f) * vibrationStepSize;
            PVector vertex = vertices.get(i);
            float x = vertex.x;
            float y = vertex.y;
            float z = vertex.z;
            PVector originalPoint = new PVector(x, y, z);
            originalPoint.add(step, step, step);
            pushMatrix();
            translate(originalPoint.x, originalPoint.y, originalPoint.z);
            noStroke();
            fill((int)(255 * random(p*i)),(int)(255 * random(p*i)), (int)(255 * random(p*i)), transparency * 255);
            // point(x, y, z);
            ellipse(0, 0, 4, 4);
            popMatrix();
        }
    }
    
    public void getVertices(PShape shape, ArrayList<PVector> vertices){
        for(int i = 0 ; i < shape.getVertexCount(); i++){
            PVector vertex = shape.getVertex(i);
            vertices.add(vertex);
        }
    }

    public void updateShape(float transparency) {
        // create the outer circle with points
        // pushMatrix();
        // translate(width/2, height/2);
        // drawSphere(p);
        // popMatrix();
        // p++;
        this.transparency = transparency;
        sphereDetail(120);
        pushMatrix();
        translate(this.position.x, this.position.y);
        // rotateZ(millis() * 0.0001 * TWO_PI);
        // rotateY(millis() * 0.0001 * TWO_PI);
        // draw_sphere();
        draw_points(p);
        vertices.clear();
        popMatrix();
        p++;
        // create the inner circle with points
        // animate the points so that they move a little bit up and down to and from the center of the circle
    }


    public void setGamma(int newGammaValue) {
        this.gammaValue = newGammaValue;
        sphereRadius = map(newGammaValue, 0, 100, 100, 200); // 0 -> 20 ... 1 up -> 1 up
        palinNoiseScale = map(newGammaValue, 0, 100, 0.2f, 0.003f); // 30, 100
        vibrationStepSize = map(newGammaValue, 0, 100, 0, 50); // 30, 100
        my_sphere = createShape(SPHERE, this.sphereRadius);
    }
}


public class Slider {
    PVector s1; // change to - nob position
    PVector sliderPosition;
    PVector sliderSize;
    int nobSize = 20;
    boolean s1Over; // change to isHovered
    float slider1y; // change to yPosition
    int min = 0;
    int max = 100;
    String sliderText = "";
    private int sliderValue = 0;

    PShape sliderShape;
    

    public Slider (PVector sliderPosition, int nobSize, String sliderText, int min, int max, PVector sliderSize) {
        slider1y = sliderPosition.y;
        s1 = new PVector(sliderPosition.x, sliderPosition.y); // s1 = position
        this.sliderPosition = sliderPosition;
        this.nobSize = nobSize;
        this.sliderText = sliderText;
        this.min = min;
        this.max = max;
        this.sliderSize = sliderSize;
    }

    public int drawSlider() {
        ellipseMode(CENTER);
        smooth();
        fill(0xffffffff);
        sliderValue = (int)map(s1.x, sliderPosition.x, sliderPosition.x + sliderSize.x , 0, 100);
        text(sliderText + "  " + sliderValue, sliderPosition.x - textWidth(sliderText) - 50, slider1y + 5);
        stroke(0xffffffff);
        line(sliderPosition.x, slider1y, sliderPosition.x + sliderSize.x, slider1y);

        noStroke();
        fill(0xffffffff, 190);
        ellipse(s1.x, s1.y, nobSize, nobSize);
        fill(0);

        checkHover();
        // vectorLogic();
        vectorLimiter();
        return sliderValue;
    }

    public void setSliderValue(int newSliderValue) {
        this.sliderValue = newSliderValue;
        this.s1.x = map(newSliderValue, 0, 100, this.sliderPosition.x , this.sliderPosition.x + this.sliderSize.x);
    }

    public void checkHover() {
        float distance = this.s1.dist(new PVector(mouseX, mouseY));
        if (distance <= nobSize) {
            s1Over = true;
        } else {
            s1Over = false;
        }

        if (!s1Over) {
            cursor(ARROW);
        } else {
            cursor(HAND);
        }
    }

    public void vectorLimiter() {
        // limiting sliders  
        if (s1.x < 20) {
            s1.x = 20;
        }
        if (s1.x > width-20) {
            s1.x = width-20;
        }
    }

    public void mouseDragged() {
        if (s1Over) {
            s1.x = mouseX;
        } 
    }
}
public class Star {
    PVector position;
    int numberOfEdgePoints = 10;
    float edgeLength = 100;
    int angleDivision = 10;
    float pointSize = 2;
    int shapeRepetitionNumber = 10;

    int betaValue = 20;
    float rotationSpeedDegPerSec = 0.5f;

    float transparency = 1;

    public float palinNoiseScale = 0.002f;
    public float palinNoiceValue = 0;
    float vibrationStepSize = 20;
    int p = 0;

    Star(PVector position, float edgeLength, int angleDivision, int numberOfEdgePoints, float pointSize, int shapeRepetitionNumber) {
        this.position = position;
        this.edgeLength = edgeLength;
        this.angleDivision = angleDivision;
        this.numberOfEdgePoints = numberOfEdgePoints;
        this.pointSize = pointSize;
        this.shapeRepetitionNumber = shapeRepetitionNumber;
    }

    public void display(float transparency) {
        this.transparency = transparency;
        pushMatrix();
        translate(position.x, position.y);
        rotate(radians(millis() * rotationSpeedDegPerSec/100));
        drawStars(p);
        popMatrix();
        p++;
    }

    public void drawStars(int p) {
        for (int i = 0; i < shapeRepetitionNumber; i++) {
            float step =  TWO_PI / angleDivision;
            float angle = i * (PI/10);
            while(angle < TWO_PI + i * (PI/10)) {
                pushMatrix();
                // translate(position.x, position.y);
                scale(0.9f + 0.05f*i);
                rotate(angle);
                drawRoundedTrianlge(p);
                popMatrix();
                angle += step;
            }
        }
    }

    public void drawRoundedTrianlge(int p) {
        // noFill();
        stroke(0xffffffff);
        shapeMode(CENTER);
        beginShape();
        float step = edgeLength / numberOfEdgePoints;
        float x = 0;
        float y = 0;
        int i = 0;
        while(i < numberOfEdgePoints) { // first edge
            // vertex(x , y);
            fill(0xffffffff);
            // ellipse(x, y, pointSize, pointSize);
            x+= step * cos(radians(45));
            y+= step * sin(radians(45));
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x+= step * cos(radians(360-45));
            y+= step * sin(radians(360-45));
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float s = (noiseValue - 0.5f) * vibrationStepSize;
            PVector originalPoint = new PVector(x, y);
            PVector pointWithVibration = originalPoint.add(s, s);
            // vertex(x , y);
            fill((int)(noise(p * i * palinNoiseScale) * 255), (int)(noise(p * i * palinNoiseScale) * 255), (int)(noise(p * i * palinNoiseScale) * 255));
            ellipse(pointWithVibration.x, pointWithVibration.y, pointSize, pointSize);
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x-= step;
            // vertex(x , y);
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float s = (noiseValue - 0.5f) * vibrationStepSize;
            PVector originalPoint = new PVector(x, y);
            PVector pointWithVibration = originalPoint.add(s, s);
            // vertex(x , y);
            fill(0xffffffff, transparency * 255);
            ellipse(pointWithVibration.x, pointWithVibration.y, pointSize, pointSize);
            i++;
        }
        endShape(CLOSE);
    }

    public void setBeta(int newBetaValue) {
        this.betaValue = newBetaValue;
        numberOfEdgePoints = (int)map(newBetaValue, 0, 100, 2, 15); // 0 -> 20 ... 1 up -> 1 up
        // edgeLength = map(newBetaValue, 0, 100, 60, 200); // 0 -> 20 ... 1 up -> 1 up
        // angleDivision = (int)map(newBetaValue, 0, 100, 4, 15); // 0 -> 20 ... 1 up -> 1 up
        // pointSize = map(newBetaValue, 0, 100, 1, 4); // 0 -> 20 ... 1 up -> 1 up
        shapeRepetitionNumber = (int)map(newBetaValue, 0, 100, 5, 10); // 0 -> 20 ... 1 up -> 1 up
        palinNoiseScale = map(newBetaValue, 0, 100, 0.002f, 0.02f); // 30, 100
        vibrationStepSize = map(newBetaValue, 0, 100, 2, 5); // 30, 100
        rotationSpeedDegPerSec = map(newBetaValue, 0, 100, 0.5f, 3); // 30, 100
    }
}


public class Torus {
    PVector position;
    private int numberOfCircle = 20;
    private float bigCircleRadius = 100;
    private float miniCircleRadius = 30;
    private float lastMouseX = 0;
    private float lastMouseY = 0;
    private int deltaValue = 20; // for now between 20 and 100
    private int startColorValue = 0xff3371a3;
    private int endColorValue = 0xffa4bad2;
    private int currentColorValue = startColorValue;
    private int step = 0x1;
    int k=0;
    int N=20; // is the number of points in the big circle's circumference 
    // if the N is high it means the number of mini circles are higher!

    int po = 0;

    public float palinNoiseScale = 0.002f;
    public float palinNoiceValue = 0;

    float transparency = 1;
    
    public Torus (int numberOfCircle, float bigCircleRadius, float miniCircleRadius, PVector position) {
        this.position = position;
        this.numberOfCircle = numberOfCircle;
        this.bigCircleRadius = bigCircleRadius;
        this.miniCircleRadius = miniCircleRadius;
        step = (endColorValue - startColorValue) / (numberOfCircle * N / 2);
    }

    public void setNumberOfCircle(int numberOfCircle) {
        this.numberOfCircle = numberOfCircle;
    }
    public void setBigCircleRadius(float bigCircleRadius) {
        this.bigCircleRadius = bigCircleRadius;
    }
    public void setMiniCircleRadius(float miniCircleRadius) {
        this.miniCircleRadius = miniCircleRadius;
    }

    public int getNumberOfCircle() {
        return this.numberOfCircle;
    }
    public float getBigCircleRadius() {
        return this.bigCircleRadius;
    }
    public float getMiniCircleRadius() {
        return this.miniCircleRadius;
    }

    public void updateShape(boolean isInteractionEnabled, float transparency) {
        this.transparency = transparency;
        if(isInteractionEnabled) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }
        float xx = map(lastMouseX, 0, width, 0, 360);
        float yy = map(lastMouseY, 0, height, 0, 360);
        pushMatrix();
        translate(this.position.x, this.position.y);
        rotateX(radians(-yy));
        rotateY(radians(-xx));
        noFill(); 
        // each loop of this for creates one of the miniCircles!
        for (int i=0; i<N; i++) { 
            float ang1 =i*TWO_PI/N;
            // This part draws a circle with R = 100 and N points in its circumference 
            float x = bigCircleRadius*cos(ang1);
            float y = bigCircleRadius*sin(ang1);

            // This part is for moving the circle  
            pushMatrix();
            translate(x, y, 0);
            rotateX(PI/2);
            rotateY(ang1); 
            circles(k);
            popMatrix();
        }
        popMatrix();
        k+=1;
        po++;
    }

    private void circles(int k) {
        int currentColor = currentColorValue;
        for (int i=0; i<numberOfCircle; i++) {
            currentColor = currentColorValue;
            float ang = i*360/numberOfCircle;
            float x = miniCircleRadius*cos(radians(ang+k));
            float y = miniCircleRadius*sin(radians(ang+k));
            strokeWeight(2);
            if (i%2==0) {
                // currentColorValue += step;
                float noiseValue = noise(po * i * palinNoiseScale);
                fill((int)(noiseValue * 255), (int) (noiseValue * 255), (int) (noiseValue * 255), transparency * 255);
            }
            else fill(-1);
            // point(x, y);
            ellipse(x, y, 5, 5);
        }
    }

    public void increaseDelta() {
        // increasing delta would increase the number of circles and increasing 
        // the bigCircleRadius and decrease smallCircleRadius and also increase N!
        if(numberOfCircle < 100)
            numberOfCircle++;
        if(bigCircleRadius < 300)
            bigCircleRadius+=3;
        if(miniCircleRadius > 5)
            miniCircleRadius--;
        if(N < 300)
            N+=10;
    }

    public void decreaseDelta() {
        // so basically do the opposite of what you do in the increase
        if(numberOfCircle > 4)
            numberOfCircle--;
        if(bigCircleRadius > 10)
            bigCircleRadius-=3;
        if(miniCircleRadius < 100)
            miniCircleRadius++;
        if(N > 30)
            N-=10;
    }

    public void setDelta(int newDeltaValue) {
        this.deltaValue = newDeltaValue;
        numberOfCircle = (int)map(newDeltaValue, 0, 100, 5, 99); // 0 -> 20 ... 1 up -> 1 up
        bigCircleRadius = map(newDeltaValue, 0, 100, 11, 180);
        miniCircleRadius = map(newDeltaValue, 0, 100, 100, 5); 
        N = (int)map(newDeltaValue, 0, 100, 5, 100); // 30, 100
    }

}
class Wave implements Comparable<Wave> {
        int waveValue = 0;
        String waveName = "";
        int waveIndex = 0;
        float waveTransparency = 0;

        Wave(int waveValue ,String waveName , int waveIndex) {
            this.waveValue = waveValue;
            this.waveName = waveName;
            this.waveIndex = waveIndex;
        }

        public void updateWave(int newWaveValue) {
            this.waveValue = newWaveValue;
        }

        public void setTransparency(float newT) {
            this.waveTransparency = newT;
        }

        public int compareTo(Wave anotherWave) {
            return anotherWave.waveValue - this.waveValue;
        }
    }
  public void settings() {  size(768, 1024, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
