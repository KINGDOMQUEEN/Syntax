package syntax;



import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.TextArea;
import java.io.IOException;
import java.util.ArrayList;

import word.*;
import java.lang.Integer;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;





 








public class Syntax {
	public int currentTokenNumber=0;
	static int  quadNum = 100;
	int row=1;
	private int index = 0;
	Word word;
	ArrayList<ParseElement> rs ;
	String str = "";
	String[] flag ;
	int flagindex = 0;
	public FourUnitList quadList;
	int firstIndex;
	public Syntax() throws Exception
	{
		//quadList=new FourUnitList();
		//word = new Word();
		//rs = word.WordParse();
		//currentTokenNumber = word.currentToken;
		//System.out.println("^^^%&^%&^%^"+currentTokenNumber);
		//word.TestArray();
		//WordAnalysis();
		
		
	}
	
	public void WordAnalysis() throws Exception
	{
		currentTokenNumber=0;
		quadNum = 100;
		row=1;
		index = 0;
		firstIndex=0;
		quadList=new FourUnitList();
		word = new Word();
		rs = new ArrayList<ParseElement>(); 
		rs = word.WordParse();
		str = "";
		flag = new String[10];
		for(int i=0;i<10;i++)
			flag[i] = "false";
		flagindex = 0;
		currentTokenNumber = word.currentToken;
	}
	
	public int nextQuad()
	{
		return quadNum+1;
	}
	
	public void P()
	{
		//System.out.println(rs);
		if(rs.get(index).getValue().equalsIgnoreCase("program"))
		{
			index++;
			//System.out.println(rs.get(index).getValue());
			this.PB();
		}
		else System.out.println("程序必须以program 关键字开始");
		
	}
	
	void PB()
	{
		//System.out.println("we are here");
		if(rs.get(index) != null)//程序名
		{
			index++;
			if(rs.get(index).getValue().equalsIgnoreCase(";"))
			{
				//System.out.println(rs.get(index).getValue());
				index++;
				row++;
				this.D();

				//System.out.println(rs.get(index).getValue());
				row++;
				//System.out.println("we are here");
				//index++;
				this.Y();

				
			}
			else System.out.println("缺少程序名");
		}
	}
	
	void D()
	{
		
		if(rs.get(index).getType().equalsIgnoreCase("var"))//是否是var
		{
			//System.out.println(rs.get(index).getValue());
			index++;
			this.V();
			if(rs.get(index).getValue().equalsIgnoreCase(";"))
			{
				//System.out.println(rs.get(index).getValue());
				index++;
			}
			else System.out.println("缺少分号");
		}
		
		
		
		
//		if(rs.get(index).getType().equalsIgnoreCase("var"))//是否是var
//		{
//			System.out.println(rs.get(index).getValue());
//			index++;
//			//System.out.println("we are here");
//			if(rs.get(index).getType().equalsIgnoreCase("varType"))
//			{
//				System.out.println(rs.get(index).getValue());
//				index++;
//				//System.out.println("we are here");
//				this.V();
//				if(rs.get(index).getValue().equalsIgnoreCase(";"))
//				{
//					System.out.println(rs.get(index).getValue());
//					index++;
//					
//				}else System.out.println("缺少分号AtD3"+row);
//				
//				
//				
//			}else
//				System.out.println("不能识别的变量类型");
//		}
//		
	}
	
	void V()
	{
		Attribute attrOfV = new Attribute();
		if(rs.get(index).getType().equalsIgnoreCase("varType"))
		{
			//System.out.println("定义语句");
			//System.out.println(rs.get(index).getValue());
			index++;
		}
		//else System.out.println("应该为变量类型");
		if(rs.get(index) != null)
		{
			//System.out.println(rs.get(index).getValue());
			//attrOfV.PLACE = ""+word.entry(rs.get(index).toString());
			index++;
		}
		//else System.out.println("无变量");
		this.VA();
	}
	
	void VA()
	{
		if(rs.get(index).getValue().equalsIgnoreCase(","))
		{
			//System.out.println(rs.get(index).getValue());
			index++;
			this.V();
		}
	}
	
/*	void V()
	{
		if(rs.get(index)!=null)
		{
			System.out.println(rs.get(index).getValue()+"V");
			index++;
			this.VA();
			
		}
		else System.out.println("无变量");
	}
	
	void VA()
	{
		if(rs.get(index).getValue().equalsIgnoreCase(","))
		{
			System.out.println(rs.get(index).getValue()+"VA");
			index++;
			this.V();
		}
		
	}*/
	
