public class ChooseScreen extends AppScreen {
  PFont firaSansBook;
  PFont firaSansExtraBold;
  Button autoButton, manualButton, scannerButton, backButton;
  Boolean automaticOrManual = true;

  ChooseScreen() {
    PVector buttonSize = new PVector(width / 2 - 10, width / 2 - 10);
    PVector buttonPosition = new PVector(5, (height - buttonSize.y) / 2);
    PVector manualButtonPosition = new PVector(buttonSize.x + 10, buttonPosition.y);
    PVector firstButtonImageSize = new PVector(buttonSize.x * 2 / 3, buttonSize.x * 2 / 3);
    PVector manualButtonImageSize = new PVector(buttonSize.x * 0.8, buttonSize.y * 0.8);
    PVector backButtonImageSize = new PVector(width/15,height/20);
    PVector backButtonPosition = new PVector(20, height-40);
    PVector scannerButtonImageSize = new PVector(1043/8, 560/8);
    PVector scannerButtonPosition = new PVector((width-scannerButtonImageSize.x)/2, (height-scannerButtonImageSize.y)-40);
    //1043 × 560 scanner

    PImage museHeadset = loadImage("headset.png", "png");
    PImage menuSlider = loadImage("slider.png", "png");
    PImage scanner = loadImage("scanner.png", "png");
    PImage back = loadImage("back.png", "png");
    autoButton = new Button(buttonSize, buttonPosition, museHeadset, firstButtonImageSize, "Automatic", automaticOrManual);
    manualButton = new Button(buttonSize, manualButtonPosition, menuSlider, manualButtonImageSize, "Manual", !automaticOrManual);
    scannerButton = new Button(scannerButtonImageSize, scannerButtonPosition, scanner, scannerButtonImageSize, "", !automaticOrManual);
    backButton = new Button(backButtonImageSize, backButtonPosition, back, backButtonImageSize, "", !automaticOrManual);


    firaSansBook = createFont("FiraSans-Book.otf", 16);
    firaSansExtraBold = createFont("FiraSans-ExtraBold.otf", 16);
  }

  void display() {
    autoButton.display();
    manualButton.display();
    scannerButton.display();
    backButton.display();
  }

}
