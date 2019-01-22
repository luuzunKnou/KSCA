package com.luuzun.ksca.domain;

public enum Permission {
	MASTER(0), MANAGER(1);
	private final int value;

	private Permission(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public static Permission valueOf(int value) {
		switch (value) {
		case 0:
			return Permission.MASTER;
		case 1:
			return Permission.MANAGER;
		default:
			throw new AssertionError("Unknown PERMISSION: " + value);
		}
	}
};