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

    void searchName(String name) {  //LookUp Time O(1)
        if(!isPresent(name))//If not Found
            System.out.println("Contact not found");
        else {
            pos = index.get(name);
            display(contactList.get(pos));
        }
    }


    void addCont(String name) {
        Long pno1,pno2;
        if(isPresent(name))  System.out.println("Contact already exists\tPress Enter to Continue");
        else {

            try{
                System.out.println("Enter 2 Phone Numbers without Country Code: ");
                 pno1 = sc.nextLong();
                 pno2 = sc.nextLong();
                 if(isNotValid(pno1) || isNotValid(pno2) )
                     throw new Exception("Invalid");
            }
            catch(Exception e ){
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
    boolean isNotValid(Long pno) {
        int n = 0;
        while(pno>0)
        {
            n++;
            pno/=10;
        }
        return n!=10;
     }

    void dispAll() {
         if(contactList.isEmpty())
             System.out.println("Contact list is empty");
         else for(Contact i: contactList)
            display(i);
    }

    void deleteCont(String name) {
         if(!isPresent(name))
         {
             System.out.println("Contact not found");
             return;
         }
         pos = index.get(name);
         index.remove(name);
         updateIndex();

         System.out.println("Contact Deleted: ");
         display(contactList.get(pos));

         contactList.remove(pos);
    }
    void updateIndex() {
         for(String key: index.keySet())
         {
             int val = index.get(key);
             if(val>pos) // The index of this number is after the deleted index, therefore needs to be updated
                 index.put(key ,val-1);
         }
    }

    public static void main(String[] args) {
        int ch;
        MgmtSys ms = new MgmtSys();
        String name;

        while(true) {
                System.out.println("Enter 0 to terminate\n1 to Search a contact\n 2 to Add a Contact\n 3 to View Contact List\n 4 to Delete a Contact");
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
                        ms.dispAll();
                        break;

                    case 4:
                        ms.deleteCont(name);
                        break;
                    default:
                        System.out.println("Invalid Choice");
                }

        }
        System.out.println("|---------------Program Terminated---------------|");
    }
}

