function foo(arguments ){

}

foo(arguments );

var Activation=new Object();
Activation.arguments= arguments;
var scope=foo.scope;
scope.push(Activation);
foo(arguments ).context.scope=scope;

Activation���ݺ�������ʽ�����б��������ԣ� ������ʵ�ʲ�����ֵ
�ں����ڲ�������ڲ������Ժ����������ʽ��Activation��ֵ�����������ڲ������ķ�����
�ں����ڲ�����ľֲ�����Ҳ�����Ե���ʽ��ֵ��Activation//��ʼ��ֵundefined �����ĸ�ֵ�����ڿ�ʼִ�д���bodyʱ