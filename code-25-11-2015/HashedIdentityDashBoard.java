package practiceRoom;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map.Entry;

public class HashedIdentityDashBoard {

	public static void main(String[] args) {
		HashMap<String, DashBoard> hm = new HashMap<String, DashBoard>();
		IdentityHashMap<DashBoard, String> idenHM = new IdentityHashMap<DashBoard, String>();

		hm.put("PendingTasks", new PendingTasks());
		hm.put("DailyTasks", new DailyTasks());
		hm.put("EscalatedTasks", new EscalatedTasks());
		// and 100 more repetitive tasks

		// identity map for repetitive tasks and their Templates and Query Forms
		idenHM.put(hm.get("PendingTasks"), "40 Templates and Query form of PendingTasks");
		idenHM.put(hm.get("DailyTasks"), "40 Templates and Query form of DailyTasks");
		idenHM.put(hm.get("EscalatedTasks"), "40 Templates and Query form of EscalatedTasks");

		// only for 1000 users we need 1000*100*40 = 4000000 Objects

		for (Entry<DashBoard, String> entry : idenHM.entrySet()) {
			DashBoard dashBoard = entry.getKey();
			System.out.println("Key : " + dashBoard + " : Value : " + idenHM.get(dashBoard));
		}

	}

}

interface DashBoard {

	public void setTitle(String widgetTitle);

}

abstract class AbstractUserDashBoard implements DashBoard {
	public String dashBoardName;

	abstract public void setTitle(String widgetTitle);

	@Override
	public String toString() {
		return "Title of DashBoard is :" + this.dashBoardName + " and hashCode is " + this.hashCode();
	}
}

class DailyTasks extends AbstractUserDashBoard {

	public DailyTasks() {
		setTitle("DailyTasks");
		System.out.println(this);
	}

	@Override
	public void setTitle(String widgetTitle) {
		this.dashBoardName = widgetTitle;
	}
}

class PendingTasks extends AbstractUserDashBoard {
	public PendingTasks() {
		setTitle("PendingTasks");
		System.out.println(this);
	}

	@Override
	public void setTitle(String widgetTitle) {
		this.dashBoardName = widgetTitle;
	}

}

class EscalatedTasks extends AbstractUserDashBoard {
	public EscalatedTasks() {
		setTitle("EscalatedTasks");
		System.out.println(this);
	}

	@Override
	public void setTitle(String widgetTitle) {
		this.dashBoardName = widgetTitle;
	}

}
