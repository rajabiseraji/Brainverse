public class BrainAtom {
    private float mass = 0;
    private float currentSpeed = 0;
    private float maxSpeed = 0;
    private PVector center;
    private float distanceToCentre = 0;
    private color atomColor = "ffffff";

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
