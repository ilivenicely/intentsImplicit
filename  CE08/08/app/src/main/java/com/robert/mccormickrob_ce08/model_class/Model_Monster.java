package com.robert.mccormickrob_ce08.model_class;

import java.io.Serializable;

public class Model_Monster implements Serializable {
    int id;
    String monster_name;
    int monster_fur, monster_leg;

    public Model_Monster() {
    }

    public Model_Monster(String name, int fur, int leg) {
        this.monster_name = name;
        this.monster_fur = fur;
        this.monster_leg = leg;
    }

    public Model_Monster(int id, String name, int fur, int leg) {
        this.id=id;
        this.monster_name = name;
        this.monster_fur = fur;
        this.monster_leg = leg;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMonster_name() {
        return monster_name;
    }

    public void setMonster_name(String monster_name) {
        this.monster_name = monster_name;
    }

    public int getMonster_fur() {
        return this.monster_fur;
    }

    public void setMonster_fur(int monster_fur) {
        this.monster_fur = monster_fur;
    }

    public int getMonster_leg() {
        return monster_leg;
    }

    public void setMonster_leg(int monster_leg) {
        this.monster_leg = monster_leg;
    }
}
