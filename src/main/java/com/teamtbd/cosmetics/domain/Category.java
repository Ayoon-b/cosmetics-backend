package com.teamtbd.cosmetics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Category {
	SKIN_LOTION(1, "스킨/로션"),
	ESSENCE(2, "에센스"), //
	CREAM(3, "크림"),
	CLEANSING_FOAM(4, "클렌징폼"),
	SUNSCREEN(5, "썬크림"),
	LIP_STICK(6, "립스틱"),
	LIP_BALM(7, "립밤"),
	EYE_LINER(8, "아이라이너"),
	MASCARA(9, "마스카라"),
	HAIR_MIST(10, "헤어미스트");

	private final int code;
	private final String name;

	public static Category valueOf(Integer dbData) {
		return Arrays.stream(values())
			.filter(category -> category.getCode() == dbData)
			.findFirst().orElse(null);
	}

	public static class CategoryConverter implements AttributeConverter<Category, Integer> {
		@Override
		public Integer convertToDatabaseColumn(Category attribute) {
			return attribute.getCode();
		}

		@Override
		public Category convertToEntityAttribute(Integer dbData) {
			return Category.valueOf(dbData);
		}
	}
}
