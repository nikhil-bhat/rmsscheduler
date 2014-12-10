import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
//simulator for the clock driven scheduler
class clkdrivenShedAnimation extends JFrame implements ActionListener
{
    Timer timer;
    schedule t;
    static int i=0;
    static int j=0;
    Button b;
    public clkdrivenShedAnimation(schedule sch)
    {
        setLayout(new BorderLayout());
        t=sch;
        b=new Button("back");
        b.addActionListener(this);
        add("South",b);
//t=new schedule(4,new int[]{4,5,20,20},new double[]{1,1.8,1,2},20);
        t.createSchedulingTable();
        for (entry e:t.schedtable)
        {
            if(e.name==256)
                System.out.println("task name"+"  : Idle"+"time"+e.time);
            else
                System.out.println("task name"+e.name+"time"+e.time);
        }







//set the timer
        timer =new Timer(1000+(int)(t.schedtable.get(0).time*10),this);
        timer.start();
    }

    public void paint(Graphics g)
    {
        super.paint(g);

        String message="Task number  ";
        int tname=t.schedtable.get(j%t.schedtable.size()).name;
        double time=t.schedtable.get(j%t.schedtable.size()).time*1000;
        g.drawString("Clock driven Scheduler at"+time ,100,100);
        g.drawString(message+Integer.toString(tname)+" is executing and schedecison number"+j,200,200);


    }


    public void actionPerformed(ActionEvent evt)
    {

        if(evt.getSource()==b)
        {
            this.dispose();
        }
        else
        {

//timeraction


            j++;
            if(j>1000)
                j=0;
            timer.stop();
//t.schedtable.remove(0);



            repaint();
            try {
                //set a new timer
                timer =new Timer( (int) ( t.schedtable.get(j%t.schedtable.size()).time- t.schedtable.get((j-1)%t.schedtable.size()).time         ) *1000,this);
                timer.start();



            }

            catch(Exception e)
            {
                System.out.println("error"+j%20);
            }

        }

    }



    /*public static void main(String args[])
    {


    clkdrivenShedAnimation a=new clkdrivenShedAnimation();
    a.setSize(500,500);
    a.setVisible(true);
    a.setTitle("Clock Driver Scheduler");
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    */
}
