package com.realizationWithTwoInterfaces.shapes;

import com.realizationWithTwoInterfaces.visitor.Visitor;

public class Dot implements Shape {                 // Simple figure in our programme.
    private int id;     // Id of the figure.
    private int x;      // X position.
    private int y;      // Y position.


    public Dot() {      // Standard constructor.
    }

    public Dot(int id, int x, int y) {      // Fully parameterized constructor.
        this.id = id;
        this.x = x;
        this.y = y;
    }



    @Override
    public void move(int x, int y) {        // Move method realization.
        // Move shape
    }

    @Override
    public void draw() {                    // Draw method realization.
        // draw shape
    }

    public String accept(Visitor visitor) { /* Realization of Visitor pattern method. Which gives an ability to
                                            * work with object through third part class.*/
        return visitor.visitDot(this);      /* Gives Visitor class explanation which method it has to call in Collection
                                            * of multiple Shapes. */
    }



    // Getters.
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getId() {
        return id;
    }
}
