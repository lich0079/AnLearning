package algorithm;

import java.util.*;
import javax.swing.*;

class Testquicksort{
		
		static int div(int a[],int b,int e){
				int i,j,k,m;
				m=a[b];
				while(true){
					for(i=b+1;i<=e && a[i]<=m;i++){}
					for(j=e;a[j]>m;j--){}
					if(i>=j)break;
					k=a[i];
					a[i]=a[j];
					a[j]=k;
				}
				k=a[j];
				a[j]=a[b];
				a[b]=k;
				
				return j;//��Ϊj<i����j������Ϊe  ���ֻ��Ϊe-1����{2��1��1��1��1��1��1��3}
				//if {2,3,3,3,3,3,3}��ʱj=0;  �ٴ�quicksortʱ����Ϊif(b<e)  0<-1 ����ݹ�
			}
		
		static void quicksort(int a[],int b,int e){
				if(b<e){
						int m=div(a,b,e);
						quicksort(a,b,m-1);
						quicksort(a,m+1,e);
					}
			}
		
		
		public static void main(String[] args){
			int num=10000;
			int[] a=new int[num];
			int[] b=new int[num];
			Random r=new Random();
			//��a���� b����ͬ��ĸ�ֵ ����ӡ
			for(int i=0;i<a.length;i++){
					a[i]=r.nextInt(num)+1;
					b[i]=a[i];
					System.out.print(a[i]+" ");
				}
				System.out.println();
				for(int i=0;i<b.length;i++){
					System.out.print(b[i]+" ");
				}
				System.out.println();
				
			//a�������quicksort	
			long atime=System.currentTimeMillis();
			quicksort(a,0,num-1);
			atime=System.currentTimeMillis()-atime;
				
			//��ӡ������a����
			System.out.println();
			for(int i=0;i<a.length;i++){
					System.out.print(a[i]+" ");
				}
				
			
			
			//b�������selectsort
			long btime=System.currentTimeMillis();
			for(int z=0;z<b.length;z++){
					int min=100000000,mi=0,t;
					for(int y=z;y<b.length;y++){
							if(b[y]<min){
									min=b[y];
									mi=y;
								}
						}
						t=b[z];
						b[z]=b[mi];
						b[mi]=t;
				}
				btime=System.currentTimeMillis()-btime;		
			
			//��ӡ������b����
				System.out.println();
				System.out.println();
				
				for(int i=0;i<b.length;i++){
					System.out.print(b[i]+" ");
				}
				
				
				//���ʱ��
				System.out.println("\nquicksort: "+atime+" ms");
				System.out.println("\nselectsort: "+btime+" ms");
				
				System.out.println("\n��"+num+"λ���������ʱ2���㷨ʱ���Ϊ: "+(btime-atime)+" ms");
		}
		
	}