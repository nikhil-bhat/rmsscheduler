import java.util.*;
class Task implements Comparable <Task>
{
    public int name;
    public int time;
    public double  execution;
    public Task(int name , int time , double execution)
    {
        this.name=name;
        this.time=time;
        this.execution=execution;

    }

    public int compareTo(Task othertask) {

        return this.time-othertask.time;
    }


}
class entry
{
    int name;
    double time;
    public entry(int name,double time)
    {
        this.name=name;
        this.time=time;
    }
}
class schedule
{
    int numTasks;
    int []periodicity;
    double []execution;
    int hyperperiod;
    public ArrayList <entry> schedtable;
    ArrayList <Task> que;

    public schedule(int numTasks,int[] periodicity,double [] execution,int lcm)
    {
        hyperperiod=lcm;
        this.periodicity=periodicity;
        this.execution=execution;
        this.numTasks=numTasks;
//createSchedulingTable();
    }

    public double createSchedulingTable()
    {
        double currentime=(double)0;
//using ratemonotonic scheduler

        int whenwhen[][]=new int [numTasks][];

        for(int i=0; i<numTasks; i++)
        {

            whenwhen[i]=new int[(int)(hyperperiod/periodicity[i])+1];
            whenwhen[i][0]=0;
            for(int j=1; j<whenwhen[i].length; j++)
                whenwhen[i][j]=whenwhen[i][j-1]+periodicity[i];


        }




        int flag=1;



        que=new ArrayList<Task>();
        for(int i=0; i<whenwhen.length; i++)
        {   for(int j=0; j<whenwhen[i].length; j++)
            {   try {
                    que.add(new Task(i,whenwhen[i][j],execution[i]));
                }

                catch(Exception E)
                {
                    System.out.println(" be!!");
                }
            }
        }

        Collections.sort(que);
        for (Task e:que)
        {
            System.out.println("Task : "+e.name +" "+"time "+e.time);
        }

        schedtable=new ArrayList<entry>();

        Task temp;
        Task temp2;
        Task temp3;
        Task e;
        int ind=0;
        int idleflag=1;
        while((int)currentime <=hyperperiod)
        {

            flag=1;

            temp=que.get(0); //pick a task

            if((temp.time<=currentime)||currentime<0.1) {



                if(temp.name==0)//highest priority
                {
                    schedtable.add(new entry(temp.name,currentime));
                    //System.out.println("Added: "+temp.name+"  which came at:"+temp.time);
                    currentime+=temp.execution;
                    temp3= que.remove(0);

                }

                else
                {

                    for (int i=1; i<que.size(); i++)
                    {
                        temp2=que.get(i);

                        if((temp2.name<temp.name)&&((double)temp2.time<currentime+temp.execution))
                        {

                            schedtable.add(new entry(temp.name,currentime));
                            System.out.println("Added:  "+temp.name+"  which came at:"+temp.time);
                            temp.execution-=((double)temp2.time-currentime);
                            currentime =temp2.time;
                            que.remove(i);
                            que.remove(0);

                            que.add(0,temp2);
                            if(temp.execution>0)
                                que.add(1,temp);
                            flag=2;
                            break;
                        }

                    }

                    if(flag==1)

                    {


                        schedtable.add(new entry(temp.name,currentime));
                        System.out.println("Added:   "+temp.name+"  which came at: "+temp.time);
                        currentime+=temp.execution;
                        que.remove(0);
                    }




                }



            }


            else
            {


                idleflag=1;
                for(int k=0; k<que.size(); k++)
                {

                    if(k==que.indexOf(temp))
                        continue;
                    else
                    {
                        e=que.get(k);
                    }

                    if(e.time>=currentime)
                    {


                        if(e.time>temp.time+temp.execution)
                        {
                            schedtable.add(new entry(256,currentime));
                            currentime=temp.time;
                            idleflag=2;
                            break;
                        }

                        else {
                            if(e.time<temp.time)
                            {   temp=e;
                                ind=que.indexOf(temp);
                                break;
                            }

                            else
                            {
                                schedtable.add(new entry(256,currentime));
                                currentime=temp.time;
                                idleflag=2;
                                break;

                            }


                        }
                    }

                }


                if(idleflag==2)
                {   continue;
                }


                if(temp.name==0)//highest priority
                {

                    schedtable.add(new entry(temp.name,currentime));
                    System.out.println("Added:   "+temp.name+"  which came at:"+temp.time);
                    currentime+=temp.execution;
                    que.remove(ind);
                }

                else
                {

                    for (int i=0; i<que.size(); i++)
                    {

                        if(i==ind)
                            continue;

                        temp2=que.get(i);

                        if((temp2.name<temp.name)&&((double)temp2.time<currentime+temp.execution))
                        {

                            schedtable.add(new entry(temp.name,currentime));
                            System.out.println("Added:   "+temp.name+"  which came at:"+temp.time);
                            temp.execution-=((double)temp2.time-currentime);
                            currentime =temp2.time;
                            que.remove(i);
                            que.remove(que.indexOf(temp));

                            que.add(0,temp2);
                            if(temp.execution>0)
                                que.add(1,temp);
                            flag=2;
                            break;
                        }

                    }

                    if(flag==1)

                    {


                        schedtable.add(new entry(temp.name,currentime));
                        System.out.println("Added:   "+temp.name+"  which came at:"+temp.time);
                        currentime+=temp.execution;
                        que.remove(ind);
                    }




                }


            }


        }

        double gtime=schedtable.get(0).time;
        for(int g=1; g<schedtable.size(); g++)
        {
            if((schedtable.get(g).time==gtime)&&schedtable.get(g-1).name>schedtable.get(g).name)
            {
                schedtable.remove(g-1);

            }
            gtime=schedtable.get(g).time;

        }






        return currentime;


    }








}




