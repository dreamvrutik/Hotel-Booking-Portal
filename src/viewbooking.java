
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIPAK
 */
public class viewbooking extends javax.swing.JFrame {

    /**
     * Creates new form viewbooking
     * 
     */
    int ct=0;
    public static int price=0;
    public static String uid=null,locate=null,hotel=null,checkin=null,checkout=null,currentDate=null,bid=null;
    public static int rr=0,ds=0;
    public viewbooking() {
        initComponents();
        int r1=0,g=0,b=0;
        float[] hsb=new float[3];
        r1=14;
        g=140;
        b=214;
        Color.RGBtoHSB(r1, g, b, hsb);
        logout.setBackground(Color.red);
        cancel.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        modify.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        back.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        r1=40;
        g=40;
        b=40;
        Color.RGBtoHSB(r1, g, b, hsb);
        this.getContentPane().setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        cancel.setVisible(false);
        modify.setVisible(false);
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            ResultSet rs=null,rs1=null;
            rs=s.executeQuery("select * from logindata where active=1;");
            Statement s1=con.createStatement();
            while(rs.next())
            {
                uid=rs.getString(1);
            }
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = new Date();  
            currentDate=formatter.format(date);
            int temp=0;
            rs=s.executeQuery("select count(*) from "+uid+" where status=1;");
            while(rs.next())
            {
                ct=rs.getInt(1);
            }
            System.out.println(ct+10);
            Object[] cname={"Location ","Hotel Name","Check in","Check out","Days","Rooms booked","Total Price","Booking ID"};
            DefaultTableModel model=new DefaultTableModel(cname,ct);
            table.setModel(model);
            rs=s.executeQuery("select * from "+uid+";");
            int r=0;
            while(rs.next())
            {
                rs1=s1.executeQuery("select datediff('"+rs.getString(3)+"','"+currentDate+"');");
                while(rs1.next())
                {
                    temp=rs1.getInt(1);
                }
                if(temp>=0 && rs.getInt(8)==1)
                {
                    for(int x=1;x<10;x++)
                    {
                        if(x<8)
                        table.setValueAt(rs.getString(x), r, x-1);
                        else if(x==9)
                        table.setValueAt(rs.getString(x), r, x-2);
                    }
                    r++;
                }
            }
            table.enable(true);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    
                    if(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString()!=null)
                    {
                        locate=table.getValueAt(table.getSelectedRow(),0).toString();
                        hotel=table.getValueAt(table.getSelectedRow(),1).toString();
                        checkin=table.getValueAt(table.getSelectedRow(),2).toString();
                        checkout=table.getValueAt(table.getSelectedRow(),3).toString();
                        price=Integer.parseInt(table.getValueAt(table.getSelectedRow(),6).toString());
                        ds=Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString());
                        rr=Integer.parseInt(table.getValueAt(table.getSelectedRow(),5).toString());
                        bid=table.getValueAt(table.getSelectedRow(),7).toString();
                    }
                    if(locate!=null)
                    {
                        cancel.setVisible(true);
                        modify.setVisible(true);
                    }
                }
            });
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        cancel = new javax.swing.JButton();
        modify = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        mybooking = new javax.swing.JLabel();

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1541829391162 (1).png"))); // NOI18N

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setFont(new java.awt.Font("Caviar Dreams", 1, 48)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("←");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        table.setBackground(new java.awt.Color(14, 140, 214));
        table.setFont(new java.awt.Font("Caviar Dreams", 1, 12)); // NOI18N
        table.setForeground(new java.awt.Color(255, 255, 255));
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null}
            },
            new String [] {
                "Location", "Hotel Name", "Check-in date", "Check-out date", "Days", "Rooms"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        table.setEnabled(false);
        table.setGridColor(new java.awt.Color(14, 140, 214));
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        cancel.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setText("Cancel Booking");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        modify.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        modify.setForeground(new java.awt.Color(255, 255, 255));
        modify.setText("Modify Booking");
        modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyActionPerformed(evt);
            }
        });

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1541829391162 (1).png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        mybooking.setFont(new java.awt.Font("Caviar Dreams", 1, 36)); // NOI18N
        mybooking.setForeground(new java.awt.Color(22, 169, 255));
        mybooking.setText("MY BOOKINGS");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(cancel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(modify)
                .addGap(176, 176, 176))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 749, Short.MAX_VALUE)))
                .addGap(20, 20, 20))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(mybooking, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(242, 242, 242))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logout)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addComponent(mybooking, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 293, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancel)
                    .addComponent(modify))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        menupage a = new menupage();
        this.setVisible(false);
        a.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
        // TODO add your handling code here:
        
    }//GEN-LAST:event_tableMouseClicked

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed
        // TODO add your handling code here:
        int daysleft=0;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            ResultSet rs=null;
            rs=s.executeQuery("select datediff('"+checkin+"','"+currentDate+"');");
            while(rs.next())
            {
                daysleft=rs.getInt(1);
            }
            if(daysleft<=3)
            {
                JOptionPane.showMessageDialog(this,"MODIFICATION NOT ALLOWED FOR THIS DATE!");
            }
            else
            {
                ModifyBooking abc=new ModifyBooking();
                abc.setVisible(true);
                this.setVisible(false);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

    }//GEN-LAST:event_modifyActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
         
        if(JOptionPane.showConfirmDialog(null, "Are you sure you want Cancel?", "Message", JOptionPane.YES_NO_OPTION) == 0)
        {try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            Statement s1=con.createStatement();
            Statement s2=con.createStatement();
            ResultSet rs=null,rs1=null,rs2=null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  
            Date date = new Date();  
            String currentDate=formatter.format(date);
            int temp=0;
            String code=null;
            
            rs=s.executeQuery("select code from "+locate+" where hname='"+hotel+"';");
            while(rs.next())
            {
                code=rs.getString(1);
            }
            rs1=s1.executeQuery("select datediff('"+checkin+"','"+currentDate+"');");
            while(rs1.next())
            {
                temp=rs1.getInt(1);
            }
            int returnprice=0;
            if(temp>=3)
            {
                returnprice=price;
            }
            else
            {
                returnprice=price/2;
            }
            JOptionPane.showMessageDialog(this,"Total refund Amount : "+returnprice);
            System.out.println("0");
            System.out.println("delete from "+code+" where uid='"+uid+"' and cin='"+checkin+"' and cout='"+checkout+"' and rooms="+rr+" and days="+ds+" and status=1 and bid='"+bid+"';");
            System.out.println("delete from "+uid+" where location='"+locate+"' and hname='"+hotel+"' and cin='"+checkin+"' and cout='"+checkout+"' and status=1 and rooms="+rr+" and days="+ds+" and bid='"+bid+"';");
            s.execute("delete from "+code+" where uid='"+uid+"' and cin='"+checkin+"' and cout='"+checkout+"' and rooms="+rr+" and days="+ds+" and status=1 and bid='"+bid+"';");
            System.out.println("1");
            s.execute("delete from "+uid+" where location='"+locate+"' and hname='"+hotel+"' and cin='"+checkin+"' and cout='"+checkout+"' and status=1 and rooms="+rr+" and days="+ds+" and bid='"+bid+"';");
            ///To update waiting list :
            
            System.out.println("2");
            rs=s.executeQuery("select * from "+code+" where status=-1;");
            System.out.println("3");
            while(rs.next())
            {
                String a=rs.getString(2);
                String up=rs.getString(1);
                System.out.println(up);
                String b=rs.getString(3);
                String cbid=rs.getString(7);
                int rr=rs.getInt(5);
                int days=rs.getInt(4);
                int checker[]=new int[days];
                int f=1;
                for(int i=0;i<days;i++)
                {
                    checker[i]=10;
                }
                rs1=s1.executeQuery("select * from "+code+" where status!=-1;");
                System.out.println("4");
                while(rs1.next())
                {
                    String x=rs1.getString(2);
                    String y=rs1.getString(3);
                    int room=rs1.getInt(5);
                    int ax=0,by=0,bx=0,ya=0;
                    rs2=s2.executeQuery("select datediff('"+a+"','"+x+"');");
                    while(rs2.next())
                    {
                        ax=rs2.getInt(1);
                    }
                    rs2=s2.executeQuery("select datediff('"+b+"','"+y+"');");
                    while(rs2.next())
                    {
                        by=rs2.getInt(1);
                    }
                    rs2=s2.executeQuery("select datediff('"+b+"','"+x+"');");
                    while(rs2.next())
                    {
                        bx=rs2.getInt(1);
                    }
                    rs2=s2.executeQuery("select datediff('"+y+"','"+a+"');");
                    while(rs2.next())
                    {
                        ya=rs2.getInt(1);
                    }
                    if(ax>=0 && by<=0)
                    {
                        for(int i=0;i<days;i++)
                        {
                            checker[i]-=room;
                        }
                    }
                    else if(ax>=0 && by>0)
                    {
                        
                        for(int i=0;i<ya;i++)
                        {
                            checker[i]-=room;
                        }
                    }
                    else if(ax<0 && by<=0 && bx>0)
                    {
                        for(int i=-ax;i<days;i++)
                        {
                            checker[i]-=room;
                        }
                    }
                    else if(ax<0 && by>0)
                    {
                        for(int i=-ax;i<(days-by);i++)
                        {
                            checker[i]-=room;
                        }
                    }
                }
                for(int i=0;i<days;i++)
                {
                    if(checker[i]<rr)
                    {
                        f=0;
                    }
                }
                System.out.println("Checked f="+f);
                if(f==1)
                {
                    System.out.println("update "+up+" set status=0 where cin='"+a+"' and cout='"+b+"' and rooms="+rr+" and status=-1 and bid=='"+cbid+"';");
                    System.out.println("update "+code+" set status=0 where uid='"+up+"' and cin='"+a+"' and cout='"+b+"' and rooms="+rr+" and status=-1 and bid='"+cbid+"';");
                    s1.execute("update "+up+" set status=0 where cin='"+a+"' and cout='"+b+"' and rooms="+rr+" and status=-1 and bid='"+cbid+"';");
                    s1.execute("update "+code+" set status=0 where uid='"+up+"' and cin='"+a+"' and cout='"+b+"' and rooms="+rr+" and status=-1 and bid='"+cbid+"';");
                }
            }
            
            
            
            
            ///
            
            
                 viewbooking a =new viewbooking();
                 this.setVisible(false);
                  a.setVisible(true);
        }
        catch(Exception e)
        {
            System.out.println(e);
            
        }}
    }//GEN-LAST:event_cancelActionPerformed

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed
        // TODO add your handling code here:
        login a =new login();
        this.setVisible(false);
        a.setVisible(true);
    }//GEN-LAST:event_logoutActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(viewbooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewbooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewbooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewbooking.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {
            
            public void run() {
                new viewbooking().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton cancel;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JButton modify;
    private javax.swing.JLabel mybooking;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
