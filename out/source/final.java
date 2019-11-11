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

public class final extends PApplet {

Toros toros;

public void setup() {
	
	background(0xff525252);
	toros = new Toros(20, 100, 30);
}

public void draw() {
	toros.updateShape();
}

public void keyPressed(){
    if(key == CODED) { 
        if (keyCode == UP) { 
            toros.increaseDelta();
        } 
        else if (keyCode == DOWN) {
            toros.decreaseDelta();
        } 
    }
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
PVector s1, s2, s3;
int nobSize = 20;
boolean s1Over, s2Over, s3Over;
int slider1y, slider2y, slider3y;

public void setup() {
  size(500, 500);
  background(255);
  rectMode(CENTER);
  smooth();
  fill(0);

  slider1y = height/3;
  slider2y = height/2;
  slider3y = height/3*2;

  s1 = new PVector(width-20, slider1y);
  s2 = new PVector(width-20, slider2y);
  s3 = new PVector(width-20, slider3y);
}

public void draw() {
  background(255);

  text("Minimum payment goal: $" + PApplet.parseInt(map(s1.x, 20, width-20 ,25, 100)), 20, slider1y - nobSize);
  text("Credit utilization goal: " + PApplet.parseInt(100 - map(s2.x, 20, width-20 ,0, 70)) + "%", 20, slider2y - nobSize);
  text("Projected line of credit: $" + PApplet.parseInt(map(s3.x, 20, width-20 ,300, 700)), 20, slider3y - nobSize);
  stroke(0);
  line(20, slider1y, width-20, slider1y);
  line(20, slider2y, width-20, slider2y);  
  line(20, slider3y, width-20, slider3y);

  noStroke();
  rect(s1.x, s1.y, nobSize, nobSize);
  rect(s2.x, s2.y, nobSize, nobSize);
  rect(s3.x, s3.y, nobSize, nobSize);

  checkHover();
  vectorLogic();
  vectorLimiter();
}

public void mouseDragged() {
  if (s1Over) {
    s1.x = mouseX;
  } else if (s2Over) {
    s2.x = mouseX;
  } else if (s3Over) {
    s3.x = mouseX;
  }
}

public void checkHover() {
  if (mouseX > s1.x - nobSize &&
    mouseX < s1.x + nobSize &&
    mouseY > s1.y - nobSize &&
    mouseY < s1.y + nobSize) {
    s1Over = true;
  } else {
    s1Over = false;
  }
  if (mouseX > s2.x - nobSize &&
    mouseX < s2.x + nobSize &&
    mouseY > s2.y - nobSize &&
    mouseY < s2.y + nobSize) {
    s2Over = true;
  } else {
    s2Over = false;
  }
  if (mouseX > s3.x - nobSize &&
    mouseX < s3.x + nobSize &&
    mouseY > s3.y - nobSize &&
    mouseY < s3.y + nobSize) {
    s3Over = true;
  } else {
    s3Over = false;
  }

  if (!s1Over && !s2Over && !s3Over) {
    cursor(ARROW);
  } else {
    cursor(HAND);
  }
}

public void vectorLogic() {
  // relationship
  if (s1Over && mousePressed) {
    s3.x = (s1.x + s2.x) / 2;
  } else if (s2Over && mousePressed) {
    s3.x = (s1.x + s2.x) / 2;
  } else if (s3Over && mousePressed) {
    if(s1.x > s2.x) {
      s1.x = (s3.x*2) - s2.x;
        if(s1.x >= width-21){
          s2.x = (s3.x*2) - s1.x;
        }
    }
    if(s2.x > s1.x) {
      s2.x = (s3.x*2) - s1.x;
    }
    if(s3.x < ((s1.x + s2.x) / 2)){
      s2.x = s3.x;
      s1.x = s3.x;
    }
    if(s3.x > ((s1.x + s2.x) / 2)){
      s2.x = s3.x;
      s1.x = s3.x;
    }
    
  }

  if (s3.x < (s1.x + s2.x) / 2) {
    s3.x = (s1.x + s2.x) / 2;
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

  if (s2.x < 20) {
    s2.x = 20;
  }
  if (s2.x > width-20) {
    s2.x = width-20;
  }

  if (s3.x < 20) {
    s3.x = 20;
  }
  if (s3.x > width-20) {
    s3.x = width-20;
  }
}


public class Toros {
    private int numberOfCircle = 20;
    private float bigCircleRadius = 100;
    private float miniCircleRadius = 30;
    int k=0;
    int N=50;
    
    public Toros (int numberOfCircle, float bigCircleRadius, float miniCircleRadius) {
        this.numberOfCircle = numberOfCircle;
        this.bigCircleRadius = bigCircleRadius;
        this.miniCircleRadius = miniCircleRadius;
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

    public void updateShape() {
        float xx = map(mouseX, 0, width, 0, 360);
        float yy = map(mouseY, 0, height, 0, 360);
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
        k+=1;
    }

    private void circles(int k) {
        for (int i=0; i<numberOfCircle; i++) {
            float ang = i*360/numberOfCircle;
            float x = miniCircleRadius*cos(radians(ang+k));
            float y = miniCircleRadius*sin(radians(ang+k));
            strokeWeight(2);
            if (i%2==0)stroke(0xffFAB800);
            else stroke(-1);
            point(x, y);
        }
    }

    public void increaseDelta() {
        // increasing delta would increase the number of circles and increasing 
        // the bigCircleRadius and decrease smallCircleRadius and also increase N!
        if(numberOfCircle < 100)
            numberOfCircle++;
        if(bigCircleRadius < 500)
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
            bigCircleRadius--;
        if(miniCircleRadius < 100)
            miniCircleRadius++;
        if(N > 30)
            N-=10;
    }
}

  public void settings() { 	size(700, 700, P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "final" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
