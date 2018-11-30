package TractorForm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Image;
import java.io.IOException;
import java.util.Random;
import javax.swing.ImageIcon;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Forms {

	public JFrame frame;
	public Tractor tractor;
	public DrawPanel panel;
	public JButton buttonCreate;
	public JButton buttonCreateWithLadle;
	public JButton buttonUp;
	public JButton buttonDown;
	public JButton buttonRight;
	public JButton buttonLeft;
	public Random rnd = new Random();
	public int IconWidth = 60;
	public int IconHeight = 60;

	public Forms() throws IOException {
		initialize();
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Forms window = new Forms();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	private void initialize() throws IOException {

		frame = new JFrame("Tractor");
		frame.setBounds(100, 100, 1032, 620);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		panel = new DrawPanel();
		panel.setBackground(Color.lightGray);
		panel.setBounds(10, 10, frame.getWidth() - 250, frame.getHeight() - 80);
		frame.getContentPane().add(panel);

		buttonCreate = new JButton("CREATE TRACTOR");
		buttonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tractor = new Tractor(rnd.nextInt(200), rnd.nextInt(1000), Color.RED, Color.BLACK, Color.BLUE, true);
				tractor.SetPosition(rnd.nextInt(150) + 50, rnd.nextInt(150) + 50, panel.getWidth(), panel.getHeight());
				panel.addTractor(tractor);
				panel.repaint();
			}
		});
		buttonCreate.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonCreate.setBounds(frame.getWidth() - 230, 20, 200, 60);
		frame.getContentPane().add(buttonCreate);
		
		buttonCreateWithLadle = new JButton("CREATE TRACTOR WITH LADLE");
		buttonCreateWithLadle.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tractor = new TractorWithLadle(rnd.nextInt(200), rnd.nextInt(1000), Color.RED, Color.BLACK, Color.BLUE,
						true);
				tractor.SetPosition(rnd.nextInt(150) + 50, rnd.nextInt(150) + 50, panel.getWidth(), panel.getHeight());
				panel.addTractor(tractor);
				panel.repaint();
			}
		});
		buttonCreateWithLadle.setFont(new Font("Verdana", Font.PLAIN, 14));
		buttonCreateWithLadle.setBounds(frame.getWidth() - 230, 100, 200, 60);
		frame.getContentPane().add(buttonCreateWithLadle);

		JButton buttonUp = new JButton();
		ImageIcon IconUp = new ImageIcon("D:\\Hikaro\\Digital\\Up.png");
		Image ImageUp = IconUp.getImage().getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
		IconUp = new ImageIcon(ImageUp);
		buttonUp.setIcon(IconUp);
		buttonUp.setBounds(frame.getWidth() - 155, frame.getHeight() - 260, 60, 60);
		buttonUp.setContentAreaFilled(false);
		buttonUp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (tractor != null) {
					tractor.MoveTransport(Direction.Up);
					panel.repaint();
				}
			}
		});
		frame.getContentPane().add(buttonUp);

		JButton buttonDown = new JButton();
		ImageIcon IconDown = new ImageIcon("D:\\Hikaro\\Digital\\Down.png");
		Image ImageDown = IconDown.getImage().getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
		IconDown = new ImageIcon(ImageDown);
		buttonDown.setIcon(IconDown);
		buttonDown.setBounds(frame.getWidth() - 155, frame.getHeight() - 140, 60, 60);
		buttonDown.setContentAreaFilled(false);
		buttonDown.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tractor != null) {
					tractor.MoveTransport(Direction.Down);
					panel.repaint();
				}
			}
		});
		frame.getContentPane().add(buttonDown);

		JButton buttonRight = new JButton();
		ImageIcon IconRight = new ImageIcon("D:\\Hikaro\\Digital\\Right.png");
		Image ImageRight = IconRight.getImage().getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
		IconRight = new ImageIcon(ImageRight);
		buttonRight.setIcon(IconRight);
		buttonRight.setBounds(frame.getWidth() - 80, frame.getHeight() - 200, 60, 60);
		buttonRight.setContentAreaFilled(false);
		buttonRight.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tractor != null) {
					tractor.MoveTransport(Direction.Right);
					panel.repaint();
				}
			}
		});
		frame.getContentPane().add(buttonRight);

		JButton buttonLeft = new JButton();
		ImageIcon IconLeft = new ImageIcon("D:\\Hikaro\\Digital\\Left.png");
		Image ImageLeft = IconLeft.getImage().getScaledInstance(IconWidth, IconHeight, Image.SCALE_SMOOTH);
		IconLeft = new ImageIcon(ImageLeft);
		buttonLeft.setIcon(IconLeft);
		buttonLeft.setBounds(frame.getWidth() - 230, frame.getHeight() - 200, 60, 60);
		buttonLeft.setContentAreaFilled(false);
		buttonLeft.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (tractor != null) {
					tractor.MoveTransport(Direction.Left);
					panel.repaint();
				}
			}
		});
		frame.getContentPane().add(buttonLeft);
	}
}
