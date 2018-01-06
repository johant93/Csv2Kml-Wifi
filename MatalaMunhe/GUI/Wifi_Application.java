import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.EmptyBorder;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Component;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JInternalFrame;
import javax.swing.JSplitPane;
import javax.swing.JTextPane;
import javax.swing.BoxLayout;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JToggleButton;
import javax.swing.JCheckBox;
import javax.swing.JPopupMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import java.awt.Button;
import java.awt.Panel;
import javax.swing.Box;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class Wifi_Application  {

	public JFrame frameApp;
	public JTextField txtnameFile;
	private JLabel lblPleaseEnter;
	private JButton btnBrowse;
	private JButton btnClear;
	public String getPath;
	public ArrayList<Wifi> database = new ArrayList<Wifi>();	
	//public ArrayList<Wifi> databaseFiltered = new ArrayList<Wifi>();	
	ArrayList<Filter> myFilters = new ArrayList<Filter>();	
	ArrayList<Wifi> refs = new ArrayList<Wifi>();

	DataBase db = new DataBase(database);
	DataBase dbfiltered = new DataBase();

	private JPanel MenuPanel;
	private JSeparator separator;
	private JLabel label;
	private JLabel selectFormat;
	private JButton buttonProperties;
	private JLabel label_2;
	private JComboBox comboFormat;
	private JButton buttonConvert;
	private JComboBox combofilter1;
	private JComboBox comboOr1;
	private JButton btnAddFilter;
	private JLabel lblFilter_3;
	private JButton buttonDataBase;
	private JLabel lblFrom;
	private JTextField prm1;
	private JTextField prm2;
	private JTextField prm3;
	private JTextField txtNewfile;
	private JButton btnMenu;
	private JTextField textMacaddress1;
	private JTextField textcoordinate;
	private JTextField textcoordinate2;
	private JTextField textinputample;
	private JTextField textsignal;
	private JTextField textmacaddress2;
	private JTextField textcoordinate3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Wifi_Application window = new Wifi_Application();
					window.frameApp.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Wifi_Application() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frameApp = new JFrame();
		frameApp.setFont(new Font("Dialog", Font.PLAIN, 14));
		frameApp.setTitle("Wifi Application");
		frameApp.setBounds(100, 100, 759, 519);
		frameApp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frameApp.getContentPane().setLayout(new CardLayout(0, 0));

		JPanel databasePanel = new JPanel();
		frameApp.getContentPane().add(databasePanel, "name_407578523299667");

		JButton btnImport = new JButton("Import");
		btnImport.setBounds(304, 179, 144, 56);
		btnImport.setBackground(Color.BLUE);
		btnImport.setFont(new Font("Lucida Grande", Font.BOLD, 18));

		txtnameFile = new JTextField();
		txtnameFile.setBounds(187, 53, 346, 26);
		txtnameFile.setColumns(10);

		lblPleaseEnter = new JLabel("Please enter the pathname of your folder/file :");
		lblPleaseEnter.setBounds(191, 17, 418, 41);
		lblPleaseEnter.setFont(new Font("Lucida Grande", Font.PLAIN, 15));

		btnBrowse = new JButton("Browse");
		btnBrowse.setBounds(545, 53, 88, 29);

		btnClear = new JButton("Clear Database");
		btnClear.setBounds(304, 244, 144, 29);
		btnClear.setForeground(Color.RED);
		databasePanel.setLayout(null);
		databasePanel.add(btnImport);
		databasePanel.add(txtnameFile);
		databasePanel.add(lblPleaseEnter);
		databasePanel.add(btnBrowse);
		databasePanel.add(btnClear);

		btnMenu = new JButton("Menu");
		btnMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databasePanel.setVisible(false);
				MenuPanel.setVisible(true);
			}
		});
		btnMenu.setBounds(6, 6, 88, 29);
		databasePanel.add(btnMenu);

		MenuPanel = new JPanel();
		MenuPanel.setToolTipText("MENU");
		MenuPanel.setLayout(null);
		MenuPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		frameApp.getContentPane().add(MenuPanel, "name_407578554449017");

		JPanel Propertiespanel = new JPanel();
		frameApp.getContentPane().add(Propertiespanel, "name_407578575161735");
		Propertiespanel.setLayout(null);

		separator = new JSeparator();
		separator.setBackground(Color.GRAY);
		separator.setBounds(0, 230, 759, 23);
		MenuPanel.add(separator);

		label = new JLabel("Csv Converter");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 20));
		label.setBounds(237, 6, 281, 29);
		MenuPanel.add(label);

		selectFormat = new JLabel("Select the format :");
		selectFormat.setFont(new Font("Lucida Grande", Font.BOLD, 17));
		selectFormat.setBounds(26, 53, 161, 33);
		MenuPanel.add(selectFormat);

		JTextPane textsamplescan = new JTextPane();
		textsamplescan.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textsamplescan.setText("0");
		textsamplescan.setBounds(151, 72, 122, 25);
		Propertiespanel.add(textsamplescan);

		JTextPane textnbofwifi = new JTextPane();
		textnbofwifi.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textnbofwifi.setText("0");
		textnbofwifi.setBounds(151, 115, 122, 25);
		Propertiespanel.add(textnbofwifi);

		JTextPane textwififiltered = new JTextPane();
		textwififiltered.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		textwififiltered.setText("0");
		textwififiltered.setBounds(465, 115, 122, 25);
		Propertiespanel.add(textwififiltered);

		buttonProperties = new JButton("properties");
		buttonProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPanel.setVisible(false);
				Propertiespanel.setVisible(true);
				textsamplescan.setText(String.valueOf(db.getNboflist()));
				textnbofwifi.setText(String.valueOf(dbfiltered.getNbofWifi()));
				int filtered = db.getNbofWifi() - dbfiltered.getNbofWifi();
				textwififiltered.setText(String.valueOf(filtered));

			}
		});

		buttonProperties.setBounds(618, 206, 141, 29);
		MenuPanel.add(buttonProperties);


		label_2 = new JLabel("Filters");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Lucida Grande", Font.BOLD | Font.ITALIC, 16));
		label_2.setBounds(320, 247, 124, 23);
		MenuPanel.add(label_2);

		JLabel lblNboffilter = DefaultComponentFactory.getInstance().createLabel("(0)");
		lblNboffilter.setBounds(412, 251, 64, 16);
		MenuPanel.add(lblNboffilter);

		comboFormat = new JComboBox();
		comboFormat.setModel(new DefaultComboBoxModel(new String[] {"Combined Csv File", "Kml File"}));
		comboFormat.setToolTipText("format");
		comboFormat.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		comboFormat.setBounds(193, 49, 198, 45);
		MenuPanel.add(comboFormat);

		buttonConvert = new JButton("Export");
		buttonConvert.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		buttonConvert.setBounds(636, 58, 107, 29);
		MenuPanel.add(buttonConvert);

		combofilter1 = new JComboBox();
		combofilter1.setModel(new DefaultComboBoxModel(new String[] {"- Select filter -", "By Time", "By Location", "By Device"}));
		combofilter1.setBounds(96, 308, 161, 27);
		MenuPanel.add(combofilter1);

		comboOr1 = new JComboBox();
		comboOr1.setModel(new DefaultComboBoxModel(new String[] {"OR", "AND"}));
		comboOr1.setBounds(277, 308, 117, 27);
		MenuPanel.add(comboOr1);

		lblFilter_3 = new JLabel("Filter  :");
		lblFilter_3.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFilter_3.setBounds(41, 311, 78, 16);
		MenuPanel.add(lblFilter_3);

		buttonDataBase = new JButton("Edit Database");
		buttonDataBase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				databasePanel.setVisible(true);
				MenuPanel.setVisible(false);
			}
		});
		buttonDataBase.setBounds(0, 6, 141, 29);
		MenuPanel.add(buttonDataBase);

		JScrollPane scrollPane_myfilter = new JScrollPane();
		scrollPane_myfilter.setBounds(19, 234, 722, 219);
		Propertiespanel.add(scrollPane_myfilter);

		JTextArea textmyFilter = new JTextArea();
		scrollPane_myfilter.setViewportView(textmyFilter);

		JLabel lblFrom = new JLabel("From");
		lblFrom.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblFrom.setBounds(42, 366, 146, 16);
		MenuPanel.add(lblFrom);

		prm1 = new JTextField();
		prm1.setBounds(193, 361, 130, 26);
		MenuPanel.add(prm1);
		prm1.setColumns(10);

		JLabel lblTo = new JLabel("To");
		lblTo.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblTo.setBounds(41, 399, 126, 16);
		MenuPanel.add(lblTo);

		prm2 = new JTextField();
		prm2.setColumns(10);
		prm2.setBounds(193, 394, 130, 26);
		MenuPanel.add(prm2);

		JLabel lblDiameter = new JLabel("Radius");
		lblDiameter.setFont(new Font("Lucida Grande", Font.BOLD, 13));
		lblDiameter.setBounds(41, 432, 78, 16);
		MenuPanel.add(lblDiameter);

		prm3 = new JTextField();
		prm3.setColumns(10);
		prm3.setBounds(193, 427, 130, 26);
		MenuPanel.add(prm3);

		JToggleButton tglbtnNot = new JToggleButton("Not");
		tglbtnNot.setBounds(412, 307, 64, 29);
		MenuPanel.add(tglbtnNot);
		MenuPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{separator, label, selectFormat, buttonProperties, label_2, comboFormat, buttonConvert, combofilter1, comboOr1, btnAddFilter, lblFilter_3, buttonDataBase}));

		lblTo.setVisible(false);
		lblFrom.setVisible(false);
		lblDiameter.setVisible(false);
		prm1.setVisible(false);
		prm2.setVisible(false);
		prm3.setVisible(false);

		combofilter1.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				int s = combofilter1.getSelectedIndex();

				if (s == 0)
				{
					lblTo.setVisible(false);
					lblFrom.setVisible(false);
					lblDiameter.setVisible(false);
					prm1.setVisible(false);
					prm2.setVisible(false);
					prm3.setVisible(false);
				}
				else if (s == 1) // by time
				{
					lblFrom.setVisible(true);
					lblFrom.setText("From");
					prm1.setVisible(true);
					prm1.setText("");

					lblTo.setVisible(true);
					lblTo.setText("To");
					prm2.setVisible(true);
					prm2.setText("");
					lblDiameter.setVisible(false);	
					prm3.setVisible(false);
				}
				else if (s == 2) // by location
				{
					lblFrom.setVisible(true);
					lblFrom.setText("Longitude");
					prm1.setVisible(true);
					prm1.setText("");

					lblTo.setText("Latitude");
					lblTo.setVisible(true);
					prm2.setText("");
					prm2.setVisible(true);

					lblDiameter.setText("Radi");
					lblDiameter.setVisible(true);	
					prm3.setText("");
					prm3.setVisible(true);
				}
				else if (s == 3) // by device
				{
					lblFrom.setVisible(true);
					prm1.setVisible(true);
					lblFrom.setText("Name of device :");
					prm1.setText("");

					lblTo.setVisible(false);
					prm2.setVisible(false);

					lblDiameter.setVisible(false);	
					prm3.setVisible(false);
				}
			}
		});

		btnAddFilter = new JButton("Add filter");
		btnAddFilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = myFilters.size()+1 ;
				switch (combofilter1.getSelectedIndex()){
				case 0: 
					JOptionPane.showMessageDialog(null,"Select filter !");
					break;
				case 1:
					FilterByTime bytime = new FilterByTime(prm1.getText(),prm2.getText());	
					bytime.setOperator(comboOr1.getSelectedItem().toString());
					if (tglbtnNot.isSelected()) bytime.setNotOperator("N");
					myFilters.add(bytime);    
					textmyFilter.append(index+"."+bytime+"\n");
					JOptionPane.showMessageDialog(null, bytime, "New filter", 1);
					break; 	
				case 2:
					FilterByPosition byposition = new FilterByPosition(prm1.getText(),prm2.getText(),prm3.getText());
					byposition.setOperator(comboOr1.getSelectedItem().toString());
					if (tglbtnNot.isSelected()) byposition.setNotOperator("N");
					myFilters.add(byposition);  
					textmyFilter.append(index+"."+byposition+"\n");
					JOptionPane.showMessageDialog(null, byposition, "New filter", 1);
					break; 
				case 3:
					FilterByDevice bydevice = new FilterByDevice(prm1.getText());
					bydevice.setOperator(comboOr1.getSelectedItem().toString());
					if (tglbtnNot.isSelected())  bydevice.setNotOperator("N");
					myFilters.add(bydevice); 
					textmyFilter.append(index+"."+bydevice+"\n");
					JOptionPane.showMessageDialog(null, bydevice, "New filter", 1);
					break; 
				}	
				//databaseFiltered = db.getDataFiltered(myFilters);
				dbfiltered.setDatabase(db.getDataFiltered(myFilters));
				lblNboffilter.setText("("+myFilters.size()+")");
			}
		});
		btnAddFilter.setBounds(602, 307, 141, 29);
		MenuPanel.add(btnAddFilter);

		textsamplescan.setText(String.valueOf(db.getNboflist()));
		textnbofwifi.setText(String.valueOf(dbfiltered.getNbofWifi()));
		int filtered = db.getNbofWifi() - dbfiltered.getNbofWifi();
		textwififiltered.setText(String.valueOf(filtered));

		buttonConvert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(txtNewfile.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "Enter a name for the new file !","input empty", 2);
				else{
					String format = 	comboFormat.getSelectedItem().toString();
					if(format.equals("Combined Csv File")){
						CsvCombinedThread csvthread = new CsvCombinedThread(dbfiltered,txtNewfile.getText());
						csvthread.start();

					}
					if(format.equals("Kml File")){
						KmlThread kmlthread = new KmlThread(dbfiltered,txtNewfile.getText());
						kmlthread.start();
					}
					txtNewfile.setText("");
					JOptionPane.showMessageDialog(null,"your file has been successfully exported");
				}
			}
		});



		JLabel lblNewJgoodiesTitle = DefaultComponentFactory.getInstance().createTitle("General");
		lblNewJgoodiesTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesTitle.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblNewJgoodiesTitle.setBounds(274, 6, 192, 30);
		Propertiespanel.add(lblNewJgoodiesTitle);

		JLabel lblFilters = DefaultComponentFactory.getInstance().createTitle("Filters");
		lblFilters.setFont(new Font("Lucida Grande", Font.BOLD, 20));
		lblFilters.setHorizontalAlignment(SwingConstants.CENTER);
		lblFilters.setBounds(288, 192, 158, 30);
		Propertiespanel.add(lblFilters);

		JLabel lblTotalOfWifis = DefaultComponentFactory.getInstance().createTitle("Total Wifis :");
		lblTotalOfWifis.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblTotalOfWifis.setBounds(44, 118, 129, 31);
		Propertiespanel.add(lblTotalOfWifis);

		JLabel lblTotalOfRouters = DefaultComponentFactory.getInstance().createTitle("Sample Scan :");
		lblTotalOfRouters.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblTotalOfRouters.setBounds(31, 81, 192, 16);
		Propertiespanel.add(lblTotalOfRouters);

		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Propertiespanel.setVisible(false);
				MenuPanel.setVisible(true);
			}
		});

		btnBack.setBounds(6, 7, 117, 29);
		Propertiespanel.add(btnBack);

		JLabel lblwififiltered = DefaultComponentFactory.getInstance().createLabel("Wifi filtered :");
		lblwififiltered.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblwififiltered.setHorizontalAlignment(SwingConstants.LEFT);
		lblwififiltered.setBounds(346, 121, 120, 25);
		Propertiespanel.add(lblwififiltered);

		JButton btnclearfilter = new JButton("clear");
		btnclearfilter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				myFilters.clear();
				textmyFilter.setText("");
				lblNboffilter.setText("(0)");
				dbfiltered.setDatabase(db.getDatabase());
			}
		});
		btnclearfilter.setForeground(Color.RED);
		btnclearfilter.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnclearfilter.setBounds(624, 462, 117, 29);
		Propertiespanel.add(btnclearfilter);

		JSeparator separator_1 = new JSeparator();
		separator_1.setForeground(Color.GRAY);
		separator_1.setBounds(0, 164, 765, 16);
		Propertiespanel.add(separator_1);
		
		JPanel panelalgo = new JPanel();
		frameApp.getContentPane().add(panelalgo, "name_537833292282885");
		panelalgo.setLayout(null);
		
		JPanel panelPreview = new JPanel();
		frameApp.getContentPane().add(panelPreview, "name_477850905938118");
		panelPreview.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 759, 450);
		panelPreview.add(scrollPane);

		JTextArea txtrData = new JTextArea();
		scrollPane.setViewportView(txtrData);

		JButton btnPreview = new JButton("View Database");
		btnPreview.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPreview.setVisible(true);
				MenuPanel.setVisible(false);
				txtrData.setText("");
				Iterator<Wifi> it = dbfiltered.getDatabase().iterator();
				while(it.hasNext())
					txtrData.append(it.next().toString()+"\n");
			}
		});
		btnPreview.setBounds(303, 134, 141, 42);
		MenuPanel.add(btnPreview);

		txtNewfile = new JTextField();
		txtNewfile.setHorizontalAlignment(SwingConstants.LEFT);
		txtNewfile.setText("NewFileName");
		txtNewfile.setBounds(398, 58, 167, 26);
		MenuPanel.add(txtNewfile);
		txtNewfile.setColumns(10);
		
		JButton btnalgo = new JButton("Find Location");
		btnalgo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPanel.setVisible(false);
				panelalgo.setVisible(true);				
			}
		});
		btnalgo.setBounds(303, 176, 141, 42);
		MenuPanel.add(btnalgo);

		JButton btnBack_1 = new JButton("back");
		btnBack_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPreview.setVisible(false);
				MenuPanel.setVisible(true);
			}
		});
		btnBack_1.setBounds(318, 462, 117, 29);
		panelPreview.add(btnBack_1);
		
		JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Mac Location");
		lblNewJgoodiesLabel.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewJgoodiesLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel.setBounds(293, 6, 157, 26);
		panelalgo.add(lblNewJgoodiesLabel);
		
		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("User Location");
		lblNewJgoodiesLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewJgoodiesLabel_1.setFont(new Font("Lucida Grande", Font.BOLD, 16));
		lblNewJgoodiesLabel_1.setBounds(305, 182, 134, 26);
		panelalgo.add(lblNewJgoodiesLabel_1);
		
		JSeparator separator_2 = new JSeparator();
		separator_2.setForeground(Color.GRAY);
		separator_2.setBounds(0, 158, 759, 12);
		panelalgo.add(separator_2);
		
		JButton btnMenu_1 = new JButton("menu");
		btnMenu_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MenuPanel.setVisible(true);
				panelalgo.setVisible(false);;
			}
		});
		btnMenu_1.setBounds(0, 6, 117, 29);
		panelalgo.add(btnMenu_1);
		
		JLabel lblmacaddress = DefaultComponentFactory.getInstance().createLabel("Mac Address :");
		lblmacaddress.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblmacaddress.setBounds(18, 72, 126, 26);
		panelalgo.add(lblmacaddress);
		
		textMacaddress1 = new JTextField();
		textMacaddress1.setBounds(118, 73, 162, 26);
		panelalgo.add(textMacaddress1);
		textMacaddress1.setColumns(10);
		
		textcoordinate = new JTextField();
		textcoordinate.setText("Latitude,Longitude,Altitude");
		textcoordinate.setBounds(118, 120, 535, 26);
		panelalgo.add(textcoordinate);
		textcoordinate.setColumns(10);
		
		JButton btnfind = new JButton("Find");
		btnfind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textMacaddress1.getText().isEmpty())
					JOptionPane.showMessageDialog(null, "input a Mac address !", "input empty", 2);
				else if (dbfiltered.getDatabase().isEmpty())
					JOptionPane.showMessageDialog(null, "Your Database is empty !", "DataBase", 2);
				else{
				Algorithme algo1 = new Algorithme();
				Mac mac = algo1.getMac(dbfiltered.getDatabase(), 4, textMacaddress1.getText());
				textcoordinate.setText(mac.getLat()+","+mac.getLon()+","+mac.getAlt());

				}
			}
		});
		btnfind.setBounds(327, 67, 91, 41);
		panelalgo.add(btnfind);
		
		JLabel lblNewJgoodiesLabel_2 = DefaultComponentFactory.getInstance().createLabel("Coordinates :");
		lblNewJgoodiesLabel_2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblNewJgoodiesLabel_2.setBounds(18, 117, 117, 29);
		panelalgo.add(lblNewJgoodiesLabel_2);
		
		JSeparator separator_3 = new JSeparator();
		separator_3.setForeground(Color.GRAY);
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setBounds(364, 220, 17, 277);
		panelalgo.add(separator_3);
		
		JLabel lblSampleWithoutCoordinate = DefaultComponentFactory.getInstance().createLabel("Sample Scan without coordinate:");
		lblSampleWithoutCoordinate.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSampleWithoutCoordinate.setBounds(18, 235, 304, 16);
		panelalgo.add(lblSampleWithoutCoordinate);
		
		textcoordinate2 = new JTextField();
		textcoordinate2.setText("Latitude,Longitude,Altitude");
		textcoordinate2.setBounds(14, 434, 338, 26);
		panelalgo.add(textcoordinate2);
		textcoordinate2.setColumns(10);
		
		textinputample = new JTextField();
		textinputample.setBounds(14, 263, 338, 26);
		panelalgo.add(textinputample);
		textinputample.setColumns(10);
		
		JButton btnfind2 = new JButton("Find");
		btnfind2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textinputample.getText().isEmpty()||!textinputample.getText().contains(",?,?,?,"))
					JOptionPane.showMessageDialog(null, "input a Sample scan without coordinate", "Wrong input", 2);
				else if (dbfiltered.getDatabase().isEmpty())
					JOptionPane.showMessageDialog(null, "Your Database is empty !", "DataBase", 2);
				else{
				Algorithme2 algo2 = new Algorithme2();
				Coordinate coord = algo2.getcoordinate(textinputample.getText(), dbfiltered.getDatabase());
				textcoordinate2.setText(coord.getLat()+","+coord.getLon()+","+coord.getAlt());
				}
			}
		});
		btnfind2.setBounds(138, 319, 91, 41);
		panelalgo.add(btnfind2);
		
		JLabel lblCoordinates = DefaultComponentFactory.getInstance().createLabel("Coordinates :");
		lblCoordinates.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblCoordinates.setBounds(18, 412, 120, 16);
		panelalgo.add(lblCoordinates);
		
		textsignal = new JTextField();
		textsignal.setText("sg");
		textsignal.setColumns(10);
		textsignal.setBounds(498, 263, 41, 26);
		panelalgo.add(textsignal);
		
		textmacaddress2 = new JTextField();
		textmacaddress2.setText("mac address");
		textmacaddress2.setColumns(10);
		textmacaddress2.setBounds(498, 231, 117, 26);
		panelalgo.add(textmacaddress2);
		
		JLabel lblmacaddress2 = new JLabel("Mac Address :");
		lblmacaddress2.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblmacaddress2.setBounds(389, 229, 105, 29);
		panelalgo.add(lblmacaddress2);
		
		JLabel lblSignal = new JLabel("Signal :");
		lblSignal.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblSignal.setBounds(393, 257, 64, 29);
		panelalgo.add(lblSignal);
		
		JLabel lblcoordinates3 = new JLabel("Coordinates :");
		lblcoordinates3.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblcoordinates3.setBounds(389, 412, 105, 16);
		panelalgo.add(lblcoordinates3);
		
		textcoordinate3 = new JTextField();
		textcoordinate3.setText("Latitude,Longitude,Altitude");
		textcoordinate3.setColumns(10);
		textcoordinate3.setBounds(389, 434, 338, 26);
		panelalgo.add(textcoordinate3);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(405, 298, 322, 62);
		panelalgo.add(scrollPane_1);
		
		JTextArea textAreaMyrefs = new JTextArea();
		scrollPane_1.setViewportView(textAreaMyrefs);
		
		JLabel lblcounter = new JLabel("(0/3)");
		lblcounter.setFont(new Font("Lucida Grande", Font.ITALIC, 12));
		lblcounter.setBounds(692, 358, 41, 16);
		panelalgo.add(lblcounter);
		
		JButton buttonfind3 = new JButton("Find");
		buttonfind3.setEnabled(false);
		JButton btnreset = new JButton("Undo");
		btnreset.setEnabled(false);
		
		JButton btnAddrefs = new JButton("Add");
		btnAddrefs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Wifi wifi = new Wifi();
				if(textmacaddress2.getText().isEmpty() || textsignal.getText().isEmpty() )
					JOptionPane.showMessageDialog(null, "enter Mac address and Signal !", "Empty field",2 ); 
				else if ( !wifi.isSignal(textsignal.getText()) || !wifi.isMac(textmacaddress2.getText()))
					JOptionPane.showMessageDialog(null, "MAC address or Signal invalid !", "Wrong Input",2 ); 
				else {
				wifi.setMac(textmacaddress2.getText());
				wifi.setSignal(textsignal.getText());
				refs.add(wifi);
				textAreaMyrefs.append(refs.size()+". MAC: "+wifi.getMac()+"  Signal:"+wifi.getSignal()+"\n");
				lblcounter.setText("("+refs.size()+"/3)");
				btnreset.setEnabled(true);
				buttonfind3.setEnabled(true);
				if(refs.size()>=3)
					btnAddrefs.setEnabled(false);
				    buttonfind3.setEnabled(false);

				}
			}
		});
		btnAddrefs.setBounds(598, 263, 70, 29);
		panelalgo.add(btnAddrefs);
		
		buttonfind3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbfiltered.getDatabase().isEmpty())
					JOptionPane.showMessageDialog(null, "Import Database ! : Your Database is empty.", "DataBase", 2);
				else{
				Algorithme2 algo2 = new Algorithme2();
				Coordinate coord = algo2.getcoordinate(refs, dbfiltered.getDatabase());
				textcoordinate3.setText(coord.getLat()+","+coord.getLon()+","+coord.getAlt());
				}
			}
		});
		buttonfind3.setBounds(513, 365, 91, 41);
		panelalgo.add(buttonfind3);
		
		btnreset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				
				btnAddrefs.setEnabled(true);
				refs.remove(refs.size()-1);
				textcoordinate3.setText("");
				textAreaMyrefs.setText("");
				lblcounter.setText("("+refs.size()+"/3)");
				int count = 1;
				for(Wifi wifi : refs)
					textAreaMyrefs.append((count++)+". MAC: "+wifi.getMac()+"  Signal:"+wifi.getSignal()+"\n");
				if(refs.isEmpty())
					btnreset.setEnabled(false);	
			}
		});
		btnreset.setForeground(Color.RED);
		btnreset.setBounds(663, 263, 70, 29);
		panelalgo.add(btnreset);

		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				db.clearDatabase();
				JOptionPane.showMessageDialog(null,"Database Cleared !");			
			}
		});
		btnBrowse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				OpenFile of = new OpenFile() ;
				try {
					of.PickMe();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				txtnameFile.setText(of.fileChooser.getSelectedFile().getAbsolutePath());
			}
		});
		btnImport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getPath = txtnameFile.getText();
				if(getPath.isEmpty()) JOptionPane.showMessageDialog(null, "enter name of a file !", "Empty path",2 );
				else if(db.isGoodFile(getPath)){  	   
					db.addToDatabase(getPath);
					dbfiltered.setDatabase(db.getDatabase());

					if(!myFilters.isEmpty()){
						int reset = JOptionPane.showConfirmDialog(
								null,
								"Reset the currents filters ?",
								"My Filters",
								JOptionPane.YES_NO_OPTION);
						// YES_OPTION	
						if(reset==0){
							myFilters.clear();
							dbfiltered.setDatabase(db.getDatabase());
							textmyFilter.setText("");
							lblNboffilter.setText("(0)");
						}
						// NO_OPTION
						else { 
							dbfiltered.setDatabase(db.getDataFiltered(myFilters));
						}

					}
					JOptionPane.showMessageDialog(null,getPath+" was added successfully");
                     buttonfind3.setEnabled(true);
					databasePanel.setVisible(false);
					MenuPanel.setVisible(true);
				}
				else {
					JOptionPane.showMessageDialog(null, "The file format is incompatible or check if the file exists", "Error File",2 );
				}
			}
		});
	}
	public DataBase geTheDatabase(){
		return db ;
	}

	public String getPath(){
		return this.getPath;
	}
	public ArrayList<Wifi> getDatabase (){
		return this.database ;
	}
}
