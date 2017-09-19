/*
Just a trial
*/

void setup() {
 size(640,900);
 fill(200);
}
float theta = 0;
float r = 50;
void draw() {
  background(0);
  stroke(255);
      float x1 = 100;
    float y1 = 100;
  ellipse(x1,y1,2*r,2*r);
  ellipse(x1,880-y1,2*r,2*r);
  ellipse(640-x1,880-y1,2*r,2*r);
  ellipse(640-x1,y1,2*r,2*r);
  stroke(0);
    float x2 = r*cos(radians(theta));
    float y2 = r*sin(radians(theta));
    
    line(x1,y1,x1+x2,y1+y2);
    line(x1,880-y1,x1+x2,880-y1-y2);
    line(640-x1,880-y1,640-x1-x2,880-y1-y2);
    line(640-x1,y1,640-x1-x2,y1+y2);
  
 stroke(204, 102, 0);
     line(x1+x2,y1+y2,x1+x2,880-y1-y2);
     line(x1+x2,y1+y2,640-x1-x2,y1+y2);
     line(640-x1-x2,y1+y2,640-x1-x2,880-y1-y2);
     line(x1+x2,880-y1-y2,640-x1-x2,880-y1-y2); 
     float ar = (640-2*x1-2*x2)*(880-2*y1-2*y2);
  text("Area = "+ ar, 250, 870); 
theta++;
if (theta == 360) theta = 0;

    
}