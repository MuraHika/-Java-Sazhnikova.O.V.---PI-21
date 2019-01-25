package TractorForm;

import java.awt.Color;
import java.awt.Graphics;

public class Tractor extends Vehicle {
	private int tractorWidth = 100;
	private int tractorHeight = 100;

	public Tractor(int maxSpeed, float weight, Color mainColor) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;		
	}
	
	public Tractor(String info) {
		String[] strs = info.split(";");
		if (strs.length == 5) {
			MaxSpeed = Integer.parseInt(strs[0]);
			Weight = Float.parseFloat(strs[1]);
			MainColor = new Color(Integer.parseInt(strs[2]), Integer.parseInt(strs[3]), Integer.parseInt(strs[4]));
		}
	}

	public void MoveTransport(Direction direction) {
		float step = MaxSpeed * 100 / Weight;
		switch (direction) {

		case Right:
			if (_startPosX + step < _ScreenWidth - tractorWidth / 2) {
				_startPosX += step;
			}
			break;

		case Left:
			if (_startPosX - step > tractorWidth / 2) {
				_startPosX -= step;
			}
			break;

		case Up:
			if (_startPosY - step > tractorHeight / 2) {
				_startPosY -= step;
			}
			break;

		case Down:
			if (_startPosY + step < _ScreenHeight - tractorHeight / 2 - 10) {
				_startPosY += step;
			}
			break;
		}
	}

	public void DrawTractor(Graphics g) {

		int PosX = (int) _startPosX;
		int PosY = (int) _startPosY;
		g.setColor(MainColor);
		g.drawRect(PosX - 50, PosY - 30, 3, 30);
		g.fillRect(PosX - 50, PosY - 30, 3, 30);
		g.drawRect(PosX - 47, PosY - 30, 50, 3);
		g.fillRect(PosX - 47, PosY - 30, 50, 3);
		g.drawRect(PosX, PosY - 30, 3, 10);
		g.fillRect(PosX, PosY - 30, 3, 10);

		g.setColor(Color.BLACK);
		g.drawRect(PosX + 1, PosY - 20, 1, 5);
		g.drawRect(PosX + 1, PosY - 15, 7, 1);
		g.drawRect(PosX + 8, PosY - 20, 1, 5);

		g.setColor(MainColor);
		g.fillRect(PosX - 50, PosY - 15, 50, 20);
		g.drawRect(PosX - 50, PosY - 15, 50, 20);
		g.fillRect(PosX - 50, PosY + 5, 60, 30);
		g.drawRect(PosX - 50, PosY + 5, 60, 30);

		g.setColor(Color.BLUE);
		g.fillRect(PosX - 25, PosY - 12, 20, 17);
		g.drawRect(PosX - 25, PosY - 12, 20, 17);

		g.setColor(Color.BLACK);
		g.fillOval(PosX - 50, PosY + 10, 35, 35);
		g.fillOval(PosX - 10, PosY + 20, 25, 25);
	}
	
	public String getInfo() {
		return MaxSpeed + ";" + Weight + ";" + MainColor.getRed() + ";" + MainColor.getGreen() + ";"
				+ MainColor.getBlue();
	}
}
