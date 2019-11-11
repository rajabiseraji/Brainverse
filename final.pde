Toros toros;
Slider slider;

void setup() {
	size(700, 700, P3D);
	background(#525252);
	toros = new Toros(20, 100, 30);
	PVector sliderPosition = new PVector(width - 20, height - 50);
	slider = new Slider(sliderPosition, 20, "Delta", 20, 20);
}

void draw() {
	background(#525252);
	toros.updateShape();
	slider.drawSlider();
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
