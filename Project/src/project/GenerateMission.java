package project;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GenerateMission extends TimerTask {

	int delayTime;
	int leftedTime;
	int limitTime = 30;
	int missionNumber;
	int missionCount;
	long missionTime;
	
	Random random = new Random();
	static Timer timer = new Timer();

	Frame_mission missionFrame;
	
//	public static void main(String[] args) {
//		new GenerateMission().run();
//	}
	
	@Override
	public void run() {
		
		delayTime = (random.nextInt(4) * 10000) + 30000; // 30초 ~ 1분에 한번 미션 창 생성
		missionNumber = random.nextInt(3);

        timer.schedule(new GenerateMission(), delayTime);
		missionFrame = new Frame_mission(missionNumber);
		System.out.println("미션 생성");
	
	}

}