	Attribute K()//L
	{
		Attribute K1=new Attribute();
		Attribute Y1=new Attribute();
		Y1=this.Y();
		K1=this.KA(Y1);
		
		
		//K1.nextList=0;
		/*if(rs.get(index).getValue().equalsIgnoreCase("begin"))
		{
			//System.out.println(rs.get(index).getValue());
			index++;
			//Attribute C1=new Attribute();
			//C1=this.C();
			K1=this.Y();
			//K1.nextList=C1.nextlist;
			if(rs.get(index).getValue().equalsIgnoreCase("end"))
			{
				//System.out.println("end");
				index++;
			}
			else System.out.println("缺少end");
		}else System.out.println("缺少begin");*/
//		System.out.println("进入K");
//		while(rs.get(index).getValue()!="end")
//		{
//			if(rs.get(index).getValue().equalsIgnoreCase(";"))
//			{
//				index++;
//				Y();
//
//			}
//			else {
//				
//				Y();
//
//			}
//			
//		}
//		
		return K1;
		
	}
	
	Attribute KA(Attribute Y1)//L'
	{
		M M1 = new M();
		Attribute KA = new Attribute();
		Attribute Y = new Attribute();
		
		KA.nextList=0;
		if(rs.get(index).getValue().equalsIgnoreCase(";"))
		{
			
			index++;
			M1.quad = Syntax.quadNum;
			quadList.backPatch(Y1.nextList, M1.quad);
			Y=this.Y();
			KA=this.KA(Y);
			//return KA;
			
		}
		else
		{
			KA.copy(Y);
		}
		return KA;
	
	}
	
	
	/*void C()
	{
		this.Y();
		if(rs.get(index).getValue().equalsIgnoreCase(";"))
		{
			//System.out.println(";");
			index++;
			this.C();
		}
		//else System.out.println("缺少;");
	}*/
	
	Attribute Y()//S
	{
		//System.out.println("进入Y");
		Attribute S = new Attribute();
		if(rs.get(index).getType().equalsIgnoreCase("variable"))
		{
			//System.out.println("赋值语句");
			S = this.F();

		}
		else if(rs.get(index).getValue().equalsIgnoreCase("while"))
		{
			//System.out.println(rs.get(index).getValue());
			S=this.X();
			//System.out.println("循环语句");
		}
		else if(rs.get(index).getValue().equalsIgnoreCase("if"))
		{
			//System.out.println(rs.get(index).getValue());
			S=this.T();
			//System.out.println("条件语句");
		}
		
		/*else if(rs.get(index).getValue().equalsIgnoreCase("begin"))
		{
			//System.out.println(rs.get(index).getValue());
			S=this.K();
		}
		//else this.F();*/
		
		else if(rs.get(index).getValue().equalsIgnoreCase("begin"))
		{
			//System.out.println(rs.get(index).getValue());
			index++;
			//Attribute C1=new Attribute();
			//C1=this.C();
			S=this.K();
			//K1.nextList=C1.nextlist;
			if(rs.get(index).getValue().equalsIgnoreCase("end"))
			{
				//System.out.println("end");
				index++;
				//System.out.println("begin-and语句段");
			}
			else System.out.println("缺少end");
		}

		return S;
		
	}
	
	Attribute F()
	{
		String valuable;
		Attribute F1= new Attribute();
		Attribute B= new Attribute();
		if(rs.get(index).getType().equalsIgnoreCase("variable"))
		{
			valuable=rs.get(index).getValue();
			index++;
			if(rs.get(index).getValue().equalsIgnoreCase(":="))
			{
				if(flag[flagindex].equalsIgnoreCase("true"))
					str+="赋值语句  ";
				else ;//System.out.println("赋值语句");
				index++;
				B=this.B();
				if(B.firstIndex==0)
					quadList.gen(Syntax.quadNum,":=",B.PLACE,null,'N',-1,valuable);
				else
					quadList.gen(Syntax.quadNum,":=",B.PLACE,null,'N',-1,valuable);

				
			}
			//else System.out.println("语法错误： 缺少表达式右边部分");
		}
		//else System.out.println("语法错误： 赋值语句左部必须为变量");
		F1.nextList = 0;
		return F1;
	}
	
