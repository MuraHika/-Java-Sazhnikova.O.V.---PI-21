package TractorForm;

import java.awt.Graphics;

import javax.swing.JPanel;

public class DrawPanel extends JPanel {

	private ITransport transport;

	public void addTractor(ITransport transport) {
		this.transport = transport;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (transport != null)
			transport.DrawTractor(g);
	}
}
