package com.realizationWithTwoInterfaces.shapes;

import com.realizationWithTwoInterfaces.visitor.Visitor;

public class Rectangle implements Shape {
    private int id;                 // ID.
    private int x;                  // X position.
    private int y;                  // Y position.
    private int width;              // Width of the rectangle.
    private int height;             // Height of the rectangle.


    public Rectangle(int id, int x, int y, int width, int height) {     // Fully parameterized constructor.
        this.id = id;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }



    @Override
    public void move(int x, int y) {                                    // Move method realization.
        // move shape
    }

    @Override
    public void draw() {                                                // Draw method realization.
        // draw shape
    }

    @Override
    public String accept(Visitor visitor) {                             /* Realization of Visitor pattern method. Which gives an ability to
                                                                        * work with object through third part class.*/
        return visitor.visitRectangle(this);                            /* Gives Visitor class explanation which method it has to call in Collection
                                                                        * of multiple Shapes. */
    }



    // Getters.
    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
