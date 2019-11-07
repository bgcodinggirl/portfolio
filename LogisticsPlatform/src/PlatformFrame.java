import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class PlatformFrame extends JFrame{
	Connection conn = null;
	PreparedStatement state = null;
	
	int delivererId=-1;
	int companyId=-1;
	int orderId=-1;
	
	private JComboBox<String> companyFromCombo;
	private JComboBox<String> companyToCombo;
	private JComboBox<String> delivererCombo;
	
	JTabbedPane tabs = new JTabbedPane();
	JPanel welcomePanel = new JPanel();
	JPanel deliverersPanel = new JPanel();
	JPanel companiesPanel = new JPanel();
	JPanel shipmentPanel = new JPanel();
	JPanel infoPanel = new JPanel();
	
	// welcome content
	JPanel welcomeUpPanel = new JPanel();
	JPanel welcomeMidPanel = new JPanel();
	JPanel welcomeDownPanel = new JPanel();

	JButton goToDeliverersBtn = new JButton("Open deliverers' frame");
	JButton goToCompaniesBtn = new JButton("Open companies' frame");
	JButton goToShipmentBtn = new JButton("Open shipment frame");
	JButton goToInfoBtn = new JButton("Open query frame");
	
	JLabel welcomeLabel = new JLabel("Welcome to SM national logistics service platform!");
	//end welcome content
	
	// deliverers content
	JTable deliverersTable=new JTable();
	JScrollPane deliverersScroller=new JScrollPane(deliverersTable);

	JTable delivererSearchTable=new JTable();
	JScrollPane delivererSearchScroller=new JScrollPane(delivererSearchTable);
	
	JPanel deliverersUpPanel = new JPanel();
	JPanel deliverersMidPanel = new JPanel();
	JPanel deliverersDownPanel = new JPanel();
	JPanel deliverersSearchPanel = new JPanel();

	JButton addDeliverer = new JButton("Add");
	JButton deleteDeliverer = new JButton("Delete");
	JButton updateDeliverer = new JButton("Update");
	JButton searchDeliverer = new JButton("Search by category");
	JButton hideSDTable = new JButton("Hide search table");
	
	JLabel firstNameLabel = new JLabel("First name:");
	JLabel lastNameLabel = new JLabel("Last name:");
	JLabel ageLabel = new JLabel("Age:");
	JLabel phoneLabel = new JLabel("Phone:");
	JLabel emailLabel = new JLabel("E-mail:");
	JLabel addressLabel = new JLabel("Address:");
	JLabel categoryLabel = new JLabel("Category:");
	
	JTextField firstNameTField = new JTextField();
	JTextField lastNameTField = new JTextField();
	JTextField ageTField = new JTextField();
	JTextField phoneTField = new JTextField();
	JTextField emailTField = new JTextField();
	JTextField addressTField = new JTextField();
	String[] licenseCategories = {"B,B1/BE","C1","C","C1E","CE"};
	JComboBox<String> categoryCombo = new JComboBox<>(licenseCategories);
	
	// end deliverers content
	
	// companies content
	JTable companiesTable=new JTable();
	JScrollPane companiesScroller=new JScrollPane(companiesTable);

	JTable companySearchTable=new JTable();
	JScrollPane companySearchScroller=new JScrollPane(companySearchTable);
	
	JPanel companiesUpPanel = new JPanel();
	JPanel companiesMidPanel = new JPanel();
	JPanel companiesDownPanel = new JPanel();
	JPanel companySearchPanel = new JPanel();

	JButton addCompany = new JButton("Add");
	JButton deleteCompany = new JButton("Delete");
	JButton updateCompany = new JButton("Update");
	JButton searchCompany = new JButton("Search company for");
	JButton hideCompanySearch = new JButton("Hide search table");
	
	JLabel nameLabel = new JLabel("Name:");
	JLabel countryLabel = new JLabel("Country:");
	JLabel cityLabel = new JLabel("City:");
	JLabel postalCodeLabel = new JLabel("Post code:");
	JLabel aboutLabel = new JLabel("About:");
	
	JTextField nameTField = new JTextField();
	JTextField countryTField = new JTextField();
	JTextField cityTField = new JTextField();
	JTextField postalCodeTField = new JTextField();
	String[] companyCategories = {"Food","Beverages","Clothing and accessories","Gas/oil","Furniture","Home appliances", "Machines", "Smart technologies","All in one"};
	JComboBox<String> aboutCombo = new JComboBox<>(companyCategories);
	
	// end companies content
	
	// shipment content
	JTable shipmentTable=new JTable();
	JScrollPane shipmentScroller=new JScrollPane(shipmentTable);
	
	JTable shipmentSearchTable=new JTable();
	JScrollPane shipmentSearchScroller=new JScrollPane(shipmentSearchTable);
	
	JPanel shipmentUpPanelOne = new JPanel();
	JPanel shipmentUpPanelTwo = new JPanel();
	JPanel shipmentMidPanel = new JPanel();
	JPanel shipmentDownPanel = new JPanel();
	JPanel shipmentSearchPanel = new JPanel();

	JButton addShipment = new JButton("Add");
	JButton deleteShipment= new JButton("Delete");
	JButton updateShipment = new JButton("Update");
	JButton searchShipment = new JButton("Search by delivery type");
	JButton hideShipmentTable = new JButton("Hide search table");
	JButton getQueryTab = new JButton("Open query tab");
	
	JLabel descriptionLabel = new JLabel("Description:");
	JLabel companyFromLabel = new JLabel("From company:");
	JLabel companyToLabel = new JLabel("To company:");
	JLabel delivererLabel = new JLabel("Deliverer:");
	JLabel typeLabel = new JLabel("Delivery type:");
	JLabel statusLabel = new JLabel("Status:");
	JLabel deliveryInfoExpress = new JLabel("Express delivery is within 1-2 working days.");
	JLabel deliveryInfoStandart = new JLabel("Standart delivery is between 4-6 working days.");
	
	JTextField descriptionTField = new JTextField();
	
	JRadioButton firstRadioBtn=new JRadioButton("Express"); 
	JRadioButton secondRadioBtn=new JRadioButton("Standart");
	String[] radioBtnContent = {"Express","Standart"};
	String[] content = {"Being processed","In transit","Delivered"};
	JComboBox<String> statusCombo = new JComboBox<>(content);
	
	// end shipment content
	
	//information for orders content
	JTable infoTable=new JTable();
	JScrollPane infoScroller=new JScrollPane(infoTable);
	
	JPanel infoUpPanel= new JPanel();
	JPanel infoMidPanel= new JPanel();
	JPanel infoDownPanel = new JPanel();
	
	JButton searchInfoBtn= new JButton("Search");
	
	JLabel delivererFNameLabel = new JLabel("First name:");
	JLabel delivererLNameLabel = new JLabel("Last name:");
	JLabel destCountryLabel = new JLabel("The country, where order has to be delivered:");
	JLabel statusInfoLabel = new JLabel("Status:");

	JTextField delivererFNameTField = new JTextField();
	JTextField delivererLNameTField = new JTextField();
	JTextField destCountryTField = new JTextField();
	JComboBox<String> statusInfoCombo = new JComboBox<>(content);
	//end information for orders content
	
	private void companyComboFill() {
		companyFromCombo.removeAllItems();
		companyToCombo.removeAllItems();
		
		conn=DBConnector.getConnection();
		try {
			String sql="SELECT* FROM COMPANIES";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				companyFromCombo.addItem(rs.getInt("COMPANY_ID")+" "+rs.getString("Name")+" "+rs.getString("Country"));
				companyToCombo.addItem(rs.getInt("COMPANY_ID")+" "+rs.getString("Name")+" "+rs.getString("Country"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	private void delivererComboFill() {
		delivererCombo.removeAllItems();
		
		conn=DBConnector.getConnection();
		try {
			String sql="SELECT* FROM DELIVERERS";
			PreparedStatement pst=conn.prepareStatement(sql);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				delivererCombo.addItem(rs.getInt("ID")+" "+rs.getString("FNAME")+" "+rs.getString("LNAME"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	//***************** the frame *******************
	
	public PlatformFrame() {
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(800, 720);
		this.setResizable(false);
		this.getContentPane().setBackground(new Color(179,193,193));
		
		tabs.add("Home", welcomePanel);
		tabs.add("Shipment", shipmentPanel);
		tabs.add("Companies", companiesPanel);
		tabs.add("Deliverers", deliverersPanel);
		tabs.add("Information for orders", infoPanel);
		this.add(tabs);
		tabs.setBackground(new Color(179,193,193));
		
		//welcome layout
		welcomePanel.setLayout(new GridLayout(3, 1));
		welcomePanel.add(welcomeUpPanel);
		welcomePanel.add(welcomeMidPanel);
		welcomePanel.add(welcomeDownPanel);
		welcomePanel.setBackground(new Color(221, 227, 227));
		
		//upPanel
		welcomeUpPanel.add(welcomeLabel);
		welcomeUpPanel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
		welcomeLabel.setFont(new Font("Arial Black",Font.BOLD,22));
		welcomeLabel.setForeground(new Color(56, 69, 69));
		welcomeUpPanel.setBackground(new Color(221, 227, 227));
		
		//midPanel
		welcomeMidPanel.setBorder(BorderFactory.createEmptyBorder(150, 150, 150, 150));
		welcomeMidPanel.add(goToShipmentBtn);
		goToInfoBtn.addActionListener(new GetQueryTabAction());
		welcomeMidPanel.add(goToInfoBtn);
		goToShipmentBtn.addActionListener(new GetShipmentTabAction());
		welcomeMidPanel.setBackground(new Color(221, 227, 227));
		
		//downPanel
		welcomeDownPanel.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 150));
		welcomeDownPanel.add(goToCompaniesBtn);
		goToCompaniesBtn.addActionListener(new GetCompaniesTabAction());
		welcomeDownPanel.add(goToDeliverersBtn);
		goToDeliverersBtn.addActionListener(new GetDeliverersTabAction());
		welcomeDownPanel.setBackground(new Color(221, 227, 227));
		
		//end welcome layout
		
		//** deliverers layout**
		deliverersPanel.setLayout(new GridLayout(4, 1));
		deliverersPanel.add(deliverersUpPanel);
		deliverersPanel.add(deliverersMidPanel);
		deliverersPanel.add(deliverersDownPanel);
		deliverersPanel.add(deliverersSearchPanel);
		deliverersPanel.setBackground(new Color(221, 227, 227));
		
		//upPanel
		deliverersUpPanel.setLayout(new GridLayout(7, 2));
		deliverersUpPanel.add(firstNameLabel);
		deliverersUpPanel.add(firstNameTField);
		deliverersUpPanel.add(lastNameLabel);
		deliverersUpPanel.add(lastNameTField);
		deliverersUpPanel.add(ageLabel);
		deliverersUpPanel.add(ageTField);
		deliverersUpPanel.add(phoneLabel);
		deliverersUpPanel.add(phoneTField);
		deliverersUpPanel.add(emailLabel);
		deliverersUpPanel.add(emailTField);
		deliverersUpPanel.add(addressLabel);
		deliverersUpPanel.add(addressTField);
		deliverersUpPanel.add(categoryLabel);
		deliverersUpPanel.add(categoryCombo);
		
		deliverersUpPanel.setBackground(new Color(221, 227, 227));
		categoryCombo.setBackground(Color.white);
		
		//midPanel
		deliverersMidPanel.add(addDeliverer);
		addDeliverer.addActionListener(new AddDelivererAction());
		deliverersMidPanel.add(deleteDeliverer);
		deleteDeliverer.addActionListener(new DeleteDelivererAction());
		deliverersMidPanel.add(updateDeliverer);
		updateDeliverer.addActionListener(new UpdateDelivererAction());
		deliverersMidPanel.add(searchDeliverer);
		searchDeliverer.addActionListener(new SearchDelivererAction());
		deliverersMidPanel.add(hideSDTable);
		hideSDTable.setVisible(false);
		hideSDTable.addActionListener(new HideSDTableAction());
		deliverersMidPanel.setBackground(new Color(221, 227, 227));
		
		//downPanel
		deliverersDownPanel.add(deliverersScroller);
		deliverersScroller.setPreferredSize(new Dimension(700,100));
		deliverersTable.setModel(DBConnector.getAllDeliverers());
		deliverersTable.addMouseListener(new DelivererTableMouseAction() );
		deliverersDownPanel.setBackground(new Color(221, 227, 227));
		
		//searchPanel
		deliverersSearchPanel.setVisible(false);
		deliverersSearchPanel.add(delivererSearchScroller);
		delivererSearchScroller.setPreferredSize(new Dimension(700,100));
		delivererSearchTable.setModel(DBConnector.getAllDeliverers());
		deliverersSearchPanel.setBackground(new Color(221, 227, 227));
		
		// **end deliverers layout**
		
		// **companies layout**
		companiesPanel.setLayout(new GridLayout(4, 1));
		companiesPanel.add(companiesUpPanel);
		companiesPanel.add(companiesMidPanel);
		companiesPanel.add(companiesDownPanel);
		companiesPanel.add(companySearchPanel);
		companiesPanel.setBackground(new Color(221, 227, 227));
		//upPanel
		companiesUpPanel.setLayout(new GridLayout(5, 2));
		companiesUpPanel.add(nameLabel);
		companiesUpPanel.add(nameTField);
		companiesUpPanel.add(countryLabel);
		companiesUpPanel.add(countryTField);
		companiesUpPanel.add(cityLabel);
		companiesUpPanel.add(cityTField);
		companiesUpPanel.add(postalCodeLabel);
		companiesUpPanel.add(postalCodeTField);
		companiesUpPanel.add(aboutLabel);
		companiesUpPanel.add(aboutCombo);
		companiesUpPanel.setBackground(new Color(221, 227, 227));
		aboutCombo.setBackground(Color.white);
		
		//midPanel
		companiesMidPanel.add(addCompany);
		addCompany.addActionListener(new AddCompaniesAction());
		companiesMidPanel.add(deleteCompany);
		deleteCompany.addActionListener(new DeleteCompaniesAction());
		companiesMidPanel.add(updateCompany);
		updateCompany.addActionListener(new UpdateCompaniesAction());
		companiesMidPanel.add(searchCompany);
		searchCompany.addActionListener(new SearchCompanyAction());
		companiesMidPanel.add(hideCompanySearch);
		hideCompanySearch.setVisible(false);
		hideCompanySearch.addActionListener(new HideCDTableAction());
		companiesMidPanel.setBackground(new Color(221, 227, 227));
		
		//downPanel
		companiesDownPanel.add(companiesScroller);
		companiesScroller.setPreferredSize(new Dimension(700,100));
		companiesTable.setModel(DBConnector.getAllCompanies());
		companiesTable.addMouseListener(new CompaniesTableMouseAction() );
		companiesDownPanel.setBackground(new Color(221, 227, 227));
		//searchPanel
		companySearchPanel.setVisible(false);
		companySearchPanel.add(companySearchScroller);
		companySearchScroller.setPreferredSize(new Dimension(700,100));
		companySearchTable.setModel(DBConnector.getAllCompanies());
		companySearchPanel.setBackground(new Color(221, 227, 227));
		// **end companies layout**
		
		// **shipment layout**
		shipmentPanel.setLayout(new GridLayout(5, 1));
		shipmentPanel.add(shipmentUpPanelOne);
		shipmentPanel.add(shipmentUpPanelTwo);
		shipmentPanel.add(shipmentMidPanel);
		shipmentPanel.add(shipmentDownPanel);
		shipmentPanel.add(shipmentSearchPanel);
		shipmentPanel.setBackground(new Color(221, 227, 227));
		//upPanel
		shipmentUpPanelOne.setLayout(new GridLayout(5, 2));
		shipmentUpPanelOne.add(descriptionLabel);
		shipmentUpPanelOne.add(descriptionTField);
		
		shipmentUpPanelOne.add(companyFromLabel);
		companyFromCombo = new JComboBox<>();
		shipmentUpPanelOne.add(companyFromCombo);
		
		shipmentUpPanelOne.add(companyToLabel);
		companyToCombo = new JComboBox<>();
		shipmentUpPanelOne.add(companyToCombo);
		
		shipmentUpPanelOne.add(delivererLabel);
		delivererCombo = new JComboBox<>();
		shipmentUpPanelOne.add(delivererCombo);
		
		shipmentUpPanelOne.add(statusLabel);
		shipmentUpPanelOne.add(statusCombo);
		
		shipmentUpPanelTwo.setLayout(new GridLayout(2, 3));
		shipmentUpPanelTwo.add(typeLabel);
		shipmentUpPanelTwo.add(firstRadioBtn);
		shipmentUpPanelTwo.add(secondRadioBtn);
		shipmentUpPanelTwo.add(deliveryInfoExpress);
		shipmentUpPanelTwo.add(deliveryInfoStandart);

		shipmentUpPanelOne.setBackground(new Color(221, 227, 227));
		shipmentUpPanelTwo.setBackground(new Color(221, 227, 227));
		firstRadioBtn.setBackground(new Color(221, 227, 227));
		secondRadioBtn.setBackground(new Color(221, 227, 227));
		companyFromCombo.setBackground(Color.white);
		companyToCombo.setBackground(Color.white);
		delivererCombo.setBackground(Color.white);
		statusCombo.setBackground(Color.white);
		//midPanel
		shipmentMidPanel.add(addShipment);
		addShipment.addActionListener(new AddShipmentAction());
		shipmentMidPanel.add(deleteShipment);
		deleteShipment.addActionListener(new DeleteShipmentAction());
		shipmentMidPanel.add(updateShipment);
		updateShipment.addActionListener(new UpdateShipmentAction());
		shipmentMidPanel.add(searchShipment);
		searchShipment.addActionListener(new SearchShipmentAction());
		shipmentMidPanel.add(hideShipmentTable);
		hideShipmentTable.setVisible(false);
		hideShipmentTable.addActionListener(new HideSSTableAction());
		shipmentMidPanel.add(getQueryTab);
		getQueryTab.addActionListener(new GetQueryTabAction());
		
		shipmentMidPanel.setBackground(new Color(221, 227, 227));
		//downPanel
		shipmentDownPanel.add(shipmentScroller);
		shipmentScroller.setPreferredSize(new Dimension(700,100));
		shipmentTable.setModel(DBConnector.getAllOrders());
		shipmentTable.addMouseListener(new ShipmentTableMouseAction());
		shipmentDownPanel.setBackground(new Color(221, 227, 227));
		//searchPanel
		shipmentSearchPanel.setVisible(false);
		shipmentSearchPanel.add(shipmentSearchScroller);
		shipmentSearchScroller.setPreferredSize(new Dimension(700,100));
		shipmentSearchTable.setModel(DBConnector.getAllOrders());
		shipmentSearchPanel.setBackground(new Color(221, 227, 227));
		// **end shipment layout**
		
		//info layout
		infoPanel.setLayout(new GridLayout(3, 1));
		infoPanel.add(infoUpPanel);
		infoPanel.add(infoMidPanel);
		infoPanel.add(infoDownPanel);
		infoPanel.setBackground(new Color(221, 227, 227));
		//upPanel
		infoUpPanel.setLayout(new GridLayout(4, 2));
		infoUpPanel.add(delivererFNameLabel);
		infoUpPanel.add(delivererFNameTField);
		infoUpPanel.add(delivererLNameLabel);
		infoUpPanel.add(delivererLNameTField);
		infoUpPanel.add(destCountryLabel);
		infoUpPanel.add(destCountryTField);
		infoUpPanel.add(statusInfoLabel);
		infoUpPanel.add(statusInfoCombo);
		infoUpPanel.setBackground(new Color(221, 227, 227));
		statusInfoCombo.setBackground(Color.white);
		
		//midPanel
		infoMidPanel.add(searchInfoBtn);
		searchInfoBtn.addActionListener(new SearchInfoAction());
		infoMidPanel.setBackground(new Color(221, 227, 227));
		//downPanel
		infoDownPanel.add(infoScroller);
		infoScroller.setPreferredSize(new Dimension(700,100));
		infoTable.setModel(DBConnector.searchShipmentInfo(null,null,null,null));
		infoDownPanel.setBackground(new Color(221, 227, 227));
		//end info layout
		companyComboFill();
		delivererComboFill();
		
	}// end constructor
	class AddDelivererAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			String firstName = firstNameTField.getText();
			String lastName = lastNameTField.getText();
			int age = Integer.parseInt(ageTField.getText());
			String phone = phoneTField.getText();
			String email = emailTField.getText();
			String address = addressTField.getText();
			String category = categoryCombo.getSelectedItem().toString();
			String sql = "INSERT INTO DELIVERERS VALUES(null,?,?,?,?,?,?,?);";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, firstName);
				state.setString(2, lastName);
				state.setInt(3, age);
				state.setString(4, phone);
				state.setString(5, email);
				state.setString(6, address);
				state.setString(7, category);
				
				state.execute();
				deliverersTable.setModel(DBConnector.getAllDeliverers());
				JOptionPane.showMessageDialog(null, "You have successfully added a new deliverer!");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			clearDelivererForm();
			delivererComboFill();
		}
		
	}//end AddDelivererAction

	class DeleteDelivererAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
		String sql="DELETE FROM DELIVERERS WHERE ID=?";
		conn=DBConnector.getConnection();
		if(delivererId>-1) {
			try {
			state=conn.prepareStatement(sql);
			state.setInt(1, delivererId);
			state.execute();
			deliverersTable.setModel(DBConnector.getAllDeliverers());
			JOptionPane.showMessageDialog(null, "You have successfully deleted deliverer with id = " + delivererId);
			delivererId=-1;
		    } 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
			delivererComboFill();
		 }
		else {
			JOptionPane.showMessageDialog(null, "Please select a deliverer to be deleted!");
		}
		
	  }
	}	//end DeleteDelivererAction
	
	class UpdateDelivererAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(delivererId>-1)
			{	
			String firstName = firstNameTField.getText();
			String lastName = lastNameTField.getText();
			int age = Integer.parseInt(ageTField.getText());
			String phone = phoneTField.getText();
			String email = emailTField.getText();
			String address = addressTField.getText();
			String category = categoryCombo.getSelectedItem().toString();
			
			String sql="UPDATE DELIVERERS SET FNAME=?,LNAME=?,AGE=?,PHONE=?, EMAIL=?, ADDRESS=?, CATEGORY=? WHERE ID=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, firstName);
				state.setString(2, lastName);
				state.setInt(3, age);
				state.setString(4, phone);
				state.setString(5, email);
				state.setString(6, address);
				state.setString(7, category);
				state.setInt(8, delivererId);
				state.execute();
				deliverersTable.setModel(DBConnector.getAllDeliverers());
				JOptionPane.showMessageDialog(null, "You have successfully updated a deliverer with id = " + delivererId);	
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			clearDelivererForm();
			delivererComboFill();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error! You must choose a deliverer to be updated! ");
			}
		}
	} // end UpdateDelivererAction
	
	class SearchDelivererAction implements ActionListener{
			public void actionPerformed(ActionEvent e) {
			String ctg= (String) JOptionPane.showInputDialog(null, 
			        "Please, select a category for search.",
			        "Driving license categories",
			        JOptionPane.INFORMATION_MESSAGE, 
			        null, 
			        licenseCategories, 
			        licenseCategories[0]);
		String sql="SELECT * FROM DELIVERERS ORDER BY FNAME,LNAME";
		conn=DBConnector.getConnection();
		try {
			state=conn.prepareStatement(sql);
			state.execute();
			delivererSearchTable.setModel(DBConnector.searchDeliverer(ctg));
			deliverersSearchPanel.setVisible(true);
			hideSDTable.setVisible(true);
			} 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
		}
	  }//end SearchDelivererAction
	
	class HideSDTableAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			deliverersSearchPanel.setVisible(false);
			hideSDTable.setVisible(false);
			}
		}
	
	
	class DelivererTableMouseAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=deliverersTable.getSelectedRow();
			 delivererId=Integer.parseInt(deliverersTable.getValueAt(row, 0).toString());
			 if(e.getClickCount()>1) {
			 firstNameTField.setText(deliverersTable.getValueAt(row, 1).toString());
			 lastNameTField.setText(deliverersTable.getValueAt(row, 2).toString());
			 ageTField.setText(deliverersTable.getValueAt(row, 3).toString());
			 phoneTField.setText(deliverersTable.getValueAt(row, 4).toString());
			 emailTField.setText(deliverersTable.getValueAt(row, 5).toString());
			 addressTField.setText(deliverersTable.getValueAt(row, 6).toString());
			categoryCombo.setSelectedItem(deliverersTable.getValueAt(row,7).toString());
			 }
			
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void clearDelivererForm() {
		firstNameTField.setText("");
		lastNameTField.setText("");
		ageTField.setText("");
		phoneTField.setText("");
		emailTField.setText("");
		addressTField.setText("");
		categoryCombo.setSelectedIndex(0);
	}
	
	class AddCompaniesAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			String name = nameTField.getText();
			String country = countryTField.getText();
			String city = cityTField.getText();
			String postalCode = postalCodeTField.getText();
			String about = aboutCombo.getSelectedItem().toString();
			String sql = "INSERT INTO COMPANIES VALUES(null,?,?,?,?,?);";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, name);
				state.setString(2, country);
				state.setString(3, city);
				state.setString(4, postalCode);
				state.setString(5, about);
				state.execute();
				companiesTable.setModel(DBConnector.getAllCompanies());
				JOptionPane.showMessageDialog(null, "You have successfully added a new company!");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			clearCompanyForm();
			companyComboFill();
		}
		
	}//end AddCompaniesAction
	
	class DeleteCompaniesAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
		String sql="DELETE FROM COMPANIES WHERE COMPANY_ID=?";
		conn=DBConnector.getConnection();
		if(companyId>-1) {
			try {
			state=conn.prepareStatement(sql);
			state.setInt(1, companyId);
			state.execute();
			companiesTable.setModel(DBConnector.getAllCompanies());
			JOptionPane.showMessageDialog(null, "You have successfully deleted company with id = "+companyId);
			companyId=-1;
		    } 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
			companyComboFill();
		 }
		else {
			JOptionPane.showMessageDialog(null, "Please select a company to be deleted!");
		}
	  }
	}	//end DeleteCompaniesAction
	class UpdateCompaniesAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(companyId>-1)
			{	
				String name = nameTField.getText();
				String country = countryTField.getText();
				String city = cityTField.getText();
				String postalCode = postalCodeTField.getText();
				String about = aboutCombo.getSelectedItem().toString();
			String sql="UPDATE COMPANIES SET NAME=?,COUNTRY=?,CITY=?,POSTAL_CODE=?, ABOUT=? WHERE COMPANY_ID=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, name);
				state.setString(2, country);
				state.setString(3, city);
				state.setString(4, postalCode);
				state.setString(5, about);
				state.setInt(6, companyId);
				state.execute();
				companiesTable.setModel(DBConnector.getAllCompanies());
				JOptionPane.showMessageDialog(null, "You have successfully updated a company!");	
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			clearCompanyForm();
			companyComboFill();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error! You must choose a company to be updated! ");
			}
		}
	} //end UpdateCompaniesAction
	
	class SearchCompanyAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
		String about= (String) JOptionPane.showInputDialog(null, 
		        "Please, select a category for search.",
		        "Company categories",
		        JOptionPane.INFORMATION_MESSAGE, 
		        null, 
		        companyCategories, 
		        companyCategories[0]);
		String sql="SELECT * FROM COMPANIES ORDER BY COMPANY_ID";
		conn=DBConnector.getConnection();
		try {
			state=conn.prepareStatement(sql);
			state.execute();
			companySearchTable.setModel(DBConnector.searchCompany(about));
			companySearchPanel.setVisible(true);
			hideCompanySearch.setVisible(true);
			} 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
		}
	  }//end SearchCompanyAction
	
	class HideCDTableAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			companySearchPanel.setVisible(false);
			hideCompanySearch.setVisible(false);
			}
		}
	
	class CompaniesTableMouseAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=companiesTable.getSelectedRow();
			 companyId=Integer.parseInt(companiesTable.getValueAt(row, 0).toString());
			 if(e.getClickCount()>1) {
			 nameTField.setText(companiesTable.getValueAt(row, 1).toString());
			 countryTField.setText(companiesTable.getValueAt(row, 2).toString());
			 cityTField.setText(companiesTable.getValueAt(row, 3).toString());
			 postalCodeTField.setText(companiesTable.getValueAt(row, 4).toString());
			 aboutCombo.setSelectedItem(companiesTable.getValueAt(row,5).toString());
			 }
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void clearCompanyForm() {
		nameTField.setText("");
		countryTField.setText("");
		cityTField.setText("");
		postalCodeTField.setText("");
		aboutCombo.setSelectedIndex(0);
	}
	
	class AddShipmentAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
		
			String description = descriptionTField.getText();
			String companyFromString = companyFromCombo.getSelectedItem().toString();
			String companyToString = companyToCombo.getSelectedItem().toString();
			String delivererString = delivererCombo.getSelectedItem().toString();
			String companyFrom=companyFromString.substring(0, companyFromString.indexOf(" "));
			String companyTo=companyToString.substring(0, companyToString.indexOf(" "));
			String deliverer=delivererString.substring(0, delivererString.indexOf(" "));
			Calendar cal = Calendar.getInstance();  
			Timestamp timestamp = new Timestamp(cal.getTimeInMillis());
			String type;
			if(firstRadioBtn.isSelected()==true) {
				type= firstRadioBtn.getText();
			}
			else {
				type= secondRadioBtn.getText();
			}
			String status = statusCombo.getSelectedItem().toString();
			String sql = "INSERT INTO SHIPMENT VALUES(null,?,?,?,?,?,?,?);";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1,description );
				state.setInt(2,Integer.parseInt(companyFrom));
				state.setInt(3,Integer.parseInt(companyTo));
				state.setInt(4,Integer.parseInt(deliverer));
				state.setTimestamp(5,timestamp);
				state.setString(6,type );
				state.setString(7,status);
				
				state.execute();
				shipmentTable.setModel(DBConnector.getAllOrders());
				JOptionPane.showMessageDialog(null, "You have successfully added a new order!");
			} 
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			clearShipmentForm();
		}
		
	}//end AddShipmentAction
	
	class DeleteShipmentAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
		String sql="DELETE FROM SHIPMENT WHERE ID=?";
		conn=DBConnector.getConnection();
		if(orderId>-1) {
			try {
			state=conn.prepareStatement(sql);
			state.setInt(1, orderId);
			state.execute();
			shipmentTable.setModel(DBConnector.getAllOrders());
			JOptionPane.showMessageDialog(null, "You have successfully deleted order with id = "+orderId);
			orderId=-1;
		    } 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
		 }
		else {
			JOptionPane.showMessageDialog(null, "Please select an order to be deleted!");
		}
		
	  }
	}	//end DeleteShipmentAction
	
	class UpdateShipmentAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if(orderId>-1)
			{	
				String description = descriptionTField.getText();
				String companyFromString = companyFromCombo.getSelectedItem().toString();
				String companyToString = companyToCombo.getSelectedItem().toString();
				String delivererString = delivererCombo.getSelectedItem().toString();
				String companyFrom=companyFromString.substring(0, companyFromString.indexOf(" "));
				String companyTo=companyToString.substring(0, companyToString.indexOf(" "));
				String deliverer=delivererString.substring(0, delivererString.indexOf(" "));
				String type;
				if(firstRadioBtn.isSelected()==true) {
					type= firstRadioBtn.getText();
				}
				else {
					type= secondRadioBtn.getText();
				}
				String status = statusCombo.getSelectedItem().toString();
			String sql="UPDATE SHIPMENT SET DESCRIPTION=?,COMPANY_FROM=?,COMPANY_TO=?,DELIVERER=?, DELIVERY_TYPE=?, STATUS=? WHERE ID=?";
			conn = DBConnector.getConnection();
			try {
				state = conn.prepareStatement(sql);
				state.setString(1, description);
				state.setInt(2, Integer.parseInt(companyFrom));
				state.setInt(3, Integer.parseInt(companyTo));
				state.setInt(4, Integer.parseInt(deliverer));
				state.setString(5, type);
				state.setString(6, status);
				state.setInt(7, orderId);
				state.execute();
				shipmentTable.setModel(DBConnector.getAllOrders());
				JOptionPane.showMessageDialog(null, "You have successfully updated an order!");	
			}
			catch (SQLException e1) {
				e1.printStackTrace();
			}
			finally {
				try {
					state.close();
					conn.close();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
			
			clearShipmentForm();
			}
			else {
				JOptionPane.showMessageDialog(null, "Error! You must choose an order to be updated! ");
			}
		}
	}//end UpdateShipmentAction
	
	class SearchShipmentAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			String deliveryType= (String) JOptionPane.showInputDialog(null, 
			        "Please, select a delivery type for search.",
			        "Delivery types",
			        JOptionPane.INFORMATION_MESSAGE, 
			        null, 
			        radioBtnContent, 
			        radioBtnContent[0]);
			String sql="SELECT DESCRIPTION, COMPANY_FROM AS FROM_CMP,CS.NAME,CS.COUNTRY, SH.COMPANY_TO AS TO_CMP,C.NAME,C.COUNTRY, DELIVERER AS D_ID,D.FNAME,D.LNAME,ORDER_DATE,DELIVERY_TYPE,STATUS" + 
					" FROM SHIPMENT SH JOIN COMPANIES CS" + 
					" ON SH.COMPANY_FROM=CS.COMPANY_ID" + 
					" JOIN COMPANIES C" + 
					" ON SH.COMPANY_TO=C.COMPANY_ID" + 
					" JOIN DELIVERERS D" + 
					" ON SH.DELIVERER=D.ID";
		conn=DBConnector.getConnection();
		try {
			state=conn.prepareStatement(sql);
			state.execute();
			shipmentSearchTable.setModel(DBConnector.searchShipment(deliveryType));
			shipmentSearchPanel.setVisible(true);
			hideShipmentTable.setVisible(true);
			} 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
		}
	  }//end SearchShipmentAction
	
	class HideSSTableAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			shipmentSearchPanel.setVisible(false);
			hideShipmentTable.setVisible(false);
			}
		}//end hideShipmentSearchAction
	
	class ShipmentTableMouseAction implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			int row=shipmentTable.getSelectedRow();
			 orderId=Integer.parseInt(shipmentTable.getValueAt(row, 0).toString());
			
			 if(e.getClickCount()>1) {
			 descriptionTField.setText(shipmentTable.getValueAt(row, 1).toString());
			 companyFromCombo.setSelectedItem((shipmentTable.getValueAt(row, 2)+" "+shipmentTable.getValueAt(row, 3)+" "+shipmentTable.getValueAt(row, 4)).toString());
			 companyToCombo.setSelectedItem((shipmentTable.getValueAt(row, 5)+" "+shipmentTable.getValueAt(row, 6)+" "+shipmentTable.getValueAt(row, 7)).toString());
			 delivererCombo.setSelectedItem((shipmentTable.getValueAt(row, 8)+" "+shipmentTable.getValueAt(row, 9)+" "+shipmentTable.getValueAt(row, 10)).toString());
			
			 if(shipmentTable.getValueAt(row,12).toString().equals("Express")) {
				 firstRadioBtn.setSelected(true);
				 secondRadioBtn.setSelected(false);
			 }
			 else {
				 firstRadioBtn.setSelected(false);
				 secondRadioBtn.setSelected(true);
			 }
			statusCombo.setSelectedItem(shipmentTable.getValueAt(row,13).toString());
			 }
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
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public void clearShipmentForm() {
		descriptionTField.setText("");
		companyFromCombo.setSelectedIndex(0);
		companyToCombo.setSelectedIndex(0);
		delivererCombo.setSelectedIndex(0);
		firstRadioBtn.setSelected(false);
		secondRadioBtn.setSelected(false);
		statusCombo.setSelectedIndex(0);
	}
	
	class SearchInfoAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
		String fName = delivererFNameTField.getText();
		String lName = delivererLNameTField.getText();
		String country = destCountryTField.getText();
		String status = statusInfoCombo.getSelectedItem().toString();
		String sql="SELECT SH.ID,DESCRIPTION,C.NAME AS FROM_COMPANY,C.CITY,"
				+ "C.COUNTRY AS FROM_DESTINATION,CS.NAME AS TO_COMPANY,"
				+ "CS.CITY,CS.COUNTRY AS TO_DESTINATION, D.FNAME, D.LNAME, STATUS " + 
				"FROM SHIPMENT SH JOIN COMPANIES C " + 
				"ON SH.COMPANY_FROM=C.COMPANY_ID " + 
				"JOIN COMPANIES CS " + 
				"ON SH.COMPANY_TO=CS.COMPANY_ID " + 
				"JOIN DELIVERERS D " + 
				"ON SH.DELIVERER=D.ID " + 
				"WHERE STATUS='"+status+"' AND D.FNAME='"+
				fName+"' AND D.LNAME='"+lName+"' AND CS.COUNTRY='"
				+country+"'"+
				" ORDER BY 1";
		conn=DBConnector.getConnection();
		try {
			state=conn.prepareStatement(sql);
			state.execute();
			infoTable.setModel(DBConnector.searchShipmentInfo(fName,lName,country,status));
			} 
			catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			}	
		clearInfoForm();
		
		}
	  }//end SearchDelivererAction
	
	public void clearInfoForm() {
		delivererFNameTField.setText("");		
		delivererLNameTField.setText("");
		destCountryTField.setText("");
		statusInfoCombo.setSelectedIndex(0);
	}
	
	class GetShipmentTabAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			tabs.setSelectedComponent(shipmentPanel);
			}
		}//end GetShipmentTabAction
		
	class GetCompaniesTabAction implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		tabs.setSelectedComponent(companiesPanel);
		}
	}//end GetCompaniesTabAction
	
	class GetDeliverersTabAction implements ActionListener{
	
	public void actionPerformed(ActionEvent e) {
		tabs.setSelectedComponent(deliverersPanel);
		}
	}//end GetDeliverersTabAction
	
	class GetQueryTabAction implements ActionListener{
		
		public void actionPerformed(ActionEvent e) {
			tabs.setSelectedComponent(infoPanel);
			}
		}//end GetQueryTabAction
		
}
