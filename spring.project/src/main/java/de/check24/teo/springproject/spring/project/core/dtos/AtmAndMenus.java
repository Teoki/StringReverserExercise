package de.check24.teo.springproject.spring.project.core.dtos;

import com.google.common.collect.ImmutableList;

public class AtmAndMenus {

    public final int atmId;
    public final ImmutableList<Integer> menuIds;

    public AtmAndMenus(int atmId, ImmutableList<Integer> menuIds) {
        this.atmId = atmId;
        this.menuIds = menuIds;
    }
}
