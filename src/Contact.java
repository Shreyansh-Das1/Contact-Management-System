public class Contact {
    String name;
    String pno1, pno2;
    int index;
    Contact(String name,Long pno1,Long pno2){
        this.name=name;
        this.pno1   =  Long.toString(pno1);
        this.pno2   =  Long.toString(pno2);
    }
}