	Attribute T()//if
	{
		//Attribute T1=new Attribute();
		M M1=new M();
		M M2=new M();
		N N1=new N();
		Attribute S1 = new Attribute();
		Attribute S2 = new Attribute();
		Attribute S = new Attribute();
		Attribute E=new Attribute();
		if(rs.get(index).getValue().equalsIgnoreCase("if"))
		{
			index++;
			E=this.J();
			if(rs.get(index).getValue().equalsIgnoreCase("then"))
			{
				//System.out.println("if-then语句，嵌套：");
				index++;
				M1.quad = Syntax.quadNum;
				/*if(rs.get(index).getValue().equalsIgnoreCase("begin"))
				{
					Attribute K1=new Attribute();
					K1=this.K();
					S.nextList=K1.nextList;
				}
				else */
				{
					//index++;
					flag[flagindex] = "true";
					S1=this.Y();
					flag[flagindex]= "false";
					flagindex ++;
					
				}
				if(rs.get(index).getValue().equalsIgnoreCase("else"))
				{
					N1.nextlist = Syntax.quadNum;
					quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y',0,null);//(  j  , — , — , 0 )  
					M2.quad = Syntax.quadNum;
					
					
					
					if(flag[flagindex].equalsIgnoreCase("true"))
					{str+="if-then-else,嵌套：";}
					else ;//System.out.println("if-then-else,嵌套：");
					index++;
						//index++;
					S2=this.Y();
					//System.out.println(M1.quad+"  "+M2.quad);
					//System.out.println(E.trueList+"  "+E.falseList);
					quadList.backPatch(E.trueList, M1.quad );
					quadList.backPatch(E.falseList, M2.quad );
					S.nextList=quadList.merge(S1.nextList, N1.nextlist, S2.nextList);
					//S.nextList=quadList.merge(S1.nextList, N1.nextlist, S2.nextList);
				}
				else 
				{
					//String sub;
					//System.out.println("if-then 语句，嵌套："+str);
					if(flag[flagindex].equalsIgnoreCase("true"))
					{str+="if-then 语句，嵌套："+str;}
					else ;//System.out.println("if-then 语句，嵌套："+str);
					quadList.backPatch(E.trueList, M1.quad );
					S.nextList=quadList.merge(E.falseList,S1.nextList);
					
			        //if(flag ==true) str+="if-then 语句，嵌套：";
					//str = null;
				}
				
			}
		}
		//quadList.print();
		return S;
	}
	
	Attribute X()//while
	{
		Attribute X=new Attribute();
		Attribute E=new Attribute();
		Attribute S=new Attribute();
		M M1= new M();
		M M2= new M();
		if(rs.get(index).getValue().equalsIgnoreCase("while"))
		{
			index++;
			M1.quad=Syntax.quadNum;
			E=this.J();
			if(rs.get(index).getValue().equalsIgnoreCase("do"))
			{
				M2.quad=Syntax.quadNum;
				
				if(flag[flagindex].equalsIgnoreCase("true"))
					{str+="while-do语句,嵌套：";}
				else ;//System.out.println("while-do语句,嵌套：");
				index++;
				S=this.Y();
				quadList.backPatch(S.nextList, M1.quad );
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y',M1.quad,null);
				quadList.backPatch(E.trueList, M2.quad );
				X.nextList= E.falseList;
			}
			//this.K();
		}
		return X;
	}
	
