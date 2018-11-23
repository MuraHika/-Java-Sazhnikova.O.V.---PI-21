package TractorForm;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private Tractor tractor;

	public void addTractor(Tractor tractor) {
		this.tractor = tractor;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (tractor != null)
			tractor.DrawTractor(g);
	}
}
