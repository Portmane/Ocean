package com.iluwatar.units;

import com.iluwatar.visitors.UnitVisitor;

public class Soldier extends Unit{
    public Soldier() {                                     // Standard constructor.

    }
    public Soldier(Unit[] units) {
        super(units);
    }


    @Override
    public void accept(UnitVisitor unitVisitor) {
        unitVisitor.visitSoldier(this);             /* When UnitVisitor implementation will be given to
                                                     * this class instance, it will call implemented
                                                     * visitSoldier() method in given UnitVisitor
                                                     * interface using META DATA of this class instance.*/
    }
}
