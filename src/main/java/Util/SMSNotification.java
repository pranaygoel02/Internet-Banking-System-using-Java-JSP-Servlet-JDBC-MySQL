package Util;

public class SMSNotification implements Notification {

	@Override
	public void sendMessage(String to, String from, String message) {
		System.out.println("Email send to " + to + " from " + from + ". Msg: " + message );
	}

}
