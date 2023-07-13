import java.util.*;

import java.io.*;
interface Ecommerce_company
{
    static void display_menu()throws IOException{}
}
public class FlipZon implements Ecommerce_company {
    static ArrayList<Admin> admin_list = new ArrayList<Admin>();
    static ArrayList<giveaway_deals> giveaway_deals_list = new ArrayList<>();
    static ArrayList<Category> categories_list= new ArrayList<Category>();
    static ArrayList <Customer> registered_Customer = new ArrayList<Customer>();
    public static void main(String args[])throws IOException
    {   
        FlipZon.admin_list.add(new Admin("Beff Jezos", "********"));
        FlipZon.admin_list.add(new Admin("Tony Thomas", "12345678"));
        FlipZon.display_menu();
    }
    static String input()throws IOException
    {
        InputStreamReader read= new InputStreamReader(System.in);
        BufferedReader br= new BufferedReader(read);
        String input=br.readLine();
        return input;
    }
    static void display_menu()throws IOException
    {
        System.out.println("Welcome to FlipZon:");
        System.out.println("\t1) Enter as Admin\n\t2) Explore the Product Catalog\n\t3) Show Available Deals\n\t4) Enter as Customer\n\t5) Exit the Application\n");
        int choice=Integer.parseInt(FlipZon.input());
        switch(choice)
        {
            case 1:
            System.out.println("Dear Admin, Please enter your username and password :\n");
            String username=FlipZon.input();
            String password=FlipZon.input();
            int counter=0;
            for(int i=0;i<admin_list.size();i++)
            {
                if(admin_list.get(i).username.equals(username)&&admin_list.get(i).password.equals(password))
                {
                    counter=1;
                    admin_list.get(i).display_menu();
                }
            }
            if (counter==0)
            {
                System.out.println("Please enter the correct Username or Password next time");
                FlipZon.display_menu();
            }
            break;
            case 2:
            FlipZon.catalog_products();
            FlipZon.display_menu();
            break;
            case 3:
            FlipZon.catalog_deals();
            FlipZon.display_menu();
            break;
            case 4:
            FlipZon.Customer_menu();
            break;
            case 5:
            System.out.println("Thank you for using FlipZon! Application session terminated successfully");
            default:
            System.out.println("You have enterted an incorrect choice !! Please select a correct choice from 1-5\n");
            FlipZon.display_menu();
        }
    }
    static void catalog_deals()throws IOException
    {
        if(giveaway_deals_list.size()>0)
            {
                System.out.println("All Giveaway Deals :\n");
                for(int i=0;i<giveaway_deals_list.size();i++)
                {
                    System.out.print((i+1)+") ");
                    giveaway_deals_list.get(i).display();
                }
            }
            else
            {
                System.out.println("Dear User, there are no deals for now!!! Please check regularly for exciting deals.");
            }
    }
    static void catalog_products()throws IOException
    {
        if(FlipZon.categories_list.size()>0)
        {
            System.out.println("All Categories and their products are :");
            for(int i=0;i<FlipZon.categories_list.size();i++)
            {
                System.out.println("Category :"+FlipZon.categories_list.get(i).name);
                for(int j=0;j<FlipZon.categories_list.get(i).products_list.size();j++)
                {
                    FlipZon.categories_list.get(i).products_list.get(j).get_details();
                }
            }
        }
        else
        {
            System.out.println("Dear User, there are no products for display for now!!! Please check regularly for exciting deals.");
        }
    }
    static void Customer_menu()throws IOException
    {
        System.out.println("Welcome User!! Please choose the option\n\t1) Sign Up\n\t2) Log In\n\t3) Back");
            int sub_choice=Integer.parseInt(FlipZon.input());
            if(sub_choice==1)
            {
                System.out.print("Enter the username and Password for signing up\nUsername:");
                String username=FlipZon.input();
                System.out.print("Password:");
                String password=FlipZon.input();
                registered_Customer.add(new Customer(username, password));
                System.out.println("New Customer successfully signed in!!");
                FlipZon.Customer_menu();
            }
            else if(sub_choice==2)
            {
                System.out.print("Enter the username and Password for log in\nUsername:");
                String username=FlipZon.input();
                System.out.print("Password:");
                String password=FlipZon.input();
                int counter=0;
                for(int i=0;i<registered_Customer.size();i++)
                {
                    if(registered_Customer.get(i).name.equals(username)&&registered_Customer.get(i).password.equals(password))
                    {
                        registered_Customer.get(i).display_menu();
                        counter=1;
                        break;
                    }
                }
                if(counter==0)
                {
                    System.out.println("Please enter the corrrect username or password!!");
                    FlipZon.Customer_menu();
                }
            }
            else if(sub_choice==3)
            {
                FlipZon.display_menu();
            }
            else 
            {
                System.out.println("You have entered an incorrect option please select the correct option!!");
                FlipZon.Customer_menu();
            }
            
    }
}
interface FlipZon_Utilities
{
    void add()throws IOException;
    void delete()throws IOException;
}

