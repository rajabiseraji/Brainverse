PVector s1, s2, s3;
int nobSize = 20;
boolean s1Over, s2Over, s3Over;
int slider1y, slider2y, slider3y;

void setup() {
  size(500, 500);
  background(255);
  rectMode(CENTER);
  smooth();
  fill(0);

  slider1y = height/3;
  slider2y = height/2;
  slider3y = height/3*2;

  s1 = new PVector(width-20, slider1y);
  s2 = new PVector(width-20, slider2y);
  s3 = new PVector(width-20, slider3y);
}

void draw() {
  background(255);

  text("Minimum payment goal: $" + int(map(s1.x, 20, width-20 ,25, 100)), 20, slider1y - nobSize);
  text("Credit utilization goal: " + int(100 - map(s2.x, 20, width-20 ,0, 70)) + "%", 20, slider2y - nobSize);
  text("Projected line of credit: $" + int(map(s3.x, 20, width-20 ,300, 700)), 20, slider3y - nobSize);
  stroke(0);
  line(20, slider1y, width-20, slider1y);
  line(20, slider2y, width-20, slider2y);  
  line(20, slider3y, width-20, slider3y);

  noStroke();
  rect(s1.x, s1.y, nobSize, nobSize);
  rect(s2.x, s2.y, nobSize, nobSize);
  rect(s3.x, s3.y, nobSize, nobSize);

  checkHover();
  vectorLogic();
  vectorLimiter();
}

void mouseDragged() {
  if (s1Over) {
    s1.x = mouseX;
  } else if (s2Over) {
    s2.x = mouseX;
  } else if (s3Over) {
    s3.x = mouseX;
  }
}

void checkHover() {
  if (mouseX > s1.x - nobSize &&
    mouseX < s1.x + nobSize &&
    mouseY > s1.y - nobSize &&
    mouseY < s1.y + nobSize) {
    s1Over = true;
  } else {
    s1Over = false;
  }
  if (mouseX > s2.x - nobSize &&
    mouseX < s2.x + nobSize &&
    mouseY > s2.y - nobSize &&
    mouseY < s2.y + nobSize) {
    s2Over = true;
  } else {
    s2Over = false;
  }
  if (mouseX > s3.x - nobSize &&
    mouseX < s3.x + nobSize &&
    mouseY > s3.y - nobSize &&
    mouseY < s3.y + nobSize) {
    s3Over = true;
  } else {
    s3Over = false;
  }

  if (!s1Over && !s2Over && !s3Over) {
    cursor(ARROW);
  } else {
    cursor(HAND);
  }
}

void vectorLogic() {
  // relationship
  if (s1Over && mousePressed) {
    s3.x = (s1.x + s2.x) / 2;
  } else if (s2Over && mousePressed) {
    s3.x = (s1.x + s2.x) / 2;
  } else if (s3Over && mousePressed) {
    if(s1.x > s2.x) {
      s1.x = (s3.x*2) - s2.x;
        if(s1.x >= width-21){
          s2.x = (s3.x*2) - s1.x;
        }
    }
    if(s2.x > s1.x) {
      s2.x = (s3.x*2) - s1.x;
    }
    if(s3.x < ((s1.x + s2.x) / 2)){
      s2.x = s3.x;
      s1.x = s3.x;
    }
    if(s3.x > ((s1.x + s2.x) / 2)){
      s2.x = s3.x;
      s1.x = s3.x;
    }
    
  }

  if (s3.x < (s1.x + s2.x) / 2) {
    s3.x = (s1.x + s2.x) / 2;
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

  if (s2.x < 20) {
    s2.x = 20;
  }
  if (s2.x > width-20) {
    s2.x = width-20;
  }

  if (s3.x < 20) {
    s3.x = 20;
  }
  if (s3.x > width-20) {
    s3.x = width-20;
  }
}