package com.test.schedule;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestSchedule {

	public static void main(String[] args) {
		ScheduledExecutorService schedule = Executors.newSingleThreadScheduledExecutor();
//		schedule.schedule(new Task1(), 90, TimeUnit.SECONDS);
//		schedule.schedule(new Task2(), 80, TimeUnit.SECONDS);
		schedule.scheduleAtFixedRate(new Task1(), 0, 90, TimeUnit.SECONDS);
		schedule.scheduleAtFixedRate(new Task2(), 0, 80, TimeUnit.SECONDS);
	}

}
