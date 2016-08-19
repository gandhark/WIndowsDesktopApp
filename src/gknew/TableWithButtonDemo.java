/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gknew;

/**
 *
 * @author abc
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author gandhark
 */
//import static dummy.TestTableSortFilter.buildTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class TableWithButtonDemo extends JPanel 
{
 
  
        private Object[][] data;

        String url = "jdbc:mysql://localhost:3306/customerdata";
        String userid = "root";
        String password = "";
        String sql = "SELECT * FROM customer";
        Connection connection = DriverManager.getConnection( url, userid, password );
        PreparedStatement preparedStatement = null;

        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery( sql );

        
        String updatequery;
    
  
            private JFrame frame = new JFrame("Table Demo");       
          
         
            private TableFromMySqlDatabase td= new TableFromMySqlDatabase();
            private JTable table = new JTable(buildTableModel(rs));
            
            
            
            
            
            
             TableColumnModel tcm = table.getColumnModel();

   // JOptionPane.showMessageDialog(null, new JScrollPane(table));
    
    
    
    
  // private JTable jTable = new JTable(model);

    private TableRowSorter<TableModel> rowSorter
            = new TableRowSorter<>(table.getModel());

    private JTextField jtfFilter = new JTextField();
    private JButton jbtFilter = new JButton("Filter");

    
            
            
            

    public void sortData() throws SQLException
    {
       //System.out.println( Arrays.toString(td.mymethod()));
    }
  
    public static DefaultTableModel buildTableModel(ResultSet rs)
        throws SQLException {

    ResultSetMetaData metaData = rs.getMetaData();

    // names of columns
    Vector<String> columnNames = new Vector<String>();
    int columnCount = metaData.getColumnCount();
   // columnCount=columnCount+1;
    for (int column = 1; column <= columnCount; column++) {
        columnNames.add(metaData.getColumnName(column));
    }
     columnNames.add("Balance_Ammount");
    // data of the table
     Object value=null;
    Vector<Vector<Object>> data = new Vector<Vector<Object>>();
    while (rs.next()) {
       int kk=0;
       
       Object value1=0;
       Object value2=0;
       
        Vector<Object> vector = new Vector<Object>();
        for (int columnIndex = 1; columnIndex <= columnCount; columnIndex++) {
          
            
            
            vector.add(rs.getObject(columnIndex));
            
            if(columnIndex==7)
            {
                value2=rs.getObject(columnIndex);
                System.err.println(value2);  
            }
            
            
            if(columnIndex==8)
            {
                
                
                
                value1 =  rs.getObject(columnIndex);
                System.err.println(value1);  
            }
            
            
            value=(int)value1 - (int)value2;
            System.out.println("balance is "+value);
            
            
            
        }
        
        
            
            
            
            
           // table.setValueAt(value, kk, 10);
            //kk++;
            
            
        
        
        
        
        vector.add(value);
        data.add(vector);
    }

    return new DefaultTableModel(data, columnNames);

}
    
    
  public TableWithButtonDemo() throws SQLException 
  {
    
      this.data = new Object[][]{            
        };
       //  System.out.println(Arrays.toString(td.mymethod()));
        table.setRowSorter(rowSorter);

        JPanel panel = new JPanel(new BorderLayout());
        panel.add(new JLabel("Specify a word to match:"),
                BorderLayout.WEST);
        panel.add(jtfFilter, BorderLayout.CENTER);

        setLayout(new BorderLayout());
        add(panel, BorderLayout.SOUTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        jtfFilter.getDocument().addDocumentListener(new DocumentListener(){

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = jtfFilter.getText();

                if (text.trim().length() == 0) {
                    rowSorter.setRowFilter(null);
                } else {
                    rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

        });
        //tcm.removeColumn(tcm.getColumn(9));
        
        
      
      
      
      
      
      table.getColumnModel().getColumn(9).setCellRenderer(new ClientsTableButtonRenderer());
    table.getColumnModel().getColumn(9).setCellEditor(new ClientsTableRenderer(new JCheckBox()));
    table.setPreferredScrollableViewportSize(table.getPreferredSize());
    table.setShowHorizontalLines(true);
    table.setShowVerticalLines(false);

   /* JScrollPane scroll = new JScrollPane(table);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.add(scroll);
    frame.pack();
    frame.setLocation(150, 150);
    frame.setVisible(true);*/
  }

  
  
  
  
  
  
  
  
  
  
  
  
  
  public static void main(String[] args) throws Exception
  {
    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    /*EventQueue.invokeLater(new Runnable()
    {
      public void run()
      {
          try {
              
              
              new TableWithButtonDemo().sortData();
          } catch (SQLException ex) {
              Logger.getLogger(TableWithButtonDemo.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    });
    
    */
    
    SwingUtilities.invokeLater(new Runnable(){
            public void run() {
                try {
                    JFrame frame = new JFrame("Row Filter");
                    
                    TableWithButtonDemo u=new TableWithButtonDemo();
                    u.sortData();
                    //frame.add(table);
                    frame.add(u);
                    frame.pack();
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(TableWithButtonDemo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        });
    
    
       /* try
        {
        new TableWithButtonDemo().sortData();
        }
        catch (Exception e)
        {
            System.out.println(e);
                }*/
    
    
    
  }

  class ClientsTableButtonRenderer extends JButton implements TableCellRenderer
  {
    public ClientsTableButtonRenderer()
    {
      setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
    {
      
        
        setForeground(Color.black);
      //setBackground(UIManager.getColor("Button.background"));
      setBackground(UIManager.getColor("Button.background"));
      setText((value == null) ? "" : value.toString());
      return this;
    }
  }
  public class ClientsTableRenderer extends DefaultCellEditor
  {
    private JButton button;
    private String label;
    private boolean clicked;
    private int row, col;
    private JTable table;

    public ClientsTableRenderer(JCheckBox checkBox)
    {
      super(checkBox);
      button = new JButton();
      button.setOpaque(true);
      button.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          fireEditingStopped();
        }
      });
    }
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column)
    {
      this.table = table;
      this.row = row;
      this.col = column;

      button.setForeground(Color.black);
      button.setBackground(UIManager.getColor("Button.background"));
      label = (value == null) ? "" : value.toString();
      button.setText(label);
      clicked = true;
      return button;
    }
    public Object getCellEditorValue()
    {
      if (clicked)
      {
        
          try {
              String cllname=table.getColumnName(1);
              String cllnameprimarykey=table.getColumnName(0);
              
              System.out.println(cllname+"\t edited columnname custname");
              System.out.println(cllnameprimarykey+"\t primarykey");
              String dd=(String) table.getValueAt(row, 1);
              System.out.println(table.getValueAt(row, 1)+"\t updated value custname");
              
              /*updatequery="'update customer set'+cllname+ '='+table.getValueAt(row, 1)+'where' +cllnameprimarykey+'='+table.getValueAt(row, 0)";
              

*/
              
             
             
              //updatequery="update customer set custname='gandhar' where billno ="+table.getValueAt(row, 0);
              //stmt.executeUpdate(updatequery);
              
              //updatequery="update customer set custname = ?,custaddress = ? ,idolammountpaid = ? where billno = ? ";
              
              
              updatequery="update customer set custname = ?,custaddress = ? , custcontactno = ? ,idolammountpaid = ? where billno = ? ";
              
              
              preparedStatement = connection.prepareStatement(updatequery);

		//int idolamountpaid=table.getValueAt(row, 6);	
             // int dolamountpaid=table.getValueAt(row, 7);	
              
              preparedStatement.setString(1, (String) table.getValueAt(row, 1));
              preparedStatement.setString(2, (String) table.getValueAt(row, 2));
              preparedStatement.setString(3, (String) table.getValueAt(row, 8));
              preparedStatement.setInt(4, (int) table.getValueAt(row, 7));
              preparedStatement.setInt(5, (int) table.getValueAt(row, 0));
              
                        System.err.println("gk");
                        System.out.println();
			
			// execute update SQL stetement
			preparedStatement.executeUpdate();

                        //int tp=(int) table.getValueAt(row, 7);
                        
                        
                        
              
                        //here forcefully asked to set value as fullpayment.
                        table.setValueAt(table.getValueAt(row, 7), row,6 );
                        int t=(int)table.getValueAt(row, 7)- (int) table.getValueAt(row, 6);
              table.setValueAt(t, row,9 );
              
              
              
              JOptionPane.showMessageDialog(button, "Column with Value: "+table.getValueAt(row, 1) + " -  Clicked!");
          } catch (SQLException ex) {
              Logger.getLogger(TableWithButtonDemo.class.getName()).log(Level.SEVERE, null, ex);
              System.out.println(ex);
          
          }
      }
      clicked = false;
      return new String(label);
    }

    public boolean stopCellEditing()
    {
      clicked = false;
      return super.stopCellEditing();
    }

    protected void fireEditingStopped()
    {
      super.fireEditingStopped();
    }
  }

}

