public class Star {
    PVector position;
    int numberOfEdgePoints = 10;
    float edgeLength = 100;

    Star(PVector position) {
        this.position = position;
    }

    void display() {
        int k = 10;
        float step =  TWO_PI / k;
        float angle = 0;
        while(angle < TWO_PI) {
            pushMatrix();
            translate(position.x, position.y);
            rotate(angle);
            drawRoundedTrianlge();
            popMatrix();
            angle += step;
        }
    }

    void drawRoundedTrianlge() {
        noFill();
        stroke(#ffffff);
        shapeMode(CENTER);
        beginShape();
        float step = edgeLength / numberOfEdgePoints;
        float x = 0;
        float y = 0;
        int i = 0;
        while(i < numberOfEdgePoints) { // first edge
            vertex(x , y);
            // fill(#ffffff);
            // ellipse(x, y, 3, 3);
            x+= step * cos(radians(45));
            y+= step * sin(radians(45));
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x+= step * cos(radians(360-45));
            y+= step * sin(radians(360-45));
            vertex(x , y);
            // fill(#ffffff);
            // ellipse(x, y, 3, 3);
            i++;
        }
        i = 0;
        while(i < numberOfEdgePoints) { // first edge
            x-= step;
            vertex(x , y);
            // fill(#ffffff);
            // ellipse(x, y, 3, 3);
            i++;
        }
        endShape(CLOSE);
    }
}
