public class Button {

    PVector position;
    PVector size;
    PVector buttonImageSize;
    PImage buttonImage;
    boolean active = false;
    String buttonText;

    PShape buttonShape;

    private PFont firaSansBook;
    private PFont firaSansExtraBold;
    private PFont currentFont;
    
    public Button (PVector size, PVector position, PImage buttonImage, PVector buttonImageSize, String buttonText, boolean active) { // image button 
        this.size = size;
        this.position = position;
        this.active = active;
        this.buttonImageSize = buttonImageSize;

        this.firaSansBook = createFont("FiraSans-Book.otf", 15);
        this.firaSansExtraBold = createFont("FiraSans-ExtraBold.otf", 15);
        this.currentFont = firaSansBook;
        
        textFont(this.currentFont);
        textAlign(CENTER, CENTER);

        if(buttonImage != null)
            this.buttonImage = buttonImage;

        this.buttonText = buttonText;
        buttonShape = createShape(GROUP);
    }

    void toggleButton() {
        this.active = !this.active;
        if(this.active)
            this.currentFont = firaSansExtraBold;
        else
            this.currentFont = firaSansBook;
    }

    void deactivateButton() {
        this.active = false;
        this.currentFont = firaSansBook;
    }

    void display() {
        textFont(this.currentFont);
        stroke(255, 255, 255, 0);
        fill(255, 255, 255, 0);
        rect(position.x, position.y, size.x, size.y);
        // mouseClickHandler();
        if (this.active)
            drawBorder();

        displayImage();
        displayText();
    }

    private void displayText() {
        fill(255, 255, 255); //text coloring
        text(this.buttonText, this.position.x + this.size.x / 2, this.position.y + this.size.y - 20);
    }

    private void displayImage() {
        if(this.buttonImage != null)
            image(this.buttonImage, position.x + (size.x - buttonImageSize.x)/2, position.y + (size.y - buttonImageSize.y)/2, this.buttonImageSize.x, this.buttonImageSize.y);
    }

    private void drawBorder() {
        stroke(255, 255, 255);
        strokeWeight(2);
        line(this.position.x, this.position.y + this.size.y, this.position.x + this.size.x, this.position.y + this.size.y);
    }

    void mouseClickHandler(MouseEvent event) {
        // if(mousePressed) {
            if(event.getX() >= position.x && event.getX() < position.x + size.x) {
                if(event.getY() >= position.y && event.getY() < position.y + size.y) {
                    toggleButton();
                    // delay(100);
                }
            }
        // }
    }

    PShape getShape(){
        return buttonShape;
    }
    
    void updateShape(PShape newpattern){
    }

}
