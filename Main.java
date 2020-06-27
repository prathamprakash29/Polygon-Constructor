import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Main {

	static String setAr;
	private JFrame frame;
	private JTextField textField;
	private JTextField AreaText;
	private int h;
	private JTable table;


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Main() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 777, 786);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		PolygonPanel panel = new PolygonPanel();
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				if(h!=-1) {
					panel.drag((int)e.getX(), (int)e.getY(), h);
					AreaText.setText(setAr);
				}
			}
		});
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				h = panel.arrList.get(0).contains((int)e.getX(), (int)e.getY());
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				h=-1;
			}
		});
		panel.setBackground(Color.WHITE);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridheight = 27;
		gbc_panel.gridwidth = 18;
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		frame.getContentPane().add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblEnterNumberOf = new JLabel("Enter Number of Vertices");
		GridBagConstraints gbc_lblEnterNumberOf = new GridBagConstraints();
		gbc_lblEnterNumberOf.insets = new Insets(0, 0, 5, 5);
		gbc_lblEnterNumberOf.gridx = 18;
		gbc_lblEnterNumberOf.gridy = 1;
		frame.getContentPane().add(lblEnterNumberOf, gbc_lblEnterNumberOf);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.NORTH;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 18;
		gbc_textField.gridy = 2;
		frame.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnDraw = new JButton("Draw");
		btnDraw.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Graphics g = panel.getGraphics();
                String s;
                s=textField.getText();
                boolean numeric = true;
                try{
                    int temp=Integer.parseInt(s);
                }

                catch (NumberFormatException e){
                    numeric = false;
                    JOptionPane.showMessageDialog(null,"please input a valid argument");
                    textField.setText("");
                }
                if(numeric){
                	int temp = Integer.parseInt(s);
                	if(temp>1){
		                panel.addPoly(temp);
		                AreaText.setText(setAr);
            	}
                else{
	            	JOptionPane.showMessageDialog(null,"Enter Integers Greater than 2 to draw a Polygon.");
	            	textField.setText("");
                }
			}
			}});
		GridBagConstraints gbc_btnDraw = new GridBagConstraints();
		gbc_btnDraw.insets = new Insets(0, 0, 5, 5);
		gbc_btnDraw.gridx = 18;
		gbc_btnDraw.gridy = 3;
		frame.getContentPane().add(btnDraw, gbc_btnDraw);
		
		JLabel lblAreaOfPolygon = new JLabel("Area of Polygon");
		GridBagConstraints gbc_lblAreaOfPolygon = new GridBagConstraints();
		gbc_lblAreaOfPolygon.insets = new Insets(0, 0, 5, 5);
		gbc_lblAreaOfPolygon.gridx = 18;
		gbc_lblAreaOfPolygon.gridy = 12;
		frame.getContentPane().add(lblAreaOfPolygon, gbc_lblAreaOfPolygon);
		
		AreaText = new JTextField();
		AreaText.setEditable(false);
		GridBagConstraints gbc_AreaText = new GridBagConstraints();
		gbc_AreaText.insets = new Insets(0, 0, 5, 5);
		gbc_AreaText.fill = GridBagConstraints.HORIZONTAL;
		gbc_AreaText.gridx = 18;
		gbc_AreaText.gridy = 13;
		frame.getContentPane().add(AreaText, gbc_AreaText);
		AreaText.setColumns(10);
		
		JButton btnShowAngles = new JButton("Show Angles");
		btnShowAngles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String[] columnNames = {"Vertex Number", "Angle"};
				String data[][] = new String[panel.arrList.get(0).getV()][2];
				for(int i=0;i<data.length;i++) {
					data[i][0] = Integer.toString(i);
					data[i][1] = Double.toString(panel.arrList.get(0).angList.get(i));
				}
				final JTable table = new JTable(new DefaultTableModel(data, columnNames));
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				model.addTableModelListener(table);
				table.setVisible(true);
				final JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setVisible(true);
		        scrollPane.setBounds(44, 248, 476, 400);
		        
		        JPanel gui = new JPanel(new BorderLayout(3,3));
		        gui.add(scrollPane, BorderLayout.CENTER);
		        JOptionPane.showMessageDialog(null, gui);
			}
		});
		GridBagConstraints gbc_btnShowAngles = new GridBagConstraints();
		gbc_btnShowAngles.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowAngles.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowAngles.gridx = 18;
		gbc_btnShowAngles.gridy = 20;
		frame.getContentPane().add(btnShowAngles, gbc_btnShowAngles);
		
		JButton btnShowSideLength = new JButton("Show Side Length");
		btnShowSideLength.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String[] columnNames = {"Side", "Length"};
				String data[][] = new String[panel.arrList.get(0).getV()][2];
				for(int i=0;i<data.length;i++) {
					int j=(i+data.length+1)%data.length;
					data[i][0] = Integer.toString((char) (0 + i))+"-"+Integer.toString((char) (0 + j));
					data[i][1] = Double.toString(panel.arrList.get(0).lenList.get(i));
				}
				final JTable table = new JTable(new DefaultTableModel(data, columnNames));
				DefaultTableModel model = new DefaultTableModel();
				model.setColumnIdentifiers(columnNames);
				model.addTableModelListener(table);
				table.setVisible(true);
				final JScrollPane scrollPane = new JScrollPane(table);
				scrollPane.setVisible(true);
		        scrollPane.setBounds(44, 248, 476, 400);
		        
		        JPanel gui = new JPanel(new BorderLayout(3,3));
		        gui.add(scrollPane, BorderLayout.CENTER);
		        JOptionPane.showMessageDialog(null, gui);
			}
		});
		GridBagConstraints gbc_btnShowSideLength = new GridBagConstraints();
		gbc_btnShowSideLength.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnShowSideLength.insets = new Insets(0, 0, 5, 5);
		gbc_btnShowSideLength.gridx = 18;
		gbc_btnShowSideLength.gridy = 24;
		frame.getContentPane().add(btnShowSideLength, gbc_btnShowSideLength);
	}
}
