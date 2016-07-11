package word;
import java.io.*;
import java.util.ArrayList;

public class Word {
	
	public  static String[] resTable;//锟斤拷锟斤拷锟街憋拷
	public  static String[] tokenTable;//锟斤拷识锟斤拷锟�
	public  int[] numberTable;//锟斤拷锟斤拷锟斤拷
	public  static int currentToken;//锟斤拷时锟斤拷识锟斤拷母锟斤拷锟�
	public  int currentNumber;//锟斤拷时锟斤拷锟斤拷锟侥革拷锟斤拷
	public  ArrayList<ParseElement> rs = new ArrayList<ParseElement>();
	

	public Word(){
		this.resTable = new String[200];
		this.tokenTable = new String[200];
		this.numberTable = new int[200];
		this.resTable[0]="program"; //program
		this.resTable[1]="begin";//begin
		this.resTable[2]="end";//end
		this.resTable[3]="var";//var
		this.resTable[4]="integer";//varType return 5
		this.resTable[5]="if";//other
		this.resTable[6]="then";//other
		this.resTable[7]="else";//other
		this.resTable[8]="do";//other
		this.resTable[9]="while";//other
		this.resTable[10]="boolean";//varType return 11
		this.resTable[11]="int";//varType return 12
		
		currentToken = 0;
		currentNumber = 0;
	}
	
	public int reserve(String strToken){//锟叫讹拷一锟斤拷锟斤拷识锟斤拷锟角凤拷锟角憋拷锟斤拷锟斤拷
		for(int i = 0;i < 12;i++)
		{
			if(strToken.equalsIgnoreCase(resTable[i])){
				return i+1;
			}
		}
		
		return 0;
		
	}
	
	public int entry(String i)
	{
		//System.out.println("currentToken="+currentToken);
		for(int index = 0;index< this.currentToken;index++)
		{
			//System.out.println(tokenTable[index]);
		
			//System.out.println(index);
			if(i.equalsIgnoreCase(tokenTable[index].toString()))
				return index;
		}
		for(int index = 0;index< this.currentNumber;index++)
		{
			//out.println(tokenTable[i]);
		
			//System.out.println("i="+i+" "+numberTable[index]);
			if(i.equalsIgnoreCase(Integer.toString(numberTable[index])))
				return index;
		}
		return -1;
		
	}
	
	public String isBoolean(String strToken){//锟叫讹拷一锟斤拷锟斤拷识锟斤拷锟角凤拷锟角诧拷锟斤拷锟斤拷

		if("true".equals(strToken))
		{
			return "true";
		}
		else if("false".equals(strToken))
		{
			return "false";
		}
		else return "null";
	}
	
	public int insertID(String strToken){//锟斤拷锟绞讹拷锟斤拷锟叫诧拷锟斤拷锟铰的憋拷识锟斤拷
		for(int i = 0;i<this.currentToken;i++)
		{
			if(strToken.equals(tokenTable[i]))
			return currentToken;
		}
		tokenTable[currentToken] = strToken;
		currentToken++;
		//System.out.println(currentToken);
		return currentToken;
		
	}
	
	public int insertConst(String strToken){//
		for(int i = 0;i<this.currentNumber;i++)
		{
			if(strToken.equals(numberTable[i]))
			return currentNumber;
		}
		numberTable[currentNumber] = Integer.parseInt(strToken);
		currentNumber++;
		
		return currentNumber;
		
	}
	
	public void init(){
		

		this.resTable[0]="program"; 
		this.resTable[1]="begin";
		this.resTable[2]="end";
		this.resTable[3]="var";
		this.resTable[4]="integer";
		this.resTable[5]="if";
		this.resTable[6]="then";
		this.resTable[7]="else";
		this.resTable[8]="do";
		this.resTable[9]="while";
		
		currentToken = 0;
		currentNumber = 0;
	}
	
