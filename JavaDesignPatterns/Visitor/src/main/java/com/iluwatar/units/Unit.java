package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Unit {
    public Unit() {                                    // Standard constructor.

    }
    public Unit(Unit[] units) {

    }



    public void accept(UnitVisitor unitVisitor) {      /* What will be done if visit will be
                                                * done to Unit instance. */
        System.out.println("Unit instance.");
    }
}