class Admin implements FlipZon_Utilities{
    String username,password;
    Admin(String username, String password){
        this.username=username;
        this.password=password;
    }
    void display_menu()throws IOException
    {
        System.out.println("Welcome "+username+"!!\n");
        System.out.println("Please choose any one of the following actions\n\t1) Add category\n\t2) Delete category\n\t3) Add Product\n\t4) Delete Product\n\t5) Set Discount on Product\n\t6) Add giveaway deal\n\t7) Back");
        int choice=Integer.parseInt(FlipZon.input());
        switch(choice)
        {
            case 1:
            add();
            this.display_menu();
            break;
            case 2:
            delete();
            this.display_menu();
            break;
            case 3:
            add_product();
            this.display_menu();
            break;
            case 4:
            delete_product();
            this.display_menu();
            break;
            case 5:
            set_discount();
            this.display_menu();
            break;
            case 6:
            giveaway_deals new_deal=new giveaway_deals(password, choice, password, choice, choice);
            FlipZon.giveaway_deals_list.add(new_deal);
            this.display_menu();
            break;
            case 7:
            FlipZon.display_menu();
            break;
            default:
            System.out.println("You have entered an incorrect option! Please enter the correct option 1-7\n");
            this.display_menu();

        }
    }
    int category_Id_checker(int Id)throws IOException
    {
        for(int i=0;i<FlipZon.categories_list.size();i++)
            {
                if(FlipZon.categories_list.get(i).Id==Id)
                {
                    System.out.println("Dear Admin, this category Id is already used!! Please set a different and a unique Id");
                    Id=Integer.parseInt(FlipZon.input());
                    Id=category_Id_checker(Id);
                }
            }
        return Id;
    }
    public void add()throws IOException
    {
        System.out.println("To Add category :\nAdd category ID : ");
        int Id=Integer.parseInt(FlipZon.input());
        Id=category_Id_checker(Id);
        System.out.println("Add the name of the category :");
        String name=FlipZon.input();
        Category category=new Category(name,Id);
        FlipZon.categories_list.add(category);
        System.out.println("New Category Added !!\n");
        category.add();
    }
    public void delete()throws IOException
    {
        System.out.println("Enter the name of the category to be deleted : ");
        String name=FlipZon.input();
        int Id=Integer.parseInt(FlipZon.input());
        int counter=0;
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            if(FlipZon.categories_list.get(i).Id==Id&&FlipZon.categories_list.get(i).name.equals(name))
            {
                counter=1;
                FlipZon.categories_list.remove(i);
                break;
            }
        }
        if(counter==0)
        {
            System.out.println("The category not found please enter the correct category Id or name or both!!");
            this.delete();
        }
        else{
            System.out.println("The entire category has been successfully deleted");
            this.display_menu();
        }
    }
    public void add_product()throws IOException
    {
        System.out.println("Enter the Category Id where the product is to be added");
        int Id=Integer.parseInt(FlipZon.input());
        int counter=0;
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            if(FlipZon.categories_list.get(i).Id==Id)
            {
                FlipZon.categories_list.get(i).add();
                counter=1;
            }
        }
        if(counter==0)
        {
            System.out.println("Sorry the entered category ID doesn't exist, we need to add a new category then");
            add();
        }

    }
    public void delete_product()throws IOException
    {
        System.out.println("Enter the Category Id where the product is to be added");
        int Id=Integer.parseInt(FlipZon.input());
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            if(FlipZon.categories_list.get(i).Id==Id)
            {
                FlipZon.categories_list.get(i).delete();
                if (FlipZon.categories_list.get(i).products_list.size()<1)
                {
                    System.out.println("The category is Empty!!\nEnter 1 to add product and 2 to remove product");
                    int choice=Integer.parseInt(FlipZon.input());
                    if(choice==1)
                    {
                        FlipZon.categories_list.get(i).add();
                    }
                    else{
                        FlipZon.categories_list.remove(i);
                    }
                }
                break;
            }
        }
    }
    public void set_discount()throws IOException
    {
        System.out.println("Enter the Product Id :");
        float Id=Float.parseFloat(FlipZon.input());
        int counter=0;
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            for(int j=0;j<FlipZon.categories_list.get(i).products_list.size();j++)
            {
                if(FlipZon.categories_list.get(i).products_list.get(j).Id==Id)
                {
                    Product product=FlipZon.categories_list.get(i).products_list.get(j);
                    System.out.println("Enter the discount for normal :");
                    product.discount_normal=Integer.parseInt(FlipZon.input());
                    System.out.println("Enter the discount for elite :");
                    product.discount_elite=Integer.parseInt(FlipZon.input());
                    System.out.println("Enter the discount for prime :");
                    product.discount_prime=Integer.parseInt(FlipZon.input());
                    counter=1;
                    break;
                }
            }
            if(counter==1)
            {
                break;
            }
        }
        if(counter==0)
        {
            System.out.println("Sorry the product cannot be found, wrong product ID entered!!");
        }
    }
}
class Category implements FlipZon_Utilities{
    String name;
    int Id;
    ArrayList<Product> products_list =new ArrayList<Product>();
    Category(String name, int Id)
    {
        this.name=name;
        this.Id=Id;
    }
    float product_Id_checker(float product_Id)throws IOException
    {
        for(int i=0;i<products_list.size();i++)
            {
                if(products_list.get(i).Id==product_Id)
                {
                    System.out.println("Dear Admin, this product Id is already used!! Please set a different and a unique Id");
                    product_Id=Float.parseFloat(FlipZon.input());
                    product_Id=product_Id_checker(product_Id);
                }
            }
            return product_Id;
    }
    public void add()throws IOException
    {
        System.out.print("Enter the Product Details :\nName: ");
        String product_name=FlipZon.input();
        System.out.print("\nID: ");
        float product_Id=Float.parseFloat(FlipZon.input());
        product_Id=product_Id_checker(product_Id);
        System.out.print("\nPrice: ");
        int price=Integer.parseInt(FlipZon.input());
        System.out.println("No of Stocks:");
        int no_of_stocks=Integer.parseInt(FlipZon.input());
        System.out.println("Description: ");
        String description="",line="\n";
        while(!(line.equals("")))
        {
          line=FlipZon.input();  
          description+=line+"\n";
        }
        description=description.trim().substring(0,description.length()-2);
        products_list.add(new Product(product_name,product_Id,description,price,no_of_stocks));
        System.out.println("New Product successfully added");
    }
    public void delete()throws IOException
    {
        System.out.println("Enter the Product Id to be deleted : ");
        float Id=Float.parseFloat(FlipZon.input());
        for(int i=0;i<products_list.size();i++)
        {
            if(products_list.get(i).Id==Id)
            {
                products_list.remove(i);
            }
        }
    }
}
class Product{
    String name,description;
    float Id;
    int no_of_stocks,price,cart_quantity,cart_price;
    float discount_normal,discount_elite, discount_prime;
    public float price_normal;
    public float price_elite;
    public float price_prime;
    Product(String name, float Id, String description, int price, int no_of_stocks)
    {
        this.name=name;
        this.Id=Id;
        this.description=description;
        this.price=price;
        this.no_of_stocks=no_of_stocks;
        this.discount_elite=0;
        this.discount_normal=0;
        this.discount_prime=0;
        this.cart_price=0;
        this.cart_quantity=0;
    }
    void get_details()
    {
        System.out.println("Product Details:\nName of Product :"+name+"\nProduct Id :"+Id+"\nDescription :"+description+"\nPrice :"+price+"\nNo of Stocks :"+no_of_stocks);
    }
    void get_details_customer()
    {
        System.out.println("Product Details:\nName of Product :"+name+"\nProduct Id :"+Id+"\nDescription :"+description+"\nPrice :"+price*cart_quantity);
    }
}
class giveaway_deals extends Product    
{
    static int count=1;
    giveaway_deals(String name, float Id, String description, int price, int no_of_stocks)throws IOException {
        super(name, Id, description, price, no_of_stocks);
        this.create_deal();
        this.Id=count;
        this.cart_price=0;
        this.cart_quantity=0;
        count++;
    }
    Product product1, product2;
    int price_normal,price_elite,price_prime;
    void create_deal()throws IOException
    {
        System.out.println("Enter the Product ID of the first product : ");
        float Id1=Float.parseFloat(FlipZon.input());
        System.out.println("Enter the Product ID of the second product : ");
        float Id2=Float.parseFloat(FlipZon.input());
        int counter=0;
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            for(int j=0;j<FlipZon.categories_list.get(i).products_list.size();j++)
            {
                if(FlipZon.categories_list.get(i).products_list.get(j).Id==Id1)
                {
                    product1=FlipZon.categories_list.get(i).products_list.get(j);
                    counter=1;
                    break;
                }
            }
            if(counter==1)
            {
                break;
            }
        }
        counter=0;
        for(int i=0;i<FlipZon.categories_list.size();i++)
        {
            for(int j=0;j<FlipZon.categories_list.get(i).products_list.size();j++)
            {
                if(FlipZon.categories_list.get(i).products_list.get(j).Id==Id2)
                {
                    product2=FlipZon.categories_list.get(i).products_list.get(j);
                    counter=1;
                    break;
                }
            }
            if(counter==1)
            {
                break;
            }
        }
        System.out.println("For Normal Customer");
        price=product1.price+product2.price;
        while(price>=product1.price+product2.price)
        {
            System.out.println("Enter the combined price for all products combined which should be less than their actual combined price:");
            price=Integer.parseInt(FlipZon.input());
            if(price>=product1.price+product2.price)
            {
                System.out.println("The entered price is higher or equal to the combined price !! Please enter the lessened price");
            }
        }
        price_normal=price;
        System.out.println("For Elite Customer");
        price=product1.price+product2.price;
        while(price>=product1.price+product2.price)
        {
            System.out.println("Enter the combined price for all products combined which should be less than their actual combined price:");
            price=Integer.parseInt(FlipZon.input());
            if(price>=product1.price+product2.price)
            {
                System.out.println("The entered price is higher or equal to the combined price !! Please enter the lessened price");
            }
        }
        price_elite=price;
        System.out.println("For Prime Customer");
        price=product1.price+product2.price;
        while(price>=product1.price+product2.price)
        {
            System.out.println("Enter the combined price for all products combined which should be less than their actual combined price:");
            price=Integer.parseInt(FlipZon.input());
            if(price>=product1.price+product2.price)
            {
                System.out.println("The entered price is higher or equal to the combined price !! Please enter the lessened price");
            }
        }
        price_prime=price;
        System.out.println("The deal has been successully created");
    }
    @Override
    void get_details_customer()
    {
        System.out.println("Giveaway Deal-");
        product1.get_details_customer();
        product2.get_details_customer();
        System.out.println("Giveaway Deal ends");
        System.out.println("Price-\nNormal -"+price_normal+"\nPrime -"+price_prime+"\nElite -"+price_elite);
    }
    void display()
    {
        System.out.println("The deal includes : "+this.product1.name+" and "+this.product1.name+" at a combined price of Rs."+this.price);
    }
}
class Customer implements FlipZon_Utilities{
    String name,password,status,delivery_time;
    float balance,delivery_charge;
    int discount;
    ArrayList<Product> Cart = new ArrayList<Product>();
    ArrayList<Integer> coupons_list= new ArrayList<Integer>();
    Customer(String name, String password)
    {
        this.name=name;
        this.password=password;
        this.balance=1000;
        this.status="NORMAL";
        this.delivery_time="The order will be placed within 7-10 days";
        this.delivery_charge=0;
    }
    void display_menu()throws IOException
    {
        System.out.println("Welcome "+name+"!!\n\t1)Browse Products\n\t2)Browse Deals\n\t3)Add a product to cart\n\t4)Add products in deal to Cart\n\t5)View Coupons\n\t6)Check Account Balance\n\t7)View Cart\n\t8)Empty Cart\n\t9)View Coupons\n\t10) Upgrade Customer status\n\t11) Add amount to wallet\n\t12) Back");
        int choice=Integer.parseInt(FlipZon.input());
        switch (choice)
        {
            case 1:
            FlipZon.catalog_products();
            this.display_menu();
            break;
            case 2:
            FlipZon.catalog_deals();
            this.display_menu();
            break;
            case 3:
            System.out.print("Enter the product ID of the Product to be added : ");
            float id=Float.parseFloat(FlipZon.input());
            int counter=0;
            for(int i=0;i<FlipZon.categories_list.size();i++)
            {
                for(int j=0;j<FlipZon.categories_list.get(i).products_list.size();j++)
                {
                    if(FlipZon.categories_list.get(i).products_list.get(j).Id==id)
                    {
                        this.add(FlipZon.categories_list.get(i).products_list.get(j),FlipZon.categories_list.get(i));
                        counter=1;
                        break;
                    }
                }
                if(counter==1)
                {
                    break;
                }
            }
            if(counter==0)
            {
                System.out.println("Sorry the product cannot be found, wrong product ID entered!!");
            }
            this.display_menu();
            break;
            case 4:
            FlipZon.catalog_deals();
            System.out.print("Enter the product ID of the Giveaway Deal to be added : ");
            int ID=Integer.parseInt(FlipZon.input());
            counter=0;
            for(int i=0;i<FlipZon.giveaway_deals_list.size();i++)
            {
                if(FlipZon.giveaway_deals_list.get(i).Id==ID)
                {
                     this.add(FlipZon.giveaway_deals_list.get(i));
                }
            }
            this.display_menu();
            break;
            case 5:
            this.view_coupons();
            this.display_menu();
            break;
            case 6:
            this.view_balance();
            this.display_menu();
            break;
            case 7:
            this.view_cart();
            this.display_menu();
            break;
            case 8:
            this.delete();
            this.display_menu();
            break;
            case 9:
            this.checkout_cart();
            this.display_menu();
            break;
            case 10:
            this.upgrade_status();
            this.display_menu();
            break;
            case 11:
            System.out.println("Enter the amount to be added");
            float amt=Float.parseFloat(FlipZon.input());
            this.add(amt);
            this.display_menu();
            break;
            case 12:
            System.out.println("Bye Bye "+this.name+"!!");
            FlipZon.Customer_menu();
            break;
            default:
            System.out.println("You have entered a wrong choice!!");
            this.display_menu();
        }
        
    }
    void upgrade_status()throws IOException
    {
        System.out.print("Current Status :"+this.status);
        System.out.print("\nEnter the status to be updated to :");
        String new_status=FlipZon.input();
        if(new_status.toUpperCase().equals("ELITE")&&!(new_status.equals(status))&&this.balance>=300)
        {
            this.status=new_status;
            this.delivery_time="The order will be placed within 2 days";
            this.discount=10;
            this.delivery_charge=0;
            this.balance-=300;
            System.out.println("Status updated!!");
        }
        else if(new_status.toUpperCase().equals("PRIME")&&!(new_status.equals(status))&&this.balance>=200)
        {
            this.status=new_status;
            this.delivery_time="The order will be placed within 3-6 days";
            this.discount=5;
            this.delivery_charge=2;
            this.balance-=200;
            System.out.println("Status updated!!");
        }
        else 
        {
            System.out.println("Status cannot be updated due to wrong status input or already in that status or the balance is insufficeint");
        }
    }
    public void add(Product product,Category category)throws IOException
    {
        Cart.add(product);
        System.out.println("Enter the quantity :");
        int quantity=Integer.parseInt(FlipZon.input());   
        while(quantity>product.no_of_stocks)
        {
            System.out.println("Please enter a quantity <="+product.no_of_stocks);
            quantity=Integer.parseInt(FlipZon.input());
        }
        product.no_of_stocks-=quantity;
        product.cart_quantity+=quantity;
        product.cart_price+=quantity*product.price;   
        if(product.no_of_stocks<1)
        {
            category.products_list.remove(product);
        }
        System.out.println("Product added to Cart!!");
    }
    public void add(giveaway_deals deal)
    {
        if (deal.product1.no_of_stocks>0&&deal.product2.no_of_stocks>0)
        {
            Cart.add(deal);
            deal.product1.no_of_stocks-=1;
            deal.product2.no_of_stocks-=1;
            System.out.println("Deal Added to Cart!!");
        }
        else{
            System.out.println("Deal cannot be added to Cart!!");
        }
    }
    public void view_coupons()
    {
        if(this.status=="NORMAL" || this.coupons_list.size()==0)
        {
            System.out.println("No Coupons Available");
        }
        else {
            System.out.println("All available coupons are :");
            for(int i=0;i<this.coupons_list.size();i++)
            {
                System.out.println(this.coupons_list.get(i)+"%");
            }
        }
    }
    public void view_cart()
    {
        System.out.println("The Cart has -");
        for(int i=0;i<this.Cart.size();i++)
        {
            Cart.get(i).get_details_customer();
        }
    }
    public void checkout_cart()
    {
        System.out.println("Proceeding to checkout Details");
        this.view_cart();
        float total=0;
        float delivery=0;
        int max_coupon=0;
        for(int i=0;i<coupons_list.size();i++)
        {
            if(max_coupon<coupons_list.get(i))
            {
                max_coupon=coupons_list.get(i);
            }
        }
        int prev_max=max_coupon;
        int counter=0;
        if(status=="ELITE")
        {
            for(int i=0;i<Cart.size();i++)
            {
                if(Cart.get(i) instanceof Product)
                {
                    if(max_coupon<Cart.get(i).discount_elite)
                    {
                        max_coupon=(int) Cart.get(i).discount_elite;
                    }
                    if(max_coupon<this.discount)
                    {
                        max_coupon=this.discount;
                    }
                    if(prev_max==max_coupon)
                    {
                        counter=1;
                    }
                    total+=Cart.get(i).cart_price-Cart.get(i).cart_price*max_coupon/100.0;
                    Cart.get(i).cart_price=0;
                    Cart.get(i).cart_quantity=0;
                }
                else 
                {
                    total+=Cart.get(i).price_elite;
                }
                max_coupon=prev_max;   
            }
            delivery=100;
            total+=delivery;
            if(total>balance)
            {
                System.out.println("Insufficient Balance !! Please Try Again");
            }
            else
            {
                if(counter==1)
                {
                    this.coupons_list.remove(prev_max);
                }
                System.out.println("Your order is placed sucessfully !!");
                System.out.println("Delivery Charges : Rs"+delivery);
                System.out.println("Total Cost : Rs"+total);
                System.out.println("Your order is placed and will be delivered within 2 days");
                if(total>5000)
                {   
                    int x=(int)(Math.random()*2)+3;
                    int y;
                    System.out.println("You have won the following coupons ");
                    for(int i=1;i<=x;i++)
                    {
                        y=(int)(Math.random()*11)+5;
                        this.coupons_list.add(y);
                        System.out.print(y+"% ,");
                    }
                }
                else
                {
                    System.out.println("No coupons won!!");
                }
                balance-=total;
            }
        }
        if(status=="PRIME")
        {
            for(int i=0;i<Cart.size();i++)
            {
                if(Cart.get(i) instanceof Product)
                {
                    if(max_coupon<Cart.get(i).discount_elite)
                    {
                        max_coupon=(int) Cart.get(i).discount_normal;
                    }
                    if(max_coupon<this.discount)
                    {
                        max_coupon=this.discount;
                    }
                    if(prev_max==max_coupon)
                    {
                        counter=1;
                    }
                    total+=Cart.get(i).cart_price-Cart.get(i).cart_price*max_coupon/100.0;
                    Cart.get(i).cart_price=0;
                    Cart.get(i).cart_quantity=0;
                }
                else 
                {
                    total+=Cart.get(i).price_prime;
                }
                max_coupon=prev_max;    
            }
            delivery=100+(2*total)/100;
            total+=delivery;
            if(total>balance)
            {
                System.out.println("Insufficient Balance !! Please Try Again");
            }
            else
            {   
                if(counter==1)
                {
                    this.coupons_list.remove(prev_max);
                }
                System.out.println("Your order is placed sucessfully !!");
                System.out.println("Delivery Charges : Rs"+delivery);
                System.out.println("Total Cost : Rs"+total);
                System.out.println("Your order is placed and will be delivered within 3-6 days");
                if(total>5000)
                {   
                    int x=(int)(Math.random()*2)+1;
                    int y;
                    System.out.println("You have won the following coupons ");
                    for(int i=1;i<=x;i++)
                    {
                        y=(int)(Math.random()*11)+5;
                        this.coupons_list.add(y);
                        System.out.print(y+"% ,");
                    }
                }
                else
                {
                    System.out.println("No coupons won!!");
                }
                balance-=total;
            }
        }
        if(status=="NORMAL")
        {
            for(int i=0;i<Cart.size();i++)
            {
                if(Cart.get(i) instanceof Product)
                {
                    if(max_coupon<Cart.get(i).discount_elite)
                    {
                        max_coupon=(int) Cart.get(i).discount_normal;
                    }
                    if(max_coupon<this.discount)
                    {
                        max_coupon=this.discount;
                    }
                    total+=Cart.get(i).cart_price-Cart.get(i).cart_price*max_coupon/100.0;
                    Cart.get(i).cart_price=0;
                    Cart.get(i).cart_quantity=0;
                }
                else 
                {
                    total+=Cart.get(i).price_normal;
                }
                max_coupon=prev_max;   
            }
            delivery=100+(5*total)/100;
            total+=delivery;
            if(total>balance)
            {
                System.out.println("Insufficient Balance !! Please Try Again");
            }
            else
            {
                System.out.println("Your order is placed sucessfully !!");
                System.out.println("Delivery Charges : Rs"+delivery);
                System.out.println("Total Cost : Rs"+total);
                System.out.println("Your order is placed and will be delivered within 7-10 days");
                System.out.println("No coupons won!!");
                balance-=total;
            }
        }
    }
    public void delete()
    {
        while(Cart.size()>0)
        {
            Cart.remove(0);
        }
        System.out.println("Cart Emptied!!");
    }
    public void view_balance()
    {
        System.out.println("Current account balance : Rs"+balance);
    }
    public void add(float amt)
    {
        balance+=amt;
        System.out.println("Amount added and balance updated successfully");
    }
    @Override
    public void add() throws IOException {
    }
}