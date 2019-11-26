

public class Moon {
    public int circleDivisions = 18;
    public float outerCircleRadius = 180; /// the max outer circle radius of the torus
    public float innerCircleRadius = 170;
    public int tethaValue = 20; // again for now between 20 and 100
    public int startColorValue = #3371a3;
    public int endColorValue = #a4bad2;
    public int currentColorValue = startColorValue;
    public int step = 0x1;
    public float palinNoiseScale = 0.002;
    public float palinNoiceValue = 0;
    int p = 0;
    
    float transparency = 1;

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

    void updateShape(float transparency) {
        // create the outer circle with points
        this.transparency = transparency;
        pushMatrix();
        translate(width/2, height/2);
        drawOuterCircle(p);
        drawInnerCircle(p);
        popMatrix();
        p++;
        // create the inner circle with points
        // animate the points so that they move a little bit up and down to and from the center of the circle
    }

    void drawOuterCircle(int p) {
        int k = 0;
        while(k < circleDivisions) {
            float noiseValue = noise(p * k * palinNoiseScale, p * k * palinNoiseScale);
            float step = (noiseValue - 0.5) * 10;
            float angle = TWO_PI * k / circleDivisions;
            float x = (outerCircleRadius + step) * cos(angle);
            float y = (outerCircleRadius + step) * sin(angle);
            fill(#ffffff, transparency * noiseValue);
            stroke(#ffffff, 0);
            ellipse(x, y, 2, 2);
            k++;
        }
    }

    void drawInnerCircle(int p) {
        int k = 0;
        while(k < circleDivisions) {
            float noiseValue = noise(p * k * palinNoiseScale, p * k * palinNoiseScale);
            float step = (noiseValue - 0.5) * 10;
            float angle = TWO_PI * k / circleDivisions;
            float x = (innerCircleRadius + step) * cos(angle);
            float y = (innerCircleRadius + step) * sin(angle);
            fill(#ffffff, transparency * noiseValue);
            stroke(#ffffff, 0);
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
        palinNoiseScale = map(newTethaValue, 0, 100, 0.2, 0.003); // 30, 100
    }
}
