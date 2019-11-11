

public class Toros {
    private int numberOfCircle = 20;
    private float bigCircleRadius = 100;
    private float miniCircleRadius = 30;
    private float lastMouseX = 0;
    private float lastMouseY = 0;
    int k=0;
    int N=50;
    
    public Toros (int numberOfCircle, float bigCircleRadius, float miniCircleRadius) {
        this.numberOfCircle = numberOfCircle;
        this.bigCircleRadius = bigCircleRadius;
        this.miniCircleRadius = miniCircleRadius;
    }

    void setNumberOfCircle(int numberOfCircle) {
        this.numberOfCircle = numberOfCircle;
    }
    void setBigCircleRadius(float bigCircleRadius) {
        this.bigCircleRadius = bigCircleRadius;
    }
    void setMiniCircleRadius(float miniCircleRadius) {
        this.miniCircleRadius = miniCircleRadius;
    }

    int getNumberOfCircle() {
        return this.numberOfCircle;
    }
    float getBigCircleRadius() {
        return this.bigCircleRadius;
    }
    float getMiniCircleRadius() {
        return this.miniCircleRadius;
    }

    void mouseDragged() {

    }

    void updateShape(boolean isInteractionEnabled) {
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
        for (int i=0; i<numberOfCircle; i++) {
            float ang = i*360/numberOfCircle;
            float x = miniCircleRadius*cos(radians(ang+k));
            float y = miniCircleRadius*sin(radians(ang+k));
            strokeWeight(2);
            if (i%2==0)stroke(#FAB800);
            else stroke(-1);
            point(x, y);
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
}

