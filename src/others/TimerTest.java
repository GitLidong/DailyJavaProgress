package others;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;


public class TimerTest {
	
	public static void main(String[] args) {
		
		TalkingClock clock = new TalkingClock();
		clock.start(2000,true);
		
		JOptionPane.showMessageDialog(null, "Quit Programe");
		System.exit(0);
	}
}

class TalkingClock {
	
	public void start(int delay, final boolean beep) {
		
		ActionListener listener = new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Date now = new Date();
				System.out.println("At the tone ,the time is "+now);
				if (beep) {
					Toolkit.getDefaultToolkit().beep();
				}
			}
		};
		
		Timer timer = new Timer(delay, listener);
		timer.start();
	}
	
}



