public class Circle
{
    double radius,x,y; //the three points you need for a triangle

    void setRadius(double r) //method to set the value of radius
    {
        this.radius=r;
    }

    double getRadius() //method to get the value of radius
    {
        return this.radius;
    }


    void setX(double x) //method to set the value of coordinate x
    {
        this.x=x;
    }


    double getX() //method to get the value of coordinate x
    {
        return this.x;
    }


    void setY(double y) //method to set the value of coordinate y
    {
        this.y=y;
    }


    double getY() //method to get the value of coordinate y
    {
        return this.y;
    }


    double getArea() //method to calculate the area of circle
    {
        return Math.PI*radius*radius;
    }


    boolean doesOverlap(Circle othercircle) //method to check whether two circles overlap or not
    {
        double dx,dy;
        dx=othercircle.x-this.x;
        dx=dx*dx;
        dy=othercircle.y-this.y;
        dy=dy*dy;
        double dist=Math.sqrt(dx+dy);
        if((othercircle.radius+this.radius)>=dist)
            return true;
        else
            return false;
    }
}
