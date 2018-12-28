import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class Garage<T extends ITransport> {

	private int _placeSizeWidth = 250;
	private int _placeSizeHeight = 80;
	private int ScreenWidth;
	private int ScreenHeight;
	private HashMap<Integer, T> _places;
	private int _maxCount;

	public int getPictureWidth() {
		return ScreenWidth;
	}

	public void setPictureWidth(int pictureWidth) {
		ScreenWidth = pictureWidth;
	}

	public int getPictureHeight() {
		return ScreenHeight;
	}

	public void setPictureHeight(int pictureHeight) {
		ScreenHeight = pictureHeight;
	}

	public Garage(int size, int pictureWidth, int pictureHeight) {
		_maxCount = size;
		_places = new HashMap<Integer, T>();
		this.ScreenWidth = pictureWidth;
		this.ScreenHeight = pictureHeight;
	}

	public int AddTractor(T transport) {
		if (_places.size() == _maxCount) {
			return -1;
		}
		for (int i = 0; i < _maxCount; i++) {
			if (checkFreePlace(i)) {
				_places.add(i, transport);
				_places.get(i).SetPosition(5 + i / 5 * _placeSizeWidth + 5 + 50, i % 5 * _placeSizeHeight + 35,
						ScreenWidth, ScreenHeight);
				return i;
			}
		}
		return -1;
	}

	public T RemoveTractor(int index) {
		if (index < 0 || index > _places.size()) {
			return null;
		}
		if (!checkFreePlace(index)) {
			T car = _places.get(index);
			_places.set(index, null);
			return car;
		}
		return null;
	}

	private boolean checkFreePlace(int index) {
		return !_places.containsKey(index);
	}

	public void Draw(Graphics g) {
		DrawMarking(g);
		for (T transport : _places.values()) {
			transport.DrawTractor(g);
		}
	}

	private void DrawMarking(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, ScreenWidth - 1, ScreenHeight - 1);
		for (int i = 0; i < _maxCount / 6; i++) {
			for (int j = 0; j < 7; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, ScreenHeight);
		}
	}
}
