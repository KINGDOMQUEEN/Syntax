package dart;

import java.util.Set;

import syntax.FourUnit;
import syntax.Syntax;

public class Dart {

	/**
	 * @param args
	 */
	VarTable[] varTable;
	int currentVar;
	Syntax s;
	int nextquad;
	int start;
	int end;
	Dart(){
		try{
				varTable = new VarTable[100];
				this.currentVar = 0;
				this.nextquad = 0;
				int start = 0;
				this.end = 0;
				s = new Syntax();
		}
		catch(Exception e){
			
		}
	}
	
	public String CreateVar(String name){
		for(int i=0;i<currentVar;i++)
		{
			if(varTable[i].name.equals(name))
				return "existed";
		}
		varTable[currentVar] = new VarTable();
		varTable[currentVar].setName(name);
		varTable[currentVar].setValue("0");
		currentVar++;
		return "success";
	}
	
	public String SetVar(String name, String value){
		for(int i=0;i<currentVar;i++)
		{
			if(varTable[i].name.equals(name))
			{
				varTable[i].value = value;
				return "success";
			}
				
		}
		return "error";
	}
	public String GetVar(String name)
	{
		if(name == null)
			return null;
		else if(Character.isDigit(name.charAt(0)) == true)
			return name;
		for(int i=0;i<currentVar;i++)
		{
			if(varTable[i].name.equals(name))
				return varTable[i].value;
		}
		return null;
	}
	
	public void PreRunning(){
		//System.out.print(s.quadList.length);
		for(int i=0;i<s.quadList.length;i++)
		{
				if(s.quadList.a[i].isCase == 'N')
				{
					if(s.quadList.a[i].f1!=null&&Character.isDigit(s.quadList.a[i].f1.charAt(0))==false)
						CreateVar(s.quadList.a[i].f1);
					if(s.quadList.a[i].f2!=null&&Character.isDigit(s.quadList.a[i].f2.charAt(0))==false)
						CreateVar(s.quadList.a[i].f2);
					if(s.quadList.a[i].f3!=null&&Character.isDigit(s.quadList.a[i].f3.charAt(0))==false)
						CreateVar(s.quadList.a[i].f3);
				}
				else
				{
					if(s.quadList.a[i].f1!=null&&Character.isDigit(s.quadList.a[i].f1.charAt(0))==false)
						CreateVar(s.quadList.a[i].f1);
					if(s.quadList.a[i].f2!=null&&Character.isDigit(s.quadList.a[i].f2.charAt(0))==false)
						CreateVar(s.quadList.a[i].f2);
				}
			
		}
	}
	
	public int MicroRunning(FourUnit f){
		if(f.isCase=='Y')
		{
			int p1 = Integer.parseInt(GetVar(f.f1));
			int p2 = Integer.parseInt(GetVar(f.f2));
			int jump = f.jump;
			if(f.symbol.equals("J<"))
			{
				if(p1 < p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else if(f.symbol.equals("J>"))
			{
				if(p1 > p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else if(f.symbol.equals("J<="))
			{
				if(p1 <= p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else if(f.symbol.equals("J>="))
			{
				if(p1 >= p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else if(f.symbol.equals("Jz"))
			{
				if(p1 == p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else if(f.symbol.equals("Jnz"))
			{
				if(p1 != p2)
					return jump-start;
				else
					return nextquad+1;
			}
			else
			{
				return jump-start;
			}
			
			
		}
		else
		{
			int p1 = Integer.parseInt(GetVar(f.f1));
			int p2 = 0;
			int p3 = 0;
			
			if(f.symbol.equals("+"))
			{
				p2 = Integer.parseInt(GetVar(f.f2));
				p3 = p1 + p2;
				SetVar(f.f3, Integer.toString(p3));
				return nextquad+1;
			}
			else if(f.symbol.equals("-"))
			{
				p2 = Integer.parseInt(GetVar(f.f2));
				p3 = p1 - p2;
				SetVar(f.f3, Integer.toString(p3));
				return nextquad+1;
			}
			else if(f.symbol.equals("*"))
			{
				p2 = Integer.parseInt(GetVar(f.f2));
				p3 = p1 * p2;
				SetVar(f.f3, Integer.toString(p3));
				return nextquad+1;
			}
			else if(f.symbol.equals("/"))
			{
				p2 = Integer.parseInt(GetVar(f.f2));
				p3 = p1 / p2;
				SetVar(f.f3, Integer.toString(p3));
				return nextquad+1;
			}
			else 
			{
				SetVar(f.f3, Integer.toString(p1));
				return nextquad+1;
			}
			
		}
		
	}
	
	public void Running(){
		this.start = s.quadList.a[0].no;
		this.end = s.quadList.length;
		System.out.println(end);
		while(nextquad != end){
			System.out.println(nextquad);
			System.out.println(s.quadList.a[nextquad]);
			nextquad = MicroRunning(s.quadList.a[nextquad]);
			
		}
			
		
	}
	public void print(){
		//System.out.println(currentVar);
		for(int i=0;i<currentVar;i++)
			System.out.println(varTable[i].name+"  "+varTable[i].value);
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
				//s = new Syntax();
		try{
		Dart d = new Dart();
		d.s.WordAnalysis();
		d.s.P();
		d.s.quadList.print();
		d.PreRunning();
		d.Running();
		d.print();
		}
		catch(Exception e){
			System.out.print(e);
		}
		
	}

}
