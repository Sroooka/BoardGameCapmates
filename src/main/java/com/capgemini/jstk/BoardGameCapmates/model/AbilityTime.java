package com.capgemini.jstk.BoardGameCapmates.model;

import java.time.LocalTime;
import java.util.Arrays;

public class AbilityTime {
	private LocalTime[][] ability;
	private int numberOfDaysAvaliable;

	public AbilityTime() {
		ability = new LocalTime[7][2];
		Arrays.fill(ability, null);
		numberOfDaysAvaliable = 0;
	}

	public void setAbility(int dayOfWeek, int fromHours, int fromMinutes, int toHours, int toMinutes) {
		checkRange(dayOfWeek, fromHours, fromMinutes, toHours, toMinutes);
		ability[dayOfWeek - 1][0] = LocalTime.of(fromHours, fromMinutes);
		ability[dayOfWeek - 1][1] = LocalTime.of(toHours, toMinutes);
		numberOfDaysAvaliable++;
	}

	public void clearAbility() {
		Arrays.fill(ability, null);
		numberOfDaysAvaliable = 0;
	}

	public void clearAbility(int dayOfWeek) {
		Arrays.fill(ability[dayOfWeek - 1], null);
		numberOfDaysAvaliable--;
	}

	public LocalTime[][] getAbility() {
		return this.ability;
	}

	private void checkRange(int dayOfWeek, int fromHours, int fromMinutes, int toHours, int toMinutes) {
		if (fromHours < 0 || fromHours > 23 || toHours < 0 || toHours > 23 || fromMinutes < 0 || fromMinutes > 59
				|| toMinutes < 0 || toMinutes > 59 || dayOfWeek < 1 || dayOfWeek > 7) {
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

	public AbilityTime matchAbilityTime(AbilityTime opponentTime) {
		AbilityTime matchedAbility = new AbilityTime();
		
		if (this.ability == null || opponentTime.getAbility() == null) {
			throw new NullPointerException();
		}

		for (int i = 0; i < 7; i++) {
			LocalTime opponentBeginTime = opponentTime.getAbility()[i][0];
			LocalTime opponentEndTime = opponentTime.getAbility()[i][1];
			LocalTime myBeginTime = this.ability[i][0];
			LocalTime myEndTime = this.ability[i][1];
			if (opponentBeginTime == null || opponentEndTime == null
					|| myBeginTime == null || myEndTime == null ||
					opponentBeginTime.isAfter(myEndTime) ||
					myBeginTime.isAfter(opponentEndTime)) {
				continue;
			}
			if(opponentBeginTime.isAfter(myBeginTime)){
				matchedAbility.setAbility(i, opponentBeginTime.getHour(), opponentBeginTime.getMinute(), myEndTime.getHour(), myEndTime.getMinute());
			}else{
				matchedAbility.setAbility(i, myBeginTime.getHour(), myBeginTime.getMinute(), opponentEndTime.getHour(), opponentEndTime.getMinute());
			}
			
		}

		return matchedAbility;
	}

	public int getNumberOfDaysAvaliable() {
		return numberOfDaysAvaliable;
	}
	
	
}