	public void printTokenTable(PrintWriter out)//锟斤拷印锟斤拷识锟斤拷锟�
	{
		for(int i = 0;i< this.currentToken;i++)
		{
			out.println(tokenTable[i]);
		}
	}
	/*
	 * 	this.resTable[0]="program"; //program
		this.resTable[1]="begin";//begin
		this.resTable[2]="end";//end
		this.resTable[3]="var";//var
		this.resTable[4]="integer";//varType return 5
		this.resTable[5]="if";//other
		this.resTable[6]="then";//other
		this.resTable[7]="else";//other
		this.resTable[8]="do";//other
		this.resTable[9]="while";//other
		this.resTable[10]="boolean";//varType return 11
		this.resTable[11]="int";//varType return 12
	 */
	public String judgeTypeByCode(int code)
	{

		if(code==1)
			return "program";
		else if(code==2)
			return "begin";
		else if(code==3)
			return "end";
		else if(code==4)
			return "var";
		else if(code==5)
			return "varType";
		else if(code==6)
			return "other";
		else if(code==7)
			return "other";
		else if(code==8)
			return "other";
		else if(code==9)
			return "other";
		else if(code==10)
			return "other";
		else if(code==11)
			return "varType";
		else 
			return "varType";

	}
	
	public void printNumberTable(PrintWriter out)//锟斤拷印锟斤拷锟斤拷锟�
	{
		for(int i = 0;i< this.currentNumber;i++)
		{
			//out.println(numberTable[i]);
		}
	}
	
