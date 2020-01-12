package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Unit {
    Unit() {                                    // Standard constructor.

    }
    Unit(Unit[] units) {

    }



    void accept(UnitVisitor unitVisitor) {      /* What will be done if visit will be
                                                * done to Unit instance. */
        System.out.println("Unit instance.");
    }
}
