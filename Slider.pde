

public class Slider {
    PVector s1; // change to position
    int nobSize = 20;
    boolean s1Over; // change to isHovered
    int slider1y; // change to yPosition
    int min = 0;
    int max = 100;
    String sliderText = "";

    PShape sliderShape;
    

    public Slider (PVector position, int nobSize, String sliderText, int min, int max) {
        slider1y = height/3;
        s1 = new PVector(width-20, slider1y); // s1 = position
        this.nobSize = nobSize;
        this.sliderText = sliderText;
        this.min = min;
        this.max = max;
    }

    public void drawSlider() {
        ellipseMode(CENTER);
        smooth();
        fill(0);

        text(sliderText + int(map(s1.x, 20, width-20 ,25, 100)), 20, slider1y - nobSize);
        stroke(0);
        line(20, slider1y, width-20, slider1y);

        noStroke();
        ellipse(s1.x, s1.y, nobSize, nobSize);

        checkHover();
        // vectorLogic();
        vectorLimiter();
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
