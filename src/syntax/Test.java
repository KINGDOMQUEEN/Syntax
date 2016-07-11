package syntax;
import   java.awt.BorderLayout; 
import java.awt.FlowLayout;
import   java.awt.event.MouseAdapter; 
import   java.awt.event.MouseEvent; 
import java.io.IOException;

import   javax.swing.JButton; 
import   javax.swing.JFrame; 
import   javax.swing.JTextArea; 
import   javax.swing.JTextField; 


public   class   Test   extends   JFrame   { 
public      JTextArea   textin; 
public   static   JTextArea   text;   //输出JTextArea 
public static   class   System               //覆盖System.out.pringln() 
{ 
private   static   class   out 
{ 

private   static   void   println(String   a) 
{ 
text.append(a); 
text.append("\n"); 

} 
} 
} 

public   static   void   main(String   args[])   { 

try   { 
Test   frame   =   new   Test(); 
frame.setVisible(true); 
}   catch   (Exception   e)   { 
e.printStackTrace(); 
} 
} 

public   Test()   { 
super(); 


setBounds(200,   200,   800,   505); 
setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 

text   =   new   JTextArea(); 
textin = new JTextArea();

getContentPane().add(text,   BorderLayout.CENTER); 
getContentPane().add(textin,   BorderLayout.NORTH); 
//getContentPane().setLayout(new FlowLayout());
//测试按钮	
final   JButton   button   =   new   JButton(); 
button.addMouseListener(new   MouseAdapter()   { 
public   void   mouseClicked(final   MouseEvent   e)   { 
	Syntax s;
	try {
		s = new Syntax();
		s.P();
		//s.quadList.print();
		int i=0;
		for(;i<s.quadList.length;i++)
		{
			if(s.quadList.a[i].isCase=='Y')
			System.out.println(s.quadList.a[i].no+" "+s.quadList.a[i].symbol+" "+s.quadList.a[i].f1+" "+s.quadList.a[i].f2+" "+s.quadList.a[i].jump+"   "+s.quadList.a[i].isCase);
			else
				System.out.println(s.quadList.a[i].no+" "+s.quadList.a[i].symbol+" "+s.quadList.a[i].f1+" "+s.quadList.a[i].f2+" "+s.quadList.a[i].f3+"   "+s.quadList.a[i].isCase);	
			
		}
	} catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	} catch (Exception e2) {
		// TODO Auto-generated catch block
		e2.printStackTrace();
	}
	

} 
}


); 
button.setText( "计算四元式"); 
getContentPane().add(button,   BorderLayout.SOUTH); 
} 

}