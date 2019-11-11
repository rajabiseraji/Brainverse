int numberOfCircle = 20;
float bigCircleRadius = 100;
float miniCircleRadius = 30;

void setup() {
  size(500, 500, P3D);
}
int k=0, N=50;
void draw() {
  background(#525252); 
  float xx = map(mouseX, 0, width, 0, 360);
  float yy = map(mouseY, 0, height, 0, 360);
  translate(width/2, height/2);
  rotateX(radians(-yy));
  rotateY(radians(-xx));
  noFill(); 
  for (int i=0; i<N; i++) { 
    float ang1 =i*TWO_PI/N;
    // This part draws a circle with R = 100 and N points in its circumference 
    float x = bigCircleRadius*cos(ang1);
    float y = bigCircleRadius*sin(ang1);

    // This part is for moving the circle  
    pushMatrix();
    translate(x, y, 0);
    rotateX(PI/2);
    rotateY(ang1); 
    circles( k);
    popMatrix();
  }
  k+=1;
}

void circles(int k) {
  for (int i=0; i<numberOfCircle; i++) {
    float ang = i*360/numberOfCircle;
    float x = miniCircleRadius*cos(radians(ang+k));
    float y = miniCircleRadius*sin(radians(ang+k));
    strokeWeight(2);
    if (i%2==0)stroke(#FAB800);
    else stroke(-1);
    point(x, y);
  }
}

void increaseDelta() {
    // increasing delta would increase the number of circles and increasing 
    // the bigCircleRadius and decrease smallCircleRadius

}

void decreaseDelta()

void keyPressed(){
  if(key == CODED) { 
    // pts
    if (keyCode == UP) { 
        increaseDelta();
    //   if (bigCircleRadius < 300){
    //     bigCircleRadius++;
    //   } 
    } 
    else if (keyCode == DOWN) {
        decreaseDelta();
    //   if (bigCircleRadius > 10){
    //     bigCircleRadius--;
    //   }
    } 
    // extrusion length
//     if (keyCode == LEFT) { 
//       if (miniCircleRadius > 5){
//         miniCircleRadius--;
//       }
//     } 
//     else if (keyCode == RIGHT) { 
//       if (miniCircleRadius < 100){
//         miniCircleRadius++;
//       }
//     } 
//   }
//   if (key == 'a') {
//       if(numberOfCircle > 4)
//         numberOfCircle--;
//   } else if (key == 'd'){
//       if(numberOfCircle < 100)
//         numberOfCircle++;
//   } else if (key == 'n') {
//       if(N > 5)
//         N--;
//   } else if (key == 'm') {
//       if(N < 200)
//         N++;
//   } 
}
