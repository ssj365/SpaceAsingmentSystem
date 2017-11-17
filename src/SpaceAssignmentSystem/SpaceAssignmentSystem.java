package SpaceAssignmentSystem;

import SpaceAssignmentSystem.clientWindow;
// Some comments here, to test git hub
public class SpaceAssignmentSystem {
	public static void main(String[] args) {
		// scheduler the rendering event.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				clientWindow.renderClientWindow();
			}
		});
	}
}
