import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class AssignmentTwo extends JFrame implements ActionListener
{
    JTextField periodicity;
    JTextField num_tasks;
    JTextField execution_time;
    JLabel l1;
    JLabel l2;
    JLabel l3;
    JButton viewGchart;
    JButton viewSimulator;
    scheduleTest st;
    schedule s;
    public AssignmentTwo()
    {
        setLayout(new GridLayout(4,2));
        l1=new JLabel("Enter num_tasks");
        l2=new JLabel("Enter task_periodicties separated by ,");
        l3=new JLabel("Enter execution_times separated by ,");
        num_tasks=new JTextField(20);
        periodicity=new JTextField(20);
        execution_time=new JTextField(20);
        viewGchart=new JButton("Generate charts");
        viewGchart.addActionListener(this);
        viewSimulator=new JButton("View clock driven scheduler");
        add(l1);
        add(num_tasks);
        add(l2);
        add(periodicity);
        add(l3);
        add(execution_time);
        add(viewGchart);
        viewSimulator.addActionListener(this);
        add(viewSimulator);

    }


    public void actionPerformed(ActionEvent ae)
    {






        if(ae.getSource()==viewGchart)
        {


            String numtasks=num_tasks.getText();
            System.out.println("The num is"+Integer.parseInt(numtasks));
            String periocities=periodicity.getText();
            String parts[]=periocities.split("," );
            int period[]=new int [Integer.parseInt(numtasks)];
            int xperiod[]=new int [Integer.parseInt(numtasks)];
            for(int i=0; i<parts.length; i++)
            {
                period[i]=Integer.parseInt(parts[i]);
                xperiod[i]=Integer.parseInt(parts[i]);
            }

            String part2[]=execution_time.getText().split(",");
            double execu[]=new double [Integer.parseInt(numtasks)];

            for(int i=0; i<part2.length; i++)
            {
                execu[i]=Double.parseDouble(part2[i]);
            }

            int lcm =st.compute_lcm(xperiod);





            s=new schedule(Integer.parseInt(numtasks), period,execu,lcm);















            taskInfoDisp disp=new taskInfoDisp(s);;
            disp.setTitle("Visualization");
            disp.setSize(500,500);
            disp.setVisible(true);
//disp.setResizable(false);
            disp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);










        }



        if(ae.getSource()==viewSimulator)  //call the simulation class
        {
            String numtasks=num_tasks.getText();
            System.out.println("The num is"+Integer.parseInt(numtasks));
            String periocities=periodicity.getText();
            String parts[]=periocities.split("," );
            int period[]=new int [Integer.parseInt(numtasks)];
            int xperiod[]=new int [Integer.parseInt(numtasks)];
            for(int i=0; i<parts.length; i++)
            {
                period[i]=Integer.parseInt(parts[i]);
                xperiod[i]=Integer.parseInt(parts[i]);
            }

            String part2[]=execution_time.getText().split(",");
            double execu[]=new double [Integer.parseInt(numtasks)];

            for(int i=0; i<part2.length; i++)
            {
                execu[i]=Double.parseDouble(part2[i]);
            }

            int lcm =st.compute_lcm(xperiod);














            s=new schedule(Integer.parseInt(numtasks), period,execu,lcm);






            clkdrivenShedAnimation a=new clkdrivenShedAnimation(s);
            a.setSize(800,300);
            a.setVisible(true);
            a.setTitle("Clock Driver Scheduler");
            a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);




        }





    }



    public static void main(String args[])
    {

        AssignmentTwo tw=new AssignmentTwo();
        tw.setSize(800,200);
        tw.setResizable(false);
        tw.setVisible(true);
        tw.setTitle("Assignment Two");
        tw.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }









}
