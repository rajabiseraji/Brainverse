import java.util.Vector;


Torus torus;
Moon moon;
Ocean ocean;


Slider slider;
boolean interactionEnabled = false;
int sliderValue = 20;
PImage img;
PFont firaSansBook;
PFont firaSansExtraBold;

int currentScreen = 0; // 0 title screen, 1 choose auto/manual, 2 main app screen manual, 3 main app screen auto
int elapsedTime = 0; 
Vector<AppScreen> appScreens = new Vector<AppScreen>();
void setup() {
  size(768, 1024, P3D);
  img = loadImage("bg.png");
  background(img);
  // background(#525252);
  torus = new Torus(5, 25, 10); // 20, 100, 30 are standard
  PVector sliderPosition = new PVector(120, height - 50);
  slider = new Slider(sliderPosition, 20, "Manual", 20, 20, new PVector(width - 140, 0));

  moon = new Moon(180 , 170, 10, 0.02, 300);
  ocean = new Ocean(20, 0.002, 100, 150);

  // firaSansBook = createFont("FiraSans-Book.otf", 16);
  // firaSansExtraBold = createFont("FiraSans-ExtraBold.otf", 16);
  TitleScreen titleScreen = new TitleScreen();
  ChooseScreen chooseScreen = new ChooseScreen();
  ManualScreen manualScreen = new ManualScreen();
  appScreens.add(titleScreen);
  appScreens.add(chooseScreen);
  appScreens.add(manualScreen);
  appScreens.add(manualScreen);
}

void draw() {
  background(img);

  // screen management
  screenManager();
  appScreens.get(currentScreen).display();

  // sliderValue = slider.drawSlider();
  // torus.setDelta(sliderValue);
  // torus.updateShape(interactionEnabled);
  // Torus
  
  // moon.setTetha(sliderValue);
  // moon.updateShape();
  
  // Moon

  // Ocean
  // ocean.setGamma(sliderValue);
  // ocean.updateShape();

}

void screenManager() {
  if(currentScreen == 0) {
    elapsedTime = millis();
    if(elapsedTime > 10000)
      currentScreen++;
  } else if(currentScreen == 1) {
    ChooseScreen chooseScreen = (ChooseScreen)appScreens.get(1);
    if(chooseScreen.startScanning) {
      elapsedTime = millis();
      if(elapsedTime > 2000)
        currentScreen = chooseScreen.automaticOrManual ? 2 : 3;
      chooseScreen.startScanning = false;
    }
  } else if(currentScreen == 2 || currentScreen == 3) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    if (m.backButton.active) {
      currentScreen = 1;
      m.backButton.active = false;
    }
  }
}


void mouseDragged(MouseEvent event) {
  // println("I'm dragged");
  if (currentScreen == 3 || currentScreen == 2) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.mouseDragHandler(event);
  }
}

void mouseMoved() {
  if(currentScreen == 3 || currentScreen == 2) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.mouseMoveHandler();
  }
}

void keyPressed(KeyEvent event){
  if(currentScreen == 3 || currentScreen == 2) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.keyHandler(event);
  }
}

void changeScreen(int newScreenNumber) {
  currentScreen = newScreenNumber;
}


void mouseClicked(MouseEvent event) {
  if(currentScreen == 1) {
    ChooseScreen chooseScreen = (ChooseScreen)appScreens.get(1);
    chooseScreen.mouseClickHandler(event);
  } else if(currentScreen == 3 || currentScreen == 2) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.mouseClickHandler(event);
  }
}

