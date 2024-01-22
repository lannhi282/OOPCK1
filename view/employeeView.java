package view;

import controller.DAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Employee;

public class employeeView extends javax.swing.JFrame {
    // Các biến thành viên

    private ArrayList<Employee> list;
    DefaultTableModel model;

     // Phương thức khởi tạo của lớp EmployeeView.
    public employeeView() {
        initComponents();
        // Đặt vị trí cửa sổ ở giữa màn hình
        this.setLocationRelativeTo(null);

        // Lấy danh sách nhân viên từ DAO
        list = new DAO().getListEmployee();
        model = (DefaultTableModel) jTable1.getModel();

        model.setColumnIdentifiers(new Object[]{
            "STT", "ID", "Full Name", "Date of birth", "Address", "Phone number", "Email", "Wage", "DaysWorked", "Total Salary"
        });

        // Hiển thị bảng
        showTable();// hien thi thong tin trong danh sach

        // Thêm lắng nghe sự kiện cho việc chọn dòng trong bảng -> có nghĩa khi mà click vào một nhân viên nó hiện các dữ liệu lên các textField
        jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent event) {
                if (!event.getValueIsAdjusting() && jTable1.getSelectedRow() != -1) {
                    int selectedRowIndex = jTable1.getSelectedRow();
                    displayStudentDetails(selectedRowIndex);
                }
            }
        });
    }
     //Phương thức hiển thị thông tin chi tiết của một nhân viên khi được chọn trong bảng.
     // @param rowIndex Chỉ số của dòng được chọn trong bảng.
     
    private void displayStudentDetails(int rowIndex) {
        Employee selectedEmp = list.get(rowIndex);
        txtid.setText(selectedEmp.getName());
        txtname.setText(selectedEmp.getAddress());
        txtdob.setText(selectedEmp.getID());
        txtaddress.setText(selectedEmp.getEmail());
        txtphonenumber.setText(selectedEmp.getPhone());
        txtemail.setText(selectedEmp.getDob().toString());
        txtWage.setText(String.valueOf(selectedEmp.getWage()));
        txtDaysWorked.setText(String.valueOf(selectedEmp.getDaysWorked()));
    }
    
     //Phương thức hiển thị dữ liệu của một đối tượng Employee lên các trường nhập liệu.
      //@param emp Đối tượng nhân viên cần hiển thị.
   
    private void model(Employee s) {
        txtid.setText(s.getName());
        txtname.setText(s.getAddress());
        txtdob.setText(s.getID());
        txtaddress.setText(s.getEmail());
        txtphonenumber.setText(s.getPhone());
        txtemail.setText(s.getDob().toString());
        txtWage.setText(String.valueOf(s.getWage()));
        txtDaysWorked.setText(String.valueOf(s.getDaysWorked()));
    }

     //Phương thức tính tổng lương của một nhân viên dựa trên lương và số ngày làm việc.
     // @return Tổng lương của nhân viên.
     
    public double calculateTotalSalary(Employee s) {
        return s.getWage() * s.getDaysWorked();
    }

     //Phương thức hiển thị dữ liệu của tất cả nhân viên trong bảng.
   
    public void showTable() {
        for (Employee s : list) {
            model.addRow(new Object[]{
                i++, s.getID(), s.getName(), s.getDob(), s.getAddress(), s.getPhone(), s.getEmail(), s.getWage(), s.getDaysWorked(), calculateTotalSalary(s)
            });
        }
    }

     //Phương thức cập nhật thông tin của một nhân viên sau khi sửa đổi.
    
    private void updateEmployee() {
        int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select a student to update.");
            return;
        }

        String empID = (String) jTable1.getValueAt(selectedRow, 1);

        Employee emp = getEmpployeeById(empID);

        emp.setName(txtid.getText());
        emp.setAddress(txtname.getText());
        emp.setEmail(txtaddress.getText());
        emp.setWage(Double.parseDouble(txtWage.getText()));
        emp.setPhone(txtphonenumber.getText());
        emp.setDaysWorked(Integer.parseInt(txtDaysWorked.getText()));

        try {
            emp.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(txtemail.getText()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }

        // Call the updateStudent method in DAO
        DAO dao = new DAO();
        if (dao.updateEmployee(emp)) {
            JOptionPane.showMessageDialog(rootPane, "Update successful!");
            // Update the table display
            model.setValueAt(emp.getName(), selectedRow, 2);
            model.setValueAt(emp.getDob(), selectedRow, 3);
            model.setValueAt(emp.getAddress(), selectedRow, 4);
            model.setValueAt(emp.getPhone(), selectedRow, 5);
            model.setValueAt(emp.getEmail(), selectedRow, 6);
            model.setValueAt(emp.getWage(), selectedRow, 7);
            model.setValueAt(emp.getDaysWorked(), selectedRow, 8);
            model.setValueAt(calculateTotalSalary(emp), selectedRow, 9);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Failed to update student.");
        }
    }

      //Phương thức lấy đối tượng nhân viên dựa trên ID của nhân viên. ( tạo hàm cho công việc update gọi lại và thực hiện )
     // @param empID ID của nhân viên cần lấy.
     //@return Đối tượng nhân viên hoặc null nếu không tìm thấy.

    private Employee getEmpployeeById(String empID) {
        for (Employee s : list) {
            if (s.getID().equals(empID)) {
                return s;
            }
        }
        return null;
    }

    @SuppressWarnings("unchecked")
   // Các TPhan
    private void initComponents() {
    	
    	//Các Lable của Attribute
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        
        //Text Field các Attribute
        txtid = new javax.swing.JTextField();
        txtname = new javax.swing.JTextField();
        txtdob = new javax.swing.JTextField();
        txtaddress = new javax.swing.JTextField();
        txtphonenumber = new javax.swing.JTextField();
        txtemail = new javax.swing.JTextField();
        txtWage = new javax.swing.JTextField();
        txtDaysWorked = new javax.swing.JTextField();
        
        //Các Button
        btnAdd = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSearch = new javax.swing.JToggleButton();
        btnExit = new javax.swing.JButton();
        
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TT Nhân Viên");
        //Set cỡ chữ 18
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); 
        //Set màu
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        
        //Đặt văn bản cho các jLabel
        jLabel1.setText("THÔNG TIN NHÂN VIÊN");

        jLabel2.setText("ID");

        jLabel3.setText("Name");

        jLabel4.setText("Dob");

        jLabel5.setText("Address");

        jLabel6.setText("Phone");

        jLabel7.setText("Email");

        jLabel8.setText("Wage");
        
        jLabel9.setText("DaysWorked");

        txtWage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtWageActionPerformed(evt);
            }
        });
       //Set ICON cho các Button
        
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-plus-math-30.png"))); 
        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-reset-24.png"))); 
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-update-30.png"))); 
        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-delete-30.png"))); 
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSearch.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-search-40.png"))); 
        btnSearch.setText("Search");
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/icons8-exit-30.png"))); 
        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        
        

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            //Đặt lại tên các cột
            new String [] {
                "STT", "ID", "Full name", "Date of birth", "Address", "Phone number", "Email", "Wage", "DaysWorked", "Total Salary"
            }
        ));
        //Cung cấp thanh cuộn cho Table
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtemail, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtphonenumber, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtaddress, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtdob, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtname, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtWage, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDaysWorked, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 25, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(324, 324, 324))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnSearch)
                                .addGap(34, 34, 34)
                                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(37, 37, 37)
                                .addComponent(btnDelete)
                                .addGap(33, 33, 33)
                                .addComponent(btnUpdate)
                                .addGap(37, 37, 37)
                                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(46, 46, 46))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtdob, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtaddress, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtphonenumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtemail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtWage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtDaysWorked, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAdd)
                    .addComponent(btnExit)
                    .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jLabel1.getAccessibleContext().setAccessibleName("THÔNG TIN NHÂN VIÊN");

        pack();
    }                      
