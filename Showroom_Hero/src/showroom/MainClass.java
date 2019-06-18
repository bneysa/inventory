package showroom;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Panel;
import java.awt.BorderLayout;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import com.mysql.cj.xdevapi.Statement;
import com.toedter.calendar.JDateChooser;

import java.awt.Font;
import java.awt.Image;

import javax.swing.JTextPane;
import javax.swing.JTable;
import java.awt.ScrollPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.UIManager;
import javax.swing.ScrollPaneConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;

public class MainClass {

	private JFrame frame;
	private JTable table;
	JTextPane name = new JTextPane();
	JTextPane price = new JTextPane();
	JTextPane id = new JTextPane();
	JDateChooser dateChooser = new JDateChooser();
	String[] name_bike = { "Splendor", "xtreme", "xtreme 200r" };
	JTextPane quantity = new JTextPane();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainClass window = new MainClass();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainClass() {
		initialize();
		getConnection();
		show_products_table();

	}

	public Connection getConnection() {
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/products_db2", "root", "");
			return con;

		} catch (SQLException ex)

		{

			Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
			return null;
		}

	}
	// check input fields

	public boolean checkInputs() {
		if (name.getText() == null || price.getText() == null || dateChooser == null || quantity.getText()==null ) {
			return false;
		} else {
			try {
				Float.parseFloat(price.getText());
				return true;
			} catch (Exception ex) {
				return false;
			}

		}
	}
	
	

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 80, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Panel panel = new Panel();
		panel.setBackground(SystemColor.activeCaption);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel pricel = new JLabel("Price:");
		pricel.setFont(new Font("Tahoma", Font.BOLD, 18));
		pricel.setBounds(73, 109, 83, 14);
		panel.add(pricel);

		JLabel idl = new JLabel("ID:");
		idl.setFont(new Font("Tahoma", Font.BOLD, 18));
		idl.setBounds(96, 33, 41, 14);
		panel.add(idl);

		JLabel namel = new JLabel("Name:");
		namel.setFont(new Font("Tahoma", Font.BOLD, 18));
		namel.setBounds(66, 71, 112, 14);
		panel.add(namel);

		JLabel datel = new JLabel("Add Date:");
		datel.setFont(new Font("Tahoma", Font.BOLD, 18));
		datel.setBounds(41, 150, 125, 14);
		panel.add(datel);

		// name
		name.setFont(new Font("Tahoma", Font.BOLD, 14));
		name.setBounds(132, 68, 148, 30);
		panel.add(name);

		// id

		id.setFont(new Font("Tahoma", Font.BOLD, 14));
		id.setBounds(132, 27, 148, 30);
		panel.add(id);

