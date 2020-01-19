package com.iluwatar.visitors;

import com.iluwatar.units.Commander;
import com.iluwatar.units.Sergeant;
import com.iluwatar.units.Soldier;

public class CommanderVisitor implements UnitVisitor{
    public CommanderVisitor() {                    /* Standard constructor. */

    }



    @Override
    public void visitSoldier(Soldier soldier) {
        System.out.println("Sorry, but I don't " +
                "have permission to work with Soldier.");   /* Implementation for Soldier instance
                                                             * if it will be send to this UnitVisitor
                                                             * implementation.*/
    }

    @Override
    public void visitSergeant(Sergeant sergeant) {
        System.out.println("Sorry, but I don't " +
                "have permission to work with Sergeant.");  /* Implementation for Sergeant instance
                                                             * if it will be send to this UnitVisitor
                                                             * implementation.*/
    }

    @Override
    public void visitCommander(Commander commander) {
        System.out.println("I have visited Commander instance with " +
                "no changes are made in source code, except " +
                "of accept() method.");                     /* In this graph you can use META DATA of
                                                            * argument in any way you want to.*/
    }
}
