public class Star {
    PVector position;
    int numberOfEdgePoints = 10;
    float edgeLength = 100;

    Star(PVector position) {
        this.position = position;
    }

    void display() {
        drawRoundedTrianlge();
    }

    void drawRoundedTrianlge() {
        noFill();
        pushMatrix();
            translate(position.x, position.y);
            beginShape();
            curveVertex(position.x, position.y);
            curveVertex(position.x, position.y);
            float step = edgeLength / numberOfEdgePoints;
            float x = 0;
            float y = 0;
            int i = 0;
            while(i < numberOfEdgePoints) { // first edge
                x+= step * cos(radians(45));
                y+= step * sin(radians(45));
                vertex(x , y);
                i++;
            }
            i = 0;
            curveVertex(x, y);
            while(i < numberOfEdgePoints) { // first edge
                x+= step;
                vertex(x , y);
                i++;
            }
            i = 0;
            curveVertex(x, y);
            while(i < numberOfEdgePoints) { // first edge
                x+= step * cos(radians(135));
                y+= step * sin(radians(135));
                vertex(x , y);
                i++;
            }
            curveVertex(x, y);
            curveVertex(x, y);
            endShape(CLOSE);
        popMatrix();
    }
}
