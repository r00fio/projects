
Thread lifecycle overhead. Thread creation and teardown are not free
. The actual overhead varies across platforms, but  thread  creation 
takes  time,  introducing  latency  into  request  processing,  and 
requires  some  processing  activity  by  the  JVM  and  OS.  If  requests  are 
frequent  and  lightweight,  as  in  most  server  applications,  creating  a 
new  thread  for  each  request can consume significant computing resources. 