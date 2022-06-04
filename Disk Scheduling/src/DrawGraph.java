import java.awt.*;
import java.util.*;
import javax.swing.*;

public class DrawGraph extends JPanel 
{    
    private int  padding = 25, labelPadding = 25,  pointWidth = 4, YDivisions = 14; //get the numbers on the Y axis
    private Color lineColor = new Color(189, 78, 134, 199);
    private Color pointColor = new Color(0, 0, 0, 255);
    private Color gridColor = new Color(200, 200, 200, 200);
    private int MAX=199, MIN=0;

    private ArrayList<Integer> sequence;
    private String TotalMovement;
    
    public DrawGraph(ArrayList<Integer> sequence, String TotalMovement) 
    {
        this.TotalMovement = TotalMovement;
        this.sequence = sequence;
    }

    @Override
    protected void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //make the line smooth
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double xScale = ((double) getWidth() - (2 * padding) - labelPadding) / (sequence.size() - 1);
        double yScale = ((double) getHeight() - (2 * padding) - labelPadding) / (MAX - MIN);

        ArrayList<Point> graphPoints = new ArrayList<>();
        for (int i = 0; i < sequence.size(); i++) 
        {
            int x1 = (int) (i * xScale + padding + labelPadding);
            int y1 = (int) ((MAX - sequence.get(i)) * yScale + padding);

            graphPoints.add(new Point(x1, y1));
        }

        // create hatch marks and grid lines for Y axis.
        for (int i = 0; i < YDivisions + 1; i++) 
        {
            int x0 = padding + labelPadding;
            int x1 = pointWidth + padding + labelPadding;
            int y0 = getHeight() - ((i * (getHeight() - padding * 2 - labelPadding)) / YDivisions + padding + labelPadding);
            int y1 = y0;

            if (sequence.size() > 0) 
            {
                g2.setColor(gridColor);
                g2.drawLine(padding + labelPadding + 1 + pointWidth, y0, getWidth() - padding, y1);
                g2.setColor(Color.BLACK);
                String yLabel = (((int) ((MIN + (MAX - MIN) * ((i * 1.0) / YDivisions)) * 100)) / 100) + "";

                FontMetrics metrics = g2.getFontMetrics();
                int labelWidth = metrics.stringWidth(yLabel);
                g2.drawString(yLabel, x0 - labelWidth - 5, y0 + (metrics.getHeight() / 2) - 3);
            }
            g2.drawLine(x0, y0, x1, y1);
        }

        // create x and y axes
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, padding + labelPadding, padding);
        g2.drawLine(padding + labelPadding, getHeight() - padding - labelPadding, getWidth() - padding, getHeight() - padding - labelPadding);


        // drawing the graph lines (movement lines)
        g2.setColor(lineColor);
        for (int i = 0; i < graphPoints.size() - 1; i++) 
        {
            int x1 = graphPoints.get(i).x;
            int y1 = graphPoints.get(i).y;
            int x2 = graphPoints.get(i + 1).x;
            int y2 = graphPoints.get(i + 1).y;
            g2.drawLine(x1, y1, x2, y2);
        }

        // drawing the graph points
        g2.setColor(pointColor);
        for (int i = 0; i < graphPoints.size(); i++) 
        {
            int x = graphPoints.get(i).x - pointWidth / 2;
            int y = graphPoints.get(i).y - pointWidth / 2;
            int W = pointWidth;
            int H = pointWidth;
            g2.fillOval(x, y, W, H); //filling the point
        }

        // printing the total head movement below the graph
        g2.setColor(pointColor);
        g2.drawString(TotalMovement, 280, 580);
    }

    static void createAndShowGui(ArrayList<Integer> sq, int movement, String title)
    {
        String TotalMovement = "Total Head Movement = " + movement;
        DrawGraph graph = new DrawGraph(sq, TotalMovement);
        graph.setPreferredSize(new Dimension(800, 600));
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().add(graph);
        frame.pack();
        frame.setVisible(true);
    }
}