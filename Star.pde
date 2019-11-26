public class Star {
    PVector position;
    int numberOfEdgePoints = 10;
    float edgeLength = 100;
    int angleDivision = 10;
    float pointSize = 2;
    int shapeRepetitionNumber = 10;

    Star(PVector position, float edgeLength, int angleDivision, int numberOfEdgePoints, float pointSize, int shapeRepetitionNumber) {
        this.position = position;
        this.edgeLength = edgeLength;
        this.angleDivision = angleDivision;
        this.numberOfEdgePoints = numberOfEdgePoints;
        this.pointSize = pointSize;
        this.shapeRepetitionNumber = shapeRepetitionNumber;
    }

    void display() {
        for (int i = 0; i < shapeRepetitionNumber; i++) {
            float step =  TWO_PI / angleDivision;
            float angle = i * (PI/10);
            while(angle < TWO_PI + i * (PI/10)) {
                pushMatrix();
                translate(position.x, position.y);
                scale(0.9 + 0.05*i);
                rotate(angle);
                drawRoundedTrianlge();
                popMatrix();
                angle += step;
            }
        }
    }

    void drawRoundedTrianlge() {
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
            // vertex(x , y);
            fill(#ffffff);
            ellipse(x, y, pointSize, pointSize);
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x-= step;
            // vertex(x , y);
            fill(#ffffff);
            ellipse(x, y, pointSize, pointSize);
            i++;
        }
        endShape(CLOSE);
    }
}
