import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;




public class Main {
	protected static class Session {
		protected static enum Result{
			W,D,L
		};
		protected class Choices {
			String[] title;
			List<Match> matches;
			public Choices(List<Match> random) {
				this.matches = random;
				title = new String[random.size()];
				for(int i = 0;i<title.length;i++)
					title[i] = random.get(i).toString();
			}
			public Result[][] make(int n) {
				Result[][] choices = new Result[n][title.length];
				for(Match m : this.matches) {
					for(int i=m.support.length-1;i>0;i--){
						for(int j=0;j<i;j++){
							if(m.support[j].value<m.support[j+1].value){
								Match.Support t = m.support[j];
								m.support[j] = m.support[j+1];
								m.support[j+1] = t;
							}
						}
					}
				}
				int count = 0;
				while(count < n){
					for(int i = 0;i<matches.size();i++){
						choices[count][i] = matches.get(i).support[0].score;
					}
					
					int max = 0;
					int p = 0;
					for(int i = 0;i<matches.size();i++){
						if(max < matches.get(i).support[0].value){
							max = matches.get(i).support[0].value;
							p = i;
						}
					}
					matches.get(p).supportRoll();
					count++;
				}
				return choices;
			}
			
		}
		protected File dataFile;
		protected ArrayList<Match> matches;
		protected static class Match{
			protected static class Support{
				int value;
				Result score;
			}
			public String toString() {
				return "TeamA:"+a+" TeamB:"+b+" "+offset;
			}
			protected Team a;
			protected Team b;
			protected float offset;
			protected Support[] support;
			protected Support supportRoll(){
				Support t = support[0];
				support[0] = support[1];
				support[1] = support[2];
				support[2] = t;
				return t;
			}
			protected Result r;
			private Match() {
				support = new Support[3];
				for(int i = 0;i<support.length;i++){
					support[i] = new Support();
					switch(i){
					case 0:support[i].score=Result.W;break;
					case 1:support[i].score=Result.D;break;
					case 2:support[i].score=Result.L;break;
					}
				}
			}
			
			protected class Team{
				public String toString() {
					return name+"#"+score;
				}
				protected String name;
				protected int score;
				Team(String name){
					this.name = name;
				}
			}

			protected static Match parse(String s) {
				Match m = new Match();
				String[] info = s.split(" ");
				m.a = m.new Team(info[0]);
				m.b = m.new Team(info[2]);
				m.offset = Float.parseFloat(info[1]);
				m.a.score = Integer.parseInt(info[3].split(":")[0]);
				m.b.score = Integer.parseInt(info[3].split(":")[1]);
				for(int i = 0;i<m.support.length;i++) {
					m.support[i].value = Integer.parseInt(info[4].split("%")[i]);
				}
				switch(Integer.parseInt(info[5])){
				case 3:m.r = Result.W;break;
				case 1:m.r = Result.D;break;
				case 0:m.r = Result.L;break;
				}
				return m;
			}
		}
		private Session() {
			matches = new ArrayList<Match>();
		}
		public static Session newSession(String fileName) {
			Session ret = null;
			File f;
			if(null != fileName && null != (f = new File(fileName)) && f.isFile()) {
				ret = new Session();
				ret.dataFile = f;
			}
			return ret;
		}
		public void parse() {
			StringBuffer sb = new StringBuffer();
			String s;
			try {
				BufferedReader br = new BufferedReader(new FileReader(dataFile));
				while(null != (s=br.readLine())) {
					matches.add(Match.parse(s));
				}
				System.err.println();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		public List<Match> random(int i) {
			int size = (i<this.matches.size())?i:matches.size();
			int num = 0;
			Set<Integer> choice = new HashSet<Integer>();
			List<Match> ret = new ArrayList<Match>();
			while(num < size){
				int n = (int) (Math.random()*matches.size());
				if(!choice.contains(n)) {
					choice.add(n);
					num++;
					ret.add(matches.get(n));
				}
			}
			return ret;
		}
		public void getChoices(List<Match> random) {
			Choices c = new Choices(random);
			check(random,c.make(350));
		}
		protected void check(List<Match> random, Result[][] choices) {
			int count = 0;
			for(Result[] r:choices){
				boolean flag = true;
				for(int i = 0;i<random.size();i++){
					if(random.get(i).r.compareTo(r[i])!=0){
						flag = false;
						break;
					}
				}
				if(flag){
					count++;
				}
			}
			System.err.println(count);
		}
		
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName = "F:\\workspace\\LotteryTest\\resource\\data";
		Session s = Session.newSession(fileName);
		s.parse();
		s.getChoices(s.random(5));
	}

}
