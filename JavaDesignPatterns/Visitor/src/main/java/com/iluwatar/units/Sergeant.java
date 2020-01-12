package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Sergeant extends Unit{
    Sergeant() {                                    // Standard constructor.
        super();
    }
    Sergeant(Unit[] units) {
        super(units);
    }


    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitSergeant(this);            /* When UnitVisitor implementation will be given to
                                                     * this class instance, it will call implemented
                                                     * visitSergeant() method in given UnitVisitor
                                                     * interface using META DATA of this class instance.*/
    }
}
