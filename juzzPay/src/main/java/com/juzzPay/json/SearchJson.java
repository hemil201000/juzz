package com.juzzPay.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.juzzPay.json.FilterInfo.Comparision;
import com.juzzPay.json.FilterInfo.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchJson {

	@JsonProperty("column")
	private String column;
	@JsonProperty("current_val")
	private Object text;
	@JsonProperty("comparision")
	private String comparision = "Equal to";

	public Comparision comparision() {
		switch (comparision) {
			case "Equal to":
				return Comparision.EQ;
			case "Less than equal to":
				return Comparision.LE;
			case "Less than":
				return Comparision.LT;
			case "Greater than equal to":
				return Comparision.GE;
			case "Greater than":
				return Comparision.GT;
			case "Like":
				return Comparision.iLKS_ANYWHERE;
			default:
				return Comparision.NE;
		}
	}

	public Type getType(Class<?> entityClass) {
		String[] split = column.split("\\.");
		for (int i = 0; i < split.length; i++) {
			String s = split[i];
			Field[] declaredFields = entityClass.getDeclaredFields();
			for (Field f : declaredFields) {
				if (f.getName().equals(s)) {
					if (i == split.length - 1) {
						if (f.getType().isAssignableFrom(Integer.class)
								|| f.getType().isAssignableFrom(Float.class)
								|| f.getType().isAssignableFrom(Double.class)) {
							return Type.NUMERIC;
						} else if (f.getType().isAssignableFrom(Long.class) || f.getType().getSimpleName().equals(Long.class.getSimpleName())
								|| f.getType().getSimpleName().equals(long.class.getSimpleName())) {
							return Type.LONG;
						} else if (f.getType().isAssignableFrom(Boolean.class)) {
							return Type.BOOLEAN;
						} else if (f.getType().isAssignableFrom(Date.class)
								|| f.getType().isAssignableFrom(java.sql.Date.class)) {
							return Type.DATE;
						} else if (f.getType().isAssignableFrom(Collection.class)) {
							return Type.LIST;
						} else if (f.getType().isAssignableFrom(String.class)) {
							return Type.STRING;
						} else {
							break;
						}
					} else {
						entityClass = f.getType();
						break;
					}
				}
			}
		}
		return Type.OBJECT;
	}

}
