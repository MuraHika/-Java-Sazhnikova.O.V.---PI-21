
import javax.swing.JFrame;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class FormGarage extends JFrame {

	private JFrame frame;
	private JButton buttonSetTractor;
	private JButton buttonSetTractorWithLadle;
	private JButton buttonTakeTractor;
	private JPanel panel;
	private JPanel panelTakeTractorFromPlace;
	private GaragePanel panelWithGarage;
	private DrawPanel panelTakeTractor;
	private JLabel labelPlace;
	private JTextField textField;
	private ITransport transport;
	private Garage<ITransport> garage;

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
		garage = new Garage<ITransport>(20, panelWithGarage.getWidth(), panelWithGarage.getHeight());
		panelWithGarage.setParking(garage);

		buttonSetTractor = new JButton("Поставить обычный Трактор");
		buttonSetTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color MainColor = JColorChooser.showDialog(null, "Choose a Color", Color.RED);
				transport = new Tractor(100, 1000, MainColor);
				garage.AddTractor(transport);
				panelWithGarage.repaint();
			}
		});
		buttonSetTractor.setBounds(665, 19, 220, 60);
		frame.add(buttonSetTractor);

		buttonSetTractorWithLadle = new JButton("Поставить Трактор с ковшом");
		buttonSetTractorWithLadle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Color MainColor = JColorChooser.showDialog(null, "Choose a Color", Color.RED);
				Color DopColor = JColorChooser.showDialog(null, "Choose a Color", Color.BLACK);
				Color GlassColor = JColorChooser.showDialog(null, "Choose a Color", Color.BLUE);
				transport = new TractorWithLadle(100, 1000, MainColor, DopColor, GlassColor, true);
				garage.AddTractor(transport);
				panelWithGarage.repaint();
			}
		});
		buttonSetTractorWithLadle.setBounds(665, 100, 220, 60);
		frame.add(buttonSetTractorWithLadle);

		panelTakeTractorFromPlace = new JPanel();
		panelTakeTractorFromPlace.setBackground(Color.lightGray);
		panelTakeTractorFromPlace.setBounds(665, 183, 170, 215);
		frame.add(panelTakeTractorFromPlace);
		panelTakeTractorFromPlace.setLayout(null);

		labelPlace = new JLabel("Место: ");
		labelPlace.setBounds(20, 14, 50, 14);
		panelTakeTractorFromPlace.add(labelPlace);

		panelTakeTractor = new DrawPanel();
		panelTakeTractor.setBackground(Color.white);
		panelTakeTractor.setBounds(10, 72, 150, 122);
		panelTakeTractorFromPlace.add(panelTakeTractor);

		buttonTakeTractor = new JButton("Вывести из гаража ");
		buttonTakeTractor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int numberOfPlace = 0;
				try {
					numberOfPlace = Integer.parseInt(textField.getText());
				} catch (Exception ex) {
					textField.setText("ERROR");
					return;
				}
				if (numberOfPlace >= garage._places.size() || numberOfPlace < 0) {
					textField.setText("ERROR");
					return;
				}
				transport = garage.RemoveTractor(numberOfPlace);
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

		textField = new JTextField();
		textField.setBounds(78, 11, 80, 20);
		panelTakeTractorFromPlace.add(textField);
		textField.setColumns(10);
	}
}