//Hiển thị thông tin về các nhân viên trong Table
     int i = 1;

    public void showResult() {
        Employee s = list.get(list.size() - 1);
        model.addRow(new Object[]{
            i++, s.getID(), s.getName(), s.getDob(), s.getAddress(), s.getPhone(), s.getEmail(), s.getWage(), s.getDaysWorked()
        });
    }
    
    //Action của nút RESET ( Khi nút được nhấn)
    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {                                         
        txtname.setText("");
        txtemail.setText("");
        txtaddress.setText("");
        txtdob.setText("");
        txtWage.setText("");
        txtphonenumber.setText("");
        txtid.setText("");
        txtDaysWorked.setText("");
    }                                        

    //Action của nút ADD
    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {                                       
        Employee s = new Employee();
        //Lấy thông tin từ các ô nhập liệu và đặt gtr cho các thuộc tính của NV.
        s.setAddress(txtname.getText());
        s.setEmail(txtaddress.getText());
        s.setID(txtdob.getText());
        s.setWage(Double.parseDouble(txtWage.getText()));
        s.setName(txtid.getText());
        s.setPhone(txtphonenumber.getText());
        s.setDaysWorked(Integer.parseInt(txtDaysWorked.getText()));

        try {
            s.setDob(new SimpleDateFormat("dd/MM/yyyy").parse(txtemail.getText()));
        } catch (ParseException ex) {
            ex.printStackTrace();
        }
     //Thêm Employee vào CSDL:
        if (new DAO().addEmployee(s)) {
            JOptionPane.showMessageDialog(rootPane, "Add Success!");
            list.add(s); // them vao danh sach NV
        } else {
            JOptionPane.showMessageDialog(rootPane, "Employee's ID cannot be duplicated!");
        }
        showResult();
    }                                      

    //Action của nút UPDATE
    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        updateEmployee();
    }                                         

    //Action của nút DELETE
    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
       //Lấy dòng được chọn trong bảng
    	int selectedRow = jTable1.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(rootPane, "Please select a student to delete.");
            return; //KThuc
        }

        //Lấy giá trị của ô trong cột "ID" của dòng được chọn
        //1: Vị trí t2 -> xác định nhân viên cần xóa.
        String studentID = (String) jTable1.getValueAt(selectedRow, 1);

        //Gọi phương thức deleteEmployee từ đối tượng DAO để xóa nhân viên dựa trên ID.
        DAO dao = new DAO();
        // Nếu xoá được
        if (dao.deleteEmployee(studentID)) {
            JOptionPane.showMessageDialog(rootPane, "Delete successful!");
            //Loại bỏ dòng tương ứng khỏi  bảng
            model.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(rootPane, "Failed to delete student.");
        }
    }                                         

    //Action của nút SEARCH
    
    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {     
        //Hiển thị hộp thoại nhập liệu để nhập ID cần tìm kiếm.
        String studentID = JOptionPane.showInputDialog("Enter Student ID to search:");
        //Ktra xem đã nhập ID hay chưa. Nếu đã nhập (không bấm Cancel và chuỗi không rỗng), tiếp tục thực hiện tìm kiếm.
        if (studentID != null && !studentID.isEmpty()) {
        	//Gọi phương thức getEmployeeById từ đối tượng DAO để tìm kiếm NV dựa trên ID.
            DAO dao = new DAO();
            Employee foundStudent = dao.getEmployeeById(studentID);
            if (foundStudent != null) {
                model(foundStudent);
            } else {
                JOptionPane.showMessageDialog(rootPane, "Employee not found.");
            }
        }
    }                                         
   //Action của nút EXIT
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {                                        
       //Hiển thị hộp thoại xác nhận thoát:
    	int choice = JOptionPane.showConfirmDialog(null, "Do you want to close the program?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);

        if (choice == JOptionPane.YES_OPTION) {
            // Khi người dùng chọn "Yes", thoát chương trình
            System.exit(0);//thoát
        }

    }                                       

    private void txtWageActionPerformed(java.awt.event.ActionEvent evt) {                                        
    }                                       
//khởi tạo và hiển thị một giao diện người dùng của lớp employeeView khi ctr được chạy.
    public static void main(String args[]) {
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new employeeView().setVisible(true);
            }
        });
    }
//khai báo và khởi tạo các thành phần giao diện người dùng
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JToggleButton btnSearch;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDaysWorked;
    private javax.swing.JTextField txtWage;
    private javax.swing.JTextField txtaddress;
    private javax.swing.JTextField txtdob;
    private javax.swing.JTextField txtemail;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtname;
    private javax.swing.JTextField txtphonenumber;
}