	Attribute J()//布尔
	{
		Attribute E1 = new Attribute();
		Attribute E2 = new Attribute();
		Attribute J1 = new Attribute();
		M M1=new M();
		E1=E();
		if(rs.get(index).getValue().equalsIgnoreCase("and"))
		{
			index++;
			M1.quad=Syntax.quadNum;
			E2=E();
			quadList.backPatch(E1.trueList, M1.quad );
			J1.trueList =E2.trueList;
			//System.out.println(E1.trueList+"  "+ E2.trueList);
			//quadList.print();
			J1.falseList =quadList.merge(E1.falseList,E2.falseList);
		}
		else if(rs.get(index).getValue().equalsIgnoreCase("or"))//or
		{
			index++;
			M1.quad=Syntax.quadNum;
			E2=E();
			quadList.backPatch(E1.falseList, M1.quad );
			J1.falseList =E2.falseList;
			J1.trueList =quadList.merge(E1.trueList,E2.trueList);
		}
		
		else
		{
			J1.trueList =E1.trueList;
			J1.falseList = E1.falseList;
		}
		while(rs.get(index).getValue().equalsIgnoreCase("and")||rs.get(index).getValue().equalsIgnoreCase("or"))
		{
			if(rs.get(index).getValue().equalsIgnoreCase("and"))
			{
				index++;
				M1.quad=Syntax.quadNum;
				E1=J1;
				E2=E();
				quadList.backPatch(E1.trueList, M1.quad );
				J1.trueList =E2.trueList;
				//System.out.println(E1.trueList+"  "+ E2.trueList);
				//quadList.print();
				J1.falseList =quadList.merge(E1.falseList,E2.falseList);
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("or"))//or
			{
				index++;
				M1.quad=Syntax.quadNum;
				E1=J1;
				E2=E();
				quadList.backPatch(E1.falseList, M1.quad );
				J1.falseList =E2.falseList;
				J1.trueList =quadList.merge(E1.trueList,E2.trueList);
			}
			
		}
		/*while(rs.get(index).getValue().equalsIgnoreCase("and")||rs.get(index).getValue().equalsIgnoreCase("or"))
		{
			if(rs.get(index).getValue().equalsIgnoreCase("and"))
			{
				index++;
				M1.quad=Syntax.quadNum;
				E2=E();
				quadList.backPatch(E1.trueList, M1.quad );
				J1.trueList =E2.trueList;
				System.out.println(E1.trueList+"  "+ E2.trueList);
				quadList.print();
				J1.falseList =quadList.merge(E1.falseList,E2.falseList);
			}
			else
			{
				index++;
				M1.quad=Syntax.quadNum;
				E2.PLACE = this.E().PLACE;
				quadList.backPatch(E1.falseList, M1.quad );
				J1.falseList =E2.falseList;
				J1.trueList =quadList.merge(E1.trueList,E2.trueList);
			}
			
		}*/
		J1.PLACE = E1.PLACE;
		return J1;
		
	}
	
	
	Attribute E()//比较
	{
		Attribute B1 = new Attribute();
		Attribute B2 =new Attribute();
		Attribute E1 =new Attribute();
		B1.PLACE = this.B().PLACE;
		//while(rs.get(index).getValue().equalsIgnoreCase("=")||rs.get(index).getValue().equalsIgnoreCase(">")||rs.get(index).getValue().equalsIgnoreCase("<")||rs.get(index).getValue().equalsIgnoreCase(">=")||rs.get(index).getValue().equalsIgnoreCase("<="))
		//{
			if(rs.get(index).getValue().equalsIgnoreCase("="))
			{
				index++;
				B2.PLACE = this.B().PLACE;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "Jz",B1.PLACE,B2.PLACE , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);
			}
			else if(rs.get(index).getValue().equalsIgnoreCase(">"))
			{
				index++;
				B2.PLACE = this.B().PLACE;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J>",B1.PLACE,B2.PLACE , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);			
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("<"))
			{
				index++;
				B2.PLACE = this.B().PLACE;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J<",B1.PLACE,B2.PLACE , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);			
			}
			else if(rs.get(index).getValue().equalsIgnoreCase(">="))
			{
				index++;
				B2.PLACE = this.B().PLACE;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J>=",B1.PLACE,B2.PLACE , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);			
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("<="))
			{
				index++;
				B2.PLACE = this.B().PLACE;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J<=",B1.PLACE,B2.PLACE , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);			
			}
			
