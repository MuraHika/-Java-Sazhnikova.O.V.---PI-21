import java.util.ArrayList;

public class MultiLevelGarage {

	ArrayList<Garage<ITransport>> garageStages;
	private final int countPlaces = 20;
	int pictureWidth;
	int pictureHeight;

	public MultiLevelGarage(int countStages, int pictureWidth, int pictureHeight) {
		garageStages = new ArrayList<Garage<ITransport>>();
		for (int i = 0; i < countStages; ++i) {
			garageStages.add(new Garage<ITransport>(countPlaces, pictureWidth, pictureHeight));
		}
	}

	public Garage<ITransport> getParking(int index) {
		if ((index > -1) && (index < garageStages.size())) {
			return garageStages.get(index);
		}
		return null;
	}
	
	private void WriteToFile(String text, FileOutputStream stream) {
		try {
			byte[] info = text.getBytes();
			stream.write(info, 0, info.length);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public boolean SaveData(String filename) {
		File file = new File(filename);
		if (file.exists())
			file.delete();
		try (FileOutputStream fileStream = new FileOutputStream(file)) {
			try (BufferedOutputStream bs = new BufferedOutputStream(fileStream)) {
				String str = "CountLeveles:" + garageStages.size() + System.lineSeparator();
				ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
				for (int i = 0; i < str.length(); i++) {
					byteOut.write(str.charAt(i));
				}
				byte[] info = byteOut.toByteArray();
				fileStream.write(info, 0, info.length);
				for (Garage<ITransport> level : garageStages) {
					byteOut = new ByteArrayOutputStream();
					str = "Level" + System.lineSeparator();
					for (int i = 0; i < str.length(); i++) {
						byteOut.write(str.charAt(i));
					}
					info = byteOut.toByteArray();
					fileStream.write(info, 0, info.length);
					for (int i = 0; i < countPlaces; i++) {
						ITransport tractor = level.getTractor(i);
						if (tractor != null) {
							byteOut = new ByteArrayOutputStream();
							String tr = "";
							if (tractor.getClass().getName() == "Tractor") {
								tr = "Tractor";
							}
							else {
								tr = "_Tractor_With_Ladle";
							}
							String busInfo = tr + ":" + tractor.getInfo() + System.lineSeparator();
							for (int j = 0; j < busInfo.length(); j++) {
								byteOut.write(busInfo.charAt(j));
							}
							info = byteOut.toByteArray();
							fileStream.write(info, 0, info.length);
						}
					}
				}
			}
			fileStream.close();
			return true;
		} catch (IOException e) {
			return false;
		}
	}

    public boolean LoadData(String filename) {
    	File file = new File(filename);
		if (!file.exists()) {
			return false;
		}
		try (FileInputStream fileStream = new FileInputStream(filename)) {
			String s = "";
			try (BufferedInputStream bs = new BufferedInputStream(fileStream)) {
				Path path = Paths.get(file.getAbsolutePath());
				byte[] b = new byte[fileStream.available()];
				b = Files.readAllBytes(path);
				ByteArrayInputStream bos = new ByteArrayInputStream(b);
				String value = new String(b, StandardCharsets.UTF_8);
				while (bos.read(b, 0, b.length) > 0) {
					s += value;
				}
				s = s.replace("\r", "");
				String[] strs = s.split("\n");
				if (strs[0].contains("CountLeveles")) {
					if (garageStages != null) {
						garageStages.clear();
					}
					garageStages = new ArrayList<Garage<ITransport>>();
				} else {
					return false;
				}
				int counter = -1;
				for (int i = 0; i < strs.length; i++) {
					if (strs[i].startsWith("Level")) {
						counter++;
						garageStages.add(new Garage<ITransport>(countPlaces, pictureWidth, pictureHeight));
					} else if (strs[i].startsWith("Tractor")) {
						ITransport tractor = new Tractor(strs[i].split(":")[1]);
						int number = garageStages.get(counter).AddTractor(tractor);
						if (number == -1) {
							return false;
						}
					} else if (strs[i].startsWith("_Tractor_With_Ladle")) {
						ITransport tractor = new TractorWithLadle(strs[i].split(":")[1]);
						int number = garageStages.get(counter).AddTractor(tractor);
						if (number == -1) {
							return false;
						}
					}
				}
			}
			return true;
		} catch (IOException ex) {
			return false;
		}
    }
}
