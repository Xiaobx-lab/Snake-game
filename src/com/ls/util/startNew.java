package com.ls.util;

import com.ls.view.LoginFrame;
import com.ls.view.registerFrame;

public class startNew {
	public static void stratLogin(registerFrame tFrame) {
		tFrame.dispose();
		LoginFrame login = new LoginFrame();
		login.init();
	}

	public static void startLogin(LoginFrame rFrame) {
		rFrame.dispose();
		registerFrame register = new registerFrame();
		register.init();
	}
}