			else//is itself
			{
				//index++;
				E1.trueList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "Jnz",B1.PLACE,"0" , 'Y', 0,null);
				E1.falseList =Syntax.quadNum;
				quadList.gen(Syntax.quadNum, "J", "0", "0", 'Y', 0,null);			
			}
	
		//}
		
		return E1;
	}
	
	Attribute B()
	{
		Attribute B1 = new Attribute();
		Attribute I1 = new Attribute();
		Attribute I2 = new Attribute();
		if(rs.get(index).getType().equalsIgnoreCase("variable")||rs.get(index).getType().equalsIgnoreCase("num"))
		{
			I1.PLACE = (rs.get(index).getValue().toString());
			index++;
			firstIndex=0;
			while(rs.get(index).getValue().equalsIgnoreCase("+")||rs.get(index).getValue().equalsIgnoreCase("-")||rs.get(index).getValue().equalsIgnoreCase("*")||rs.get(index).getValue().equalsIgnoreCase("/"))
			{
			if(rs.get(index).getValue().equalsIgnoreCase("+"))
			{
				index++;
				I2.PLACE = (rs.get(index).getValue().toString());
				index++;
				if(firstIndex==0)
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "+", I1.PLACE, I2.PLACE, 'N',-1, "T"+firstIndex);
				}
				else 
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "+", "T"+(firstIndex-1), I2.PLACE, 'N',-1, "T"+firstIndex);
				}
				
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("-"))
			{
				index++;
				I2.PLACE = (rs.get(index).getValue().toString());
				index++;
				if(firstIndex==0)
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "-", I1.PLACE, I2.PLACE, 'N',-1, "T"+firstIndex);
				}
				else 
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "-", "T"+(firstIndex-1), I2.PLACE, 'N',-1, "T"+firstIndex);
				}
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("*"))
			{
				index++;
				I2.PLACE = (rs.get(index).getValue().toString());
				index++;
				if(firstIndex==0)
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "*", I1.PLACE, I2.PLACE, 'N',-1, "T"+firstIndex);
				}
				else 
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "*", "T"+(firstIndex-1), I2.PLACE, 'N',-1, "T"+firstIndex);
				}
			}
			else if(rs.get(index).getValue().equalsIgnoreCase("/"))
			{
				index++;
				I2.PLACE = (rs.get(index).getValue().toString());
				index++;
				if(firstIndex==0)
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "/", I1.PLACE, I2.PLACE, 'N',-1, "T"+firstIndex);
				}
				else 
				{
					firstIndex++;
				quadList.gen(Syntax.quadNum, "/", "T"+(firstIndex-1), I2.PLACE, 'N',-1, "T"+firstIndex);
				}
			}
			else 
			{
				I2.PLACE = rs.get(index).getValue();
			}
			
			}
			//System.out.println("下一字符为："+rs.get(index).getType());
		}
		
			if(firstIndex==0)
			{
				B1.PLACE=I1.PLACE;
				B1.firstIndex=firstIndex;
			}
			else
			{
				
				B1.PLACE="T"+firstIndex;
				B1.firstIndex=firstIndex;
				
			}
            return B1;
	}
	
//	void J()
//	{
//		this.B();
//		if(rs.get(index).getValue().equalsIgnoreCase("<")||rs.get(index).getValue().equalsIgnoreCase("<=")||rs.get(index).getValue().equalsIgnoreCase(">")||rs.get(index).getValue().equalsIgnoreCase(">=")||rs.get(index).getValue().equalsIgnoreCase("="))
//			{
//				index++;
//				this.B();
//			}
//		//else System.out.println("语法错误");
//		this.JA();
//	}
//	
//	
//	void JA()
//	{
//		if(rs.get(index).getValue().equalsIgnoreCase("and"))
//		{
//			index++;
//			//System.out.println(rs.get(index).getValue());
//			this.J();
//			this.JA();
//		}
//	}
//	
//	void B()
//	{
//		if(rs.get(index).getType().equalsIgnoreCase("variable"))
//		{
//			//System.out.println(rs.get(index).getValue());
//			index++;
//			this.BA();
//		}
//		else if(rs.get(index).getType().equalsIgnoreCase("num"))
//		{
//			//System.out.println(rs.get(index).getValue());
//			index++;
//			this.BA();
//		}
//		else {
//			System.out.println("语法错误");
//			index++;
//			//this.B();
//		}
//		
//	}
//	
//	void BA()
//	{
//
//		if(rs.get(index).getValue().equalsIgnoreCase("+")||rs.get(index).getValue().equalsIgnoreCase("-")||rs.get(index).getValue().equalsIgnoreCase("*")||rs.get(index).getValue().equalsIgnoreCase("/"))
//		{
//			index++;
//			this.B();
//			this.BA();
//			
//		}
//
//	}
	public static void main(String args[]) throws Exception
	{
		Syntax s = new Syntax();
		s.WordAnalysis();
		s.P();
		s.quadList.print();
}
}
