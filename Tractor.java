package TractorForm;

import java.awt.Color;
import java.awt.Graphics;

public class Tractor {
	private float _startPosX;
	private float _startPosY;

	private int _ScreenWidth;
	private int _ScreenHeight;

	private int tractorWidth = 100;
	private int tractorHeight = 100;

	public boolean Crane;

	public int MaxSpeed;

	public float Weight;

	public Color MainColor;
	public Color DopColor;
	public Color GlassColor;

	public Tractor(int maxSpeed, float weight, Color mainColor, Color dopColor, Color glassColor, boolean crane) {
		MaxSpeed = maxSpeed;
		Weight = weight;
		MainColor = mainColor;
		DopColor = dopColor;
		GlassColor = glassColor;
		Crane = crane;
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_ScreenHeight = height;
		_ScreenWidth = width;
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
