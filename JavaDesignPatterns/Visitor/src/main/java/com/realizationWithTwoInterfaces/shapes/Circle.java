package com.realizationWithTwoInterfaces.shapes;

import com.realizationWithTwoInterfaces.visitor.Visitor;

public class Circle extends Dot {                           // Extends Dot method realizations and Dot variable etc.
    private int radius;                                     // Radius of the circle.


    public Circle(int id, int x, int y, int radius) {       // Fully parameterized constructor.
        super(id, x, y);
        this.radius = radius;
    }



    @Override
    public String accept(Visitor visitor) {                 /* Realization of Visitor pattern method. Which gives an ability to
                                                            * work with object through third part class. */
        return visitor.visitCircle(this);                   /* Gives Visitor class explanation which method it has to call in Collection
                                                            * of multiple Shapes. */
    }



    // Getter.
    public int getRadius() {
        return radius;
    }
}
