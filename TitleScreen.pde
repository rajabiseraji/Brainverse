public class TitleScreen extends AppScreen {
  PImage titleImage, titleBar;
  PVector titleImageSize, titleBarSize;
  TitleScreen() {
    titleImage = loadImage("NVtitle.png"); 
    titleImageSize = new PVector(titleImage.width, titleImage.height);
    titleBar = loadImage("titlebar.png");
    titleBarSize = new PVector(titleBar.width, titleBar.height);

    titleImage.resize(width * 0.8, (width * 0.8) * (titleImageSize.y / titleImageSize.x));
    titleBar.resize(width * 0.8, (width * 0.8) * (titleBarSize.y / titleBarSize.x));
  }

  void display() {
    image(titleImage, (width - titleImageSize.x) / 2, 0.1 * height);
    image(titleBar, (width - titleImageSize.x) / 2, 0.9 * height);
  }
}
