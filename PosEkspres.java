import java.io.*;
import java.util.*;
import java.text.*;
import javax.swing.JOptionPane;

public class PosEkspres
{
	private int id;
	private Envelop envelop; //aggregation
	private Payment payment; //aggreagation
	private DeliveryStatus status; //aggregation

	public PosEkspres(){}

	public PosEkspres(int a, Envelop c, Payment d, DeliveryStatus e)
	{
		id = a;
		envelop = c;
		payment = d;
		status = e;
	}

	public void setId(int id)
	{
		this.id=id;
	}

	public int getId()
	{
		return id;
	}

	public void displayEnvelop()
	{
		envelop.readData();
	}
	public double getTotal()
	{
		return payment.getTotalPayment();
	}

	public void print()  // concept aggregation untuk delivery n payment
	{
		System.out.println("\t\t\tCutomer ID: "+id);
		System.out.println("\t\t\tYour Tracking Number: " +status.getTrackNo());
	}
	
	public void envelopHandler(){   // apply concept aggregation untuk display zone

		Envelop envelop=new Envelop();
		envelop.viewZone();
	}

	
	public void envelopHandler(int a ,int b,String c){   // apply concept aggregation untuk print zone
		int type=a,noOfEnvelop=b;
		String color=c;
		
		Envelop envelop=new Envelop(type,noOfEnvelop,color);
		envelop.printZone();
	}

