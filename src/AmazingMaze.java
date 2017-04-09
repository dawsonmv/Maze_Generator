/**
 * Created by dawsonvaldes on 3/24/17.
 */

import com.algorithm.IUnionFind;

public class AmazingMaze
{

    private IUnionFind maze;

    private int maze_height;
    private int maze_width;
    private int maze_size;

    private int north_bound;
    private int south_bound;
    private int end_bound;

    private double x_scale;
    private double y_scale;
    private double room_size;

    private int walls[];
    private int start;
    private int end;


    public AmazingMaze( int mw , int mh , IUnionFind ma  )
    {
        maze_width = mw;
        maze_height = mh;
        maze_size = mw * mh;

        maze = ma;

        north_bound = maze_width - 1;
        south_bound = maze_size - maze_width;
        end_bound = maze_size - 1;
        start = end_bound + 1;
        end = end_bound + 2;

        int walls[];

        if ( maze_height == maze_width )
        {
            x_scale = 1.0;
            y_scale = 1.0;
            room_size = 1.0 / maze_width;
        }
        else if( maze_height > maze_width )
        {
            x_scale = maze_width / maze_height;
            y_scale = 1.0;
            room_size = 1.0 / maze_height;
        }
        else
        {
            x_scale = 1.0;
            y_scale = maze_height / maze_width;
            room_size = 1.0 / maze_width;
        }

    }

    public void build_maze()
    {

        StdDraw.setCanvasSize( (int) (1280 * x_scale) , (int) (1280 * y_scale) );
        StdDraw.setPenColor( StdDraw.WHITE );
        StdDraw.setPenRadius( room_size * 0.49 );

        // First Break The Walls
        int walls[];

        maze.union( 0 , start);
        maze.union( end_bound , end );

        for( int room = 1 ; room < end_bound ; room++ )
        {

            walls = get_walls( room , north_bound , south_bound , end_bound , maze_width);

            boolean new_door = false;

            while( !new_door )
            {

                int wall = walls[0];

                if( walls.length > 1 )
                    wall = get_wall( walls );

                int door;
                door = get_door( room , wall , maze_width  );

                if ( maze.union( room , door ) )
                {
                    StdDraw.line( (room % maze_width) * room_size , 1.0 - ((room % maze_height) * room_size) , (door % maze_width) * room_size, 1.0-((door % maze_height) * room_size ));
                    new_door = true;
                }
                else
                {
                    walls = eliminate( walls , wall );
                }

            }

        }


    }

    private int get_door( int room , int wall , int maze_width )
    {

        int door;

        switch ( wall )
        {
            case 0: // North
                door = room - maze_width ;

            case 1: // East
                door = room - 1;

            case 2: // West
                door = room + 1;

            case 3:  // South
                door = room - maze_width;

            default: door = room;
        }

        return door;

    }

    private int[]get_walls(int room , int north_bound, int south_bound, int end_bound, int maze_width)
    {

        // Check for corners first

            // nw corner , only east and south
      //  if( room == 0 )
      //      return new int[]{1,3};

            // ne corner , only west and south
       if( room == north_bound )
            return new int[]{2,3};

            // sw corner , only north and east
        else if( room == south_bound )
            return new int[]{0,1};

            // se corner , only north and west
      //  else if( room == end_bound )
          //  return new int[]{0,2};

        //check for edges

            //  north edge , no  north
        else if( room < north_bound )
            return new int[]{1,2,3};

            // south edge , no south
        else if( room > south_bound )
            return new int[]{0,1,2};

            // west edge , no west
        else if( room % maze_width == 0 )
            return new int[]{0,1,3};

            // east edge , no east
        else if( room % maze_width == north_bound )
            return new int[]{0,2,3};

            //and the rest, allow all directions
        else return new int[]{0,1,2,3};

    }

    private int get_wall(int[] walls )
    {

        int rando;
        rando = (int) Math.floor( Math.random( ) * walls.length );

        int wall;
        wall = walls[rando];

        return wall;

    }

    private int[] eliminate( int[] a , int b)
    {

        int[] c = new int[a.length-1];

        int d = 0;

        for (int i = 0; i < a.length ; i++)
        {
            if(a[i] != b)
            {
                c[d] = a[i];
                d++;
            }
        }

        return c;

    }

    public void save_maze( String name )
    {
        StdDraw.save( name+"_AmazingMaze_"+maze_width+"x"+maze_height+".png");
        StdDraw.clear();
    }

}
