import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


class taskInfoDisp extends JFrame implements ActionListener //this class is used for displaying the scheduling table in gantt format
{

    schedule t;
    Button b;
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource()==b)
            this.dispose();
    }
    public taskInfoDisp(schedule s)
    {
//t=new schedule(4,new int[]{5,4,20,20},new double[]{1,1.8,1,2},20);
        t=s;
        t.createSchedulingTable(); //create the table
        b=new Button("Back");
        b.addActionListener(this); //add a button for going back
        setLayout(new BorderLayout());
        add("South",b);



    }






    public void paint(Graphics g) // method for generating graphics
    {
        String taskname="Task_";
        int prevx=0;
        int x=0;
        int y1=200;
        int y2=290;
        super.paint(g);

        g.drawString(" scheduling table / one hyperperiod",0,100);
        g.setColor(Color.red);
        g.drawLine(10,300,this.getWidth(),300);
        g.setColor(Color.black);


        for(entry e: t.schedtable)
        {
            x=(int)(e.time*50);
            g.drawString("T"+Integer.toString(e.name),x,245);
            g.drawString(""+e.time,x,310);
            g.drawLine(x,y1,x,y2);
            g.drawLine(x,y1,prevx,y1);
            prevx=x;


        }





    }





    /*
    public static void main(String args[])
    {
    taskInfoDisp disp=new taskInfoDisp();;
    disp.setTitle("Visualization");
    disp.setSize(500,500);
    disp.setVisible(true);
    //disp.setResizable(false);
    disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



    }


    */








}