import java.util.Vector;


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
  ManualScreen manualScreen = new ManualScreen();
  appScreens.add(manualScreen);
}

void draw() {
  background(img);
  appScreens.get(currentScreen).display();
}

void mouseDragged(MouseEvent event) {
    ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
    m.mouseDragHandler(event);
}

// fallback for stupid MACs :))
void mouseMoved() {
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.mouseMoveHandler();
}

void keyPressed(KeyEvent event){
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.keyHandler(event);
}

void changeScreen(int newScreenNumber) {
  currentScreen = newScreenNumber;
}


void mouseClicked(MouseEvent event) {
  ManualScreen m = (ManualScreen)appScreens.get(currentScreen);
  m.mouseClickHandler(event);
}

