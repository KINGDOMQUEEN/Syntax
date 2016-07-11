package syntax;

public class FourUnitList
{
	public FourUnit[] a;
	public int sp;
	public int length;
	FourUnitList()
	{
		sp=100;
		length=0;
		a=new FourUnit[100];
	}
	
	public boolean extend()
	{
		int l=a.length+20;
		FourUnit[] b=new FourUnit[l];
		if(b==null)
			return false;
		for(int i=0;i<a.length;i++)
			b[i]=a[i];
		a=b;
		return true;
	};
	
	public int backPatch(int startPoint, int value)
	{
		//int temp=startPoint;
		//String handle=a[startPoint-sp].jump;
		int temp;
		while(startPoint!=0)
		{
			//temp=Integer.parseInt(j);
			temp=a[startPoint-sp].jump;
			a[startPoint-sp].jump=value;
			startPoint=temp;
		}
		
		return value; 
	}
	
	public int merge(int p1,int p2)
	{
		int temp;
		int startPoint=p2;
		if(p1==0)
			return p2;
		if(p2==0)
			return p1;
		
		while(a[startPoint-sp].jump!=0)
		{
			temp=a[startPoint-sp].jump;
			startPoint=temp;
		}
		a[startPoint-sp].jump=p1;
		return p2;
		
	}
	
	public int merge(int p1,int p2,int p3)
	{
		int temp;
		temp=merge(p2,p1);
		return merge(p3,temp);
		
		
	}
	public int gen(int startPoint,String symbol, String f1, String f2,char isCase, int jump, String f3)
	{
		a[startPoint-sp]=new FourUnit();
		a[startPoint-sp].setValue(startPoint,symbol,f1,f2,isCase,jump,f3);
		length++;
		Syntax.quadNum++;
		/*if(a[startPoint-sp].isCase=='Y')
			System.out.println(a[startPoint-sp].no+" "+a[startPoint-sp].symbol+" "+a[startPoint-sp].f1+" "+a[startPoint-sp].f2+" "+a[startPoint-sp].jump+"   "+a[startPoint-sp].isCase);
			else
				System.out.println(a[startPoint-sp].no+" "+a[startPoint-sp].symbol+" "+a[startPoint-sp].f1+" "+a[startPoint-sp].f2+" "+a[startPoint-sp].f3+"   "+a[startPoint-sp].isCase);*/	
		return a[startPoint-sp].no;
	}
	
	public void print()
	{
		int i=0;
		for(;i<length;i++)
		{
			if(a[i].isCase=='Y')
			System.out.println(a[i].no+" "+a[i].symbol+" "+a[i].f1+" "+a[i].f2+" "+a[i].jump+"   "+a[i].isCase);
			else
				System.out.println(a[i].no+" "+a[i].symbol+" "+a[i].f1+" "+a[i].f2+" "+a[i].f3+"   "+a[i].isCase);	
			
		}
	}


}