	public ArrayList<ParseElement> WordParse() throws IOException{
		
		
		FileInputStream input = new FileInputStream(new File("C:\\Users\\liu\\workspace\\in.txt"));
		PushbackInputStream in = new PushbackInputStream(input);
		String strToken;
		int line = 0, col = 0,errorcount=0;
		Word s = new Word();
		
		char c;
		int intofc;
		PrintWriter out = new PrintWriter("C:\\Users\\liu\\workspace\\out.txt");
		int code, value;
		while((intofc=in.read())!=-1)
		{
		strToken = "";
		c=(char)intofc;
		col++;
		if(Character.isLetter(c)){
			while(Character.isLetter(c)||Character.isDigit(c))
			{
				strToken+=c;
				c=(char)in.read();
				col++;
			}
			in.unread(c);
			col-=1;
			//strToken.substring(0, strToken.length()-1);
			code = s.reserve(strToken);
			
			if(code==0){
			value = s.insertID(strToken);
			out.println("$ID,"+value);
			ParseElement pe = new ParseElement();
			pe.setType("variable");
			pe.setValue(strToken);
			rs.add(pe);
			}
			else 
			{
				out.println(code+",-");
				ParseElement pe = new ParseElement();
				pe.setType(this.judgeTypeByCode(code));
				pe.setValue(strToken);
				rs.add(pe);
				
			}
			if(s.isBoolean(strToken)=="true")
			{
				out.println("%BOOL,true");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue(strToken);
				rs.add(pe);
			}
			else if(s.isBoolean(strToken)=="false")
			{
				out.println("%BOOL,false");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue(strToken);
				rs.add(pe);
			}
			
		}
		else if(Character.isDigit(c))
		{
			while(Character.isDigit(c))
			{
				strToken+=c;
				c=(char)in.read();
				col++;
			}
			in.unread(c);
			col-=1;
			value = s.insertConst(strToken);
			out.println("$INT,"+value);
			ParseElement pe = new ParseElement();
			pe.setType("num");
			pe.setValue(strToken);
			rs.add(pe);
			
		}
		else{
			
		if(c==':')
		{
			c = (char)in.read();
			col++;
			if(c=='=')
			{
				out.println("$ASSIGN,-");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue(":=");
				rs.add(pe);
			}
			else 
				{
				in.unread(c);
				col-=1;
				}
				
		}
		else if(c=='+')
		{
			out.println("$PLUS,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("+");
			rs.add(pe);
		}
		else if(c=='-')
		{
			out.println("$SUBSTRACT,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("-");
			rs.add(pe);
		}
		else if(c=='*')
		{
			c = (char)in.read();
			col++;
			if(c=='*')
				{	
					out.println("$POWER,-");
					ParseElement pe = new ParseElement();
					pe.setType("other");

					pe.setValue("**");
					rs.add(pe);
					}
			else {
					out.println("$STAR,-");
					ParseElement pe = new ParseElement();
					pe.setType("other");
					pe.setValue("*");
					rs.add(pe);
					in.unread(c);
				}
			
		}
		else if(c==';')
		{
			out.println("$SEMICOLON,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue(";");
			rs.add(pe);
		}
		else if(c=='(')
		{
			out.println("$LPAR,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("(");
			rs.add(pe);
			}
		else if(c==')')
		{	
			out.println("$RPAR,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue(")");
			rs.add(pe);
		}
		else if(c=='{')
		{
			out.println("$LBRACE,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("{");
			rs.add(pe);
			}
		else if(c=='}')
		{	
			out.println("$RBRACE,-");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("}");
			rs.add(pe);
			}
		else if(c==' ')
		{}
		else if(c=='\r')
		{}
		else if(c==',')
		{					
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue(",");
			rs.add(pe);
			}
		else if(c=='\n')
		{
			line++;
			col = 0;
		}
		else if(c=='=')
		{
			out.println("equal");
			ParseElement pe = new ParseElement();
			pe.setType("other");
			pe.setValue("+");
			rs.add(pe);
		}
		else if(c=='/')
		{
			c = (char) in.read();
			col++;
			if(c=='*')
			{
				while(true)
				{
					c=(char)in.read();
					col++;
					if(c=='\n')
					{
						line++;
						col = 0;
					}
					else if(c!='*')
						continue;
					else{
						if ((c = (char) in.read())=='/')
						{
							col++;
							break;}
						}
				}
			}
			else if(c=='/')
			{
					while(true)
					{	
						c = (char)in.read();
						col++;
						if(c!='\n')
							continue;
						else 
							break;
					}
			}
			else 
			{
				out.println("$DIVISION,-");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue("/");
				rs.add(pe);
				in.unread(c);
			}
		}

		else if(c=='<')
		{
			c = (char)in.read();
			col++;
			if(c=='=')
				{
					out.println("<=");
					ParseElement pe = new ParseElement();
					pe.setType("other");
					pe.setValue("<=");
					rs.add(pe);
					}
			else if(c=='>')
				{
					out.println("unequal");
					ParseElement pe = new ParseElement();
					pe.setType("other");
					pe.setValue("<>");
					rs.add(pe);
					}
			else
			{
				out.println("<");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue("<");
				rs.add(pe);
				in.unread(c);
				col-=1;
			}
		}
		else if(c=='>')
		{
			c = (char)in.read();
			col++;
			if(c=='=')
				{
					out.println(">=");
					ParseElement pe = new ParseElement();
					pe.setType("other");
					pe.setValue(">=");
					rs.add(pe);
				}
			else
			{
				out.println(">");
				ParseElement pe = new ParseElement();
				pe.setType("other");
				pe.setValue(">");
				rs.add(pe);
				in.unread(c);
				col-=1;
			}
				
		}
		else 
			{
			System.out.println("ERROR At line "+(line+1)+"col "+col+": undefined token "+"'"+c+"'");
			errorcount++;
			}
		}
		}
		//System.out.println("Total number of Word errors "+errorcount);
		//System.out.println("******* Table of numbers  *******");
		//s.printNumberTable(out);
		//System.out.println("******* Table of tokens   *******");
		//s.printTokenTable(out);
		//System.out.println();
		out.close();
		input.close();
		in.close();
		return rs;
		
	}

	public void TestArray()
	{
		for(int i = 0;i<rs.size();i++)
		{
			//System.out.println("Type "+rs.get(i).getType()+ "   Values "+rs.get(i).getValue());
		}

	}
	
	
	public static void main(String args[]) throws IOException
	{
		Word w = new Word();
		w.WordParse();
		w.TestArray();
		for(int i=0; i<w.rs.size();i++)
			w.rs.get(i).print();
		System.out.println(w.currentToken);
		System.out.println(w.currentNumber);
	}

}


