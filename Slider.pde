

public class Slider {
    PVector s1; // change to - nob position
    PVector sliderPosition;
    PVector sliderSize;
    int nobSize = 20;
    boolean s1Over; // change to isHovered
    float slider1y; // change to yPosition
    int min = 0;
    int max = 100;
    String sliderText = "";
    private int sliderValue = 0;

    PShape sliderShape;
    

    public Slider (PVector sliderPosition, int nobSize, String sliderText, int min, int max, PVector sliderSize) {
        slider1y = sliderPosition.y;
        s1 = new PVector(sliderPosition.x, sliderPosition.y); // s1 = position
        this.sliderPosition = sliderPosition;
        this.nobSize = nobSize;
        this.sliderText = sliderText;
        this.min = min;
        this.max = max;
        this.sliderSize = sliderSize;
    }

    public int drawSlider() {
        ellipseMode(CENTER);
        smooth();
        fill(0);
        sliderValue = (int)map(s1.x, sliderPosition.x, sliderPosition.x + sliderSize.x , 0, 100);
        text(sliderText + sliderValue + " ", sliderPosition.x, slider1y - nobSize);
        stroke(0);
        line(sliderPosition.x, slider1y, sliderPosition.x + sliderSize.x, slider1y);

        noStroke();
        ellipse(s1.x, s1.y, nobSize, nobSize);

        checkHover();
        // vectorLogic();
        vectorLimiter();
        return sliderValue;
    }

    void checkHover() {
        float distance = this.s1.dist(new PVector(mouseX, mouseY));
        if (distance <= nobSize) {
            s1Over = true;
        } else {
            s1Over = false;
        }

        if (!s1Over) {
            cursor(ARROW);
        } else {
            cursor(HAND);
        }
    }

    void vectorLimiter() {
        // limiting sliders  
        if (s1.x < 20) {
            s1.x = 20;
        }
        if (s1.x > width-20) {
            s1.x = width-20;
        }
    }

    void mouseDragged() {
        if (s1Over) {
            s1.x = mouseX;
        } 
    }
}



// void vectorLogic() {
//   // relationship
//   if (s1Over && mousePressed) {
//     s3.x = (s1.x + s2.x) / 2;
//   } else if (s2Over && mousePressed) {
//     s3.x = (s1.x + s2.x) / 2;
//   } else if (s3Over && mousePressed) {
//     if(s1.x > s2.x) {
//       s1.x = (s3.x*2) - s2.x;
//         if(s1.x >= width-21){
//           s2.x = (s3.x*2) - s1.x;
//         }
//     }
//     if(s2.x > s1.x) {
//       s2.x = (s3.x*2) - s1.x;
//     }
//     if(s3.x < ((s1.x + s2.x) / 2)){
//       s2.x = s3.x;
//       s1.x = s3.x;
//     }
//     if(s3.x > ((s1.x + s2.x) / 2)){
//       s2.x = s3.x;
//       s1.x = s3.x;
//     }
    
//   }

//   if (s3.x < (s1.x + s2.x) / 2) {
//     s3.x = (s1.x + s2.x) / 2;
//   }
// }
