import java.util.*;
public class GameOfLife2
{
    public static void main(String[] args)
    {
        Scanner scan = new Scanner(System.in);
        String numIter_string = scan.nextLine();
        int numIter = Integer.parseInt(numIter_string);
        int M = 20, N = 20;
        
        String[] strInput = new String[20];
        int[][] grid = new int[N][M];
        
        for(int i = 0; i < N; i++)
        {
            strInput[i] = scan.nextLine();
        }
        
        // construct int array
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                int num = strInput[i].charAt(j);
                
                //ascii value
                if(num == 48)
                {
                    grid[i][j] = 0;
                }
                else if(num == 49)
                {
                    grid[i][j] = 1;
                }
            }
        }
        
        int[][] nextIter = nextGen(grid, M, N);
        for(int k = 0; k < numIter-1; k++)
        {   
            nextIter = nextGen(nextIter, M, N);
        }
        
        int count = 0;
        for (int i = 0; i < M; i++)
        {
            for (int j = 0; j < N; j++)
            {
                //ascii value
                if(nextIter[i][j] == 0)
                {
                    
                }
                else if(nextIter[i][j] == 1)
                {
                    count++;
                }
            }
        }
        System.out.println(count);
    }
  
    public static int[][] nextGen(int grid[][], int M, int N)
    {
        int[][] newGrid = new int[M][N];
  
        for (int l = 1; l < M - 1; l++)
        {
            for (int m = 1; m < N - 1; m++)
            {
                int aliveCells = 0;
                for (int i = -1; i <= 1; i++)
                    for (int j = -1; j <= 1; j++)
                        aliveCells += grid[l + i][m + j];
  
                aliveCells -= grid[l][m];
  
                // turn off cell if it doesnt touch 2 or 3 other cells
                if ((grid[l][m] == 1) && (aliveCells < 2 || aliveCells > 3))
                    newGrid[l][m] = 0;
  
                // turn on cell
                else if ((grid[l][m] == 0) && (aliveCells == 3))
                    newGrid[l][m] = 1;
  
                // keep cell on
                else
                    newGrid[l][m] = grid[l][m];
            }
        }
 
        return newGrid;
    }
}
