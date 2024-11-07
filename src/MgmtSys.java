import java.util.*;

public class MgmtSys {

    static Scanner sc = new Scanner(System.in);
    HashMap<String,Integer> index;
    ArrayList<Contact> contactList;
    int pos;
    Contact contact;

     MgmtSys()
    {
        index= new HashMap<>();
        contactList= new ArrayList<>();
        pos = 0;
        contact = null;
    }

    boolean isPresent(String name) {    return index.containsKey(name);     }

    void display(Contact c) {   System.out.printf("\nName:%s\nPhone Number 1:%s\nPhone Number 2:%s\n\n",c.name,c.pno1,c.pno2);    }

    void searchName(String name)
    {
        if(!isPresent(name))//If not Found
            System.out.println("Contact not found");
        else {
            pos = index.get(name);
            display(contactList.get(pos));
        }
    }

    void addCont(String name)
    {
        Long pno1,pno2;
        if(isPresent(name))  System.out.println("Contact already exists\tPress Enter to Continue");
        else {
            try{
                System.out.println("Enter 2 Phone Numbers without Country Code: ");
                 pno1 = sc.nextLong();
                 //sc.nextLine();
                 pno2 = sc.nextLong();
                 if(isValid(pno1) || isValid(pno2))
                     throw new Exception("Invalid");
            }   catch(Exception e ){
                System.out.println("Invalid Phone Number");
                return;
            }
            contact = new Contact(name, pno1,pno2);
            contactList.add(contact);
            index.put(name, contactList.size()-1);

            System.out.println("Contact added:");
            display(contact);
        }
    }

        boolean isValid(Long pno) {     return (    (pno/(Math.pow(10,11)) ==0) && (pno>=Math.pow(10,10))   );  }

    public static void main(String[] args) {
        int ch=-1;
        MgmtSys ms = new MgmtSys();
        String name;

        while(ch!=0) {
            try {
                System.out.println("Enter 1 to Search a contact\n 2 to Add a Contact\n 3 to Delete a Contact\n0 to terminate");
                ch = Integer.parseInt(sc.nextLine());

                if(ch==0) break;

                System.out.println("Enter the Name ");
                name = sc.nextLine();

                switch (ch) {
                    case 1:
                        ms.searchName(name);
                        break;

                    case 2:
                        ms.addCont(name);
                        sc.nextLine();
                        break;

                    case 3:
                        break;

                    default:
                        System.out.println("Invalid Choice");
                }

            } catch (Exception e) {
                System.out.println("Invalid Choice");
            }

        }
        System.out.println("|---------------Program Terminated---------------|");

    }
}