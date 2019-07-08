
import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javax.swing.JOptionPane;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author DIPAK
 */
public class hotel_display extends javax.swing.JFrame {

    /**
     * Creates new form hotel_display
     */
    public int f=1;
    public static int ppn=0;
    public static String code=null;
    public hotel_display() {
        initComponents();
        int r=0,g=0,b=0;
        float[] hsb=new float[3];
        r=14;
        g=140;
        b=214;
        Color.RGBtoHSB(r, g, b, hsb);
        logout.setBackground(Color.red);
        book.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        back.setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        r=40;
        g=40;
        b=40;
        Color.RGBtoHSB(r, g, b, hsb);
        this.getContentPane().setBackground(Color.getHSBColor(hsb[0],hsb[1],hsb[2]));
        hname.setText(bookl.selected_hotel.toUpperCase());
        String locate=location.place;
        String hotel=bookl.selected_hotel;
        String amme=null;
        
        int checker[] = new int[location.days];
        for(int i=0;i<location.days;i++)
        {
            checker[i]=10;
        }
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Connection con1=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
            Statement s=con.createStatement();
            Statement s1=con1.createStatement();
            ResultSet rs=null,rs1=null;
            rs=s.executeQuery("select * from "+locate+" where hname='"+hotel+"';");
            while(rs.next())
            {
                rating.setText("Rating : "+rs.getString(4));
                ct.setText("Rated by : "+rs.getString(5));
                brief.setText("Brief Description : "+rs.getString(8));
                amme=rs.getString(7);
                amm.setText("Ammenities : "+amme);
                ppn=rs.getInt(6);
            }
            cin.setText("Check - in : "+location.ind);
            cout.setText("Check - out : "+location.outd);
            rr.setText("Rooms req. : "+location.tr);
            tp.setText("Total price : "+ppn*location.tr*location.days);
            rs=s.executeQuery("select code from "+location.place+" where price="+ppn+";");
            while(rs.next())
            {
                code=rs.getString(1);
            }
            rs=s.executeQuery("select * from "+code+" where status!=-1;");
            String ccin=location.ind;
            String ccout=location.outd;
            int indiff=0,outdiff=0;
            while(rs.next())
            {
                String cind=rs.getString(2);
                String coutd=rs.getString(3);
                int bx=0;
                int room=rs.getInt(5);
                rs1=s1.executeQuery("select datediff('"+ccout+"','"+cind+"');");
                while(rs1.next())
                {
                    bx=rs1.getInt(1);
                }
                rs1=s1.executeQuery("select datediff('"+ccin+"','"+cind+"');");
                while(rs1.next())
                {
                    indiff=rs1.getInt(1);
                }
                rs1=s1.executeQuery("select datediff('"+ccout+"','"+coutd+"');");
                while(rs1.next())
                {
                    outdiff=rs1.getInt(1);
                }
                if(indiff>=0 && outdiff<=0)
                {
                    for(int i=0;i<location.days;i++)
                    {
                        checker[i]-=room;
                    }
                }
                else if(indiff>=0 && outdiff>0)
                {
                    int temp=0;
                    rs1=s1.executeQuery("select datediff('"+coutd+"','"+ccin+"');");
                    while(rs1.next())
                    {
                       temp=rs1.getInt(1);
                    }
                    for(int i=0;i<temp;i++)
                    {
                        checker[i]-=room;
                    }
                }
                else if(indiff<0 && outdiff<=0 && bx>0)
                {
                    for(int i=-indiff;i<location.days;i++)
                    {
                        checker[i]-=room;
                    }
                }
                else if(indiff<0 && outdiff>0)
                {
                    for(int i=-indiff;i<(location.days-outdiff);i++)
                    {
                        checker[i]-=room;
                    }
                }
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        for(int i=0;i<location.days;i++)
            {
                if(checker[i]<location.tr)
                {
                    f=0;
                    break;
                }
            }
            if(f==0)
            {
                book.setText("Join Waiting List");
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

        hname = new javax.swing.JLabel();
        back = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        brief = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        amm = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        rating = new javax.swing.JLabel();
        ct = new javax.swing.JLabel();
        cin = new javax.swing.JLabel();
        rr = new javax.swing.JLabel();
        cout = new javax.swing.JLabel();
        tp = new javax.swing.JLabel();
        book = new javax.swing.JToggleButton();
        logout = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 204, 204));

        hname.setFont(new java.awt.Font("Caviar Dreams", 1, 24)); // NOI18N
        hname.setForeground(new java.awt.Color(255, 255, 255));
        hname.setText("Hname");

        back.setFont(new java.awt.Font("Caviar Dreams", 1, 48)); // NOI18N
        back.setForeground(new java.awt.Color(255, 255, 255));
        back.setText("←");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        brief.setFont(new java.awt.Font("Caviar Dreams", 1, 21)); // NOI18N
        brief.setForeground(new java.awt.Color(255, 255, 255));
        brief.setText("Brief");

        amm.setFont(new java.awt.Font("Caviar Dreams", 1, 21)); // NOI18N
        amm.setForeground(new java.awt.Color(255, 255, 255));
        amm.setText("Ammenities");

        rating.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        rating.setForeground(new java.awt.Color(255, 255, 255));
        rating.setText("Rating :");

        ct.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        ct.setForeground(new java.awt.Color(255, 255, 255));
        ct.setText("Count :");

        cin.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        cin.setForeground(new java.awt.Color(255, 255, 255));
        cin.setText("Check - in :");

        rr.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        rr.setForeground(new java.awt.Color(255, 255, 255));
        rr.setText("Rooms req.");

        cout.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        cout.setForeground(new java.awt.Color(255, 255, 255));
        cout.setText("Check - out :");

        tp.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        tp.setForeground(new java.awt.Color(255, 0, 51));
        tp.setText("Total Price :");

        book.setFont(new java.awt.Font("Caviar Dreams", 1, 18)); // NOI18N
        book.setForeground(new java.awt.Color(255, 255, 255));
        book.setText("Proceed to Book");
        book.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bookActionPerformed(evt);
            }
        });

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/1541829391162 (1).png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 1066, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(amm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(brief, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(11, 11, 11)
                        .addComponent(rr, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cout, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(33, 33, 33))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(hname, javax.swing.GroupLayout.PREFERRED_SIZE, 510, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(86, 86, 86)
                        .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(38, 38, 38)
                        .addComponent(ct, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(back)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logout)
                        .addGap(31, 31, 31))))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(392, 392, 392)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(book, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 233, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(4, 4, 4))))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logout, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(hname, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rating, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ct, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(brief, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(amm, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cin, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rr, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cout, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(tp, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(book, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        bookl a=new bookl();
        this.setVisible(false);
        a.setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void bookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bookActionPerformed
        // TODO add your handling code here:
        if(f==1)
        {
            finalize a = new finalize();
            this.setVisible(false);
            a.setVisible(true);
        }
        else
        {
            
            try
            {
                Class.forName("com.mysql.jdbc.Driver");
                Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/oop?zeroDateTimeBehavior=convertToNull","root","12345");
                Statement s=con.createStatement();
                ResultSet rs=null;
                String uid=null;
                rs=s.executeQuery("select * from logindata where active=1;");
                while(rs.next())
                {
                    uid=rs.getString(1);
                }
                DateTimeFormatter dtf=DateTimeFormatter.ofPattern("yyMMddHHmmss");
		LocalDateTime now = LocalDateTime.now();
                String bid="WTL"+dtf.format(now);
                System.out.println(uid);
                System.out.println("insert into "+uid+" values('"+location.place+"','"+bookl.selected_hotel+"','"+location.ind+"','"+location.outd+"',"+location.days+","+location.tr+","+hotel_display.ppn*location.tr*location.days+",-1);");
                System.out.println("insert into "+hotel_display.code+" values('"+uid+"','"+location.ind+"','"+location.outd+"',"+location.days+","+location.tr+",-1);");
                s.execute("insert into "+uid+" values('"+location.place+"','"+bookl.selected_hotel+"','"+location.ind+"','"+location.outd+"',"+location.days+","+location.tr+","+hotel_display.ppn*location.tr*location.days+",-1,'"+bid+"');");
                s.execute("insert into "+hotel_display.code+" values('"+uid+"','"+location.ind+"','"+location.outd+"',"+location.days+","+location.tr+",-1,'"+bid+"');");
               
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
            JOptionPane.showMessageDialog(this,"Joined Waiting list. Check Waiting list for further notifications!");
            menupage a =new menupage();
            this.setVisible(false);
            a.setVisible(true);
        }
    }//GEN-LAST:event_bookActionPerformed

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
            java.util.logging.Logger.getLogger(hotel_display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(hotel_display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(hotel_display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(hotel_display.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new hotel_display().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel amm;
    private javax.swing.JButton back;
    private javax.swing.JToggleButton book;
    private javax.swing.JLabel brief;
    private javax.swing.JLabel cin;
    private javax.swing.JLabel cout;
    private javax.swing.JLabel ct;
    private javax.swing.JLabel hname;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JButton logout;
    private javax.swing.JLabel rating;
    private javax.swing.JLabel rr;
    private javax.swing.JLabel tp;
    // End of variables declaration//GEN-END:variables
}
