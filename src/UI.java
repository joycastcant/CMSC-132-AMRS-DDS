import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.io.File;

public class UI extends JFrame {
	
	Container c;
	String filepath;
	final JFileChooser FC = new JFileChooser();
	static DefaultTableModel model;
	static DefaultTableModel hazmodel;
	static DefaultTableModel regmodel;
	static DefaultTableModel flagmodel;
	static JLabel clk;
	static JLabel stalls;
	static JPanel center = new JPanel();
	static JButton reset = new JButton("Reset");
	static JButton start = new JButton("Start");
	static JButton choose = new JButton("Choose File");
	
	public UI(String name) {
		super(name);
		FC.setCurrentDirectory(new java.io.File("../files"));
		filepath = "...";
		setComponents();
	}
	
	public void setComponents() {
		this.setPreferredSize(new Dimension(800,500));
        this.setResizable(false);
        this.setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        
		c = this.getContentPane();
		c.setLayout(new BorderLayout());
		
		init(c);

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
		

		
		reset.addActionListener(new ActionListener() {			
			public void actionPerformed(ActionEvent e)  {
				filepath = "...";
				start.setEnabled(true);
				choose.setEnabled(true);
				setVisible(false);
				center.removeAll();
				c.removeAll();
				init(c);
				setVisible(true);
			}
		});
	}
		public void init(Container c)
		{//-------------------------------- WEST PANEL
				
				JPanel west = new JPanel();
				west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
				west.setBackground(new Color(255, 58, 107));
				west.setPreferredSize(new Dimension(250, 500));
				
				JPanel topButtons = new JPanel();
				JLabel chlabel = new JLabel("file: ... ");
				topButtons.add(choose);
				topButtons.add(chlabel);

				reset.setEnabled(false);
				start.setEnabled(false);

				JPanel buttons = new JPanel();
				buttons.add(reset);
				buttons.add(start);
		
				JPanel infoPanel = new JPanel();
				infoPanel.setPreferredSize(new Dimension(300, 50));
				clk = new JLabel("Cycles: 0\t");
				stalls = new JLabel("Stalls: 0");
				infoPanel.add(clk);
				infoPanel.add(stalls);
				
				String[] hzrdCols = {"Instructions", "Hazard"};
				Object[][] hzrdData = {};
				JTable hazardTable = new JTable(new NonEditableModel(hzrdData, hzrdCols));
				hazardTable.setPreferredScrollableViewportSize(new Dimension(300, 280));
				hazardTable.setFillsViewportHeight(true);
				JScrollPane hzscrll = new JScrollPane(hazardTable);
				hazmodel = (DefaultTableModel) hazardTable.getModel();
				
				String[] flgCols = {"Flag Register", "Status"};
				Object[][] flgData = {};
				JTable flagTable = new JTable(new NonEditableModel(flgData, flgCols));
				flagTable.setPreferredScrollableViewportSize(new Dimension(300, 180));
				flagTable.setFillsViewportHeight(true);
				JScrollPane flgscrll = new JScrollPane(flagTable);
				flagmodel = (DefaultTableModel) flagTable.getModel();
				
				String[] rgstrCols = {"Register", "Value"};
				Object[][] rgstrData = {};
				JTable regTable = new JTable(new NonEditableModel(rgstrData, rgstrCols));
				regTable.setPreferredScrollableViewportSize(new Dimension(300, 380));
				regTable.setFillsViewportHeight(true);
				JScrollPane regscrll = new JScrollPane(regTable);
				regmodel = (DefaultTableModel) regTable.getModel();
			
			
				west.add(topButtons);
				west.add(buttons);
				west.add(infoPanel);
				west.add(hzscrll);
				west.add(flgscrll);
				west.add(regscrll);
				
				//-------------------------------- CENTER PANEL
				
				//JPanel center = new JPanel();
				center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
				//center.setBackground(new Color(255, 58, 107));
				center.setPreferredSize(new Dimension(550, 500));
				
				String[] scrBrdCols = {"Instructions","1","2","3","4","5","6","7"};/* ,"8","9","10","11","12","13","14","15"}; */
				Object[][] scrBrdData = {};
				JTable scoreboard = new JTable(new NonEditableModel(scrBrdData, scrBrdCols));
				scoreboard.setPreferredScrollableViewportSize(new Dimension(300, 280));
				scoreboard.setFillsViewportHeight(true);
				scoreboard.setRowHeight(50);
				JScrollPane scrbrdscrll = new JScrollPane(scoreboard, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
				scoreboard.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
				
				model = (DefaultTableModel) scoreboard.getModel();
				//model.addRow(new Object[]{"LOAD F4, 6+, R1", "1", "", "", "", ""}); 
			
				center.add(scrbrdscrll);
				
				//-------------------------------- ADDING CONTENTS TO CONTENT PANE
				
				c.add(west, BorderLayout.WEST);
				c.add(center, BorderLayout.CENTER);

				choose.addActionListener(new ActionListener() {			
					public void actionPerformed(ActionEvent e)  {
						int returnVal = FC.showOpenDialog(null);

						if (returnVal == JFileChooser.APPROVE_OPTION) {
							File file = FC.getSelectedFile();
							reset.setEnabled(true);
							start.setEnabled(true);
							//This is where a real application would open the file.
							System.out.println("Opening: " + file.getName() + ".");
							chlabel.setText(file.getName());
							filepath = file.getPath();
						} else {
							System.out.println("Open command cancelled by user.");
						}
					}
				});
				
				start.addActionListener(new ActionListener() {			
					public void actionPerformed(ActionEvent e)  {
						if(!filepath.equals("...")){
							Main.begin(filepath);
							start.setEnabled(false);
							choose.setEnabled(false);
						}
						else JOptionPane.showMessageDialog(null, "No file loaded", "File error", JOptionPane.INFORMATION_MESSAGE);
						
					}
				});
			}
}