package com.teamtbd.cosmetics.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.AttributeConverter;
import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum Site {
	OLIVEYOUNG(1, "올리브영"),
	THEFACESHOP(2, "더페이스샵"),
	INNISFREE(3, "이니스프리"),
	SKINFOOD(4, "스킨푸드"),
	NUNC(5, "눙크"),
	NATURE_REPUBLIC(6, "네이처리퍼블릭");

	private final int code;
	private final String value;

	public static Site valueOf(Integer dbData) {
		return Arrays.stream(values())
			.filter(site -> site.getCode() == dbData)
			.findFirst().orElse(null);
	}

	public static class SiteConverter implements AttributeConverter<Site, Integer> {
		@Override
		public Integer convertToDatabaseColumn(Site attribute) {
			return attribute.getCode();
		}

		@Override
		public Site convertToEntityAttribute(Integer dbData) {
			return Site.valueOf(dbData);
		}
	}
}
