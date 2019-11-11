Toros toros;
Slider slider;
boolean interactionEnabled = false;
int sliderValue = 20;
PImage img;

void setup() {
  size(768, 1024, P3D);
  img = loadImage("bg.png");
  background(img);
  // background(#525252);
  toros = new Toros(20, 100, 30);
  PVector sliderPosition = new PVector(120, height - 50);
  slider = new Slider(sliderPosition, 20, "Delta", 20, 20, new PVector(width - 140, 0));
}

void draw() {
  background(img);
  sliderValue = slider.drawSlider();
  toros.setDelta(sliderValue);
  toros.updateShape(interactionEnabled);
}

void mouseDragged() {
  slider.mouseDragged();
}

void keyPressed(){
    if(key == CODED) { 
        if (keyCode == UP) { 
            toros.increaseDelta();
        } 
        else if (keyCode == DOWN) {
            toros.decreaseDelta();
        } 
    } else if(key == 'i') {
    switchInteraction();
  }

}

void switchInteraction() {
  if(interactionEnabled)
    interactionEnabled = false;
  else 
    interactionEnabled = true;
}
