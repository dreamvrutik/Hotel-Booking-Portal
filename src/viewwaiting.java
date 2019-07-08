
import java.awt.Color;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIPAK
 */
public class viewwaiting extends javax.swing.JFrame {

    /**
     * Creates new form viewwaiting
     */
    public static int price=0;
    int status=0;
    public String uid=null,locate=null,hotel=null,checkin=null,checkout=null,bid=null;
    public int rr=0,ds=0;
    public viewwaiting() {
        initComponents();
        int r1=0,g=0,b=0;
        float[] hsb=new float[3];
        r1=14;
        g=140;
        b=214;
        Color.RGBtoHSB(r1, g, b, hsb);
        logout.setBackground(Color.red);
        cancel.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        confirm.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        back.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        r1=40;
        g=40;
        b=40;
        Color.RGBtoHSB(r1, g, b, hsb);
        this.getContentPane().setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        cancel.setVisible(false);
        confirm.setVisible(false);
        int ct=0;
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
            String currentDate=formatter.format(date);
            int temp=0;
            rs=s.executeQuery("select count(*) from "+uid+" where status!=1;");
            while(rs.next())
            {
                ct=rs.getInt(1);
            }
            if(ct==0){
                menupage a = new menupage();
                this.setVisible(false);
                a.setVisible(true);
            }
            Object[] cname={"Location ","Hotel Name","Check in","Check out","Days","Rooms booked","Total Price","Status","Waiting ID"};
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
                if(temp>=0 && rs.getInt(8)!=1)
                {
                    for(int x=1;x<10;x++)
                    {
                        if(x==8)
                        {
                            status=rs.getInt(8);
                            if(status==0)
                            {
                                table.setValueAt("CONFIRMED",r, x-1);
                            }
                            if(status==-1)
                            {
                                table.setValueAt("PENDING", r, x-1);
                            }
                        }
                        else
                        {
                            table.setValueAt(rs.getString(x), r, x-1);
                        }
                    }
                    r++;
                }
            }
            table.enable(true);
            table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    String stat=null;
                    if(table.getValueAt(table.getSelectedRow(),table.getSelectedColumn()).toString()!=null)
                    {
                        locate=table.getValueAt(table.getSelectedRow(),0).toString();
                        hotel=table.getValueAt(table.getSelectedRow(),1).toString();
                        checkin=table.getValueAt(table.getSelectedRow(),2).toString();
                        checkout=table.getValueAt(table.getSelectedRow(),3).toString();
                        price=Integer.parseInt(table.getValueAt(table.getSelectedRow(),6).toString());
                        rr=Integer.parseInt(table.getValueAt(table.getSelectedRow(),5).toString());
                        ds=Integer.parseInt(table.getValueAt(table.getSelectedRow(),4).toString());
                        bid=table.getValueAt(table.getSelectedRow(),8).toString();
                        stat=table.getValueAt(table.getSelectedRow(),7).toString();
                    }
                    
                    if(locate!=null&&stat!="PENDING")
                    {
                        cancel.setVisible(true);
                        confirm.setVisible(true);
                    }
                    else if(locate!=null){
                        cancel.setVisible(true);
                        confirm.setVisible(false);
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

        back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        confirm = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        back.setFont(new java.awt.Font("Caviar Dreams", 1, 48)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("←");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        table.setFont(new java.awt.Font("Caviar Dreams", 1, 14)); // NOI18N
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(table);

        confirm.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        confirm.setForeground(new java.awt.Color(255, 255, 255));
        confirm.setText("Confirm");
        confirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                confirmActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        cancel.setForeground(new java.awt.Color(255, 255, 255));
        cancel.setText("Opt-out of Waiting list");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1541829391162 (1).png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Caviar Dreams", 1, 36)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(22, 169, 255));
        jLabel1.setText("WAITING LIST");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(back)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(180, 180, 180)
                .addComponent(logout)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(283, 283, 283)
                        .addComponent(confirm, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(cancel))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(58, 58, 58)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 883, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, Short.MAX_VALUE))
                        .addGap(55, 55, 55))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(confirm)
                    .addComponent(cancel))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        menupage a = new menupage();
        this.setVisible(false);
        a.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            ResultSet rs=null;
            
            String code=null;
            
            rs=s.executeQuery("select code from "+locate+" where hname='"+hotel+"';");
            while(rs.next())
            {
                code=rs.getString(1);
            }
            
            
            s.execute("delete from "+code+" where uid='"+uid+"' and cin='"+checkin+"' and cout='"+checkout+"' and rooms="+rr+" and days="+ds+" and bid='"+bid+"';");
            s.execute("delete from "+uid+" where location='"+locate+"' and hname='"+hotel+"' and cin='"+checkin+"' and cout='"+checkout+"' and rooms="+rr+" and days="+ds+" and bid='"+bid+"';");
            viewwaiting x=new viewwaiting();
            this.setVisible(false);
            x.setVisible(true);
        }
        catch(Exception  e)
        {
            System.out.println(e);
        }
    }//GEN-LAST:event_cancelActionPerformed

    private void confirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_confirmActionPerformed
        // TODO add your handling code here:
        location.place=locate;
        bookl.selected_hotel=hotel;
        location.ind=checkin;
        location.outd=checkout;
        location.tr=rr;
        hotel_display.ppn=price/(ds*rr);
        location.days=ds;
        
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            ResultSet rs=null;
            
            int temp=0;
            String code=null;
            
            rs=s.executeQuery("select code from "+locate+" where hname='"+hotel+"';");
            while(rs.next())
            {
                code=rs.getString(1);
            }
            
            hotel_display.code=code;
            System.out.println("delete from "+code+" where uid='"+uid+"' and cin='"+checkin+"' and cout='"+checkout+"' and status=0 and days="+ds+" and rooms="+rr+";");
            System.out.println("delete from "+uid+" where location='"+locate+"' and hname='"+hotel+"' and cin='"+checkin+"'and cout='"+checkout+"' and status=0 and days="+ds+" and rooms="+rr+";");
            s.execute("delete from "+code+" where uid='"+uid+"' and cin='"+checkin+"' and cout='"+checkout+"' and status=0 and days="+ds+" and rooms="+rr+" and bid='"+bid+"';");
            s.execute("delete from "+uid+" where location='"+locate+"' and hname='"+hotel+"' and cin='"+checkin+"'and cout='"+checkout+"' and status=0 and days="+ds+" and rooms="+rr+" and bid='"+bid+"';");
        }
        catch(Exception  e)
        {
            System.out.println(e);
        }
        
        
        finalize x=new finalize();
        this.setVisible(false);
        x.setVisible(true);
        login.fback=1;
    }//GEN-LAST:event_confirmActionPerformed

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
            java.util.logging.Logger.getLogger(viewwaiting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(viewwaiting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(viewwaiting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(viewwaiting.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new viewwaiting().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JButton cancel;
    private javax.swing.JButton confirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton logout;
    private javax.swing.JTable table;
    // End of variables declaration//GEN-END:variables
}
