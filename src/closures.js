function foo(arguments ){

}

foo(arguments );

var Activation=new Object();
Activation.arguments= arguments;
var scope=foo.scope;
scope.push(Activation);
foo(arguments ).context.scope=scope;

Activation根据函数的形式参数列表设置属性， 并根据实际参数赋值
在函数内部定义的内部函数以函数对象的形式给Activation赋值，属性名是内部函数的方法名
在函数内部定义的局部变量也以属性的形式赋值给Activation//初始赋值undefined 真正的赋值发生在开始执行代码body时