import java.awt.Graphics;

import javax.swing.JPanel;

public class GaragePanel extends JPanel {

	private Garage<ITransport> garage;

	public void setParking(Garage garage) {
		this.garage = garage;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (garage != null) {
				garage.Draw(g);
			}
		} catch (Exception ex) {
		}
	}
}
