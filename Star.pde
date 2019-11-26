public class Star {
    PVector position;
    int numberOfEdgePoints = 10;
    float edgeLength = 100;
    int angleDivision = 10;
    float pointSize = 2;
    int shapeRepetitionNumber = 10;

    int betaValue = 20;
    float rotationSpeedDegPerSec = 0.5;

    float transparency = 1;

    public float palinNoiseScale = 0.002;
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

    void display(float transparency) {
        this.transparency = transparency;
        pushMatrix();
        translate(position.x, position.y);
        rotate(radians(millis() * rotationSpeedDegPerSec/100));
        drawStars(p);
        popMatrix();
        p++;
    }

    void drawStars(int p) {
        for (int i = 0; i < shapeRepetitionNumber; i++) {
            float step =  TWO_PI / angleDivision;
            float angle = i * (PI/10);
            while(angle < TWO_PI + i * (PI/10)) {
                pushMatrix();
                // translate(position.x, position.y);
                scale(0.9 + 0.05*i);
                rotate(angle);
                drawRoundedTrianlge(p);
                popMatrix();
                angle += step;
            }
        }
    }

    void drawRoundedTrianlge(int p) {
        // noFill();
        stroke(#ffffff);
        shapeMode(CENTER);
        beginShape();
        float step = edgeLength / numberOfEdgePoints;
        float x = 0;
        float y = 0;
        int i = 0;
        while(i < numberOfEdgePoints) { // first edge
            // vertex(x , y);
            fill(#ffffff);
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
            float s = (noiseValue - 0.5) * vibrationStepSize;
            PVector originalPoint = new PVector(x, y);
            PVector pointWithVibration = originalPoint.add(s, s);
            // vertex(x , y);
            fill(#ffffff, transparency);
            ellipse(pointWithVibration.x, pointWithVibration.y, pointSize, pointSize);
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x-= step;
            // vertex(x , y);
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float s = (noiseValue - 0.5) * vibrationStepSize;
            PVector originalPoint = new PVector(x, y);
            PVector pointWithVibration = originalPoint.add(s, s);
            // vertex(x , y);
            fill(#ffffff, transparency);
            ellipse(pointWithVibration.x, pointWithVibration.y, pointSize, pointSize);
            i++;
        }
        endShape(CLOSE);
    }

    void setBeta(int newBetaValue) {
        this.betaValue = newBetaValue;
        numberOfEdgePoints = (int)map(newBetaValue, 0, 100, 2, 15); // 0 -> 20 ... 1 up -> 1 up
        // edgeLength = map(newBetaValue, 0, 100, 60, 200); // 0 -> 20 ... 1 up -> 1 up
        // angleDivision = (int)map(newBetaValue, 0, 100, 4, 15); // 0 -> 20 ... 1 up -> 1 up
        // pointSize = map(newBetaValue, 0, 100, 1, 4); // 0 -> 20 ... 1 up -> 1 up
        shapeRepetitionNumber = (int)map(newBetaValue, 0, 100, 5, 10); // 0 -> 20 ... 1 up -> 1 up
        palinNoiseScale = map(newBetaValue, 0, 100, 0.002, 0.02); // 30, 100
        vibrationStepSize = map(newBetaValue, 0, 100, 2, 5); // 30, 100
        rotationSpeedDegPerSec = map(newBetaValue, 0, 100, 0.5, 3); // 30, 100
    }
}
