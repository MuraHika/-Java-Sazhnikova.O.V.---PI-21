
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FormGarage extends JFrame {

	private JFrame frame;
	private JButton buttonSetTractor;
	private JButton buttonSetTractorWithLadle;
	private JButton buttonTakeTractor;
	private JPanel panel;
	private DefaultListModel listModel;
	private JList listLevels;
	private JPanel panelTakeTractorFromPlace;
	private GaragePanel panelWithGarage;
	private DrawPanel panelTakeTractor;
	private JLabel labelPlace;
	private JTextField textField;
	private ITransport transport;
	private MultiLevelGarage garage;
	private final int countLevel = 5;
	FormTractorConfig select;

	public FormGarage() throws IOException {
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FormGarage window = new FormGarage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() throws IOException {

		frame = new JFrame("Garage");
		frame.setBounds(100, 100, 910, 455);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		panel.setLayout(null);
		frame.getContentPane().add(panel);

		panelWithGarage = new GaragePanel();
		panelWithGarage.setBackground(Color.lightGray);
		panelWithGarage.setBounds(10, 11, 639, 400);
		frame.add(panelWithGarage);
		panelWithGarage.setParking(garage);

		listModel = new DefaultListModel();
		for (int i = 0; i < countLevel; i++) {
			listModel.addElement("Level " + Integer.toString(i + 1));
		}

		listLevels = new JList(listModel);
		listLevels.setBounds(910, 40, 170, 124);
		frame.add(listLevels);
		listLevels.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
		listLevels.setSelectedIndex(0);

		ListSelectionListener listSelectionListener = new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				panelWithGarage.repaint();
			}
		};
		listLevels.addListSelectionListener(listSelectionListener);
		garage = new MultiLevelGarage(countLevel, panelWithGarage.getWidth(), panelWithGarage.getHeight());
		panelWithGarage.setParking(garage);
		panelWithGarage.setList(listLevels);

		buttonSetTractor = new JButton("Create Tractor");
		buttonSetTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				select = new FormTractorConfig(frame);
				if (select.res()) {
					ITransport tractor = select.getTractor();
					if (tractor != null) {
						int place = garage.getParking(listLevels.getSelectedIndex()).AddTractor(tractor);

						if (place != -1) {
							panelWithGarage.repaint();
						}
					}
					panel.repaint();
				}
			}
		});
		buttonSetTractor.setBounds(665, 19, 220, 60);
		frame.add(buttonSetTractor);

		garage = new MultiLevelGarage(countLevel, panelWithGarage.getWidth(), panelWithGarage.getHeight());
		panelWithGarage.setParking(garage);
		panelWithGarage.setList(listLevels);
		
		panelTakeTractorFromPlace = new JPanel();
		panelTakeTractorFromPlace.setBackground(Color.lightGray);
		panelTakeTractorFromPlace.setBounds(665, 183, 170, 215);
		frame.add(panelTakeTractorFromPlace);
		panelTakeTractorFromPlace.setLayout(null);

		labelPlace = new JLabel("Place: ");
		labelPlace.setBounds(20, 14, 50, 14);
		panelTakeTractorFromPlace.add(labelPlace);

		panelTakeTractor = new DrawPanel();
		panelTakeTractor.setBackground(Color.white);
		panelTakeTractor.setBounds(10, 72, 150, 122);
		panelTakeTractorFromPlace.add(panelTakeTractor);

		buttonTakeTractor = new JButton("Take Tractor");
		buttonTakeTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (listLevels.getSelectedIndex() == -1) {
					return;
				}
				int numberOfPlace = 0;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
				} catch (Exception ex) {
					textField.setText("ERROR");
					return;
				}
				transport = garage.getParking(listLevels.getSelectedIndex()).RemoveTractor(numberOfPlace);
				if (transport != null) {
					transport.SetPosition(80, 50, panelTakeTractor.getWidth(), panelTakeTractor.getHeight());
				}
				panelTakeTractor.addTractor(transport);
				panelTakeTractor.repaint();
				panelWithGarage.repaint();
			}
		});
		buttonTakeTractor.setBounds(10, 39, 150, 23);
		panelTakeTractorFromPlace.add(buttonTakeTractor);
		
		menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 100, 26);
		frame.add(menuBar);
		JMenu menufile = new JMenu("File");
		menufile.setHorizontalAlignment(SwingConstants.CENTER);
		menuBar.add(menufile);
		JMenuItem save = new JMenuItem("Save File");
		JMenuItem download = new JMenuItem("Open File");
		menufile.add(save);
		menufile.addSeparator();
		menufile.add(download);

		save.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                JFileChooser fileChoser = new JFileChooser();
                fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", ".txt"));
                int ret = fileChoser.showDialog(null, "Сохранить файл");
                if (ret == JFileChooser.APPROVE_OPTION) {
                    File file = fileChoser.getSelectedFile();
                    if (garage.SaveData(file.getAbsolutePath())) {
                        JOptionPane.showMessageDialog(frame, "Сохранение прошло успешно");
                    } else {
                        JOptionPane.showMessageDialog(frame, "Произошла ошибка");
                    }
                }
            }

		});
		download.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
	                JFileChooser fileChoser = new JFileChooser();
	                fileChoser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
	                int ret = fileChoser.showDialog(null, "Открыть файл");
	                if (ret == JFileChooser.APPROVE_OPTION) {
	                    File file = fileChoser.getSelectedFile();
	                    if (garage.LoadData(file.getAbsolutePath())) {
	                        JOptionPane.showMessageDialog(frame, "Загрузка прошло успешно");
	                        panelWithGarage.repaint();
	                    } else {
	                        JOptionPane.showMessageDialog(frame, "Произошла ошибка");
	                    }
	                }
	            }
		});

		textField = new JTextField();
		textField.setBounds(78, 11, 80, 20);
		panelTakeTractorFromPlace.add(textField);
		textField.setColumns(10);
		
		JLabel levelsLabel = new JLabel("Levels:");
		levelsLabel.setBounds(756, 11, 56, 16);
		panel.add(levelsLabel);
	}
}
