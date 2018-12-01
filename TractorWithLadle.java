package TractorForm;

import java.awt.Color;
import java.awt.Graphics;

public class TractorWithLadle extends Tractor {

	public Color DopColor;
	public Color GlassColor;
	public boolean Crane;

	public Color getDopColor() {
		return DopColor;
	}
 
	public Color getGlassColor() {
		return GlassColor;
	}

	public boolean getCrane() {
		return Crane;
	}

	public TractorWithLadle(int maxSpeed, int weight, Color mainColor, Color dopColor, Color glassColor,
			boolean crane) {
		super(maxSpeed, weight, mainColor);
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		GlassColor = glassColor;
		Crane = crane;
	}

	public void DrawTractor(Graphics g) {

		int PosX = (int) _startPosX;
		int PosY = (int) _startPosY;

		int[] PointFX = new int[] { (PosX + 15), (PosX + 15), (PosX + 60) };
		int[] PointFY = new int[] { (PosY - 12), (PosY + 38), (PosY + 38) };

		if (Crane) {
			g.setColor(MainColor);
			g.drawRect(PosX - 50, PosY - 30, 3, 30);
			g.fillRect(PosX - 50, PosY - 30, 3, 30);
			g.drawRect(PosX - 47, PosY - 30, 50, 3);
			g.fillRect(PosX - 47, PosY - 30, 50, 3);
			g.drawRect(PosX, PosY - 30, 3, 10);
			g.fillRect(PosX, PosY - 30, 3, 10);

			g.setColor(DopColor);
			g.drawRect(PosX + 1, PosY - 20, 1, 5);
			g.drawRect(PosX + 1, PosY - 15, 7, 1);
			g.drawRect(PosX + 8, PosY - 20, 1, 5);
		}
		g.setColor(DopColor);
		g.fillRect(PosX + 25, PosY + 20, 6, 10);
		g.drawRect(PosX + 25, PosY + 20, 6, 10);
		g.drawPolygon(PointFX, PointFY, 3);
		g.fillPolygon(PointFX, PointFY, 3);

		g.setColor(MainColor);
		g.fillRect(PosX - 50, PosY - 15, 50, 20);
		g.drawRect(PosX - 50, PosY - 15, 50, 20);
		g.fillRect(PosX - 50, PosY + 5, 60, 30);
		g.drawRect(PosX - 50, PosY + 5, 60, 30);

		g.setColor(GlassColor);
		g.fillRect(PosX - 25, PosY - 12, 20, 17);
		g.drawRect(PosX - 25, PosY - 12, 20, 17);

		g.setColor(DopColor);
		g.fillOval(PosX - 50, PosY + 10, 35, 35);
		g.fillOval(PosX - 10, PosY + 20, 25, 25);
	}
}
