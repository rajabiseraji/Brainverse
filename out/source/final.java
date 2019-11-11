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

Toros toros;
Slider slider;
boolean interactionEnabled = false;
int sliderValue = 20;
PImage img;

public void setup() {
  
  img = loadImage("bg.png");
  background(img);
  // background(#525252);
  toros = new Toros(20, 100, 30);
  PVector sliderPosition = new PVector(120, height - 50);
  slider = new Slider(sliderPosition, 20, "Delta", 20, 20, new PVector(width - 140, 0));
}

public void draw() {
  background(img);
  sliderValue = slider.drawSlider();
  toros.setDelta(sliderValue);
  toros.updateShape(interactionEnabled);
}

public void mouseDragged() {
  slider.mouseDragged();
}

public void keyPressed(){
    if(key == CODED) { 
        if (keyCode == UP) { 
            toros.increaseDelta();
        } 
        else if (keyCode == DOWN) {
            toros.decreaseDelta();
        } 
    } else if(key == 'i') {
    switchInteraction();
  }

}

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
    private float distanceToCentre = 0;
    private int atomColor = "ffffff";

    public BrainAtom (float mass, float currentSpeed, float maxSpeed, PVector center, float distanceToCentre) {
        this.mass = mass;
        this.currentSpeed = currentSpeed;
        this.maxSpeed = maxSpeed;
        this.center = center;
        this.distanceToCentre = distanceToCentre;
        this.atomColor = atomColor;
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

    // getters
    public float getmass() {
        return this.mass;
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






}
interface Displayable {
    public PShape getShape();
    public void setShape(PShape newShape);
}
// import java.lang.Math;

// float mass = [];
// var positionX = [];
// var positionY = [];
// var velocityX = [];
// var velocityY = [];

// /////////////////////////////////////////////////////////////////////////////////////////////////////

// void setup() {
// 	size(width, height);
// 	noStroke();
// 	fill(64, 255, 255, 192);
// }

// /////////////////////////////////////////////////////////////////////////////////////////////////////

// void draw() {
// 	background(32);
	
// 	for (int particleA = 0; particleA < mass.length; particleA++) {
// 		float accelerationX = 0, accelerationY = 0;
		
// 		for (int particleB = 0; particleB < mass.length; particleB++) {
// 			if (particleA != particleB) {
// 				float distanceX = positionX[particleB] - positionX[particleA];
// 				float distanceY = positionY[particleB] - positionY[particleA];

// 				float distance = sqrt(distanceX * distanceX + distanceY * distanceY);
// 				if (distance < 1) distance = 1;

// 				float force = (distance - 320) * mass[particleB] / distance;
// 				accelerationX += force * distanceX;
// 				accelerationY += force * distanceY;
// 			}
// 		}
		
// 		velocityX[particleA] = velocityX[particleA] * 0.99 + accelerationX * mass[particleA];
// 		velocityY[particleA] = velocityY[particleA] * 0.99 + accelerationY * mass[particleA];
// 	}
	
// 	for (int particle = 0; particle < mass.length; particle++) {
// 		positionX[particle] += velocityX[particle];
// 		positionY[particle] += velocityY[particle];
		
// 		ellipse(positionX[particle], positionY[particle], mass[particle] * 1000, mass[particle] * 1000);
// 	}
// }

// /////////////////////////////////////////////////////////////////////////////////////////////////////

// void addNewParticle() {
// 	mass.push(random(0.003, 0.03));
// 	positionX.push(mouseX);
// 	positionY.push(mouseY);
// 	velocityX.push(0);
// 	velocityY.push(0);
// }

// /////////////////////////////////////////////////////////////////////////////////////////////////////

// function mouseClicked() {
// 	addNewParticle();
// }

// /////////////////////////////////////////////////////////////////////////////////////////////////////

// function mouseDragged() {
// 	addNewParticle();
// }
// public class ShapeManager {

//     public ShapeManager (arguments) {
        
//     }

// }


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


public class Toros {
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
    int N=50;
    
    public Toros (int numberOfCircle, float bigCircleRadius, float miniCircleRadius) {
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
        bigCircleRadius = map(newDeltaValue, 0, 100, 11, 299);
        miniCircleRadius = map(newDeltaValue, 0, 100, 100, 5); 
        N = (int)map(newDeltaValue, 0, 100, 30, 300);
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
