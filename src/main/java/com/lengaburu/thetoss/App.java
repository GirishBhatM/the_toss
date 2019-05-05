package com.lengaburu.thetoss;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class App {

	private static final String BOWLS = "Bowls";

	private static final String BATS = "Bats";

	private static final String CLOUDY = "CLOUDY";

	private static final String CLEAR = "CLEAR";

	private static final String DAY = "DAY";

	private static final String NIGHT = "NIGHT";

	private static final String[] TEAMS = new String[] { "Lengaburu", "Enchai" };

	private static final Map<String, WeatherRow> WEATHER_TABLE = new HashMap<>();

	private static final Map<String, DayNightRow> DAY_NIGHT_TABLE = new HashMap<>();

	private App() {
		WEATHER_TABLE.put(TEAMS[0], new WeatherRow(TEAMS[0], BATS, BOWLS));
		WEATHER_TABLE.put(TEAMS[1], new WeatherRow(TEAMS[1], BOWLS, BATS));

		DAY_NIGHT_TABLE.put(TEAMS[0], new DayNightRow(TEAMS[0], BATS, BOWLS));
		DAY_NIGHT_TABLE.put(TEAMS[1], new DayNightRow(TEAMS[1], BOWLS, BATS));

	}

	public void predict(String weather, String dayNight) {
		String tossWinner = selectTossWinner();
		WeatherRow weatherRow = WEATHER_TABLE.get(tossWinner);
		DayNightRow dayNightRow = DAY_NIGHT_TABLE.get(tossWinner);

		String choiceByWeather = choiceByWeather(weather, weatherRow);
		String choiceByDayNight = choiceByDayNight(dayNight, dayNightRow);

		String choice = BATS;
		if (choiceByWeather.equalsIgnoreCase(choiceByDayNight)) {
			choice = choiceByWeather;
		}

		System.out.println(tossWinner + " wins toss and " + choice.toLowerCase());
	}

	private String selectTossWinner() {
		// since both the team has equally probability
		Random random = new Random(System.currentTimeMillis());
		int team = random.nextInt(2);
		return TEAMS[team];
	}

	private String choiceByWeather(String weather, WeatherRow row) {
		if (CLOUDY.equalsIgnoreCase(weather)) {
			return row.getCloudy();
		}
		return row.getClear();
	}

	private String choiceByDayNight(String dayNight, DayNightRow row) {
		if (DAY.equalsIgnoreCase(dayNight)) {
			return row.getDay();
		}
		return row.getNight();
	}

	private static interface Row {
		String getId();
	}

	private static class WeatherRow implements Row {
		private String id;
		private String clear;
		private String cloudy;

		public WeatherRow(String id, String clear, String cloudy) {
			this.id = id;
			this.clear = clear;
			this.cloudy = cloudy;
		}

		public String getId() {
			return this.id;
		}

		public String getClear() {
			return clear;
		}

		public String getCloudy() {
			return cloudy;
		}

	}

	private static class DayNightRow implements Row {
		private String id;
		private String day;
		private String night;

		public DayNightRow(String id, String day, String night) {
			this.id = id;
			this.day = day;
			this.night = night;
		}

		public String getDay() {
			return day;
		}

		public String getNight() {
			return night;
		}

		public String getId() {
			return id;
		}

	}

	public static void main(String[] args) {
		String weather=args[0];
		String dayNight=args[1];
		new App().predict(weather,dayNight);
	}

}
