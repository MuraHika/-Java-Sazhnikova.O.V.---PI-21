import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;

public class Garage<T extends ITransport> implements Serializable, Comparable<Garage<T>>, Iterable<T>, Iterator<T> {

	private int _placeSizeWidth = 250;
	private int _placeSizeHeight = 80;
	private int ScreenWidth;
	private int ScreenHeight;
	private HashMap<Integer, T> _places;
	private int _maxCount;
	private int currentIndex;

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

	public int AddTractor(T transport) throws GarageOverflowException, GarageOccupiedPlaceException, GarageAlreadyHaveException {
		if (_places.size() == _maxCount) {
			throw new GarageOverflowException();
		}
		int index = _places.size();
		for (int i = 0; i <= _places.size(); i++) {
			if (checkFreePlace(i)) {
				index = i;
			}
			if (_places.containsValue(transport)) {
				throw new GarageAlreadyHaveException();
			}
			if (index != _places.size()) {
				_places.put(index, transport);
				_places.get(index).SetPosition(5 + i / 5 * _placeSizeWidth + 5 + 50, i % 5 * _placeSizeHeight + 35,
						ScreenWidth, ScreenHeight);
				return index;
			}
			this._places.put(this._places.size(), transport);
			_places.get(index).SetPosition(5 + index / 5 * _placeSizeWidth + 5 + 50, index % 5 * _placeSizeHeight + 35,
					ScreenWidth, ScreenHeight);
			return this._places.size() - 1;
		}
		throw new GarageOccupiedPlaceException();
	}

	public T RemoveTractor(int index) throws GarageNotFoundException {
		if (index < 0 || index > _places.size()) {
			return null;
		}
		if (!checkFreePlace(index)) {
			T car = _places.get(index);
			_places.set(index, null);
			return car;
		}
		throw new GarageNotFoundException();
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
		g.drawRect(0, 0, 638, 399);
		for (int i = 0; i < _maxCount / 6; i++) {
			for (int j = 0; j < 7; ++j) {
				g.drawLine(i * _placeSizeWidth, j * _placeSizeHeight, i * _placeSizeWidth + 110, j * _placeSizeHeight);
			}
			g.drawLine(i * _placeSizeWidth, 0, i * _placeSizeWidth, 399);
		}
	}
	
	public T getTractor(int index) {
		if (_places.get(index) != null) {
			return _places.get(index);
		} else {
			return null;
		}
	}
	
	public void setTractor(int ind, T t) {
		if (checkFreePlace(ind)) {
			_places.put(ind, t);
			_places.get(ind).SetPosition(5 + ind / 5 * _placeSizeWidth + 5 + 50, ind % 5 * _placeSizeHeight + 35,
					ScreenWidth, ScreenHeight);
		}
	}
	
	@Override
	public int compareTo(Garage<T> other) {
		if (this._places.size() > other._places.size()) {
			return -1;
		} else if (this._places.size() < other._places.size()) {
			return 1;
		} else {
			Integer[] thisKeys = this._places.keySet().toArray(new Integer[this._places.size()]);
			Integer[] otherKeys = other._places.keySet().toArray(new Integer[other._places.size()]);
			for (int i = 0; i < this._places.size(); i++) {
				if (this._places.get(thisKeys[i]).getClass().equals(Tractor.class)
						&& other._places.get(otherKeys[i]).getClass().equals(TractorWithLadle.class)) {
					return 1;
				}
				if (this._places.get(thisKeys[i]).getClass().equals(TractorWithLadle.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Tractor.class)) {
					return -1;
				}
				if (this._places.get(thisKeys[i]).getClass().equals(Tractor.class)
						&& other._places.get(otherKeys[i]).getClass().equals(Tractor.class)) {
					return ((Tractor) this._places.get(thisKeys[i]))
							.compareTo((Tractor) other._places.get(otherKeys[i]));
				}
				if (this._places.get(thisKeys[i]).getClass().equals(TractorWithLadle.class)
						&& other._places.get(otherKeys[i]).getClass().equals(TractorWithLadle.class)) {
					return ((TractorWithLadle) this._places.get(thisKeys[i]))
							.compareTo((TractorWithLadle) other._places.get(otherKeys[i]));
				}
			}
		}
		return 0;
	}

	@Override
	public Iterator<T> iterator() {
		return this;
	}

	@Override
	public boolean hasNext() {
		if (currentIndex + 1 >= _places.size()) {
			currentIndex = -1;
			return false;
		}
		currentIndex++;
		return true;
	}

	@Override
	public T next() {
		return (T) _places.get(currentIndex);
	}

	private void reset() {
		currentIndex = -1;
	}
}
