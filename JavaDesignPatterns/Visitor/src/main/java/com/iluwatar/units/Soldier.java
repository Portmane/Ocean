package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Soldier extends Unit{
    Soldier() {                                     // Standard constructor.

    }
    Soldier(Unit[] units) {
        super(units);
    }


    @Override
    void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitSoldier(this);             /* When UnitVisitor implementation will be given to
                                                     * this class instance, it will call implemented
                                                     * visitSoldier() method in given UnitVisitor
                                                     * interface using META DATA of this class instance.*/
    }
}
