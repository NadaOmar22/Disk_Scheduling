import java.util.*;
public class Disk_Scheduling 
{
    private ArrayList<Integer> Requests;
    public  ArrayList<Integer> sequence = new ArrayList<>();
    private int head;
    private int  movement;

    public Disk_Scheduling(ArrayList<Integer> Requests, int head)
    {
        this.Requests = Requests;
        this.head = head;
    }

    public void FCFS()
    {
        sequence.add(head);
        int currentHead = head;
        for(int i = 0; i < Requests.size(); i++)
        {
            movement += Math.abs(currentHead - Requests.get(i));
            currentHead = Requests.get(i);
            sequence.add(currentHead);
        }
    }

    public void SSTF()
    {
        sequence.add(head);
        int head2 = head;
        ArrayList <Integer> dest = new ArrayList();
        ArrayList <Integer> request = new ArrayList<>();

        for(int i = 0; i < Requests.size(); i++)
            request.add(Requests.get(i));

        while(!request.isEmpty())
        {
            for(int i = 0; i < request.size(); i++)
            {
                dest.add(Math.abs(request.get(i) - head2));
            }
            int min = Collections.min(dest);
            movement +=  min ;
            int index = dest.indexOf(min);
            head2 = request.get(index);
            sequence.add(head2);
            request.remove(request.get(index));
            dest = new ArrayList<>();
        }
    }

    public void SCAN()
    {
        sequence.add(head);
        ArrayList <Integer> requests = new ArrayList<>();

        for(int i = 0; i < Requests.size(); i++)
            requests.add(Requests.get(i));

        Collections.sort(requests);

        movement = head;
        boolean check = false;

        for (int i=0;i<requests.size();i++)
        {
            if (head < requests.get(i)&& !check)
            {
                for (int j = i-1; j >= 0; j--)
                    sequence.add(requests.get(j));

                sequence.add(0);
                movement += requests.get(i); // add 65
                movement += (requests.get(requests.size()-1)-requests.get(i)); // add the difference between the biggest one and 65
                sequence.add(requests.get(i));
                check = true;
            }
            else if (check)
            {
                sequence.add(requests.get(i));
            }
        }
    }

    public void C_SCAN()
    {
        sequence.add(head);
        ArrayList <Integer> requests = new ArrayList<>();
        for(int i = 0; i < Requests.size(); i++)
            requests.add(Requests.get(i));

        Collections.sort(requests);

        for (int i = 0; i < requests.size(); i++)
        {
            if(requests.get(i) > head)
            {
                sequence.add(requests.get(i));
            }
        }
        movement += 199 - head;
        sequence.add(199);
        sequence.add(0);

        for (int i = 0; i < requests.size(); i++)
        {
            if(requests.get(i) < head)
            {
                sequence.add(requests.get(i));
            }
            else
            {
                movement += requests.get(i - 1);
                break;
            }
        }
    }

    public void Look()
    {
        sequence.add(head);
        ArrayList <Integer> requests = new ArrayList<>();
        for(int i = 0; i < Requests.size(); i++)
            requests.add(Requests.get(i));

        Collections.sort(requests);

        int pos = 0;
        for (int i = 0; i < requests.size(); i++)
        {
            if(requests.get(i) > head)
            {
                sequence.add(requests.get(i));
            }
            else
                pos++;
        }

        for (int j = pos-1; j>=0; j--)
        {
            sequence.add(requests.get(j));
        }

        movement+=requests.get(requests.size()-1)-head;
        movement+=(requests.get(requests.size()-1)-requests.get(0));
    }

    public void C_LOOK()
    {
        sequence.add(head);
        ArrayList <Integer> requests = new ArrayList<>();
        for(int i = 0; i < Requests.size(); i++)
            requests.add(Requests.get(i));

        Collections.sort(requests);

        for (int i = 0; i < requests.size();i++)
        {
            if(requests.get(i) > head)
            {
                sequence.add(requests.get(i));
            }
        }
        int i ;
        for (i = 0 ;  i < requests.size(); i++)
        {
            if(requests.get(i) < head)
                sequence.add(requests.get(i));
            else
                break;
        }
        movement += requests.get(requests.size()-1) - head;
        movement +=(requests.get(i - 1)-requests.get(0));
        movement += requests.get(requests.size()-1)-requests.get(0);
    }


    /*                     "Summary of Optimal Algorithm"
     *  initially the disk head always starts at position 0,
     *  if the head starts at a position other than zero, we go to zero and then starts from there,
     *  we sort the sequence array in ascending order,
     *  Service the input request in front of head immediately till we reach to the end of the range,
     *  Return total_head_movements.
     */
    public void optimal()
    {
        ArrayList <Integer> requests = new ArrayList<>();
        for(int i = 0; i < Requests.size(); i++)
            requests.add(Requests.get(i));

        Collections.sort(requests);

        sequence.add(head);
        if(head != 0)
        {
            movement += head;
            sequence.add(0);
        }

        for(int i = 0; i < Requests.size(); i++)
        {
            sequence.add(requests.get(i));
        }
        int siz = sequence.size();
        movement += sequence.get(siz-1);
    }

    public ArrayList<Integer> print()
    {
        System.out.println("movement : "+movement);
        System.out.println("sequence : "+sequence);

        return sequence;
    }

    public int getMovement()
    {
        return movement;
    }

    public void clear()
    {
        sequence= new ArrayList<>();
        movement = 0;
    }
}