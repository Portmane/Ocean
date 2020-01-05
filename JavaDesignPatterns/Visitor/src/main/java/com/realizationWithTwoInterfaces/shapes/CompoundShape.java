package com.realizationWithTwoInterfaces.shapes;

import com.realizationWithTwoInterfaces.visitor.Visitor;

import java.util.ArrayList;
import java.util.List;

public class CompoundShape implements Shape {
    public int id;                                          // ID.
    public List<Shape> children = new ArrayList<>();        // List of all compound shapes.


    public CompoundShape(int id) {                          // Fully parameterized constructor.
        this.id = id;
    }



    @Override
    public void move(int x, int y) {                        // Move method realization.
        // move shape
    }

    @Override
    public void draw() {                                    // Draw method realization.
        // draw shape
    }

    @Override
    public String accept(Visitor visitor) {                 /* Realization of Visitor pattern method. Which gives an ability to
                                                            * work with object through third part class.*/
        return visitor.visitCompoundGraphic(this);      /* Gives Visitor class explanation which method it has to call in Collection
                                                            * of multiple Shapes. */
    }

    public void add(Shape shape) {                          // Adds new compound shape.
        children.add(shape);
    }



    public int getId() {
        return id;
    }                       // Getter.
}