	public static void main(String [] args) {

	
			
			String color="";
			double price=0.0;
			int id = 0;
			Scanner in = new Scanner(System.in);
			String c, a, b,name,address,ct,st,poskod, kw;
			int poscode, codeEnvelop, bil, people, zoneCode;
			
			c = JOptionPane.showInputDialog("Enter Your Access Type\n1.USER\n2.ADMIN");
			people = Integer.parseInt(c);
			
			Information sender=new Information();
			Information rec=new Information();
			Information date= new Information();
			
			while(people==1 || people==2){
				
				//User program
					if(people==1){
						
						Scanner cin = new Scanner(System.in);
						JOptionPane.showMessageDialog(null,"Enter Sender's Information");						
						name = JOptionPane.showInputDialog("Enter Sender name");
						address = JOptionPane.showInputDialog("Enter Sender address");
						st = JOptionPane.showInputDialog("Enter state");
						ct = JOptionPane.showInputDialog("Enter city");
						poskod = JOptionPane.showInputDialog("Enter poscode");
						poscode=Integer.parseInt(poskod);
						
						sender=new Information(name,address,st,ct,poscode);
						
						JOptionPane.showMessageDialog(null,"Enter recepient's Information");
						name = JOptionPane.showInputDialog("Enter recepient name");
						address = JOptionPane.showInputDialog("Enter recepient address"); 
						st = JOptionPane.showInputDialog("Enter state");
						ct = JOptionPane.showInputDialog("Enter city");
						poskod = JOptionPane.showInputDialog("Enter poscode");
						poscode=Integer.parseInt(poskod);
					
						rec=new Information(name,address,st,ct,poscode);
						
						Envelop.readData();
						
						a = JOptionPane.showInputDialog("Select Envelop Code :  1 / 2 / 3 / 4 / 5 ");
						
						b = JOptionPane.showInputDialog("Enter Envelop Quantity");

						codeEnvelop = Integer.parseInt(a);
						bil = Integer.parseInt(b);

					//select envelop code
					
					if(codeEnvelop==1){
						Envelop ca=new CodeA();
						price=ca.calEnvelopPrice();//overide
					}
						
					else if(codeEnvelop==2){
						Envelop cb=new CodeB();
						price=cb.calEnvelopPrice();
					}
					else if(codeEnvelop==3){
						Envelop cc=new CodeC();
						price=cc.calEnvelopPrice();//overide
					}
					else if(codeEnvelop==4){
						Envelop cd=new CodeD();
						price=cd.calEnvelopPrice();//overide
					}
					else if(codeEnvelop==5){
							Envelop ce=new CodeE();
						price=ce.calEnvelopPrice();//overide
					}

					//Envelop vz = new Envelop();
					//vz.viewZone(); // ni cara biasa untuk call method panggil display zone
					
					PosEkspres en=new PosEkspres(); // method aggregation concept untuk call print zone
					en.envelopHandler();
					
					kw = JOptionPane.showInputDialog("Enter postage zone ( 1 / 2 / 3 / 4 / 5)");
					
					zoneCode = Integer.parseInt(kw);
					if(zoneCode==1)
						color = "red";
					else if(zoneCode==2)
						color = "blue";
					else if(zoneCode==3)
						color = "purple";
					else if(zoneCode==4)
						color = "yellow";
					else if(zoneCode==5)
						color = "orange";
								
					JOptionPane.showMessageDialog(null,"Please check your mailbox color");
					
					Payment p = new Payment(price, bil); // payment
					
					Envelop e = new Envelop(codeEnvelop,bil, color); //Envelop
				
					DeliveryStatus ds = new DeliveryStatus(); // delivery
								
					JOptionPane.showMessageDialog(null,"\t\t\t***Thank you***");
					System.out.println("\n\t\t---------------------------------------------");
					System.out.println("\t\t\t\t** Payment **");
					System.out.println("\t\t---------------------------------------------");
					System.out.printf("\n\t\t\tTotal Price: RM %.2f\n", p.getTotalPayment());
					System.out.printf("\n\t\t\tEnter your price payment => RM");
					double pay = cin.nextDouble();
					
					System.out.println("\n\t\t\tYour receipt has been generated..");
					
					id += 1;
					System.out.println("\n\t\t---------------------------------------------");
					System.out.println("\t\t\t\t** Receipt **");
					System.out.println("\t\t---------------------------------------------");
					
					date.showDate();
					System.out.println("\n\t\t\tZone:  " +kw);
					
					System.out.println("\t\t\t Code :" +a);
					
					System.out.println("\t\t\tItem Quantity: " +b+ " envelope(s)/box(es) \n");
					
					PosEkspres payment = new PosEkspres(id,e,p,ds);
					payment.print(); // aggreagation cencept call untuk print
					
					PosEkspres envelop=new PosEkspres(); // aggregation concept call
					envelop.envelopHandler(codeEnvelop,bil, color);
					
					System.out.printf("\t\t\tChange: RM%.2f",(pay - p.getTotalPayment()));
															
					}//end of user

					//Admin program
					if(people==2){
					
					
					//print detail sender,rec
					//menu 
					
					String username, password;
					int un,ps;
							
					username = JOptionPane.showInputDialog("Username");
					password = JOptionPane.showInputDialog("Password");
					
					un = Integer.parseInt(username);
					ps = Integer.parseInt(password);
					
						if ( un==1234 && ps ==1234) {
							
							int menu;
							Scanner cin = new Scanner(System.in);
							do{
								System.out.println("\n\t\t---------------------------------------------");
								System.out.println("\t\t\t	WELCOME ADMIN/STAFF");
								System.out.println("\t\t---------------------------------------------");
								System.out.println("\n\t\t\t  1. View Sender information");
								System.out.println("\t\t\t  2. View Recipient information");
								System.out.println("\t\t\t  0. Exit");
								System.out.print("\n\t\t\t  Enter your options menu => ");
								menu = cin.nextInt();
							
							
							if(menu == 1){
								System.out.println("\n\t\t\t*******Sender Detail Information*******");
								sender.getInfo();
							}
							
							else if(menu == 2){
								System.out.println("\n\t\t\t******Recipient Detail Information******");
								rec.getInfo();
							}
			
							else if(menu == 0){
								System.out.println("\n\t\t\tExit the System..");
							}
							
							else
								System.out.println("\n\t\t\tWrong options TRY AGAIN!");

							}while(menu != 0);
							
						} else {
							
							JOptionPane.showMessageDialog(null,"Wrong Password / Username");
						}
				}
			
				c = JOptionPane.showInputDialog("Enter Your Access Type\n1.USER\n2.ADMIN\n3.EXIT");
				people = Integer.parseInt(c);
			
			}// keluar loop while
			


		}
    }
	



	class Payment
	{
		private double price;
		private int quantity;

		public Payment(){}

		public Payment(double a, int b)
		{
			price = a;
			quantity = b;
		}

		public void setPrice(double price)
		{
			this.price=price;
		}

		public void getQuantity(int quantity)
		{
			this.quantity=quantity;
		}

		public double getPrice()
		{
			return price;
		}

		public int getQuantity()
		{
			return quantity;
		}

		public double getTotalPayment()
		{
			return price*quantity;
		}
	}

	class Envelop
	{
		private int type;
		private int noOfEnvelop;
		private String zone;
		
		public Envelop(){}
		
		
		public Envelop(int a, int b, String c)
		{
			type = a;
			noOfEnvelop = b;
			zone=c;
		}

		public void setType(int type)
		{
			this.type=type;
		}

		public void setNoOfEnvelop(int noOfEnvelop)
		{
			this.noOfEnvelop=noOfEnvelop;
		}

		public void setZone(String zone)
		{
			this.zone=zone;
		}
		
		public String getZone()
		{
			return zone;
		}
		
		public int getType()
		{
			return type;
		}

		public int getNoOfEnvelop()
		{
			return noOfEnvelop;
		}

		public static void readData()
		{
			String fileName = "Ukuran.txt";

			String line = null;

			try
			{
				FileReader fileReader = new FileReader(fileName);

				BufferedReader bufferedReader = new BufferedReader(fileReader);

				int i=0;
				System.out.println("\nBelow is the informations of Envelope and Boxes Size.");
				System.out.println("\nCode, Size, \tMax.Weight(gram),\tType, \t\tprice(RM)");

				while((line = bufferedReader.readLine()) != null)
				{
					System.out.printf(++i+ ": ");
					System.out.println(line);
				}
				System.out.println();
				bufferedReader.close();
			 }

			 catch(FileNotFoundException ex) //exceptionalHandling
			 {
				 System.out.println("Unable to open file '" + fileName + "'");
			 }
			 catch(IOException ex)  //exceptionalHandling
			 {
				System.out.println("Error reading file '" + fileName + "'");
			 }
		}
		
		public void printZone(){
				System.out.println("\t\t\tYour mailbox color zone is "+this.zone);
		}
		public void viewZone(){
					System.out.println("-------------------------------Zone-----------------------------------------"); 
					System.out.println("Delivery" +"\t" +"\tZone Coverage"); 
					System.out.println("Zone 1" +"\t" +"\tLocal Town Delivery");
					System.out.println("Zone 2" + "\t" +"\tWithin Peninsular Malaysia/Within Sarawak/ Within Sabah\n");
		
					System.out.println("-------------------------------Zone-----------------------------------------"); 
					System.out.println("Delivery" +"\t" +"\tZone Coverage"); 
					System.out.println("Zone 3" +"\t" +"\tBetween Sabah & Sarawak");
					System.out.println("Zone 4" + "\t" +"\tBetween Peninsular Malaysia & Sarawak");
					System.out.println("Zone 5" + "\t" +"\tBetween Peninsular Malaysia & Sabah\n");
		}
		
		public double calEnvelopPrice(){
			return 4; //fixed price if no envelope
		}
	}

	class CodeA extends Envelop{
		
		public CodeA(){}
		
		public double calEnvelopPrice(){
			
			return (7.31);
		}
	}

	class CodeB extends Envelop{
		public CodeB(){}
		
		
		public double calEnvelopPrice(){
			
			return (10.49);
		}
	}

	class CodeC extends Envelop{
		public CodeC(){}
		
		
		public double calEnvelopPrice(){
			
			return (13.78);
		}
	}

	class CodeD extends Envelop{
		public CodeD(){}
		
		
		public double calEnvelopPrice(){
			
			return (21.20);
		}
	}

	class CodeE extends Envelop{
		public CodeE(){}
		
		
		public double calEnvelopPrice(){
			
			return (31.8);
		}
	}
	 class Information{  //association
		
		private Date date;
		String name, address, state, city;
		int poscode;
		
		Information(){}
		
		Information(String a,String b, String c, String d, int e){
			this.name=a;
			this.address=b;
			this.state=c;
			this.city=d;
			this.poscode=e;
		}
		
		public String displayDate() {
	    Date dNow = new Date( );
	    SimpleDateFormat ft =
	    new SimpleDateFormat ("E yyyy.MM.dd 'at' hh:mm:ss a zzz");

	    return "Date: " + ft.format(dNow);
		}
		
		public void getInfo(){
		
			System.out.println("\n\t\t\t\tName: "+this.name);
			System.out.println("\t\t\t\tAddress: " +this.address);
			System.out.println("\t\t\t\tState: " + this.state);
			System.out.println("\t\t\t\tCity: " + this.city);
			System.out.println("\t\t\t\tPoscode: " + this.poscode);
			System.out.println("\n");
			}
		
		public void showDate(){
				
				System.out.println("\t\t\t"+displayDate());
		}
		
	}

	class DeliveryStatus
	{
		private int trackNo;

		public DeliveryStatus(){}

		public void setTrackNo(int trackNo)
		{
			this.trackNo=trackNo;
		}

		public int getTrackNo() // search internet how nak generate random number
		{
				final int minimum = 10000000;
				final int maximum = 99999999;
				trackNo = minimum + (int)(Math.random() * maximum);
				return trackNo;
		}
	}
	