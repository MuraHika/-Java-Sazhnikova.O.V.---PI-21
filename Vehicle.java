package TractorForm;

import java.awt.Color;
import java.awt.Graphics;

public abstract class Vehicle implements ITransport {

	protected float _startPosX;
	protected float _startPosY;
	protected int _ScreenWidth;
	protected int _ScreenHeight;
	public int MaxSpeed;
	public float Weight;
	public Color MainColor;
 
	public int getMaxSpeed() {
		return MaxSpeed;
	}

	public float getWeight() {
		return Weight;
	}

	public Color getMainColor() {
		return MainColor;
	}

	public void SetPosition(int x, int y, int width, int height) {
		_startPosX = x;
		_startPosY = y;
		_ScreenWidth = width;
		_ScreenHeight = height;
	}

	public void SwitchColor(Graphics g) {
		MainColor = Color.cyan;
	}

	public abstract void DrawTractor(Graphics g);

	public abstract void MoveTransport(Direction direction);
}
