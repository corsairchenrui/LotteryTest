package utils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;


public class quantilizied_ranking {
	public static final int[][] QRanking = {
		{-20,-19,-18,-17,-16,-15},
		{-14,-13,-12,-11,-10,-9},
		{-8,-7,-6,-5,-4,-3},
		{-2,-1,0,1,2,3},
		{4,5,6,7,8,9},
		{10,11,12,13,14,15},
		{16,17,18,19,20}};
	public static int getQRanking(int ranking_difference){
		for(int i=0;i<QRanking.length;i++)
			for(int j=0;j<QRanking[i].length;j++)
				if(ranking_difference == QRanking[i][j])
					return i+1;
		return 4;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File input = new File("E:\\data");
			quantilize(input, new File(input.getAbsolutePath() + ".txt"));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	private static void quantilize(File input, File output) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(input));
		FileOutputStream fos = new FileOutputStream(output, true);
		
		String line = null;
		while(null != (line = br.readLine())) {
			String[] splitted = line.split(" ");
			for(String s:splitted)
				fos.write((s+" ").getBytes(Charset.forName("UTF-8")));
			fos.write((getQRanking(Integer.valueOf(splitted[splitted.length - 1]))+"").getBytes(Charset.forName("UTF-8")));
			fos.write("\n".getBytes(Charset.forName("UTF-8")));
			fos.flush();
		}
		fos.close();
		br.close();
	}

}
