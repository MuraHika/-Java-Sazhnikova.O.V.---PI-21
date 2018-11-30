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

		int[] PointFX = new int[] { (PosX + 30), (PosX + 30), (PosX + 90) };
		int[] PointFY = new int[] { (PosY - 20), (PosY + 50), (PosY + 50) };
		if (Crane) {

			g.setColor(MainColor);
			g.drawRect(PosX - 50, PosY - 50, 5, 50);
			g.fillRect(PosX - 50, PosY - 50, 5, 50);
			g.drawRect(PosX - 45, PosY - 50, 60, 5);
			g.fillRect(PosX - 45, PosY - 50, 60, 5);
			g.drawRect(PosX + 15, PosY - 50, 5, 20);
			g.fillRect(PosX + 15, PosY - 50, 5, 20);

			g.setColor(Color.BLACK);
			g.drawRect(PosX + 17, PosY - 30, 1, 5);
			g.drawRect(PosX + 11, PosY - 25, 7, 1);
			g.drawRect(PosX + 11, PosY - 30, 1, 5);
		}

		g.setColor(DopColor);
		g.fillRect(PosX + 25, PosY + 20, 6, 10);
		g.drawRect(PosX + 25, PosY + 20, 6, 10);
		g.drawPolygon(PointFX, PointFY, 3);
		g.fillPolygon(PointFX, PointFY, 3);

		g.setColor(MainColor);
		g.fillRect(PosX - 50, PosY - 30, 50, 30);
		g.drawRect(PosX - 50, PosY - 30, 50, 30);
		g.fillRect(PosX - 50, PosY, 75, 40);
		g.drawRect(PosX - 50, PosY, 75, 40);

		g.setColor(GlassColor);
		g.fillRect(PosX - 25, PosY - 25, 20, 25);
		g.drawRect(PosX - 25, PosY - 25, 20, 25);

		g.setColor(DopColor);
		g.fillOval(PosX - 50, PosY + 10, 40, 40);
		g.fillOval(PosX + 5, PosY + 30, 20, 20);
	}
}
