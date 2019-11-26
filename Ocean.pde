

public class Ocean {
    public int xyzSteps = 18;
    public float sphereRadius = 180; /// the max outer circle radius of the torus
    public int gammaValue = 20; // again for now between 20 and 100
    public int startColorValue = #3371a3;
    public int endColorValue = #a4bad2;
    public int currentColorValue = startColorValue;
    public int step = 0x1;
    public float palinNoiseScale = 0.002;
    public float palinNoiceValue = 0;
    float vibrationStepSize = 10;
    int p = 0;

    int transparency = 1;
    

    PShape my_sphere;
    ArrayList<PVector> vertices = new ArrayList<PVector>();
    
    public Ocean () {
        /**
        TODO: other inits necessary
        */
        // step = (endColorValue - startColorValue) / (numberOfCircle * N / 2);
    }

    public Ocean(int gammaValue,float palinNoiseScale, int xyzSteps, float sphereRadius) {
        this.palinNoiseScale = palinNoiseScale;
        this.gammaValue = gammaValue;
        this.palinNoiseScale = palinNoiseScale;
        this.xyzSteps = xyzSteps;
        this.sphereRadius = sphereRadius;
        // fill(0, 0, 0);
        my_sphere = createShape(SPHERE, this.sphereRadius);
        sphereDetail(120);
    }

    void draw_sphere(){
        shape(my_sphere);
        my_sphere.setVisible(true);
    }
    
    void draw_points(int p){
        getVertices(my_sphere, vertices);
        for(int i=0; i < vertices.size(); i++){
            float noiseValue = noise(p * i * palinNoiseScale, p * i * palinNoiseScale);
            float step = (noiseValue - 0.5) * vibrationStepSize;
            PVector vertex = vertices.get(i);
            float x = vertex.x;
            float y = vertex.y;
            float z = vertex.z;
            PVector originalPoint = new PVector(x, y, z);
            originalPoint.add(step, step, step);
            pushMatrix();
            translate(originalPoint.x, originalPoint.y, originalPoint.z);
            noStroke();
            fill((int)(255 * noiseValue),(int)(255 * noiseValue), (int)(255 * noiseValue), (int)(transparency * 255));
            // point(x, y, z);
            ellipse(0, 0, 4, 4);
            popMatrix();
        }
    }
    
    void getVertices(PShape shape, ArrayList<PVector> vertices){
        for(int i = 0 ; i < shape.getVertexCount(); i++){
            PVector vertex = shape.getVertex(i);
            vertices.add(vertex);
        }
    }

    void updateShape() {
        // create the outer circle with points
        // pushMatrix();
        // translate(width/2, height/2);
        // drawSphere(p);
        // popMatrix();
        // p++;
        sphereDetail(120);
        pushMatrix();
        translate(width/2, height/2);
        // rotateZ(millis() * 0.0001 * TWO_PI);
        // rotateY(millis() * 0.0001 * TWO_PI);
        // draw_sphere();
        draw_points(p);
        vertices.clear();
        popMatrix();
        p++;
        // create the inner circle with points
        // animate the points so that they move a little bit up and down to and from the center of the circle
    }


    public void setGamma(int newGammaValue) {
        this.gammaValue = newGammaValue;
        sphereRadius = map(newGammaValue, 0, 100, 100, 200); // 0 -> 20 ... 1 up -> 1 up
        palinNoiseScale = map(newGammaValue, 0, 100, 0.2, 0.003); // 30, 100
        vibrationStepSize = map(newGammaValue, 0, 100, 0, 50); // 30, 100
        my_sphere = createShape(SPHERE, this.sphereRadius);
    }
}