class scheduleTest
{


    static int compute_lcm(int [] periods)
    {
        int []orig;
        orig=new int[periods.length];
        for(int i=0; i<periods.length; i++)
        {
            orig[i]=periods[i];
        }


        boolean notallsame=true;
        int lcm=1;
        int least=periods[0];
        int leastindex=0;
        do
        {
            least=periods[0];
            for(int i=0; i<periods.length; i++) //finding least element and its index
            {
                if(least>=periods[i])
                {
                    least=periods[i];
                    leastindex=i;
                }
            }





            periods[leastindex]+=orig[leastindex];//incrementing the least value


            int issame=periods[0];

            for(int i=0; i<periods.length; i++) //checking if all elements are same
            {
                if(periods[i]==issame)
                {   notallsame=false;
                }
                else
                {

                    notallsame=true;
                    break;
                }
            }


        }
        while(notallsame!=false);


        lcm=periods[0];





        return lcm;
    }



    public static void main(String Args[])
    {
        schedule sced;
        int numTasks;
        int []periodicity;
        int []cperiodicity;
        double []execution;
        int lcm;
        double utilization=(double)0;

        Scanner sc=new Scanner(System.in);
        System.out.println("Enter number of tasks...");
        numTasks=sc.nextInt();
        System.out.println("Enter the periodicities of the tasks......");
        periodicity=new int[numTasks];

        execution=new double[numTasks];
        for(int i=0; i<numTasks; i++)
            periodicity[i]=sc.nextInt();

        cperiodicity=new int[periodicity.length];
        System.arraycopy(periodicity,0,cperiodicity,0,periodicity.length);
        System.out.println("Enter the execution times  of the tasks......");
        for(int i=0; i<numTasks; i++)
            execution[i]=sc.nextDouble();
        System.out.println("Checking Utilization......");
        for(int i=0; i<numTasks; i++)
            utilization+=execution[i]/(double)periodicity[i];

        if(utilization>1)
        {
            System.out.println("Not possible.......");
        }
        else
        {   lcm=compute_lcm(cperiodicity);
            System.out.println("Utilization="+utilization+":Hyperperiod ="+lcm);
            System.out.println("Sending for creation");
            sced=new schedule(numTasks,periodicity,execution,lcm);

            System.out.println("Timetable generated till "+sced.createSchedulingTable());

            for (entry e:sced.schedtable)
            {
                if(e.name==256)
                    System.out.println("task name"+"  : Idle"+"time"+e.time);
                else
                    System.out.println("task name"+e.name+"time"+e.time);
            }

        }

    }


}
