import java.util.*;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileOutputStream;

class InputGenerator
{
	public static void main(String arg[])
	{
		int row = Integer.parseInt(arg[1]);
		int limit= Integer.parseInt(arg[2]);
		WrFile wr = new WrFile();
		wr.writeFile(arg[0],row,limit);
	}
}
class WrFile
{
	public void writeFile(String s,int row,int limit)
	{
			Random randomGenerator = new Random();
			//int a[]={0,1,2,3,4,5};
			String val;
			String m;
			int num;
			num = randomGenerator.nextInt(row*row);
			while(num < row*2)
				num = randomGenerator.nextInt(row*row);

//			m = num.toString();
			String eol = System.getProperty("line.separator");
			try
			{
				File file = new File(s);
				// if file doesnt exists, then create it
				if (!file.exists()) {
					file.createNewFile();
				}
      				int randomInt = 0 ;
				int i=0;
				int j=0;
				int count=0;
				FileWriter fw = new FileWriter(file.getAbsoluteFile());
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(row+eol);
//				bw.write(num+eol);
				int[][]	r = new int[row][row];
				while(i<row)
				{
					j=0;
					while(j<row)
					{
						if(i < j)
							randomInt = randomGenerator.nextInt(limit);
						else if(i > j)
							randomInt = r[j][i];
						else if(i == j)
							randomInt = 0;

						r[i][j] = randomInt;
						if( randomInt != 0 )	count++;
						val=randomInt+"	";
						bw.write(val);
						j++;
					}
					bw.write(eol);
					i++;
				}
				count = count/2; //Undirected Graph
				bw.write(count+eol);
				bw.close();
			}
			catch (IOException e)
			{
				System.out.println("IOException occured");
			}
	}
}
