import java.lang.Math;

float[] mass = [];
var positionX = [];
var positionY = [];
var velocityX = [];
var velocityY = [];

/////////////////////////////////////////////////////////////////////////////////////////////////////

void setup() {
	size(width, height);
	noStroke();
	fill(64, 255, 255, 192);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////

void draw() {
	background(32);
	
	for (int particleA = 0; particleA < mass.length; particleA++) {
		float accelerationX = 0, accelerationY = 0;
		
		for (int particleB = 0; particleB < mass.length; particleB++) {
			if (particleA != particleB) {
				float distanceX = positionX[particleB] - positionX[particleA];
				float distanceY = positionY[particleB] - positionY[particleA];

				float distance = sqrt(distanceX * distanceX + distanceY * distanceY);
				if (distance < 1) distance = 1;

				float force = (distance - 320) * mass[particleB] / distance;
				accelerationX += force * distanceX;
				accelerationY += force * distanceY;
			}
		}
		
		velocityX[particleA] = velocityX[particleA] * 0.99 + accelerationX * mass[particleA];
		velocityY[particleA] = velocityY[particleA] * 0.99 + accelerationY * mass[particleA];
	}
	
	for (int particle = 0; particle < mass.length; particle++) {
		positionX[particle] += velocityX[particle];
		positionY[particle] += velocityY[particle];
		
		ellipse(positionX[particle], positionY[particle], mass[particle] * 1000, mass[particle] * 1000);
	}
}

/////////////////////////////////////////////////////////////////////////////////////////////////////

void addNewParticle() {
	mass.push(random(0.003, 0.03));
	positionX.push(mouseX);
	positionY.push(mouseY);
	velocityX.push(0);
	velocityY.push(0);
}

/////////////////////////////////////////////////////////////////////////////////////////////////////

function mouseClicked() {
	addNewParticle();
}

/////////////////////////////////////////////////////////////////////////////////////////////////////

function mouseDragged() {
	addNewParticle();
}