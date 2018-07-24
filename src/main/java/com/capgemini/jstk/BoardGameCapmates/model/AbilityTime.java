package com.capgemini.jstk.BoardGameCapmates.model;

import java.util.Arrays;

public class AbilityTime {
	private boolean[][] ability = new boolean[24][60];

	AbilityTime() {
		Arrays.fill(ability, false);
	}

	public void setAbility(int fromHours, int fromMinutes, int toHours, int toMinutes) {
		checkRange(fromHours, fromMinutes, toHours, toMinutes);
		for (int h = fromHours; h < toHours; h++) {
			for (int m = fromMinutes; m < toMinutes; m++) {
				ability[h][m] = true;
			}
		}
	}
	
	public void setUnability(int fromHours, int fromMinutes, int toHours, int toMinutes) {
		checkRange(fromHours, fromMinutes, toHours, toMinutes);
		for (int h = fromHours; h < toHours; h++) {
			for (int m = fromMinutes; m < toMinutes; m++) {
				ability[h][m] = false;
			}
		}
	}
	
	public void clearAbility(){
		Arrays.fill(ability, false);
	}
	
	public boolean[][] getAbility(){
		return this.ability;
	}

	private void checkRange(int fromHours, int fromMinutes, int toHours, int toMinutes) {
		if (fromHours < 0 || fromHours > 23 || toHours < 0 || toHours > 23 || fromMinutes < 0 || fromMinutes > 59
				|| toMinutes < 0 || toMinutes > 59) {
			throw new IllegalArgumentException();
		}
		if (fromHours < toHours) {
			throw new IllegalArgumentException();
		}
		if (fromHours == toHours) {
			if (fromMinutes < toMinutes) {
				throw new IllegalArgumentException();
			}
		}
	}
}
