package projecteuler.problem;

import java.util.Calendar;

import projecteuler.ProblemTemplate;

public class Problem19 extends ProblemTemplate {

	@Override
	public String getTitle() {
		return "Counting Sundays";
	}

	@Override
	public String getResult() {
		return String.valueOf(getNumOfSundaysOnMonthFirst(1901, 2000));
	}

	private int getNumOfSundaysOnMonthFirst(int yearStart, int yearEnd) {
		int sumSunday = 0;
		int dayStart = getDayStart(yearStart);
		for (int i = yearStart; i <= yearEnd; i++) {
			for (int j = Calendar.JANUARY; j <= Calendar.DECEMBER; j++) {
				if (dayStart == Calendar.SUNDAY) {
					sumSunday++;
				}
				int days;
				if (j == Calendar.FEBRUARY) {
					if (isLeapYear(i)) {
						days = 29;
					} else {
						days = 28;
					}
				} else if (j == Calendar.APRIL || j == Calendar.JUNE || j == Calendar.SEPTEMBER || j == Calendar.NOVEMBER) {
					days = 30;
				} else {
					days = 31;
				}
				dayStart = getDayStart(dayStart, days);
			}
		}
		return sumSunday;
	}

	private int getDayStart(int year) {
		if (year < 1900) {
			throw new UnsupportedOperationException();
		}
		int dayStart = Calendar.MONDAY;
		int sumDays = 0;
		for (int i = 1900; i < year; i++) {
			int days = isLeapYear(i) ? 366 : 365;
			sumDays += days;
		}
		return getDayStart(dayStart, sumDays);
	}

	private int getDayStart(int dayStart, int daysDuring) {
		return (dayStart + daysDuring) % Calendar.DAY_OF_WEEK;
	}

	private boolean isLeapYear(int year) {
		if (year % 400 == 0) {
			return true;
		} else if (year % 4 == 0 && year % 100 != 0) {
			return true;
		} else {
			return false;
		}
	}
}
// Tricky way: years * 12 / 7
