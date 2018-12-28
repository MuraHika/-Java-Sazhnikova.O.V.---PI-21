import java.awt.Graphics;
import javax.swing.JList;
import javax.swing.JPanel;

public class GaragePanel extends JPanel {

	private MultiLevelGarage garage;
	private JList listLevels;

	public void setParking(MultiLevelGarage garage) {
		this.garage = garage;
	}

	public void setList(JList listLevels) {
		this.listLevels = listLevels;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			if (garage != null) {
				if (listLevels.getSelectedIndex() != -1) {
					garage.getParking(listLevels.getSelectedIndex()).Draw(g);
				}
			}
		} catch (Exception ex) {
		}
	}
}
