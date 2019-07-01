package com.palta.BuildRig.Models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Wishlist {

    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    Rig rig;

    public Wishlist(){}

    public Rig getRig() {
        return rig;
    }

    public void setRig(Rig rig) {
        this.rig = rig;
    }
}
