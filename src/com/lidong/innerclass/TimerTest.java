package com.lidong.innerclass;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 
 * @author LiDong
 * 
 * 内部类是定义在一个类中的类，内部类有一下三点用法：
 * 1，内部类方法可以访问该类定义所在的作用域中的数据，包括私有数据
 * 2，内部类可以对同一个包中的其他类隐藏
 * 3，当想要定义一个回调函数且不想编写大量代码时，使用匿名（anonymous)内部类比较便捷
 *
 */

public class TimerTest {

	public static void main(String[] args) {

		TalkingClock clock = new TalkingClock(2000, true);
		clock.start();

		JOptionPane.showMessageDialog(null, "Quit Programe");
		System.exit(0);
	}
}

class TalkingClock {

	private int delay;
	private boolean beep;

	public TalkingClock(int delay, boolean beep) {
		// TODO Auto-generated constructor stub
		this.beep = beep;
		this.delay = delay;
	}

	public void start() {

		ActionListener listener = new TimerPrinter();
		Timer timer = new Timer(delay, listener);
		timer.start();
	}

	class TimerPrinter implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			Date now = new Date();
			System.out.println("At the tone ,the time is " + now);
			if (beep) {
				Toolkit.getDefaultToolkit().beep();
			}
		}
	}

}
