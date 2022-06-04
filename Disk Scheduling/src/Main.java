import javax.swing.*;
import java.util.*;

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Enter the head pointer");   // 53
        Scanner input = new Scanner(System.in);
        int head = input.nextInt();

        System.out.println("Enter number of request"); // 8
        int numOfRequests = input.nextInt();        

        ArrayList<Integer> Requests = new ArrayList<>();
        System.out.println("Enter the requests"); //98 183 37 122 14 124 65 67
        for (int i = 0; i < numOfRequests; i++) 
        {
            Requests.add(input.nextInt());
        }
        System.out.println();

        Disk_Scheduling disk_scheduling = new Disk_Scheduling(Requests, head);

        System.out.println("Choose Disk Scheduling Algorithm from the following: ");
        System.out.println("1.FCFS \n2.SSTF \n3.SCAN \n4.C-SCAN \n5.LOOK \n6.C-LOOK \n7.new optimal \n0. Exit");
        int choice = -1;
        while(choice != 0)
        {
            choice = input.nextInt();
            switch (choice)
            {
                case 1:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* FCFS *");
                    disk_scheduling.FCFS();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();

                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run() 
                        {
                            graph.createAndShowGui(sq, movement,"FCFS");
                        }

                    });
                    break;
                }
                case 2:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* SSTF *");
                    disk_scheduling.SSTF();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run() 
                        {
                            graph.createAndShowGui(sq, movement, "SSTF Graph");
                        }

                    });
                    break;
                }
                case 3:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* Scan *");
                    disk_scheduling.SCAN();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run()
                        {
                            graph.createAndShowGui(sq, movement, "SCAN Graph");
                        }

                    });
                    break;
                }
                case 4:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* C-Scan *");
                    disk_scheduling.C_SCAN();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run()
                        {
                            graph.createAndShowGui(sq, movement, "C-SCAN Graph");
                        }

                    });
                    break;
                }
                case 5:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* Look *");
                    disk_scheduling.Look();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable()
                    {
                        public void run() 
                        {
                            graph.createAndShowGui(sq, movement, "LOOK Graph");
                        }

                    });
                    break;
                }
                case 6:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* C-Look *");
                    disk_scheduling.C_LOOK();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run() 
                        {
                            graph.createAndShowGui(sq, movement, "C-LOOK Graph");
                        }

                    });
                    break;
                }
                case 7:
                {
                    int movement;
                    ArrayList<Integer> sq;
                    System.out.println("* Optimized *");
                    disk_scheduling.optimal();
                    sq = disk_scheduling.print();
                    movement = disk_scheduling.getMovement();
                    String TotalMovement = "Total Head Movement = " + movement;
                    disk_scheduling.clear();
                    System.out.println();
                    DrawGraph graph = new DrawGraph(sq, TotalMovement);
                    SwingUtilities.invokeLater(new Runnable() 
                    {
                        public void run() 
                        {
                            graph.createAndShowGui(sq, movement, "Optimal Graph");
                        }
                    });
                    break;
                }
                case 0:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, Please enter number from 1 to 7, OR Press 0 to exit");
            }
        }
    }
}