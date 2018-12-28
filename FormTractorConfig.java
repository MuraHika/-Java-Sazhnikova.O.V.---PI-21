import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.SystemColor;

import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.TransferHandler;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.border.BevelBorder;

import java.awt.Font;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTarget;
import java.awt.dnd.DropTargetDragEvent;
import java.awt.dnd.DropTargetDropEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JButton;

public class FormTractorConfig extends JDialog {

	ITransport tractor;
	JPanel panel;
	boolean res;
	Color color;
	Color dopColor;
	int maxSpeed;

	public FormTractorConfig(JFrame parent) {
		super(parent, true);
		initialize();
	}

	public boolean res() {
		setVisible(true);
		return res;
	}

	private void initialize() {
		this.getContentPane().setBackground(SystemColor.controlHighlight);
		this.setBounds(100, 100, 540, 350);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		panel.setBounds(194, 29, 143, 127);
		this.getContentPane().add(panel);

		MouseListener mouseL = new MouseListener() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mousePressed(MouseEvent e) {
				JComponent jc = (JComponent) e.getSource();
				TransferHandler th = jc.getTransferHandler();
				th.exportAsDrag(jc, e, TransferHandler.COPY);
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
			}
		};

		panel.setDropTarget(new DropTarget() {

			public void drop(DropTargetDropEvent e) {
				try {
					for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
						if (e.getTransferable().getTransferData(df) == "Tractor") {
							tractor = new Tractor(100, 1000, Color.RED);
						} else if (e.getTransferable().getTransferData(df) == "Tractor With Ladle") {
							tractor = new TractorWithLadle(100, 1000, Color.RED, Color.BLACK, Color.BLUE, true);
						}
						draw(panel, tractor);
					}
				} catch (Exception ex) {
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JLabel lblMainColor = new JLabel("Main Color");
		lblMainColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblMainColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMainColor.setBounds(194, 187, 143, 27);
		this.getContentPane().add(lblMainColor);

		JLabel lblDopColor = new JLabel("Dop Color");
		lblDopColor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDopColor.setHorizontalAlignment(SwingConstants.CENTER);
		lblDopColor.setBounds(194, 235, 143, 27);
		this.getContentPane().add(lblDopColor);

		lblMainColor.setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent e) {
				if (tractor != null) {
					try {
						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							tractor.SetMainColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, tractor);
						}
					} catch (Exception ex) {
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		lblDopColor.setDropTarget(new DropTarget() {
			public void drop(DropTargetDropEvent e) {
				if (tractor != null) {
					try {
						for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
							((TractorWithLadle) tractor)
									.SetDopColor((selectColor(e.getTransferable().getTransferData(df).toString())));
							draw(panel, tractor);
						}
					} catch (Exception ex) {
						System.out.println(ex);
					}
				}
			}

			public void dragEnter(DropTargetDragEvent e) {
				for (DataFlavor df : e.getTransferable().getTransferDataFlavors()) {
					try {
						if (e.getTransferable().getTransferData(df) instanceof String)
							e.acceptDrag(DnDConstants.ACTION_COPY);
						else
							e.acceptDrag(DnDConstants.ACTION_NONE);
					} catch (UnsupportedFlavorException | IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});

		JPanel panelYellow = new JPanel();
		panelYellow.setName("YELLOW");
		panelYellow.setBackground(Color.YELLOW);
		panelYellow.setBounds(444, 135, 46, 39);
		this.getContentPane().add(panelYellow);
		panelYellow.addMouseListener(mouseL);
		panelYellow.setTransferHandler(new TransferHandler("name"));

		JPanel panelBlue = new JPanel();
		panelBlue.setName("BLUE");
		panelBlue.setBackground(Color.BLUE);
		panelBlue.setBounds(386, 135, 46, 39);
		this.getContentPane().add(panelBlue);
		panelBlue.addMouseListener(mouseL);
		panelBlue.setTransferHandler(new TransferHandler("name"));

		JPanel panelRed = new JPanel();
		panelRed.setName("RED");
		panelRed.setBackground(Color.RED);
		panelRed.setBounds(444, 83, 46, 39);
		this.getContentPane().add(panelRed);
		panelRed.addMouseListener(mouseL);
		panelRed.setTransferHandler(new TransferHandler("name"));

		JPanel panelGreen = new JPanel();
		panelGreen.setName("GREEN");
		panelGreen.setBackground(Color.GREEN);
		panelGreen.setBounds(386, 83, 46, 39);
		this.getContentPane().add(panelGreen);
		panelGreen.addMouseListener(mouseL);
		panelGreen.setTransferHandler(new TransferHandler("name"));

		JPanel panelBlack = new JPanel();
		panelBlack.setName("BLACK");
		panelBlack.setBackground(Color.BLACK);
		panelBlack.setBounds(386, 29, 46, 39);
		this.getContentPane().add(panelBlack);
		panelBlack.addMouseListener(mouseL);
		panelBlack.setTransferHandler(new TransferHandler("name"));

		JPanel panelWhite = new JPanel();
		panelWhite.setName("WHITE");
		panelWhite.setBackground(Color.WHITE);
		panelWhite.setBounds(444, 29, 46, 39);
		this.getContentPane().add(panelWhite);
		panelWhite.addMouseListener(mouseL);
		panelWhite.setTransferHandler(new TransferHandler("name"));

		JPanel panelGray = new JPanel();
		panelGray.setName("GRAY");
		panelGray.setBackground(Color.GRAY);
		panelGray.setBounds(386, 187, 46, 39);
		this.getContentPane().add(panelGray);
		panelGray.addMouseListener(mouseL);
		panelGray.setTransferHandler(new TransferHandler("name"));

		JPanel panelOrange = new JPanel();
		panelOrange.setName("ORANGE");
		panelOrange.setBackground(Color.ORANGE);
		panelOrange.setBounds(444, 187, 46, 39);
		this.getContentPane().add(panelOrange);
		panelOrange.addMouseListener(mouseL);
		panelOrange.setTransferHandler(new TransferHandler("name"));

		JButton buttonOk = new JButton("OK");
		buttonOk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				res = true;
				dispose();
			}
		});
		buttonOk.setBounds(27, 197, 100, 40);
		this.getContentPane().add(buttonOk);

		JButton buttonCancel = new JButton("Exit");
		buttonCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		buttonCancel.addActionListener((ActionEvent e) -> {
			res = false;
			dispose();
		});
		buttonCancel.setBounds(27, 250, 100, 40);
		this.getContentPane().add(buttonCancel);

		JPanel panelGroup = new JPanel();
		panelGroup.setBounds(12, 29, 170, 127);
		getContentPane().add(panelGroup);
		panelGroup.setLayout(null);

		JLabel lblTractorWithLadle = new JLabel("Tractor");
		lblTractorWithLadle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTractorWithLadle.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTractorWithLadle.setBounds(13, 23, 144, 30);
		panelGroup.add(lblTractorWithLadle);
		lblTractorWithLadle.addMouseListener(mouseL);
		lblTractorWithLadle.setTransferHandler(new TransferHandler("text"));

		JLabel lblTractor = new JLabel("Tractor With Ladle");
		lblTractor.setHorizontalAlignment(SwingConstants.CENTER);
		lblTractor.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTractor.setBounds(13, 72, 145, 30);
		panelGroup.add(lblTractor);
		lblTractor.addMouseListener(mouseL);
		lblTractor.setTransferHandler(new TransferHandler("text"));
	}

	public ITransport getTractor() {
		return tractor;
	}

	public void draw(JPanel panel, ITransport tractor) {
		if (tractor != null) {
			Graphics gr = panel.getGraphics();
			gr.clearRect(0, 0, panel.getWidth(), panel.getHeight());
			tractor.SetPosition(70, 50, panel.getWidth(), panel.getHeight());
			tractor.DrawTractor(gr);
		}
	}

	public Color selectColor(String color) {
		switch (color) {
		case "YELLOW":
			return Color.YELLOW;
		case "BLUE":
			return Color.BLUE;
		case "RED":
			return Color.RED;
		case "GREEN":
			return Color.GREEN;
		case "BLACK":
			return Color.BLACK;
		case "WHITE":
			return Color.WHITE;
		case "GRAY":
			return Color.GRAY;
		case "ORANGE":
			return Color.ORANGE;
		}
		return null;
	}
}