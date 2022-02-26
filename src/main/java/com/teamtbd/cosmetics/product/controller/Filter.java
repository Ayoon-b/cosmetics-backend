package com.teamtbd.cosmetics.product.controller;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Filter {
    NAME("name"),
    INGREDIENTS("ingredients"),
    ORIGIN("origin"),
    BRAND("brand");

    public final String columnName;
}
