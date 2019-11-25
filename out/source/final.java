import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Final extends PApplet {

Torus torus;
Slider slider;
boolean interactionEnabled = false;
int sliderValue = 20;
PImage img;
PFont firaSansBook;
PFont firaSansExtraBold;
Button autoButton, manualButton, scannerButton, backButton;
Boolean automaticOrManual = true;

Moon moon;

 
public void setup() {
  
  img = loadImage("NVsmall.png");
  background(img);
  // background(#525252);
  torus = new Torus(5, 25, 10); // 20, 100, 30 are standard
  PVector sliderPosition = new PVector(120, height - 50);
  slider = new Slider(sliderPosition, 20, "Manual", 20, 20, new PVector(width - 140, 0));

  moon = new Moon(180 , 170, 10, 0.02f, 300);

  // PVector buttonSize = new PVector(width / 2 - 10, width / 2 - 10);
  // PVector buttonPosition = new PVector(5, (height - buttonSize.y) / 2);
  // PVector manualButtonPosition = new PVector(buttonSize.x + 10, buttonPosition.y);
  // PVector firstButtonImageSize = new PVector(buttonSize.x * 2 / 3, buttonSize.x * 2 / 3);
  // PVector manualButtonImageSize = new PVector(buttonSize.x * 0.8, buttonSize.y * 0.8);
  // PVector backButtonImageSize = new PVector(width/15,height/20);
  // PVector backButtonPosition = new PVector(20, height-40);
  // PVector scannerButtonImageSize = new PVector(1043/8, 560/8);
  // PVector scannerButtonPosition = new PVector((width-scannerButtonImageSize.x)/2, (height-scannerButtonImageSize.y)-40);
  //1043 × 560 scanner

  // PImage museHeadset = loadImage("headset.png", "png");
  // PImage menuSlider = loadImage("slider.png", "png");
  // PImage scanner = loadImage("scanner.png", "png");
  // PImage back = loadImage("back.png", "png");
  // // autoButton = new Button(buttonSize, buttonPosition, museHeadset, firstButtonImageSize, "Automatic", automaticOrManual);
  // manualButton = new Button(buttonSize, manualButtonPosition, menuSlider, manualButtonImageSize, "Manual", !automaticOrManual);
  // scannerButton = new Button(scannerButtonImageSize, scannerButtonPosition, scanner, scannerButtonImageSize, "", !automaticOrManual);
  // backButton = new Button(backButtonImageSize, backButtonPosition, back, backButtonImageSize, "", !automaticOrManual);


  // firaSansBook = createFont("FiraSans-Book.otf", 16);
  // firaSansExtraBold = createFont("FiraSans-ExtraBold.otf", 16);
}

public void draw() {
  background(img);
  // sliderValue = slider.drawSlider();
  // torus.setDelta(sliderValue);
  // torus.updateShape(interactionEnabled);
  // Torus

  moon.updateShape();

  // Moon


  // autoButton.display();
  // manualButton.display();
  // scannerButton.display();
  // backButton.display();
}

public void mouseDragged() {
  // println("I'm dragged");
  slider.mouseDragged();
}

// void mouseMoved() {
//     println(mousePressed);
//   if(mousePressed && (mouseButton == LEFT)) {
//     slider.mouseDragged();
//   } 
// }

public void keyPressed(){
    if(key == CODED) { 
        if (keyCode == UP) { 
            torus.increaseDelta();
        } 
        else if (keyCode == DOWN) {
            torus.decreaseDelta();
        } 
    } else if(key == 'i') {
    switchInteraction();
  }

}

// void mouseClicked(MouseEvent event) {
//   autoButton.mouseClickHandler(event);
// }

public void switchInteraction() {
  if(interactionEnabled)
    interactionEnabled = false;
  else 
    interactionEnabled = true;
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

    // enum Type {
    //     IMAGE,
    //     TEXT
    // }

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


public class Moon {
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
    
    public Moon () {
        /**
        TODO: other inits necessary
        */
        // step = (endColorValue - startColorValue) / (numberOfCircle * N / 2);
    }

    public Moon(float outerCircleRadius,float innerCircleRadius,int tethaValue,float palinNoiseScale, int circleDivisions) {
        this.outerCircleRadius = outerCircleRadius;
        this.innerCircleRadius = innerCircleRadius;
        this.tethaValue = tethaValue;
        this.palinNoiseScale = palinNoiseScale;
        this.circleDivisions = circleDivisions;

    }

    public void updateShape() {
        // create the outer circle with points
        pushMatrix();
        translate(width/2, height/2);
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
            fill(0xffffffff, noiseValue * 255);
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
            fill(0xffffffff, noiseValue * 255);
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

    // public void setDelta(int newDeltaValue) {
    //     this.deltaValue = newDeltaValue;
    //     numberOfCircle = (int)map(newDeltaValue, 0, 100, 5, 99); // 0 -> 20 ... 1 up -> 1 up
    //     bigCircleRadius = map(newDeltaValue, 0, 100, 11, 180);
    //     miniCircleRadius = map(newDeltaValue, 0, 100, 100, 5); 
    //     N = (int)map(newDeltaValue, 0, 100, 5, 100); // 30, 100
    // }
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


public class Torus {
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
    
    public Torus (int numberOfCircle, float bigCircleRadius, float miniCircleRadius) {
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

    public void updateShape(boolean isInteractionEnabled) {
        if(isInteractionEnabled) {
            lastMouseX = mouseX;
            lastMouseY = mouseY;
        }
        float xx = map(lastMouseX, 0, width, 0, 360);
        float yy = map(lastMouseY, 0, height, 0, 360);
        pushMatrix();
        translate(width/2, height/2);
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
                currentColorValue += step;
                stroke(currentColor);
            }
            else stroke(-1);
            point(x, y);
            // ellipse(x, y, 2, 2);
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
  public void settings() {  size(384, 512, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
