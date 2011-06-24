package Thread;

import java.util.Random;

public class Transfer implements Runnable {

    Bank b;
    
    public Transfer(Bank b) {
        this.b=b;
    }
    
    
    public void transfer(int from,int to,int money){
        System.out.println(Thread.currentThread());
        if(b.account[from]>money){
            b.account[from]-=money;
            b.account[to]+=money;
            System.out.println(money+" from account-"+to +" to "+to+"    balance = "+b.getTotalBalance());
        }else{
            System.out.println("not enough");
        }
    }
    
    public void run() {
        while (true) {
            Random r=new Random(System.currentTimeMillis());
            int from=r.nextInt(10);
            transfer(from, r.nextInt(10), r.nextInt(100));
        }
    }

    
    public static void main(String[] args) {
        Bank a=new Bank(10);
        for (int i = 0; i < 10; i++) {
            Thread t1=new Thread(new Transfer(a));
            t1.start();
        }
        
    }
}
