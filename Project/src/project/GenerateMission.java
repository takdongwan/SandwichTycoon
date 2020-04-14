package project;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class GenerateMission extends TimerTask {

	int missionNumber;
	long missionTime;
	
	Random random = new Random();
	static Timer timer = new Timer();

	Frame_mission missionFrame;
	
//	public static void main(String[] args) {
//		new GenerateMission().run();
//	}
	
	@Override
	public void run() {
		
		missionTime = (random.nextInt(4) * 10000) + 60000; // 1분 ~ 1분 30초에 한번 미션 창 생성
		missionNumber = random.nextInt(3);

        timer.schedule(new GenerateMission(), missionTime);
		missionFrame = new Frame_mission(missionNumber);
		System.out.println("미션 생성");		
	}

}