//		
		// price

		price.setFont(new Font("Tahoma", Font.BOLD, 14));
		price.setBounds(132, 109, 148, 30);
		panel.add(price);
		

		// date
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(132, 149, 148, 30);
		dateChooser.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(dateChooser);



		

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			
				int index = table.getSelectedRow();
		        ShowItem(index);
			
			}
		});
		table.setBounds(364, 23, 421, 328);
		table.setModel(new DefaultTableModel(
			new Object[][] {
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
//				{null, null, null, null, null},
			},
			new String[] {
				"ID", "Name", "Price", "Add Date", "Quantity"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(79);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setPreferredWidth(85);
		table.getColumnModel().getColumn(4).setMinWidth(14);
		table.getColumnModel().getColumn(4).setMaxWidth(88);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(383, 29, 421, 328);
		panel.add(scrollPane);
		scrollPane.setViewportView(table);
		 
		ImageIcon image = new ImageIcon("E:\\java projects\\PAC MAN\\Showroom_Hero\\bin\\image\\add.png");

		JButton insertbutton = new JButton("Insert");

		Image img = image.getImage();
		Image newimg = img.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon = new ImageIcon(newimg);
		insertbutton.setBackground(SystemColor.textHighlight);
		insertbutton.setForeground(SystemColor.inactiveCaptionText);
		insertbutton.setIcon(icon);
		insertbutton.setIconTextGap(8);
		insertbutton.setFont(new Font("Tahoma", Font.BOLD, 13));
		insertbutton.setBounds(41, 410, 106, 30);
		panel.add(insertbutton);

		ImageIcon update_image = new ImageIcon("E:\\java projects\\PAC MAN\\Showroom_Hero\\bin\\image\\update.png");
		JButton update_button = new JButton("Update");
		
		update_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		Image img1 = update_image.getImage();
		Image newimg1 = img1.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon1 = new ImageIcon(newimg1);
		update_button.setBackground(SystemColor.textHighlight);
		update_button.setForeground(SystemColor.inactiveCaptionText);
		update_button.setIcon(icon1);
		update_button.setIconTextGap(8);
		update_button.setBounds(157, 410, 106, 30);
		panel.add(update_button);

		JButton delete_button = new JButton("Delete");
		
		ImageIcon delete_image = new ImageIcon("E:\\java projects\\PAC MAN\\Showroom_Hero\\bin\\image\\delete.png");
		Image img2 = delete_image.getImage();
		Image newimg2 = img2.getScaledInstance(16, 16, java.awt.Image.SCALE_SMOOTH);
		ImageIcon icon2 = new ImageIcon(newimg2);
		delete_button.setIcon(icon2);
		delete_button.setIconTextGap(8);
		delete_button.setForeground(Color.BLACK);
		delete_button.setFont(new Font("Tahoma", Font.BOLD, 13));
		delete_button.setBackground(SystemColor.textHighlight);
		delete_button.setBounds(273, 410, 106, 30);
		panel.add(delete_button);

		
		JLabel choose = new JLabel("Quantity");
		choose.setFont(new Font("Tahoma", Font.BOLD, 18));
		choose.setBounds(41, 188, 83, 22);
		panel.add(choose);
		
		
		quantity.setFont(new Font("Tahoma", Font.BOLD, 14));
		quantity.setBounds(132, 190, 148, 30);
		panel.add(quantity);
		
		JLabel label = new JLabel("");
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(41, 239, 83, 22);
		panel.add(label);
		
		
		

		

		
		//insert data in database
		insertbutton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (checkInputs()) {
					try {
						Connection con = getConnection();

						PreparedStatement ps = con.prepareStatement(
								"INSERT INTO products(`name`,`price`,`add_date`,`quantity`)VALUES(?,?,?,?)");

						ps.setString(1, name.getText());
						ps.setString(2, price.getText());

						SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
						String addDate = dateFormat.format(dateChooser.getDate());
						ps.setString(3, addDate);

						ps.setString(4, quantity.getText());
						ps.executeUpdate();
						show_products_table();

						JOptionPane.showMessageDialog(null, "Data Inserted");

					} catch (SQLException ex) {
						Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);

					}

				} else

				{
					JOptionPane.showMessageDialog(null, "One or More field Are Missing");
				}
				System.out.println(name.getText());
				System.out.println(price.getText());
				System.out.println(dateChooser.getDate());
				System.out.println(quantity.getText());
			
			}
		});
		
		
		update_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
				if(checkInputs() &&id.getText() !=null)
					
				{
					
					String UpdateQuery=null;
					PreparedStatement ps=null;
					Connection con= getConnection();
					
					try
					{
					UpdateQuery="UPDATE products SET name=?,price=?"+", add_date=?,quantity=? WHERE id=?";
				ps=con.prepareStatement(UpdateQuery);
				ps.setString(1,name.getText());
				ps.setString(2, price.getText());
				
				SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
				String addDate=dateFormat.format(dateChooser.getDate());
				ps.setString(3, addDate);
				
				ps.setString(4, quantity.getText());
				
				ps.setInt(5, Integer.parseInt(id.getText()));
				ps.executeUpdate();
				show_products_table();
				
				JOptionPane.showMessageDialog(null, "Data Updated");
					} catch (SQLException ex) {
						Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);

					}
					
					
			
				}
				else
				{
					JOptionPane.showMessageDialog(null, "One or More field Are Missing or wrong");	
				}
			
			
			}
		});
		
		delete_button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			if(!id.getText().equals(""))
			{
				
				try {
					Connection con=getConnection();
	 				PreparedStatement ps=con.prepareStatement("DELETE FROM products WHERE ID=?");
	     			int id1=Integer.parseInt(id.getText());
	     			ps.setInt(1, id1);
	     			ps.executeUpdate();
	     			show_products_table();
	     			JOptionPane.showMessageDialog(null, "Data Deleted");
				
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			else
				JOptionPane.showMessageDialog(null, "Product not deleted");
			
			}
		});
		
	

	}
	
	
	//displaying data in table
		public 	ArrayList<Product> getProductList()
			{
			 ArrayList<Product> productList  = new ArrayList<Product>();
	            Connection con = getConnection();
	            String query = "SELECT * FROM products";
	            
	            java.sql.Statement st;
	            ResultSet rs;
	            
	        try {
	            
	            st = con.createStatement();
	            rs = st.executeQuery(query);
	            Product product;
	            
	            while(rs.next())
	            {
	                product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getInt("quantity"));
	                productList.add(product);
	            }
	            
	        } catch (SQLException ex) {
	            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	        return productList; 
	                
				
			}
		
		//populate jtable
		
		public void show_products_table()
		{
			ArrayList<Product> list=getProductList();
			DefaultTableModel model=(DefaultTableModel)table.getModel();
			model.setRowCount(0);
			Object[] row=new Object[5];
			
			for(int i=0;i <list.size();i++)
			{
				row[0]=list.get(i).getId();
				row[1]=list.get(i).getName();
				row[2]=list.get(i).getPrice();
				row[3]=list.get(i).getAddDate();
				row[4]=list.get(i).getquantity();
				
				model.addRow(row);
				
			}
		}
		
		public void ShowItem(int index)
		{
			
			id.setText(Integer.toString(getProductList().get(index).getId()));
            name.setText(getProductList().get(index).getName());
            price.setText(Float.toString(getProductList().get(index).getPrice()));
            quantity.setText(Integer.toString(getProductList().get(index).getquantity()));
            
           
            
			try
			{
			java.util.Date addDate = null;
	            addDate =  new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
	            dateChooser.setDate(addDate);
	        } catch (ParseException ex) {
	            Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        
	       
	    
	    
				
}
		}
		

