public class Flower {
    PVector position;
    float radius = 40;
    float radiusOffest = 10;
    int numberOfStarPoints = 7;
    int numberOfStarPointsOffset = 3;
    int numberOfFlowers = 5;
    float rotationSpeedDegPerSec = 0.5;
    int p = 0;

    int transparency = 1;

    int alphaValue = 20;

    public float palinNoiseScale = 0.002;
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

    void display() {
        drawFlowers(p);
        p++;
    }

    void drawFlowers(int p) {
        for (int i = 0; i < numberOfFlowers; i++) {
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float innerRadius = map(noiseValue, 0, 1, this.radius, this.radius + this.radiusOffest);
            float outerRadius = map(noiseValue, 0, 1, this.radius + this.radiusOffest*2, this.radius + 3*this.radiusOffest);
            int numberOfPoints = (int)map(noiseValue, 0, 1, this.numberOfStarPoints, this.numberOfStarPoints + this.numberOfStarPointsOffset);
            drawFlower(innerRadius, outerRadius, numberOfPoints);
        }
    }

    void drawFlower(float radius1, float radius2, int npoints) {
        noFill();
        stroke(#ffffff, (int)(transparency * 255));
        pushMatrix();
            translate(position.x, position.y);
            float angle = TWO_PI / npoints;
            float halfAngle = angle/2.0;
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
            endShape(CLOSE);
        popMatrix();
    }

    void setAlpha(int newAlphaValue) {
        this.alphaValue = newAlphaValue;
        // this.radius = map(newAlphaValue, 0, 100, 30, 160);
        this.numberOfFlowers = (int)map(newAlphaValue, 0, 100, 5, 30);
        this.rotationSpeedDegPerSec = map(newAlphaValue, 0, 100, 0.3, 3);
        this.radiusOffest = map(newAlphaValue, 0, 100, 10, 50);
        this.numberOfStarPoints = (int)map(newAlphaValue, 0, 100, 5, 30);
        this.numberOfStarPointsOffset = (int)map(newAlphaValue, 0, 100, 2, 20);
    }

}
