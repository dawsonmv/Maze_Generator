/**
 * Created by dawsonvaldes on 3/19/17.
 */


import com.algorithm.QuickFind;
import com.algorithm.QuickUnion;
import com.algorithm.WeightedQuickUnion;
import com.algorithm.WQUPathCompression;

import java.util.InputMismatchException;


public class MazeGenerator
{

    public MazeGenerator() // initialze program
    {

    }

    public static void main( String[] args )
    {

            int height;
            int width;
            int size;

            StdOut.println("Welcome to the Amazing Maze Generator");
            StdOut.println("*************************************");
            StdOut.println();

            StdOut.println("Please Enter the Size of the Maze. ( minimum 2x2 )");
            StdOut.println();

            height = input_size("What should the height be ? ( min. 2)");
            StdOut.println();

            width = input_size("What should the width be ? ( min. 2 )");
            StdOut.println();

            size = (height * width)+2;

            AmazingMaze am;
            am = new AmazingMaze(width,height,new QuickFind(size));
            am.build_maze();
            am.save_maze("qf");

            am = null;
            am = new AmazingMaze(width,height,new QuickUnion(size));
            am.build_maze();
            am.save_maze("qu");

            am = null;
            am = new AmazingMaze(width,height,new WeightedQuickUnion( size ));
            am.build_maze();
            am.save_maze("wqu");

            am = null;
            am = new AmazingMaze(width,height,new WQUPathCompression( size ));
            am.build_maze();
            am.save_maze("wqupc");

            am = null;


    }

    private static int input_size(String message)
    {

        boolean needs_input = true;
        int input = -1;

        while(needs_input)
        {

            try
            {
                StdOut.println(message);
                input = StdIn.readInt();
            }
            catch( InputMismatchException ime )
            {
                StdOut.println();
                StdOut.println("!!! Invaild Input !!!");
            }

            if( input < 2)
            {
                StdOut.println();
                StdOut.println("The size must be an interger ( whole number )  larger than 2 ! ");
                StdOut.println("Please try again!");
            }
            else
            {
                needs_input = false;
            }

        }

        return input;

    }


}
