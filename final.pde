Toros toros;

void setup() {
	size(700, 700, P3D);
	background(#525252);
	toros = new Toros(20, 100, 30);
}

void draw() {
	background(#525252);
	toros.updateShape();
}

void keyPressed(){
    if(key == CODED) { 
        if (keyCode == UP) { 
            toros.increaseDelta();
        } 
        else if (keyCode == DOWN) {
            toros.decreaseDelta();
        } 
    }
}
