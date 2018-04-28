package PosTagger.Postagger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

public class postagger {
	
	public static void main(String args[])
	{
	BufferedReader br=null;
	BufferedReader tr=null;
	BufferedReader gr=null;
	try
	{
		 br=new BufferedReader(new FileReader("F:\\2nd sem iiitB\\text processing\\postagger\\train.txt"));
		 tr=new BufferedReader(new FileReader("F:\\2nd sem iiitB\\text processing\\postagger\\train.txt"));
		 gr=new BufferedReader(new FileReader("F:\\2nd sem iiitB\\text processing\\postagger\\train.txt"));
		String line;
	
		int i=0;
		double a[][]=new double[36][36]; // transition probability
		HashMap<String,Integer> hm=new HashMap<>();
		HashMap<String,Integer> hw=new HashMap<>();
		hm.put("CC",i++);
		hm.put("CD",i++);
		hm.put("DT",i++);
		hm.put("EX",i++);
		hm.put("FW",i++);
		hm.put("IN",i++);
		hm.put("JJ",i++);
		hm.put("JJR",i++);
		hm.put("JJS",i++);
		hm.put("LS",i++);
		hm.put("MD",i++);
		hm.put("NN",i++);
		hm.put("NNS",i++);
		hm.put("NNP",i++);
		hm.put("NNPS",i++);
		hm.put("PDT",i++);
		hm.put("POS",i++);
		hm.put("PRP",i++);
		hm.put("PRP$",i++);
		hm.put("RB",i++);
		hm.put("RBR",i++);
		hm.put("RBS",i++);
		hm.put("RP",i++);
		hm.put("SYM",i++);
		hm.put("TO",i++);
		hm.put("UH",i++);
		hm.put("VB",i++);
		hm.put("VBD",i++);
		hm.put("VBG",i++);
		hm.put("VBN",i++);
		hm.put("VBP",i++);
		hm.put("VBZ",i++);
		hm.put("WDT",i++);
		hm.put("WP",i++);
		hm.put("WP$",i++);
		hm.put("WRB",i++);
		//hm.put("qi",i++);
		//hm.put("qf",i++);
		String v[]=new String[36];
		int l=0;
		v[l++]="CC";
		v[l++]="CD";
		v[l++]="DT";
		v[l++]="EX";
		v[l++]="FW";
		v[l++]="IN";
		v[l++]="JJ";
		v[l++]="JJR";
		v[l++]="JJS";
		v[l++]="LS";
		v[l++]="MD";
		v[l++]="NN";
		v[l++]="NNS";
		v[l++]="NNP";
		v[l++]="NNPS";
		v[l++]="PDT";
		v[l++]="POS";
		v[l++]="PRP";
		v[l++]="PRP$";
		v[l++]="RB";
		v[l++]="RBR";
		v[l++]="RBS";
		v[l++]="RP";
		v[l++]="SYM";
		v[l++]="TO";
		v[l++]="UH";
		v[l++]="VB";
		v[l++]="VBD";
		v[l++]="VBG";
		v[l++]="VBN";
		v[l++]="VBP";
		v[l++]="VBZ";
		v[l++]="WDT";
		v[l++]="WP";
		v[l++]="WP$";
		v[l++]="WRB";
		
		int p=0;
		int count=0;
		String prev="";
		while((line=br.readLine())!=null)
		{	//System.out.println(line);
			String words[]=line.split(" ");
			if(words.length>=3) {
			if(!hw.containsKey(words[0]) && hm.containsKey(words[1]))
				hw.put(words[0],count++);
			if(p==0)
			{	//System.out.println(words[1]);
				if(hm.containsKey(words[1])) {
				prev=words[1];
				p++;
				}
			}
			else
			if(p!=0)
			{	
				//System.out.println(words[1]);
				if(hm.containsKey(prev) && hm.containsKey(words[1])) {
				a[hm.get(prev)][hm.get(words[1])]++;
				prev=words[1];
				}
			}
			}
			else
				{
				p=0;
				//System.out.println("!!!line changed!!! "+p);
				}
		}
		double b[]=new double[36];
		double sum=0;
		for( i=0;i<36;i++)
		{	
			for(int j=0;j<36;j++)
			{
				sum=sum+a[i][j];
			}
			b[i]=sum;
			sum=0;
		}	
		double total=0;
		for( i=0;i<36;i++)
		{	total=0;
			for(int j=0;j<36;j++)
			{
				a[i][j]=a[i][j]/b[i];
				//total=total+a[i][j];
				//System.out.print(a[i][j]+" ");
			}
			//System.out.println();
		}	
		//System.out.println();
	//	System.out.println(hw.size());
	/////////////////////////////////////////////////////////////////////////////////////////////////////	
		double c[][]=new double[36][19100]; //words and tags
		
		while((line=tr.readLine())!=null)
		{
			String tw[]=line.split(" ");
			if(tw.length>=3)
			{
				if(hw.containsKey(tw[0]) && hm.containsKey(tw[1]))
				{
					c[hm.get(tw[1])][hw.get(tw[0])]++;
				}
			}
		}
		
		
	
	double b1[]=new double[19100];
	double sum1=0;
	for(int j=0;j<19100;j++)
	{	
		for(i=0;i<36;i++)
		{
			sum1=sum1+c[i][j];
		}
		b1[j]=sum1;
		sum1=0;
	}	
	double total1=0;
	for( int j=0;j<19100;j++)
	{	total1=0;
		for(i=0;i<36;i++)
		{	
			if(b1[j]==0)
				c[i][j]=0;
			else
			c[i][j]=c[i][j]/b1[j];
			//total=total+a[i][j];
			//System.out.print(c[i][j]+" ");
		}
		//System.out.println();
	}	
		
		
		/*for(i=0;i<36;i++)
		{
			for(int j=0;j<19100;j++)
			{
				System.out.print(c[i][j]+" ");
			}
			System.out.println();
		}*/
		
		
		double d[][]=new double[2][36]; //tag start and end
		int y=0;
		while((line=gr.readLine())!=null)
		{
			String init[]=line.split(" ");
			
			if(init.length>=3)
			{	if(y==0)
				if(hm.containsKey(init[1]))
					{d[0][hm.get(init[1])]++;
					y=1;
					}
			if(hm.containsKey(init[1]))
			prev=init[1];
			}
			else
			{
				d[1][hm.get(prev)]++;
				y=0;
			}
			
		}
		System.out.println();
		double b2[]=new double[2];
		double sum2=0;
		for( i=0;i<2;i++)
		{	
			for(int j=0;j<36;j++)
			{
				sum2=sum2+d[i][j];
			}
			b2[i]=sum2;
			sum2=0;
		}	
		double total2=0;
		for( i=0;i<2;i++)
		{	total2=0;
			for(int j=0;j<36;j++)
			{
				d[i][j]=d[i][j]/b2[i];
				//total=total+a[i][j];
				//System.out.print(d[i][j]+" ");
			}
			//System.out.println();
		}	
		
		
		
		
		/*for(i=0;i<2;i++)
			{for(int j=0;j<36;j++)
				System.out.print(d[i][j]+" ");
			System.out.println();
			}*/
		
		
		Scanner sc=new Scanner(System.in);
		String sentence=sc.nextLine();
		//System.out.println(sentence);
		String tag[]=sentence.split(" ");
		double vm[][]=new double[36][tag.length]; //viterbi matrix
		double trr[][]=new double[36][tag.length];
		for(int col=0;col<tag.length;col++)
		{
			for(int row=0;row<36;row++)
			{	
				if(col==0) {
				if(hw.containsKey(tag[col])) {
				double q1=c[row][hw.get(tag[col])];
				double q2=d[0][row];
				vm[row][col]=q1*q2;	
				}
				else
				{	
					double q2=d[0][row];
					vm[row][col]=q2;
				}
				//trr[i][0]=-1;
				}
				else
				{	if(hw.containsKey(tag[col]))
					vm[row][col]=compute(row,col,a,c,d,hw,tag[col],vm,trr);		
				else
					vm[row][col]=unknown(row,col,a,c,d,hw,tag[col],vm,trr);	
				}
			}
		}
		
		double max=0;
		int last=0;
		for(int pi=0;pi<36;pi++)
		{
			if(max<vm[pi][tag.length-1]*d[1][pi])
				{
				max=vm[pi][tag.length-1]*d[1][pi];
				last=pi;
				}
		}
	//	System.out.println(max);
		for(int pi=0;pi<36;pi++)
		{
			for(int qi=0;qi<tag.length;qi++)
			{
				//System.out.print(vm[pi][qi]+" ");
			}
			//System.out.println();
		}
		int fin[]=new int[tag.length];
		fin[tag.length-1]=last;
		//System.out.print(last+" ");
		int cl=tag.length-1;
		int ro=last;
		while(cl>0)
		{
			//System.out.print(trr[ro][cl]+" ");
			 fin[cl-1]=(int)trr[ro][cl];
			cl--;
		}
		
		for(i=0;i<tag.length;i++)
		{
			System.out.println(tag[i]+" "+v[(int)fin[i]]+" ");
		}
		
		
	}		
	catch(Exception e)
	{
		e.printStackTrace();
	}
	}
	
	
	public static double compute(int row,int col,double a[][],double c[][],double d[][],HashMap<String,Integer> h,String word,double vm[][],double trr[][])
	{	
		double max=0;
		for(int i=0;i<36;i++)
		{
			if(max<vm[i][col-1]*a[i][row])
				{max=vm[i][col-1]*a[i][row];
				trr[row][col]=i;
				}
		}
		max=max*c[row][h.get(word)];
		
		return max;
	}
	
	public static double unknown(int row,int col,double a[][],double c[][],double d[][],HashMap<String,Integer> h,String word,double vm[][],double trr[][])
	{	
		double max=0;
		for(int i=0;i<36;i++)
		{
			if(max<vm[i][col-1]*a[i][row])
				{max=vm[i][col-1]*a[i][row];
				trr[row][col]=i;
				}
		}
		
		return max;
	}
	
	
	
	
	
}
//nn=noun
//vbz=verb
//rb=adverb
//in=preposition