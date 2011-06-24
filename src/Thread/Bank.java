package Thread;


public class Bank {
    int[] account;
    protected boolean _maintenanceThreadStarted=true;
    private Object lock=new Object();
    
    public Bank(int num) {
        account=new int[num];
        for (int i = 0; i < account.length; i++) {
            account[i]=1000;
        }
    }
    
    public int getTotalBalance(){
        int total=0;
        for (int i = 0; i < account.length; i++) {
            total+=account[i];
        }
        return total;
    }
    
    public void transfer(int from,int to,int money){
        synchronized (lock) {
            System.out.println(Thread.currentThread());
            if(account[from]>money){
                account[from]-=money;
                account[to]+=money;
                System.out.println(money+" from account-"+from +" to "+to+"    balance = "+getTotalBalance());
            }else{
                System.out.println("not enough");
            }
        }
    }
    
    public void startThread(){
        (new Thread() {
            public void run() {
                while (_maintenanceThreadStarted) {
                    int from=(int)(account.length*Math.random());
                    int to=(int)(account.length*Math.random());
                    transfer(from, to, (int)(100*Math.random()));
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }
    
    public void stopMaintenanceThread() {
        _maintenanceThreadStarted = false;
    }
    
    public static void main(String[] args) throws Exception {
        Bank a=new Bank(10);
        for (int i = 0; i < 2; i++) {
            a.startThread();
        }
        Thread.sleep(50000);
        a.stopMaintenanceThread();
    }
}
