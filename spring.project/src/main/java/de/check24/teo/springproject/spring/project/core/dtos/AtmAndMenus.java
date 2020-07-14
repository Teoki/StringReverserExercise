package de.check24.teo.springproject.spring.project.core.dtos;

import com.google.common.collect.ImmutableList;

public class AtmAndMenus {

    public final int atmId;
    public final ImmutableList<Integer> menusId;

    public AtmAndMenus(int atmId, ImmutableList<Integer> menusId) {
        this.atmId = atmId;
        this.menusId = menusId;
    }
}
