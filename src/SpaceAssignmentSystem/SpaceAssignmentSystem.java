package SpaceAssignmentSystem;

import SpaceAssignmentSystem.guiBuilder;

public class SpaceAssignmentSystem {
	public static void main(String[] args) {
		// scheduler the rendering event.
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				guiBuilder.renderGUI();
			}
		});
	}
}
